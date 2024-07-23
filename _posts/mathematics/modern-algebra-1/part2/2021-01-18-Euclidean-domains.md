---
title: "Euclidean Domain"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br>

이번 파트에서는 *Division Algorithm* 에 대한 일반화를 다룬다.

<br>
<hr>

### Euclidean Norm & Euclidean Domain

<br><span class="statement-title">Definition.</span><br>

<div class="notice" markdown="1">

A **<u>Euclidean norm</u>** on an integral domain $D$

is a function $\nu$ mapping the non-zero elements of $D$ into the non-negative integers

such that the following conditions are satisfied:

1. For all $a, b \in D$ with $b \ne 0$, there exist $q$ and $r$ in $D$ s.t. $a = bq + r$, where either $r = 0$ or $\nu(r) = \nu(b)$.

2. For all non-zero $a, b \in D$, $\nu(a) \le \nu(ab)$.

</div>

"Euclidean norm"을 다른 말로 "Euclidean valuation"이라고도 한다.


<br><span class="statement-title">Definition.</span><br>

<div class="notice" markdown="1">

An integral domain $D$ is an "Euclidean Domain"

if $\exists$ a Euclidean norm on $D$.

</div>

<br><span class="statement-title">Example.</span><br>

<div class="math-statement" markdown="1">

$$
\begin{aligned}
\mathbb{Z}^{*} &\longrightarrow \{ 1, 2, \cdots, \}\\
n &\longmapsto \left| n \right|
\end{aligned}
$$

</div>

<br><span class="statement-title">Example.</span><br>

<div class="math-statement" markdown="1">

$$
\begin{aligned}
F[x]^{*} &\longrightarrow \{0, 1, 2, \cdots \} \\
f(x) &\longmapsto \deg (f(x))
\end{aligned}
$$

</div>

<br>
<hr>

<br><span class="statement-title">Theorem 46.4</span><br>

<div class="notice" markdown="1">

Every Euclidean Domain is a PID.

</div>

<br><span class="statement-title">proof.</span><br>
<details>
<div class="math-statement" markdown="1">

Let $D$ be a Euclidean Domain with a Euclidean norm $\nu$.

Let $N$ be an ideal in $D$.

If $N = \\{ 0 \\}$, then $N = \left< 0 \right>$ and $N$ is principal.

Supp. that $N \ne \\{ 0 \\}$.

Then there exist some $b \ne 0$ in $N$ s.t. $\nu (b) \le \nu (n)$ for all $n \in N$.

Claim: $N = \left< b \right>$.

Let $a \in N$, then by Condition 1 for E.D.,

there exist $q$ and $r$ in $D$ s.t.

$$
a = bq + r
$$

where either $r = 0$ or $\nu (r) < \nu (b)$.

$r = a - bq$에 대해 $a, b \in N$이므로 $r \in N$이다.($\because$ $N$ is an ideal)

minimal $\nu(b)$로 $b$를 골랐으므로 $r$이 $\nu(r) < \nu(b)$인 경우는 불가능하다. 따라서 $r = 0$이다.

따라서 $a = bq$이다.

이것은 Ideal $N$이 principal ideal $\left< b \right>$임을 의미한다. $\blacksquare$

</div>
</details>


<br><span class="statement-title">Corollary 46.5</span><br>

<div class="notice" markdown="1">

Every Euclidean Domain is a UFD.

</div>

위의 정리를 통해 E.D.가 PID임을 보였다. 하지만, 일반적으로 반대 명제는 성립하지 않는다!!

PID가 E.D.가 되지 안는 경우를 찾는 것은 생각보다 쉽진 않다...

<br>
<hr>

#### Arithmetic in Euclidean Domains

<br><span class="statement-title">Theorem 46.5</span><br>

<div class="notice" markdown="1">

For a E.D. with Euclidean norm $\nu$,

1. $\nu(1)$ is minimal among all $\nu(a)$ for non-zero $a \in D$.

2. $u \in D$ is a unit $\iff$ $\nu(u) = \nu (1)$.

</div>

<br><span class="statement-title">proof.</span><br>
<details>
<div class="math-statement" markdown="1">

(1번 명제에 대한 증명)

Euclidean norm $\nu$의 두번재 조건에 의하면 아래가 성립한다.

$$
\nu(1) \le \nu(1a) = \nu(a)
$$

$\blacksquare$

<br>

(2번 명제에 대한 증명)

if $u$ is a unit in $D$, then

$$
\nu (u) \le \nu (u u^{-1}) = \ne (1)
$$

반대로 $\nu (u) = \nu (1)$라면, division algorithm에 의해 아래의 식이 성립한다.

$$
1 = uq + r
$$

where either $r=0$ or $\nu(r) < \nu(u)$

이때, $\nu(1)$은 E.D.의 모든 원소에 대해 minimal이고, $\nu(u) = \nu(1)$이므로 $\nu(r) < \nu(u)$인 경우는 불가능하다.

따라서 $r=0$이 되고, $1 = uq$이므로 $u$는 unit이다.

$\blacksquare$

</div>
</details>

<br>

<br><span class="statement-title">Example.</span><br>

<div class="math-statement" markdown="1">

For $\mathbb{Z}$, $\nu(n) = \left\| n \right\|$.

$\min \nu (n) = 1 = \nu (1)$.

when $\nu(n) = 1$, $n = 1, -1$.

그리고 $1, -1$은 $\mathbb{Z}$에서 unit이다.

</div>

<br><span class="statement-title">Example.</span><br>

<div class="math-statement" markdown="1">

$F[x]$, for non-zero $f(x)$, $\nu(f(x)) = \deg f(x)$.

$\min \ne(f(x)) = 0$.

$\nu(f(x)) = 0 = \nu (1)$ $\iff$ $f$: const

Therefore, $\mathcal{U}(F[x]) = F^{\*}$.

</div>

<br>
<hr>

#### Euclidean Algorithm

**"유클리드 알고리즘"**은 두 정수의 GCD를 찾는 알고리즘이다.

이 알고리즘을 정수 집합 $\mathbb{Z}$을 일반화한 Euclidean Domain에서 적용하여, "Euclidean Domain에서 GCD를 찾는 알고리즘"으로 일반화 할 수 있다!!

수업 때 다루지는 않았어서, 본 글에서는 짧게 언급하고 넘어가겠다.

<br>
<hr>

$\mathbb{Z}$와 $F[x]$는 대표적인 Euclidean Domain이다.

다음 포스트에선 조금 특별하고 중요한 Euclidean Domain인 "Gaussian Integer"에 대해 다룬다. [link]({{"2021/01/18/Gaussian-Integer" | relative_url}})