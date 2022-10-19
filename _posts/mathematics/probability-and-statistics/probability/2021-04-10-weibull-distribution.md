---
title: "Weibull Distribution (Optional)"
layout: post
tags: ["probability"]
---
“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다 🎲

<div class="proof" markdown="1">

**시리즈: Continuous Probability Distributions**

1. [Uniform and Normal Distribution]({{"/2021/03/29/uniform-and-normal-distribution.html" | relative_url}})
2. [Exponential Distribution]({{"/2021/03/31/exponential-distribution.html" | relative_url}})
3. [Gamma Distribution]({{"/2021/04/05/gamma-distribution.html" | relative_url}})
4. [Chi-square Distribution]({{"/2021/04/06/chi-square-distribution.html" | relative_url}})
5. [Beta Distribution]({{"/2021/04/07/beta-distribution.html" | relative_url}})
6. [Log-normal Distribution]({{"/2021/04/08/log-normal-distribution.html" | relative_url}})
7. [Weibull Distribution (optional)]({{"/2021/04/10/weibull-distribution.html" | relative_url}}) 👀

</div>

<br><span class="statement-title">TOC.</span><br>

- Weibull Distribution; $\text{Weibull}(\alpha, \beta)$
  - Failure rate & Reliability

<hr/>

## Weibull Distribution

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span><br/>

Let $\alpha > 0$ and $\beta > 0$. We say that a RV $X$ has a \<**Weibull distribution**\>, denoted as $X \sim \text{Weibull}(\alpha, \beta)$, 
if its pdf $f(x)$ is given by

$$
f(x; \alpha, \beta) = \alpha \beta \cdot x^{\beta - 1} \cdot e^{-\alpha x^{\beta}} \quad \text{for } x > 0
$$

</div>

\<Weibull Distribution\>은 이런 분포가 있다 정도만 소개하고 넘어간다.

<span class="statement-title">Remark.</span><br/>

1\. Relationship with Exponential Distribution

if $\beta = 1$, then $\text{Weibull}(\alpha, 1) = \text{Exp}(\alpha)$.

<br/>

2\. cdf of $X$ is 

$$
F(x) = \int^x_0 f(y) \, dy = \begin{cases}
    1 - e^{-\alpha x^{\beta}} & \text{for } x > 0 \\
    \quad 0 & \text{else}
\end{cases}
$$

위의 식을 미분해보면, Weibull의 pdf가 나온다는 걸 쉽게 확인할 수 있다.

<hr/>

### Failure rate & Reliability

Let $T$ be a RV representing the **lifetime** (or time to failure) of a certain component.

Let $f(t)$ and $F(t)$ be its pdf and cdf respectively.

<span class="statement-title">Definition.</span><br/>

1\. reliability function, or survival function

$$
R(t) := P(T > t) = 1 - F(t)
$$

즉, CDF의 tail probability다. 왜냐하면, $P(T > t)$는 component가 $[0, t]$ 동안 survive할 확률을 의미하기 때문이다!

2\. failure rate, or hazard rate

$$
Z(t) := \frac{f(t)}{R(t)}
$$

Q. Why?

<div class="math-statement" markdown="1">

$$
\begin{aligned}
f(t) &= \frac{d}{dt} F(t) \\
     &= \lim_{h \rightarrow 0} \frac{F(t+h) - F(t)}{h} \\
     &= \lim_{h \rightarrow 0} \frac{P(T < t+h) - P(T < t)}{h} \\
     &= \lim_{h \rightarrow 0} \frac{P(t < T \le t+h)}{h}
\end{aligned}
$$

이때, 위의 식에 $R(t)$를 나눠보자!

$$
\begin{aligned}
\frac{f(t)}{R(t)} &= \lim_{h \rightarrow 0} \frac{P(t < T \le t+h)}{h} \cdot \frac{1}{R(t)} \\
                  &= \lim_{h \rightarrow 0} \frac{P(t < T \le t+h)}{h \cdot R(t)} \\
                  &= \lim_{h \rightarrow 0} \frac{1}{h} \cdot \frac{P(t < T \le t+h)}{P(T > t)}
\end{aligned}
$$

위의 식에서 볼 수 있듯, condition probability $\dfrac{P(t < T \le t+h)}{P(t > t)} = P(t < T \le t+h \mid T > t )$이 된다. 그래서 식을 정리하면,

$$
\frac{f(t)}{R(t)} = \lim_{h \rightarrow 0} \frac{P(t < T \le t+h \mid T > t)}{h}
$$

위의 식은 failure rate $f(t)/R(t)$가 <span class="half_HL">"the rate of the probability of the failure right after time $t$"</span>임을 의미한다! $\blacksquare$

</div>

만약 $T$가 Weibull distribution을 따른다면, failure rate $Z(t)$는

$$
Z(t) = \frac{f(t)}{R(t)} = \frac{\alpha \beta \cdot t^{\beta-1} e^{-\alpha t^{\beta}}}{e^{-\alpha e^{\beta}}} = \alpha \beta \cdot t^{\beta - 1}
$$

이때 $\beta$의 값에 따라서 failure rate의 양상을 살펴볼 수도 있는데,


1\. if $\beta = 1$, then the failure rate is $\alpha$ (**constant**).

즉, 시간에 관계없이 failure rate는 항상 같다.

<br/>

2\. if $\beta > 1$, then failure rate is **increasing** as $t$ flows.

즉, 시간이 지날수록 장비가 약해진다는 것을 의미한다.

<br/>

3\. if $\beta < 1$, then failure rate if **decreasing**.

즉, 시간이 지날수록 장비가 오히려 더 좋아지는 것을 의미한다.

<hr/>

이어지는 포스트에서는 Random Variable에 간단한 변환(Transform)을 적용했을 때의 pdf를 어떻게 구하는지 살펴본다. 뒷부분에는 moment을 구하는 함수인 \<MGF; Momentim Generating Function\>도 등장하기 때문에 중요한 챕터라고 할 수 있다!

👉 [Transformations of Random Variable - 1]({{"/2021/04/11/transformations-of-random-variable-1.html" | relative_url}})