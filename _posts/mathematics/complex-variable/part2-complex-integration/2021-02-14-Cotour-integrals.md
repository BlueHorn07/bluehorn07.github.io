---
title: "Contour Integrals"
layout: post
use_math: true
tags: ["Complex Variable"]
---


2020-2학기, 대학에서 '응용복소함수론' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- parametrized curves
- contour integrals

<hr/>

### Comlex Contour

<div class="statement" markdown="1">

$$
\int_{C} f(z) dz
$$

- $f(z)$: a complex function
- $C$: a curve in a complex plane

</div>

실수 영역 $\mathbb{R}^2$에서의 contour는 $\vec{r}(t)=(x(t), y(t))$와 같이 parametrized 하여 표현했다. 

그런데, 복소 영역 $\mathbb{C}$에서의 contour는 $z(t) = x(t) + i y(t)$와 같이 표현한다. $\mathbb{R}^2$에서와 '거의' 비슷하다.


#### derivatives and integrals

Let define $w(t)$ as $w(t) : [a, b] \rightarrow \mathbb{C}$

$$
w(t) = u(t) + i v(t)
$$

Then,

1\. derivatives

$$
w'(t) = u'(t) + i v'(t)
$$

2\. integrals

$$
\int^{b}_{a} w(t) dt = \int^{b}_{a} u(t) dt + i \int^{b}_{a} v(t) dt
$$


#### parametric curves

A *parametrized curve* is a continuous function $z(t): [a, b] \rightarrow \mathbb{C}$.

1\. *smooth*: "$z'(t)$ exists" and "is continuous" on $[a, b]$, and "$z'(t) \ne 0$".

2\. *piecewise smooth*: 생-략

3\. *closed*: $z(a) = z(b)$

4\. *simple*: if $t \ne s$, $z(t) \ne z(s)$

5\. *positive orientation*: counter clockwise


#### equivalent contour

For two curves $z_1(t)$, $z_2(t)$,

$$
z_1(t): [a, b] \rightarrow \mathbb{C}, \quad z_2(t): [c, d] \rightarrow \mathbb{C}
$$

are *equivalent*, if there is a function $t(s)$

$$
s \rightarrow t(s): [c, d] \rightarrow [a, b]
$$

so that $t'(s) > 0$ and $z_2(s) = z_1(t(s))$.

(cf) $t'(s) > 0$ 조건이 필요한 이유는, 만약 $t'(s) < 0$라면, 두 커브의 움직이는 방향이 달라지게 된다. 또한, 만약에 $t'(s) = 0$이라면, ${z_2}' = {z_1}' \frac{dt}{ds}$에서 $\frac{dt}{ds} = 0$이 되어서 올바른 값을 얻지 못하게 된다. (흠... 설명이 매끄럽지 못하네 ㅠㅠ)


#### Length of curve $C$

$$
\begin{aligned}
\textrm{length of } C &= \int^{b}_{a} \left| z'(t) \right| dt \\
&= \int^{b}_{a} \sqrt{\left(\frac{dx}{dt}\right)^2 + \left(\frac{dy}{dt}\right)^2} dt
\end{aligned}
$$

참고로, 이때 curve의 길이는 parametrization function에 의존하지 않는다. 즉, parameterization이 달라도 같은 길이를 가질 수 있다는 말이다.

<br/>
<hr/>

### Contour Integrals

<div class="statement" markdown="1">

Let $C$ be a smooth curve parametrized by $z(t): [a, b] \rightarrow \mathbb{C}$.

Then, the integral of $f$ along a curve $C$ is

$$
\int_{C} f(z) dz = \int^{b}_{a} f(z(t)) z'(t) dt
$$

</div>

이때, "curve $C$에 대한 함수 $f$의 contour 적분은 curve $C$의 parametrization에 의존하지 않는다." 즉, equivalent curve에 대한 적분을 동일한 결과를 뱉는다는 말이다.


#### Existence of Contour Integral

"If $f$ is continuous, then $\int_{C} f(z) dz$ exists"

<span class="statement-title">proof.</span>

<div class="math-statement" markdown="1">

$f(z) = f(x+iy) = u(x,y) + i v(x,y)$

$z(t): [a, b] \rightarrow \mathbb{C}$ is a parametized curve $C$.

Then

$$
\begin{aligned}
    \int_{C} f(z) dz &= \int^{b}_{a} \left[ u(x(t), y(t)) + i v(x(t), y(t)) \right] \left( x'(t) + i y'(t) \right) dt \\
    &= \int^{b}_{a} (ux' - vy') + i (uy' + vx') dt \\
    &= \int^{b}_{a} (ux' - vy') dt + i \int^{b}_{a} (uy' + vx') dt
\end{aligned}
$$

이때, $ux' - vy'$ 그리고 $uy' + vw'$가 continuous function이기 때문에 실수에서의 적분에 대한 유일성에 의해 Contour Integral on Complex Plane의 적분도 존재한다! $\blacksquare$

</div>



