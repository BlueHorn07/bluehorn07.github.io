---
title: "Sampling Distribution"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Statistics"]
---
“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

<div class="notice" markdown="1">

**시리즈: Sampling Distributions**

1. [Sampling Distribution](/2021/04/25/sampling-distribution") 👀
2. [Sampling Distribution of Mean](/2021/04/26/sampling-distribution-of-mean-and-clt")
3. [Sampling Distribution of Variance](/2021/04/27/sampling-distribution-of-variance")
4. [Student's t-distribution](/2021/04/27/student-t-distribution")
5. [F-distribution](/2021/05/04/F-distribution")
6. [EDF and Quantile](/2021/05/04/EDF-and-Quantile")

</div>

# Introduction

확통 수업을 듣는 전체 학생을 대상으로, 확통 수업을 선호하는 학생의 비율을 구하고자 한다. 그런데, 확통 수업을 듣는 학생 수가 너무 많아서 전체를 조사할 순 없고, 전체 중 $n$명 학생을 대상으로 설문조사를 시행한다고 하자.

$X$가 "$n$명의 학생 중에 확통 수업을 선호한다고 응답한 학생 수"라는 RV라면, $X$는 [HyperGeometric Distribution](/2021/03/24/hypergeometric-distribution)를 따를 것이다. 만약 전체 학생 수가 충~분히 크다면, HyperGeometric 분포를 Binomial 분포로 근사할 수도 있을 것이다.

각 학생 $i$의 선호를 RV $X_i$는 Binary 값을 가진다.

$$
X_i = \begin{cases}
    1 & i\text{-th student likes it!} \\
    0 & \text{else}
\end{cases}
$$

그리고 RV $X_1, \dots, X_n$를 전체를 종합하면, 새로운 RV $\overline{X}$를 유도할 수 있다.

$$
\overline{X} := \frac{X_1 + \cdots X_n}{n}
$$

이렇게 유도한 $\overline{X}$를 \<**sample mean**\>이라고 한다!

<br/>

위의 예시를 좀더 구체화 해서 생각해보자.

$n=100$, and 60 students said they like lecture. Then, $\overline{x} = \frac{60}{100} = 0.6$

이때, 우리가 \<sample mean\> $\overline{x}$에 대해 논하고자 하는 주제는 바로

$$
P(\left| \overline{X} - 0.6 \right| < \epsilon)
$$

의 확률을 어떻게 구하는지에 대한 것이다. 이것을 구하는 이유는

$$
P(\left| \overline{X} - \mu_0 \right| < \epsilon)
$$

의 확률을 구하여, 제시한 $\mu_0$와 우리가 얻은 sample mean이 얼마나 차이 나는지를 확인하고, 이것을 활용해 $\mu = \mu_0$라는 가설(Hypothesis)를 검정(Test)할 수 있기 때문이다. 이 내용은 뒤의 [\<가설 검정; Hypothesis Test\>](/2021/05/18/introduction-to-hypothesis-tests) 부분에서 자세히 다룬다.

$P(\left\| \overline{X} - \mu_0 \right\| < \epsilon)$, 이것을 구하기 위해서는 $\overline{X}$에 대한 분포를 알아야 하며, 우리는 이것을 \<**sampling distribution**; 표본 분포\>라고 한다! 표본 분포에 대한 정의는 아티클의 맨 마지막에 정리하였다.

# Population and Sample

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> population; 모집단<br>

A \<population\> is the totality of observations.

</div>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> sample; 표본<br>

A \<sample\> is a subset of population.

</div>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> random sample<br>

RVs $X_1, \dots, X_n$ are said to be a \<random sample\> of size $n$, <span class="half_HL">if they are **independent and identically distributed** as pmf or pdf $f(x)$</span>.

That is,

$$
f_{(X_1, \dots, X_n)} (x_1, \dots, x_n) = f_{X_1} (x_1) \cdots f_{X_n} (x_n)
$$

The observed values $x_1, \dots, x_n$ of $X_1, \dots, X_n$ are called \<**sample points**\> or \<**observations**\>.

</div>

# Statistics

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Statistics; 통계량<br>

A \<**Statistics; 통계량**\> is a function of a random sample $X_1, \dots, X_n$, <span class="half_HL">not depending on unknown parameters</span>.

</div>

즉, $f(X_1, \dots, X_n)$ 형태의 함수를 \<Statistics\>라고 한다. 이 \<Statistics\>는 RV 집합의 대표값 역할을 한다.

<br/>

<span class="statement-title">Example.</span><br>

Supp. $X_1, \dots, X_n$ is a random sample from $N(\mu, 1)$.

Then,

1\. $\dfrac{X_1 + \cdots + X_n}{n}$ is a <u>Statistics</u>!

2\. $\max \\{ X_1, \dots, X_n \\}$ is a <u>Statistics</u>!

3\. $\dfrac{X_1 + \cdots + X_n + \mu}{n}$ is <u>not a Statistics</u>!

우리는 개별 샘플값 $X_i = x_i$가 아니라, 통계량 \<Statistics\>을 통해서만 모집단에 대한 각종 성질을 추론할 수 있다.

# Location Measures of a Sample

Let $X_1, \dots, X_n$ be a random sample.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> sample mean<br>

$\overline{X} = \dfrac{X_1 + \cdots + X_n}{n}$ is called a \<sample mean\>.

</div>

(1) $\overline{X}$ is also a random variable!

(2) If $E(X_1) = \mu$ and $\text{Var}(X_1) = \sigma^2$, then  <span class="half_HL">$E(\overline{X}) = \dfrac{n\mu}{n} = \mu$ and $\text{Var}(\overline{X}) = \dfrac{\sigma^2}{n}$</span>

(3) $\overline{X}$ can be sensitive to outliers.


<br/>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> sample median<br>

Sample에서의 중간값.

</div>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> sample mode<br>

Sample에서의 최빈값.

</div>

# Variability Measures of a Sample

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> sample variance<br>

Let $X_1, \dots, X_n$ be a random sample with $E[X_i] = \mu$ and $\text{Var}(X_i) = \sigma^2$.

$$
S^2 := \frac{1}{n-1} \sum^n_{i=1} \left( X_i - \overline{X}\right)^2
$$

</div>

## Why divide by (n-1)?

Q. Why $(n-1)$ in the bottom??

A. 왜냐하면,  <span class="half_HL">$(n-1)$로 나눠줘야 표본 분산의 평균 $E[S^2]$이 $\sigma^2$이 되기 때문!!!</span>

<div class="proof" markdown="1">

<span class="statement-title">*Proof*.</span><br>

w.l.o.g. we can assume that $E[X_i] = 0$. (그냥 편의를 위해 $X_i$를 적당히 표준화 한 것이다.)

$$
\begin{aligned}
S^2
&= \frac{1}{n-1} \sum^n_{i=1} \left( X_i^2 - 2 X_i \overline{X} + (\overline{X})^2 \right) \\
&= \frac{1}{n-1} \left\{ \sum^n_{i=1} X_i^2 - 2 \overline{X} \sum^n_{i=1} X_i + n (\overline{X})^2 \right\} \\
\end{aligned}
$$

이때, $\displaystyle\sum^n_{i=1} X_i$는 그 정의에 의해 $n\overline{X}$가 된다.

$$
\begin{aligned}
S^2
&= \frac{1}{n-1} \left\{
\sum^n_{i=1} X_i^2 - 2 \overline{X} \cdot n\overline{X} + n (\overline{X})^2
\right\} \\
&= \frac{1}{n-1} \left\{
\sum^n_{i=1} X_i^2 - n (\overline{X})^2
\right\} \\
\end{aligned}
$$

이제 위의 식의 양변에 평균을 취해보자.

$$
\begin{aligned}
E[S^2]
&= \frac{1}{n-1} \left\{ \sum^n_{i=1} E[X_i^2] - n \cdot E\left[(\overline{X})^2\right] \right\} \\
&= \frac{1}{n-1} \left\{ \sum^n_{i=1} (\sigma^2 + \cancelto{0}{E[X_i]^2}) - n \cdot E\left[(\overline{X})^2\right] \right\} \\
&= \frac{1}{n-1} \left\{ n \cdot \sigma^2 - n \cdot \frac{1}{n^2} \cdot E \left[(X_1 + \cdots + X_n)^2 \right] \right\} \\
&= \frac{1}{n-1} \left\{ n \cdot \sigma^2 - \frac{1}{n} \cdot \left( n \cdot E[X_1^2] + E[X_i X_j] + \cdots \right) \right\} \\
\end{aligned}
$$

이때, $X_i$는 서로 독립이므로 $E[X_i X_j] = E[X_i] E[X_j] = 0 \cdot 0 = 0$이 됩니다.

$$
\begin{aligned}
E[S^2]
&= \frac{1}{n-1} \left\{ n \cdot \sigma^2 - \frac{1}{n} \cdot \left( n \cdot E[X_1^2] + \cancelto{0}{E[X_i X_j]} + \cdots \right) \right\} \\
&= \frac{1}{n-1} \left\{ n \cdot \sigma^2 - \frac{1}{\cancel{n}} \cdot \cancel{n} \cdot \cancelto{\sigma^2}{E[X_1^2]} \right\} \\
&= \frac{1}{n-1} \left\{ n \cdot \sigma^2 - \sigma^2 \right\} \\
&= \sigma^2
\end{aligned}
$$

$\blacksquare$

</div>

<br/>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> sample standard deviation<br>

$$
S := \sqrt{S^2} = \sqrt{\frac{1}{n-1} \sum^n_{i=1}\left( X_i - \overline{X} \right)^2}
$$

</div>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> range<br>

$$
R := \max_{1 \le i \le n} X_i - \min_{1 \le i \le n} X_i
$$

</div>

# Sampling Distribution

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> sampling distribution<br>

The <span class="half_HL">probability distribution of a sample Statistics</span> is called a \<sampling distribution\>.

ex) distribution of sample mean, distribution of sample variance, ...

</div>

이때, 표본 통계량(sample Statistics)는 sample mean, sample variance와 같이 표본의 특성을 나타내는 대표값이다. 자세한 내용은 아래의 두 포스트를 참고하자!

- [Sampling Distribution of Mean, and CLT](/2021/04/26/sampling-distribution-of-mean-and-clt")
- [Sampling Distribution of Variance](/2021/04/27/sampling-distribution-of-variance")


진행했던 프로젝트 두 가지에 대하여 활용 기술, 본인의 역할, 진행 방식, 결과를 포함하여 구체적으로 설명해 주세요.

[Events 파이프라인 운영 및 개선]
하루 4억 건, 500GB의 이벤트 트래픽을 유실 없이 적재할 수 있는 파이프라인을 구성하고 운영한 경험이 있습니다. 초기에는 파이프라인 유지보수 담당으로 시작했지만, 현재는 Data Platform 유닛 리더로서 이벤트 파이프라인을 포함해 데이터팀의 AWS 및 Kubernetes 인프라 전반의 방향성과 개선을 주도하고 있습니다.

이벤트 파이프라인은 인게임에서 발생하는 모든 이벤트를 로깅하고 이를 데이터 레이크에 적재하는 end-to-end 구조로, 가장 먼저 이벤트를 처리하는 Events Producer는 Istio와 Knative 기반의 Serverless Application으로 구축되어 있습니다. 트래픽 변화에 따라 자동 스케일링이 가능하도록 설계되어 있으며, 이를 통해 대량 트래픽에도 유실 없이 데이터를 안정적으로 수집할 수 있습니다. 이벤트는 Kafka 토픽에 적재되는데, 초기에는 단일 토픽에 모든 이벤트를 저장하는 방식이었으나, 이로 인해 Sink Connector의 Lag 모니터링이 어렵고, Events ETL 구성에 블로커가 발생하는 문제가 있었습니다. 이를 해결하기 위해 이벤트 유형별 전용 Kafka 토픽을 분리하는 로직을 개발하여 단일 토픽 부하를 50% 이상 분산하였고, Flink 기반의 Events ETL 개발이 가능하도록 환경을 조성하였습니다.

또한, Prometheus를 연동하여 파이프라인 장애 발생 시 상황별 알람이 오도록 설정하고, 분당 더미 데이터를 자동 전송하여 파이프라인의 상태를 지속적으로 체크할 수 있도록 개선하였습니다. 더 나아가, Custom Prometheus 지표를 정의하여 Producer별 Success/Fail/DLQ 메시지의 추이를 실시간으로 모니터링할 수 있도록 구성함으로써 유관팀이 장애 상황을 보다 명확하게 파악하고 대응할 수 있도록 지원하였습니다.

배포 프로세스 또한 기존 Pulumi 기반 IaC 방식을 유지하되, PR 리뷰 과정에서 불필요한 클릭을 여러 번 수행해야 하는 문제를 발견하였고, 이를 해결하기 위해 Atlantis 기능을 모사한 PR Comment 기반 자동화된 Github Workflow를 개발하여 배포 프로세스를 단순화했습니다.

이벤트 원천 데이터는 게임 운영과 분석에 중요한 역할을 하기 때문에, 파이프라인의 안정적인 운영과 장애 대응이 필수적이었습니다. 이를 위해 촘촘한 모니터링 체계를 구축하고, 쉬운 배포 프로세스를 마련하였으며, 팀원들에게 Istio 및 Kubernetes 인프라에 대한 세션을 진행하여 협력하여 안전하고 빠르게 배포할 수 있는 환경을 조성하였습니다. 그 결과, Kafka 부하를 효과적으로 분산시키며 Sink Connector의 Lag 문제를 해결할 수 있었고, 실시간 모니터링 및 자동화된 상태 체크 시스템을 통해 장애 대응 속도를 크게 향상시킬 수 있었습니다.

[메타스토어 설계 및 마이그레이션]
Hive Metastore에서 Databricks Unity Catalog로 1만 개 이상의 테이블과 1천 개 이상의 워크플로우를 마이그레이션하며 데이터 레이크하우스 성능을 최적화하고 팀의 생산성을 향상시킨 경험이 있습니다. 마이그레이션 TF 팀장으로서 2024년 3월부터 8월까지 프로젝트를 주도하며 전체 과정 설계와 디버깅을 담당했습니다.

마이그레이션을 진행하기에 앞서, 평가 도구 및 커스텀 쿼리를 활용하여 기존 Hive Metastore와 Unity Catalog 간의 호환성을 분석하였고, 새로운 Catalog 구조를 설계하여 데이터 접근성과 관리 효율성을 개선하였습니다. 데이터팀은 2018년부터 Spark과 Databricks를 사용해왔으며, 그동안 쌓인 Notebook, Workflow, ACL 등의 레거시를 정리하고 최신 환경에 맞게 최적화하는 과정이 필요했습니다. GitOps 기반으로 관리되는 Notebook에서는 Spark Context를 활용한 레거시 API 및 Databricks Legacy API를 최신 API로 전환하였고, Spark UDF를 재설계하고 Partitioned 메타데이터 구성을 변경하여 Shared Cluster에서 발생하는 Spark Session 충돌 문제를 해결하였습니다.

쿼리 성능을 최적화하기 위해 Parquet에서 Delta Lake로 데이터 포맷을 변경하고 증분 업데이트 방식을 도입하여 S3 요청을 기존의 10% 수준으로 줄였습니다. 이 과정에서 파이프라인 장애도 여러 차례 발생하였고, 유관 부서에서 마이그레이션으로 인한 불편을 호소하기도 했습니다. 그러나 마이그레이션 전후의 성능 비교 자료를 제공하고, Auditing 및 Lineage 기능을 활용하여 데이터 활용도를 높일 수 있다는 점을 강조하며 적극적으로 커뮤니케이션을 진행했습니다.

결과적으로, 모든 메타데이터를 Unity Catalog 환경에서 일원화하여 관리할 수 있게 되었으며, 쿼리 속도 개선 및 리소스 절감 효과를 거둘 수 있었습니다. 또한, LLM Agent 개발, Audit Log 기반 모니터링 등 새로운 데이터 활용 영역에 도전할 수 있는 기반이 마련되었습니다. 프로젝트 과정에서 반복적인 작업과 장애 대응으로 인한 부담이 컸지만, 이를 통해 Spark에 대한 깊은 이해와 성능 최적화 및 장애 대응 역량을 확보할 수 있었습니다.


기억에 남는 트러블슈팅 경험을 가능한 상세하게 설명해 주세요.

Datahub를 통한 사내 Data Discovery Platform(DDP) 구축이 가장 기억에 남습니다. 베이글코드에서 Data Scientist로 근무할 때, 유관 부서로부터 테이블과 컬럼에 대한 메타정보와 데이터추출에 대한 요청이 빈번 했습니다. 이를 해하 위해 비개발 직군을 대상으로 "Query101" 세션을 진행하며 데이터 추출에 대한 방법과 Superset, Admunsen, 인하우스 스키마 페이지 등 도구를 소개했지만, 여전히 비개발팀이 직접 데이터 추출하는 것은 어려운 일이었습니다.

Data Engineer로 부서를 이동한 후에도 같은 문제가 지속되었고, 이에 첫 번째 프로젝트로 기존 Amundsen을 대체할 새로운 Data Discovery Platform(DDP) 구축을 진행하게 되었습니다. 오픈소스인 Datahub를 활용하여 데이터 검색과 메타데이터 관리를 보다 체계적으로 운영하고자 했으며, 주요 과제로 워크로드 배포 및 메타데이터 반영 자동화를 설정하였습니다.

초기 워크로드 배포 과정에서 개발용 클러스터에서 익명의 사용자가 Admin 계정을 알아내는 보안 이슈가 발생했습니다. 다행히 운영 환경과 연결되지 않은 개발 클러스터였기에 즉시 비밀번호를 변경하여 대응할 수 있었습니다. 디버깅을 진행한 결과, Datahub의 Helm Chart에서 Admin 계정의 비밀번호가 고정값으로 설정되어 있다는 문제를 발견했습니다.
이 문제는 저뿐만 아니라 Datahub를 사용하는 모든 유저들이 겪을 수 있는 보안 취약점이라 판단했고, 즉시 GitHub에 Issue를 생성한 후 직접 PR을 작성하여 문제를 해결했습니다. 해당 개선 사항은 Datahub 프로젝트에 반영되었고, 이를 통해 대규모 오픈소스에 대한 컨트리뷰션을 처음으로 경험할 수 있었습니다.

메타데이터 반영 자동화 과정에서는 Databricks 테이블 목록과 메타데이터는 정상적으로 수집되지만, 테이블 간 의존성이 보이지 않는 문제가 있었습니다. 데이터 분석 및 ETL 과정에서 테이블 간 관계를 파악하는 것이 중요한데, 이를 해결하지 않으면 데이터 흐름을 추적하기 어려웠습니다.
이에 Spark Query Plan을 파싱하여 테이블 간의 의존성을 별도로 저장하고, 이를 메타데이터 플랫폼에 반영하는 방식을 도입했습니다. 이 개선을 통해 테이블 간 리니지를 시각적으로 확인할 수 있게 되었고, 장애 발생 시 팀원들이 이를 기반으로 백필을 계획하고 실행할 수 있도록 지원하였습니다. 추가적으로 Airflow-Datahub 연동을 구현하여 Databricks 테이블과 Airflow Task 사이의 관계를 연결함으로써, 데이터 및 워크플로우 간의 연결성을 한눈에 파악할 수 있는 기능을 확장하였습니다.

프로젝트를 통해 데이터팀뿐만 아니라 비개발 직군까지 포함한 전사적인 데이터 가시성을 향상시킬 수 있었습니다. 또한, 기술적 문제를 해결하는 과정에서 보안 개선, 오픈소스 기여, 데이터 엔지니어링 역량을 키울 수 있었던 점이 큰 의미가 있었습니다.

