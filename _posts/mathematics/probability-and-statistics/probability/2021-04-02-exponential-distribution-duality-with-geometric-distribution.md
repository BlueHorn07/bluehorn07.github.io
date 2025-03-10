---
title: "Duality: Exponential Distribution and Geometric Distribution"
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
3. [Exponential Distribution](/2021/03/31/exponential-distribution)
   1. [Duality: Exponential Distribution and Poisson Process](/2021/04/01/exponential-distribution-duality-with-poisson-process)
   1. [Duality: Exponential Distribution and Geometric Distribution](/2021/04/02/exponential-distribution-duality-with-geometric-distribution) 👀
4. [Gamma Distribution](/2021/04/05/gamma-distribution)
5. [Chi-square Distribution](/2021/04/06/chi-square-distribution)
6. [Beta Distribution](/2021/04/07/beta-distribution)
7. [Log-normal Distribution](/2021/04/08/log-normal-distribution)
8. [Weibull Distribution (optional)](/2021/04/10/weibull-distribution)

</div>

# Memoryless Property

어떤 확률 분포가 **Memoryless Property**(기억 없음 성질)를 가진다면, 다음 조건을 만족합니다.

$$
P(X > a + t \mid X > a) = P(X > t)
$$

이는 **"이미 $a$만큼 시간이 지났더라도 남은 시간 $t$ 동안 사건이 발생하지 않을 확률은 처음부터 $t$ 시간 만큼 기다릴 때와 동일하다"**는 의미입니다. 즉, 현재까지 얼마나 기다렸는지와 관계없이 남은 대기 시간이 동일한 확률 분포를 따른다는 특징을 가집니다.

지수 분포(Exponential Distribution)가 위의 **Memoryless Property**를 만족하는지 확인해 봅시다.

$$
\begin{aligned}
P(X > a + t \mid X > a) &= \frac{P(X > a + t)}{P(X > a)} \\
                        &= \frac{e^{-\lambda (a+t)}}{e^{-\lambda a}} \\
                        &= e^{-\lambda t} = P(X > t)
\end{aligned}
$$

따라서, **지수 분포(Exponential Distribution)는 Memoryless Property를 가집니다!** 🚀

## Example

어떤 전구의 수명이 지수 분포를 따른다고 합니다. 즉, 전구가 언제 고장날지는 완전히 랜덤이고, 평균적으로 $1/\lambda$ 시간마다 고장 난다고 합니다.

지금까지 100시간 동안 전구가 고장나지 않았다고 합니다. 그럼 지금부터 $t$시간 더 전구가 지속할지는

- "이 전구는 이미 100시간이나 버텼으니까 앞으로도 한참 더 버티겠지?" ❌ (이건 틀린 생각!)
- "이 전구는 처음 샀을 때랑 똑같은 확률로 앞으로도 고장이 날 거야." ✅ (이게 맞는 생각!)


## Relationship with Geometric Distribution

우리는 어떤 사건이 처음으로 발생하는 시행 횟수 $X$를 모델링한 **[Geometric Distribution](/2021/03/24/discrete-probability-distributions-2/#geometric-distribution)**을 살펴본 적이 있습니다.

기하 분포 역시 Memoryless Property를 만족합니다. 즉, 이미 몇 번의 시행이 진행되었든 상관없이, 앞으로 사건이 처음 발생하기까지 걸리는 시행 횟수의 확률은 항상 동일합니다. 놀랍게도, 기하 분포에서 지수 분포의 Memoryless 성질을 유도할 수도 있습니다!

<div class="proof" markdown="1">

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

</div>

# 맺음말

- "지수 분포"는 "기하 분포"의 극한 버전 입니다. 기하 분포에서 trial을 시행하는 시간 간격 $1/n$이 0에 가까워진다면, 기하 분포는 지수 분포를 따르게 됩니다.

<br/>

본 포스트는 "[지수 분포(Exponential Distribution)](/2021/03/31/exponential-distribution)"에 대한 포스트 내용이 길어져서 별도로 분리한 문서 입니다. 지수 분포와 관련된 전체 목록은 아래에서 확인할 수 있습니다!

- [Exponential Distribution](/2021/03/31/exponential-distribution)
   - [Duality: Exponential Distribution and Poisson Process](/2021/04/01/exponential-distribution-duality-with-poisson-process)
   - [Duality: Exponential Distribution and Geometric Distribution](/2021/04/02/exponential-distribution-duality-with-geometric-distribution) 👀

# References

- ['soohee410'님의 포스트](https://soohee410.github.io/exponential_dist)
