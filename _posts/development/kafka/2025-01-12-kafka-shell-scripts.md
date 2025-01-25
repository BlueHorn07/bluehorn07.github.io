---
title: "Kafka Shell Script 둘러보기"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "Kafka에서 제공하는 42개의 Shell Script 도구 전부 살펴보기 🔍"
---

# 들어가며

# 입문

- `kafka-topics.sh`
- `kafka-configs.sh`
- `kafka-console-producer.sh`
- `kafka-console-consumer.sh`
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

이게 현재 리더 파티션이 순서대로 `101`, `100`, `102`에 존재하는데, 이걸 +1 브로커로 재분배 해봅시다! 
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

아직 반영이 안 되었다. 리더 재선출을 위해서는 `kafka-leader-election.sh` 명령어를 추가로 실행해야 한다.

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

`0`번 파티션이 브로커 `102`번으로 옮겨졌다!! ㅎㅎ 강제로 핫스팟으로 만들어졌다 ㅋㅋ

그러나 이런 파티션 재설정은 카프카 내부에 많은 작업을 요구하기해 클러스터에 부하가 올 수 있고, 또 해당 토픽을 구독하는 컨슈머에게도 리밸런싱을 트리거 하게 됩니다. 그래서 운영 환경에서는 재설정을 아주 조심히 진행해야 하며, 만약 장애가 예상된다면 파티션을 신규 생성해 해결하는 것이 방법일 수도 있습니다.

# Kafka Delete record

# Kafka Dump Logs



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
- `kafka-cluster.sh`
- `kafka-dump-log.sh`
- `kafka-metadata-shell.sh`
- `kafka-storage.sh`
- `zookeeper-security-migration.sh`

# 참고자료

- [kyeongseo.oh님의 블로그](https://kyeongseo.tistory.com/entry/kafka-kafka-reassign-partitionssh%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%9C-%ED%8C%8C%ED%8B%B0%EC%85%98-%EC%9E%AC%ED%95%A0%EB%8B%B9)