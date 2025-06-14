---
title: "Bernoulli Process"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

# Bernoulli Process

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Bernoulli Process<br>

The \<**Bernoulli process**\> is a **<u>sequence</u>** of independent Bernoulli trials.

At each trial $X_i$,

- $P(H) = P(X_i = 1) = p$
- $P(T) = P(X_i = 0) = 1-p$

즉, 베르누이 시행은 Bernoulli RV Sequence $X = \\{ X_n : n=1, 2, \dots \\}$라고 볼 수 있다.

$$
X_i \sim \text{Ber}(p) \quad \text{and} \quad X \sim \text{BP}(p)
$$

</div>

이런 베르누이 프로세스의 예로는

- 매일 코스피 지수의 상승/하락에 대한 binary sequence
- 주어진 time interval에 신호가 수신되는지 아닌지에 대한 binary seq.


\<Bernoulii Process\>에서 어떤 random variable $Y$를 조건과 함께 정의하면 새로운 확률 분포를 유도할 수 있다! 우리는 \<Binomial distribution\>, \<Geometric distribution\>, \<Negative BIN distribution\>을 \<Bernoulli Process\>로부터 유도해보겠다 😁

<div class="theorem" markdown="1">

<big>1. Number of Success $S_n$ in $n$ trials.</big>

Let's derive a random variable $S_n = X_1 + \cdots + X_n$ from the Bernoulli Process.

Then, $S_n$ follows the \<**Binomial Distribution**\>!

$$
P(S_n = x) = \binom{n}{x} p^x (1-p)^{n-x} \quad \text{for} \; x=0, 1, \dots, n
$$

</div>

<div class="theorem" markdown="1">

<big>2. Time until the first success</big>

Let's derive a random variable $T_1 = \min \\{ i \in \mathbb{N} : X_i = 1\\}$ from the Bernoulli Process.

Then, $T_1$ follows the \<**Geometric Distribution**\>!

$$
P(T_1 = x) = P(\underbrace{0, 0, \dots, 0}_{x-1}, 1) = (1-p)^{x-1} p \quad \text{for} \; x=1, 2, \dots
$$

</div>

<div class="theorem" markdown="1">

<big>3. Time until the first $k$ success</big>

\<Geometric Random Variable\>인 $T_1$을 확장한 개념이다.

Let's derive a random variable $T_k = \min \\{ i \in \mathbb{N} : \| \\{ X_i : X_i = 1 \\} \| = k\\}$ from the Bernoulli Process.

Then, $T_n$ follows the \<**Negative Binomial Distribution**\>!

$$
P(T_k = x) = P(\underbrace{0, 1, \dots, 1, \dots, 0}_{k-1 \text{ success}}, 1) = \binom{x-1}{k-1} (1-p)^{x-k} p^k \quad \text{for} \; x=k, k+1, \dots
$$

</div>

# 맺음말

사실 이것보다 더 중요한 것은 바로 이어서 살펴볼 "푸아송 프로세스"다. 푸아송 프로세스는 그 자체로도 재밌는 성질이 많이 나오고, 여러 확률 분포와 엮여 있다 ㅎㅎ
그럼 바로 빠져보자!

"[Poisson Process](/2021/03/26/poisson-process/)"
