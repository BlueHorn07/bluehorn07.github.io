---
title: "Complex Analysis - Basic"
toc: true
toc_sticky: true
categories: ["Complex Variable"]
---


2020-2학기, 대학에서 '응용복소함수론' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- Complex Functions
- Complex Limit
- Complex Continuity
- Complex Derivatives
- Analytic Functions

<hr>

### Complex Functions

<div class="notice" markdown="1">

A function $f$ defined on $S \subset \mathbb{C}$ is a rule that assigns to each $z \in S$ to a complex number $w$.

$$
f: S \longrightarrow \mathbb{C}
$$

</div>

<br><span class="statement-title">Example.</span><br>

- $f(z) = \frac{1}{z}$, $z \ne 0$
- $f(z) = z^2$

복수 함수는 정의역도 2차원이고, 공역도 2차원이기 때문에 하나의 그래프에 visualization 하는 것이 불가능함!!

<br>

<div class="notice" markdown="1">

복소 함수는 real-part와 imaginary-part를 각각 두 개의 real-valued function으로 분리해 표현할 수도 있음.

$$
f(z) = u(x, y) + i v(x, y)
$$

</div>

<br><span class="statement-title">Example.</span><br>

$f(x) = z^2$, $z = x + iy$

-> $(x+iy)^2 = x^2 - y^2 + i(2xy)$

<br>

<div class="notice" markdown="1">

복소 함수를 polar coordinate로 표현할 수도 있음!

$$
f(z) = u(r, \theta) + i v(r, \theta)
$$

</div>

<br><span class="statement-title">Example.</span><br>

$f(z) = z^2$, $z = r e^{i\theta}$

-> ${r^2}e^{2\theta} = r^2 \cos 2\theta + i (r^2 \sin 2\theta)$

<br>

<div class="notice" markdown="1">

복소 함수의 경우, 몇몇 경우에서는 "multi-valued relation"이 유도될 수 있음.

예를 들면,

함수 $f(z)$가 $f(z) = z^{1/2}$라면,

$$
z^{1/2} = \pm \sqrt{r} \exp {(i\theta / 2)}
$$

가 된다.

즉, $f(z) = z^{1/2}$에 대해서는 하나의 복소수 $z$에 대해 두 개의 함숫값 $f(z)$이 존재할 수 있다는 것이다.

이렇듯, 몇몇 복소 함수는 "multi-valued relation"을 보이기도 하는데, 보통은 multi-valued relation을 single-valued relation으로 적절히 restriction하여 해결한다.

</div>

<hr>

### Complex Limit

<div class="notice" markdown="1">

As $z$ approaches to $z_0$, $f(z)$ approaches to $w_0$.

$$
\lim_{z \rightarrow z_0} {f(z)} = w_0
$$

실수 함수에서의 극한은 "$\epsilon$-$\delta$ 논법"에 의해 정의가 되었다. 복소 함수에서의 극한 역시 "$\epsilon$-$\delta$ 논법"을 사용한다.

For each $\epsilon > 0$, there is $\delta$ such that

$$
\left| f(z) - w_0 \right| < \epsilon \quad \textrm{whenever} \quad 0 < \left| z - z_0 \right| < \delta
$$

즉, 공역 위에서 어떤 $\epsilon$을 잡더라도, 정의역에서 위의 부등식을 만족하는 적절한 $\delta$를 잡을 수 있다는 것을 말한다.

</div>

<div class="notice" markdown="1">

단, 극한이 존재하지 않는 경우도 있다. 이 경우는 아래와 같이 묘사한다.

"There exist a sequence $(z_n) \quad (z_n \ne z_0)$ s.t. $z_n \rightarrow z_0$ but $\left\| f(z_n) \rightarrow w_0 \right\| \ge \epsilon > 0$ for some $\epsilon$"

즉, $z_n$이 아무리 $z_0$에 가깝게 다가가도 $f(z_n)$와 $w_0$ 사이에 적어도 $\epsilon$ 만큼의 간격이 존재하는 것이다!

또 다르게 표현하자면, 극한이 존재할 때는 '모든' $\epsilon$에 대해 부등식을 만족하는 $\delta$를 찾을 수 있지만, 극한지 존재하지 않을 때는 '어떤' $\epsilon$에 대해선 부등식을 만족하는 $\delta$를 찾을 수 없다는 말이기도 하다!

</div>

<br><span class="statement-title">Example.</span><br>

(1) $f(z) = 2 \overline{z}$, $\lim_{z \rightarrow i} f(z) = ?$

$$
\begin{aligned}
    \left| f(z) - f(i) \right| &= \left| 2\overline{z} - 2 \overline{i} \right| \\
    &= \left| 2z - 2i \right| \\
    &= 2 \left| z - i \right|
\end{aligned}
$$

So, $\left\| f(z) - f(i) \right\| < \epsilon$ whenevery $\left\| z - i \right\| < \frac{\epsilon}{2}$.

<br>

(2) $f(z) = \frac{z}{\bar{z}}$, $\lim_{z \rightarrow 0} f(z) = ?$

- (i) Let $z = x$, $f(z) = \frac{x}{x} = 1$
- (ii) Let $z = iy$, $f(z) = \frac{iy}{-iy} = -1$

서로 다른 방향에서의 얻는 극한값이 일치하지 않기 때문에 극한이 존재하지 않는다.

<br>

<div class="notice" markdown="1">

**Theorem 1.** When a 'limit' of a function $f(z)$ exists at a point $z_0$, then it is unique. <br>
<small>(극한값이 2개가 될 수 없다.)</small>



**Theorem 2.** $f(z) = u(x, y) + i v(x, y)$, $z_0 = x_0 + i y_0$, $w_0 = u_0 + i v_0$.

$$
\lim_{(x, y) \rightarrow (x_0, y_0)} u(x, y) = w_0, \lim_{(x, y) \rightarrow (x_0, y_0)} v(x, y) =  u_0 \iff \lim_{z \rightarrow z_0} f(z) = w_0
$$

즉, real & imaginary part가 각각 극한을 가지면, $f(z)$도 극한을 가지며 그 값은 위와 같다.

</div>

<hr>

### Complex Continuity

<div class="notice" markdown="1">

$f(z)$ is continuous at $z_0$ if "$f(z_0)$ is defined" and "$\lim_{z \rightarrow z_0} f(z) = f(z_0)$".

For each $\epsilon > 0$, there exist $\delta$ such that

$$
\left| f(z) - f(z_0) \right| < \epsilon \quad \textrm{whenever} \quad \left| z - z_0 \right| < \delta
$$

</div>

<div class="notice" markdown="1">

Any polynomial $P(z)$ is continuous everywhere.

</div>

<div class="notice" markdown="1">

**Theorem 1.** A composition of continuous functions is continuous.

<br>

**Theorem 2.** $f(z) = u(x, y) + i v(x, y)$, $z_0 = x_0 + i y_0$.

$f(z)$ is continuous at $z_0$

$\iff$ $u(x, y)$ and $v(x, y)$ are conti. at $(x_0, y_0)$.

<br>

**Theorem 3.** Let $R$ be a closed and bounded set.

Supp. that $f$ is "continuous" on $R$.

Then there exist $M$ such that

$$
\left| f(z) \right| \le M \quad \textrm{for all} \;\; z \in R
$$

"Theorem 3"은 복소 평면에서의 "최대-최소 정리"라고 볼 수 있다!

</div>

<br/>
<hr/>

### Complex Derivatives

<div class="notice" markdown="1">

The derivative of $f$ at $z_0$ is the limit

$$
f'(z_0) = \lim_{z \rightarrow z_0} {\frac{f(z) = f(z_0)}{z-z_0}}
$$

The function $f$ is said to be "**differentiable**" when $f'(z_0)$ exists.

</div>

**NOTE**: differentiable $\ne$ analytic

### Analytic Functions

<div class="notice" markdown="1">

- $f(z)$ is **<u>analytic</u>** in an open set $S$, if $f(z)$ is differentiable everywhere in $S$.

- $f(z)$ is **<u>analytic</u>** at $z_0$, if $f(z)$ is analytic in some neightborhood of $z_0$.

- An **<u>entire function</u>** is a function that is analytic at each point in the entire complex plane.

</div>

<br/>

<div class="notice" markdown="1">

* Polynomials are entire functions.

$$
P(z) = a_0 + a_1 z + \cdots + a_n z^n
$$

<hr/>

* Rational function

$$
f(z) = \frac{P(z)}{Q(z)} \quad (P, Q: \quad \textrm{polynomials})
$$

Rational functions are analytic except at the points where $Q(z) \ne 0$.

</div>

