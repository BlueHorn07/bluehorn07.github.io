---
title: "Random Process"
toc: true
toc_sticky: true
categories: ["Machine Learning"]
modified_date: 2021.09.21
readtime: 20 minutes
---

"Machine Learning"을 공부하면서 개인적인 용도로 정리한 포스트입니다. 지적은 언제나 환영입니다 :)
{: .notice }

이번 포스트는 "확률론(Probability Theory)"과 "Machine Learning"에서 등장하는 *"Process"* 가 붙은 모든 개념을 넓은 시야로 살펴보기 위해 작성한 포스트입니다. 다루는 주제는 아래와 같습니다.

# Introduction to Random Process

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Random Process<br>

A **random process** is a time-varying function, that assigns the outcome of a random experiment to each time instant.

</div>

대충, (time instant)에 random experiment의 결과(outcome)을 매핑한다는 뜻이다.

또는 아래와 같이 정의하기도 하는데,

<div class="definition" markdown="1">

A (infinite) sequence of random variables $X_1, X_2, \dots, X_n, \dots$

</div>

즉, RV의 infinite sequence를 \<**random process**\>라고 한다. 첫번째 정의보다는 두번째 정의가 좀더 와닿는 편이다. 👍

\<random process\>를 정의할 때, RV $X_i$가 등장했으니 자연스럽게 아래의 성질들을 가질 것이다.

- $E[X_i]$: mean of RV
- $\text{Var}(X_i)$: variance of RV
- $p_{X_i} (x_i)$: marginal probability distribution of RV

우리는 \<random process\> 자체의 분포를 생각해볼 수도 있는데, 이것은 아래와 같은 joint probability distribution이 된다.

$$
p_{X_1, \dots, X_n, \dots} (x_1, \dots, x_n, \dots)
$$

또, 우리는 \<random process\>의 Sample Space $\Omega$에 대해 생각해볼 수 있다.

\<random process\> 중 하나인 \<Bernoulli Process\>의 경우, Sample Sapce는 0-1의 infinite sequence가 된다.

<div class="theorem" markdown="1">

<span class="statement-title">Property.</span> Sample Space of Bernoulli Process<br>

$$
\Omega_{\text{BP}}
= \left\{
  (b_1, \dots, b_n, \dots ) \mid b_i \in \{ 0, 1 \}
  \right\}
$$

</div>

## Some Properties of Random Process

\<Random Process\>는 몇가지 특징을 가질 수 있습니다만,
<span style="color: red">이것은 필수적인 것은 아니며, 몇몇 \<random process\>에 공통적으로 보이는 특징이거나 가정입니다.</span>

<big>**1. Independence btw trials**</big>

개별 trials은 서로 독립적으로 이루어진다. 즉, 영향을 주지 않는다.

- Bernoulli Process, Poisson Process, ...

<big>**2. Memoeryless Property**</big>

$$
P(X = x + k \mid x > k) = P(X = x)
$$

- Bernoulli Process, Poisson Process, ...
- Markov Process

# Bernoulli Process

\<Bernoulli Process\>는 매 시간 간격 마다 베르누이 시행을 수행하는 실험 입니다.

👉 [Bernoulli Process](/2021/03/26/bernoulli-process/)

# Poisson Process

\<Poisson Process\>는 \<Bernoulli Process\>에서 극한을 취해 time interval의 간격을 아주아주 줄여서 continuous domain 위에서 정의한 Random Process이다.
BP가 $\mathbb{N}$ 위에서 정의되었다면, PP는 $\mathbb{R^{+}}$ 위에서 정의되는 Random Process인 셈!

PP에 대한 내용은 아래 포스트의 내용으로 대체한다 🙏

👉 [Poisson Process](/2021/03/26/poisson-process/)


# Gaussian Process

A sequence of Gaussian distribution으로, multi-variate Gaussian distribution의 일반화된 버전이다.
"distribution over functions"으로 취급한다! 💪

👉 [Distribution over functions & Gaussian Process](/2021/07/01/Gaussian-process/)


# Markov Process

👉 [Markov Process](/2021/07/03/markov-process/)


# References

- [[MIT OCW] Introduction to Probability: Lecture 21](https://ocw.mit.edu/resources/res-6-012-introduction-to-probability-spring-2018/part-iii-random-processes/MITRES_6_012S18_L21AS.pdf)



