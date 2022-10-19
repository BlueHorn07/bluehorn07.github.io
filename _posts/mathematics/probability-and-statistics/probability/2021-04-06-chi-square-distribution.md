---
title: "Chi-sqaure Distribution"
layout: post
tags: ["probability"]
---
“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics]({{"/category/probability-and-statistics" | relative_url}})에서 확인하실 수 있습니다 🎲

<div class="proof" markdown="1">

**시리즈: Continuous Probability Distributions**

1. [Uniform and Normal Distribution]({{"/2021/03/29/uniform-and-normal-distribution.html" | relative_url}})
2. [Exponential Distribution]({{"/2021/03/31/exponential-distribution.html" | relative_url}})
3. [Gamma Distribution]({{"/2021/04/05/gamma-distribution.html" | relative_url}})
4. [Chi-square Distribution]({{"/2021/04/06/chi-square-distribution.html" | relative_url}}) 👀
5. [Beta Distribution]({{"/2021/04/07/beta-distribution.html" | relative_url}})
6. [Log-normal Distribution]({{"/2021/04/08/log-normal-distribution.html" | relative_url}})
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

- [Chi-square Distribution](#chi-square-distribution) $\chi^2(n)$

<hr/>

# Chi-square Distribution

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Chi-square Distribution<br/>

A RV $X$ is called a \<**Chi-square RV**\> with <u>$n$ degrees of freedom</u>, denoted as $X \sim \chi^2(n)$, <br/>
if it has a <span class="half_HL">Gamma distribution with $\alpha = n/2$ and $\beta=2$</span>.

That is, its pdf is given by 

$$
f(x; n/2, 2) = \frac{1}{\Gamma(n/2) \cdot 2^{n/2}} \cdot x^{n/2 - 1} \cdot e^{-x/2}
$$

$$
\chi^2(n) = \text{Gamma}\left(\frac{n}{2}, 2\right)
$$

</div>

<br/>

<div class="statement" markdown="1">

<span class="statement-title">Remark.</span><br/>

1\. If $Z \sim N(0, 1)$, then $Z^2 \sim \chi^2(1)$.

</div>

<div class="math-statement" markdown="1">

<span class="statement-title">*proof*.</span><br/>

For $Z \sim N(0, 1)$, let $Y = Z^2$.

Let's see cdf $P(Y \le y)$,

$$
\begin{aligned}
F(y) &= P(Y \le y) = P(Z^2 \le y) \\
     &= P(-\sqrt{y} \le Z \le \sqrt{y})
\end{aligned}
$$

그럼 이제 정규분포 $Z$에서의 확률을 구하는 것이므로 적분식을 구성하면,

$$
\begin{aligned}
\int^{\sqrt{y}}_{-\sqrt{y}} \frac{1}{\sqrt{2\pi}} e^{-\frac{z^2}{2}} dz &= 2 \int^{\sqrt{y}}_{0} \frac{1}{\sqrt{2\pi}} e^{-\frac{z^2}{2}} dz
\end{aligned}
$$

위의 과정에서는 정규분포의 우함수 특성을 사용한 것이다. 위의 식에서 $z = \sqrt{x}$로 치환적분을 진행해보자.

$$
z = \sqrt{x} \iff dz = \frac{1}{2\sqrt{x}} dx
$$

그리고 적분식에 대입하면,

$$
\begin{aligned}
2 \int^{\sqrt{y}}_{0} \frac{1}{\sqrt{2\pi}} e^{-\frac{z^2}{2}} dz &= \frac{1}{\sqrt{2\pi}} \cancel{2} \int^y_0 \frac{1}{\cancel{2}\sqrt{x}} e^{-\frac{x}{2}} dx \\
&= \frac{1}{\sqrt{2\pi}} \int^y_0 x^{-\frac{1}{2}} e^{-\frac{x}{2}} dx
\end{aligned}
$$

즉, $Y = Z^2$의 cdf는

$$
F(y) = \frac{1}{\sqrt{2\pi}} \int^y_0 x^{\frac{1}{2} - 1} e^{-\frac{x}{2}} dx
$$

이다. 이제 pdf를 구하기 위해 양변을 미분하면,

$$
\begin{aligned}
f(y) = \frac{d}{dy} F(y) = \frac{1}{\sqrt{2\pi}} y^{\frac{1}{2} - 1} e^{-\frac{y}{2}}
\end{aligned}
$$

이때, 감마함수 $\Gamma(1/2)$는 $\sqrt{\pi}$의 값을 갖는다. 따라서,

$$
\begin{aligned}
f(y) &= \frac{1}{\sqrt{2\pi}} y^{\frac{1}{2} - 1} e^{-\frac{y}{2}} \\
    &= \frac{1}{\Gamma(1/2) \cdot 2^{\frac{1}{2}}} \cdot y^{\frac{1}{2} - 1} e^{-\frac{y}{2}}
\end{aligned}
$$

이것은 곧, 감마 분포 $\text{Gamma}(1/2, 2)$의 pdf와 같다! 따라서, 

$$
\left(Z(0, 1)\right)^2 \overset{D}{=} \text{Gamma}(1/2, 2) \overset{D}{=} \chi^2(1)
$$

</div>

<br/>

<div class="statement" markdown="1">

2\. If $X \sim \chi^2(n)$, then

- $E[X] = n$
- $\text{Var}(X) = 2n$

</div>

<hr/>

# 맺음말

이어지는 포스트에서는 \<**Weibull Distribution**\>을 통해 \<결함률; Failture rate\>와 \<신뢰도; Reliability\>을 모델링한다. 이 부분은 정규 수업에서는 소개만 하고 넘어간 부분이기 때문에 관심이 있거나 꼭 필요한게 아니라면 건너 뛰어도 괜찮다.

👉 [Weibull Distribution (Optional)]({{"/2021/04/10/weibull-distribution.html" | relative_url}})
