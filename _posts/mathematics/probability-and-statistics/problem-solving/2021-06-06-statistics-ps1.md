---
title: "Statistics - PS1"
layout: post
use_math: true
tags: ["statistics", "Problem Solving"]
---


2021-1학기, 대학에서 '확률과 통계' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

이 글은 "[Point Estimation]({{"/2021/05/05/point-estimation.html" | relative_url}})" 포스트에서 제시한 숙제 문제들을 풀이한 포스트입니다.

<span class="statement-title">TOC.</span><br>

- [MSE(Mean Squared Error) is the sum of variance and square of bias](#mse-is-the-sum-of-variance-and-square-of-bias)
- [Sample ariance $S^2$ is not the minimal variance estimator](#sample-variance-is-note-the-minimal-variance-estimator)
- [Compare $S^2$ and $\hat{S}^2$ which one is the most efficient variance estimator?](#which-one-is-the-most-efficient-variance-estimator)

<hr/>

## MSE is the sum of variance and square of bias

<div class="statement" markdown="1">

<span class="statement-title">Theorem.</span><br>

The \<MSE; Mean Squared Error\> of an estimator is defined as 

$$
\text{MSE} := E \left[ \left( \hat{\Theta} - \theta \right)^2 \right] = \text{Var}(\hat{\Theta}) + \left[ \text{Bias} \right]^2
$$

where $\text{Bias} := E [ \hat{\Theta} - \theta ]$.

</div>

<div class="proof" markdown="1">

<span class="statement-title">*Proof*.</span><br>

먼저, MSE(Mean Square Error)는 아래와 같이 표현할 수 있다.

$$
\text{MSE} = E [(\hat{\Theta} - \theta)^2]
$$

위의 식을 변형하면 아래와 같고, 이를 전개해보자.

$$
\begin{aligned}
E [(\hat{\Theta} - \theta)^2]
&= E \left[ (\hat{\Theta} - E[\hat{\Theta}] + E[\hat{\Theta}] - \theta)^2 \right] \\
&= E \left[ \left((\hat{\Theta} - E[\hat{\Theta}]) + (E[\hat{\Theta}] - \theta)\right)^2 \right] \\
&= E \left[ \cancelto{\text{Var}(\hat{\Theta})}{(\hat{\Theta} - E[\hat{\Theta}])^2} + (\hat{\Theta} - E[\hat{\Theta}]) (E[\hat{\Theta}] - \theta) + (E[\hat{\Theta}] - \theta)^2 \right] \\
&= \text{Var}(\hat{\Theta}) + E \left[ (\hat{\Theta} - E[\hat{\Theta}]) (E[\hat{\Theta}] - \theta) + (E[\hat{\Theta}] - \theta)^2 \right] \\
&= \text{Var}(\hat{\Theta}) + E \left[ \hat{\Theta} E[\hat{\Theta}] - \cancel{E[\hat{\Theta}]^2} - \hat{\Theta} \theta + \theta E [\hat{\Theta}] + \cancel{E[\hat{\Theta}]^2} + 2 \theta E[\hat{\Theta}] + \theta^2\right] \\
&= \text{Var}(\hat{\Theta}) + E \left[ \hat{\Theta} E[\hat{\Theta}] - \hat{\Theta} \theta - \theta E[\hat{\Theta}] + \theta^2\right]
\end{aligned}
$$

위의 식의 오른편의 텀을 아래와 같이 묶어준다.

$$
\begin{aligned}
E \left[ \hat{\Theta} E[\hat{\Theta}] - \hat{\Theta} \theta - \theta E[\hat{\Theta}] + \theta^2\right] 
&= E \left[ (\hat{\Theta} - \theta) E[\hat{\Theta}] - (\hat{\Theta} - \theta )\theta\right] \\
&= E \left[ (\hat{\Theta} - \theta) (E[\hat{\Theta}] - \theta)\right] \\
&= E \left[ (\hat{\Theta} - \theta) \cdot \cancelto{\text{bias}}{E[\hat{\Theta} - \theta]}\right] \\
&= \text{bias} \cdot \cancelto{\text{bias}}{E \left[ \hat{\Theta} - \theta \right]} \\
&= \text{bias} \cdot \text{bias} = (\text{bias})^2
\end{aligned}
$$

따라서, MSE는 아래와 같다.

$$
\text{MSE} = \text{Var}(\hat{\Theta}) + (\text{bias})^2
$$

$\blacksquare$

</div>

<hr/>


## Sample Variance is not the minimal variance estimator

<div class="statement" markdown="1">

<span class="statement-title">Exercise.</span><br>

Let $X_1, \dots, X_n$ be iid $N(\mu, \sigma^2)$.

Let $\displaystyle S^2 := \frac{1}{n-1} \sum^n_i (X_i - \bar{X})^2$ and $\displaystyle \hat{S}^2 := \frac{1}{n} \sum^n_i (X_i - \bar{X})^2$

Show that $\text{Var}(S^2) > \text{Var}(\hat{S}^2)$.

</div>

<div class="proof" markdown="1">

<span class="statement-title">Solve.</span><br>

두 estimator의 Variance를 구해보자.

$S^2$에 대해 $\dfrac{(n-1)S^2}{\sigma^2} \sim \chi^2(n-1)$가 성립하므로, 아래의 $\chi^2$ 분포의 성질에 따라 아래의 식이 성립한다.

$$
\text{Var} \left(\frac{(n-1)S^2}{\sigma^2}\right) = \text{Var} \left(\chi^2(n-1)\right) = 2(n-1)
$$

위의 식을 잘 정리하면,

$$
\begin{aligned}
\text{Var} \left(\frac{(n-1)S^2}{\sigma^2}\right)
&= 2(n-1) \\
\frac{(n-1)^2}{\sigma^4} \text{Var} (S^2)
&= 2(n-1) \\
\text{Var} (S^2)
&= \frac{2\sigma^4}{(n-1)}
\end{aligned}
$$

이제, $\hat{S}^2$의 Variance를 구해보자.

$$
\begin{aligned}
\text{Var}(\hat{S}^2) 
&= \text{Var} \left(\frac{n-1}{n}S^2\right) \\
&= \frac{(n-1)^2}{n^2}\text{Var}(S^2) \\
&= \frac{(n-1)^2}{n^2} \cdot \frac{2\sigma^4}{(n-1)} \\
&= \frac{2(n-1)\sigma^4}{n^2}
\end{aligned}
$$

두 estimator의 Variance를 비교해보면,

$$
\text{Var}(\hat{S}^2) = \frac{(n-1)^2}{n^2}\text{Var}(S^2) < \text{Var}(S^2)
$$

이다. 즉, $S^2$ 보다 낮은 variance를 갖는 estimator가 존재한다. $\blacksquare$ <br/>
(단, $\hat{S}^2$는 biased estimator임!)

</div>

<hr/>


## Which one is the most efficient variance estimator?

<div class="statement" markdown="1">

<span class="statement-title">Exercise.</span><br>

Compare $S^2$ and $\hat{S}^2$, which one is the most efficient estimator?

</div>

<div class="proof" markdown="1">

First, we know that the $S^2$ is an unbiased estimator, so $\text{bias}(S^2) = 0$.

Next, let's find the bias of $\hat{S}^2$.

$$
\begin{aligned}
\text{bias}(\hat{S}^2)
&= E \left[ \hat{S}^2 - \sigma^2 \right] \\
&= E \left[ \frac{1}{n} \sum_i^n (X_i - \bar{X})^2 - \sigma^2 \right] \\ 
&= E \left[ \frac{n-1}{n} \cdot \frac{1}{n-1} \sum_i^n (X_i - \bar{X})^2 - \sigma^2 \right] \\
&= \frac{n-1}{n} \cdot E \left[ \frac{1}{n-1} \sum_i^n (X_i - \bar{X})^2 \right] - \sigma^2 \\
&= \frac{n-1}{n} \cdot \sigma^2 - \sigma^2 \\ 
&= \frac{1}{n} \cdot \left( (n-1) \sigma^2 - n \sigma^2 \right) = - \frac{\sigma^2}{n}
\end{aligned}
$$

We've already got the variance of two estimators. Then, the MSE for two estimators are:

$$
\text{MSE}(S^2) = \frac{2\sigma^4}{(n-1)} + 0
$$

$$
\text{MSE}(\hat{S}^2) = \frac{2(n-1)\sigma^4}{n^2} + \frac{\sigma^4}{n^2}
$$

$$
\begin{aligned}
\frac{2\sigma^4}{(n-1)} \quad &\text{vs.} \quad \frac{2(n-1)\sigma^4}{n^2} + \frac{\sigma^4}{n^2} \\
\frac{2}{(n-1)} \quad &\text{vs.} \quad \frac{2(n-1)}{n^2} + \frac{1}{n^2} \\
\frac{2}{(n-1)} \quad &\text{vs.} \quad \frac{2(n-1) + 1}{n^2} \\
\frac{2}{(n-1)} \quad &\text{vs.} \quad \frac{2n-1}{n^2} \\
2 \cdot n^2 \quad &\text{vs.} \quad (2n-1) \cdot (n-1) \\
2 n^2 \quad &\text{vs.} \quad 2n^2 - 3n + 1 \\
\end{aligned}
$$

즉, $\text{MSE}(S^2) > \text{MSE}(\hat{S}^2)$이므로, $\hat{S}^2$가 "the most efficient estimator"이다! $\blacksquare$

</div>