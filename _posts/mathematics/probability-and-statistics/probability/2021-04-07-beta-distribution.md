---
title: "Beta Distribution"
toc: true
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](https://bluehorn07.github.io/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

<div class="notice" markdown="1">

**시리즈: Continuous Probability Distributions**

1. [Uniform and Normal Distribution]({{"/2021/03/29/uniform-and-normal-distribution" | relative_url}})
2. [Exponential Distribution]({{"/2021/03/31/exponential-distribution" | relative_url}})
3. [Gamma Distribution]({{"/2021/04/05/gamma-distribution" | relative_url}})
4. [Chi-square Distribution]({{"/2021/04/06/chi-square-distribution" | relative_url}})
5. [Beta Distribution]({{"/2021/04/07/beta-distribution" | relative_url}}) 👀
6. [Log-normal Distribution]({{"/2021/04/08/log-normal-distribution" | relative_url}})
7. [Weibull Distribution (optional)]({{"/2021/04/10/weibull-distribution" | relative_url}})

</div>

선행 개념으로 [Gamma Distribution]({{"/2021/04/05/gamma-distribution" | relative_url}})에 대해 알고 있어야 한다.

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

- [Beta Distribution](#beta-distribution); $\text{Beta}(\alpha, \beta)$

<hr/>

## Beta Distribution

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Beta function; $B(\alpha, \beta)$<br/>

Let $\alpha > 0$ and $\beta > 0$. A \<bet function\> is defined as

$$
B(\alpha, \beta) = \int^1_0 x^{\alpha-1}(1-x)^{\beta-1} dx
$$

</div>

<div class="theorem" markdown="1">

<span class="statement-title">Claim.</span><br/>

$$
B(\alpha, \beta) = \frac{\Gamma(\alpha) \Gamma(\beta)}{\Gamma(\alpha + \beta)}
$$

</div>

<div class="math-statement" markdown="1">

$$
\begin{aligned}
\Gamma(\alpha) \Gamma(\beta) &= \left(\int^{\infty}_0 x^{\alpha - 1} e^{-x} dx\right) \cdot \left(\int^{\infty}_0 x^{\beta - 1} e^{-x} dx\right) \\
&= \int^{\infty}_0 \int^{\infty}_0 x^{\alpha-1} y^{\beta-1} \cdot e^{-(x+y)} dx dy
\end{aligned}
$$

위의 식에서 다음과 같이 적분변수를 치환해보자.

$$
\begin{aligned}
x &= uv \\
y&= u(1-v) \\
\left| J \right| &= \left| \begin{matrix}
    x_u & x_v \\
    y_u & y_v
\end{matrix} \right| = \left| \begin{matrix}
    v & u \\
    1-v & -u
\end{matrix} \right| = u
\end{aligned}
$$

$$
\begin{aligned}
&\int^{\infty}_0 \int^{\infty}_0 x^{\alpha-1} y^{\beta-1} \cdot e^{-(x+y)} dx dy \\
&= \int^{\infty}_0 \int^{\infty}_0 (uv))^{\alpha-1} (u(1-v))^{\beta-1} \cdot e^{-(\cancel{uv}+u-\cancel{uv})} \; u \, dudv \\
&= \int^{\infty}_0 \int^{\infty}_0 u^{(\alpha-1) + (\beta-1) + 1} v^{\alpha-1} (1-v)^{\beta-1} \cdot e^{-u} \; du dv \\
&= \int^{\infty}_0 u^{\alpha + \beta-1} \cdot e^{-u} \; du \int^{\infty}_0 v^{\alpha-1} (1-v)^{\beta-1} \; dv \\
&= \Gamma(\alpha + \beta) \cdot B(\alpha, \beta)
\end{aligned}
$$

즉,

$$
\Gamma(\alpha)\Gamma(\beta) = \Gamma(\alpha + \beta) B(\alpha, \beta)
$$

이므로

$$
B(\alpha, \beta) = \frac{\Gamma(\alpha) \Gamma(\beta)}{\Gamma(\alpha + \beta)}
$$

$\blacksquare$

</div>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Beta Distribution; $\text{Beta}(\alpha, \beta)$<br/>

Let $\alpha>0$ and $\beta>0$. A RV $X$ is called a \<**beta RV**\> and decnoted as $X \sim \text{Beta}(\alpha, \beta)$ if its pdf is given by

$$
f(x) = \frac{x^{\alpha - 1} \cdot (1-x)^{\beta-1}}{B(\alpha, \beta)} \quad \text{for } x \in (0, 1)
$$

</div>

<span class="statement-title">Remark.</span><br/>

1\. $X \sim \text{Beta}(1, 1)$

When $\alpha = \beta = 1$, then $X \sim \text{Beta}(1, 1)$ and pdf is

$$
f(x) = \frac{x^0 \cdot (1-x)^0}{B(1, 1)} = \frac{1 \cdot 1}{1} = 1
$$

($B(1, 1) = \dfrac{\Gamma(1) \cdot \Gamma(1)}{\Gamma(2)} = \dfrac{0! \cdot 0!}{1!} = 1$)

즉, $\text{Beta}(1, 1)$은 Uniform distribution을 따르게 된다. 이런 점 때문에 Beta Distribution을 <span class="half_HL">generalization of the uniform distribution on $[0, 1]$</span>라고 여기기도 한다!

<br/>

2\. Coin Tossing

"If $P(H) = p$, then we can say $p \sim \text{Unif}(0, 1)$."

위의 아이디어를 확장하면,

Toss a coin $n+m$ times, and then we got $n$ heads. Then the distribution of $p$ given this<small>(= got $n$ heads)</small> is

$$
p \sim \text{Beta}(n+1, m+1)
$$

<br/>

3\. Expection & Variance

If $X \sim \text{Beta}(\alpha, \beta)$, then

- $E[X] = \dfrac{\alpha}{\alpha + \beta}$
- $\text{Var}(X) = \dfrac{\alpha\beta}{(\alpha+\beta)^2 (\alpha+\beta+1)}$

유도 과정은 추후에 추가하겠다.

<br/>

<span class="statement-title">Example.</span><br/>

Let $X_1, X_2, X_3$ be $\text{Unif}(0, 1)$ and independent.

Let $Y:=\max(X_1, X_2, X_3)$. Find the distribution of $Y$.

<div class="math-statement" markdown="1">

$$
\begin{aligned}
P(Y \le y) &= P(X_1 \le y, X_2 \le y, X_3 \le y) \\
        &= P(X_1 \le y) P(X_2 \le y) P(X_3 \le y) \quad (\text{independence}) \\
        &= y \cdot y \cdot y = y^3
\end{aligned}
$$

따라서, pdf는 $f(y) = 3y^2$가 되고 이것은 Beta Distriubtion인 $\text{Beta}(3, 1)$의 pdf와 동일하다!!

$$
B(3, 1) = \frac{\Gamma(3)\Gamma(1)}{\Gamma(3+1)} = \frac{2! \; 0!}{3!} = \frac{1}{3}
$$

$$
f(x) = \frac{x^{3-1}(1-x)^{1-1}}{B(3, 1)} = \frac{x^2 \cdot 0}{1/3} = 3x^2
$$

</div>

<hr/>

# 맺음말

이어지는 포스트에서는 \<**Weibull Distribution**\>을 통해 \<결함률; Failture rate\>와 \<신뢰도; Reliability\>을 모델링한다. 이 부분은 정규 수업에서는 소개만 하고 넘어간 부분이기 때문에 관심이 있거나 꼭 필요한게 아니라면 건너 뛰어도 괜찮다.

👉 [Weibull Distribution (Optional)]({{"/2021/04/10/weibull-distribution" | relative_url}})
