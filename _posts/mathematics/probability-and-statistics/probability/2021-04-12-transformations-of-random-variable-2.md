---
title: "Transformations of Random Variable - 2"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
---

“확률과 통계(MATH230)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Probability and Statistics](https://bluehorn07.github.io/categories/probability-and-statistics)에서 확인하실 수 있습니다 🎲
{: .notice--info}

<span class="statement-title">Theorem.</span><br/>

Let $X$ and $Y$ be independent RVs.

<big>**1\. Discrete Case**</big>

If $X$ and $Y$ are **discrete** with pmfs $f_X(x)$ and $f_Y(y)$,

then $X+Y$ has the pmf

$$
f_{X+Y} (z) = \sum_u f_X(u) f_Y (z-u)
$$

<br/>

<big>**2\. Continuous Case**</big>

If $X$ and $Y$ are **continuous** with pdfs $f_X(x)$ and $f_Y(y)$,

then $X+Y$ has the pdf

$$
f_{X+Y}(z) = \int_u f_X(u) f_Y(z-u) du
$$

[2]번 명제의 경우, 증명하려면 상당한 엄밀성이 요구된다고 정규 수업에서는 [2]번에 대한 증명을 다루진 않았다.

<span class="statement-title">*Proof*.</span> [1]<br/>

<div class="math-statement" markdown="1">

$$
\begin{aligned}
U &= X \\
V &= X + Y
\end{aligned}
$$

$$
f_{U, V} (u, v) = f_{X, Y} (u, v-u) = f_X(u) f_Y(v-u)
$$

따라서,

$$
f_V (v) = \sum_u f_{U, V} (u, v) = \sum_u f_X(u) f_Y(v-u)
$$

$\blacksquare$

</div>

<br/>

<span class="statement-title">Example.</span><br/>

Let $X \sim \text{Gamma}(n, \beta)$, $Y \sim \text{Gamma}(m, \beta)$, and $X \perp Y$.

Find the pdf of $X+Y$.

<div class="math-statement" markdown="1">

$$
\begin{aligned}
f_{X+Y} (Z) &= \int_u f_X (u) f_Y (z-u) \, du \\
            &= \frac{1}{\Gamma(n) \cdot \beta^n} \frac{1}{\Gamma(m) \cdot \beta^m} \cdot \int^{\cancelto{z}{\infty}}_0 u^{n-1} e^{-u/\beta} \cdot (z-u)^{m-1} e^{-(z-u))/\beta} \, du \qquad (z - u > 0)\\
            &= \frac{1}{\Gamma(n) \cdot \beta^n} \frac{1}{\Gamma(m) \cdot \beta^m} \cdot \int^z_0 u^{n-1} (z-u)^{m-1} \cdot e^{-z/\beta} \, du \\
            &= \frac{1}{\Gamma(n) \cdot \beta^n} \frac{1}{\Gamma(m) \cdot \beta^m} \cdot e^{-z/\beta} \cdot \int^z_0 u^{n-1} (z-u)^{m-1} \, du \\
\end{aligned}
$$

이때, $u = zy$로 치환적분해보자.

$$
\begin{aligned}
f_{X+Y} (Z) &= \frac{1}{\Gamma(n) \cdot \beta^n} \frac{1}{\Gamma(m) \cdot \beta^m} \cdot e^{-z/\beta} \cdot \int^z_0 u^{n-1} (z-u)^{m-1} \, du \\
  &= \frac{1}{\Gamma(n) \cdot \beta^n} \frac{1}{\Gamma(m) \cdot \beta^m} \cdot e^{-z/\beta} \cdot \int^1_0 (zy)^{n-1} (z-zy)^{m-1} \, z dy \\
  &= \frac{1}{\Gamma(n) \cdot \beta^n} \frac{1}{\Gamma(m) \cdot \beta^m} \cdot e^{-z/\beta} \cdot z^{n-1} z^{m-1} z \cdot \int^1_0 y^{n-1} (1-y)^{m-1} \, dy\\
  &= \frac{1}{\Gamma(n) \cdot \beta^n} \frac{1}{\Gamma(m) \cdot \beta^m} \cdot e^{-z/\beta} \cdot z^{n+m-1} B(n, m) \\
  &= \frac{1}{\cancel{\Gamma(n)} \cdot \beta^n} \frac{1}{\cancel{\Gamma(m)} \cdot \beta^m} \cdot e^{-z/\beta} \cdot z^{n-m-1} \frac{\cancel{\Gamma(n)} \cancel{\Gamma(m)}}{\Gamma(n+m)} \\
  &= \frac{1}{\Gamma(n+m) \beta^{n+m}} \cdot z^{n+m - 1} e^{-z\beta}
\end{aligned}
$$

따라서, $X+Y \sim \text{Gamma}(n+m, \, \beta)$인 것이다! $\blacksquare$

</div>

위의 예제를 활용하면, $\text{Gamma}(n, \beta)$가 $\text{Exp}(\beta)$의 generalization임을 쉽게 확인할 수 있다.

Let $X_1, \dots, X_n$ follows $\text{Exp}(\beta)$ and they are mutually independent.

We know $\text{Gamma}(1, \beta) = \text{Exp}(\beta)$.

Therefore,

$$
(X_1 + \cdots + X_n) \sim \text{Gamma}(n, \beta)
$$

<hr/>

### not 1-1 Transform

<div style="text-align: center; font-size: larger;" markdown="1">

$y = g(X)$ and $g$ is not 1-1

</div>

<span class="statement-title">Example.</span><br/>

Let $X$ be a **discrete** RV with $P(X=0) = P(X=2) = P(X=-2) = 1/3$.

Let $Y := X^2$. Find $f_Y(y)$.

Since $Y = X^2$, $Y = \\{ 0, 4\\}$

따라서,

(1) If $y=4$, then $x=2$ or $x=-2$

$$
P(Y = 4) = P(X^2 = 4) = P(X=2) + P(X = -2) = \sum_{x: g(x)=4} f_X (x) = 2/3
$$

(2) If $y=0$, then $x=0$

$$
P(Y = 0) = P(X^2 = 0) = P(X=0) = \sum_{x: g(x) = 0} f_X (x) = 1/3
$$

<br/>

<span class="statement-title">Example.</span><br/>

Let $X \sim N(0, 1)$. Find the pdf of $Y := X^2$.

$$
\begin{aligned}
P(Y \le y) &= P(X^2 \le y) \\
  &= P(-\sqrt{y} \le X \le \sqrt{y}) \\
  &= P(X \le \sqrt{y}) - P(X \le -\sqrt{y}) \\
  &= F_X (\sqrt{y}) - F_X (-\sqrt{y})
\end{aligned}
$$

위의 cdf 식에서 pdf를 유도해보면,

$$
\begin{aligned}
f_Y(y) &= \frac{d}{dy} P(Y \le y) \\
    &= \frac{d}{dy} F_X (\sqrt{y}) - \frac{d}{dy} F_X (-\sqrt{y}) \\
    &= f_X (\sqrt{y}) \frac{1}{2\sqrt{y}} + f_X (-\sqrt{y}) \frac{1}{2\sqrt{y}} \\
    &= \frac{1}{\sqrt{y}} \cdot f_X (\sqrt{y}) \qquad (\text{$f_X$가 우함수})
\end{aligned}
$$

<br/>

<span class="statement-title">Theorem.</span><br/>

Supp. $X$ has pmf or pdf $f_X (x)$.

Let $Y = g(X)$ where $g$ may not be 1-1.

Assume that the support for $f_X(x)$ can be partitioned into $k$ segments s.t., in each segment, $x_i = w_i(y)$ is 1-1 for $i=1, \dots, k$.

Then,

(1) when $X$ is discrete, $\displaystyle f_Y (y) = \sum^k_{i=1} f_X (w_i (y))$

(2) when $X$ ic continuous, $\displaystyle f_Y (y) = \sum^k_{i=1} f_X (w_i(y)) \cdot \left\| w'_i (y) \right\|$

<hr/>

이어지는 포스트에서는 RV의 momentum인 $E[X]$, $E[X^2]$를 생성하는 함수인 \<MGF; Momemtum Generating Function\>에 대해 다룬다. 🤩

👉 [Momemtum Generating Function]({{"/2021/04/13/momemtum-generating-function" | relative_url}})


