---
title: "Log-normal Distribution"
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
6. [Log-normal Distribution]({{"/2021/04/08/log-normal-distribution.html" | relative_url}}) 👀
7. [Weibull Distribution (optional)]({{"/2021/04/10/weibull-distribution.html" | relative_url}})

</div>

선행 개념으로 [Gamma Distribution]({{"/2021/04/05/gamma-distribution.html" | relative_url}})에 대해 알고 있어야 한다.

$$
f(x; \alpha, \beta) 
= \begin{cases}
    C_{\alpha, \beta} \cdot x^{\alpha-1} e^{-\frac{x}{\beta}} & \text{for } x > 0 \\
    \quad 0 & \text{else}
\end{cases}
$$

$$
C_{\alpha, \beta} 
= \frac{1}{\Gamma(\alpha) \cdot \beta^{\alpha}}
$$

<br><span class="statement-title">TOC.</span><br>

- [Log-normal Distribution](#log-normal-distribution) $\text{LN}(\mu, \sigma^2)$

<hr/>

## Log-normal Distribution

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span><br/>

A RV $X$ is called a \<log-normal RV\> if $\log X \sim N(\mu, \sigma^2)$. We denote $X \sim \text{LN}(\mu, \sigma^2)$.

즉, RV $X$에 log를 취한 것이 normal distribution이 된다면, "log-normal"이라고 부르는 것이다.

</div>

<span class="statement-title">Remark.</span><br/>

1\. $X := e^Y$

If $Y \sim N(\mu, \sigma^2)$ and $X := e^Y$, then $X \sim \text{LN}(\mu, \sigma^2)$.

<br/>

2\. Expectation & Variance

- $E[X] = \exp \left(\mu + \frac{\sigma^2}{2} \right)$
- $\text{Var}(X) = (e^{\sigma^2} - 1)\cdot e^{2\mu + \sigma^2}$

<hr/>

# 맺음말

이어지는 포스트에서는 \<**Weibull Distribution**\>을 통해 \<결함률; Failture rate\>와 \<신뢰도; Reliability\>을 모델링한다. 이 부분은 정규 수업에서는 소개만 하고 넘어간 부분이기 때문에 관심이 있거나 꼭 필요한게 아니라면 건너 뛰어도 괜찮다.

👉 [Weibull Distribution (Optional)]({{"/2021/04/10/weibull-distribution.html" | relative_url}})
