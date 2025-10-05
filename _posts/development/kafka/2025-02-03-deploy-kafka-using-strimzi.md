---
title: "Deploy Kafka Cluster using Strimzi"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "Strimzi Operator로 Kafka 클러스터를 선언형으로 디플로이 하고 관리하기!"
---

회사에서 Confluent를 통해 Kafka 클러스터를 잘 사용하고 있었습니다. 그런데, 이 카프카에 대해서 좀더 자세히 알고, 전문성을 갖추고 싶다는 생각이 들어서 2025년 목표로 Confluent의 Kafka 자격증인 [Confluent Certified Developer for Apache Kafka®](https://training.confluent.io/examdetail/confluent-dev) 자격증을 준비하고 있습니다 ✌️ 전체 포스트는 [여기](/categories/kafka)에서 확인할 수 있습니다.
{: .notice }

# 들어가며

Kafka를 깊이있게 공부하면서 관련된 eco-system도 살펴보고 있습니다! 회사에서 Kafka 관련 작업을 많은 아티클이나 회사들에서 `Strimzi`로 Kafka 클러스터나 Kafka Connect를 디플로이 하는 걸 접했습니다.
그것들을 보면서 '오오...! 나도 언젠가 한번 써봐야 겠다!'라고 생각했는데요! 이번에 자격증을 준비하면서 핸즈온과 함께 살펴본 이야기를 적어보고자 합니다. 참고로 "*Strimzi*"는 Stream을 체코어로 표현한 거라고 합니다! 🇨🇿

# Deploy Strimzi Operator

[Strimzi 공식 문서](https://strimzi.io/docs/operators/latest/deploying#deploying-cluster-operator-helm-chart-str)

```bash
helm install strimzi-cluster-operator oci://quay.io/strimzi-helm/strimzi-kafka-operator -n strimzi
```

# Kafka Cluster

## Zookeeper Mode

아래와 같이 `Kafka` 리소스를 정의합니다. 3개의 주키퍼와 3개의 브로커로 구성된 클러스터 입니다.

```yaml
# @kafka-zookeeper-cluster.yaml
apiVersion: kafka.strimzi.io/v1beta2
kind: Kafka
metadata:
  name: my-cluster
  namespace: strimzi
spec:
  kafka:
    version: 3.9.0
    replicas: 3
    listeners:
      - name: plain
        port: 9092
        type: internal
        tls: false
    storage:
      type: ephemeral
  zookeeper:
    replicas: 3
    storage:
      type: ephemeral
  entityOperator:
    topicOperator: {}
    userOperator: {}
```

```bash
$ kubectl apply -f kafka-zookeeper-cluster.yaml
```

Strimzi `0.45.0` 버전은 Zookeeper 모드를 지원하는 마지막 Strimzi 버전입니다. 앞으로는 Strimzi에서 KRaft 모드의 카프카 클러스터만 디플로이 할 수 있습니다.

## KRaft Mode

Strimzi는 KRaft 모드로 카프카 클러스터를 디플로이 하는 것도 가능합니다! 다만, 아까 봤던 주키퍼 기반 클러스터랑은 다르게 `KafkaNodePool` 리소스를 추가로 디플로이 해야 합니다.

> To deploy a Kafka cluster in KRaft mode, you must use `Kafka` and `KafkaNodePool` custom resources. The Kafka resource using KRaft mode must also have the annotations `strimzi.io/kraft: enabled` and `strimzi.io/node-pools: enabled`. - [Strimzi](https://strimzi.io/docs/operators/in-development/deploying#assembly-kraft-mode-str)

일단 `Kafka` 클러스터를 정의하고 디플로이 합니다.

```yaml
apiVersion: kafka.strimzi.io/v1beta2
kind: Kafka
metadata:
  name: my-cluster
  namespace: strimzi
  annotations:
    strimzi.io/kraft: enabled
    strimzi.io/node-pools: enabled
spec:
  kafka:
    version: 3.9.0
    listeners:
      - name: plain
        port: 9092
        type: internal
        tls: false
  entityOperator:
    topicOperator: {}
    userOperator: {}
```

주키퍼 때와는 다르게 `spec.kafka.replicas` 항목과 `spec.kafka.storage` 항목이 없어졌습니다!

이서서 `KafkaNodePool`을 정의 합니다. 이때, `strimzi.io/cluster` 레이블이 아까 정의한 `Kafka` 클러스터의 이름과 동일하도록 설정합니다.

```yaml
apiVersion: kafka.strimzi.io/v1beta2
kind: KafkaNodePool
metadata:
  name: controller-nodes
  namespace: strimzi
  labels:
    strimzi.io/cluster: my-cluster  # Kafka 클러스터 이름과 일치해야 함
spec:
  replicas: 3  # 컨트롤러 개수
  roles:
    - controller
  storage:
    type: ephemeral
---
apiVersion: kafka.strimzi.io/v1beta2
kind: KafkaNodePool
metadata:
  name: broker-nodes
  namespace: strimzi
  labels:
    strimzi.io/cluster: my-cluster  # Kafka 클러스터 이름과 일치해야 함
spec:
  replicas: 3  # 브로커 개수
  roles:
    - broker
  storage:
    type: ephemeral
```

그리고 디플로이 하면, 아래와 같이 KRaft 모드의 클러스터가 디플로이 됩니다!!

```bash
NAME                                          READY   STATUS    RESTARTS      AGE
strimzi-cluster-operator-74f9cd5689-v7tcz     1/1     Running   7 (28m ago)   4h40m
my-cluster-controller-nodes-3                 1/1     Running   0             5m39s
my-cluster-broker-nodes-2                     1/1     Running   0             5m39s
my-cluster-controller-nodes-5                 1/1     Running   0             5m39s
my-cluster-controller-nodes-4                 1/1     Running   0             5m39s
my-cluster-broker-nodes-0                     1/1     Running   0             5m39s
my-cluster-broker-nodes-1                     1/1     Running   0             5m39s
my-cluster-entity-operator-7c4b65b7cb-x9gz8   2/2     Running   0             5m16s
```

함께 생성된 `entity-operator`는 Kafka 클러스터에서 사용자(`KafkaUser`)와 토픽(`KafkaTopic`) 리소스를 자동으로 관리하는 컨트롤러 입니다. 요 `entity-operator`를 통해 Kafka의 유저와 토픽을 선언적으로 관리할 수 있습니다.

# Kafka Topic

Strimzi에서는 Kafka Topic을 선언적으로 생성하고 관리할 수 있습니다.

```yaml
apiVersion: kafka.strimzi.io/v1beta2
kind: KafkaTopic
metadata:
  name: my-topic
  labels:
    strimzi.io/cluster: my-cluster
spec:
  partitions: 3
  replicas: 3
  config:
    retention.ms: 7200000
    segment.bytes: 1073741824
```

```bash
$ kubectl get kafkatopic
NAME       CLUSTER      PARTITIONS   REPLICATION FACTOR   READY
my-topic   my-cluster   3            3                    True
```

확인을 위해 Kafka CLI로 클러스터에 접속 해봅시다.

```bash
$ kafka-topics.sh \
  --bootstrap-server my-cluster-kafka-bootstrap.strimzi:9092 \
  --list
my-topic
```

# 맺음말

Strimzi로 카프카 클러스터를 디플로이 하니 zookeeper 디플로이 후에 broker들이 디플로이 되었습니다! bitnami-kafka 때는 주키퍼와 브로커가 동시에 디플로이 되었고, 주키퍼가 초기화 되지 않은 상태에서 브로커가 동작하면서 브로커 retry가 좀 있었는데요. Strimzi에선 그런 현상이 없었습니다!!

Kafka 토픽을 선언적으로 정의할 수 있다는 것은 분명한 장점입니다! Kafka의 모든 요소들을 yaml 파일로 코드화 하고 관리할 수 있다니! 정말 편리할 것 같습니다! 그동안 Confluent에서 웹이나 Confluent SDK로 토픽을 만들거나 관리 했는데요. Strimzi는 "Kafka도 IaC 할 수 있어요!"라는 생각을 일깨워 준 것 같습니다 ㅎㅎ

그리고 카프카 클러스터를 빠르게 디플로이 할 수 있다는 것도 분명한 장점이구요!! 앞으로 카프카 개발자로 살아가면서 Strimzi를 어떻게 더 활용해 볼 수 있을지 이리저리 즐겁게 고민 해봐야 겠습니다!

# References

- [Strimzi Official Examples](https://github.com/strimzi/strimzi-kafka-operator/tree/0.45.0/examples)
