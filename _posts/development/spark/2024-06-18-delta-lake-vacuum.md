---
title: "Delta Lake Vacuum"
toc: true
toc_sticky: true
categories: ["Spark"]
excerpt: "Stale 된 Parquet 파일들을 정리해서 용량 압축하기 🧹"
---

회사에서 Databricks를 통해 Spark Cluster를 운영하고 있습니다. 본 글은 Databricks를 기준으로 작성했음을 미리 밝힙니다.
{: .notice }


# Vacuum

Delta 테이블은 `_delta_log/` 폴더를 통해 버전이 관리 되고, DML 연산을 할 때마다 영향 받는 `.parquet` 파일을 새로 쓰기 때문에 연산을 수행할 수록 데이터가 쌓여만 간다. 이렇게 쌓여진 데이터 덕분에 Delta에서 시간 여행(Time Travel)이 가능한 것이지만, 시간 여행이란 기능이 항상 필요한 것도 아니고, 어느 시점이 지나면 Stale 된 변경 사항과 `.parquet` 파일들을 정리하는게 저장 공간을 효율적으로 쓰는 방법이다. Delta에서는 이런 Stale 파일들을 정리하기 위해 `VACUUM` 명령어를 지원한다.

```sql
VACUUM <schema>.<table> RETAIN 120 HOURS
```

이 `VACUUM`이란 연산은 자동으로 수행되는 것이 아니며, 직접 실행하여 과거 데이터를 정리해줘야 한다. Stale 된 `.parquet` 파일은 기본적으로 `7 Days`의 Deleted File Retention을 가진다. 만약 `VACUUM` 연산을 수행할 때, 별도로 지정하지 않으면 테이블에 명시된 `delta.deletedFileRetentionDuration` 값에 따라 데이터를 정리한다.

```sql
ALTER TABLE <schema>.<table>
SET TBLPROPERTIES ('delta.deletedFileRetentionDuration' = 'interval 14 days')
```

## (번외) Spark CalendarInterval

참고로 Retention Duration은 `internval ...`의 형식으로 적는데, 이때 Spark의 `CalendarInterval`라는 포맷을 따라야 한다고 Delta Lake 문서에 적혀있다. [[문서]](https://docs.delta.io/latest/table-properties.html)

해당하는 Spark 명세서([[링크]](https://spark.apache.org/docs/latest/api/java/org/apache/spark/unsafe/types/CalendarInterval.html))를 찾아보면, 이 `CalendarInterval`은 `days`, `months`, `microseconds` 필드를 가지고 있다.

따라서 Retention Duration을 줄 때도 요 필드를 따라야 하는 것 같다. 만약, 한 시간


# Log Retention

모든 Delta 파일은 `_delta_log/`라는 특별한 폴더에 연산 기록을 남긴다. 우리가 `DESC HISTORY ...`를 통해 확인하는 연산 기록과 시간 여행 기능이 모두 `_delta_log/`에 기록된 정보들 덕분인 것이다.

> Each time a checkpoint is written, Delta Lake automatically cleans up log entries older than the retention interval

Delta의 연산 기록은 무한히 남겨두는게 아니라 Log Retention에 따라서 얼만큼의 기록을 남길지를 결정한다. 그리고, Retention이 지난 기록들은 Delta 테이블의 **체크포인트(Checkpoint)**가 작성될 때마다 함께 정리된다. Log Retention 값은 아래의 명령어를 통해 수정할 수 있다.

```sql
ALTER TABLE <schema>.<table>
SET TBLPROPERTIES ('delta.logRetentionDuration' = 'interval 14 days')
```

참고로 위처럼 테이블 속성을 바꾸는 것도 모두 Delta 히스토리에 `SET TBLPROPERTIES`로 기록 된다.

앞에서도 말했듯 Stale Log의 정리는 Checkpoint가 작성될 때 수행된다. 예전에는 `VACUUM`을 수행하면 Stale Log도 같이 정리되는 줄 알았다. (머쓱)


# References

- Spark
  - [class CalendarInterval](https://spark.apache.org/docs/latest/api/java/org/apache/spark/unsafe/types/CalendarInterval.html)
- Delta Lake
  - [Remove files no longer referenced by a Delta table](https://docs.delta.io/latest/delta-utility.html#remove-files-no-longer-referenced-by-a-delta-table)
  - [Delta table properties reference](https://docs.delta.io/latest/table-properties.html)
- Databricks
- [[Delta Lake] 데이터 레이크하우스: 테이블 활용하기](https://data-engineer-tech.tistory.com/55)
