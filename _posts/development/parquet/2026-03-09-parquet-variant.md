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




# Variant는 Parquet 표준이 아니다.

사실 `VARIANT` 타입은 Parquet 스펙이 아닙니다. 각 데이터 엔진(delta-lake, iceberg, snowflake, spark) 등에서 자체적으로 `variant` 타입을 정의하고 그걸 Parquet 위에서 사용하는 방식 입니다 ㅋㅋ...




https://parquet.apache.org/docs/file-format/types/variantencoding/
