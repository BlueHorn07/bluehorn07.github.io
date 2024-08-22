---
title: "💻 로컬 맥북에서 Spark 실행하기 - 2편: Cluster Mode"
toc: true
toc_sticky: true
categories: ["Spark"]
excerpt: ""
---

2024년 목표로 Databricks Certification을 취득해보려고 Apache Spark를 "제대로" 공부해보고 있습니다. 🎇
{: .notice--info}

1편에서는 단일 머신에서 실행하는 Local Mode로 Spark 작업을 하는 방법을 살펴보았다. 2편에서는 Cluster Mode로 Spark 클러스터를 구성하고, Spark 작업을 실행하는 방법을 살펴보자.

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

![](https://spark.apache.org/docs/latest/img/cluster-overview.png){: .align-center style="max-height: 400px" }

Standalone의 뜻인 "자립형/독립형"라는 뜻에 맞게 Standalone 모드에서는 Cluster Manager의 역할을 Spark 자체에서 수행한다. 보통 Production 환경에서는 YARN이나 Mesos가 Cluster Manager 역할을 수행하도록 구축한다고 한다. 만약 spark-on-kubernetes라면, Kubernetes가 Cluster Manager의 역할을 수행하기도 한다.
