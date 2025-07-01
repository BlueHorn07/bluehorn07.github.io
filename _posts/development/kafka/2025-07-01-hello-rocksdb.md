---
title: "Hello, RocksDB!"
toc: true
toc_sticky: true
categories: ["Kafka"]
excerpt: "K-Streams와 Flink에서 중간 결과를 Key-value로 저장하기 위해 쓰는 RocksDB를 알아보자!"
---

회사에서 Confluent를 통해 Kafka 클러스터를 잘 사용하고 있었습니다. 그런데, 이 카프카에 대해서 좀더 자세히 알고, 전문성을 갖추고 싶다는 생각이 들어서 2025년 목표로 Confluent의 Kafka 자격증인 [Confluent Certified Developer for Apache Kafka®](https://training.confluent.io/examdetail/confluent-dev) 자격증을 준비하고 있습니다 ✌️ 전체 포스트는 [여기](/categories/kafka)에서 확인할 수 있습니다.
{: .notice }

# 들어가며

K-streams와 Flink 관련 자료를 찾아보면서, RocksDB를 상태저장 백엔드로 사용한다는 것을 알게 되었습니다. RocksDB를 제대로 사용해본 적이 없는 것 같아서 한번 친해져보려고, 로컬 환경에서 핸즈온을 진행해보았습니다.

처음에는 Python으로 진행해보려고 했는데, Python은 공식 pip 패키지가 없고 커뮤니티의 Maintanence도 이뤄지지 않아서 최신 python 버전(3.13)과 호환되지 않는 것 같더라구요 ㅠㅠ 그래서 Java(JDK 17) 환경에서 RocksDB를 처음 시작해보았습니다.

# RocksDB란?

스트림 어플리케이션에서 윈도우에 대한 집계를 수행할 때, 중간 연산 결과를 RocksDB에 저장합니다.

구체적으로는 스트리밍 집계 연산을 수행할 때, 중간 결과를 메모리에 들고 있다가 메모리 한계를 넘게 되면, RocksDB에 결과를 저장한다고 알고 있습니다. 이를 통해 스트리밍 처리가 OOM으로 중단되지 않도록 지원한다고 합니다.

RocksDB를 "**임베디드**" Key-Value Store라고 하는데요. 비슷한 임베디드 DB로는 SQLite(임베디드 SQL), derby(Hive Metastore), duckDB 등이 있습니다. 저는 RocksDB는 Redis의 임베디드 버전이라고 이해 했습니다.

RocksDB는 스트리밍 어플리케이션 수준에서 사용 됩니다. Kafka 클러스터 쪽에서는 전혀 사용하지 않습니다.

# Hello, RocksDB!

일단 바로 실습을 해봅시다! `gradle init`으로 빈 프로젝트를 하나 만들고, 그곳의 `app/build.gradle`에 아래와 같이 RocksDB 의존성을 추가합니다.

```groovy
dependencies {
    ...
    implementation 'org.rocksdb:rocksdbjni:8.10.0'
    ...
}
```

그리고 `App.java`를 요렇게 구성 합니다.

```java
package org.example;

import org.rocksdb.*;

public class App {
    public static void main(String[] args) throws RocksDBException {
        Options options = new Options();
        options.setCreateIfMissing(true);

        RocksDB db = RocksDB.open(options, "rocksdb");

        db.put("hahahaha".getBytes(), "hohohoho".getBytes());

        System.out.println(new String(db.get("hahahaha".getBytes())));

        db.close();
    }
}
```

그리고 `./gradlew run`을 실행하면, RocksDB가 `app/rocksdb/` 폴더에 구성 됩니다.

```bash
tree .
.
├── 000008.sst
├── 000009.log
├── CURRENT
├── IDENTITY
├── LOCK
├── LOG
├── LOG.old.1751384040339784
├── MANIFEST-000010
├── OPTIONS-000007
└── OPTIONS-000012

1 directory, 10 files
```

주목할 것은 `.sst` 파일과 `.log` 파일 입니다.

## Sorted String Table (SST)

RocksDB가 실제 Key-Value 저장소로 사용하는 파일 입니다. 이 파일에 persistent한 데이터를 보관 합니다. RocksDB의 인메모리 테이블이 가득 차면, `.sst`로 플러쉬 되고, 나중에 여러 `.sst` 파일이 병합되는 방식으로 관리 됩니다.

`.sst` 파일 내부는 Key 기준으로 정렬 되어 있기 때문에, 빠른 Range Search를 지원 합니다.

단, `.sst` 파일은 불변적이기 때문에 한번 만들어지면 수정하지 않습니다. `.sst` 파일에 대한 수정은 병합(compaction) 단계에서 이뤄집니다.

## Write-Ahead log (WAL, LOG)

데이터를 인메모리 테이블에 넣기 전에 디스크에 먼저 기록하는 로그 파일 입니다. 크래쉬 상황에서 복구 용도로 사용합니다.

데이터가 `.log` 파일에 작성될 때, `.sst` 파일을 건드리지는 않습니다. `.sst`는 인메모리 테이블의 내요이 플러쉬 될 때만 영향을 받습니다.

일정 시간이 지나거나, 인메모리 테이블이 플러쉬 될 때 `.log` 파일도 같이 정리 됩니다.

# 더 나아가기

RocksDB는 그외에도 Transaction이나 TTL 기능을 지원 한다고 합니다!

Kafka 스트리밍 앱을 공부하면서 RocksDB를 처음 알게 되었지만, Spark Streaming에서도 집계와 같은 Stateful 연산을 수행할 때 RocksDB를 백엔드로 사용하는 경우도 있다고 합니다.

이제 처음 살펴보게 된 녀석이라 실무에서 어떻게 사용해야 할지는 더 살펴봐야 할 것 같습니다 ㅎㅎ 요즘 임팩트 있는 Kafka 스트리밍 사레를 좀 만들어보고 싶다는 생각이 있는데요! 요 방향으로 좀더 파보고 고민해봐야 할 것 같습니다 ㅎㅎ
