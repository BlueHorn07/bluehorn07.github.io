---
title: "Kafka Shell: `kafka-topics.sh`"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "Kafka에서 Topic을 조회, 생성, 변경, 삭제에 쓰는 도구! Kafka에서 가장 자주 사용하는 Shell 명령어!!"
---

# 들어가며

회사에서 Confluent로 Kafka 클러스터를 운영하고 있고, CCDAK 자격증 시험을 준비하고 있습니다. 이번 포스트에서는 `bin/kafka-topics.sh` 명령어에 대해 살펴보겠습니다.

## 시작하기 전에

로컬에서 Kafka를 실행해 핸즈온 해보고 있습니다! 로컬 맥북에 Kafka 클러스터를 디플로이 하는 방법은 "[Deploy Kafka on Kubernetes ☸](/2025/01/05/deploy-kafka-on-k8s/)" 포스트에서 기술하였으니 참고 바랍니다! 본 포스트는 해당 포스트에 기술한 방식으로 디플로이한 Kafka 클러스터의 Broker 중 하나에 접속해 진행한 내용을 정리한 것입니다.

## 클러스터 접속

bitnami-kafka를 Authentication 없이 접속하는 `PLAINTEXT` 모드로 디플로이 합니다. 그리고 디플로이한 Broker 중 하나에 `kubectl exec`로 접속 합니다.

```bash
$ kubectl exec -it bitnami-kafka-broker-2 -n kafka -- sh
```

참고로 bitnami-kafka를 디플로이 한 것이기 때문에 Kafka Shell이 `/opt/bitnami/kafka/bin` 경로에 모여 있습니다! `cd` 명령어를 사용해 이 경로로 해당 경로로 이동해도 되지만, Kafka Shell 도구들이 이미 등록이 되어 있기 때문에 아무 경로에서 진행해도 상관 없습니다 :)

# Create topic

```bash
$ kafka-topics.sh \
    --bootstrap-server=bitnami-kafka.kafka.svc.cluster.local:9092 \
    --create \
    --topic test
Created topic test.
```

`--create --topic test`를 주면 해당 이름으로 토픽이 생성 됩니다. 이때, 따로 설정하지 않으면 partition 갯수나 `replication.factor`와 같은 값은 브로커에 설정된 기본값으로 설정 됩니다.

# Describe all topics

```bash
$ kafka-topics.sh \
    --bootstrap-server=bitnami-kafka.kafka.svc.cluster.local:9092 \
    --describe
Topic: test     TopicId: fj5dT8R4QkGFObM3IsrLRA PartitionCount: 1       ReplicationFactor: 1    Configs: 
        Topic: test     Partition: 0    Leader: 102     Replicas: 102   Isr: 102        Elr: N/A        LastKnownElr: N/A
```

현재 Kafka 클러스터에 존재하는 토픽을 나열하고, 세부 정보를 나열 합니다. 따로 설정하지 않았기 때문에 Partition과 `replication.factor`가 둘다 1로 설정되었습니다.
`replication.factor`라는 것은 리더 파티션의 내용을 복제한 팔로워 파티션이 존재하지 않는 다는 것을 말합니다. 각 파티션에 하나의 복제본만 존재하면 이것이 파티션 그 자체이자 리더 역할을 합니다.

다음은 Partition Level 정보 입니다. 내용을 각 항목을 설명하면 아래와 같습니다.

- `Leader`
  - 토픽의 파티션이 Producer/Consumer와 통신하며 신규 데이터가 쌓이거나, 쌓인 데이터가 소비 되는 파티션 입니다.
  - 출력로 보면 `Partition: 0 Leader: 102`로 되어 있어, 첫번째 파티션의 리더 파티션이 `102`라는 Id의 브로커에 존재함을 알 수 있습니다.
- `Replicas`
  - 리더 파티션을 복제한 팔로워 파티션을 말합니다.
  - 출력에서는 `Replicas: 102`로 되어 있어 브로커 `102`가 복제본을 가지고 있습니다.
- `Isr`
  - In-Sync Replica 목록입니다.
  - 팔로워 파티션 중에 리더 파티션과 동일한 데이터를 가지고 있으며, 리더 파티션 장애 시, 리더 파티션으로 승격될 수 있는 복제본의 "집합"을 말합니다.
  - 출력에서는 `Isr: 102`로 되어 있어 브로커 `102`에 존재하는 팔로워 파티션이 싱크 상태인 ISR에 속합니다.
- `Elr` & `LastKnownElr`
  - End Log Region의 약자 입니다.
  - Kafka의 확장 기능 중 일부에서 사용하는 개념으로, 본래 Kafka 클러스터는 데이터를 브로커의 디스크 공간에 저장합니다. 그러나 Kafka Tiered Storage를 사용하게 되면, 오래된 데이터를 저렴한 스토리지(ex: AWS S3)에 저장합니다.
  - ELR은 파티션 데이터가 로컬에서 더이상 유지되지 않고, 외부 스토리지로 이동한 시점을 가리킵니다. 즉, "로컬 디스크에서 접근 가능한 로그의 끝 지점(offset)"을 나타냅니다. 이 값 이전의 데이터는 외부 스토리지에서만 읽을 수 있습니다.

# Alter Topic Partition Number

```bash
$ kafka-topics.sh \
    --bootstrap-server=bitnami-kafka.kafka.svc.cluster.local:9092 \
    --alter \
    --topic test \
    --partitions 2 \
    --replication-factor 2
```

토픽의 설정을 변경하고 싶다면, `--alter`를 사용하면 됩니다.
단, `kafka-topics.sh --alter`에서 Topic의 모든 Config를 변경할 수 있는 건 아니고,
오직 토픽 파티션에 대한 것만 변경 가능합니다. 또, 토픽 파티션을 늘리는 건 가능하지만, 줄이는 건 불가능합니다!

그래서 때로는 `kafka-configs.sh`를 사용해 토픽에 설정된 Config를 변경해야 할 때도 있습니다.

```bash
$ kafka-configs.sh \
    --bootstrap-server=bitnami-kafka.kafka.svc.cluster.local:9092 \
    --alter \
    --topic test \
    --add-config retention.ms=360000
```

이것으로 토픽이 데이터를 유지 하는 기간인 `retention.ms`를 기존 7일(604800000)에서 1시간(3600000)으로 변경해볼 수 있습니다.

`kafka-configs.sh`는 토픽 외에도 브로커, 사용자와 카프카 클라이언트 어플리케이션에 설정된 Config를 확인하고 변경할 수 있습니다!

<br/>

참고로 한번 설정하면 토픽을 지우지 않는 이상 변경이 불가능한 Config도 있습니다. 팔로워 파티션으로 리더 파티션을 복제하는 정도인 `replication.factor` 값이 변경 불가능한 Config 입니다. (단, 이것도 파티션 재할당 도구인 `kafka-reassign-partitions.sh`를 사용하면 간접적으로 변경할 수 있다고 합니다.)

# Delete topic

```bash
$ kafka-topics.sh \
    --bootstrap-server=bitnami-kafka.kafka.svc.cluster.local:9092 \
    --delete \
    --topic test
```

삭제는 `--delete` 명령어를 사용하면 됩니다 EzEz

# 맺음말

이것으로 Kafka 클러스터의 가장 기본적인 명령어인 `kafka-topics.sh`를 살펴보았습니다! (얏호) 이제 다른 명령어들을 핸즈온 해봅시다!

