---
title: "EDF and Quantile"
toc: true
toc_sticky: true
categories: ["Statistics"]
---
“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다 🎲

<div class="proof" markdown="1">

**시리즈: Sampling Distributions**

1. [Sampling Distribution]({{"/2021/04/25/sampling-distribution" | relative_url}})
2. [Sampling Distribution of Mean]({{"/2021/04/26/sampling-distribution-of-mean-and-clt" | relative_url}})
3. [Sampling Distribution of Variance]({{"/2021/04/27/sampling-distribution-of-variance" | relative_url}})
4. [Student's t-distribution]({{"/2021/04/27/student-t-distribution" | relative_url}})
5. [F-distribution]({{"/2021/05/04/F-distribution" | relative_url}})
6. [EDF and Quantile]({{"/2021/05/04/EDF-and-Quantile" | relative_url}}) 👀

</div>

<br><span class="statement-title">TOC.</span><br>

- [EDF; Empirical Distribution Function; $\hat{F}$](#edf-empirical-distribution-function)
- [Quantile; 분위수](#quantile)
  - Quantile of a Sample
- Normal Quantile-Quantile plot; Q-Q plot; 본위수대조도

<hr/>

### EDF; Empirical Distribution Function

For given samples $X_1, \dots, X_n$,

- $\bar{X}$ is a "location of the sample"
- $S^2$ is a "variability of sample"

이때, 우리는 위와 같이 sample points $X_1, X_2, \dots, X_n$를 바탕으로 어떤 distribution function을 아래와 같이 유도할 수 있다.

<br/>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> EDF; Empirical Distribution Function<br>

Let $X_1, \dots, X_n$ be a random sample,

Let's define $\hat{F}(x)$ as

$$
\hat{F}(x) := \frac{\left| \\{ i : X_i \le x\\} \right|}{n} = \frac{1}{n} \sum^n_i I(x_i \le x) = \frac{\text{# of elts $X_i$'s which is less than $x$}}{n}
$$

우리의 위와 같이 sample로부터 유도한 distribution function을 \<**Empirical Distribution Function**\>이라고 한다.

</div>

<br/>

<span class="statement-title">Remark.</span><br>

1\. $\hat{F}$ is a random variable.

2\. Let $F(x) := P(X \le x)$ where $X \overset{D}{=} X_i$,

then $\hat{F}(x) \rightarrow F(x)$ as $n \rightarrow \infty$ in sense of probability.

<div class="math-statement" markdown="1">

$$
\begin{aligned}
\hat{F}(x)
&= \frac{1}{n} \sum^n_{i=1} I(X_i \le x) \\
\end{aligned}
$$

Let $Y_i = I(X_i \le x)$, then

$$
\begin{aligned}
\hat{F}(x)
&= \frac{Y_1 + \cdots + Y_n}{n}
\end{aligned}
$$

By WLLN,

$$
\begin{aligned}
\hat{F}(x)
&= \frac{Y_1 + \cdots + Y_n}{n} \\
&\overset{\text{WLLN}}{\longrightarrow} E(Y_i) = E(I(X_i \le x)) \\
&= 1 \cdot P(X_i \le x) = F(x)
\end{aligned}
$$

$\blacksquare$

</div>

따라서, 우리는 EDF를 통해 CDF를 추정할 수 있다. 또한, 우리는 EDF의 \<Quantile\>을 살펴봄으로써 "distribution of population"을 <u>결정</u>할 수 있다!

<hr/>

### Quantile

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Quantile; 분위수<br>

The \<**Quantile**\> of the distribution function $F$ is the inverse of $F$.

A \<Quantile\> of a sample, $q(f)$, is a value for which a specified fraction $f$ of the data values is less than or equal to $q(f)$.

$$
q(f) := \inf \left\{ x \in \mathbb{R} : F(x) \ge f \right\}
$$

즉, $q(f)$는 $F(x) \ge f$가 되는 $x$ 값들 중, 가장 작은 값을 말한다.

</div>

\<Quantile\>에는 \<Quertiles\>, \<Percentiles\>, \<Deciles\> 등 여러 변형들이 있다. 아래의 포스트를 통해 그 변형들을 살펴보자.

👉 ['Statistics How To'의 포스트](https://www.statisticshowto.com/quantile-definition-find-easy-steps/)

<br/>

If $F$ is strictly increasing, then $F(q(f)) = f$ for $f \in [0, 1]$.

<span class="statement-title">Examples.</span><br>

1\. Let $X \sim \text{Unif}(0, 1)$

then, $g(f) = f$ for $f \in [0, 1]$.

2\. Let $X \sim N(0, 1)$

(생-략)

<hr/>

<span class="statement-title">Definition.</span> Quantile of a sample, $\hat{q}(f)$<br>

the inverse of EDF $\hat{F}(x)$,

$$
\hat{q}(f) = \inf \left\{ x : \hat{F}(x) \ge f \right\}
$$

<span class="statement-title">Example.</span><br>

Let $\\{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 \\}$ be sample points.

EDF $\hat{F}(x)$ is $\hat{F}(x) = \dfrac{x}{10}$. thus, $\hat{q}(0.7) = 7$

<hr/>

### Normal Q-Q Plot

Q. What can we do about \<Quantile\>?

A. "모집단이 정규분포를 따른다"는 가정을 검토하는 데에 사용할 수 있음!!

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Normal Quantile-Quantile plot; Q-Q plot<br>

A plot of quantile of $X$ against $q_{0, 1}(f)$ where $q_{0, 1}(f)$ is the quantile of $N(0, 1)$.

<div class="img-wrapper">
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Normal_normal_qq.svg/450px-Normal_normal_qq.svg.png" width="300px">
  <p>
  Image from <a href="https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Normal_normal_qq.svg/450px-Normal_normal_qq.svg.png">Wikipedia</a>
  </p>
</div>

IF the distribution of $X$ is very close to $N(0, 1)$, then a \<Normal Quantile-Quantile plot\> should show **<u>a straight line</u>**.

</div>

<hr/>

지금까지 **"통계적 추론(Statistical Inference)"**를 수행하기 위한 기초를 살펴봤다! 🙌

다음 포스트부터는 "통계적 추론"의 방식 중 하나인 **\<Estimation; 추정\>**에 대해 다룬다. estimator의 \<bias\>와 \<variance\>에 대해 살펴보며, 신뢰 구간을 구하는 \<Interval Estimation\>을 수행한다 😁

👉 [Point Estimation]({{"/2021/05/05/point-estimation" | relative_url}})

👉 [Interval Estimation]({{"/2021/05/06/interval-estimation" | relative_url}})


