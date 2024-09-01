---
title: "Spark Speculative Execution"
toc: true
toc_sticky: true
categories: ["Spark"]
excerpt: ""
---

Databricks Certification 취득을 목표로 Apache Spark를 “제대로” 공부해보고 있습니다. 회사에선 Databricks Unity Catalog를 도입하려고 분투하고 있는데요. Spark와 좀 친해질 수 있을까요? 🎇 전체 포스트는 [Development - Spark](/topic/development#apache-spark)에서 확인해실 수 있습니다.
{: .notice--info}

# Spark Speculative Execution이란

본인 회사는 2018년부터 Databricks를 도입해 Spark를 사용하고 있었다. 사용한지 오래 되어서 그런지 Databricks Job들에 정말 다양한 Spark Config들이 세팅 되어 있었다. 요즘 회사에서 사용하는 Spark Config를 정리해서 발표를 준비하고 있는데, `spark.speculation`라는 config가 내 눈길을 끌게 되었고, 그렇게 오늘의 주제인 Speculative Execution에 대해 살펴보게 되었다.

Speculative Execution 기능은 Spark `0.6.0`부터 도입된 유서 깊은 기능이다. `spark.speculation`라는 Config로 제어하는데, 설명은 아래와 같다.

![](/images/development/spark/spark-speculative-execution.png){: .align-center style="max-height: 400px" }
[Databricks: Best Practices for Enabling Speculative Execution on Large Scale Platforms](https://youtu.be/MIyQPz_R168?si=yGzbKIASEyG0WEfl)
{: .align-caption .text-center .small .gray }

> If set to "true", performs speculative execution of tasks. This means if one or more tasks are running slowly in a stage, they will be re-launched.	

이 기능은 Spark에서 실행하는 일부 Task가 비정상적으로 느리게 실행되고 있을 때, 그것을 인식하고 해당 Task를 다른 Worker 노드에서 추가로 실행해보는 기법이다. *speculative*라고 이름 붙은 이유는 Task가 정상적으로 완료되지 않을 가능성을 추정(speculate)해 대비한기 떄문이라고 한다.


만약 다른 노드에 복제된 작업이 느리게 실행되던 기존 작업보다 먼저 끝나게 된다면, 그 작업의 결과를 사용하고 기존 작업은 취소(kill) 시킨다.

# Spark Configuration

요 기능은 기본적으로 꺼져있고, 기능을 켜고 싶다면 `spark.speculation = true`로 설정하면 된다.

Speculation에서 작업을 복제할지 말지 여부 판단은 `spark.speculation.interval`에 명시한 주기로 일어나며, 기본값은 `100ms`로 되어 있다. 이때, `spark.speculation.minTaskRuntime` 값(default: 100ms)보다 오래 실행되는 작업이 Speculation 대상이 된다. 또, `spark.speculation.quantile	` 값(default: 0.75)에 의해 해당 Task가 실행되는 Stage의 Task들이 일정 비율 이상 실행 완료 되어야 Speculation 작업이 트리거 되기 시작한다.

Speculation 검사가 실행되더라도, 작업이 충분히 느려야 Speculative Execution이 트리거 된다. 그 값은 `spark.speculation.multiplier`로 판단하며, 해당 Task가 실행되는 Stage에서 함께 실행되는 작업들의 평균 작업 완료 시간을 기준으로 몇 배(default: 1.5) 이상 걸리면 느린 작업이라고 판단하고, 작업을 복제한다.

# 맺음말

요 기능은 본래 Hadoop에서 유래한 기능이다. 하둡에서도 같은 이름인 "Speculative Execution"라고 불렀다. 대규모 분산 시스템에서 작업이 지연되는 문제를 해결하는 중요한 기법이라고 한다.

다만, Spark에서 제공하는 Default Config의 값이 실제 환경에서 사용하기에는 괴리가 있어서 Linkedin의 경우는 아래와 같이 값을 커스텀하여 사용하고 있다고 한다.

![](/images/development/spark/spark-speculative-execution-linkedin.png){: .align-center style="max-height: 300px" }
[Databricks: Best Practices for Enabling Speculative Execution on Large Scale Platforms](https://youtu.be/MIyQPz_R168?si=yGzbKIASEyG0WEfl)
{: .align-caption .text-center .small .gray }


# References

- [Databricks: Understanding speculative execution](https://kb.databricks.com/scala/understanding-speculative-execution)
- [Databricks: Best Practices for Enabling Speculative Execution on Large Scale Platforms](https://youtu.be/MIyQPz_R168?si=yGzbKIASEyG0WEfl)
- [Spark Configuration](https://spark.apache.org/docs/latest/configuration.html)

