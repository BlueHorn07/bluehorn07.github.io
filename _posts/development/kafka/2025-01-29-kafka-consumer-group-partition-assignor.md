---
title: "Partition Assignors of Consumer Group"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "컨슈머 그룹이 처리할 파티션을 똑똑하게 할당 하는 방법에 대해. 작은 클러스터처럼 동작하는 컨슈머 그룹을 어떻게 제어할 수 있을까?"
---

회사에서 Confluent를 통해 Kafka 클러스터를 잘 사용하고 있었습니다. 그런데, 이 카프카에 대해서 좀더 자세히 알고, 전문성을 갖추고 싶다는 생각이 들어서 2025년 첫 목표로 Confluent의 Kafka 자격증인 [Confluent Certified Developer for Apache Kafka®](https://training.confluent.io/examdetail/confluent-dev) 자격증을 준비하고 있습니다 ✌️
{: .notice }

# 들어가며

컨슈머 그룹은 여러 컨슈머가 모여서 카프카 토픽의 데이터를 병렬로 처리하기 위해 구성된 컴포넌트 입니다. "컨슈머가 N개니까, 처리량도 N개? 히히"처럼 단순하게 생각할 수 있겠지만, 본질은 컨슈머 그룹이 하나의 "작은 클러스터"가 되어 카프카 브로커와 통신하게 됩니다. 클러스터 처럼 동작하는 컴포넌트이니 이들을 제어하고 관리하는 컨트롤 플레인 역할이 필요 합니다! 지난번 포스트에서 그 역할을 해주는 것이 코디네이터(Coordinator)에 대해 살펴보았습니다: "", 이번 포스트에서는 각 컨슈머에 파티션을 할당하는 "Partition Assignor"에 대해 살펴보겠습니다.

# Range Assignor

![](/images/development/kafka/partition-assignor-1.png)
[Confluent: Consumer Group Protocol](https://developer.confluent.io/courses/architecture/consumer-group-protocol/)
{: .gray .small .text-center }

컨슈머 그룹에서 기본값으로 사용하는 할당 방식 입니다.

각 토픽 별 파티션을 정렬한 후, 컨슈머 그룹에 정렬된 컨슈머 ID 순서대로 할당하는 정책입니다. 이 과정이 토픽 별로 이뤄지기 때문에 초반 컨슈머들은 반드시 파티션을 할당 받지만, 후반 컨슈머를 토픽을 할당 받지 못할 수도 있습니다.

다만, 그림에도 나오듯이 구독하는 토픽이 2개 이상이라면, 컨슈머 그룹을 이루는 초반 컨슈머들이 많은 부하를 받고, 나머지 컨슈머는 idle 상태가 될 수 있습니다. 기껏 컨슈머를 디플로이 했는데, 노는 것들이 있으면 안 되겠죠? 이 문제를 해결하기 위해 제안된 것이 RoundRobinAssignor 입니다!

# RoundRobin Assignor

![](/images/development/kafka/partition-assignor-2.png)
[Confluent: Consumer Group Protocol](https://developer.confluent.io/courses/architecture/consumer-group-protocol/)
{: .gray .small .text-center }

Range Assignor의 경우, 초반 컨슈머가 많은 파티션이 할당되어 핫스팟이 발생할 수 있었습니다. RoundRobin Assignor는 이를 개선하고자 나온 할당 방식입니다.

RoundRobin에서는 구독하는 모든 토픽와 토픽의 파티션을 하나로 모아서 할당을 진행합니다. 위의 사진의 경우 `[customers-P0, customers-P1, orders-P0, orders-P1]`처럼 모든 토픽-파티션 쌍을 모은 후, 이를 각 컨슈머를 순회하며 할당 합니다.

만약 컨슈머의 갯수가 토픽-파티션의 쌍보다 적다면, 모든 토픽이 적어도 하나 이상의 파티션을 할당 받아 처리하는 것을 보장 합니다. 이것은 idle 컨슈머가 없다는 것을 말합니다.

반면에, 컨슈머 갯수가 토픽-파티션 쌍보다 많다면, idle 컨슈머가 생기게 됩니다. 이는 이후에 나올 개선된 할당 정책에서도 마찬가지로 발생합니다.

# Rebalancing

컨슈머의 신규 등록 또는 탈퇴, 토픽 파티션의 증가 하는 상황이 발생하면 브로커는 "리밸런싱"을 트리거 합니다.

`JoinGroup`, `SyncGroup`과 같은 예시를 보면, 컨슈머 쪽에서 브로커에게 Request를 보냈는데요. 브로커는 "리밸런싱"을 어떻게 트리거 하는 것일까요?

리밸런싱이 필요한 상황이 발생하면, 브로커는 컨슈머 요청에 대한 응답으로 `REBALANCE_IN_PROGRESS`를 보냅니다. 컨슈머는 브로커에게 주기적으로 하트비트(`Heartbeat`) 요청을 보내는데, 이 응답으로 리밸런싱이 필요하다고 보내는 것이죠.

`REBALANCE_IN_PROGRESS` 응답을 받은 컨슈머는 현재 할당된 모든 파티션을 해제합니다. 그리고 브로커에게 다시 `JoinGroup` 요청을 전송합니다.

컨슈머 쪽에서는 리밸런싱 해야 한다는 응답을 받으면, `onPartitionsRevoked` 콜백을 실행하고, `SyncRequest`의 응답으로 파티션을 할당 받으면 `onPartitionsAssigned` 콜백을 실행합니다.

## Stop-the-world Rebalance

리밸런싱은 컨슈머 그룹의 구독 상태를 완전히 바꿀 수 있기 때문에 아주아주 비싼 작업이고, 이로 인해서 컨슈머 그룹의 처리가 크게 지연되는 경우도 있습니다. 짧게는 수 초 안에 완료 될 수 있지만, 길면 몇 분 정도 기다려야 할 수도 있다고 합니다.

![](/images/development/kafka/partition-assignor-3.png)
[Confluent: Consumer Group Protocol](https://developer.confluent.io/courses/architecture/consumer-group-protocol/)
{: .gray .small .text-center }

기존의 리밸런싱 과정은 파티션 할당을 바꾸기 전에 모든 컨슈머가 할당 받은 파티션을 Revoke 하도록 합니다. 이로 인해 컨슈머 그룹의 데이터 처리가 일시 중단 된다는 문제가 있었습니다.

그리고 리밸런싱 전후로 같은 파티션을 할당 받는 컨슈머가 있더라도 그들이 기존에 가지고 있던 파티션이 아닌 다른 파티션을 할당 받게 될 가능성이 있었습니다.

## Sticky Assignor

![](/images/development/kafka/partition-assignor-4.png)
[Confluent: Consumer Group Protocol](https://developer.confluent.io/courses/architecture/consumer-group-protocol/)
{: .gray .small .text-center }

Sticky Assignor는 리밸런싱 이후에 컨슈머가 자신이 처리하던 파티션을 최대한 그대로 유지하도록 할당하는 방식입니다.

Round Robin Assignor는 "**각 컨슈머가 처리하는 파티션의 갯수는 모두 같거나 최대 하나 정도만 차이나게 한다.**"라는 특징을 갖는데, 이를 활용해 파티션 할당을 조정 합니다. 이 명제를 바탕으로 재할당 과정에서 기존 컨슈머와 신규 컨슈머의 파티션 차이가 2개 이상 차이가 나면 재할당 대상이 됩니다. 만약, 둘 이상 차이 나지 않는다면, 그 컨슈머의 파티션 할당은 건드리지 않습니다.

위의 캡쳐와 함께 살펴봅시다. 상황은 `Consumer 3`가 추가 된 상황입니다. 이때, 컨슈머가 처리하는 파티션 갯수가 최대 하나만 차이 나야 하는데, `Consumer 1`과 `Consumer 3`이 2개와 0개로 두 개 차이가 납니다. 반면에 `Consumer 2`와는 1개와 0개로 하나만 차이 나므로 파티션 조정의 대상이 되지 않습니다. 그래서 `Consumer 1`에서 처리하던 파티션 중 하나인 `p2`를 해제하고 이를 `Consumer 3`에 할당 하도록 합니다.

<br/>

하지만, Sticky Assignor도 Stop-the-world 문제는 그대로 가지고 있습니다. 리밸런싱이 이뤄지는 과정에서 컨슈머 그룹의 처리가 중단되는 것은 피할 수 없습니다.

## Cooperative Sticky Assignor

리밸런싱 과정에서 컨슈머 그룹 전체의 처리가 중단되는 문제를 해결하고자 개발된 것이 Cooperative Sticky Assignor 입니다.

앞에서 살펴본 Assignor들은 `JoinGroup`-`SyncGroup` 요청을 한번만 수행하는데, Cooperative Sticky Assignor는 `JoinGroup`-`SyncGroup` 요청을 2번 수행합니다!

![](/images/development/kafka/partition-assignor-5.png)
[Confluent: Consumer Group Protocol](https://developer.confluent.io/courses/architecture/consumer-group-protocol/)
{: .gray .small .text-center }

첫번째 과정에서는 현재 `JoinGroup`으로 멤버 현황만 파악합니다. 그리고 `SyncGroup`에서는 리밸런싱 결과로 할당 해제할 파티션에 대해서만 revoke 합니다. 이때, revoke 대상이 되지 않은 나머지 파티션의 처리를 계속 이어집니다!

![](/images/development/kafka/partition-assignor-6.png)
[Confluent: Consumer Group Protocol](https://developer.confluent.io/courses/architecture/consumer-group-protocol/)
{: .gray .small .text-center }

두번재 과정에서는 한번더 `JoinGroup`과 `SyncGroup`을 수행합니다. 이때, `SyncGroup`의 응답으로 revoke 했던 파티션을 다른 컨슈머가 받아서 처리를 진행합니다.

기존에는 revoke와 재할당이 한번에 이뤄져서 전체 revoke 후에 재할당을 진행하였습니다. 그런데 Cooperative Sticky Assignor에서는 revoke와 재할당 과정을 two-step으로 분리하여 stop-the-world 문제를 해결하였습니다!!


# 맺음말

카프카를 처음 공부할 때, 요 파티션 할당 정책을 공부했던 기억이 있는데, 그때는 컨슈머 그룹이 토픽 하나만을 subscribe 할 때를 생각했던 것 같습니다. 2년 동안 카프카에 대한 경험치가 쌓였고, 요 파티션 할당 정책을 제대로 이해하려면 컨슈머 그룹이 토픽을 2개 이상 subscribe 할 때를 기준으로 이해해야 한다는 걸 깨닫게 되었습니다.

앞으로 회사에서 더 많은 스트리밍 ETL과 스트리밍 Application을 개발해보고 싶습니다. 지금 공부하는 것들이 언젠가 도움이 되기를 기대하며 ㅎㅎ 오늘도 파잇힝!
