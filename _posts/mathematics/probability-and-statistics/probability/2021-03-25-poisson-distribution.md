---
title: "Poisson Distribution"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

# Poisson Distribution

\<푸아송 분포 Poisson Distribution\>는 [이항 분포](/2021/03/18/binomial-distribution) $\text{BIN}(n, p)$의 특수한 경우이다. $\text{BIN}(n, p)$에서 $n$이 무한대로 커지고, $p$가 아주아주 작아질 때, 분포는 푸아송 분포를 만족하게 된다!

그렇다면 본래 BIN이던걸 왜 푸아송 분포로 해석하는 걸까? 이 질문에 대한 답은 아래의 유튜브 영상에서 정말 잘 설명하고 있다. 한번 보고 오자.

👉 [Youtube - 푸아송분포 소개](https://youtu.be/JOWYEDwqAtY?t=79)

즉, <span class="half_HL">$n$과 $p$의 값을 다룰 수도 없고 정의할 수도 없을 때</span>, 푸아송은 $np$를 $\lambda$로 두고 새로운 형태의 분포를 유도한 것이다. 또는 평균값인 $\lambda$를 아는 상태에서 유도한 분포라고 볼 수 있을 것 같다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Poisson Distribution<br>

A Poisson random variable $X$ with parameter $\lambda > 0$, denoted as  $X \sim \text{POI}(\lambda)$, and it has a pmf $f(x)$ as

$$
f(x) = e^{-\lambda} \frac{\lambda^x}{x!} \quad \text{for} \quad x=0, 1, \dots
$$

</div>

이 푸아송 분포가 정말 pmf인지 검증해보자. 확률의 合이 1이 됨을 보이면 된다.

$$
\begin{aligned}
\sum f(x) &= e^{-\lambda} \sum_{x=0} \frac{\lambda^x}{x!} \\
&= e^{-\lambda} e^\lambda = 1
\end{aligned}
$$

## Derivation

앞에서 푸아송 분포는 이항분포의 특수한 경우라고 소개했다. 이것을 확인해보자.

<div class="proof" markdown="1">

<span class="statement-title">Derivation.</span><br>

Let $X \sim \text{BIN}(n, p) = \text{BIN}(n, \frac{\lambda}{n})$, then pmf $f_n (x)$ is

$$
f_n (x) = \binom{n}{x} p^x (1-p)^{n-x} = \binom{n}{x} \left( \frac{\lambda}{n}\right)^x \left( 1 - \frac{\lambda}{n}\right)^{n-x}
$$

위의 식에서 $\binom{n}{x}$를 풀어서 써보면 아래와 같고, 이것을 잘 정리해보자.

$$
\begin{aligned}
f_n (x) &= \binom{n}{x} \left( \frac{\lambda}{n}\right)^x \left( 1 - \frac{\lambda}{n}\right)^{n-x} \\
&= \frac{n!}{x!(n-x)!} \frac{\lambda^x}{n^x} \left( 1 - \frac{\lambda}{n}\right)^n \left( 1 - \frac{\lambda}{n}\right)^{-x} \\
&= \frac{\lambda^x}{x!} \cdot \left( 1 - \frac{\lambda}{n}\right)^n \cdot \frac{n!}{(n-x)!} \left(\frac{1}{n}\right)^x \left( 1 - \frac{\lambda}{n}\right)^{-x}
\end{aligned}
$$

이제 위의 식에서 $n \rightarrow \infty$를 취하자!

$$
\begin{aligned}
\lim_{n \rightarrow \infty} f_n (x) &= \lim_{n \rightarrow \infty} \frac{\lambda^x}{x!} \cdot \left( 1 - \frac{\lambda}{n}\right)^n \cdot \frac{n!}{(n-x)!} \left(\frac{1}{n}\right)^x \left( 1 - \frac{\lambda}{n}\right)^{-x} \\
&= \lim_{n \rightarrow \infty} \frac{\lambda^x}{x!} \cdot e^{-\lambda} \cdot \frac{n(n-1)\cdots(n-x+1)}{n^x} \cdot \frac{(n-\lambda)^{-x}}{n^{-x}} \\
&= \frac{\lambda^x}{x!} \cdot e^{-\lambda} \cdot 1 \cdot 1 \\
&= \frac{\lambda^x}{x!} e^{-\lambda}
\end{aligned}
$$

$\blacksquare$

</div>

위의 유도 과정에서는 이항 분포를 사용했지만, 미분방정식으로도 푸아송 분포를 유도할 수 있다고 한다. 유도 과정에 대한 영상을 링크로 걸어둔다. 👉 [YouTube - 푸아송 분포, 미분방정식으로 유도](https://youtu.be/vGExuMJRSyU)

## Law of Rare Events

<div class="notice" markdown="1">

<span class="statement-title">Theorem.</span> Law of Rare Events<br>

$n$이 무한히 커지게 되면, 자연스럽게 확률 $p=\dfrac{\lambda}{n}$는 작아지게 된다. 하지만, 이항 분포의 성질에 따라 여전히 평균과 분산은 아래와 같을 것이다.

- $\displaystyle E[X] = \lim_{n\rightarrow\infty} np = \lambda$
- $\displaystyle \text{Var}(X) = \lim_{n\rightarrow\infty} n \frac{\lambda}{n} \left( 1 - \frac{\lambda}{n}\right) = \lambda$

</div>



위의 명제에 대한 증명은 평균과 분산의 정의에 입각해 식을 전개하면 된다. 증명은 추후에 기술하겠다.

# Poisson Process

(주의) 지수 분포를 알고 있어야 합니다.

푸아송 프로세스는 지수 분포의 확률 분포를 갖는 시행을 계속할 때, 일정 시간 동안 사건이 몇 번 발생하는지를 설명하는 분포 입니다.

자세한 내용은 별도 포스트 "Poisson Process"로 분리 하였습니다.

- "[Bernoulli Process](/2021/03/26/bernoulli-process/)"
  - 랜덤 프로세스를 처음 들어봤다면 이 녀석부터 입문해보자!
- "[Poisson Process](/2021/03/26/poisson-process/)"


# 맺음말

이번 포스트에서 다룬 \<Poisson Distribution\>을 끝으로 교재에서 다루는 모든 이산 확률 분포를 살펴보았다. 다음 포스트부터는 연속 RV가 갖는 "연속 확률 분포; Continuous Distribution"를 살펴본다.

- Continuous Probability Distribution
  - [Uniform Distribution](/2021/03/29/uniform-distribution)
  - [Normal Distribution](/2021/03/30/normal-distribution)
  - [Exponential Distribution](/2021/03/31/exponential-distribution)
  - [Gamma Distribution](/2021/04/05/gamma-distribution)
  - [Chi-square Distribution](/2021/04/06/chi-square-distribution)
  - [Beta Distribution](/2021/04/07/beta-distribution)
  - [Log-normal Distribution](/2021/04/08/log-normal-distribution)
  - [Weibull Distribution (optional)](/2021/04/10/weibull-distribution)

