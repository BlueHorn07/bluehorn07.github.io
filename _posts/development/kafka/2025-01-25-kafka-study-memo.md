---
title: "Kafka 이것저것 메모"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "카프카 자격증... 언제 따려나..."
---

# 들어가며

회사에서 Confluent를 통해 Kafka 클러스터를 잘 사용하고 있었습니다. 그런데, 이 카프카에 대해서 좀더 자세히 알고, 전문성을 갖추고 싶다는 생각이 들어서 2025년 첫 목표로 Confluent의 Kafka 자격증인 [Confluent Certified Developer for Apache Kafka®](https://training.confluent.io/examdetail/confluent-dev) 자격증을 준비하고 있습니다 ✌️

# 컨트롤러 브로커는 뭘 하는 녀석인가?

리더 파티션을 결정하는 녀석 입니다. 주키퍼로부터 브로커들의 상태를 지속적으로 가져옵니다.

리더 파티션이 있던 브로커가 유실되면, 주키퍼에 저장되어 있는 나머지 브로커의 상태를 바탕으로 남은 팔로워 파티션 중 하나를 새로운 리더 파티션으로 승격합니다. 리더 파티션이 재선정 되면, 주키퍼에게 이를 알려줍니다.

주키퍼와 통신하며, 클러스터의 메타 데이터를 싱크 합니다.

컨트롤 플레인 zookeeper든 Kraft 모드이든 컨트롤러 브로커는 리더 파티션을 선정 합니다. 다만, 모드에 따라서 컨트롤러 브로커가 유실된 상태에서 새로운 컨트롤러 브로커를 선출하는 방식이 다른 것입니다.

- zookeeper 모드
  - 주키퍼가 남은 브로커 중 하나를 컨트롤러 브로커로 선출합니다.
- Kraft 모드
  - Raft 프로토콜에 따라 투표 방식으로 컨트롤러를 선출 합니다.

# 주키퍼는 뭘 하는 녀석인가?

Kafka 클러스터 관리를 위해 여러 역할을 합니다.

- **컨트롤러 브로커를 선출**
- 브로커 등록과 상태 관리
  - znode 어쩌구... (TODO)
- 각 토픽의 파티션 정보
  - 누가 리더고, 누가 팔로워인지
  - ISR 집합은 어떻게 되어 있는지
- 각 토픽의 메타데이터
  - 이름, config, retention 등등
- 각 토픽의 ACL

# 모든 상호작용은 오직 리더 파티션만!

프로듀서가 데이터를 보낼 때, 그리고 컨슈머가 데이터를 가져올 때, 두 경우 모두 리더 파티션이 있는 브로커와 상호작용 하는 것임.

팔로워 파티션의 데이터는 오직 장애 복구를 위한 용도일 뿐임!! `replication.factor`가 크다고 해서 프로듀서/컨슈머의 처리량이 전혀 늘어나지 않음.

# 부트스트랩 서버에 대해

카프카 클러스터를 이루는 모든 브로커가 카프카 클러스터의 진입점이 될 수 있음. 즉, 브로커가 컨트롤러 브로커일 필요도 없음.

옛날에 헷갈렸던 건 Kafka Properties에 적던 `bootstrap.servers`가 주키퍼의 주소인 줄 알았음. 그런데, 그게 전혀 아니고 각 브로커의 주소를 말함.

그래서 `bootstrap.servers`에 아래와 같이 여러 브로커의 주소를 적는게 가능함.

```toml
bootstrap.servers=broker1:9092,broker2:9092,broker3:9092
```

# 클린 로그 vs. 더티 로그

토픽 데이터를 정리하는 방법 중에 Log Compaction을 할 때 쓰는 용어.

Active Segment는 현재 데이터가 실시간으로 쌓이고 있으니 압축 대상이 아님! 그래서 Inactive Segment 들이 로그 압축의 대상이 됨.

세그먼트에는 key 별로 latest 로그도 있고, non-latest 로그도 있을 것임. 만약, latest 로그라면 삭제 되지 않고 보존됨. non-latest 로그라면 로그가 삭제됨. 그래서 보존되는 latest 로그를 "**클린 로그**"라고 하고, 삭제 처리 되는 non-latest 로그를 "**더티 로그**"라고 함.

# Log Compaction에서 Tombstone 레코드

Log Compaction이 활성화된 토픽에서는 특정 key의 데이터를 삭제할 수 있음.

방법은 삭제하려는 key에 null value 묶어서 레코드로 쏘면 됨. 이렇게 삭제를 위해 보내는 null value 레코드를 "**Tombstone 레코드**"라고 함.

Tombstone 레코드가 생기면, 기존에 해당 key에 존재하던 값이 non-latest가 되고, Log Compaction이 될 때 삭제 처리가 됨.

Tombstone 레코드는 해당 key에 대한 latest 레코드이므로, Log Compaction의 대상이 되지 않고, 영원히 존재할 가능성이 있음. 그래서 Kafka에서는 `delete.retention.ms`를 설정하여 주기적으로 Tombstone 레코드를 정리함!!

이 값은 기본값이 24시간으로 설정 되어 있고, Tombstone 레코드로 인한 non-latest 레코드 정리(Log Compaction) 주기 보다 충분히 길게 설정 해줘야 함. Log Compaction 주기는 2가지 속성에 의해 영향을 받는데, `min.cleanable.dirty.ratio`(default: 0.5), `log.cleaner.backoff.ms`(default: 15,000 ms)로 되어 있음. 그래서 왠만하면 Tombstone 레코드가 삭제 되기 전에, non-latest 값들이 정리 됨.

이렇게 레코드 삭제를 지원하는 이유는 GDPR과 같은 개인정보 보호 요구 사항을 만족하기 위해서임.


# 레코드 삭제에 대해

Kafka 토픽의 데이터는 프로듀서도, 컨슈머도 아닌 오직 브로커만이 데이터를 삭제할 수 있음.

데이터 삭제는 파일 단위로 이뤄지는데, 이 단위를 로그 세그먼트(log segment)라고 함. 삭제가 파일 단위로 이뤄지기 때문에 데이터베이스처럼 특정 데이터를 선별해 삭제하는게 불가능함.


# Confluent 클러스터는 몇개의 브로커 구성 되어 있는가?

Confluent는 Serverless Kafka 경험을 제공하기 때문에, Kafka 클러스터가 얼만큼의 브로커로 구성 되어 있는지 알 수 없습니다. Confluent 클러스터는 얼마만큼의 퍼포먼스를 보장하고, 사용량에 따라 과금 하는 것일뿐 Kafka 클러스터 관리는 고객이 하지 않습니다.

그럼에도 불구하고! Cluster를 이루는 Broker 갯수를 확인하는 방법이 있습니다!! Kafka Shell의 `kafka-broker-api-versions.sh`를 사용 하면 됩니다!

```bash
$ kafka-broker-api-versions.sh \
    --bootstrap-server xxxx.xxxx.xxx.confluent.cloud:9092 \
    --command-config /xxxx/client.properties
```

이렇게 하면, 각 브로커의 목록을 확인할 수 있습니다. 저의 경우는 아래와 같이 출력 되었습니다.

```bash
b0-xxxx.xxxx.aws.confluent.cloud:9092 (id: 8 rack: usw2-az2) -> (
	Produce(0): 0 to 11 [usable: 11],
	Fetch(1): 0 to 17 [usable: 17],
	ListOffsets(2): 0 to 10 [usable: 9],
	Metadata(3): 0 to 13 [usable: 12],
    ...
)
b1-xxxx.xxxx.aws.confluent.cloud:9092 (id: 7 rack: usw2-az3)
b2-xxxx.xxxx.aws.confluent.cloud:9092 (id: 6 rack: usw2-az4)
```

`BASIC` 플랜의 클러스터 였는데, 총 18개의 브로커로 구성 되어 있었습니다!

```py
from confluent_kafka.admin import AdminClient

# Kafka 클라이언트 설정
admin_client = AdminClient({
    "bootstrap.servers": "xxxx.xxxx.aws.confluent.cloud:9092",
    "security.protocol": "SASL_SSL",
    "sasl.mechanism": "PLAIN",
    "sasl.username": "xxxx",
    "sasl.password": "xxxx"
})

# 브로커 정보 출력
brokers = admin_client.describe_cluster()
print("Cluster ID:", brokers.result().cluster_id)
print("Controller:", brokers.result().controller)
for node in brokers.result().nodes:
  print(node)
```

요런 Python 코드를 사용하면, 브로커 이름만 출력해 확인할 수 있습니다!

# Kafka Tiered Storage

본래 Kafka 클러스터는 데이터를 브로커의 디스크 공간에 저장합니다. 그러나 Kafka Tiered Storage를 사용하게 되면, 오래된 데이터를 저렴한 스토리지(ex: AWS S3)에 저장합니다.

“local-log-start-offset”은 파티션 데이터가 로컬에서 더이상 유지되지 않고, 외부 스토리지로 이동한 시점을 가리킵니다. 즉, "로컬 디스크에서 접근 가능한 로그의 끝 지점(offset)"을 나타냅니다. 이 값 이전의 데이터는 외부 스토리지에서만 읽을 수 있습니다. [[Apache Kafka]](https://cwiki.apache.org/confluence/display/KAFKA/KIP-405%3A+Kafka+Tiered+Storage)

요 기능은 2020년 Confluent에서 먼저 제공했고, 2023년 Kafka에서도 정식으로 지원하기 시작 했습니다. Confluent에서 이 기능을 키려면 브로커에 `confluent.tier.feature=true`로 설정하면 됩니다. [[Confluent]](https://docs.confluent.io/platform/current/clusters/tiered-storage.html)

기존에는 모든 레코드를 로컬 스토리지에 저장하기 때문에 브로커 서버의 디스크 사용량을 아주 엄격하게 관리해주어야 했습니다. Tiered Storage를 사용하면, 일부 데이터가 remote storage로 옮겨가기 때문에 브로커의 디스크 관리가 여유로워진다고 합니다. [[데브원영님의 아티클]](https://blog.voidmainvoid.net/509)

# Confluent REST Proxy

Confluent에서 개발하여 제공하는 독점적인 기능합니다. Kafka 클러스터에 RESTful 인터페이스를 제공합니다! 기존에는 Kafka의 네이티브 프로코톨을 사용하거나 Kafka에서 제공하는 SDK를 사용해 상호작용 해야 했던 것들 REST API를 통해 가능하게 합니다!!

```bash
curl \
  -X GET \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic <BASE64-encoded-key-and-secret>" \
  https://pkc-xxxx.us-west-2.aws.confluent.cloud/kafka/v3/clusters/<CLUSTER_ID>/topics
```

응답은 아래와 같은 형식으로 받게 됩니다!

```json
{
    "kind": "KafkaTopicList",
    "metadata": {
        "self": "https://pkc-xxxx.us-west-2.aws.confluent.cloud/kafka/v3/clusters/xxx-xxxx/topics",
        "next": null
    },
    "data": [
        {
            "kind": "KafkaTopic",
            "metadata": {
                "self": "https://pkc-xxxx.us-west-2.aws.confluent.cloud/kafka/v3/clusters/xxx-xxxx/topics/<TOPIC_NAME>",
                "resource_name": "crn:///kafka=xxx-xxxx/topic=<TOPIC_NAME>"
            },
            "cluster_id": "xxx-xxxx",
            "topic_name": "<TOPIC_NAME>",
            "is_internal": false,
            "replication_factor": 3,
            "partitions_count": 1,
            "partitions": {
                "related": "https://pkc-xxxx.us-west-2.aws.confluent.cloud/kafka/v3/clusters/xxx-xxxx/topics/<TOPIC_NAME>/partitions"
            },
            "configs": {
                "related": "https://pkc-xxxx.us-west-2.aws.confluent.cloud/kafka/v3/clusters/xxx-xxxx/topics/<TOPIC_NAME>/configs"
            },
            "partition_reassignments": {
                "related": "https://pkc-xxxx.us-west-2.aws.confluent.cloud/kafka/v3/clusters/xxx-xxxx/topics/<TOPIC_NAME>/partitions/-/reassignment"
            },
            "authorized_operations": []
        },
        ...
  ]
}
```

단순히 토픽 목록을 조회하는 것 말고도 Topic에 데이터를 Produce 하는 것도 가능 합니다... 라고 문서에는 나와 있는데... 요상하게도 Postman으로 테스트 해보는데 잘 안 되네요...;; 요건 추후에 다시 좀 살펴보겠습니다!!

그리고 REST Proxy를 통해 토픽의 데이터를 Consume 하는 것은 지원하지 않습니다!

Confluent에서 제공하는 REST Proxy 전체를 확인하고 싶다면, "[Confluent Cloud APIs](https://docs.confluent.io/cloud/current/api.html)" 문서를 살펴보면 됩니다!


# Java

## slf4j `DEBUG` 출력 커스텀

로컬에서 Java Kafka Client를 실행하면, 자꾸 `DEBUG` 출력이 나왔음;;

![](/images/development/kafka/java-kafka-client-debug.png)

요렇게 Kafka Client의 초기화 단계에 대해서 출력이 되는데, `DEBUG` 부분은 출력을 좀 안 하고 싶었음. GPT의 도움을 받아 `DEBUG` 출력을 쓸 수 있었음.

### logback 설정 변경

```java
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public static void main () {
  Logger kafkaLogger = (Logger) LoggerFactory.getLogger("org.apache.kafka");
  kafkaLogger.setLevel(Level.INFO);
  ...
}
```

이렇게 하니까 이제 `DEBUG` 출력이 안 나옴!!! (happy)

### Define System Property

다른 방식으로는 JVM 실행 때, 요런 시스템 프로퍼티를 설정하라고 조언 했음.

```bash
-Dlogging.level.org.apache.kafka=INFO
```

`-D`는 Define system property의 약자라고 함. JVM이 실행되는 동안 사용할 시스템의 프로퍼티 쌍을 정의하는 방법이라고 함.

`-DpropertyName=value` 요런 형식임.

요 방식은 어플리케이션 코드를 수정하지 않고도, 실행 동작을 변경해줄 수 있다고 함. 대신 코드에서 `System.geProperty()`로 값을 받아오긴 해야 합니다!

```java
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public static void main () {
  String logLevelProperty = System.getProperty("logging.level.org.apache.kafka", "INFO");
  Level logLevel = Level.valueOf(logLevelProperty.toUpperCase());

  Logger kafkaLogger = (Logger) LoggerFactory.getLogger("org.apache.kafka");
  kafkaLogger.setLevel(Level.INFO);
  ...
}
```

여기에서 꼬리로 더 찾아본 건, 회사에서 JVM 위에서 돌아가는 어플리케이션들을 조정하다가 보면, `-Xms1g -Xmx4g`와 같이 JVM의 힙 메모리를 조정한 경험이 있음. 그래서 `-D`랑 `-X`랑 무슨 차이인지 궁금해졌음.

| 옵션 | 목적 | 접근 방법 | 사용 예제 |
|-|-|-|-|
| `-D` | 애플리케이션의 시스템 프로퍼티 설정 | System.getProperty("key") | `-Dapp.env=production` |
| `-X` | JVM의 비표준 옵션 (메모리, GC 등) | JVM 내부에서만 사용 | `-Xmx2g` |
| `-XX` | JVM의 고급 옵션 (GC 튜닝, 내부 동작 설정) | JVM 내부에서만 사용 | `-XX:+UseG1GC`|

`-D`로 전달하는 값은 Java 코드에서 값을 조회할 수 있습니다. 반면에, `-X`로 전달 하는 값은 JVM 내부에서 사용하기 때문에 Java 코드에서 값을 조회할 수 없습니다.

# MQTT Proxy

- “Message Queuing Telemetry Transport”의 약자
- MQTT proxy는 MQTT를 기반으로 하는 앱의 데이터가 kafka에 적재될 수 있도록 하는 가교 역할을 함.
- 센서 장치나 미니 컴퓨터 사이에 통신을 중계하는 장치이자 프로토콜
- **IoT 업계**의 카프카 같은 느낌?

# Message Delivery Guarantees

카프카 프로듀서는 기본적으로 "**at-least-once**"를 보장함. 다만, 네트워크 오류 등으로 인해 동일 메시지가 두 번 전송되어 적재될 수 있음. 이렇게 중복 적재되는 것을 막기 위해 "**at-most once**"와 "**exactly-once**" 기능을 추가로 지원함.

## At most once

데이터를 딱 한번만 보냄. 데이터가 중복 적재되지는 않지만, 경우에 따라서는 데이터가 아예 유실 되어 버릴 수 있음.

사실 가장 안 좋은 방법이겠지만, 그만큼 가장 빠르고 처리량을 높일 수 있음.

```toml
retries=0
acks=0
```

사실 `retries=0`만 설정하면 재전송이 없으니 "At most once"를 달성하는게 아닌가라고 생각 했음. 그런데, `acks=0`까지 설정해야 "Fire & Forgot"을 만족한다고 함. "At most once"는 사실상 "Fire & Forgot"을 의미한다는 것.


## Exactly Once

시스템에 장애가 발생하더라도, 데이터가 정확히 한번 적재 되는 것을 보장함.

```toml
enable.idempotence=true
acks=all
transactional.id=my-transaction
```

`enable.idempotence=true`는 데이터의 중복을 방지하고, `acks=all`은 ISR이 만족되길 기다리므로 데이터의 손실 방지를 보장한다.

그러나 `transactional.id`는 왜 필요한지 조금 의문이었는데, 프로듀서가 Transaction 기능이 필요하지 않다면, 필요 없을 수도 있을 것 같다. 하지만 Transaction 기반으로 동작하는 프로듀서라면 이 속성값도 반드시 넣어줘야 할 것 같다.

# Kafka 활용 사례

- Kakao
  - [카프카, 대규모 클러스터 운영 후기 / if(kakao) 2022](https://youtu.be/kGYdLiPzTOI?si=HzV086w2Gou1y7Kj)
    - 온프레미스에서 Kafka 클러스터를 운영하는 노하우가 잔뜩 소개 되어 있음.
    - RAID와 같이 디스크 프로토콜에 대해서도 다루고, SSD가 좋은지 HDD가 좋은지 같은 low-level 내용도 흥미로웠음.
    - Disk 쪽 장애가 많아서 장애 복구를 자동화 했다는 것도 흥미로움.
    - 카프카는 Kernel 단에서 zero-copy로 처리한다고 언급되는데, 좀더 알아봐야 겠음.
    - Kafka는 Page Cache를 최대한 활용하기 때문에, Heap 공간은 6GB로 (비교적) 작게 할당한다고 함
    - Kafka Parameter를 기본값이 아니라 커스텀 값 사용한 것들도 이유와 노하우를 친절히 알려주심
    - 본래 `log.dir`은 `/tmp` 경로에 로그 데이터를 적재하는데, 요게 OS 환경에 따라 자동 삭제 될 수 있기 때문에 경로는 바꿔줘야 한다고 함.
  - [카프카, Kraft를 만나다: 주키퍼 없이 운영하는 카프카의 실전 운영 노하우 / if(kakaoAI) 2024](https://youtu.be/VIGkd2U_8Ro?si=yipxHTJbCebZqo0w)
    - 컨슈머 그룹 관리에 대한 메타 정보를 예전에는 Zookeeper에서 관리 했는데, 나중엔 System Topic인 `__consumer_offsets`으로 옮겼다는 얘기를 처음 알게 됨!
      - 즉, 예전부터 Kafka는 주키퍼에 대한 의존성이 점점 줄이고 있었다.
    - `__cluster_metadata`라는 System Topic으로 클러스터 메타 정보를 관리함.
      - 이 정보는 컨트롤러 노드 뿐만 아니라 옵저버 노드도 해당 토픽에 대한 파티션을 가지고 있음.
    - Checkpoint를 구성하여, 빠른 복구가 가능하도록 함.
    - 정말 꼼꼼하게 벤치마크 테스트를 진행해서 놀랐음! (정말 배워야 할 점이라고 생각함)
      - [trogdor](https://github.com/a0x8o/kafka/blob/master/TROGDOR.md): Trogdor is a test framework for Apache Kafka.
      - 데이터 센터에 직접 방문해서 물리적인 장애를 직접 만들어볼 정도라니!
    - 브로커와 프로듀서의 압축 방식이 다르면, 압축을 풀고 다시 압축 하는 과정이 들기 때문에, 왠만하면 브로커와 프로듀서의 압축 방식을 동일하게 맞춰주는게 좋다고 함.
  - [카프카 산전수전 노하우 / if(kakao) dev 2018](https://tv.kakao.com/channel/3150758/cliplink/391419257)
    - 초창기 카카오의 카프카 운영 경험 (귀하다 귀해)
    - 하나의 주키퍼 클러스터에 여러 브로커 클러스터를 운영할 수 있음.
      - 이전에는 하나의 주키퍼 클러스터가 하나의 브로커 클러스터만을 관리하는 줄 알았음...;;
    - Shrinking ISR
      - Kafka 초기 버전에 있던 버그.
      - ISR 집합이 축소되면서, 한 브로커가 파티션 리더를 꽉 잡게 되고 이로 인해서 Replication이 제대로 되지 않는 상황임.
      - 카카오는 버전 업그레이드로 해결
    - 브로커 클러스터 다운 후 복구할 때, 메시지 손실 사례에 대해서.
      - 클러스터 다운 직전에 리더 파티션을 **마지막으로** 가지고 있던 브로커를 먼저 복구시켜야 메시지 손실이 없음.
      - 그런데, 클러스터 재시작 과정에서 마지막 리더 파티션을 가진 브로커를 찾기 쉽지 않을 것 같음.
      - 빠른 운영 회복성이 필요하다면 복구 과정에서 메시지 손실은 불가피 (이건 선택의 영역)
    - Lag 모니터링
      - "[Burrow](https://github.com/linkedin/Burrow)"라는 툴을 사용하라고 추천.
      - 이것 어떻게 할지 좀더 살펴봐야 할 것 같음.
    - 모니터링을 위해 `Info` 로그도 잘 살펴보자.
    - 브로커 클러스터에서 `Warning` 로그나 `Error` 로그가 발생하는 경우, 알람을 받도록 구성하자.
      - 이건 브로커 클러스터가 아니라 어떤 App을 운영하더라도 필요한 항목!
