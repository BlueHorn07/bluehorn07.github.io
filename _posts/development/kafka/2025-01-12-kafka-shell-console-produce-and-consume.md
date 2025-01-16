---
title: "Kafka Shell: Console Produce/Consume"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "Consume로 Kafka 토픽에 데이터를 보내고, 확인하는 방법에 대해"
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

# Kafka Console Producer

```bash
$ kafka-console-producer.sh \
    --bootstrap-server bitnami-kafka:9092 \
    --topic test
> haha
> hoho
```

Kafka 클러스터를 테스트 할 때 많이 사용하게 되는 명령어 입니다. 요 명령어를 통해 아주 쉽게 더미 데이터를 토픽에 적재하고 확인해볼 수 있습니다.

다만, 위의 명령어는 토픽에 데이터를 넣을 때, key를 설정하지 않고 `NULL` 값으로 보내게 됩니다. 이 경우, 레코드는 Round Robin 방식으로 토픽의 파티션 중 하나에 적재 됩니다. 그래서 

```bash
$ kafka-console-producer.sh \
    --bootstrap-server bitnami-kafka:9092 \
    --topic test \
    --property key.separator=:
> haha:haha
> hoho:hoho
```

요렇게 `key.separator`를 설정하면 key 값을 함께 보낼 수 있습니다.

# Kafka Console Consumer

```bash
$ kafka-console-consumer.sh \
    --bootstrap-server bitnami-kafka:9092 \
    --topic test \
    --from-beginning
haha
hoho
ha:ha
ho:ho
```

`kafka-console-consumer.sh`는 콘솔로 Kafka 토픽의 내용을 확인하는 도구 입니다. `--from-beginning` 옵션을 주면, 토픽의 데이터를 처음부터 읽어옵니다.

그외에도 `--partition` 옵션으로 특정 파티션에 저장된 레코드만 읽을 수도 있습니다.

```bash
$ kafka-console-consumer.sh \
    --bootstrap-server bitnami-kafka:9092 \
    --topic test \
    --group console
...
```

또, 하나 유용한 옵션을 Consoler Consumer를 컨슈머 그룹으로 취급하게 하는 `--group` 옵션 입니다. 이 옵션을 사용하면, Console Consumer 자체로 이뤄진 컨슈머 그룹가 생성되며, 그 컨슈머 그룹이 어디까지 읽었는지 Offset 정보가 Kafka 클러스터의 Offset 토픽에 저장됩니다.

실제로 컨슈머 그룹이 생성된게 맞는지 확인하려면 `kafka-consumer-groups.sh` 명령어를 사용하면 됩니다.

```bash
$ kafka-consumer-groups.sh \
    --bootstrap-server bitnami-kafka:9092 \
    --list
console
console-consumer-9123
console-consumer-88809
```

```bash
$ kafka-consumer-groups.sh \
    --bootstrap-server bitnami-kafka:9092 \
    --describe \
    --group console
GROUP           TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID                                           HOST            CLIENT-ID
console         test            0          5               5               0               console-consumer-7c2f547f-2d90-48cc-9aab-19b779cec481 /10.42.0.78     console-consumer
```

