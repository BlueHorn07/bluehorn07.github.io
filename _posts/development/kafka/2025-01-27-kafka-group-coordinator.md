---
title: "Coordinator of Consumer Group"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "컨슈머 그룹이 데이터를 잘 처리할 수 있도록 조율하고 관리하는 코디네이터에 대해. 작은 클러스터처럼 동작하는 컨슈머 그룹을 어떻게 제어할 수 있을까?"
---

회사에서 Confluent를 통해 Kafka 클러스터를 잘 사용하고 있었습니다. 그런데, 이 카프카에 대해서 좀더 자세히 알고, 전문성을 갖추고 싶다는 생각이 들어서 2025년 첫 목표로 Confluent의 Kafka 자격증인 [Confluent Certified Developer for Apache Kafka®](https://training.confluent.io/examdetail/confluent-dev) 자격증을 준비하고 있습니다 ✌️
{: .notice }

# 들어가며

컨슈머 그룹은 여러 컨슈머가 모여서 카프카 토픽의 데이터를 병렬로 처리하기 위해 구성된 컴포넌트 입니다. "컨슈머가 N개니까, 처리량도 N개? 히히"처럼 단순하게 생각할 수 있겠지만, 본질은 컨슈머 그룹이 하나의 "작은 클러스터"가 되어 카프카 브로커와 통신하게 됩니다. 클러스터 처럼 동작하는 컴포넌트이니 이들을 제어하고 관리하는 컨트롤 플레인 역할이 필요 합니다! 그 역할을 해주는 것이 코디네이터(Coordinator)이고, 이번 포스트에서 살펴볼 녀석 입니다.

# 전체적인 구성

![](/images/development/kafka/consumer-group-architecture.png)
[Confluent: Consumer Group Protocol](https://developer.confluent.io/courses/architecture/consumer-group-protocol/)
{: .gray .small .text-center }

큰그림을 먼저 그려봅시다!

- 그룹 코디네이터
  - 브로커에 존재하는 컴포넌트 입니다. 그래서 "server-side"라고 부르기도 합니다.
  - 모든 브로커에는 그룹 코디네티어가 존재합니다만, Consumer Group 별로 담당하는 브로커가 다릅니다. 자세한 내용은 그룹 코디네이터 문단에 기술하였습니다.
  - 컨슈머의 신규 등록 또는 탈퇴, 토픽 파티션의 증가와 같은 상황에서 "컨슈머 리밸런싱"을 트리거 합니다.
- 컨슈머 코디네이터
  - 각 컨슈머에 존재하는 컴포넌트 입니다. 그래서 "client-side"라고 부르기도 합니다.
  - 그룹 코디네이터에게 지속적으로 "하트비트(heartbeat)"를 보내며, 컨슈머가 정상적으로 동작하고 있음을 알립니다.
  - 컨슈머 그룹에 등록되기 위한 `JoinGroupRequest`와 `LeaveGroupRequest`를 그룹 코디네이터에게 보냅니다.
  - 그룹 코디테이너가 할당한 토픽과 파티션의 데이터를 처리할 수 있도록 합니다.
- 컨슈머 그룹 리더
  - 컨슈머 중 하나가 컨슈머들을 조율하는 그룹 리더가 됩니다.
  - 보통 그룹 코디네이터와 가장 먼저 접촉한 컨슈머가 그룹 리더가 됩니다.
  - 그룹 코디네티어가 트리거 한 리밸런싱을 그룹 리더가 수행 합니다.

# 컴포넌트들의 상호작용

각 과정을 따라가며 컨슈머 그룹이 어떻게 코디네이션 되는지 살펴봅시다. Confluent의 "Consumer Group Protocol"라는 영상에서 이를 자세히 설명해주고 있습니다.

## Find Group Coordinator

![](/images/development/kafka/consumer-group-1.png)
[Confluent: Consumer Group Protocol](https://developer.confluent.io/courses/architecture/consumer-group-protocol/)
{: .gray .small .text-center }

컨슈머 어플리케이션은 실행 될 때, 카프카 클러스터에 `FindCoordinator` 요청을 보냅니다. 이때, `group.id`가 실려 요청이 전송되는데, 이 값은 해싱되어 `__consumer_offsets`을 이루는 대응되는 파티션과 매칭 됩니다.

`__consumer_offsets` 토픽은 카프카 클러스터에 연결된 컨슈머 그룹의 정보를 저장하는 시스템 토픽 입니다. 컨슈머 그룹이 카프카 클러스터에 `FindCoordinator`를 요청하면, 클러스터를 구성하는 브로커 중 하나가 그 컨슈머 그룹의 코디네이션을 전담 하게 됩니다.

어떤 브로커가 컨슈머 그룹을 전담할지는 컨슈머 그룹의 `group.id`의 해시값과 관련 있습니다. `group.id`는 해싱 되어서 `__consumer_offsets` 토픽의 파티션 중 하나에 할당 되어 정보가 관리 됩니다. 이때, `group.id`에 대응 되는 파티션의 리더 파티션을 관리하는 브로커가 해당 Consumer Group의 코디네이션을 전담 하게 됩니다.

참고로 `__consumer_offsets` 토픽은 기본값으로 50개의 파티션에 Replication 3으로 구성 됩니다.

각 컨슈머는 `FindCoordinator` 요청의 응답으로 컨슈머 그룹을 관리할 코디네이터가 있는 브로커의 endpoint를 응답으로 돌려 받고, 앞으로 해당 브로커에만 요청을 보냅니다.

## Members Join

![](/images/development/kafka/consumer-group-2.png)
[Confluent: Consumer Group Protocol](https://developer.confluent.io/courses/architecture/consumer-group-protocol/)
{: .gray .small .text-center }

각 컨슈머는 전달받은 endpoint로 `JoinGroup` 요청을 보냅니다. 여기에는 Topic subscription과 관련된 정보가 전달됩니다.

그룹 코디네이터는 `JoinGroup` 요청을 보낸 컨슈머 중 하나를 그룹 리더로 선정하여 응답을 보냅니다. 보통은 제일 먼저 요청을 보낸 컨슈머가 그룹 리더가 됩니다

응답에는 각 컨슈머에 할당된 `memberId` 값이 담겨서 전달됩니다. 그룹 리더에게는 전체 멤버 리스트와 subscription과 관련된 정보가 전달됩니다. 이는 그룹 리더가 실제 파티션 할당과 리밸런싱 작업을 수행하기 때문입니다.


## Partitions Assigned

![](/images/development/kafka/consumer-group-3.png)
[Confluent: Consumer Group Protocol](https://developer.confluent.io/courses/architecture/consumer-group-protocol/)
{: .gray .small .text-center }

그룹 리더는 `JoinGroup` 요청에서 멤버 리스트에 대한 정보를 받습니다. 그룹 리더는 "파티션 할당(Partition Assign)" 계획을 수립 합니다. 할당 정책은 컨슈머 그룹의 `partition.assignment.strategy`에 정의되어 있고, 아래와 같은 옵션들이 있습니다.

- RoundRobinAssignor
- RangeAssignor
- StickyAssignor

파티션 어싸인에 대한 부분은 별도 포스트에서 좀더 다뤄보도록 하겠습니다.

그룹 리더가 파티션 할당을 계획을 수립하면, 브로커에게 `SyncGroup` 요청을 보내어 해당 정보를 전달합니다. 이 정보는 각 컨슈머가 `SyncGroup` 요청을 할 때, 응답으로 전달 됩니다.

## Commit Consumption

![](/images/development/kafka/consumer-group-4.png)
[Confluent: Consumer Group Protocol](https://developer.confluent.io/courses/architecture/consumer-group-protocol/)
{: .gray .small .text-center }

컨슈머 그룹은 할당 받은 파티션의 데이터를 Subscription 하여 처리 한 후, 처리 정보를 기록하기 위해 브로커에 `CommitOffset` 요청을 보냅니다. 그러면, 브로커는 `__consumer_offsets` 토픽에 이 정보를 반영합니다.

## Fetching Offset

![](/images/development/kafka/consumer-group-5.png)
[Confluent: Consumer Group Protocol](https://developer.confluent.io/courses/architecture/consumer-group-protocol/)
{: .gray .small .text-center }

컨슈머 그룹이 재시작하거나 맨처음에 실행되는 경우, 브로커에게서 처리할 Offset 정보를 받아야 합니다. 각 컨슈머는 `OffsetFetch` 요청을 브로커에 보내고, 기존에 처리하던 정보가 있다면 그 정보를, 처리하던 정보가 없다면, 컨슈머의 `auto.offset.reset` 값에 따라 Offset 값을 결정해 응답으로 반환합니다.

# 맺음말

그동안 카프카를 사용하면서, "고성능이고 고가용성을 갖추는 분산 MQ" 정도라고 생각했습니다. 그런데 마음먹고 카프카를 깊게 공부해보니, 위의 특성들을 갖추기 위해 필요한 구현과 컨셉이 한 둘이 아닌 것 같습니다 ㅎㄷㄷ

더 많이 알면, 더 재밌는 것들을 많이 해볼 수 있는법!! ㅎㅎ 예전보다는 현생에서 신경 쓸 것들이 많아서 새로운 걸 바닥까지 익혀볼 시간이 부족했는데, 오랜만에 즐겁게 공부하고 있습니다 🙂

# References

- [Consumer Group Protocol](https://developer.confluent.io/courses/architecture/consumer-group-protocol/)
- [Coordinator in Apache Kafka](https://www.waitingforcode.com/apache-kafka/coordinator-in-apache-kafka/read)
