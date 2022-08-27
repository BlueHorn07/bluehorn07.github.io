---
title: "Maximum Likelihood Estimation"
layout: post
use_math: true
tags: ["Statistics"]
---


2021-1학기, 대학에서 '확률과 통계' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- Introduction
- MLE; Maximum Likelihood Estimation
  - MLE for Bernoulli case
  - MLE for Normal case

<hr/>

### Introduction

\<MLE; Maximum Likelihood Estimation\>은 지금까지의 접근법과는 사뭇 다른 접근 방식이다. \<MLE\>는 statistical inference를 수행하는 많은 접근법 중 하나로, statistical apporach에 새로운 철학과 시야를 제공한다 😁

<br/>

<span class="statement-title">Example.</span><br>

확률 $p$를 정확히 알지 못하는 p-coin을 10번 던진다고 하자. 10번의 결과는 아래와 같다.

<div align="center" style="margin: 8px">

H H T H T T H H H T

</div>

앞에서 배운 \<proportion estimation\>의 방법으로 접근하면, $p$는 Point Estimator $\hat{p} = 6/10$으로 추정된다.

<br/>

이번에는 \<MLE\>의 방식으로 접근해보자! 먼저 10의 동전 던지기가 위와 같이 나올 확률은 아래와 같다.

$$
P(H, H, T, \dots, H, T) = p^6 q^4
$$

위의 식을 다시 쓰면, $L(p) = p^6 (1-p)^4$로, 동전의 확률이 $p$일 때 "H H ... H T"의 결과를 얻을 확률을 의미한다.

이제, 이 함수 $L(p)$를 maximize 하는 $p$를 구해보자. 방법은 간단하다. 그냥 $p$에 대해 미분방정식을 풀면 된다. 이때, 계산의 편의를 위해 $\log$를 먼저 취해주자.

$$
\ell(p) = \log (L(p)) = 6 \log p + 4 \log (1-p)
$$

$$
\frac{d\ell(p)}{dp} = \frac{6}{p} - \frac{4}{1-p} = 0 \quad \rightarrow \quad p = 6/10
$$

즉, $p=6/10$이 "H H ... H T"라는 결과가 나올 확률을 Maximize하는 확률이라는 말이다!

이제 \<MLE\>를 수학적으로 정리해 다시 살펴보자!

<hr/>

### MLE; Maximum Likelihood Estimation

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span> MLE for Bernoulli case<br>

Let $X_1, \dots, X_n$ be a $\text{Ber}(p)$ Random Samples, with iid.

Then, the likelihood function $L(p; x_1, \dots, x_n)$ would be

$$
\begin{aligned}
L(p;\, x_1, \dots, x_n)
&= f(x_1;\, p) \cdots f(x_n;\, p) \\
&= p^{x_1} (1-p)^{1-x_1} \cdots p^{x_n} (1-p)^{1-x_n} \\
&= p^{\sum x_i} (1-p)^{n - \sum x_i}
\end{aligned}
$$

Take log on it!

$$
\begin{aligned}
\ell(p) = \sum x_i \cdot \log p + (n - \sum x_i) \cdot \log (1-p)
\end{aligned}
$$

Take derivative for $p$!

$$
\frac{d\ell(p)}{dp} = \frac{\sum x_i}{p} - \frac{n-\sum x_i}{1-p} = 0
$$

when solve the equation, then

$$
p = \frac{\sum x_i}{n} = \bar{x}
$$

</div>

<hr/>

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span> MLE for Normal case<br>

Let $X_1, \dots, X_n$ be a $N(\mu, 1)$ Random Samples, with iid.

Find the MLE of $\mu$!

$$
\begin{aligned}
L(\mu; \, x_1, \dots, x_n)
&= f(x_1; \, \mu) \cdots f(x_n; \, \mu) \\
&= \left( \frac{1}{\sqrt{2\pi}}\right)^n \exp \left( - \sum \, (x_i - \mu)^2 / \, 2 \right)
\end{aligned}
$$

Take log on it!

$$
\ell(\mu; \, \cdots) = n \cdot \log \left( \frac{1}{\sqrt{2\pi}}\right) - \frac{\sum \, (x_i - \mu)^2}{2}
$$

Take derivative for $\mu$!

$$
\frac{d\ell}{d\mu} = \sum (x_i - \mu) = 0
$$

when solve the equation, then

$$
\mu = \bar{x}
$$

</div>

<hr/>

이제 다음 포스트부터 통계학의 꽃🌹이라고 할 수 있는 \<**가설 검정; Hypothesis Tests**\>에 대해 다룬다!! 😁 우리가 지금까지 수행한 "추정(Estimation)"을 활용해 의사결정을 내리는 것이 바로 \<Hypothesis Test\>다!

👉 [Introduction to Hypothesis Tests]({{"/2021/05/18/introduction-to-hypothesis-tests.html" | relative_url}})

