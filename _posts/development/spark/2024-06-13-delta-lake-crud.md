---
title: "Delta Lake: CRUD"
toc: true
toc_sticky: true
categories: ["Spark"]
exceprt: Delta Lake의 기본 연산에 대한 고찰
---

회사에서 Databricks를 통해 Spark Cluster를 운영하고 있습니다. 본 글은 Databricks를 기준으로 작성했음을 미리 밝힙니다.
{: .notice }

# Create Delta Table

## With Specific Name

```sql
CREATE TABLE `sample_schema`.`sample_table` (
    id INT,
    name STRING
) USING delta
```

만약 `LOCATION`을 따로 지정하지 않으면, Databricks의 기본 경로에 `_delta_log/` 폴더와 함께 테이블이 생성된다. 웬만하면 아래와 같이 `LOCATION`을 지정하자.

```sql
CREATE TABLE `sample_schema`.`sample_table` (
    id INT,
    name STRING
) USING delta
LOCATION `s3a://{BUCKET_NAME}/{PATH}`
```

## Without Name

```sql
CREATE TABLE delta.`s3://{BUCKET_NAME}/{PATH}` (
    id INT,
    name STRING
) USING delta;
SELECT * FROM delta.`s3://{BUCKET_NAME}/{PATH}`
```

이것이 가능한 이유는 Delta 테이블의 경우, 스키마 정보가 Hive Metastore에 저장되는 것이 아니라 `_delta_log/` 폴더에 담기기 때문이다. 즉, Delta 테이블을 생성되지만, Hive Metastore에는 등록되지 않는다.

앞에 붙은 `delta`는 스키마 이름이 아니라 `delta` 테이블을 읽거나 만든다는 예약 키워드다.

# Read Delta Table

## With Table Name

테이블 이름을 지정했을 경우에는 `{SCHEMA_NAME}.{TABLE_NAME}`으로 읽으면 된다.

```sql
SELECT * FROM `sample_schema`.`sample_table`
```

```scala
val df = spark.table("sample_schema.sample_table")
```

## Without Table Name

```sql
SELECT * FROM delta.`s3://{BUCKET_NAME}/{PATH}`
```

```scala
val df = spark.read.format("delta").load("s3://{BUCKET_NAME}/{PATH}")
```

# Write to Delta Table

## save vs. saveAsTable

```scala
// 테이블 이름으로 Write
df.write.format("delta").mode("append").saveAsTable("sample_schema.sample_table")

// S3에 바로 Write
df.write.format("delta").mode("append").save("s3://{BUCKET_NAME}/{PATH}")
```

## append vs overwrite

```scala
// 기존 데이터는 남기고 append
df.write.format("delta").mode("append").saveAsTable("sample_schema.sample_table")

// 기존 데이터 모두 drop 하고 overwrite
df.write.format("delta").mode("overwrite").saveAsTable("sample_schema.sample_table")
```

## replaceWhere

기존 데이터 중 일부를 대체하고 싶을 때 사용.

```scala
df.write.format("delta")
  .mode("overwrite")
  .option("replaceWhere", "id = 1")
  .saveAsTable("sample_schema.sample_table")
```

## with Commit Message

```scala
df.write.format("delta")
  .mode("overwrite")
  .option("userMetadata", "HAHA HOHO!")
  .saveAsTable("sample_schema.sample_table")
```

이렇게 `userMetadata`에 값을 주면, 이후에 Delta의 Version History를 검색할 때 유용하게 쓸 수 있다.

```scala
import io.delta.tables.DeltaTable

val d = DeltaTable.forName("example_schema.example_table")
val d_history = d.history.select("version", "operation", "userMetadata").show()

---
+-------+------------+------------+
|version|   operation|userMetadata|
+-------+------------+------------+
|      6|       WRITE|comment-test|
|      5|       WRITE|        null|
|      4|       WRITE|        null|
|      3|       WRITE|        null|
|      2|       WRITE|        null|
|      1|       WRITE|        null|
|      0|CREATE TABLE|        null|
+-------+------------+------------+
```


# Delete

Parquet 기반 테이블과 달리 DML 연산이 가능하다!! 다만, 삭제의 경우 데이터가 물리적으로 삭제 되는 것이 아니다! 기존에 읽던 `.parquet` 파일이 그대로 존재한다.

예를 들어, `id`가 1부터 1000까지 있는 테이블에 아래와 같은 `DELETE` 연산을 해보자.

```sql
DELETE FROM example_schema.example_table
WHERE id BETWEEN 1 AND 500
```

이 경우, `id=1~1000` 데이터가 들어있던 `.parquet` 파일에서 삭제된 부분을 제외한 `id=501~1000` 데이터가 담긴 `.parquet` 파일이 새로 생성된다. 데이터를 절반쯤 날렸으니, 새로 생성된 `.parquet`는 기존 파일의 절반 쯤의 크기를 갖게 된다.

이럼 점 때문에, `DELETE` 연산은 오히려 스토리지 용량이 늘어난다. 또, `DELETE` 연산을 할수록 여분 데이터가 복제되어 신규 버전에 쌓이는 것이기 때문에 작은 `DELETE`를 여러번 할수록 Delta에 담긴 `.parquet` 파일들과 크기의 총합이 매우 증가하게 된다.


# Update

```sql
UPDATE example_schema.example_table
SET name = "hahaha"
WHERE id >= 150
```

`UPDATE`도 마찬가지로 기존 parquet 데이터가 `SET` 내용을 적용한 후에, 새로운 `.parquet` 파일을 만든다. 이때, `UPDATE` 전의 기존 `.parquet`와 적용 후의 `.parquet` 파일은 행 갯수가 같다.

즉, Delta의 `DELETE`/`UPDATE` 연산 둘다 데이터의 온전한 스냅샷을 만든다는 걸 알 수 있다!!


# Merge Into

Delta에서는 `MERGE`라는 Upsert 연산을 지원한다. 만약 조건에 맞으면 기존 데이터를 갱신하고, 조건에 맞지 않으면 신규 데이터를 삽입한다.

```sql
MERGE INTO example_schema.example_table AS target
USING example_schema.product AS source
ON
  target.id = source.id
WHEN MATCHED THEN
  UPDATE SET
    target.id = source.id,
    target.name = source.name
WHEN NOT MATCHED THEN
  INSERT *
```

위의 쿼리에는 `UPDATE`, `INSERT` 동작을 수행하지만, `DELETE` 동작도 할 수 있다! 예를 들어, `WHEN MATCHED THEN DELETE`로 만약 조건에 맞는 데이터가 있다면, 삭제할 수도 있다!!

# References

- Delta Lake
  - [Table Batch Reads and Writes](https://docs.delta.io/0.4.0/delta-batch.html)
  - [Table Deletes, Updates, and Merges](https://docs.delta.io/0.4.0/delta-update.html)
- [[Delta Lake] 데이터 레이크하우스: 테이블 활용하기](https://data-engineer-tech.tistory.com/55)
