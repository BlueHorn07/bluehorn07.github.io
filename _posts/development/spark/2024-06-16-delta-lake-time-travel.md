---
title: "Delta Lake Time Travel"
toc: true
toc_sticky: true
categories: ["Spark"]
excerpt: Delta 테이블의 버전 관리를 통해 특정 시점과 특정 버전으로 시간 여행하기. 특정 버전으로 복구하는 것까지!
---

회사에서 Databricks를 통해 Spark Cluster를 운영하고 있습니다. 본 글은 Databricks를 기준으로 작성했음을 미리 밝힙니다.
{: .notice }

# Time Travel

![](https://delta.io/static/9c42ea9f028932de03257ed75d35a8ba/cf8e5/image1.png)

Delta 테이블은 특정 시점과 특정 버전으로 테이블 시간 여행(Time Travel) 할 수 있다. 덕분에 데이터를 실수로 삭제하더라도, 직전 버전으로 돌아가 데이터를 확인할 수 있다.


## 현재 어떤 버전인지 확인하기

아래의 SQL 구문으로 Delta 테이블의 수정 기록을 확인할 수 있다.

```sql
DESC HISTORY <schema>.<table>
```

|version|timestamp           |userId          |operation   |
|-------|--------------------|----------------|------------|
|10     |2024-06-14T01:12:19Z|xxxx            |MERGE       |
|9      |2024-06-14T00:54:37Z|xxxx            |UPDATE      |
|8      |2024-06-14T00:47:27Z|xxxx            |DELETE      |
|7      |2024-06-14T00:24:52Z|xxxx            |DELETE      |
|6      |2024-06-13T15:06:12Z|xxxx            |WRITE       |


Scala에선 아래의 코드로 같은 정보를 확인할 수 있다.

```scala
import io.delta.tables.DeltaTable

val df = DeltaTable.forName("<schema>.<table>")
val history_df = df.history()
```


## 특정 버전으로 이동

SQL에선 `VERSION AS OF` 명령어를 붙여서 쿼리하면 된다.

```SQL
SELECT
  *
FROM
  <schema>.<table>
  VERSION AS OF 1
```

이때, `@`를 사용해서 더 짧게 SQL 코드를 작성할 수도 있다.

```sql
SELECT * FROM <schema>.<table>@v1
```

특정 버전을 쿼리하려면 `@v`를 붙이고, 특정 시점으로 쿼리하려면 `@yyyyMMdd` 포맷으로 접근하면 된다.


Scala에서는 테이블을 읽을 때 `versionAsOf` 옵션을 주면 된다.

```scala
val df = spark.read.option("versionAsOf", 1).table("<schema>.<table>")
println(df.count())
```

위의 `@` 문법은 Scala, Python에서도 모두 통한다.

```scala
spark.table("<schema>.<table>@v1")
```

## 특정 시점으로 이동


SQL에선 `TIMESTAMP AS OF` 명령어를 붙여서 쿼리하면 된다.

```SQL
SELECT
  *
FROM
  <schema>.<table>
  TIMESTAMP AS OF '2024-06-06'
```

timestamp 형식은 `yyyy-MM-DD hh:mm:ss.0`으로 줄 수도 있고, `DATE_SUB(CURRENT_DATE(), 7)`와 같이 SQL 함수를 통해 값을 전달할 수도 있다.

Scala에선 `timestampAsOf` 옵션을 주면 된다.

```scala
val df = spark.read.option("timestampAsOf", "2024-06-06").table("<schema>.<table>")
println(df.count())
```


## 특정 시점으로 복구

SQL에선 `RESTORE` 명령어로 테이블을 복구하면 된다.

```sql
RESTORE TABLE <schema>.<table> TO VERSION AS OF 1
```

이 경우, 현재 버전에서 `+1`을 해서 `RESTORE` 버전을 하나 추가하여 테이블을 복구한다. 단, Delta 히스토리에 추가될 뿐 새로운 `.parquet` 파일이 생기거나 하진 않는다.


Scala에선 아래와 같이 실행하면 된다.

```scala
import io.delta.tables.DeltaTable

val df = DeltaTable.forName("<schema>.<table>")

df.restoreToVersion(1)
df.restoreToTimestamp("2024-06-06")
```


# References

- Delta Lake
  - [Delta Lake Time Travel](https://delta.io/blog/2023-02-01-delta-lake-time-travel/)
- Databricks
  - [Introducing Delta Time Travel for Large Scale Data Lakes](https://www.databricks.com/blog/2019/02/04/introducing-delta-time-travel-for-large-scale-data-lakes.html)
  - [Work with Delta Lake table history](https://docs.databricks.com/en/delta/history.html)
- [[Delta Lake] 데이터 레이크하우스: 테이블 활용하기](https://data-engineer-tech.tistory.com/55)
