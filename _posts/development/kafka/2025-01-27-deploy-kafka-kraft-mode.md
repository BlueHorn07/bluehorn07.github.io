---
title: "Deploy Kafka KRaft Mode"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "주키퍼 의존성을 제거한 카프카 클러스터! 강건한 분산 시스템을 만들기 위한 고민들을 살펴봅시다! 🏄"
---

# 들어가며

회사에서 Confluent를 통해 Kafka 클러스터를 잘 사용하고 있었습니다. 그런데, 이 카프카에 대해서 좀더 자세히 알고, 전문성을 갖추고 싶다는 생각이 들어서 2025년 첫 목표로 Confluent의 Kafka 자격증인 [Confluent Certified Developer for Apache Kafka®](https://training.confluent.io/examdetail/confluent-dev) 자격증을 준비하고 있습니다 ✌️

# 주키퍼

![](/images/development/kafka/zookeper-mode.png)
[Confluent: The Apache Kafka Control Plane](https://developer.confluent.io/courses/architecture/control-plane/)
{: .gray .small .text-center }

사실 주키퍼는 Kafka를 위해 만들어진 도구가 아닙니다. 주키퍼는 분산처리 환경의 컨트롤 플레인 역할을 할 수 있는 컴포넌트 입니다. 이것은 Kafka와 주키퍼가 서로 독자적인 생태계를 가지고 있다는 뜻이며, Kafka 클러스터를 운영하는 사람은 결국 주키퍼라는 별도 생태계까지 공부하고 경험을 쌓아야 한다는 것을 말합니다.


## 주키퍼 기반으로 동작하던 카프카가 갖던 문제란 무엇인가?

주키퍼는 카프카 클러스터의 메타 데이터 정보(ex: ISR과 리더 파티션 정보)를 가지고 있고, 이를 코디테이션을 위해 사용합니다. 컨트롤러 브로커는 메타정보를 개별 브로커에 전파합니다. 즉, 플로우가

```
주키퍼 -> 컨트롤러 브로커 -> 일반 브로커
```

순서로 여러 절차가 필요 했습니다.

이런 상황에서 컨트롤러 브로커와 일반 브로커 사이에 네트워크 장애가 발생하여 서로 통신하지 못하게 된다면, 어떤 토픽에 대해서 리더 일시적으로 2개가 되는 현상이 발생한다고 합니다.

마찬가지로 주키퍼 쪽에서는 리더 파티션이 변경 되었는데, 주키퍼와 컨트롤러 브로커 사이 연결이 지연되는 경우도 문제가 발생할 수 있습니다.

카프카 개발팀은 이것이 절차가 많기 때문이라고 생각했고, 이를 해결하기 위해... 주키퍼 컴포넌트를 없애서 절차를 간소화 하고자 합니다!


# Kraft 모드

Kraft 모드에서는 "컨트롤러가 여러대" 있음. 그리고 "컨트롤러 중 한 대가 리더"가 됨. 컨트롤러와 리더는 기존 브로커처럼 데이터를 적재하거나 처리 하지 않습니다. 이들은 오직 클러스터 메타데이터와 모든 브로커의 상태 정보를 관리합니다. 만약 원한다면, 컨트롤러가 브로커 역할까지 겸하는 `Combined` 모드로 운영하는 것도 가능하다고 합니다. (다만, Combined 모드는 운영 환경에서는 권장되지 않습니다.)

기존에 컨트롤러 노드가 하던 "리더 파티션 선출" 기능은 컨트롤러 리더 노드가 수행합니다. 나머지 컨트롤러 팔로워 노드는 선출된 내용을 리더부터 복제하여 저장합니다.

평소의 컨트롤러 팔로워 노드는 메타데이터 분산 저장 외에 딱히 역할이 없습니다. 추가로 리더 노드가 잘 살아있나?를 지속적으로 확인합니다.

팔로워 노드는 리더 노드에 장애가 발생해 리더 노드 선출이 필요할 때 역할을 수행 합니다. 팔로워 노드가 입후보가 되어 선거를 치르게 됩니다. 이때, 일반 브로커는 투표 과정에 입후보 할 수도 없고, 투표권을 행사할 수도 없습니다. 즉, 일반 브로커는 데이터를 송수신하고 저장하는 역할만 집중합니다.

컨트롤러가 후보자로 전활 될 때는 미리 설정된 랜덤 Timeout 값에 의해 전환 됩니다. 각 컨트롤러마다 부여받은 Timeout 값이 제각각이기 때문에 투표할 후보자가 2명이 되는 일은 아주 드뭅니다. 그래도 만약, 후보가 2명 생겼다면, Kraft에서는 더 최신 메타데이터 로그를 가지고 있던 쪽이 투표를 받도록 강제 합니다. 만약, 그럼에도 불구하고 동률이 나온다면, 새로운 랜덤 Timeout을 다시 부여 하고, 다음 선거가 열리기를 기다립니다.

## Deploy on Kubernetes

이전의 [Deploy Kafka on Kubernetes](/2025/01/05/deploy-kafka-on-k8s/)에서 했던 것처럼 bitnami-kafka Helm Chart를 사용합니다. 먼저, 아래와 같이 `helmfile.bitnami-kafka-kraft.yaml`을 준비합니다.

```yaml
# @helmfile.bitnami-kafka-kraft.yaml
repositories:
  - name: bitnami
    url: https://charts.bitnami.com/bitnami

releases:
  - name: bitnami-kafka
    namespace: kafka
    chart: bitnami/kafka
    version: 31.1.1
    values:
      - ./values.bitnami-kafka-kraft.yaml
```

본래 bitnami-kafka는 Kraft 모드로 카프카 클러스터를 구성합니다만, 저희는 약간 커스텀을 해보도록 하겠습니다. 그래서 `values.bitnami-kafka-kraft.yaml` 파일을 만들고 아래와 같이 작성합니다.

```yaml
# @values.bitnami-kafka-kraft.yaml
zookeeper:
  enabled: false

kraft:
  enabled: true

broker:
  replicaCount: 3

controller:
  replicaCount: 3

listeners:
  client:
    protocol: 'PLAINTEXT'
  controller:
    protocol: 'PLAINTEXT'
```

```bash
$ kgp
NAME                         READY   STATUS    RESTARTS      AGE
bitnami-kafka-controller-1   1/1     Running   0             113s
bitnami-kafka-controller-2   1/1     Running   0             113s
bitnami-kafka-controller-0   1/1     Running   0             113s
bitnami-kafka-broker-1       1/1     Running   0             113s
bitnami-kafka-broker-2       1/1     Running   0             113s
bitnami-kafka-broker-0       1/1     Running   0             113s
```

3대의 컨트롤러와 3대의 일반 브로커로 카프카 클러스터가 구성 되었습니다! 신기한 점은 Zookeeper 클러스터가 없다는 점이죠! (대신 Controller들이 생겼으니 리소스는 비슷하게 쓰려나요? 🤔)

## Check Kraft Metadata

Kraft 클러스터의 현재 정보를 확인해봅시다.

```bash
$ kafka-metadata-quorum.sh \
  --bootstrap-server bitnami-kafka:9092 \
  describe --status
ClusterId:              uCTcACi4VDHJQ1KNvNxjrb
LeaderId:               0
LeaderEpoch:            5
HighWatermark:          718
MaxFollowerLag:         0
MaxFollowerLagTimeMs:   0
CurrentVoters:          [{"id": 0, "directoryId": null, "endpoints": ["CONTROLLER://bitnami-kafka-controller-0.bitnami-kafka-controller-headless.kafka.svc.cluster.local:9093"]}, {"id": 1, "directoryId": null, "endpoints": ["CONTROLLER://bitnami-kafka-controller-1.bitnami-kafka-controller-headless.kafka.svc.cluster.local:9093"]}, {"id": 2, "directoryId": null, "endpoints": ["CONTROLLER://bitnami-kafka-controller-2.bitnami-kafka-controller-headless.kafka.svc.cluster.local:9093"]}]
CurrentObservers:       [{"id": 102, "directoryId": "Hw2L-tv6Xs9Ho7qENmW71g"}, {"id": 100, "directoryId": "4ES0SAcYLzFQVJ2diLkQ6A"}, {"id": 101, "directoryId": "GMd5xaITd4TWXvhvAJ_VEQ"}]
```

현재 누가 리더 노드인지, 지금까지 몇 번의 선거가 있었는지(Leader Epoch), Kraft가 몇개의 컨트롤러로 구성 되어 있는지 그리고 Kraft를 이루는 일반 브로커들인 Observer는 얼마나 되는지를 확인할 수 있었습니다.

이 상태에서 현재 리더 노드인 0번 컨트롤러를 `kubectl delete`로 잠시 다운 시켜 보겠습니다. 그리고 다시 `kafka-metadata-quorum.sh`로 확인하면

```bash
ClusterId:              uCTcACi4VDHJQ1KNvNxjrb
LeaderId:               1
LeaderEpoch:            6
HighWatermark:          1130
MaxFollowerLag:         0
MaxFollowerLagTimeMs:   28
CurrentVoters:          [{"id": 0, "directoryId": null, "endpoints": ["CONTROLLER://bitnami-kafka-controller-0.bitnami-kafka-controller-headless.kafka.svc.cluster.local:9093"]}, {"id": 1, "directoryId": null, "endpoints": ["CONTROLLER://bitnami-kafka-controller-1.bitnami-kafka-controller-headless.kafka.svc.cluster.local:9093"]}, {"id": 2, "directoryId": null, "endpoints": ["CONTROLLER://bitnami-kafka-controller-2.bitnami-kafka-controller-headless.kafka.svc.cluster.local:9093"]}]
CurrentObservers:       [{"id": 102, "directoryId": "Hw2L-tv6Xs9Ho7qENmW71g"}, {"id": 100, "directoryId": "4ES0SAcYLzFQVJ2diLkQ6A"}, {"id": 101, "directoryId": "GMd5xaITd4TWXvhvAJ_VEQ"}]
```

1번 컨트롤러가 새로운 리더 노드로 선출되었고, Leader Epoch가 `5`에서 `6`으로 하나 증가하였습니다!

## `__cluster_metadata` Topic

![](/images/development/kafka/kraft-cluster-metadata-topic.png)
[Confluent: The Apache Kafka Control Plane](https://developer.confluent.io/courses/architecture/control-plane/)
{: .gray .small .text-center }

Kraft 모드에서는 클러스터 메타 정보를 모든 브로커들이 분산 저장 합니다. `__cluster_metadata`라는 토픽에 클러트서 정보가 업데이트 되며, 이 토픽은 단일 Partition으로 구성 됩니다.

주키퍼 모드에서는 각 브로커가 클러스터 메타정보를 동기화 하기 위해 주키퍼에 주기적으로 fetch 요청을 보내야 했습니다. 이것이 이제는 본인이 팔로워 파티션으로 가지고 있는 `__cluster_metadata` 토픽에서 가져오는 것으로 변경된 것입니다. 클러스터 변경 사항을 `__cluster_metadata`에 반영하는 것은 리더 노드가 배타적으로 관리하고, 이 정보는 Kafka의 리더-팔로워 파티션 사이의 복제 메커니즘에 따라 전파 됩니다. 즉, Kafka-native 하게 클러스터 메타 정보를 관리하는 것이죠!! 😲

## Snapshot

![](/images/development/kafka/kraft-cluster-snapshot.png)
[Confluent: The Apache Kafka Control Plane](https://developer.confluent.io/courses/architecture/control-plane/)
{: .gray .small .text-center }

각 브로커는 `__cluster_metadata`의 내용을 압축해 `.snapshot` 파일로 만들어 보관 합니다. 그리고 `__cluster_metadata`의 용량을 Truncate로 감축 합니다.

이렇게 `.snapshot`을 만드는 이유는 빠른 복구를 위해서 입니다! 만약, 브로커가 유실 되었고 복구가 필요한 상황이라고 해봅시다. `__cluster_metadata`에 있는 로그를 처음부터 보낸다면 브로커가 복구 되는데 오랜 시간이 걸릴 것 입니다. 빠른 복구를 위해 압축된 정보인 `.snapshot`을 대신 보냅니다. 이를 통해 브로커 복구 시간을 획기적으로 낮출 수 있습니다!


## 주키퍼도 투표를 합니다.

Kraft 모드를 처음 배울 때, 오해했던 점은 Raft 프로토콜를 채택하는게, Kraft 모드의 목표인 줄 알았습니다. 아무래도 이름에 "Raft"라고 떡하니 있으니 그렇게 오해 했나봅니다;; 글을 적으면서 돌아보면, Kraft가 도입된 것은 더 간편한 카프카 클러스터 운영과 복구가 핵심이었습니다.

주키퍼에도 Kraft처럼 Quorum(정족수)가 있고, 리더와 팔로워가 있습니다. 리더가 클라이언트 메타데이터를 처리하고, 팔로워는 이 데이터를 복제 해뒀다가, 리더 주키퍼 노드에 장애가 발생하면, 과반수 투표를 통해 새로운 리더를 선출 합니다. Raft 프로토콜과 아주 유사하게 돌아가죠. 그러나 이런 투표 방식이 Raft 프로토콜을 따른다고 하지는 않습니다. ZAB(Zookeeper Atomic Broadcast)라는 자체적인 투표&합의 프로토콜을 사용합니다. 아마 컨셉이 비슷하고 세부 사항에서 조금 다른게 있나봅니다.


# 맺음말

그동안 데이터 엔지니어로 일을 하면서, 여러 분산 처리 시스템을 접해보았습니다.

- Kubernetes
  - 컨트롤 플레인과 워커 노드
  - CPU와 Memory 등등의 자원을 Pod에 어떻게 분배할지에 대해
  - 그리고 네트워크나 Topology나 등등등
- Istio
  - 서비스 메쉬에 대해
  - 분산 시스템에서의 트래픽 관리를 어떻게 할지에 대한 솔루션
- Spark
  - 드라이버 노드와 워커 노드가 대용량 데이터를 어떻게 처리할지에 대해
  - Map-Reduce의 구현체
- ElasticSearch
  - 빠른 검색을 위한 분산 시스템
- Kafka
  - 강건한 분산 로깅 시스템을 구축하기 위한 솔루션

컨트롤 플레인 장애를 어떻게 빠르게 복구할까에 대한 고민과 해결 방법을 Kafka를 공부하면서 제일 많이 접하게 된 것 같습니다! 그만큼 Kafka가 Fault Tolerance에 강건하려는 철학을 가지고 있는 것 이겠죠 ㅎㅎ

이것저것 많이 배우면, 이도저도 안 되는게 아닌가 싶은 생각도 있지만... 데이터 엔지니어라는 분야가 두루두루 알면 좋은 점이 많은 것 같습니다.
목적을 위해 필요한 컴포넌트를 선정해 바로바로 사용하는 느낌이지만, 배경지식이 얼마나 되느냐에 따라서 프로젝트와 "운영"의 "디테일"의 수준이 결정 되는 것 같습니다.
공부를 하면서 '만약 내가 이런 분산 시스템을 처음부터 만들어야 한다면, 어떻게 만들까?'라는 상상도 많이 하게 되구요 ㅎㅎ

암튼 주저리주저리 였구요! 올해도 새로운 도전을 위해 파잇힝! 해봅시다!!

# References

- [Confluent: The Apache Kafka Control Plane](https://developer.confluent.io/courses/architecture/control-plane/)
- [분산 시스템의 내결함성을 높이는 뗏목 합의 알고리즘(Raft Consensus Algorithm)과 정족수(Quorum) 개념 알아보기](https://seongjin.me/raft-consensus-algorithm/)
