---
title: "Kafka Shell Script 둘러보기"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "Kafka에서 제공하는 42개의 Shell Script 도구 전부 살펴보기 🔍"
---

# 들어가며

회사에서 Confluent를 통해 Kafka 클러스터를 잘 사용하고 있었습니다. 그런데, 이 카프카에 대해서 좀더 자세히 알고, 전문성을 갖추고 싶다는 생각이 들어서 2025년 첫 목표로 Confluent의 Kafka 자격증인 [Confluent Certified Developer for Apache Kafka®](https://training.confluent.io/examdetail/confluent-dev) 자격증을 준비하고 있습니다 ✌️

이번 포스트는 작성 시점 기준 최신 버전인 Kafka 3.9에서 제공하는 모든 Kafka Shell Script의 기능을 한번씩 살펴보는 걸 목표로 기록한 포스트 입니다.

## With Authentication

대부분 로컬 K8s에 디플로이 한 Kafka 클러스터에 실행해보았고, 일부 경우는 Confluent Cluster에서 실험해보았습니다.

로컬에 디플로이 한 카프카 클러스터에서는 편하게 실험해보기 위해서 Authentication 과정을 없앴습니다! 만약, SASL 인증을 해야 한다면, Shell 실행 때 아래와 같이 추가로 설정해줍시다.

```bash
$ kafka-xxxx.sh \
  --bootstrap-servers xxxx:9092 \
  --command-config /tmp/xxxx.properties
```

Kafka Shell은 로컬에서 실행할 수도 있을 것이고, Kafka Shell을 실행할 수 있는 컨테이너에 `kubectl exec`로 접속해 실행할 수도 있습니다. 제가 CCDAK 자격증을 준비하면서 느낀 점은 "**컨테이너로 격리된 환경에서 Kafka Shell을 실행하는게 가장 안전하다**"는 것 입니다. 맥북 로컬의 Kafka Shell에서는 Exception을 뿜으며 제대로 동작하지 않던 것이 Kafka Broker pod에서는 제대로 동작하는 경우가 종종 있었습니다!

# 입문

- `kafka-topics.sh`
  - "[Kafka Shell: `kafka-topics.sh`](/2025/01/11/kafka-shell-kafka-topics-sh/)" 포스트로 분리
- `kafka-configs.sh`
  - 이미 생성한 카프카 토픽의 Config를 변경할 때 사용함. `--alter`를 사용.
  - "[Kafka Shell: `kafka-topics.sh`](/2025/01/11/kafka-shell-kafka-topics-sh/)" 포스트에 함께 기술함.
- `kafka-console-producer.sh`
  - "[Kafka Shell: Console Produce/Consume](/2025/01/12/kafka-shell-console-produce-and-consume/)" 포스트로 분리
- `kafka-console-consumer.sh`
  - "[Kafka Shell: Console Produce/Consume](/2025/01/12/kafka-shell-console-produce-and-consume/)" 포스트로 분리
- `kafka-consumer-groups.sh`
  - `--list`
    - 컨슈머 그룹 리스팅
  - `--describe --group xxxx`
    - 컨슈머 그룹 상세 보기
  - `--execute --to-latest/--to-earlist/...`
    - 컨슈머 그룹의 offset을 초기화 하거나 최신으로 이동 시키려고 할 때

# Performance Test

카프카 클러스터의 퍼포먼스를 측정하기 위한 도구 입니다.

```bash
$ kafka-producer-perf-test.sh \
    --producer-props bootstrap.servers=bitnami-kafka:9092 \
    --topic test \
    --num-records 100 \
    --throughput 10 \
    --record-size 100
52 records sent, 10.3 records/sec (0.00 MB/sec), 8.3 ms avg latency, 316.0 ms max latency.
100 records sent, 10.027073 records/sec (0.00 MB/sec), 4.84 ms avg latency, 316.00 ms max latency, 1 ms 50th, 13 ms 95th, 316 ms 99th, 316 ms 99.9th.
```

```bash
$ kafka-consumer-perf-test.sh \
    --bootstrap-server bitnami-kafka:9092 \
    --topic test \
    --messages 10
start.time, end.time, data.consumed.in.MB, MB.sec, data.consumed.in.nMsg, nMsg.sec, rebalance.time.ms, fetch.time.ms, fetch.MB.sec, fetch.nMsg.sec
2025-01-15 09:26:16:817, 2025-01-15 09:26:20:246, 0.0096, 0.0028, 105, 30.6212, 3411, 18, 0.5312, 5833.3333
```

# Reassign Partitions

토픽을 이루는 리더 파티션이 하나의 브로커에 올리는 핫스팟(Hot spot) 현상을 방지하기 위한 도구 입니다. `kafka-reassign-partitions.sh` 명령어를 사용하면, 리더 파티션과 팔로워 파티션 위치를 재설정 합니다.

먼저 재분배 작업을 위해 적당한 파티션과 Replication Factor를 가진 토픽을 하나 생성합니다.

```bash
$ kafka-topics.sh \
  --bootstrap-server bitnami-kafka:9092 \
  --topic test2 \
  --partitions 3 \
  --replication-factor 3 \
  --create
$ kafka-topics.sh \
  --bootstrap-server bitnami-kafka:9092 \
  --topic test2 \
  --describe
Topic: test2    TopicId: a3vf0OqaSN6sqjtAVfrOhw PartitionCount: 3       ReplicationFactor: 3    Configs:
        Topic: test2    Partition: 0    Leader: 101     Replicas: 101,100,102   Isr: 101,100,102        Elr: N/A        LastKnownElr: N/A
        Topic: test2    Partition: 1    Leader: 100     Replicas: 100,102,101   Isr: 100,102,101        Elr: N/A        LastKnownElr: N/A
        Topic: test2    Partition: 2    Leader: 102     Replicas: 102,101,100   Isr: 102,101,100        Elr: N/A        LastKnownElr: N/A
```

이게 현재 리더 파티션이 각 브로커에 고르게 분산 되어 있습니다. 이를 `102`번 브로커에 집중시켜 강제로 핫스팟으로 만들어봅시다!
일단 명령어를 실행하기 위해선 `--reassignment-json-file`에 전달할 json 파일이 필요합니다.

```bash
$ cat <<EOF > /tmp/partitions.json
{
  "partitions":
  [
    { "topic": "test2", "partition": 0, "replicas": [102,100,101] }
  ],
  "version": 1
}
EOF
```

```bash
$ kafka-reassign-partitions.sh \
  --bootstrap-server bitnami-kafka:9092 \
  --reassignment-json-file /tmp/partitions.json \
  --execute
Current partition replica assignment

{"version":1,"partitions":[{"topic":"test2","partition":0,"replicas":[100,102,101],"log_dirs":["any","any","any"]}]}

Save this to use as the --reassignment-json-file option during rollback
Successfully started partition reassignment for test2-0
```

```bash
$ kafka-reassign-partitions.sh \
  --bootstrap-server bitnami-kafka:9092 \
  --reassignment-json-file /tmp/partitions.json \
  --verify
Status of partition reassignment:
Reassignment of partition test2-0 is completed.

Clearing broker-level throttles on brokers 100,101,102
Clearing topic-level throttles on topic test2
```

그리고 다시 `--describe`를 해보면

```bash
$ kafka-topics.sh \
  --bootstrap-server bitnami-kafka:9092 \
  --topic test2 \
  --describe
Topic: test2    TopicId: a3vf0OqaSN6sqjtAVfrOhw PartitionCount: 3       ReplicationFactor: 3    Configs:
        Topic: test2    Partition: 0    Leader: 101     Replicas: 101,102,100   Isr: 101,100,102        Elr: N/A        LastKnownElr: N/A
        Topic: test2    Partition: 1    Leader: 100     Replicas: 100,101,102   Isr: 100,102,101        Elr: N/A        LastKnownElr: N/A
        Topic: test2    Partition: 2    Leader: 102     Replicas: 102,101,100   Isr: 102,101,100        Elr: N/A        LastKnownElr: N/A
```

아직 반영이 안 되었다. 리더 재선출을 위해서는 `kafka-leader-election.sh` 명령어를 추가로 실행해야 합니다.

```bash
$ kafka-leader-election.sh \
  --bootstrap-server bitnami-kafka:9092 \
  --topic test2 \
  --partition 0 \
  --election-type preferred
```

그리고 다시 `--describe`를 해보면

```bash
$ kafka-topics.sh \
  --bootstrap-server bitnami-kafka:9092 \
  --topic test2 \
  --describe
Topic: test2    TopicId: a3vf0OqaSN6sqjtAVfrOhw PartitionCount: 3       ReplicationFactor: 3    Configs:
        Topic: test2    Partition: 0    Leader: 102     Replicas: 102,100,101   Isr: 101,100,102        Elr: N/A        LastKnownElr: N/A
        Topic: test2    Partition: 1    Leader: 100     Replicas: 101,100,102   Isr: 100,102,101        Elr: N/A        LastKnownElr: N/A
        Topic: test2    Partition: 2    Leader: 102     Replicas: 102,101,100   Isr: 102,101,100        Elr: N/A        LastKnownElr: N/A
```

`0`번 파티션이 브로커 `102`번으로 옮겨졌다!! ㅎㅎ 강제로 핫스팟으로 만들어졌습니다 ㅋㅋ

그러나 이런 파티션 재설정은 카프카 내부에 많은 작업을 요구하기해 클러스터에 부하가 올 수 있고, 또 해당 토픽을 구독하는 컨슈머에게도 리밸런싱을 트리거 하게 됩니다. 그래서 운영 환경에서는 재설정을 아주 조심히 진행해야 하며, 만약 장애가 예상된다면 파티션을 신규 생성해 해결하는 것이 방법일 수도 있습니다.

# Kafka Delete record

# Kafka Dump Logs

# Kafka Cluster

`kafka-broker-api-versions.sh`로 카프카 클러스터를 구성하는 브로커 목록과 각 브로커가 지원하는 API 버전을 확인할 수 있습니다.

```bash
$ kafka-broker-api-versions.sh \
  --bootstrap-server bitnami-kafka:9092
bitnami-kafka-broker-1.bitnami-kafka-broker-headless.kafka.svc.cluster.local:9092 (id: 101 rack: null) -> (
        Produce(0): 0 to 11 [usable: 11],
        Fetch(1): 0 to 17 [usable: 17],
        ListOffsets(2): 0 to 9 [usable: 9],
        Metadata(3): 0 to 12 [usable: 12],
        LeaderAndIsr(4): 0 to 7 [usable: 7],
        StopReplica(5): 0 to 4 [usable: 4],
        UpdateMetadata(6): 0 to 8 [usable: 8],
        ...
)
...
```

브로커의 각 API는 고유한 API Key 값과 Versioning이 된다고 합니다. 현제 브로커에서 지원하는 API 버전의 범위를 확인할 수 있습니다.


# Connectors

- `connect-distributed.sh`
- `connect-standalone.sh`


# 고급

- `kafka-e2e-latency.sh`
- `kafka-mirror-maker.sh`
- `kafka-streams-application-reset.sh`
- `zookeeper-server-start.sh`
- `connect-mirror-maker.sh`
- `kafka-features.sh`
- `kafka-producer-perf-test.sh`
- `zookeeper-server-stop.sh`
- `connect-plugin-path.sh`
- `kafka-get-offsets.sh`
- `kafka-reassign-partitions.sh`
- `kafka-transactions.sh`
- `zookeeper-shell.sh`
- `kafka-jmx.sh`
- `kafka-replica-verification.sh`
- `kafka-verifiable-consumer.sh`
- `kafka-acls.sh`
- `kafka-consumer-perf-test.sh`
- `kafka-leader-election.sh`
- `kafka-run-class.sh`
- `kafka-verifiable-producer.sh`
- `kafka-broker-api-versions.sh`
- `kafka-delegation-tokens.sh`
- `kafka-log-dirs.sh`
- `kafka-server-start.sh`
- `trogdor.sh`
- `kafka-client-metrics.sh`
- `kafka-delete-records.sh`
- `kafka-metadata-quorum.sh`
- `kafka-server-stop.sh`
- `kafka-dump-log.sh`
- `kafka-metadata-shell.sh`
- `kafka-storage.sh`
- `zookeeper-security-migration.sh`
- `kafka-cluster.sh`

# 참고자료

- [kyeongseo.oh님의 블로그](https://kyeongseo.tistory.com/entry/kafka-kafka-reassign-partitionssh%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%9C-%ED%8C%8C%ED%8B%B0%EC%85%98-%EC%9E%AC%ED%95%A0%EB%8B%B9)