---
title: "Chi-sqaure, Beta and Log-normal Distribution"
layout: post
use_math: true
tags: ["Probability"]
---


2021-1학기, 대학에서 '확률과 통계' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- [Chi-square Distribution](#chi-square-distribution) $\chi^2(n)$
- [Beta Distribution](#beta-distribution); $\text{Beta}(\alpha, \beta)$
- [Log-normal Distribution](#log-normal-distribution) $\text{LN}(\mu, \sigma^2)$

<hr/>

## Chi-square Distribution

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

<span class="statement-title">Remark.</span><br/>

1\. If $Z \sim N(0, 1)$, then $Z^2 \sim \chi^2(1)$.

<div class="math-statement" markdown="1">

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

2\. If $X \sim \chi^2(n)$, then

- $E[X] = n$
- $\text{Var}(X) = 2n$

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

이어지는 포스트에서는 \<**Weibull Distribution**\>을 통해 \<결함률; Failture rate\>와 \<신뢰도; Reliability\>을 모델링한다. 이 부분은 정규 수업에서는 소개만 하고 넘어간 부분이기 때문에 관심이 있거나 꼭 필요한게 아니라면 건너 뛰어도 괜찮다.

👉 [Weibull Distribution (Optional)]({{"/2021/04/10/weibull-distribution.html" | relative_url}})
