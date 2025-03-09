---
title: "Exponential Distribution"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---
“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

<div class="notice" markdown="1">

**시리즈: Continuous Probability Distributions**

1. [Uniform Distribution]({{"/2021/03/29/uniform-distribution" | relative_url}})
2. [Normal Distribution]({{"/2021/03/30/normal-distribution" | relative_url}})
3. [Exponential Distribution]({{"/2021/03/31/exponential-distribution" | relative_url}}) 👀
4. [Gamma Distribution]({{"/2021/04/05/gamma-distribution" | relative_url}})
5. [Chi-square Distribution]({{"/2021/04/06/chi-square-distribution" | relative_url}})
6. [Beta Distribution]({{"/2021/04/07/beta-distribution" | relative_url}})
7. [Log-normal Distribution]({{"/2021/04/08/log-normal-distribution" | relative_url}})
8. [Weibull Distribution (optional)]({{"/2021/04/10/weibull-distribution" | relative_url}})

</div>

# 들어가며

어떤 사건이 평균적으로 “5분에 1번” 발생한다고 가정해보자. 예를 들어, 출퇴근 시간에 2호선 지하철이 평균적으로 5분마다 한 대씩 도착한다고 생각할 수 있다.

블루혼은 선릉역까지 출근하기 위해 사당역에서 2호선 지하철을 기다리고 있습니다. 수많은 출퇴근 경험에 의해 블루혼은 이 시간대에 평균적으로 5분 정도 기다리면 지하철이 온다는 것을 알고 있습니다. 어떨 때는 2호선을 눈앞에서 놓쳐도 다른 다음 열차가 들어와서 3분도 안 기다릴 때가 있지만, 어떨 때는 2호선이 하염없이 오래 걸릴 때도 있습니다.

평균적인 대기 시간을 알고 있을 때, 블루혼이 2호선 지하철을 기다리기 위해 쓰는 시간은 "지수 분포"라는 연속 확률 분포를 따릅니다!

# Distribution for waiting time

다음에 들어올 2호선을 기다리기 위해 걸리는 시간에 대한 분포에 대한 식을 살펴봅시다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Exponential Distribution (waiting time)<br>

Let $\beta >0$ is an average waiting time, and we say that $X$ has an \<**exponential distribution**\> with parameter $\beta$ if it has pdf $f(x)$ as

$$
f(x) =
\begin{cases}
\frac{1}{\beta} e^{- \frac{1}{\beta} x} & \text{for} \; x > 0\\
\quad 0 & \text{else}
\end{cases}
$$

and we denote such RV $X$ as $X \sim \text{EXP}(\beta)$.

</div>

## Expectation and Variance

$\beta$를 평균 대기 시간(average waiting time)으로 정의했으므로, 확률 변수 $X$의 기댓값(즉, 평균)은 $\beta$와 같습니다. 즉,

$$
E[X] = \beta
$$

분산의 경우는 지수 확률 분포에 대한 식을 잘 정리하면 아래의 결과를 얻을 수 있습니다.

$$
\text{Var}(X) = \beta^2
$$

이때 $\beta$는 사건이 발생하기 위해 평균적으로 대기 하는 시간을 의미 합니다. "5분에 1건씩" 발생하는 경우라면, $\beta = 5$가 되고, 분포는 아래와 같습니다.

$$
P(X = x) = \frac{1}{5} e^{-\frac{1}{5} x}
$$


## Distribution for event rate

블혼은 출퇴근 시간에 지하철역에서 다음 열차를 기다리는 동안, 전광판을 보면서 "1시간 동안 열차가 몇 대나 지나갔을까?"하는 궁금증이 생겼습니다. 대기 시간에 대판 분포를 살펴봤던 것처럼, 지하철이 평균 5분대 1대씩 도착한다고 가정하면, 1시간 동안 도착하는 열차의 갯수는 12대가 될 것 입니다.

그러나 어떤 날은 스크린도어 고장으로 열차가 지연 되어 1시간 동안 10대가 올 수 있고, 어떤 날은 운행 문제가 없어서 1시간에 15대가 올 수도 있습니다.

블루혼은 일정한 시간 동안 열차가 몇 대 도착하는지를 확률적으로 모델링하고 싶어졌습니다. 이러한 사건 발생 횟수(count)를 따르는 확률 분포가 바로 “**푸아송 분포**”입니다. 푸아송 분포에 대해서는 [별도 포스트](/2021/03/25/poisson-distribution/)에 정리한 것도 있습니다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Poisson Distribution (event rate)<br>

Let $\lambda >0$ is an event occurring rate, and we say that $X$ has an \<**Poisson distribution**\> with parameter $\lambda$ if it has pdf $f(x)$ as

$$
f(x) = \frac{\lambda^x \cdot e^{-\lambda}}{x!}
$$

for $x = 0, 1, ...$ and we denote such RV $X$ as $X \sim \text{POI}(\lambda)$.

</div>

예시의 상황을 가져와서 함수를 모델링 하면, 5분당 1대의 열차가 들어온다면 1분당 0.2대의 열차가 들어오는 것과 같습니다. 즉, $\lambda = 0.2$ 이것을 포아송 분포의 함수로 적으면

$$
P(X = x) = \frac{(0.2)^x \cdot e^{-0.2}}{x!}
$$

와 같습니다.

# Duality with Poisson Distribution

처음에 Waiting Time에 대한 분포를 떠올리며 지수 분포 $\text{EXP}(\beta)$를 떠올렸고, 단위 시간당 이벤트 발생 횟수(Event Rate)에 대한 분포를 떠올리며 푸아송 분포 $\text{POI}(\lambda)$를 떠올렸습니다. 그런데, 이 두 분포는 서로 쌍대성(Duality)를 갖고 있습니다.

평균 대기 시간 $\beta$는 사건이 한 번 발생하기까지 걸리는 평균 시간을 의미합니다. 예를 들어, 5분에 한 대씩 지하철이 도착한다면, $\beta = 5$입니다. 이를 “단위 시간당 평균 발생 횟수(event rate)“로 변환하면, 1분당 평균 0.2대의 열차가 도착하게 됩니다. 즉, $\lambda = 0.2$이며, 이는 $\lambda = \frac{1}{\beta}$로 나타낼 수 있습니다.


$$
\beta = \frac{1}{\lambda} \quad \text{and} \quad \frac{1}{\beta} = \lambda
$$

이것은 $\beta$와 $\lambda$ 둘중 하나만 알면 다른 파라미터의 값은 자연스럽게 알 수 있다는 것이죠.

그래서 지수 분포를 아래와 같이 정의할 수도 있습니다.

$$
\text{EXP}(1/\lambda): P(X = x) = \lambda \cdot e^{-\lambda x}
$$

그리고 푸아송 분포도 이렇게 정의할 수도 있습니다.

$$
\text{POI}(1/\beta): P(X = x) = \frac{(1/\beta)^x \cdot e^{-1/\beta}}{x!}
$$

보면 $\text{EXP}(1/\lambda)$는 분수식 표현 $\frac{1}{\beta}$가 없어지면서 식이 꽤 깔끔해졌는데, $\text{POI}(1/\beta)$는 식이 아주 괴랄해졌습니다. 그래서 수업 때 교수님은 $\text{EXP}(\lambda)$으로 표기하기도 하셨는데요. $\lambda$가 Event Rate에 대한 것이다라는 명확한 합의와 표기만 있다면 이렇게 써도 될 것 같습니다.

다만, "Exponential Distribution은 대기 시간에 대한 분포다!"라는 사실은 직관적으로 받아들이고 싶어서 $\text{EXP}(\beta)$를 표준으로 쓰고, $\lambda$가 주어진 경우라면 $\text{EXP}(\beta = 1/\lambda)$ 이렇게 적게 될 것 같습니다.

## Relationship with Poisson Process

푸아송 과정(Poisson Process) $\{ N(t) \}$에서 $t$시간 동안 발생한 총 사건의 개수 $N(t)$는 포아송 분포를 따릅니다. 즉,

$$
N(t) \sim \text{POI}(\lambda t)
$$

여기서 $\lambda$는 단위 시간당 평균 사건 발생 횟수이며, 따라서 $t$시간 동안의 총 발생 횟수는 평균적으로 $\lambda t$가 됩니다. 푸아송 과정에 대해서는 [별도 포스트](/2021/03/25/poisson-distribution)에서 자세히 정리한 것이 있습니다.

### 첫 번째 사건이 발생할 때까지 걸린 시간

푸아송 과정에서 *“어떤 사건이 처음 발생하기까지 걸리는 시간”*을 생각해 봅시다. 우리는 사건이 발생하는 정확한 시간을 알 수 없으며, 사건은 확률적으로 일어나므로, 이 시간을 확률 변수(Random Variable) $T$로 정의합시다.

이제 *“첫 번째 사건이 $t$시간 이후에 발생할 확률”*을 구해 봅시다. 첫 번째 사건이 $t$ 이후에 발생하려면, 즉 $T > t$이려면, $t$시간 동안 아무런 사건도 발생하지 않은 상태여야 합니다. 이는 곧 푸아송 과정에서 $t$시간 동안 사건의 개수가 0개라는 의미이므로, 다음과 같이 표현할 수 있습니다.

$$
P(T > t) = P(N(t) = 0)
$$


우리는 아직 확률 변수 $T$가 어떤 분포를 따르는지 모릅니다. 하지만 $P(T > t)$가 푸아송 과정에서 $P(N(t) = 0)$과 같다는 것을 알고 있으며, 푸아송 분포의 확률 질량 함수(PMF)는 다음과 같습니다.

$$
P(N(t) = k) = e^{-\lambda t} \frac{(\lambda t)^k}{k!}
$$

여기서 $k = 0$을 대입하면,

$$
P(N(t) = 0) = e^{-\lambda t} \frac{(\lambda t)^0}{0!} = e^{-\lambda t}
$$

따라서,

$$
P(T > t) = e^{-\lambda t}
$$

즉, 첫 번째 사건이 $t$ 이후에 발생할 확률은 $e^{-\lambda t}$로 나타낼 수 있습니다.

위에서 구한 $P(T > t)$는 확률 변수 $T$의 **꼬리 확률(Tail Probability)**입니다. 이를 **누적 분포 함수(CDF)**의 형태로 변환하면,

$$
P(T \le t) = 1 - P(T > t) = 1 - e^{-\lambda t}
$$

이제 CDF를 미분하면 확률 밀도 함수(PDF)를 얻을 수 있습니다.

$$
f_T(t) = \frac{d}{dt} P(T \le t) = \frac{d}{dt} (1 - e^{-\lambda t}) = \lambda e^{-\lambda t}
$$

👀 익숙한 형태 아닌가요?

바로 **지수 분포(Exponential Distribution)**의 확률 밀도 함수(PDF)입니다! 🎉

$$
T \sim \text{EXP}(\beta = 1/\lambda) \quad \Rightarrow \quad f_T(t) = \lambda e^{-\lambda t}
$$

즉, **지수 분포는 어떤 사건이 처음 발생할 때까지 걸리는 시간**에 대한 확률 분포라고 할 수 있습니다! 😎


# Memoryless Property

어떤 확률 분포가 **Memoryless Property**(기억 없음 성질)를 가진다면, 다음 조건을 만족합니다.

$$
P(X > a + t \mid X > a) = P(X > t)
$$

이는 **"이미 $a$만큼 시간이 지났더라도 남은 시간 $t$ 동안 사건이 발생할 확률은 처음부터 기다릴 때와 동일하다"**는 의미입니다. 즉, 현재까지 얼마나 기다렸는지와 관계없이 남은 대기 시간이 동일한 확률 분포를 따른다는 특징을 가집니다.

지수 분포(Exponential Distribution)가 위의 **Memoryless Property**를 만족하는지 확인해 봅시다.

$$
\begin{aligned}
P(X > a + t \mid X > a) &= \frac{P(X > a + t)}{P(X > a)} \\
                        &= \frac{e^{-\lambda (a+t)x}}{e^{-\lambda a x}} \\
                        &= e^{-\lambda tx} = P(X > t)
\end{aligned}
$$

따라서, **지수 분포(Exponential Distribution)는 Memoryless Property를 가집니다!** 🚀

## Relationship with Geometric Distribution

우리는 앞서 어떤 사건이 처음으로 발생하는 시행 횟수 $X$를 모델링한 **[Geometric Distribution](/2021/03/24/discrete-probability-distributions-2/#geometric-distribution)**을 살펴본 적이 있습니다.

흥미롭게도, 기하 분포 역시 Memoryless Property를 만족합니다. 즉, 이미 몇 번의 시행이 진행되었든 상관없이, 앞으로 사건이 처음 발생하기까지 걸리는 시행 횟수의 확률은 항상 동일합니다.

놀랍게도, 지수 분포 또한 같은 Memoryless Property를 가지며, 기하 분포에서 이를 유도할 수도 있습니다. 이제 이를 살펴보도록 하겠습니다. 🚀

<details class="proof" markdown="1">
<summary>📂 Geometric Distribution에서 Exponential Distribution 유도 (펼쳐보기)</summary>

확률 변수 $X_n$을 *"각 시행 간격이 $1/n$초일 때, 버스가 처음 도착할 때까지 걸린 시행 횟수"*라고 정의합시다.
즉, 매 $1/n$초마다 버스가 도착했는지 확인하며, $X_n$은 처음으로 버스를 발견하기까지의 시행 횟수를 나타냅니다. 또한, $X$를 *"버스가 처음 도착할 때까지 걸린 실제 시간"*이라고 정의합니다.

즉, $X_n$이 한 번 증가할 때마다 실제 시간 $X$는 $1/n$초씩 증가하므로,

$$
X = \frac{X_n}{n}
$$

가 됩니다. 이를 비례식으로 표현하면 다음과 같습니다.

$$
X_n : X = 1 : \frac{1}{n}
$$

즉, **$X_n$은 시행 횟수를 나타내는 이산 확률 변수이며, $X$는 연속적인 시간 변수입니다.**
이제 $X_n$이 어떤 확률 분포를 따르는지 살펴봅시다.

<br/>

$X_n$는 **기하 분포(Geometric Distribution)**를 따르는 확률 변수 입니다.

$$
X_n \sim \text{Geo}(p_n)
$$

여기서 $p_n$은 매 시행(즉, $1/n$초마다) 버스가 도착할 확률을 의미합니다.
이때, **$n$을 충분히 크게 만들면, 즉 시행 간격을 매우 짧게 하면, 이산적인 기하 분포가 연속적인 지수 분포로 수렴할 것**임을 기대할 수 있습니다.

이제 이 과정을 수식적으로 정리해 봅시다.

- $X_n$이 기하 분포를 따른다면, $X_n \sim \text{Geo}(p)$.
- 기댓값은 $E[X_n] = 1/p$이므로, 최초의 버스가 도착하는 걸 확인하기 위해 평균적으로 $1/p$번 확인을 합니다.
- 이를 시간 $X$의 관점에서 보면, 평균적으로 $\frac{1}{p} \cdot \frac{1}{n} = 1/(np)$초가 걸립니다.

즉,

$$
\beta = \frac{1}{np}, \quad \lambda = np
$$

따라서,

$$
X_n \sim \text{Geo}\left( \frac{\lambda}{n} \right)
$$

이제 $X$의 tail probability $P(X > x)$를 유도해 봅시다.

$$
P(X > x) = P\left(\frac{X_n}{n} > x\right)
$$

이것은 맨 처음에 세운 비례식에 의해 성립합니다.

$$
\begin{aligned}
    P(X > x) &= P\left(\frac{X_n}{n} > x\right) \\
            &= P(X_n > nx) \\
            &= \left( 1 - \frac{\lambda}{n}\right)^{nx} \\
            &= e^{-\lambda x} \quad \text{as } n \rightarrow \infty
\end{aligned}
$$

Tail Probability $P(X > x)$에서 CDF $P(X \le x)$를 유도하면 아래와 같습니다.

$$
P(X \le x) = 1 - P(X > x) = 1 - e^{-\lambda x}
$$

CDF를 미분하면 PDF를 얻을 수 있습니다.

$$
f_X(x) = \lambda \cdot e^{-\lambda x}
$$

즉, **기하 분포에서 극한을 취하면 지수 분포가 유도됩니다!** 🚀

</details>

# Expectation and Variance

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span><br>

Let $X \sim \text{EXP}(\lambda)$, then

- $E[X] = \dfrac{1}{\lambda}$
- $\text{Var}(X) = \dfrac{1}{\lambda^2}$

</div>

분산에 대한 것만

<div class="proof" markdown="1">

<span class="statement-title">*Proof*.</span><br>

$\lambda = \beta = 1$인 표준 지수 분포에 대해 생각해봅시다.

$$
Y \sim \text{EXP}(1)
$$

이 표준 지수 분포의 평균과 분산은 어떻게 될까요?

$$
\begin{aligned}
E[Y] &= \int^{\infty}_0 y \cdot e^{-y} \; dy = 1
\end{aligned}
$$

Variance를 구해보면,

$$
\begin{aligned}
E[Y^2] = \int^{\infty}_0 y^2 \cdot e^{-y} \; dy = 2
\end{aligned}
$$

따라서, $\text{Var}(Y) = E[Y^2] - E[Y]^2 = 2 - 1^2 = 1$.

<br/>

이제, $X \sim \text{EXP}(\beta = 1 / \lambda)$를 살펴 봅시다. 대기 시간이 $\beta$ 만큼 늘어났으므로 $X = \beta \cdot Y = \dfrac{Y}{\lambda}$를 만족합니다. 따라서

$$
E[X]
= E\left[\beta \cdot Y \right]
= E\left[\frac{Y}{\lambda}\right]
= \frac{1}{\lambda}
$$

그리고 분산은

$$
\text{Var}(X)
= \text{Var}\left( \beta \cdot Y \right)
= \text{Var}\left( \frac{Y}{\lambda} \right)
= \frac{1}{\lambda^2}
$$

</div>

# Unit Conversion

If $X \sim \text{EXP}(1)$, then $Y := \dfrac{X}{\lambda} \sim \text{EXP}(\lambda)$.

$$
P(Y > y) = P(\frac{X}{\lambda} > y) = P(X > \lambda y) = e^{-\lambda y}
$$

본인은 위의 상황을 (minute - second) 변환을 바탕으로 이해했다. 만약 $X$가 분 단위에서 처음 도착하는 버스의 시간을 모델링하고, 그 때의 parameter가 $\lambda = 1$라고 하자. 우리는 이것을 초 단위인 $60X$로 변환할 수 있다. 이때의 tail probability는

$$
P(60X > x) = P(X > x/60) = e^{- x/60}
$$

따라서, $60X \sim \text{EXP}(1/60)$이 된다. 이것은 $60X$에서 $\lambda$가 $\lambda = 1/60$이 됨을 의미한다. 이때, $\lambda$는 Poisson Process의 parameter로, Time Unit 당 도착하는 버스의 수를 모델링한다. 따라서 1초 당 평균적으로 1/60 대의 버스가 도착함을 의미한다. 이것을 $\beta = 1 / \lambda$로 해석하면, 버스가 한번 도착하는 시간이 평균적으로 60초가 됨을 의미한다!

# 맺음말

- 어떤 사건의 발생 횟수가 **포아송 분포**를 따른다면, 사건 사이의 대기 시간은 **지수 분포**를 따르게 된다. (또는 첫 사건이 발생하는 데까지 걸리는 시간은 지수 분포를 따른다.)
- $\lambda$는 Unit time 동안 Event가 일어날 평균 **횟수**를 의미한다. 그리고 그 역수인 $\beta$는 한 번의 Event가 발생하는 데 걸리는 평균 **시간**을 의미한다.
- \<Exponential Distribution\>은 \<Geometric Distribution\>의 극한 버전이다. Geo에서 trial을 시행하는 시간 간격 $1/n$이 0에 가까워질 때, Geo가 EXP를 따르게 되는 것이다.

이어지는 포스트에서는 연속 확률 분포에서 \<정규 분포\>만큼이나 중요한 분포인 \<**감마 분포; Gamma Distribution**\>에 대해 살펴본다! 🤩

👉 [Gamma Distribution]({{"/2021/04/05/gamma-distribution" | relative_url}})

<hr/>

### References

- ['soohee410'님의 포스트](https://soohee410.github.io/exponential_dist)