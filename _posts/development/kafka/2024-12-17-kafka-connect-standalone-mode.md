---
title: "Kafka Connector on k8s - Standalone Mode"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "Kafka Connect로 Topic 데이터를 손쉽게 전달하기 ✌️ File Sink 플러그인으로 핸즈온까지!"
---

# 들어가며

회사에서 Confluent를 통해 Kafka 클러스터를 사용하고 있습니다. 대부분의 경우 Confluent-managed Connector를 사용 했었으나, 최근 Confluent에서 지원하지 않는 데이터베이스로 Topic의 데이터를 적재해야 할 일이 생겼습니다...! 저는 Confluent를 이해할 좋은 기회라고 생각했고, Kubernetes 위에서 Sink Connector를 운영하는 경험을 해볼 수 있었습니다.

구글에 검색해보면, [Strimzi](https://strimzi.io/)를 사용해 Kafka Cluster와 Connector까지 띄우는 경우를 많이 봤는데요. Strimzi도 매력적인 도구이지만, 이번 작업에서는 그것 없이 Sink Connector를 띄워보고 싶었습니다! 그래서 저 말고도 이런 니즈를 가진 분들이 있을까 하여 내용을 정리해보았습니다 ㅎㅎ


# Kafka Connector란?

내용을 들어가기 전에 잠깐 "Kafka Connector"에 대해 설명하고자 합니다. "Kafka Connector"는 Kafka Topic에 데이터를 Produce하거나 Consume 하는 패턴과 기능을 정리해 Interface로 만든 것 입니다.

예를 들어, Topic 데이터를 AWS S3에 적재하고자 하는 작업은 Kafka를 사용하는 팀과 기업에서 아주 빈번하게 일어나는 패턴입니다. 이를 공통 사례를 지원하기 위해 S3 Sink Connector라는 패키지가 존재합니다. 만약 이게 없었다면, Kafka에서 데이터를 읽어 S3에 적재하는 S3 Consumer를 모두가 코드를 일일이 작성해야 했을 것 입니다.

# Standalone vs. Distributed

Kafka Connector는 Standalone 모드와 Distributed 모드, 2가지 모드를 지원 합니다. 그동안 Confluent에서 버튼 딸깍으로 Connector를 디플로이 하던 저에게 둘 중 어떤 걸 선택할지는 큰 고민 포인트 였습니다.

Confluent의 [Kafka Connect 문서](https://docs.confluent.io/platform/current/connect/index.html#standalone-workers)에는 요렇게 나와 있습니다.

- Standalone workers
  - Standalone mode is the simplest mode, where a single process is responsible for executing all connectors and tasks.
  - Standalone mode is convenient for getting started, **during development**, and in certain situations where only one process makes sense, such as collecting logs from a host.
- Distributed Workers
  - Distributed mode provides scalability and automatic fault tolerance for Kafka Connect. In distributed mode, you start many worker processes using the same group.id and they coordinate to schedule execution of connectors and tasks across all available workers.

요약하면, Standalone 모드는 싱글 프로세스 방식이고, Distributed 모드는 확장성과 자동 복구를 지원한다고 합니다. 이런 점 때문에 Standalone 모드는 테스트와 개발 과정에서 권장 되는 모드입니다.

일단 처음이니 Standalone 모드로 시작해보기로 마음 먹었고, 추후에 Confluent 쪽 조언을 따라 Distributed 모드로 전환 하였습니다. 비롯 추가 작업이 있었지만, Kafka Connector를 처음 디플로이 해보는 상황이라면 Standalone 모드부터 시작해보길 추천드립니다 😊

# File Sink Connector

File Sink Connector는 Kafka Connect에서 기본으로 제공되는 Connector 입니다. 그래서 plugin 설치 과정이 따로 필요 없습니다.

처음엔 어떤 Docker Image를 사용할지 정해야 했는데요. 저는 Confluent에서 관리하는 Connector base 이미지인 [`confluentinc/cp-kafka-connect:7.7.1.amd64`](https://hub.docker.com/r/confluentinc/cp-kafka-connect)를 사용하였습니다.

그리고 아래와 같이 K8s pod yaml을 구성하여 테스트를 진행하였습니다.

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: file-sink-connector
spec:
  selector:
    matchLabels:
      app: file-sink-connector
  template:
    metadata:
      labels:
        app: file-sink-connector
    spec:
      containers:
      - name: file-sink-connector
        image: confluentinc/cp-kafka-connect:7.7.1.amd64
        command: ["sleep", "infinity"]
        env:
          - name: CLASSPATH
            value: /usr/share/filestream-connectors/*
        resources:
          limits:
            cpu: 1000m
            memory: 2048Mi
          requests:
            cpu: 500m
            memory: 1024Mi
        ports:
        - containerPort: 8083
```

처음에 pod을 띄워서 kafka connect에서 쓸 파일들이 어디에 있는지 파악하기 위해서
pod command를 `sleep infinity`로 주고 시작 했습니다.

작업을 하다보니 CPU/Mem 리소스가 부족해 Connector가 안 뜨는 경우가 있어서 리소스는 넉넉하게 할당 했습니다. 케이스에 따라 이것보다 줄이셔도 됩니다. 💰

무한 대기 중인 k8s pod에 접속해 살펴보면 아래 두 경로에 중요한 파일과 스크립트가 있는 것을 볼 수 있습니다

- `/etc/kafka/`
  - 각종 default properties 파일이 모여 있습니다.
  - `connect-standalone.properties`, `connect-distributed.properties`, ...
- `/usr/bin/`
  - 각종 binary script들이 모여 있습니다.
  - Connector를 실행하는 `connect-standalone`, `connect-distributed` 명령어도 이곳에 위치 합니다.

## Configure Properties

이제 Connector에서 Confluent에 접속하기 위한 Property를 구성해봅시다.

### Standalone Properties

Standalone Connector가 동작하고, Confluent 플랫폼과 통신할 수 있도록 Properties를 정의합니다. 속성 별로 자세한 내용은 [Confluent 문서](https://docs.confluent.io/platform/current/connect/references/allconfigs.html#common-worker-configuration)를 참고하시길 바랍니다.

```toml
# @./standalone.properties
bootstrap.servers=xxxx.aws.confluent.cloud:9092
ssl.endpoint.identification.algorithm=https
security.protocol=SASL_SSL
sasl.mechanism=PLAIN
sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username="xxxx" password="xxxx";
request.timeout.ms=20000
retry.backoff.ms=500

consumer.bootstrap.servers=xxxx.aws.confluent.cloud:9092
consumer.ssl.endpoint.identification.algorithm=https
consumer.security.protocol=SASL_SSL
consumer.sasl.mechanism=PLAIN
consumer.sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username="xxxx" password="xxxx";
consumer.request.timeout.ms=20000
consumer.retry.backoff.ms=500
consumer.auto.offset.reset=earliest

offset.flush.interval.ms=10000
offset.storage.file.filename=/tmp/connect.offsets

# Required connection configs for Confluent Cloud Schema Registry
key.converter=org.apache.kafka.connect.storage.StringConverter
value.converter=io.confluent.connect.avro.AvroConverter
value.converter.basic.auth.credentials.source=USER_INFO
value.converter.schema.registry.basic.auth.user.info=xxxx:xxxx
value.converter.schema.registry.url=https://xxxx.aws.confluent.cloud
```

Schema Registry 부분은 본인이 Subscribe 할 토픽의 key/value에 맞춰 설정하도록 합니다. 저는 Avro value로 값이 담기고 있어 `AvroConverter`를 사용했습니다.

작성한 `standalone.properties` 파일을 Pod에서 사용할 수 있도록 아래 커맨드를 통해 K8s Secret으로 생성합니다.

```bash
$ kubectl create secret generic file-sink-standalone-properties --from-file=standalone.properties
```

<div class="notice" markdown="1">

[Kafka Connect Configuration 문서](https://docs.confluent.io/platform/current/installation/docker/config-reference.html#kconnect-long-configuration)를 살펴보면, `.properties` 파일이 아니라 환경 변수(ENV)를 통해 세팅하는 부분이 나옵니다. 문서에 따르면, `CONNECT_`라는 접두사(prefix)를 붙여 Connector Configuration이 가능합니다.

저도 처음엔 ENV를 통해 세팅하는 방법을 사용 했습니다만, 작업을 하다보니 별도 파일로 분리하는 것이 더 편리하고, 또 요게 Standalone Properties만 `CONNECT_` ENV로 넣을 수 있고, Connector Plugin의 Properties는 따로 `.properties` 파일 형태로 존재해야 했습니다. 그래서 형식을 통일하고자 Properties 정의를 모두 `xxxx.properties`로 통일하였습니다 🙏

</div>

### File Sink Properties

이어서 File Sink Plugin을 사용하기 위한 Properties를 정의합니다. 속성 별로 자세한 내용은 [Confluent 문서](https://docs.confluent.io/platform/current/connect/filestream_connector.html)를 참고하시길 바랍니다.

`file` 속성에 정의한 경로에 Topic 데이터가 한줄씩 쌓이게 됩니다.

```toml
# @./local-file-sink.properties
name=local-file-sink
connector.class=FileStreamSink
tasks.max=1
file=/tmp/test.sink.txt
topics=YOUR_TOPIC
```

그리고 마찬가지로 K8s Secret으로 만들어 Pod에서 이 파일을 사용할 수 있도록 합니다.

```bash
$ kubectl create secret generic local-file-sink-properties --from-file=local-file-sink.properties
```

## Deploy Connector

이제 아래의 yaml 파일을 사용해 File Sink Connector를 디플로이 합니다.

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: file-sink-connector
spec:
  selector:
    matchLabels:
      app: file-sink-connector
  template:
    metadata:
      labels:
        app: file-sink-connector
    spec:
      containers:
      - name: file-sink-connector
        image: confluentinc/cp-kafka-connect:7.7.1.amd64
        command: ["connect-standalone", "/etc/kafka-connect-properties/standalone/standalone.properties", "/etc/kafka-connect-properties/file-sink-connector/local-file-sink.properties"]
        env:
          - name: CLASSPATH
            value: /usr/share/filestream-connectors/*
        resources:
          limits:
            cpu: 1000m
            memory: 2048Mi
          requests:
            cpu: 500m
            memory: 1024Mi
        ports:
        - containerPort: 8083
        volumeMounts:
          - name: local-file-sink-properties
            mountPath: /etc/kafka-connect-properties/file-sink-connector/
            readOnly: true
          - name: file-sink-standalone-properties
            mountPath: /etc/kafka-connect-properties/standalone/
            readOnly: true
      volumes:
        - name: local-file-sink-properties
          configMap:
            name: local-file-sink-properties
        - name: file-sink-standalone-properties
          configMap:
            name: file-sink-standalone-properties
```


# 맺음말

File Sink 플러그인이 아닌 다른 플러그인을 사용하고 싶다면, 위의 틀에서 Connector Plugin에 대한 부분을 형식에 맞게 수정하면 됩니다.

다음으로는 확장성과 안정성을 갖춘 Distributed 모드로 Connector를 띄워봅시다! 👊
