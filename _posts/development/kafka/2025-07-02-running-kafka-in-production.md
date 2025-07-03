---
title: "Running Kafka in Production"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "Kafka를 운영할 때의 권장 세팅을 살펴보자."
robots: noindex
sitemap: false
---

회사에서 Confluent를 통해 Kafka 클러스터를 잘 사용하고 있었습니다. 그런데, 이 카프카에 대해서 좀더 자세히 알고, 전문성을 갖추고 싶다는 생각이 들어서 2025년 목표로 Confluent의 Kafka 자격증인 [Confluent Certified Developer for Apache Kafka®](https://training.confluent.io/examdetail/confluent-dev) 자격증을 준비하고 있습니다 ✌️ 전체 포스트는 [여기](/categories/kafka)에서 확인할 수 있습니다.
{: .notice }

# 들어가며

이전 회사에서는 Confluent에서 Kafka 클러스터를 운영 했습니다. 그래서 클러스터를 어떻게 구성할지나 클러스터 안정성에 대해서는 크게 고민하지 않고 Kafka 관련 작업을 했습니다. 새로운 회사에서는 Kafka 클러스터를 직접 운영 합니다! 그래서 어떻게 해야 클러스터를 안정적으로 운영할 수 있을까 고민하게 되었습니다 ㅎㅎ

이 글은 Confluent의 "[Running Kafka in Production with Confluent Platform](https://docs.confluent.io/platform/current/kafka/deployment.html)" 글을 읽고, 직접 클러스터를 구성 할 때의 가이드를 정리한 메모 글 입니다.


# Disk

> RAID 1 and RAID 10: Preferred<br/>
> RAID 0: 2nd preferred

운영 환경에서 RAID 사용이 권장 됩니다. 요건 이전의 "[Kafka Internals](/2025/06/29/kafka-internals/)" 포스트에서도 살펴본 적이 있습니다 ㅎㅎ

<br/>

주의사항으로 Tiered Stroage나 Self-balancing Cluster를 쓴다면, JBOD(Just a Bundle of Disks)를 쓰지 말라고 합니다.

이유는 JBOD는 여러 개의 디스크를 각각 독립적으로 사용하는 운영 방식인데, RAID 시스템과 다르게 디스크를 묶어서 사용하는 것이 아닙니다.

예를 들어, 브로커에 3개의 디스크가 있고,

- `/mnt/disk1`
- `/mnt/disk2`
- `/mnt/disk3`

이걸 Kafka 설정에서 요렇게 설정하면,

```bash
log.dirs=/mnt/disk1/kafka-logs,/mnt/disk2/kafka-logs,/mnt/disk3/kafka-logs
```

그러면, Kafka는 파티션 로그 데이터를 3개 디스크에 알아서 분산 저장 해줍니다. 같은 파티션에 속하는 로그는 같은 디스크에 위치합니다. Kafka 브로커가 새로운 파티션을 처리하게 될 때, 현재 마운트된 디스크 중에 사용량이 낮은 쪽을 선호해서 고른다고 합니다.

Tiered Storage, Self-balancing Cluster는 브로커가 하나의 디스크를 가지고 운영 되는 것으로 가정하고 있기 때문입니다.

<br/>

디스크 공간을 Kafka 이외의 다른 서비스와 공유 하며 사용하지 말아라. 이런 구성은 Kafka의 성능을 저하 시킵니다.

<br/>

NAS 디스크는 심각한 latency를 겪을 수 있으니 쓰지 말아라.

# JVM

카프카를 세팅하기 전에 JDK 설치를 수행하라. 컨플 설치 패키지에 JDK와 OS는 포람 되어 있지 않다.

# Memory

이전의 "[Kafka Internals](/2025/06/29/kafka-internals/)" 포스트에서 Kafka의 Page Cache에 대해 살펴보면서 같이 살펴보았습니다!

32Gb가 권장 사항인 것으로 보입니다.

# Kraft Mode

운영 환경에서는 최소 3대의 브로커와 3대의 컨트롤러 노드로 구성하라.

각 노드는 설정된 `process.roles` 값에 따라서 역할을 받습니다.

# Seucirty

RBAC를 세팅하는 것을 권장한다.

SASL/SCRAM을 구성하여 inter-broker 사이 통신과 broker-client 사이 통신을 보호하라.

# File Descriptors

파일 디스크립터(fd)는 리눅스에서 파일, 소켓, 파이프 등 거의 모든 I/O 자원을 가리키는 정수형 숫자 입니다.

Kafka는 많은 로그 파일들을 다루고, 또 클라이언트-브로커 간 통신을 위한 소켓을 사용하므로, 하나의 Kafka 프로세스가 매우 많은 fd를 필요로 합니다.

대부분의 리눅스 배포판이 1024개의 fd가 기본값으로 제한 되어 있습니다. 이 수치로 인해 Kafka가 로그 파일을 열 수 없거나 클라이언트 사이의 통신이 불안정 해질 수 있습니다.

문서에서는 fd의 제한을 100,000개 이상으로 늘리는 것을 권합니다. OS에 따라 제한값을 바꾸는 방법이 다르기 때문에, 자세한 것은 OS의 가이드 문서를 보라고 합니다!

# mmap (Memory Mapping)

Kafka는 로그 파일을 처리할 때, 성능을 위해 mmap을 사용합니다. 이때, 시스템의 mmap 한계값이 낮게 설정 되어 있으면, Kafka가 더이상 파일을 메모리 매핑 할 수 없게 되어 OOM이 발생하거나 오류가 발생할 수 있습니다.

그래서 `vm.max_map_count` 값을 늘려줘야 합니다. 충분히 높은 값으로 변경 해주면 되고, 구체적인 변경 방법은 OS의 가이드 문서를 참고.
