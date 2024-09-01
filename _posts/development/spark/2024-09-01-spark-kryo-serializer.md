---
title: "Spark Kryo Serializer"
toc: true
toc_sticky: true
categories: ["Spark"]
excerpt: "Spark의 직렬화, 그리고 직렬화 성능을 올려주는 Kryo Serializer 사용하기."
---

Databricks Certification 취득을 목표로 Apache Spark를 “제대로” 공부해보고 있습니다. 회사에선 Databricks Unity Catalog를 도입하려고 분투하고 있는데요. Spark와 좀 친해질 수 있을까요? 🎇 전체 포스트는 [Development - Spark](/topic/development#apache-spark)에서 확인해실 수 있습니다.
{: .notice--info}

# 직렬화란?

직렬화(Serialization)이란 프로그래밍 언어(Java, Python 등등)의 객체를 바이너리 포맷인 byte 형태로 변환하는 작업을 말한다. 데이터를 바이너리로 변환하고, 이 데이터를 네트워크나 저장소로 보내는 처리한다. 데이터를 Json, Csv 또는 Python Pickle 파일로 저장하는 모든 과정이 직렬화에 해당한다.

반댓말은 역질렬화(deserialization)이다. 바이너리로 된 데이터를 프로그래밍 언어의 객체로 변환하는 과정을 말한다. 파일로 저장된 데이터를 프로그래밍 언어에서 읽거나, Response로 받은 네트워크 데이터의 값을 프로그래밍 언어에서 읽는 과정을 말한다.

# Spark에서의 직렬화

Spark은 많은 부분에서 데이터를 직렬화 하여 전달한다.

1. 워커 노드 사이에 데이터를 셔플링 할 떄
2. RDD 데이터를 디스크에 저장할 때

Spark에선 2가지 방식의 직렬화를 제공하는데, 하나가 Java Serialization이고, 또 하나가 요 포스트에서 살펴보는 [Kryo Seriliazation](https://github.com/EsotericSoftware/kryo)이다.

Spark 문서에 따르면, Kryo가 Java 직렬화 보다 보통 10배 정도 빠르다고 한다. Spark에서 다루는 단순 데이터들을 Kryo를 사용해 직렬화 처리하는 것이 확실히 낫다는 말.

다만, 요 Kryo 직렬화를 사용하려면 `spark.serializer`의 값을 `org.apache.spark.serializer.KryoSerializer`로 직접 설정해야 한다.

직렬화는 거의 모든 Spark 작업에서 반드시 일어나기 때문에, Kryo 직렬화를 사용하는 건 Spark 튜닝의 가장 쉬운 방법이다. 심지어 Kryo를 쓰기만 하면 개선이 된다고 한다!!! (대박)

![](https://www.databricks.com/wp-content/uploads/2020/05/blog-adaptive-query-execution-2.png){: .align-center style="width: 100%" }
[Databricks: Adaptive Query Execution](https://www.databricks.com/blog/2020/05/29/adaptive-query-execution-speeding-up-spark-sql-at-runtime.html)
{: .align-caption .text-center .small .gray }


# References

- [Spark: Tuning - Data Serialization](https://spark.apache.org/docs/latest/tuning.html)
