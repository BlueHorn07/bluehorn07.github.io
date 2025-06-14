---
title: "Linear Fractional Transform"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Complex Variable"]
---


2020-2학기, 대학에서 '응용복소함수론' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

### Linear Fractional Transformation

$$
w = \frac{az + b}{cz + d}
$$

Linear Fractional Trans.의 각 케이스를 살펴보자!

<br>
<hr>

##### (1) $ad - bc = 0$

$$
\begin{aligned}
  ad - bc = 0 \iff \frac{a}{b} = \frac{c}{d} \quad (b, \; d \ne 0)
\end{aligned}
$$

만약 위와 같이 $ad - bc = 0$이라는 조건에서는 $w = C$; **<u>const transformation</u>**이 된다!

$$
w = \frac{\frac{a}{b} z + 1}{\frac{c}{d} z + 1} = 1 \quad (ad - bc = 0)
$$

이런 const transformation은 우리의 관심이 아니다!

<br>

##### Derivative of Linear Fractional Transform

$$
\begin{aligned}
\frac{dw}{dz} &= \frac{a \cdot (cz + d) - c \cdot (az + b)}{(cz + d)^2} \\
&= \frac{ad - bc}{(cz + d)^2}
\end{aligned}
$$

우리가 $ad - bc \ne 0$이라고 가정한다면, Linear Fractional Transform은 미분값이 $0$이 되지 않는다!!

<br>
<hr>

#### some special cases

##### (i) $c=0$, $d=1$

$w = \dfrac{az + b}{0z + 1} = az + b$; Linear Transformation

##### (ii) $a=d=0$, $b=c=1$

$w = \dfrac{0z + 1}{1z + 0} = \dfrac{1}{z}$; Inversion Transformation

<br>
<hr>

##### Inverse of Linear Fractional Transformation

$$
\begin{aligned}
  w &= \frac{az + b}{cz + d} \\
  w \cdot (cz + d) &= az + b \\
  (c \cdot w - a) z &= - d \cdot w + b \\
  z &= \frac{-d \cdot w + b}{c \cdot w - a}
\end{aligned}
$$

즉, Inverse Transform 역시 Linear Fractional Trnasformation이다!

<br>
<hr>

##### Linear Fractional Transform + Extended Complex Plane

$w = T(z) = \frac{az + b}{cz + d}$는 $z = -\frac{d}{c}$에서 정의되지 않는다. Linear Fractional Transform에서도 Extended Complex Plane을 도입하여 $T\left(-\frac{d}{c}\right) = \infty$로 정의한다.

- $T\left(-\frac{d}{c}\right) = \infty$
- $T\left(\infty\right) = \underset{z \rightarrow \infty}{\lim} \frac{az+b}{cz+d} = \frac{a}{c}$
  - 만약 $c=0$이라면, $T\left(\infty\right) = \infty$

<br>
<hr>

#### Properties of L.F. Transform

**<u>Statement.</u>**<br>

<div class="proof">

  $w = \frac{az+b}{cz+d}$ transforms (circles and lines) into (circles and lines).

</div>

<br>

**<u>proof.</u>**<br>

$$
\begin{aligned}
  w &= \frac{az+b}{cz+d} \\
  &= \frac{\dfrac{a}{c}(cz+d) + \left(b - \dfrac{a}{c}d\right)}{cz+d} \\
  &= \frac{a}{c} + \frac{k}{cz + d} \quad \left(k = b - \dfrac{a}{c}d\right)
\end{aligned}
$$

즉,

$$
z \longrightarrow cz + d \longrightarrow \frac{1}{cz+d} \longrightarrow k \cdot \frac{1}{cz+d} + \frac{a}{c}
$$

따라서 L.F. Trnasform은 Linear Trnasform과 Inversion Transform의 합성이다. $\blacksquare$

<br>

**<u>Exercise.</u>**<br>

<div class="proof">

  <b><u>Composition</u></b> of L.F. trnasform is also a L.F. transform.

</div>

<br>
<hr>

**<u>Definition.</u>**; Fixed points<br>

<div class="proof">

  For $z_0 \in \mathbb{C}$, IF $T(z_0) = z_0$, THEN $z_0$ is a <b><u>fixed point</u></b> of $T(z)$.

</div>


Let $T(z) = \frac{az+b}{cz+d}$, THEN

$$
\begin{aligned}
  &T(z_0) = \frac{az_0+b}{cz_0+d} = z_0 \\
  &\implies c {z_0}^2 + d z_0 = a z_0 + b \\
  &\implies c {z_0}^2 + (d-a) z_0 - b = 0
\end{aligned}
$$

$c {z_0}^2 + (d-a) z_0 - b = 0$는 2차 방정식으로 **<u>해가 많아야 2개</u>**다.

(i) $c\ne0$ : <br>
at most two fixed points

(ii) $c = 0$:

- $d-a \ne 0$ : $z_0 = -\frac{b}{d-a}$
- $d-a = 0$, $b\ne0$ : no fixed point
- $d-a = 0$, $b=0$ : $T(z) = z$; $\mathbb{C}$

<br>
<hr>

**<u>Lemma.</u>**<br>

<div class="proof">

Let $T_1(z) = \frac{az+b}{cz+d}$, and $T_2(z) = \frac{Az+B}{Cz+D}$ <br>

Supp. that $T_1(z_1) = T_2(z_1)$, $T_1(z_2) = T_2(z_2)$, and $T_1(z_3) = T_2(z_3)$ <br>

THEN,

$$
T_1(z) = T_2(z)
$$

</div>

즉, 두 Transform이 세 점에서 그 값이 같다면, 두 Transform은 동일하다는 말이다.

<br>

**<u>proof.</u>**<br>

$T^{-1}_1 \circ T_2$ is also a L.F. transform

THEN,

$$
\begin{aligned}
  T^{-1}_1 \circ T_2(z_1) = T^{-1}_1 (z_1) = z_1 \\
  T^{-1}_1 \circ T_2(z_2) = z_2 \\
  T^{-1}_1 \circ T_2(z_3) = z_3 \\
\end{aligned}
$$

즉, $T^{-1}_1 \circ T_2$는 fixed point를 3개 가지므로, $T^{-1}_1 \circ T_2(z) = z$이다.

따라서 $T^{-1}_1 (z) = T_2(z)$이다. $\blacksquare$

<br>

**<u>Theorem.</u>**<br>

<div class="proof">

Let $w=Tz$ be a L.F. transform s.t. $T(z_1) = w_1$, $T(z_2) = w_2$ and $T(z_3) = w_3$. <br>

THEN the mapping $Tz$ is given by

$$
\frac{w-w_1}{w-w_3} \cdot \frac{w_2-w_3}{w_2-w_1} = \frac{z-w_1}{z-w_3} \cdot \frac{z_2-w_3}{z_2-w_1}
$$

(IF one of these point is the point $\infty$, THEN 그 부분을 약분하여 1로 만들어라.)

$$
w_3 = \infty \implies \frac{w-w_1}{\cancel{w-w_3}} \cdot \frac{\cancel{w_2-w_3}}{w_2-w_1} = \frac{w-w_1}{w_2-w_1}
$$

</div>

<br>

**<u>proof.</u>**<br>
Let's define two L.F transformation $F(w)$, $G(z)$

$$
F(w) := \frac{w-w_1}{w-w_3} \cdot \frac{w_2-w_3}{w_2-w_1}, \quad G(z) := \frac{z-z_1}{z-z_3} \cdot \frac{z_2-z_3}{z_2-w_1}
$$

THEN,

$$
F(w_1) = 0, \quad F(w_2) = 1, \quad F(w_3) = \infty
$$

$$
G(z_1) = 0, \quad G(z_2) = 1, \quad G(z_3) = \infty
$$

Because $F(w)$, $G(z)$ are L.F. transform, $F^{-1} \circ G$ is also a L.F. transformation.

THEN,

$$
\begin{aligned}
  F^{-1} \circ G(z_1) = F^{-1} (0) = w_1 \\
  F^{-1} \circ G(z_2) = F^{-1} (1) = w_2 \\
  F^{-1} \circ G(z_3) = F^{-1} (\infty) = w_3 \\
\end{aligned}
$$

따라서 $F^{-1} \circ G$가 바로 우리가 찾는 L.F. transformation이다!

$$
\begin{aligned}
  F^{-1} \circ G (z) &= w \\
  G(z) &= F(w) \\
  \frac{z-w_1}{z-w_3} \cdot \frac{z_2-w_3}{z_2-w_1} &= \frac{w-w_1}{w-w_3} \cdot \frac{w_2-w_3}{w_2-w_1}
\end{aligned}
$$
