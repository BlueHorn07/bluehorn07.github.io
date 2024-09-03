---
title: "💻 로컬 맥북에서 Spark 실행하기 - 2편: Client Mode"
toc: true
toc_sticky: true
categories: ["Spark"]
excerpt: "로컬에서 Spark 클러스터 구축하기 ✌️ Master Node가 Cluster Manager가 되는 Standalon 모드. Spark Application을 실행하는 두 가지 방법: Client 모드, Cluster 모드. Master vs. Worker 그리고 Driver vs. Executor"
---

Databricks Certification 취득을 목표로 Apache Spark를 “제대로” 공부해보고 있습니다. 회사에선 Databricks Unity Catalog를 도입하려고 분투하고 있는데요. Spark와 좀 친해질 수 있을까요? 🎇 전체 포스트는 [Development - Spark](/topic/development#apache-spark)에서 확인해실 수 있습니다.
{: .notice--info}

1편에서는 단일 머신에서 실행하는 "Local Mode"로 Spark 작업을 하는 방법을 살펴보았다. 2편에서는 "Client Mode"로 Spark 클러스터를 구성하고, Spark 작업을 실행하는 방법을 살펴보자.

# Setup Spark Cluster

## Setup Master Node

먼저 Master 노드를 띄워보자. `pyspark`로 Spark를 세팅했다면, 함께 제공하는 `spark-class` 명령어로 Master 노드를 띄울 수 있다.

```sh
$ spark-class org.apache.spark.deploy.master.Master -h localhost
...
24/08/22 00:52:41 INFO Utils: Successfully started service 'sparkMaster' on port 7077.
24/08/22 00:52:41 INFO Master: Starting Spark master at spark://localhost:7077
24/08/22 00:52:41 INFO Master: Running Spark version 3.5.2
24/08/22 00:52:41 INFO JettyUtils: Start Jetty 0.0.0.0:8080 for MasterUI
24/08/22 00:52:41 INFO Utils: Successfully started service 'MasterUI' on port 8080.
24/08/22 00:52:41 INFO MasterWebUI: Bound MasterWebUI to 0.0.0.0, and started at http://172.30.1.16:8080
24/08/22 00:52:41 INFO Master: I have been elected leader! New state: ALIVE
```

그리고 `http://localhost:8080`에 접속하면, Spark UI도 볼 수 있다.

![](/images/development/spark/spark-master-deploy.png){: .align-center style="max-height: 480px" }
Master 노드만 띄운 상태
{: .align-caption .text-center .small .gray }

일단 Master 노드만 띄운 상태에서 요기에 `spark-hello.py` 작업을 `spark-submit` 해보자.

```sh
$ spark-submit \
  --master spark://localhost:7077 \
  ./hello-spark.py
```

이 상태에선 Spark Application은 만들어진다. 그러나 Spark App을 실행할 워커가 없기 때문에, 실행이 되지 않고 계속 기다리기만 한다.

> WARN TaskSchedulerImpl: Initial job has not accepted any resources; check your cluster UI to ensure that workers are registered and have sufficient resources

즉, Worker가 아직 붙지 않아서 Spark App이 실행되지 않고 있었다! 그러면 이제 워커 노드를 붙여보자.

## Setup Worker Node

```bash
$ spark-class org.apache.spark.deploy.worker.Worker \
    spark://localhost:7077
---
...
24/08/22 13:00:57 INFO JettyUtils: Start Jetty 0.0.0.0:8081 for WorkerUI
24/08/22 13:00:57 INFO Utils: Successfully started service 'WorkerUI' on port 8081.
24/08/22 13:00:57 INFO WorkerWebUI: Bound WorkerWebUI to 0.0.0.0, and started at http://172.16.80.54:8081
24/08/22 13:00:57 INFO Worker: Connecting to master localhost:7077...
24/08/22 13:00:57 INFO TransportClientFactory: Successfully created connection to localhost/127.0.0.1:7077 after 11 ms (0 ms spent in bootstraps)
24/08/22 13:00:57 INFO Worker: Successfully registered with master spark://localhost:7077
```

![](/images/development/spark/spark-cluster-deploy.png){: .align-center style="max-height: 400px" }
Master 노드에 Worker 노드를 하나 붙인 상태
{: .align-caption .text-center .small .gray }

참고로 Worker 노드 자체의 Spark UI도 있는데, 요건 `http://localhost:8081`에서 접속할 수 있다.

## spark-submit again

이제 Master 노드와 Worker 노드 둘 다 구성을 했으니, 다시 `spark-submit`을 해보자.

```sh
$ spark-submit \
  --master spark://localhost:7077 \
  ./hello-spark.py
```

![](/images/development/spark/spark-submit-on-cluster-mode.png){: .align-center style="max-height: 400px" }
`spark-submit`을 실행 했을 때의 Master 노드의 Spark UI
{: .align-caption .text-center .small .gray }

Spark App이 실행되고, Completed 상태로 넘어간 걸 볼 수 있다. 그리고 Worker 노드에서는 이렇게 나온다.

![](/images/development/spark/spark-worker-view.png){: .align-center style="max-height: 400px" }
`spark-submit`을 실행 했을 때의 Worker 노드의 Spark UI
{: .align-caption .text-center .small .gray }

<br/>

Spark UI를 통해 지표를 살펴보면, 간단한 count 작언인데도 CPU core를 무려 `12 core`나 사용했다! 이것은 `spark-submit`을 할 때, 따로 사용할 Core와 Memory 용량을 지정하지 않았기 때문이다!

```sh
$ spark-submit \
  --master spark://localhost:7077 \
  --total-executor-cores 2 \
  --executor-memory 500m \
  ./hello-spark.py
```

![](/images/development/spark/spark-submit-with-custom-spec.png){: .align-center style="max-height: 400px" }
Core 수와 Memory 수를 조정해 `spark-submit`을 실행한 모습
{: .align-caption .text-center .small .gray }

# Spark Standalone 모드

Master-Worker 노드로 Spark 클러스터를 구축한 이 방식을 "Standalone" 모드라고 한다.

본래 Spark 클러스터는 Master 노드, Worker 노드 뿐만 아니라 "Cluster Manager"라는 컴포넌트가 있어 Spark 클러스터 상태 관리, 리소스 할당, 작업 분배 등의 역할을 수행한다.

- Memory Management
- Fault Recovery
- Task Scheduling

![](https://spark.apache.org/docs/latest/img/cluster-overview.png){: .align-center style="max-height: 400px" }

Standalone의 뜻인 "자립형/독립형"라는 뜻에 맞게 Standalone 모드에서는 Master 노드가 Cluster Manager의 역할까지 수행한다.

Spark 클러스터를 Prod 환경에서 운영할 때는 YARN이나 Mesos를 Cluster Manager로 많이 사용한다고 한다.  spark-on-kubernetes로 클러스터를 운영한다면, Kubernetes가 Cluster Manager의 역할을 수행하기도 한다.

# Client Mode에 대해 좀더 자세히

![](/images/development/spark/spark-client-mode.png){: .align-center style="max-height: 400px" }
Spark Client Mode
{: .align-caption .text-center .small .gray }

지난 포스트에서는 "Local Mode"로 Spark Application을 실행했다면, 이번에는 "Client Mode"로 Spark App을 실행했다. Client 모드에서는 Driver Process가 Client JVM 위에서 실행된다. 그리고 Worker JVM 위에서 Executor Process들이 실행되는 구조다.

## Master and Worker, Driver and Executor

이 부분을 공부하면서 어떨 때는 Master-Worker 또는 Driver-Worker라고 부르고, 또 어떨 때는 Driver-Executor라고 부르는 용어들이 정말 헷갈렸다.

그래서 찾아보니 이렇게 설명하는 곳이 있었다.

- Spark 클러스터 관점에서는
  - Master 노드와 Worker 노드로 구성된다.
  - 각 노드는 물리적 머신의 개념이다.
- Spark의 관점에서는
  - Driver Process와 Executor Progress로 구성된다.
  - Process 대신 Program라고도 말한다.

이때, Driver Process는 반드시 Master 노드에 뜨는 건 아니다. 앞에서 살펴본 Client Mode는 Driver Process가 Client의 JVM에 뜬다. 반면에, 아직 실험해보지 않은 "Cluster Mode"에서는 Driver Process가 클러스터 내부에서 결정된다. (이것도 Master 노드에 반드시 뜨는게 아니라 Worker 노드 중 하나에 Driver Process가 실행되는 경우도 있다는 것 같다. 😵‍💫)


![](/images/development/spark/spark-cluster-mode.png){: .align-center style="max-height: 400px" }
Spark Cluster Mode
{: .align-caption .text-center .small .gray }

위의 Client Mode에서의 그림과 잘 비교해보면, Client JVM인지 Driver JVM인지 표기가 다르게 되어 있다. 아래는 Spark 공식 문서에서 묘사된 Client 모드와 Cluster 모드의 차이점이다.

> Spark Deploy Mode: Distinguishes where the driver process runs. In "cluster" mode, the framework launches the driver inside of the cluster. In "client" mode, the submitter launches the driver outside of the cluster. - [Spark Doc](https://spark.apache.org/docs/latest/cluster-overview.html#glossary)


# 다음에는

아직 "Cluster Mode"를 제대로 살펴보지 못 했다. 실험은 해봤는데, 아래와 같은 오류가 뜨면, Cluster Mode에서는 `pyspark`를 실행시킬 수 없다고 한다.

> Exception in thread "main" org.apache.spark.SparkException: Cluster deploy mode is currently **not supported** for python applications on standalone clusters.

그래서 Cluster Mode는 다음에 scala 실험을 하게 될 때 다시 살펴볼 것!

<br/>

다음 포스트에선 `SparkSession`과 `SparkContext`를 살펴보고 둘을 비교 해보고자 한다.

👉 [Jump into Spark Sessions](/2024/08/21/jump-into-spark-sessions-and-contexts/)
