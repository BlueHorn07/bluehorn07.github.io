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

$X$가 "$n$명의 학생 중에 확통 수업을 선호한다고 응답한 학생 수"라는 RV라면, $X$는 [HyperGeometric 분포](/2021/03/24/discrete-probability-distributions-2#hypergeometric-distribution)를 따를 것이다. 만약 전체 학생 수가 충~분히 크다면, HyperGeometric 분포를 Binomial 분포로 근사할 수도 있을 것이다.

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
S^2 &= \frac{1}{n-1} \sum^n_{i=1} \left( X_i^2 - 2 X_i \overline{X} + (\overline{X})^2 \right) \\
    &= \frac{1}{n-1} \left\{ \sum^n_{i=1} X_i^2 - 2 \overline{X} \sum^n_{i=1} X_i + n (\overline{X})^2 \right\} \\
\end{aligned}
$$

이때, $\displaystyle\sum^n_{i=1} X_i$는 그 정의에 의해 $n\overline{X}$가 된다.

$$
\begin{aligned}
S^2 &= \frac{1}{n-1} \left\{ \sum^n_{i=1} X_i^2 - 2 \overline{X} \cdot n\overline{X} + n (\overline{X})^2 \right\} \\
    &= \frac{1}{n-1} \left\{ \sum^n_{i=1} X_i^2 - n (\overline{X})^2 \right\} \\
\end{aligned}
$$

이제 위의 식의 양변에 평균을 취해보자.

$$
\begin{aligned}
E[S^2] &= \frac{1}{n-1} \left\{ \sum^n_{i=1} E(X_i)^2 - n E\left[(\overline{X})^2\right] \right\} \\
       &= \frac{1}{n-1} \left\{ n \cdot \sigma^2 - n \cdot \frac{1}{n^2} \cdot E \left[(X_1 + \cdots + X_n)^2 \right] \right\} \\
       &= \frac{1}{n-1} \left\{ n \cdot \sigma^2 - \frac{1}{n} \cdot \left( n \cdot E[X_1^2] + \cancelto{0}{E[X_i X_j]} + \cdots \right) \right\} \\
       &= \frac{1}{n-1} \left\{ n \cdot \sigma^2 - \frac{1}{\cancel{n}} \cdot \left( \cancel{n} \cancelto{\sigma^2}{E[X_1^2]} \right) \right\} \quad (\text{independence}) \\
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
