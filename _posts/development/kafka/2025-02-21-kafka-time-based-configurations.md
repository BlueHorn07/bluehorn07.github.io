---
title: "Kafka 시간 기반으로 동작하는 Config 모음"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "째깍째깍⏰ 타이머를 가지고 바쁘게 돌아가는 카프카의 기능들에 대해서"
---

회사에서 Confluent를 통해 Kafka 클러스터를 잘 사용하고 있었습니다. 그런데, 이 카프카에 대해서 좀더 자세히 알고, 전문성을 갖추고 싶다는 생각이 들어서 2025년 첫 목표로 Confluent의 Kafka 자격증인 [Confluent Certified Developer for Apache Kafka®](https://training.confluent.io/examdetail/confluent-dev) 자격증을 준비하고 있습니다 ✌️
{: .notice }

# 들어가며

CCDAK 시험을 준비하면서 카프카의 각종 컴포넌트에 있는 시간 기반으로 동작하는 기능들과 그것의 Configuration들을 많이 보았습니다. 너무 많아서 헷갈릴 정도인데요. 이번 포스트에서 그 Configuration들을 한번에 정리해보고자 합니다.

# Kafka Producer

Confluent의 "[Kafka Producer Configuration Reference for Confluent Platform](https://docs.confluent.io/platform/current/installation/configuration/producer-configs.html)" 문서를 참고해 작성하였습니다.

## `linger.ms`

배치 처리를 위해 프로듀서의 Accumulator에 레코드가 쌓이는 시간 입니다.

프로듀서의 처리 성능을 위해 아주 중요한 값인데, 기본값이 "**0**" 입니다. 그래서 프로듀서를 구성할 때 반드시 직접 세팅 해줘야 합니다.

## `request.timeout.ms`

프로듀서가 브로커에 레코드를 보낸 후에 응답이 돌아올 때까지 기다리는 시간 입니다. 기본값은 "**30초**" 입니다.

만약 이 시간을 초과 한다면, 프로듀서는 레코드를 re-send 하게 됩니다.

이 값을 `replica.lag.time.max.ms`의 값보다는 크게 설정해줘야 레코드 복제 과정 중에 프로듀서가 시도 하는 것을 방지할 수 있다고 합니다.

## `delivery.timeout.ms`

프로듀서는 레코드를 보냈지만, 실패하게 되었을 때 re-send를 하게 됩니다. 다만, 두 가지 조건에 의해 해당 레코드의 전송이 완전히 실패했다고 결론 내리는데

- `retries`에 설정한 횟수를 모두 소진 했거나
- 재시도의 누적 시간이 `delivery.timeout.ms` 시간을 초과 했거나

그러면 재시도는 중단되고 해당 레코드는 완전히 버려지게 됩니다.

기본값은 "**2분**" 입니다. `delivery.timeout.ms`는 "`linger.ms` + `request.timeout.ms`" 보다 크게 잡는 것을 권장 합니다.

## `retry.backoff.ms` & `retry.backoff.max.ms`

전송에 실패한 레코드를 재전송 하기 전에 잠시 휴식 합니다. 얼만큼 쉴지는 Exponential하게 증가합니다. `retry.backoff.ms`는 초기 휴식 시간을 지정 하는 것으로 기본값은 "**100 밀리초**" 입니다.

`retry.backoff.max.ms`는 Exponential 하게 증가하는 휴식 시간의 상한치 입니다. 이 값 이상으로는 재시도가 휴식하지 않도록 합니다. 기본값은 "**1초**" 입니다.

## `transaction.timeout.ms`

트랜잭션 프로듀서에서 트랜잭션의 open 상태가 얼마나 유지 될지에 대한 시간 입니다. 이 시간이 지나도록 트랜잭션이 완료되지 않으면 카프카는 자동으로 그 트랜잭션을 중단(abort) 시킵니다. 기본값은 "**1분**" 입니다.

## `metadata.max.age.ms`

주기적으로 강제 메타데이터 갱신을 수행하는 시간 간격 입니다. 기본값은 "**5분**" 입니다. 이 강제 갱신은 파티션의 리더쉽에 변화와 브로커 추가/삭제, 파티션 추가 이벤트와 상관 없이 강제적 그리고 주기적으로 수행 됩니다.

## `metadata.max.idle.ms`

프로듀서가 토픽에 레코드를 더이상 보내지 않아서(idle) 해당 토픽의 메타 데이터를 삭제하게 할 시간 간격 입니다. 기본값은 "**5분**" 입니다.

프로듀서가 해당 토픽의 메타데이터를 삭제한 후에 다시 그 토픽에 접근하려고 한다면, 메타데이터 fetch 단계가 강제로 수행 됩니다.


# Kafka Consumer

Confluent의 "[Kafka Consumer Configuration Reference for Confluent Platform](https://docs.confluent.io/platform/current/installation/configuration/consumer-configs.html)" 문서를 참고해 작성 하였습니다.

## `heartbeat.interval.ms`

컨슈머는 자신이 잘 동작하고 있음을 코디네이터에게 주기적으로 하트비트를 전송 해야 합니다 이 값은 그 하트비트를 전송하는 시간 간격에 대한 값 입니다. 기본값은 "**3초**" 입니다.

이 값은 Session timeout을 방지하기 위해 아래 설명할 `session.timeout.ms` 값보다 낮게 설정해야 합니다. 일반적으로 1/3 이하로 설정 합니다.

## `session.timeout.ms`

컨슈머 클라이언트에 장애가 발생 했는지 판단하기 위한 설정값 입니다. 코디네이터가 컨슈머의 하트비트를 너무 오래 받지 못 했다면, Session Timeout이 발생하고 해당 컨슈머를 제외하면서 "리밸런싱"이 트리거 됩니다.

기본값은 "**45초**" 입니다.

## `max.poll.interval.ms`

컨슈머는 계속 `poll()`을 호출하며 땡겨올 레코드가 있는지 확인 해야 합니다. 만약  `poll()` 호출이 실행되지 않고, 여기에 설정한 값을 초과 하게 된다면, Kafka는 컨슈머가 죽었다고 판단하고 "리밸런싱"이 트리거 됩니다.

컨슈머가 대량의 데이터를 처리하고 있거나, CPU 스로틀링으로 데이터의 처리가 급격히 느려지는 경우에 이것에 의한 리밸런싱이 발생할 수 있습니다. 기본값은 "**5분**" 입니다.

## `fetch.max.wait.ms`

컨슈머는 데이터를 배치로 가져오기 위해 잠시 기다리는데, 얼만큼 기다릴지에 대한 설정값 입니다. 기본값은 "**500 밀리초**" 입니다. 만약 배치 사이즈가 `fetch.min.bytes`에 설정한 값만큼 찼다면 시간이 다 지나지 않아도 데이터 소비가 일어납니다.

## `auto.commit.interval.ms`

컨슈머가 처리한 데이터를 브로커에 커밋하는 주기 입니다. 기본값은 "**5초**" 입니다.

저는 처음에 이 값이 좀 길다고 느끼고, 그 사이에 다른 태스크가 해당 레코드를 중복 소비하면 어떡하지 생각 했습니다. 그런데, 컨슈머의 한 Task가 하나의 파티션을 담당하기 때문에, 한 파티션의 데이터가 여러 Task에서 중복 처리 되지 않습니다.

## `connections.max.idle.ms`

컨슈머가 connection을 종료하는 설정값 입니다. 컨슈머가 `poll()`을 주기적으로 수행해도 일정 시간 동안 수집되는 데이터가 없다면 Connection이 종료 됩니다. 기본값은 "**9분**" 입니다.


# Kafka Broker

Confluent의 "[Kafka Broker and Controller Configuration Reference for Confluent Platform](https://docs.confluent.io/platform/current/installation/configuration/broker-configs.html)" 문서를 참고하였습니다.

## `log.retention.ms`

브로커에 적재한 로그 세그먼트에 대한 보관 시간 입니다. 브로커는 `log.retention.hours`에 기본값이 "7일"로 설정 되어 있고, 이 속성에는 기본값이 없습니다.

액티브 세그먼트는 삭제나 압축의 대상이 되지 않습니다.

## `log.cleaner.backoff.ms`

로그 압축을 수행할 대, 정리할 데이터가 없다면 얼마나 대기할지를 결정할지에 대한 값 입니다. 기본값은 "15초" 입니다.


## `replica.lag.time.max.ms`

리더 브로커의 데이터를 복제하는 팔로워가 너무 오랫동안 데이터를 따라잡지 못하여 팔로워를 ISR 목록에서 제거하는 기준 시간 입니다.

- 팔로워가 리더에게 fetch 요청을 보내지 않거나
- 최신 로그를 일정 시간 동안 동기화 하지 못하면

ISR에서 제거 됩니다. 기본값은 "30초" 입니다.

## `broker.heartbeat.interval.ms`

브로커가 컨트롤러에게 주기적으로 하트비트를 전송하는 시간 간격 입니다. Kraft 모드에서만 사용한다고 합니다. 기본값은 "2초" 입니다.

## `zookeeper.session.timeout.ms`

카프카 브로커는 주기적으로 하트비트 신호를 주키퍼에 전송하여 자신의 상태를 알려줘야 합니다. 이때, 하트비트가 일정 시간 동안 오지 않으면 해당 브로커가 다운 되었다고 판단하고, 해당 브로커의 리더 파티션을 다른 브로커로 이동 합니다. 기본값은 "18초" 입니다.

# Kafka Topic

## `segment.ms`

로그 세그먼트 파일이 꽉 차지 않았더라도, 일정 시간이 지나면 강제로 세그먼트는 닫도록 하는 설정값 입니다. 기본값은 "7일" 입니다.

참고로 로그 세그먼트의 최대 크기를 결정하는 `segment.bytes`의 기본값은 "1 Gb" 입니다.

## `retention.ms`

오래된 세그먼트를 얼마나 유지할지에 대한 설정값 입니다. 기본값은 브로커에 설정된 `log.retention.ms` 값을 따라 가며, 둘다 기본값은 "7일" 입니다.

## `delete.retention.ms`

로그 압축에서 특정 키의 레코드를 삭제하는 Tombstone 레코드를 유지하는 시간에 대한 값 입니다. 기본값은 "1일" 입니다. 컨슈머는 이 값이 지나기 전에 Tombstone 레코드를 읽어야 레코드 삭제의 의도가 잘 전달될 수 있습니다.

## `file.delete.delay.ms`

카프카는 세그먼트가 `log.retention.ms` 값을 초과하거나 로그 크기의 임계값을 초과 하면 오래된 로그 세그먼트를 삭제합니다. 하지만, 이 삭제는 즉시 이뤄지지 않고 `file.delete.delay.ms` 동안 기다린 후에 완전한 물리적 삭제가 이뤄집니다. 기본값은 "1분" 입니다.

## `flush.ms`

카프카 브로커는 신규 데이터를 페이지 캐시(Page Cache)에 우선 저장한 후, 운영체제의 자동 동기화를 통해 디스크에 데이터가 저장 됩니다. `flush.ms`는 카프카가 주기적으로 `fsync()`를 호출하여 디스크에 강제 반영 되도록 합니다.

권장 사항으로는 `flush.ms` 값을 따로 설정하지 않고, 운영 체제의 페이지 관리 정책에 맡기는 것이 권장 됩니다. 실제로 기본값도 몇 백년으로 설정 되어 있습니다.

## `local.retention.ms`

`retention.ms`와 헷갈리는 녀석으로 로컬 브로커에 세그먼트를 저장할 주기를 말합니다. 이것은 Kafka의 Tiered Storage를 사용할 때만 신경 쓰면 됩니다. 기본값은 `retention.ms` 값을 사용합니다.

## `min.compaction.lag.ms` & `max.compaction.lag.ms`

`log.cleanup.policy=compact`인 토픽에서만 작용하는 것으로, 메시지가 압축되기 전에 일정 시간 동안 보존되도록 보장합니다.

압축 대상이 되는 세그먼트의 레코드는

- `min.compaction.lag.ms`에 설정된 값만큼은 최소한 보관 되어야 합니다.
- 그리고 `max.compaction.lag.ms`에 설정된 값이 지나면 해당 로그는 압축되어 사라집니다. (최신 로그였다면 남습니다.)

`min.compaction.lag.ms`의 기본값은 "0초"이고 `max.compaction.lag.ms`는 "몇백년" 입니다. 그래서 `log.cleanup.policy=compact` 상황에서는 반드시 설정해주는 것이 좋습니다.

# 맺음말

![](/images/meme/give-me-the-test.jpeg){: .align-center style="max-height: 360px;" }

오늘 드디어 CCDAK 시험 등록을 했습니다...! 이래저래 치이는 것들이 많으니 시험 준비 기간이 길어지네요... 암튼!! 이젠 시험 날짜도 확정 되었으니 힘차게 또 달려봅시다!
