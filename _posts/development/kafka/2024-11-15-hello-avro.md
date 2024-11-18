---
title: "Hello, Avro!"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: Json보다 빠르고 컴팩트한 Avro 포맷을 만나보자 👋
---

# 왜 Avro를 쓰게 되었나요??

데이터 엔지니어로 일을 하면서 현재 이벤트 파이프라인을 도맡아서 관리하고 있습니다. 하루 3억 건 정도의 데이터를 받아서 Kafka에 밀어넣고 있는데요! 이 과정에서 `AvroSerializer`로 데이터를 인코딩하여 Kafka에 보내고 있습니다.

그런데, 어느 순간!! 왜 Avro 포맷을 쓰는 걸까?라는 생각이 들었습니다. 1년 넘게 이벤트 파이프라인을 관리하고 있었는데, Avro가 뭔지 제대로 알지도 못하는 상태에서 Avro를 쓰고 있다는 걸 문득 깨닫게 되었습니다. 이번 포스트는 '아직 부족한게 많구나'라는 마음, 그리고 '여전히 재밌는 것들이 남았군!'라는 생각으로 정리해보겠습니다.

# What is Avro

JSON, CSV를 담은 데이터 형식입니다. 특이한 점은 스키마 정보가 데이터 파일에 함께 담긴다는 점 입니다. 즉, `xxxx.avro`라는 파일 안에 아래와 같은 스키마 정보를 담을 수 있습니다.

```json
{
  "namespace": "NAMESPACE",
  "name": "NAME",
  "doc": "Any Commentary",
  "type": "record",
  "fields": [
    {"name": "name", "type": "string"},
    {"name": "age", "type": "int"},
  ]
}
```

이때, avro의 스키마 정보는 `.avsc`라는 **Av**ro **Sc**hema라는 포맷에 맞춰 작성됩니다.

![](https://www.oreilly.com/api/v2/epubs/9781492049517/files/assets/opdl_0403.png)
[O'Reilly - Operationalizing the Data Lake](https://www.oreilly.com/library/view/operationalizing-the-data/9781492049517/ch04.html)
{: .small .gray .text-center }

# Let's Start Avro

Avro 포맷을 사용하는 여러 방법이 있지만, 이번 포스트에서는 Python으로 진행합니다. 이를 위해 `fastavro`라는 패키지를 설치합니다. 저는 작성 시점 기준으로 최신 버전인 `1.9.7` 버전으로 진행했습니다.

```bash
pip install fastavro==1.9.7
```

## Avro Write

```py
from fastavro import parse_schema, writer

schema = {
  "namespace": "earth",
  "name": "person",
  "doc": "person information",
  "type": "record",
  "fields": [
    {"name": "name", "type": "string"},
    {"name": "age", "type": "int"},
  ]
}

parsed_schema = parse_schema(schema)

message = {
  "name": "Jobs",
  "age": 26,
}

with open("./hello.avro", "wb") as f:
  writer(f, parsed_schema, [message] * 100)
```

간단합니다. `fastavro`의 `writer()` 함수에 스키마와 저장할 데이터를 전달합니다.

## Avro Read

```py
from fastavro import reader

with open("hello.avro", "rb") as f:
  _reader = reader(f)
  print(_reader)
  print(_reader.writer_schema)
  print(_reader.reader_schema)

  for record in _reader:
    print(record)
    break
```

Avro 파일에는 스키마 정보가 이미 담겨 있기 때문에, 읽을 때는 스키마를 지정할 필요가 없습니다.

# Avro Schema

Avro 스키마는 JSON 포맷으로 작성됩니다.

```json
{
  "namespace": "earth",
  "name": "person",
  "doc": "person information",
  "type": "record",
  "fields": [
    {"name": "name", "type": "string"},
    {"name": "age", "type": "int"},
  ]
}
```

`.avro` 파일을 출력해보면, Avro Schema는 바이너리가 직렬화되지 않고, 그 값 그대로 존재합니다.

![](/images/development/kafka/avro-raw-bytes.png){: style="max-width: 100%" }
{: .small .gray .text-center }

Avro Schema에 존재하는 `name`과 `namespace` 필드는 스키마를 식별하기 위한 식별자 입니다. 저는 쉽게 생각해 `name`이 RDB의 Table, `namespace`가 RBD의 database에 대응되는 개념이라고 이해했습니다.


## Reject Schema Mismatch when AVRO Write

```py
message = {
  "name": "Jobs",
  # no `age` field
}
```

`fastavro.write()`를 할 때, 저장할 메시지가 설정한 스키마와 불일치 한다면, avro write이 일어나지 않습니다. 예를 들어, 스키마에는 `age` 필드가 정의되어 있는데, 메시지에는 없다면 `ValueError: no value and no default for age` Exception을 보게 됩니다.

반대로, 메시지에 정의하지 않은 컬럼이 추가로 들어오는 경우는 

```py
message = {
  "name": "Jobs",
  "age": 26,
  "address": "USA" # new field, but not defined
}
```

Avro write는 성공하지만, 저장된 데이터를 읽어보면, 정의하지 않은 필드는 `.avro`에 저장되지 않습니다.

Rejection은 타입에 대해서도 일어나는데, 

```py
message = {
  "name": "Jobs",
  "age": "26" # string, but defined as int
}
```

`age`를 int가 아닌 string으로 전달하면 `TypeError: an integer is required on field age`라는 Exception을 보게 됩니다.

## Nullable Field

Avro에서 어떤 필드가 Nullable 한지는 아래와 같은 스키마로 표현할 수 있습니다.

```json
{
  "namespace": "earth",
  "name": "person",
  "doc": "person information",
  "type": "record",
  "fields": [
    {"name": "name", "type": "string"},
    {"name": "age", "type": ["null", "int"]},
  ]
}
```

이런 경우, `age` 필드가 nullable 이기 때문에, 해당 필드의 값이 없어도 avro write가 가능합니다.

## Union Field

사실 위에서 nullable 필드를 표현하는 방법은 Avro 스키마의 "Union" 타입을 활용한 것입니다. Union 타입은 하나의 필드에 여러 데이터 타입을 쓸 수 있도록 허용합니다.

```json
{
  "namespace": "earth",
  "name": "person",
  "doc": "person information",
  "type": "record",
  "fields": [
    {"name": "name", "type": "string"},
    {"name": "age", "type": ["null", "int", "string"]},
  ]
}
```

그래서 이렇게 age가 `int`이면서, `string`이면서, nullable인 경우도 가능합니다!!

## Schema Reference

Avro에서는 정의한 스키마를 다른 스키마의 필드 객체로 사용하는 것도 가능하다. 아래 예제는 `earth.animal`라는 스키마를 정의하고, 이를 `earth.person`라는 스키마의 `pet` 필드의 타입으로 사용하는 예제이다.

```json
schema = [{
  "namespace": "earth",
  "name": "animal",
  "doc": "animal information",
  "type": "record",
  "fields": [
    {"name": "pet_name", "type": "string"},
    {"name": "type", "type": ["null", "string"]},
  ]
}, {
  "namespace": "earth",
  "name": "person",
  "doc": "person information",
  "type": "record",
  "fields": [
    {"name": "name", "type": "string"},
    {"name": "age", "type": ["null", "int", "string"]},
    {"name": "pet", "type": ["null", "earth.animal"]},
  ]
}]

message = [{
  "name": "John",
  "age": "30",
  "pet": {
    "pet_name": "Maru",
    "type": "dog"
  }
}]
```

## Union Schema

위의 Schema Reference 케이스에서는 `xxxx.avro` 파일이 하나에 `earth.pet`과 `earth.person`, 두 개의 avro 스키마가 헤더에 저장될 것 입니다. 위의 예시에서는 `earth.person`에 대한 데이터를 저장하는 것처럼 적었지만, 아래와 같이 `earth.pet`과 `earth.person` 데이터를 하나의 avro 파일에 저장하는 것도 가능합니다.

```json
...
message = [{
  "name": "John",
  "age": "30",
  "pet": {
    "name": "Maru",
    "type": "dog"
  }
}, {
  "pet_name": "Maru-2",
  "type": "human?"
}]
```

암튼 전하고 싶은 말은 Avro는 다중 스키마도 지원합니다!

# 맺음말

Avro는 명백히 Schema가 있는 자료구조지만, Union Field나 Union Schema의 케이스를 보면 다중 타입이나 다중 스키마를 지원한다는 유연성도 갖추고 있습니다.

포스트를 작성하면서 지금의 이벤트 파이프라인 코드가 어떤 의도로 커스텀을 했는지 자세히 살펴보는 계기가 된 것 같습니다. 앞으로 종종 Kafka를 공부하며 접하는 지식들을 정리해보겠습니다 ㅎㅎ
