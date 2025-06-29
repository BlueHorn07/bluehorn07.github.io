---
title: "Kafka Internals"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "Page Cache, Zero-Copy, RAID 10까지, 카프카의 내부 구조를 살펴보자!"
---

회사에서 Confluent를 통해 Kafka 클러스터를 잘 사용하고 있었습니다. 그런데, 이 카프카에 대해서 좀더 자세히 알고, 전문성을 갖추고 싶다는 생각이 들어서 2025년 첫 목표로 Confluent의 Kafka 자격증인 [Confluent Certified Developer for Apache Kafka®](https://training.confluent.io/examdetail/confluent-dev) 자격증을 준비하고 있습니다 ✌️
{: .notice }

# 들어가며

Kafka는 최고 퍼포먼스를 위해 OS와 커널의 기능을 최대한 활용 합니다. 어떻게 활용하는지 내부를 들여다봅시다!

# Page Cache

![](/images/development/kafka/kafka-internals.png)

Kafka는 모든 로그를 파일 시스템에 적재 합니다. 이때, 토픽의 각 파티션은 로그가 쌓이는 디렉터리가 되고, 로그를 `.log` 포맷으로 적재 됩니다.

그런데, Kafka의 진가는 **페이지 캐시** 활용에서 드러납니다. Kafka 브로커 자체는 JVM heap을 많이 사용하지 않습니다. **겨우 6Gb 정보만 사용한다**고 합니다!! 그리고 머신의 나머지 용량은 OS가 파일에 대한 시스템 캐시(=페이지 캐시)로 활용 합니다.

그래서 32Gb 머신 위에서 카프카 클러스터를 운영하면, `-Xmx6g`로 6Gb의 힙 공간을 할당 하고 나머지 26Gb는 OS가 알아서 페이지 캐시를 충분히 활용할 수 있도록 비워둡니다!

Kafka가 "페이지 캐시"를 사용한다는 건 회사에서 Kafka 업무를 본격적으로 맡으면서 정말 많이 들었습니다... ㅋㅋ 암튼 그만큼 중요한 개념이라는 것!

이렇게 데이터를 페이지 캐시에 넣어뒀다가, 디스크에 지연해서 넣게 되면 Consumer에 데이터를 전달할 때 더 빠르게 전달해줄 수 있습니다.

이렇게 디스크에서 메모리에 데이터를 올리는 과정이 생략되기 때문에 Kafka 서버는 CPU 사용률이 적고, 처리 속도도 빠릅니다.

# Zero-Copy

클라이언트가 서버에 데이터를 요청하면, 서버는 데이터를 로컬 파일 시스템이나 원격 시스템에서 읽어서 그걸 전달해줍니다. 일반적인 서버는 (1) 파일을 읽고 (2) 데이터를 메모리에 적재한 후에 (3) 그걸 클라이언트에게 전달 합니다. Kafka는 여기에서 (2)번 과정을 생략 합니다! 즉, **파일의 내용이 메모리에 올라가지 않습니다!**

대신 Kafka는 디스크 파일 내용을 직접 네트워크 소켓으로 보내는 Zero-copy 방식을 채택 했습니다! 그래서 데이터가 프로세스 메모리 영역인 JVM heap에 올라가지 않고 즉시 전달 되며, 혹시나 적재가 되더라도 Page Cache 영역에 적재 됩니다.

만약, 전달해야 하는 데이터가 아직 페이지 캐시에 있거나, 이미 페이지 캐시로 올라왔다면 메모리에서 네트워크 소켓으로 바로 보내도록 합니다.

# RAID 10

먼저, RAID는 "Redundant Array of Inexpensive Disk"의 약자로 저렴한 디스크 여러 개를 묶어서 사용하는 디스크 클러스터 기법 입니다.

"RAID 0"는 데이터를 스트리핑(stripping)으로 저장 합니다. 스트리핑은 데이터를 저장할 때, 두 디스크에 번갈아가면서 저장하는 것 입니다. 이를 통해 읽기/쓰기 부하가 분산 됩니다.

"RAID 1"는 데이터를 이중으로 저장하여 내구성을 확보 합니다. 이를 통해 장애 복구가 가능하고,읽기 부하가 두 디스크로 분산 됩니다. 단, 전체 용량의 절반만 사용 가능 합니다.

"RAID 10"은 두 방식을 결합한 것으로 데이터 이중화와 스트라이핑 저장이 돌다 사용 됩니다!
Kafka 환경에서는 가장 권장되는 디스크 구성 방식 입니다.

# References

- [Running Kafka in Production with Confluent Platform](https://docs.confluent.io/platform/current/kafka/deployment.html)
- [How Producers Work: Kafka Producer and Consumer Internals, Part 1](https://www.confluent.io/blog/kafka-producer-internals-preparing-event-data/)
