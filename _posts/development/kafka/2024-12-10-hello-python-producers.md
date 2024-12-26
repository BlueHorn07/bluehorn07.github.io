---
title: "Hello, Python Producers!"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "꼭 Java로 Kafka Producer 개발할 필욘 없잖아 ¯\\\_(ツ)_/¯ 그리고 Confluent Schema Registry로 스키마 정의하고 데이터 적재 하기"
---

# 왜 Python Producer에 관심을 갖게 되었나요?

"Kafka 개발은 Java가 일반적이죠"라는 말을 참 많이 들은 것 같다. 그런데, 회사에서의 Kafka Producer Application은 Python으로 개발 되어 있고, 이걸로 꽤 많은 데이터가 처리되고 있다!! (지금도!)

그렇게 생각하니 'Python Producer도 나쁘지 않는데?'라는 생각이 들기도 하고, 한번 제대로 정리해보면 좋을 것 같다는 생각이 들었다. 이 글은 그런 생각이 들은 내가 테스트 해본 Python Producer 코드들을 아카이브 하고 깨달은 점을 적은 글이다.

글을 시작하기 전에 미리 밝히자면, 지금 회사에서 Confluent Cloud를 사용하기 있고, 아래 예시 코드들 역시 `confluent_kafka` pip 패키지를 사용함을 밝힙니다.
{: .notice }

# Simple Producer

key와 value 값만 정하고 Kafka 토픽에 데이터를 쏘는 예제 입니다.

```py
from confluent_kafka import SerializingProducer

producer = SerializingProducer({
  'bootstrap.servers': 'xxxx.confluent.cloud:9092',
  'security.protocol': 'SASL_SSL',
  'sasl.mechanism': 'PLAIN',
  'sasl.username': 'xxxx',
  'sasl.password': 'xxxx',
})

def acked(err, msg):
    if err is not None:
        print(f'Failed to deliver message: {str(msg)}: {str(err)}')
    else:
        print(f'Message produced: {str(msg)}, offset: {msg.offset()}')

producer.produce(
  topic='my_topic',
  key='my_key',
  value='my_value',
  on_delivery=acked
)

producer.poll(1)
producer.flush()
```

Kafka Topic에서는 전달 받은 `my_value` 그대로 데이터가 적재됩니다. 포스트 전반에서 중요하게 볼 점은 `value.serializer` 입니다. 여기에서 Serializer를 따로 설정하지 않았는데, 이 경우 Value Serializer가 아무것도 지정되지 않고 `None`으로 세팅 됩니다.

이 경우, 아무런 Serialization 없이 바로 Kafka로 produce 됩니다. [confluentinc/confluent-kafka-python](https://github.com/confluentinc/confluent-kafka-python/blob/v2.6.1/src/confluent_kafka/serializing_producer.py#L136-L146)

단, `str`이 아닌 `int`, `dict`, `list` 등등 다른 모든 타입은 아래와 같은 오류와 함께 Kafka에 데이터가 적재되지 않고 실패합니다.

```bash
TypeError: a bytes-like object is required, not 'int'
```

# String Producer

```py
from confluent_kafka import SerializingProducer
from confluent_kafka.serialization import StringSerializer

producer = SerializingProducer({
  'bootstrap.servers': 'xxxx.confluent.cloud:9092',
  'security.protocol': 'SASL_SSL',
  'sasl.mechanism': 'PLAIN',
  'sasl.username': 'xxxx',
  'sasl.password': 'xxxx',
  'key.serializer': StringSerializer('utf_8'),
  'value.serializer': StringSerializer('utf_8'),

})

producer.produce(
  topic='my_topic',
  key='my_key',
  value='my_value',
  on_delivery=acked
)
```

여기서부터 `acked()`와 `poll()`, `flush()`에 대한 부분은 빼고 작성하겠습니다. String Producer는 `StringSerializer`를 Serializer로 사용합니다.

`StringSerializer('utf_8')`로 설정하는데, `utf_8`은 코덱(codec) 값을 말합니다. 기본값이 `utf_8`로 설정되어 있습니다.

만약 string이 아닌 `int(12345)`와 같은 값을 준다면 아래와 같이 SerializationError를 겪습니다.

```bash
confluent_kafka.error.ValueSerializationError: KafkaError{code=_VALUE_SERIALIZATION,val=-161,str="'int' object has no attribute 'encode'"}
```

# Confluent Schema Registry

여기서 잠깐! 뒤에 이어질 Json Producer와 Avro Producer를 테스트 하기 전에 반드시 Schema Registry에 Schema가 세팅 되어 있어야 합니다. 구체적으로는 Serializer에 `schema_registry` client 값을 넘겨줘야 합니다.

```py
from confluent_kafka.schema_registry import SchemaRegistryClient

sr_client = SchemaRegistryClient({
    'url': 'https://xxxx.confluent.cloud',
    'basic.auth.user.info': '{Key}:{Secret}'.format(Key='xxxx', Secret='xxxx'),
})
```

여기서부터 다루는 부분이 이 글을 쓰게 된 가장 큰 계기 입니다. 생각보다 복잡 했거든요. 일단 Serializer 종류별로 사레를 살펴봅시다.

# Json Producer w/ Json Schema

## Json Schema

일단 "User"라는 Json Schema 예시부터 살펴봅니다.

```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "User",
  "description": "A Confluent Kafka Python User",
  "type": "object",
  "properties": {
    "name": {
      "description": "User's name",
      "type": "string"
    },
    "favorite_number": {
      "description": "User's favorite number",
      "type": "number",
      "exclusiveMinimum": 0
    },
    "favorite_color": {
      "description": "User's favorite color",
      "type": "string"
    }
  },
  "required": [ "name", "favorite_number", "favorite_color" ]
}
```

첫줄부터 `$schema`라는 요상한 녀석이 등장하는데, Json Schema에 대한 스키마 표준 버전(draft)을 적어주는 부분입니다. Json 스키마의 표준은 시간이 지나면서 업데이트 되고 새로운 버전(draft)가 나오면서 기능과 문법이 변화 합니다. `$schema` 부분은 정의한 스키마가 어떤 표준 버전을 기반으로 하고 있는지 명시하는 것입니다. 특정 버전에서만 지원하는 기능들이 있다고 하니 버전에 유의해서 사용합니다.

이어지는 `title`, `description` 등의 값은 당연한 값들의 나열이라 별도로 설명하진 않겠습니다.

아! 그리고 JSON schema를 사용하기 위해 아래의 패키지를 설치해줍니다.

```bash
$ pip install jsonschema
```

## Json Producer

Json Producer 코드를 아래와 같이 작성합니다.

```py
from confluent_kafka import SerializingProducer
from confluent_kafka.schema_registry import SchemaRegistryClient
from confluent_kafka.serialization import StringSerializer
from confluent_kafka.schema_registry.json_schema import JSONSerializer

schema_str = """
  {
    "$schema": "http://json-schema.org/draft-07/schema#",
    "title": "User",
    "description": "A Confluent Kafka Python User",
    "type": "object",
    "properties": {
      "name": {
        "description": "User's name",
        "type": "string"
      },
      "favorite_number": {
        "description": "User's favorite number",
        "type": "number",
        "exclusiveMinimum": 0
      },
      "favorite_color": {
        "description": "User's favorite color",
        "type": "string"
      }
    },
    "required": [ "name", "favorite_number", "favorite_color" ]
  }
"""

producer = SerializingProducer({
  'bootstrap.servers': 'xxxx.confluent.cloud:9092',
  ...
  'key.serializer': StringSerializer('utf_8'),
  'value.serializer': JSONSerializer(
    schema_registry_client=sr_client,
    schema_str=schema_str,
  ),
})

producer.produce(
  topic='my_topic,
  key='my_user',
  value={
    'name': 'Gildong-Hong',
    'favorite_number': 18,
    'favorite_color': 'blue',
  },
  on_delivery=acked
)
```


## Serializer Configs

몇가지 Configuration이 있습니다.

- `auto.register.schemas` (default: True)
  - 만약 Topic에 Schema가 등록되지 않았다면 자동으로 등록해주는 옵션
  - Schema 이름은 아래의 `subject.name.strategy`에 따라 결정되는데
    - Default 값인 TopicName Strategy를 따른다면, `{TopicName}-key`, `{TopicName}-value`와 같은 형식으로 등록됩니다.
- `subject.name.strategy` (default: topic_subject_name_strategy)
- `normalize.schemas` (default: False)
  - 대충 스키마에 정의된 컬럼 순서까지 따르도록 validation을 수행할 것인가에 대한 옵션입니다.
- `use.latest.version` (default: False)
  - 토픽에 등록된 Schema의 최신 버전으로 Serialization을 할 것이냐에 대한 옵션입니다.
  - 위에서 설명한 `auto.register.schemas` 옵션과 함께 사용할 수 없습니다.
  - Confluent에서 토픽에 등록된 최신 스키마를 찾아서 그걸 그대로 사용합니다. [[confluent-kafka-python] json_schema.py](https://github.com/confluentinc/confluent-kafka-python/blob/master/src/confluent_kafka/schema_registry/json_schema.py#L246-L249)

```py
producer = SerializingProducer({
  'bootstrap.servers': 'xxxx.cloud:9092',
  ...
  'key.serializer': StringSerializer('utf_8'),
  'value.serializer': JSONSerializer(
    schema_registry_client=sr_client,
    schema_str=schema_str,
    conf = {
        'auto.register.schemas': False,
        'use.latest.version': True,
    }
  ),
})
```

`use.latest.version` 옵션 관련해서 테스트를 좀더 해보니, `schema_str` 값은 여전히 넣어줘야 했습니다. `None`이런 값을 넣어서도 안 됩니다. 앞에서 정의한 대로 `schema_str` 값을 넣어야 하며, 제공한 `schema_str` 값의 Json 스키마에 따라 validation도 이뤄집니다. 그저, Schema Registry에 신규로 등록하지 않고 기존 것을 사용한다의 켄섭?만을 제공하는 옵션인 것 같습니다. (아쉽)


# Avro Producer w/ Avro Schema

## Avro Schema

Avro 포맷에 대해선 지난 포스트인 ["Hello, Avro!"](/2024/11/15/hello-avro/) 포스트에서 다뤘으니 자세한 주소 설명은 생락하겠습니다 ㅎㅎ
왜 쓰느냐고 누군가 묻는다면 Json 보다 압축률이 좋아서 사용한다로 답변할 수 있을 듯 합니다. (그리고 Union Schema 기능도요!)

```json
{
    "name": "User",
    "type": "record",
    "fields": [
        {
            "name": "name",
            "type": "string"
        },
        {
            "name": "favorite_number",
            "type": "long"
        },
        {
            "name": "favorite_color",
            "type": "string"
        }
    ]
}
```

우선 위와 같이 정의한 Avro Schema를 사용합니다.

## Avro Producer

앞서 살펴본 [JSON Producer](#json-producer)의 사례와 정말 비슷합니다. Serializer에 Schema Registry Client를 전달해줘야 합니다.

```py
from confluent_kafka import SerializingProducer
from confluent_kafka.schema_registry import SchemaRegistryClient
from confluent_kafka.serialization import StringSerializer
from confluent_kafka.schema_registry.avro import AvroSerializer

schema_str = """
...
"""

producer = SerializingProducer({
  'bootstrap.servers': 'xxxx.confluent.cloud:9092',
  ...
  'key.serializer': StringSerializer('utf_8'),
  'value.serializer': AvroSerializer(
    schema_registry_client=sr_client,
    schema_str=schema_str,
  ),
})

producer.produce(
  topic='my_topic,
  key='my_user',
  value={
    'name': 'Gildong-Hong',
    'favorite_number': 18,
    'favorite_color': 'blue',
  },
  on_delivery=acked
)
```

## Serializer Config

Producer config에 대한 부분도 `JsonSerializer`의 것과 동일합니다. `auto.register.schemas`로 Topic에 신규/변경된 스키마를 자동으로 반영할 수 있습니다.

# 맺음말

Kafka 그리고 Confluent 플랫폼에 대해 어렴풋이 알던 것들이 직접 코드를 구성하고 옵션들을 실험해보면서 얻게 되는게 많아지는 것 같습니다.

요즘에는 이론을 공부하는 것보다 그걸 활용하고 적용하고, 프로덕을 만들어 가는 것이 더 가치 있다고 느끼게 되는 것 같습니다. 빠르게 배워서 빠르게 적용하고, 그리고 빠르게 실패하고(ㅋㅋ). 글을 쓰는 시점에는 2024년이 이제 한 달도 채 남지 않았습니다. 내년에는 뭘 해볼까? 시간을 어떻게 써볼까? 고민하고 상상 해보고 있습니다. 내년에 재밌는 것들은 왕창 할 수 있길!! 🏄

이것저것 Serializer를 바꿔가며 테스트 해볼 때, `confluentinc/confluent-kafka-python`에 있는 [examples](https://github.com/confluentinc/confluent-kafka-python/blob/master/examples) 폴더의 예시 코드들이 많이 도움이 되었습니다. Producer 말고도 Consumer 쪽 예시 코드도 있으니 참고하시길!!
