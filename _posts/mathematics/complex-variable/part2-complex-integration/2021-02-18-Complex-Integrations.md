---
title: "complex primitive function & ML-inequality"
toc: true
toc_sticky: true
categories: ["Complex Variable"]
---

2020-2학기, 대학에서 '응용복소함수론' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<span class="statement-title">Example.</span>

<div class="notice" markdown="1">

$$
\int_{C} \frac{1}{z} dz
$$

If $C: \left\| z \right\| = 1$, then (준식) = $2\pi i$.


이걸 확장하면,

$$
\int_{C} \frac{1}{(z-z_0)^m} dz, \quad (m=1, 2, ...)
$$

where $C: z(t) = z_0 + r e^{it}$ with $0 \le t \le 2\pi$.

If $m=1$, (준식) = $2\pi i$

If $m \ne 1$, (준식) = 0

(실제로 계산해보면, 저렇게 나옴!!)

</div>

<br/>

### Integrals of primitive functions


<div class="notice" markdown="1">

\<미적분의 기본정리 Fundamental thm of Calculus\>에 따르면,

$$
\int^{b}_{a} f(x) dx = F(b) - F(a)
$$

이다.

이것을 복소 적분에도 적용해보자.

$$
\begin{aligned}
    \int_{C} f(z) dz &= \int^{b}_{a} f(z(t))) \cdot z'(t) dt \\
    &= \int^{b}_{a} \frac{d}{dt} [F \circ z(t)] dt \\
    &= F(z(b)) = F(z(a))
\end{aligned}
$$

</div>

<span class="statement-title">Definition.</span>

<div class="notice" markdown="1">

Let $D$ be an open set in $\mathbb{C}$.

$f(z)$ is called a *primitive* function in $D$, <br/>
if it is **<u>continuous</u>** in $D$, and **<u>there is an analytic function $F(z)$</u>** s.t.

$$
F'(z) = f(z) \quad \textrm{in} \quad D
$$

</div>

<br/>

<span class="statement-title">Theorem.</span>

<div class="notice" markdown="1">

Let $f(z)$ be a primitive function in a domain $D$ with $F'(z) = f(z)$.

Let $C$ be a smooth curve in $D$ that begins at $z_1$ and ends at $z_2$.

Then

$$
\int_{C} f(z) dz = F(z_2) - F(z_1)
$$

즉, **primitive** function에 대해선 적분이 시작과 끝점에 의해서만 결정된다는 것!!

</div>


<span class="statement-title">Corollary.</span>

<div class="notice" markdown="1">

Let $C$ be a smooth closed curve in $D$.

Let $f$ be a primmitive function on $D$.

Then

$$
\oint_{C} f(z) dz = 0
$$

</div>

<br/>
<hr/>

### ML-inequality

ML-inequality는 적분의 절댓값의 상한을 계산하게 하는 아주아주아주 좋은 도구다!!

<span class="statement-title">Theorem.</span>

<div class="notice" markdown="1">

Let $L$ be the length of $C$.

If $\left\| f(z) \right\| \le M$ for all $z \in C$,

then

$$
\left| \int_{C} f(z) dz \right| \le ML
$$

</div>

얼핏 보면, 미적분학의 중간값 정리와 비슷하다.

<div class="notice" markdown="1">

$$
\left| \int^{b}_{a} f(x) dx \right| \le M(b-a)
$$

$M = \textrm{sup} \left\| f(x) \right\| \quad x \in (a, b)$

</div>

<span class="statement-title">proof.</span>

<div class="math-statement" markdown="1">

$$
\begin{aligned}
\left| \int_{C} f(z) dz \right| &= \left| \int^{b}_{a} f(z(t)) \frac{dz}{dt} dt \right| \\
&\le \int^{b}_{a} \left| f(z(t)) \right| \left| \frac{dz}{dt} \right| dt \\
&\le M \cdot L
\end{aligned}
$$

</div>