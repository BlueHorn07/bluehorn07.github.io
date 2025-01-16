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
