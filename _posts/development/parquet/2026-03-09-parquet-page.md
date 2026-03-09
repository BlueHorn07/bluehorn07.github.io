---
title: "Parquet Page 구조"
toc: true
toc_sticky: true
excerpt: "Parquet에서 컬럼 데이터가 저장되는 최소 단위, Page"
---

데이터 엔지니어로서 일을 한지 벌써 3년이 넘었다...! 그런데 그동안 맨날 써온 `.parquet` 포맷을 내가 제대로 알고 있는게 맞나?라는 생각이 들었다. Parquet도 포맷이 v2까지 나온 꽤 오래되고 유서 깊은 포맷이고, 또 최근에는 Parquet Variant 타입 관련해서도 작업을 하고 있는데, 내가 이 포맷에 대해 자신 있게 얘기할 수 있는지 스스로 돌아보게 되었다. 그래서 이번 기회에 Parquet를 다시 보고 내가 부족했던 부분들은 채워넣어보려고 한다.

지난 포스트에선 Parquet 포맷의 구조를 살펴보았다. 이번 포스트는 Parquet에서 컬럼 데이터가 저장되는 최소 단위인 Page 구조에 대해 살펴본다.


![alt text](/images/development/parquet/parquet-row-group.png)
{: .fill style="width: 400px" .align-center .text-center }

# Dictionary Page

컬럼 데이터를 Parquet에 저장할 때, 데이터의 카디널리티에 따라서 Dictionary Encoding 하는 경우도 있다. 카디널리티가 낮다면 Dictionary Encoding 된다.

모든 primitive 타입은 Dictionary Encoding이 가능하다. Parquet write는 처음에는 Dictionary Encoding으로 데이터 쓰기를 시도한다. 그러다가 고유값이 너무 많아져서 Dictionary Page 크기가 일정 크기 이상으로 커지면, 그때는 Dictionary Encoding을 포기하고 다른 방식으로 전환한다.

spark에서는 `parquet.dictionary.page.size = 1mb` 기본값으로 세팅 되어 있고, 요 config로 조정할 수 있다. 이 크기를 넘으면, 해당 Column Chunk엔 Dictionary Page 없이 Data Page만 존재한다. 생각해보면, Column Chunk의 사이즈도 1mb로 제한되기 때문에, Dictionary Page의 최대 크기도 1mb로 제한되는것이 자연스럽긴 하다! 하지만, Data Page와 Dictionary Page는 서로 독립적인 설정이다. 두 Page의 사이즈 limit이 같은게 의도된 세팅이겠지만, 실제 동작할 때는 서로 다르게 동작한다는 것! (제대로 적은건가..!?)

- `parquet.block.size` -> Row Group의 크기 (default: 128mb)
- `parquet.page.size` -> Data Page의 크기 제한 (default: 1mb)
- `parquet.dictionary.page.size` -> Dictionary Page의 크기 제한 (default: 1mb)

컬럼 데이터의 값은 Row Group으로 나눠서 저장된다. 그런데 Row Group으로 분할된 컬럼 데이터마다 카디널리티가 다를 수 있다. 그래서, Row Group 0에서는 Column Chunk에 Diciontary Encoding이 적용되지만, Row Group 1에선 fallback -> plain encoding으로 저장되는 것도 가능하다.

<br/>

Row Group(실질적으론 Chunk Column) 내에서 해당 컬럼이 Dictionary Encoding이 가능하다면, Chunk Column은 이렇게 구성된다.

> Dictionary Page -> Data Page 0 -> Data Page 1 -> ... -> Data Page N



# Definition Level

요건 Page에 저장한 데이터에 null 값이 어디어디에 있는지 표시하는 bit array다. Parquet Page 구조에서 제일 쉬운 개념이다. ㅋㅋ

N번째 위치의 데이터가 null이냐 아니냐를 본다. 그래서 null 기반 필터를 할 때, null count를 할 때는 실제 데이터를 안 읽고 Definition Level을 보고 데이터를 접근하고 null count를 세면 된다.


# Repetition Level

모든 값이 flat한 primitive 타입에서는 repetition level이 전부 zero bit이 되기 때문에 의미가 없다. rep level이 의미가 있으려면, `ARRAY`, `STRUCT`, `VARIANT` 타입일 때다.

## ARRAY

예를 들어, `tags ARRAY<STRING>` 컬럼이 있고, 그 값이 아래와 같다고 하자.

```
row 0: tags = ["a", "b", "c"]
row 1: tags = ["d"]
```

이걸 flat하게 펼치면,

```
values:   a b c d
rep:      0 1 1 0
def:      1 1 1 1
```

이제 무슨 의미일까? 싶겠지만, 일단 rep level의 패턴에 집중하라. `rep bit = 0`이면, 새로운 배열의 시작 값이라는 거다. 즉, 배열을 시작하는 괄호 `(` 또는 `[`라는 것이다. 반대로 `rep bit = 1`이면, 이전에 열었떤 배열이 계속된다는 것이다. 즉, 콤마 `,`라는 것이다.

이제 이 내용을 가지고 위의 표현을 다시 적어보자.

```
values:   a b c d
rep:      0 1 1 0
rep*:     [ , , ][...
```

### empty array, null array

```
row 0: tags = ["a", "b", "c"]
row 1: tags = []
row 2: tags = ["d"]
```

데이터 사이에 empty array `[]`가 끼어든 케이스를 보자. 이때, def level이 빈배열과 null 배열을 구분하기 위해 이렇게 정의된다.

- `def=0`: tags 자체가 nulll
- `def=1`: tags는 존재하지만 비어있음. empty array
- `def=2`: tags에 값이 있음.

그래서 표현식이 이렇게 된다.

```
values:   a b c (empty) d
rep:      0 1 1 0       0
def:      2 2 2 1       2
```

### Nested Array

이번에는 배열인데, 중첩 배열이다. `items ARRAY<ARRAY<STRING>>` 컬럼이 있다고 하자. 이 컬럼은 아이템 목록을 담는데, 그 안에는 그 아이템이 가진 tag 정보를 갖는다.

```
row 0: items = [ {tags: ["a", "b"]}, {tags: ["c"]}]
row 1: items = [ {tags: []} ]
row 2: items = [ {tags: ["d"]} ]
row 3: items = []
row 4: items = null
```

def level은 이렇게 정의된다.

- def=0 -> 아이템 자체가 null -> `null`
- def=1 -> items는 있지만, 비어있음. -> `[]`
- def=2 -> items는 있지만, tags가 비어있음. -> `[[]]`
- def=3 -> items도 있고, tag에도 값이 있음. -> `[["a"]]`

rep level은 이렇게 정의된다.

- rep=0 -> 새 row의 시작 -> item을 여는 괄호 `[`
- rep=1 -> 같은 row 안에서 새 item이 시작된다. -> tags를 여는 괄호 `[ {tags: `
- rep=2 -> 같은 item 안에서 tags 값이 계속 이어짐.

이제 정의대로 rep/dev level로 표현하면,

```
value:  a  b  c  [[]]  d  []  null
rep:    0  2  1  0     0  0   0
def:    3  3  3  2     3  1   0
```

## STRUCT

이런 스키마가 있다고 하자.

```
message Order {
  optitional group address {
    required string city;
    optional string zipcode;
  }
}
```

그리고 데이터는

```
row 0: address = {city: "Seoul", zipcode: "04524"}
row 0: address = {city: "Pohang", zipcode: null}
row 0: address = null
```

`STRUCT` 타입은 leaf 컬럼으로 분해해서 저장한다. 즉, `address.city`, `address.zipcode`가 별도의 Column Chunk로 저장된다. 그래서 `STRUCT` 타입은 구조체 컬럼임에도 빠른 쿼리가 가능한 것이다.

def/rep level도 각 leaf 컬럼 기준으로 정해진다. 요건 primitive 타입과 비슷하게 이뤄지기 때문에 여기에서는 생략 하겠다.

이렇게 `STRUCT` 타입을 leaf 컬럼으로 분해해서 각각 저장하는 걸 "**Shredding**"이라고 한다. `address` 컬럼 자체는 Column Chunk가 없으며, `STRUCT` 구조 정보는 Parquet 스키마에만 존재한다.

반대로 `STRUCT` 타입의 데이터를 읽는 과정은 **Assembly**라고 한다. 분해된 leaf 컬럼을 원래의 `STRUCT` 구조로 복원하는 것!

# `DATA_PAGE_V2`

Parquet 1.x 후반에 추가된 이 포맷은 Data Page를 저장하고 압축할 때, rep/def level을 압축하지 않는다.

본래 `DATA_PAGE`에선 rep+def+values 전체를 압축해서 저장했다. 그런데, 이렇게 하니 null 값을 찾을 때, Page 전체를 압축 해제해야 그 안의 def level을 보고 null 값 필터링이 가능했다.

`DATA_PAGE_V2`는 오직 values 값만 압축해서 저장한다. rep/def level은 압축하지 않고 그대로 저장한다. 이렇게 하면, null 필터링을 압축해제 없이 빠르게 할 수 있다.

Spark에서는 기본값으로 `DATA_PAGE`(v1)을 사용한다. v2를 쓰려면, `spark.sql.parquet.dataPageVersion=v2`로 명시해줘야 한다.
