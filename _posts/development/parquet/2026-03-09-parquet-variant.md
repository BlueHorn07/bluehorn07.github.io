---
title: "Parquet Variant에 대해 살펴보기"
toc: true
toc_sticky: true
excerpt: ""
---

데이터 엔지니어로서 일을 한지 벌써 3년이 넘었다...! 그런데 그동안 맨날 써온 `.parquet` 포맷을 내가 제대로 알고 있는게 맞나?라는 생각이 들었다. Parquet도 포맷이 v2까지 나온 꽤 오래되고 유서 깊은 포맷이고, 또 최근에는 Parquet Variant 타입 관련해서도 작업을 하고 있는데, 내가 이 포맷에 대해 자신 있게 얘기할 수 있는지 스스로 돌아보게 되었다. 그래서 이번 기회에 Parquet를 다시 보고 내가 부족했던 부분들은 채워넣어보려고 한다.

이번에는 마지막으로 Parquet를 다시 살펴보게 만든 `VARIANT` 타입에 대해서 살펴본다! 24년 10월에 처음 반영된 녀석으로 비교적 최근에 도입된 기능이다!

# Hello Variant!

`VARIANT` 타입은 한 컬럼에 다양한 값을 타입 상관 없이 담을 수 있다. 한 행 숫자를 넣었다가, 다른 행에는 문자열이 나와도 전혀 상관 없다.

```
row 0: 42
row 1: 3.14
row 2: "hello world!"
row 3: null
row 4: true
```

## metadata

Variant 타입의 기본 구조는 아래와 같다.

```
optional group event (VARIANT) {
  required binary metadata;
  required binary value;
}
```

`metadata`에는 Variant 타입 컬럼에 작성된 JSON 포맷 데이터에 등장한 모든 키 값을 인덱싱한다. 만약 Variant 컬럼의 데이터가

```
row 1: {"event":"login","user":"alice"}
row 2: {"event":"purchase","amount":120.5}
row 3: ["unexpected","array","data"]
row 4: "simple string event"
row 5: 12345
```

였다면, `metadata` 값은

```
metadata={"event": 0, "user": 1, "amount": 2}
```

이렇게 된다.

만약, Variant 컬럼에 모든 값이 primitive 타입이었다면, `metadata`는 empty dictionary `{}`가 된다.

## value

value에는 해당 타입의 실제 데이터가 들어간다. 이때, 바로 데이터가 들어가지는 않고, 데이터에 대한 타입 정보가 먼저 들어간다. (Variant에 타입 제한 없이 데이터를 넣을 수 있기 때문)

```
           7                                  2 1          0
          +------------------------------------+------------+
value     |            value_header            | basic_type |  <-- value_metadata
          +------------------------------------+------------+
          |                                                 |
          :                   value_data                    :  <-- 0 or more bytes
          |                                                 |
          +-------------------------------------------------+
```


value metadata에서 `basic_type`은 2 bit로 4가지 타입을 표현한다.

- `basic_type=0`
  - Primitive (int, string, bool)
- `basic_type=1`
  - Short String (최적화된 짧은 문자열)
- `basic_type=2`
  - Object
- `basic_type=3`
  - Array

`value_header`는 `basic_type`에 따라 들어가는 정보가 다른다.

`basic_type=0`(primitive)라면, 구체적인 타입 정보(int8, int16, float, timestamp)가 들어나고, `basic_type=2,3`(Object, Array)라면 크기 정보를 넣는다.

- `basic_type=3`(Array)
  - 몇 개 element가 담겨 있는지
- `basic_type=2`(Object)
  - 몇 개 key 값이 있는지

눈치가 빠르다면, Nested JSON `{"a": [1, 2]}`인 경우를 어떻게 처리해야 할지 궁금할 텐데...! 요게 Variant에서 어려운 부분이라 바로 다음 섹션에서 자세히 살펴보자!

# Nested JSON의 경우

JSON 포맷은 자유롭기 때문에, 1st key 아래에 2nd key, 3rd key, ... 등등으로 존재할 수 있다. `VARIANT` 타입에서 Nested JSON을 어떻게 표현하는지 이해한다면, 이제 이걸 다 이해한 거라고 할 수 있다.

Variant 타입의 행으로 `{"a": {"b": 1}}`라는 값이 들어왔고, 이걸 Variant 인코딩 해보자.

일단 기존에 JSON Variant를 처리하는 것처럼 `metadata`와 `value`를 구성해야 한다. `metadata`에는 key dictionary가 들어가며, 지금은 `metadata=["a", "b"]`으로 초기화 된다.

root value는 중첩 JSON이기 때문에, `basic_type=2(Object)`가 된다.

그 다음 레벨인 `{"b": 1}`은 다시 JSON이 된다. 그래서 여기도 `basic_type=2(Object)`가 된다. 마지막으로 제일 안쪽인 `1`이 남고 이건 `basic_type=0(Primitive)`가 된다.

```
metadata = ["a", "b"]

root_value = OBJECT(
  keys=[id("a")],
  values=[
    OBJECT(
      keys=[id("b")],
      values=[
        PRIMITIVE_INT(1)
      ]
    )
  ]
)
```

데이터를 이렇게 저장하면, 쿼리 떄 유리할까?

`$.a.b`라는 쿼리가 들어왔을 때, 각 Variant 값의 `metadata`를 먼저 읽는다. `metadata`에 `"a"`, `"b"` 두 키가 모두 존재하는지 체크한다. `metadata`가 sorted 되어 있다면, 더 빠르게 찾을 수도 있다. (metadata 정렬은 필수가 아니라고 들었다.)
 반대로 `metadata`에 해당 key를 찾을 수 없는 케이스라면, 해당 행을 스킵 하면 된다.

`metadata` 탐색을 통과 했다면, 이제 루트 `Object`에서 `"a"` 값의 위치를 찾는다. 이건 `Object.keys`에 저장된 value별 offset으로 찾아간다. 해당 offset에 저장된 데이터의 `byte_type`을 보고 어떤 타입인지 체크한다.

쿼리가 `$.a.b` 였으니, 루트 Object 내부의 데이터도 `Object` 타입이어야 한다. 이건 `byte_type`을 보고 판단하면 되고, 이제 그 Object 안에서 다시 `Object.keys`를 보고 해당 value의 offset을 찾아간다.

<br/>

딱 하나만 더 해보자! 이번에는 `{"a": [1, 2]}`다. Object 타입 안에 Array 타입이 들어갔다.

```
metadata: ["a"]

root_value = OBJECT(
  keys=[id("a")],
  values=[
    ARRAY(
      values=[
        PRIMITIVE_INT(1),
        PRIMITIVE_INT(2)
      ]
    )
  ]
)
```

<br/>

쉽게 설명하려고 하나 빼먹은게 있는데, `OBJECT` value와 `ARRAY` value를 Variant 인코딩할 때, `num_elements` 값도 기록한다.

`ARRAY` 타입이 이해하기 더 쉬워서 이걸 먼저 보면,

```
array_value_data:
  num_elements = 2
  field_offset = [0, len(v0), len(v0)+len(v1)]
  values = v0 || v1

v0 = PRIMITIVE_INT(1) encoded bytes
v1 = PRIMITIVE_INT(2) encoded bytes
```

이렇게 `field_offset`을 따로 두는 이유는 `ARRAY<INT>`와 다르게 JSON ARRAY는 내부 값의 타입을 지정하지 않는다. 그래서 PRIMITIVE_INT가 올 수도, PRIMITIVE_BYTE_ARRAY가 올 수도 있다.


`OBJECT` 타입은 이렇게 된다.

```
ex. {"a": 10, "b": [1,2]}:

metadata = ["a", "b"]

object_value_data:
  num_elements = 2
  field_id     = [id("a"), id("b")]
  field_offset = [0, len(v0), len(v0)+len(v1)]
  values       = v0 || v1

v0 = PRIMITIVE_INT(10)
v1 = ARRAY(
       values=[PRIMITIVE_INT(1), PRIMITIVE_INT(2)]
     )
```

<br/>

Variant 타입 내부 구조가 첨 보면 복잡하긴 해도, 하나씩 뜯어보면 저절로 고개가 끄덕여 진다 ㅋㅋ


# Shredded Variant

지금까지 Variant 타입이 어떻게 저장되는지 열심히 설명 했는데...! 방식이 하나 더 있습니다! *Shredding*이라고 하는 이 방식은 기존에 Parquet `STRUCT` 타입 처럼 `$.a.b` 컬럼을 미리 만들고, 저장하는 방식입니다.

다만, 이 글을 적는 26.03.10 기준으로는 아직 데이터 생태계에 완전히 통합된건 아닌 것 같습니다. [Databricks의 문서](https://docs.databricks.com/aws/en/delta/variant-shredding)에서도 Beta 기능이라고 나옵니다만, Spark 4.1 이후부터는 Variant shredding이 기본으로 활성화 될 것 같네요 흠....

그럼 언제 variant 타입을 shredded variant로 저장하는게 더 이득일까? 데이터가 JSON으로 들어오기는 해도, 공통 컬럼들이 있고, `WHERE`에서 `a.b = 1` 같이 필터링이 많다면, shredded variant로 인코딩하고 predicate pushdown의 이점을 얻는 것이 좋다고 합니다.

하지만 반대로 Variant로 저장하는 데이터의 타입이 일관적이지 않고, 저장하는 JSON 스키마의 형태도 너무너무 자유롭다면, shredded variant보다는 기존대로 unshredded variant를 쓰는게 더 나을 수 있습니다.

<br/>

variant shredding은 row group 단위로 shredding 됩니다. Row Group 마다 저장되는 Variant 컬럼의 JSON schema가 다르고 shredded 되는 필드도 달라집니다.

TODO: shredded variant는 내용 좀더 보충하고, 예시 추가하기.

# References

- https://parquet.apache.org/docs/file-format/types/variantencoding/
- https://parquet.apache.org/docs/file-format/types/variantshredding/
