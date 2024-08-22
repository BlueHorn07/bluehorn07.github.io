---
title: "💻 로컬 맥북에서 Spark 실행하기 - 1편: Local Mode"
toc: true
toc_sticky: true
categories: ["Spark"]
excerpt: "Spark 꼼꼼히 살펴보기의 첫걸음"
---

2024년 목표로 Databricks Certification을 취득해보려고 Apache Spark를 "제대로" 공부해보고 있습니다. 🎇
{: .notice--info}

악기를 배울 때의 첫 걸음을 악기를 사는 것(음.!?), 새로운 언어를 배울 때도 언어를 세팅 하는 것부터 하듯이. Spark도 로컬에 세팅을 해보면서 배우는게 많을 거라고 생각한다. 본인은 회사에서 Databricks를 사용해서 그동안 Spark를 한번도 직접 세팅 해본 적이 없었다...! Databricks가 참 편리하긴 한데, 근본 원리를 모르고 쓰다보니 모래성 위에서 뭔가를 쌓아올리는 느낌을 떨칠 수 없었다. 그래서 시작하게 된 Spark 공부...!

지금까지 많은 것들을 새로 익히고 배웠지만...!? 새로운 걸 배우는 걸 늘 힘들고 괴로웠다. 처음엔 어렵겠지만 곧 익숙해지겠지 뭐~~ 암튼 일단 로컬에 `pyspark`를 설치해서 Spark를 돌려보자!!

# Install Spark

Spark를 설치하는 방법은 여러 방법이 있겠으나, 여기서는 `pip3 install pyspark`를 설치해 사용했다. `pyspark` 안에 pip package 뿐만 아니라 각종 Spark CLI들이 모두 들어있다. 만약, `pyspark` 방식을 하고 싶지 않다면, 아래와 같이 Spark CLI만 설치해서 사용해볼 수도 있다.

```bash
$ brew install apache-spark
```

## Just Install PySpark

```sh
# venv 세팅
$ python3 -m venv venv
$ source venv/bin/activate

# pyspark 설치
$ pip3 install pyspark==3.5.2
```

## `pyspark` CLI

```sh
$ pyspark
Python 3.12.4 (main, Jun  6 2024, 18:26:44) [Clang 15.0.0 (clang-1500.3.9.4)] on darwin
Type "help", "copyright", "credits" or "license" for more information.
24/08/18 17:06:43 WARN Utils: Your hostname, Seokyunui-MacBookPro.local resolves to a loopback address: 127.0.0.1; using 192.168.0.21 instead (on interface en0)
24/08/18 17:06:43 WARN Utils: Set SPARK_LOCAL_IP if you need to bind to another address
Setting default log level to "WARN".
To adjust logging level use sc.setLogLevel(newLevel). For SparkR, use setLogLevel(newLevel).
24/08/18 17:06:43 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
Welcome to
      ____              __
     / __/__  ___ _____/ /__
    _\ \/ _ \/ _ `/ __/  '_/
   /__ / .__/\_,_/_/ /_/\_\   version 3.5.2
      /_/

Using Python version 3.12.4 (main, Jun  6 2024 18:26:44)
Spark context Web UI available at http://192.168.0.21:4040
Spark context available as 'sc' (master = local[*], app id = local-1723968404162).
SparkSession available as 'spark'.
>>> 
```

위의 출력에서 주목할 부분이 있는데,

> Spark context Web UI available at `http://192.168.0.21:4040`<br/>
> Spark context available as `sc` (master = local[*], app id = local-1723968404162).<br/>
> SparkSession available as `spark`.

`http://localhoost:4040/jobs/`에 들어가보면, 요렇게 Spark UI가 뜨는 걸 볼 수 있다.

![](/images/development/spark/pyspark-spark-ui.png){: .align-center style="max-height: 400px" }

해당 콘솔에서 Spark Context와 Spark Session에 접근하려면 각각 `spark`와 `sc`로 접근하면 된다. (참고로 Databricks도 마찬가지로 클러스터를 붙이면 `spark`, `sc` 변수가 항상 존재한다.)

```py
>>> spark
<pyspark.sql.session.SparkSession object at 0x10cc3b6b0>

>>> sc
<SparkContext master=local[*] appName=PySparkShell>
```

Spark Context에서 `master=local[*]`로 되어 있는 부분은 2가지 의미를 담고 있는데,

- `local`: Spark 어플리케이션이 로컬 모드에서 실행됨. 로컬 모드에선 클러스터가 필요하지 않으며, 단일 머신에서 모든 작업이 수행됨.
- `[*]`: 사용 가능한 모든 코어를 의미. 컴퓨터의 사용 가능한 모든 코어를 쓰겠다는 의미임.

만약 코어 갯수를 지정하고 싶다면, `local[4]`와 같이 적으면 된다고 한다.


## `import pyspark`

`import pyspark`해서 pyspark 코드를 실행하는 것도 가능하다. 아주 간단한 pyspark 코드를 실행해보자. SparkSession과 SparkContext는 특별한 이유가 없다면, 모두 `spark`와 `sc`로 이름을 지정하겠다.

```py
from pyspark.sql import SparkSession

spark = SparkSession.builder.getOrCreate()
print(spark)

sc = spark.sparkContext
print(sc)

rdd = sc.parallelize(range(10000))
cnt = rdd.count()
print(cnt)
---
<pyspark.sql.session.SparkSession object at 0x105bbe3c0>
<SparkContext master=local[*] appName=pyspark-shell>
10000 
```

이때, 마지막에 `time.sleep(60 * 60 * 2)`를 추가 해두면, Spark UI를 확인할 수 있다. 주소는 이전과 똑같이 `http://localhoost:4040/jobs/`이다.

![](/images/development/spark/pyspark-spark-ui-2.png){: .align-center style="max-height: 400px" }

실행 했던 것들이 Spark Jobs들로 만들어져서 잘 나온다!!

# Spark Local Mode란?

지금까지 로컬에서 `pyspark`를 설치해 실행한 방법은 Spark를 Local Mode로 사용한 것이다. Spark를 실행하는 방법은 크게 "Local Mode"와 "Cluster Mode"로 나뉜다. 쉽게 생각하면 단일 머신이냐 드라이버-워커로 구성된 클러스터로 돌리냐의 차이.

![](/images/development/spark/spark-local-mode.png){: .align-center style="max-height: 400px" }
Spark Local Mode
{: .align-caption .text-center .small .gray }

- **Local Mode** (지금까지 한 것)
- Cluster Mode (언젠가 다룰 예정)
  - Client Deploy Mode
  - Cluster Deploy Mode

암튼 지금까지 우리가 실행한 방식은 Local Mode라는 것! 이 모드는 단일 머신에서 모든 것을 실행한다. 그래도 Diver와 Executor는 각각 1개씩 뜬다.

# `spark-submit`

`spark-submit`을 통해 Spark 머신에 python 코드를 실행하게 만들 수 있다. Local Mode에서 `spark-submit`을 해보자.

```sh
$ spark-submit \
  --master "local[2]" \
  ./hello-spark.py
---
...
24/08/22 01:15:54 INFO TaskSchedulerImpl: Adding task set 0.0 with 2 tasks resource profile 0
24/08/22 01:15:54 INFO TaskSetManager: Starting task 0.0 in stage 0.0 (TID 0) (172.30.1.16, executor driver, partition 0, PROCESS_LOCAL, 8979 bytes) 
24/08/22 01:15:54 INFO TaskSetManager: Starting task 1.0 in stage 0.0 (TID 1) (172.30.1.16, executor driver, partition 1, PROCESS_LOCAL, 8979 bytes) 
24/08/22 01:15:54 INFO Executor: Running task 0.0 in stage 0.0 (TID 0)
24/08/22 01:15:54 INFO Executor: Running task 1.0 in stage 0.0 (TID 1)
24/08/22 01:15:55 INFO PythonRunner: Times: total = 329, boot = 285, init = 44, finish = 0
24/08/22 01:15:55 INFO PythonRunner: Times: total = 329, boot = 283, init = 46, finish = 0
24/08/22 01:15:55 INFO Executor: Finished task 1.0 in stage 0.0 (TID 1). 1324 bytes result sent to driver
24/08/22 01:15:55 INFO Executor: Finished task 0.0 in stage 0.0 (TID 0). 1324 bytes result sent to driver
24/08/22 01:15:55 INFO TaskSetManager: Finished task 0.0 in stage 0.0 (TID 0) in 390 ms on 172.30.1.16 (executor driver) (1/2)
24/08/22 01:15:55 INFO TaskSetManager: Finished task 1.0 in stage 0.0 (TID 1) in 389 ms on 172.30.1.16 (executor driver) (2/2)
24/08/22 01:15:55 INFO TaskSchedulerImpl: Removed TaskSet 0.0, whose tasks have all completed, from pool 
24/08/22 01:15:55 INFO PythonAccumulatorV2: Connected to AccumulatorServer at host: 127.0.0.1 port: 51645
24/08/22 01:15:55 INFO DAGScheduler: ResultStage 0 (count at /Users/seokyunha/xxxx/hello-spark.py:10) finished in 0.832 s
24/08/22 01:15:55 INFO DAGScheduler: Job 0 is finished. Cancelling potential speculative or zombie tasks for this job
24/08/22 01:15:55 INFO TaskSchedulerImpl: Killing all running tasks in stage 0: Stage finished
24/08/22 01:15:55 INFO DAGScheduler: Job 0 finished: count at /Users/seokyunha/xxxx/hello-spark.py:10, took 0.848853 s
10000
```

아직 로그 읽는 법은 잘 모르겠다. 나중에 찾아볼 것!!

# 마무리

![](/images/meme/thumbs-up.png){: .align-center style="max-height: 280px" }

여기까지가 단일 머신에서 Spark 작업을 실행하는 Local Mode 였다. 포스트를 나누어, 2편에서는 다중 머신 구조인 Cluster Mode에서 Spark 작업을 실행하는 방법을 살펴보자!!

👉 [로컬 맥북에서 Spark 실행하기 - 2편: Cluster Mode](/2024/08/18/run-spark-on-local-2/)
