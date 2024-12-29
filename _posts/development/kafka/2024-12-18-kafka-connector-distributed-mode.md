---
title: "Kafka Connector on k8s - Distributed Mode"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: ""
---

이번 포스트는 [Kafka Connector on k8s - Standalone Mode](/2024/12/17/kafka-connector-standalone-mode/)에서 내용이 이어집니다. 🙏


# 왜 Distributed Mode에 관심을 갖게 되었나요?

회사에서 Confluent를 통해 Kafka 클러스터를 사용하고 있습니다. 대부분의 경우 Confluent-managed Connector를 사용 했었으나, 최근 Confluent에서 지원하지 않는 데이터베이스로 Topic의 데이터를 적재해야 할 일이 생겼고, 이를 K8s에 Standalone mode로 띄웠습니다.

그렇게 한 달 정도 잘 운영하고 있다가 Confluent 정기 미팅에서 Connector를 Standalone 모드로 쓰고 있다고 말씀 드리니, Standalone 모드는 문서 어디를 봐도 "*개발, 테스트 목적으로 쓰시오*"라고 나와있지 Prod 환경에서는 "Distributed" 모드로 돌리는 것이 권장 사항이라는 피드백을 들었습니다! 암튼 여기까지가 배경이었구요! 어떻게 구현했는지 살펴보겠습니다.

# Standalone vs. Distributed: Scalability

![](/images/development/kafka/kafka-connect-standalone-vs-distributed.png){: style="width: 100%" }
{: .small .gray .text-center }

Kafka Connect를 Standalone 모드로 디플로이 한 상태에서 Pod Replica를 1에서 2로 늘리게 된다면, 각 Pod이 동일 데이터를 처리하게 됩니다. 즉, Throughput이 2배가 되는 것은 맞지만 데이터도 2배씩 중복해서 들어오게 됩니다!!

반면에 Distributed 모드로 디플로이 한 상태에서 Pod Replica를 2로 늘리게 되면, 각 Pod이 데이터를 "절반씩 나누어" 처리하게 됩니다. 즉, Throughput을 2배로 늘리면서 데이터도 중복되지 않습니다!


# Standalone vs. Distributed: Config Topics

Standalone 모드에서는 Connector를 실행할 때, 아래와 같이 실행합니다.

```bash
$ connect-standalone standalone.properties local-file-sink.properties
```

`standalone.properties`에는 Kafka Server에 접속하기 위한 정보가 담겨있었고, `local-file-sink.properties`에는 File Sink Connector를 동작하기 위한 정보가 담겨 있었습니다.

Distributed 모드에서는 뒤의 Connector Plugin 구성이 파일이 아니라 Kafka Configuration Topic에 기록됩니다.

![](https://images.ctfassets.net/gt6dp23g0g38/6SpP65mFNZLSdngL4Gf4XD/9df532fc6b1b3d2c9385d443c17770e1/kafka-connect-distributed-mode.jpg)
{: .align-center style="width: 100%" }
developer.confluent.io
{: .small .gray .text-center }

Distributed 모드는 Standalone과 달리 3가지 토픽을 필요로 합니다.

- `config.storage.topic`
  - Kafka Connector에서 실행하는 작업(task)에 대한 구성 정보
  - `local-file-sink.properties` 파일에 있던 정보가 요 토픽에 담긴다.
- `offset.storage.topic`
  - Kafka Connector의 작업이 어디까지 처리 했는지 기록한 정보
  - Standalone에서는 `offset.storage.file.filename`에 명시한 파일에 해당 정보가 담겼다.
- `status.storage.topic`
  - Kafka Connector 위에서 동작하는 개별 작업(task)의 상태를 저장하는 정보
  - Distributed 모드에서는 Fault Tolerance를 위해 각 Task가 서로 상태를 체크한다.

이렇게 구성 정보가 Kafka Connect 내부가 아니라, 외부(remote)인 Topic에 기록되기 때문에 모든 Pod이 중단되거나 유실 되더라도, Topic에 기록해두었던 정보를 바탕으로 Kafka Connect를 안전하게 다시 실행할 수 있습니다 😌


# Standalone → Distributed

제 경우는 Standalone 모드로 디플로이 한 Kafka Connect를 Distributed 모드로 전환하는 경우 였습니다. 어떤 properties를 변경 했는지 위주로 살펴보면

```diff
- offset.storage.file.filename=/tmp/connect.offsets
+ group.id=local-file-sink
+ config.storage.topic=_local_file_sink.config
+ offset.storage.topic=_local_file_sink.offset
+ status.storage.topic=_local_file_sink.status
```

우선 더이상 offset 정보를 Kafka Connect의 로컬에 저장하지 않기 때문에 `offset.storage.file.filename` 값이 필요 없습니다.

그리고 Distributed 모드로 동작하기 위해 각 태스크의 정보를 저장할 Topic 3가지를 지정 합니다.

- `config.storage.topic`
- `offset.storage.topic`
- `status.storage.topic`

Distributed 모드로 Kafka Connect를 디플로이 하게 되면, Kafka Connect를 클러스터(cluster) 형식으로 운영하게 됩니다. 그래서 이 클러스터 이름을 `group.id`로 지정해줍니다.

## Update Pod Yaml

Pod Yaml도 아래와 같이 변경합니다.

```diff
- command:
- - "connect-standalone"
- - "/etc/kafka-connect-properties/standalone/standalone.properties"
- - "/etc/kafka-connect-properties/file-sink-connector/local-file-sink.properties"
+ command:
+ - "connect-distributed"
+ - "/etc/kafka-connect-properties/distributed/distributed.properties"
```

크게 변경되는 점은 없고, container를 돌릴 때, `connect-distributed`와 위의 요구사항을 반영한 `distributed.properties`로 실행하도록 변경합니다.

## Registry Task using REST API

Standalone 모드에서는 어떤 작업(task)를 돌릴지 `.properties` 파일을 작성하고 이를 `connect-standalone`에 넘겨주었습니다.

Distributed 모드에서는 작업(task)을 등록할 때 Kafka Connect의 REST API를 사용합니다!

```bash
$ curl -X POST -H "Content-Type: application/json" \
  http://localhost:8083/connectors \
  --data "@/etc/kafka-connect-properties/file-sink-connector/local-file-sink.json"
```

위의 `curl` 명령어에서 POST의 body를 `local-file-sink.json`으로 전달 합니다.
이를 위해 `local-file-sink.json`을 아래와 같이 작성한 후, Standalone 모드에서 했던 것처럼 K8s Secret으로 만들어 Pod에 Volume Mount로 주입합니다.
(아! 참고로 요렇게 `.json` 파일로 주입하는 경우 경로 맨 앞에 `@`를 꼭 넣어줘야 했습니다;;)

```json
// @./local-file-sink.json
{
  "name": "local-file-sink",
  "config":
  {
      "connector.class": "FileStreamSink",
      "tasks.max": "1",
      "topics": "szcode2.qa.avro.server",

      "file": "/tmp/test.sink.txt"
  }
}
```

`curl`을 통한 작업 등록은 Kafka Connector Pod이 디플로이 되고, 몇초간의 랜딩 후 등록이 가능합니다.

그리고, 작업 등록 후에 또 1분~3분 정도 기다리면, Confluent에서도 Distributed 모드의 Connector가 등록된 것을 확인할 수 있습니다 😌


# 맺음말

뭔가 Kafka를 처음 공부할 때 봤던 기억이 어렴풋이 나는 것 같은데, 직접 띄워보니 왜 Standalone 모드와 Distributed 모드, 두 방식이 존재하는지 잘 와닿는 것 같습니다 ㅎㅎ (역시 직접 해봐야 늘어)

Distributed Mode 속성 중에 `rest.advertised.host.name` 쪽은 아직 제대로 못 봤는데, 나중에 시간이 나면 좀더 살펴보고자 합니다. (적당히 끊어주는 것도 필요 ㅎㅎ)

이번에 회사 업무로 Kafka 작업을 꽤 많이 해보게 되어서 다음 자격증으로 Confluent Certificate를 목표로 설정 했습니다! 이쪽 생태계에 대해서도 더더더 많이 알 수 있게 되길 ㅎㅎ 그럼 앞으로도 아좌잣! 👊
