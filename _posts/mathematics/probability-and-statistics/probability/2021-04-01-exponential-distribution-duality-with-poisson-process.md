---
title: "Duality: Exponential Distribution and Poisson Process"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---
“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

<div class="notice" markdown="1">

**시리즈: Continuous Probability Distributions**

1. [Uniform Distribution](/2021/03/29/uniform-distribution)
2. [Normal Distribution](/2021/03/30/normal-distribution)
3. [Exponential Distribution](/2021/03/31/exponential-distribution) 👀
   1. [Duality: Exponential Distribution and Poisson Process](/2021/04/01/exponential-distribution-duality-with-poisson-process)
   1. [Duality: Exponential Distribution and Geometric Distribution](/2021/04/02/exponential-distribution-duality-with-geometric-distribution)
4. [Gamma Distribution](/2021/04/05/gamma-distribution)
5. [Chi-square Distribution](/2021/04/06/chi-square-distribution)
6. [Beta Distribution](/2021/04/07/beta-distribution)
7. [Log-normal Distribution](/2021/04/08/log-normal-distribution)
8. [Weibull Distribution (optional)](/2021/04/10/weibull-distribution)

</div>

# 들어가며

"[지수 분포(Exponential Distribution)](/2021/03/31/exponential-distribution)"에 대한 포스트에서 이어지는 내용 입니다.


처음에 Waiting Time에 대한 분포를 떠올리며 지수 분포 $\text{EXP}(\beta)$를 떠올렸고, 단위 시간당 이벤트 발생 횟수(Event Rate)에 대한 분포를 떠올리며 푸아송 분포 $\text{POI}(\lambda)$를 떠올렸습니다. 그런데, 이 두 분포는 서로 쌍대성(Duality)를 갖고 있습니다.

# Duality with Poisson Distribution

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

보면 지수 분포 $\text{EXP}(1/\lambda)$는 분수식 표현 $1/\beta$가 없어지면서 식이 꽤 깔끔해졌는데, $\text{POI}(1/\beta)$는 식이 아주 괴랄해졌습니다. 그래서 수업 때 교수님은 $\text{EXP}(\lambda)$으로 표기하기도 하셨는데요. $\lambda$가 Event Rate에 대한 것이다라는 명확한 합의와 표기만 있다면 이렇게 써도 될 것 같습니다.

다만, "Exponential Distribution은 대기 시간에 대한 분포다!"라는 사실은 직관적으로 받아들이고 싶어서 저의 포스트에서는 $\text{EXP}(\beta)$를 표준으로 쓰고, $\lambda$가 주어진 경우라면 $\text{EXP}(\beta = 1/\lambda)$ 이렇게 적도록 하겠습니다.


## Relationship with Poisson Process

**푸아송 과정(Poisson Process)** $\{ N(t) \}$는 $t$시간 동안 발생한 총 사건의 개수에 대한 모델링 입니다. 이때, 특정 시점 $t$에서의 $N(t)$는 포아송 분포를 따릅니다. 즉,

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

👀 익숙한 형태 아닌가요? 바로 **지수 분포(Exponential Distribution)**의 확률 밀도 함수(PDF)입니다! 🎉

$$
T \sim \text{EXP}(\beta = 1/\lambda) \quad \Rightarrow \quad f_T(t) = \lambda e^{-\lambda t}
$$

즉, **지수 분포는 어떤 사건이 처음 발생할 때까지 걸리는 시간**에 대한 확률 분포라고 할 수 있습니다!

# 맺음말

- 어떤 사건의 발생 횟수가 **포아송 분포**를 따른다면, 사건 사이의 대기 시간은 **지수 분포**를 따르게 된다. (또는 첫 사건이 발생하는 데까지 걸리는 시간은 지수 분포를 따른다.)
- $\lambda$는 Unit time 동안 Event가 일어날 평균 **횟수**를 의미한다. 그리고 그 역수인 $\beta$는 한 번의 Event가 발생하는 데 걸리는 평균 **시간**을 의미한다.

<br/>

본 포스트는 "[지수 분포(Exponential Distribution)](/2021/03/31/exponential-distribution)"에 대한 포스트 내용이 길어져서 별도로 분리한 문서 입니다. 지수 분포와 관련된 전체 목록은 아래에서 확인할 수 있습니다!

- [Exponential Distribution](/2021/03/31/exponential-distribution)
   - [Duality: Exponential Distribution and Poisson Process](/2021/04/01/exponential-distribution-duality-with-poisson-process) 👀
   - [Duality: Exponential Distribution and Geometric Distribution](/2021/04/02/exponential-distribution-duality-with-geometric-distribution)

# References

- ['soohee410'님의 포스트](https://soohee410.github.io/exponential_dist)
