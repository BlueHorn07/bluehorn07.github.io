---
title: "Gaussian Integer"
toc: true
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br>

Guassian Integer는 "Euclidean Domain"의 일종이다. Euclidean Domain에 대한 포스트는 [이곳]({{"2021/01/18/Euclidean-domains.html" | relative_url}})에서 확인할 수 있다.

1. [Gaussian Integers](#gaussian-integers)
2. [Multiplicative Norms](#multiplicative-norms)

<br>
<hr>

### Gaussian Integers

<br><span class="statement-title">Definition.</span><br>

<div class="statement" markdown="1">

A **<u>Gaussian Intger</u>** is a complex number $a + bi$, where $a, b \in \mathbb{Z}$.

For Gaussian integer $\alpha = a + bi$, the **<u>norm</u>** $N(\alpha) = a^2 + b^2$.

</div>

위의 Guassian Integer를 모두 모은 집합이 바로 $\mathbb{Z}[i] \subset \mathbb{C}$가 된다.

우리의 목표는 "Guassian Integers $\mathbb{Z}[i]$가 Euclidean Domain이 됨"을 보이는 것이다!

<br>
<hr>

<br><span class="statement-title">Lemma 47.2</span><br>

<div class="statement" markdown="1">

On $\mathbb{Z}[i]$, the following properties of Norm holds.

1. $N(\alpha) \ge 0$
2. $N(\alpha) = 0 \iff \alpha = 0$
3. $N(\alpha \beta) = N(\alpha)N(\beta)$

즉, $N$은 semi-group homomoprhism이다.

</div>

Guassian Norm $N$을 잘 생각해보면, 너무 당연한 명제들이다.

<br><span class="statement-title">Lemma 47.3</span><br>

<div class="statement" markdown="1">

$Z[i]$ is an Integral Domain.

</div>

<span class="statement-title">proof.</span><br>

<details>
<div class="math-statement" markdown="1">

$\mathbb{Z}[i]$는 commutative ring with unity이다.

$\mathbb{Z}[i]$가 Integral Domain임을 보이기 위해 zero-divisor가 존재하지 않음을 보여야 한다.

"Lemma 47.2"에 의해

if $\alpha \beta = 0$, then

$$
N(\alpha)N(\beta) = N(\alpha \beta) = N(0) = 0
$$

따라서 $\alpha \beta = 0$은 $N(\alpha) = 0$ 또는 $N(\beta) = 0$을 의미하한다.

다시 "Lemma 47.2"에 의해 위의 결과는 $\alpha = 0$ 또는 $\beta = 0$을 의미한다.

즉, zero-divisor가 존재하지 않으므로 $\mathbb{Z}[i]$는 Integral Domain이다.

</div>
</details>

<br><span class="statement-title">Theorem 47.4</span><br>

<div class="statement" markdown="1">

The function $\nu$ given by $\nu(\alpha) = N(\alpha)$ for non-zero $\alpha \in \mathbb{Z}[i]$ is an Euclidean norm on $\mathbb{Z}[i]$.

Thus $\mathbb{Z}[i]$ is an Euclidean Domain.

</div>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

Note taht for $\beta = b_1 + b_2 i \ne 0$, $N(\beta) = {b_1}^2 + {b_2}^2$, so $N(\beta) \ge 1$.

Then for all non-zero $\alpha, \beta \in \mathbb{Z}[i]$, $N(\alpha) \le N(\alpha)N(\beta) = N(\alpha \beta)$.

This proves **<u>Condition 2</u>** for a Euclidean norm.

<br>

이제 Euclidean norm의 첫번쨰 조건인 "division algorithm"에 대해 증명해야 한다.

Let $\alpha, \beta \in \mathbb{Z}[i]$ with $\alpha = a_1 + a_2 i$, $\beta = b_1 + b_2 i \ne 0$.

We must find $\sigma, \rho \in \mathbb{Z}[i]$ s.t. $\alpha = \beta \sigma \rho$, where either $\rho = 0$ or $N(\rho) < N(\beta)$.

Let $\alpha / \beta = r + si$ for $r, s \in \mathbb{Q}$. <small>(by $\mathbb{C}$ 아래 연산)</small>

<div class="img-wrapper">
<img src="https://upload.wikimedia.org/wikipedia/en/thumb/b/bb/Gauss-euklid.svg/500px-Gauss-euklid.svg.png" width="45%"><br>
</div>

Let $n, m \in \mathbb{Z}$ as close as possible to the rational numbers $r$ and $s$.

Let $\sigma = n + m i$ and $\rho = \alpha - \beta \sigma$.

If $\rho = 0$, we are done.

Otherwise, by construction of $\sigma$, we see that $\left\| r - n \right\| \le \frac{1}{2}$ and $\left\| s - m \right\| \le \frac{1}{2}$

Therefore,

$$
\begin{aligned}
N \left( \frac{\alpha}{\beta} - \sigma \right) &= N \left( (r+si) - (n+mi) \right) \\
&= N \left( (r - n) - (s - m)i \right) \le \left( \frac{1}{2} \right)^2 + \left( \frac{1}{2} \right)^2 = \frac{1}{2}
\end{aligned}
$$

Thus, we obtain

$$
\begin{aligned}
    N(\rho) &= N(\alpha - \beta \sigma) = N\left( \beta \left( \frac{\alpha}{\beta} - \sigma \right) \right) \\
    &= N(\beta)N\left( \frac{\alpha}{\beta} - \sigma \right) \le N(\beta) \frac{1}{2} < N(\beta)
\end{aligned}
$$

so, $N(\rho) < N(\beta)$.

Therefore, Gaussian norm $N$ is an Euclidean norm. $\blacksquare$

</div>

<br>

"$\mathcal{U}(\mathbb{Z}[i]) = \\{ \pm 1, \pm i\\}$"

당연히 그렇겠지만, $\mathbb{Z}[i]$는 $\mathbb{Z}$와는 다른 모습이 발견된다.

예를 들어 $\mathbb{Z}$에선 5가 irreducible인 반면,

$\mathbb{Z}[i]$에선 5가 $5 = (1 + 2i)(1 - 2i)$로 분해가능하다!

<br>
<hr>

### Multiplicative Norms

이번 섹션에서는 세심하게 정의된 norm은 Integeral Domain $D$의 artihmetic structure를 결정하는 데에 많은 도움을 준다는 사실을 살펴볼 것이다.

*Algebraic Number Theory*에선 이렇게 *norm* 을 통해 대수적인 구조를 파악하는 것이 빈번하다.

<br><span class="statement-title">Definition.</span><br>

<div class="statement" markdown="1">

Let $D$ be an integral domain.

A **<u>multiplicative norm</u>** $N$ on $D$ is a function mapping $D$ into the integers $\mathbb{Z}$ such that the following conditions are satisfied:

1. $N(\alpha) = 0 \iff \alpha = 0$
2. $N(\alpha \beta) = N(\alpha) N(\beta)$ for all $\alpha, \beta \in D$

</div>

<br><span class="statement-title">Theorem 47.7</span><br>

<div class="statement" markdown="1">

If $D$ is an integral domain with a multiplicative norm $N$,

then

1. $N(1) = 1$
2. $\left\| N(u) \right\| = 1$ for every unit $u \in D$.
3. If Every $\alpha$ s.t. $\left\| N(\alpha) \right\| = 1$ is a **<u>unit</u>** in $D$, <br>
   then an elt $\pi$ in $D$ with $\left\| N(\pi) \right\| = p$ for a **<u>prime</u>** $p$ is an **<u>irreducible</u>** of $D$. 🔥

</div>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

Let $D$ be an integral domain with a multiplicative norm $N$.

(1번 명제)

$$
N(1) = N(1 \cdot 1) = N(1) N(1)
$$

show that $N(1) = 1$

<br>

(2번 명제)

If $u$ is a unit in $D$, then

$$
1 = N(1) = N(u u^{-1}) = N(u) N(u^{-1})
$$

Since $N(u)$ is an integer, this implies that $\left\| N(u) \right\| = 1$.

<br>

🔥 3번 명제 🔥

Supp. that the units of $D$ are exactly the elements of norm $\pm 1$.

For $\pi \in D$ with $\left\| N(\pi) \right\| = p$ where $p$ is a prime.

Then if $\pi = \alpha \beta$, we have

$$
p = \left| N(\pi) \right| = \left| N(\alpha) N(\beta) \right|
$$

so either $\left\| N(\alpha) \right\| = 1$ or $\left\| N(\beta) \right\| = 1$.

By our assumption, this means $\alpha$ or $\beta$ is a unit.

Thus $\pi$ is an irreducible of $D$.

</div>

<br><span class="statement-title">Examples.</span><br>

In Gaussian Integers $\mathbb{Z}[i]$, $N(\alpha)$ is a multiplicative norm!

$1+2i$ and $1-2i$ are irreducibles.

반대로 $5$의 경우 $N(5) = 25$이기 때문에 reducible이 된다.

<br>
<hr>

<br><span class="statement-title">Example.</span> Integeral Domain, but not UFD 🔥<br>

<div class="math-statement" markdown="1">

Let $\mathbb{Z}[\sqrt{-5}] = \\{ a+ib\sqrt{5} \mid a, b \in \mathbb{Z} \\}$.

As a subset of the complex numbers $\mathbb{Z}[\sqrt{-5}]$ is an integral domain.

Define $N$ on $\mathbb{Z}[\sqrt{-5}]$ by

$$
N(a + b\sqrt{-5}) = a^2 + 5 b^2
$$

Clearly, $N(\alpha) = 0$ $\iff$ $\alpha = 0$.

Also $N(\alpha \beta) = N(\alpha) N(\beta)$.

이번에는 multiplicative norm의 특징에 비추어 $\mathbb{Z}[\sqrt{-5}]$의 unit를 생각해보자.

$N(\alpha) = a^2 + 5 b^2 = 1$이 되는 $\alpha$는 오직 $b=0$이고 $a = \pm 1$이어야 한다.

따라서 $\mathcal{U}(\mathbb{Z}[\sqrt{-5}]) = \\{ \pm  1\\}$이다.

&nbsp; 이제 $\mathbb{Z}[\sqrt{-5}]$의 원소인 21에 대한 factorization을 살펴보자.

21은 두 가지 factorization을 가질 수 있다.

- $21 = (3)(7)$
- $21 = (1+2\sqrt{-5})(1-2\sqrt{-5})$

이제 $3$, $7$, $1+2\sqrt{-5}$, $1-2\sqrt{-5}$가 irreducible임만 보인다면, $\mathbb{Z}[\sqrt{-5}]$가 UFD가 아님을 보일 수 있다.

<br>

($3$의 경우)

Supp. that $3 = \alpha \beta$, then

$$
9 = N(3) = N(\alpha) N(\beta)
$$

$N(\alpha)$가 가질 수 있는 값은 1 또는 3 또는 9이다.

- If $N(\alpha) = 1$, then $\alpha$ is a unit.
- $N(\alpha) = a^2 + 5 b^2$이므로 $N(\alpha) = 3$를 만족시킬 수 있는 $\alpha$는 존재하지 않는다.
- If $N(\alpha) = 9$, $\beta$ is a unit.

따라서 $3$은 irreducible이다.

비슷한 방법으로 $7$가 irreducible임도 보일 수 있다.

<br>

($1 + 2\sqrt{-5}$의 경우)

If $1 + 2\sqrt{-5} = \gamma \delta$, we have

$$
21 = N(1 + 2\sqrt{-5}) = N(\gamma)N(\delta)
$$

따라서 $N(\gamma)$는 1, 3, 7, 또는 21의 값을 갖는다.

앞에서 3과 7을 norm으로 갖는 수는 $\mathbb{Z}[\sqrt{-5}]$에 존재하지 않음을 확인했다.

따라서 $N(\gamma)$는 1 또는 21인데, 이것은 "either $\gamma$ or $\delta$ is a unit"라는 결과를 유도한다.

따라서 $1 + 2\sqrt{-5}$는 irreducible이다.

같은 방법으로 $1 - 2\sqrt{-5}$가 irreducible임도 보일 수 있다.

<br>

따라서 $\mathbb{Z}[\sqrt{-5}$는 Integral Domain이지만, Unique Factorization은 가지지 않는다! $\blacksquare$

</div>

<br>
<hr>

앞에서한 논의를 활용하면, "페르마의 두 제곱수 정리" <small>(Fermat's $p = a^2 + b^2$ Theorem)</small>을 증명할 수 있다!!

다음 포스트: [link]({{"2021/01/18/Fermat-thm-on-sums-of-two-squares.html" | relative_url}})
