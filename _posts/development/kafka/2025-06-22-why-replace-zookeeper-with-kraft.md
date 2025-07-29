---
title: "Why Replace Zookeeper with KRaft"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "주키퍼 대신 KRaft를 도입하게 된 이유에 대해서."
robots: noindex
sitemap: false
---

회사에서 Confluent를 통해 Kafka 클러스터를 잘 사용하고 있었습니다. 그런데, 이 카프카에 대해서 좀더 자세히 알고, 전문성을 갖추고 싶다는 생각이 들어서 2025년 목표로 Confluent의 Kafka 자격증인 [Confluent Certified Developer for Apache Kafka®](https://training.confluent.io/examdetail/confluent-dev) 자격증을 준비하고 있습니다 ✌️ 전체 포스트는 [여기](/categories/kafka)에서 확인할 수 있습니다.
{: .notice }

# 들어가며

CCDAK 시험을 준비하면서, Confluent에서 발행한 각종 문서들을 읽어보고 있습니다. 이번 포스트에선

"[Why ZooKeeper Was Replaced with KRaft – The Log of All Logs](https://www.confluent.io/blog/why-replace-zookeeper-with-kafka-raft-the-log-of-all-logs/)"

라는 아티클을 읽으면서, 메모한 것들을 포스트로 기록 합니다. 참고로 이 포스트는 개인적인 메모이므로 인터넷에서 검색되지 않습니다.

# Introduction

왜 Zookeeper에서 KRaft로 바꾸었는가?

왜 Raft 알고리즘을 채택 했는가?

2012년, kafka controller에 기능 추가?
이 controller는 topic partition log 뿐만 아니라, cluster/broker metadata와 cluster-wide config, security credential 등을 모두 저장하도록 디자인 하고 있었다?

zookeeper는 SOT로 사용 했음.

그런데, non-controller broker도 zookeeper에 접속해 ISR 정보나 리더 정보를 업데이트할 수 있었음. 그럼 controller는 ZK에서 그 정보를 받아야 했음.

그래서 원래는 Controller가 ZK를 SOT로 사용하면서 클러스터 관리를 하려고 했는데, 이렇게 하면 ZK와 소통하는 모든 컴포넌트가 controller를 거치지 않고 클러스터 정보를 업뎃하는 일이 일어나게 됨.

초창기 카프카는 컨슈머도 직접 ZK에 통신할 수 있었음. 그런데, 카프카가 발전하면서 이걸 막고 컨슈머는 브로커를 통해 간접적으로 리더 정보를 업뎃하도록 변경 되어 왔음.

이렇게 ZK 직접 접근을 막는 이유는 ZK에 대한 R/W 부하를 줄이기 위해서 였음.

## Zookeeper watcher

Q. "ZooKeeper watcher" 이게 뭐지? 이게 곧 Controller 브로커 아님?

"watcher"는 ZK 시스템 내에서의 개념임. 구체적으로는 ZK의 "event notification" 메커니즘임.

ZK watcher는 Znode(데이터 노드)에 변화를 감지하고 ZK 클라이언트가 알림을 받을 수 있는 메커니즘 입니다. 카프카에서는 ZK 클라이언트는 "**브로커**" 입니다.

Znode에 변화는 (1) 브로커가 클러스터에 join하거나 leave 할 때, (2) 토픽 메타데이터가 변경될 때, (3) 컨트롤러 브로커가 변경될 때 입니다. 이런 변경 사항은 대부분 컨트롤러 브로커가 만들게 됩니다.

카프카 브로커는 Znode에 변화를 감지(watch)하고, 변화가 있으면 그 이벤트를 확인해 대응 합니다.

# Controller Scalability Limitation

또, 현재는 하나의 Controller 브로커가 ZK에 접속하도록 하면서, 클러스터 대규모로 확장되면 해당 Controller 브로커가 큰 부하를 받게 됨.

## Broker Shutdown

non-controller 브로커 하나를 내리게 되면, 이 브로커를 내리기 위해서 broker->controller->ZK 일련의 과정이 일어나야 하고, 반대로 ZK->controller->(other) borkers로 전파가 일어아냐 함. 그런데, 이 과정은 꽤 긴 시간이 걸리고 클러스터의 즉각적인 다운을 어렵게 만들었음.

## Controller Failover

컨트롤러 브로커도 셧다운 될 수 있음.

이 상황이 발생하면, 나머지 모든 브로커가 ZK에 핑을 보내고, 그 중에서 가장 먼저 응답을 받는 녀석이 Controller 브로커로 임명 됨.

Controller가 된 브로커가 가장 먼저 하는 일은 ZK에서 메타데이터를 가져오는 것임.
만약 기존 정보와 불일치 하는 정보가 있다면, 그걸 핸들링 하고(=신규 메타 정보로 overwrite), 신규 정보를 하위 브로커에 전파함.

이 과정에서의 병목은 신규로 임명된 controller가 ZK에서 메타데이터를 fetching 하는 것임. 이 과정은 topic partition이 많을수록 오래걸림. 이런 bootstrapping 과정 동안은 컨트롤러 브로커가 어떤 요청도 처리할 수 없기 때문에 클러스터의 일부 기능이 제대로 동작하지 않게 됨.

# Why choose KRaft?

ZK에 저장하는 정보가 뭔지 잘 생각해보니, 결국은 메타데이터가 어떻게 바뀌는지에 대한 transaction log 였음.

그리고 이런 log 데이터를 저장하는데 가장 좋은 녀석이 바로 카프카 자신임!! ㅋㅋ

그래서 이 metadata log를 저장하는 내부 토픽을 두고, controller가 이 정보를 "직접" 관리하도록 하는게 어떨까 하는 생각으로 이어짐.

그래서 몇개의 브로커를 그룹으로 묶어서 이 metadata log를 관리하도록 역할을 부여함. 그리고 이 그룹은 quorum을 이룸. (이전에는 하나의 컨트롤러 였는데, 이젠 quorum으로 운영!)


"Primary-backup" replication algorithm
- single leader replica takes all of the incoming writes
- and tries to replicate them to other replicas as its followers.
- after the followers have acks, the leader considers it committed and returns to writing to its client.
^the metadata log also takes this procedure also!

"Quorum" replication algorithm
- still a single leader trying to take writes, and replicating to followers.
- But, instead of waiting for all followers to ack
- it only waits for the majority of replicas, including itself.
^ 일종의 분산 환경에서 정보 저장을 위해 사용하는 consuensus algorithm 임.

primary-backup과 비교해서 quorum 방식은 좀더 빠른 availability guaratee를 제공함.

# KRaft

Kafka Raft Implementation

새로운 컨트롤러 그룹은 metadata log에 대해서 이 quorum replication 방식을 채택 함.


그런데, 이것은 metadata log에 대해서 ZK의 역할을 가져온 것임.
기존에 ZK에서 담당하던 리더 컨트롤러 선출 알고리즘에 대해서서도 이관이 필요함.
이 선출 알고리즘은 ZK에 보장하던, 동시에 여러 리더가 존재하는 상황을 방지할 수 있어야 함.

"gridlock scenario" = 교착상태
- 브로커가 특정 조건에서 서로 대기하거나 충돌하여 아무것도 진행하지 못하는 상태임.

deadlock과 비슷한 개념이긴 한데, gridlock은 분산 시스템의 가용 상태에 대해 말할 때 쓰는 듯?

# Leader Election

브로커는 3가지 역할로 나뉨.

- voter
  - 컨트롤러 그룹에 속하는 브로커
- leader candidate
  - 랜덤하게 일정 시간이 지나면 voter 중 하나가 리더 후보가 됨.
- observer
  - 컨트롤러 그룹에 속하지 않는 일반 브로커

만약 어떤 후보도 다수결을 얻지 못했다면, 투표를 무효로 하고 epoch을 늘린 후, 다시 선거를 진행함.

# Log Replication

기존 Raft 알고리즘은 push-based로 log replication을 수행함.
KRaft 알고리즘은 ZK 때처럼 pull-based로 log replication을 수행함.

> we can see that this fetch request is also leveraged as a heartbeat to determine the liveness of the leader.

Other voters within the quorum actively replicate the metadata log so that newly appended records get committed.

# Quorum Controller

The quorum controller receives the linveness of all registered brokers with heartbeats.

When an existing broker is shutting down, it can piggyback its intention within a heartbeat and the controller can remove it from all of its partitions.

Quorum controller batch all of the partition movement events when appending them to the metadata log.

# 맺음말

KRaft 모드에 대한 역사와 동작 방식 그리고 실험 결과까지 아주 알찬 포스트 였습니다! 주말에 여유롭게 카페에서 읽는데 술술 읽히더라구요 ㅎㅎ

Raft가 push-based 였다면, KRaft는 pull-based로 동작한다는 것도 이 포스트를 읽으면서 처음 알았네요 ^^

아티클에 있던 표현 중에 `“not reinventing the wheel”`가 인상적 입니다. 카프카 개발자들이 기존 코드와 시스템을 최대한 활용하는 방향을 선택했다는 것이 참 똑똑한 것 같습니다.

`“not reinventing the wheel”` 정책은 대규모 오픈소스를 운영하고 관리하는 데에 좋은 길잡이가 되어줄 것 같습니다. 물론 어떤 순간에는 완전히 새로운 도구를 개발해야 할 수도 있겠지만, 그것이 바퀴(wheel)가 아닌 다른 것(예를 들면, 바퀴를 활용하는 자전거라는 시스템?)인지 빠르게 판단하고 개발 방향을 정할 수 있어야 하는 것 같습니다.
