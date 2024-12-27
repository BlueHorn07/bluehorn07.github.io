---
title: "Uniform Distribution"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---
“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

<div class="notice" markdown="1">

**시리즈: Continuous Probability Distributions**

1. [Uniform Distribution]({{"/2021/03/29/uniform-distribution" | relative_url}}) 👀
2. [Normal Distribution]({{"/2021/03/30/normal-distribution" | relative_url}})
3. [Exponential Distribution]({{"/2021/03/31/exponential-distribution" | relative_url}})
4. [Gamma Distribution]({{"/2021/04/05/gamma-distribution" | relative_url}})
5. [Chi-square Distribution]({{"/2021/04/06/chi-square-distribution" | relative_url}})
6. [Beta Distribution]({{"/2021/04/07/beta-distribution" | relative_url}})
7. [Log-normal Distribution]({{"/2021/04/08/log-normal-distribution" | relative_url}})
8. [Weibull Distribution (optional)]({{"/2021/04/10/weibull-distribution" | relative_url}})

</div>

# Uniform Distribution



<span class="statement-title">Definition.</span> Uniform Distribution<br>

We say that $X$ is a \<**uniform RV**\> on $[a, b]$ if its pdf $f(x)$ is given by

$$
f(x) = \begin{cases}
    \dfrac{1}{b-a} & x \in (a, b) \\
    \quad 0 & \text{else}
\end{cases}
$$

이런 \<Uniform RV\> $X$를 $X \sim \text{Unif}(a, b)$라고 표기한다.

cdf $F(x)$를 구해보면,

$$
F(x) = \int^x_{\infty} f(t) dt = \begin{cases}
    \quad 0 & \text{if } x < a \\
    \dfrac{x-a}{b-a} & \text{if } a \le x < b \\
    \quad 1 & \text{if } x \ge b
\end{cases}
$$

평균 $E[X]$는 $\dfrac{a+b}{2}$, 분산 $\text{Var}(X) = \dfrac{(b-a)^2}{12}$이다. 천천히 손으로 유도해보면 쉽게 구할 수 잇으니 여기서 과정을 기술하지는 않겠다.

If $U \sim \text{Unif}(0, 1)$, then $X := aU + b \sim \text{Unif}(b, a + b)$.

If $X \sim \text{Unif}(a, b)$, then $U := \dfrac{X-a}{b-a} \sim \text{Unif}(0, 1)$.

<hr/>

이어지는 포스트에서는 좀더 다양하고, 엄청난 분포들을 만나게 된다.

- [Exponential Distribution]({{"/2021/03/31/exponential-distribution" | relative_url}})
- [Gamma Distribution]({{"/2021/04/05/gamma-distribution" | relative_url}})
- [Chi-square, Beta and Log-normal Distribution]({{"/2021/04/06/chi-and-beta-and-lognormal-distribution" | relative_url}})
