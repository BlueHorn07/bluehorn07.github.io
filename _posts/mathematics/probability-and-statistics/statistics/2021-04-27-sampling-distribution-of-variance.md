---
title: "Sampling Distribution of Variance"
layout: post
use_math: true
tags: ["Statistics"]
---


2021-1학기, 대학에서 '확률과 통계' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- Sampling Distribution of $S^2$
  - chi-square distribution!
- (next) When $\sigma$ is unknown


<hr/>

### Sampling Distribution of $S^2$

Let $X_1, \dots, X_n$ be a random sample with $\text{Var}(X_i) = \sigma^2$. We already know that $E[S^2] = \sigma^2$. How about the distribution of $\displaystyle S^2 = \dfrac{1}{n-1} \sum^n_{i=1} (X_i - \bar{X})^2$?

가장 간단한 경우인, $n=2$인 경우를 살펴보자. 이때, $\bar{X} = \dfrac{X_1 + X_2}{2}$이다. 이때, $Y_i := X_i - \bar{X}$라고 둔다면,

$$
\begin{aligned}
    Y_1 = X_1 - \bar{X} = \frac{X_1 - X_2}{2} \\
    Y_2 = X_2 - \bar{X} = \frac{X_2 - X_1}{2} \\
\end{aligned}
$$

즉, $Y_1 = - Y_2$로 서로 dependent다! 그래서 $S^2$에 대해서는 CLT를 적용할 수가 없다 😥 그러나 아래의 정리를 잘 활용하면, $S^2$에 대한 Distribution을 유도할 수 있다!!

<br/>

<span class="statement-title">Note.</span><br>

Let $X_1, \dots, X_n$ be random sample from $N(\mu, \sigma^2)$.

1\. $\bar{X} \sim N\left( \mu, \sigma^2/n\right)$

2\. $\displaystyle\sum^n_i \left( \frac{X_i - \mu}{\sigma} \right)^2 \sim \chi^2(n)$; <small>$X_i$를 정규화하면 $Z(0, 1)$가 되고, 또 각 $X_i$가 independent 하기 때문!</small>

<div class="theorem" markdown="1">


<span class="statement-title">Theorem.</span> Sampling Distribution of $S^2$<br>

Let $X_1, \dots, X_n$ be random sample from $N(\mu, \sigma^2)$, then

$$
\frac{(n-1)S^2}{\sigma^2} = \sum^n_{i=1} \left( \frac{X_i - \bar{X}}{\sigma}\right)^2 \sim \chi^2 (n-1)
$$

"We lose one degree of freedom, because we estimate a parameter $\mu$ by $\bar{X}$."

</div>

<div class="math-statement" markdown="1">

<span class="statement-title">*Proof*.</span><br>

$$
\begin{aligned}
\frac{1}{\sigma^2} \sum^n_i \left( X_i - \mu \right)^2 &= \frac{1}{\sigma^2} \sum^n_{i=1} (X_i - \bar{X} + \bar{X} - \mu)^2 \\
&= \frac{1}{\sigma^2} \sum^n_i (X_i - \bar{X})^2 + \frac{1}{\sigma^2} \sum^n_i (\bar{X} - \mu)^2 + \frac{1}{\sigma^2} \sum^n_i 2 (X_i - \bar{X})(\bar{X} - \mu)
\end{aligned}
$$

이때, 마지막 텀인 $\displaystyle \frac{1}{\sigma^2} \sum^n_i 2 (X_i - \bar{X})(\bar{X} - \mu)$를 살펴보자.

$$
\begin{aligned}
\frac{1}{\sigma^2} \sum^n_i (X_i - \bar{X})(\bar{X} - \mu) &= \frac{1}{\sigma^2} \cdot (\bar{X} - \mu) \cdot \sum^n_i (X_i - \bar{X}) \\
&= \frac{1}{\sigma^2} \cdot (\bar{X} - \mu) \cdot \cancelto{0}{(X_1 + \cdots + X_n - n\bar{X})} \\
&= 0
\end{aligned}
$$

다시 원래의 식으로 돌아가서

$$
\begin{aligned}
\frac{1}{\sigma^2} \sum^n_i \left( X_i - \mu \right)^2 
&= \frac{1}{\sigma^2} \sum^n_i (X_i - \bar{X})^2 + \frac{1}{\sigma^2} \sum^n_i (\bar{X} - \mu)^2 + \cancelto{0}{\frac{1}{\sigma^2} \sum^n_i (X_i - \bar{X})(\bar{X} - \mu)} \\
&= \frac{1}{\sigma^2} \sum^n_i (X_i - \bar{X})^2 + \frac{1}{\sigma^2} \sum^n_i (\bar{X} - \mu)^2 \\
&= \frac{1}{\sigma^2} \sum^n_i (X_i - \bar{X})^2 + \frac{n(\bar{X} - \mu)^2}{\sigma^2} \\
&= \frac{1}{\sigma^2} \sum^n_i (X_i - \bar{X})^2 + \left( \frac{\bar{X} - \mu}{\sigma/\sqrt{n}}\right)^2 \\
\end{aligned}
$$

이때, 좌변의 $\displaystyle \frac{1}{\sigma^2} \sum^n_i \left( X_i - \mu \right)^2$는 $\chi^2(n)$의 분포를 따르고, 우변의 $\displaystyle \left( \frac{\bar{X} - \mu}{\sigma/\sqrt{n}}\right)^2$는 $\chi^2(1)$의 분포를 따른다.

만약 $Z = X + Y$에서 $Z \sim \chi^2(n)$이고, $Y \sim \chi^2(1)$이고, 게다가 $X \perp Y$라면, $X \sim \chi^2(n-1)$가 된다. 그러나 우리는 아직 $X \perp Y$에 대해 언급하지 않았다. 그래서 아래의 Lemma를 통해 $X \perp Y$를 확인해보자.

<div class="statement" markdown="1">

<span class="statement-title">Lemma.</span><br>

Let $X_1, \dots, X_n$ be a random sample from $N(\mu, \sigma^2)$, then $S^2$ and $\bar{X}$ are independent.

In fact, $\bar{X}$ and $(X_1 - \bar{X}, \; \dots, \; X_n - \bar{X})$ are independent.

</div>

따라서, 위의 Lemma에 의해 

$$
\frac{1}{\sigma^2} \sum^n_i (X_i - \bar{X})^2  = \frac{(n-1) S^2}{\sigma^2} \sim \chi^2(n-1)
$$

$\blacksquare$

</div>

<hr/>

이번 포스트에서는 population variance $\sigma^2$의 값을 알고 있을 때, sample mean $\bar{X}$, sample variance $S^2$의 분포를 구하는 상황이었다. 이어지는 포스트에서는 이 $\sigma^2$를 모르는 상황에서 $\bar{X}$와 $S^2$의 분포를 구하는 방법을 살펴볼 예정이다. 

미리 스포하자면, sample mean은 \<Student's t-distribution\>, sample variance는 \<F-distribution\>을 써야 한다!

👉 [Student's t-distribution]({{"/2021/04/27/student-t-distribution.html"}})

👉 [F-distribution]({{"/2021/05/04/F-distribution.html" | relative_url}})


