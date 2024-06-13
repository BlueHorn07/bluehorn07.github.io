---
title: "Residue Theorem"
toc: true
toc_sticky: true
categories: ["Complex Variable"]
---


2020-2학기, 대학에서 '응용복소함수론' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

**Residue**를 정의하기 위해선 먼저 Singular point에 대해 알아야 합니다.

#### Singular point

**<u>Definition.</u>** singular point<br>
$z_0$ is called a **<u>singular point</u>** IF $f(z)$ is not analytic at $z_0$.

**<u>Definition.</u>** isolated singular point<br>
IF there is $r>0$ such that $f(z)$ is analytic on $0<\lvert z-z_0 \rvert < r$, THEN a singular point $z_0$ is said to be **<u>isolated</u>**.

즉, 한 점에서만 singular하고 근방에선 analytic 하다면, **<u>isolated singular point</u>**라고 한다.

<br>

**<u>Example.</u>**<br>
$$
f(z)=\frac{z-1}{z^5(z+9)}
$$

**<u>Sol.</u>**<br>
분자, 분모가 polynomial이므로 분모가 0인 지점에서 singular point! 따라서 singular point is $z=0$ & $z=-9$

<br>

일반적으로 Domain $D$에서 singular point가 유한개라면, 모두 **isoloated** 이다.

**<u>Property.</u>**<br>
IF $f(z)=\frac{P(z)}{Q(z)}$, $P$, $Q$: polynomials, THEN all singular points are isolated.

**<u>proof.</u>**<br>
Polynomial은 해가 유한하기 때문 $\blacksquare$

<br>

**<u>Example.</u>** Not isolated singular points<br>
$$
f(z)= \textrm{Log} \, z = \log r + i\theta \quad (r>0, \; -\pi<\theta<\pi)
$$

**<u>Sol.</u>**<br>
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Complex_log.jpg/300px-Complex_log.jpg" style="width: 25%;">

branch cut이 모두 singular point에 해당한다. 이때, branch cut은 $x<0$인 모든 실수이기 때문에 그 갯수가 무한하고, not isolated singular points이다.

<br>

**<u>Example.</u>** Infinitely many isolated singular points<br>
$$
f(z)=1/\sin(\pi/z)
$$

**<u>Sol.</u>**
$\sin(\pi/z)=0$이 되는 지점을 생각해보면,

$$
\begin{aligned}
  \pi/z &= \pm n\pi \\
  1/z &= \pm \pi \\
  z &= 1/{\pm n}
\end{aligned}
$$

이때 $z$가 원점에 근접해 가지만, 어떤 singular point를 잡더라도 적당한 $r$을 잡아 analytic region을 잡을 수 있으므로 모두 isolated singular point이다.

단, $z=0$은 not isolated singular point이다!

<hr>

#### (review) Laurent series
Supp. that a function $f$ is **<u>analytic on an annular region</u>** $0<\lvert z-z_0\rvert < R$, THEN $f(z)$ can be represented as the **<u>Laurent series</u>**.

$$
f(z)=\sum^{\infty}_{n=0}{a_n(z-z_0)^n} + \sum^{\infty}_{n-1}{\frac{b_n}{(z-z_0)^n}}
$$

**<u>Coefficients of the Laurent series</u>**<br>

$$
a_n=\frac{1}{2{\pi}i} \oint_{C} {\frac{f(w)}{(w-z_0)^{n+1}} dw} \quad \textrm{and} \quad b_n=\frac{1}{2{\pi}i} \oint_{C} {f(w)(w-z_0)^n-1 dw}
$$

where $C$ is any simple closed contour around $z_0$ with CCW orientation that lies in $0<\lvert z-z_0\rvert < R$

<br>

이때, 주목할 점은 로랑 급수의 계수 $b_1$이다!

$$
b_1 = \frac{1}{2{\pi}i} \oint_{C} {f(w) dw}
$$

즉, 함수 $f(z)$의 적분값을 구하고 싶다면, 로랑 급수의 계수 $b_1$을 확인하면 된다는 의미가 된다!!

### Residue

이제 Residue를 정의해보자.

<br>

**<u>Definition.</u>** residue<br>
$b_1$ is called the **<u>residue</u>** of $f(z)$ at the isolated singular point $z_0$.

$$
b_1 = \underset{z=z_0}{\textrm{Res}} f(z)
$$

즉, residue는 로랑 급수의 계수 $b_1$이라는 것이다!

<br>

**<u>Note.</u>** <br>

$$
\begin{aligned}
  b_1 &= \frac{1}{2{\pi}i} \oint_{C} {f(w) dw} \\
  \oint_{C} {f(w) dw} &= 2{\pi}i \times b_1 \\
\end{aligned}
$$

즉, Residue로 적분값을 구할 수 있고, 적분값으로 Reside를 구할 수 있다!

<hr>

### Cauchy's residue Theorem

$$
\oint_{C} {f(z) dz}
$$

위의 Contour 적분은 다음과 같이 계산할 수 있다.

1\. IF $f(z)$ is analytic on $C$ and inside of $C$,

$$
\oint_{C} {f(z) dz} = 0
$$

[^1]

2\. IF $f(z)$ has one **singular point** $z_0$ inside $C$ and it is analytic on $C$ and inside of $C$

$$
\oint_{C} {f(z) dz} = 2{\pi}i \left(\underset{z=z_0}{\textrm{Res}} f(z) \right)
$$

<br>

**<u>Example.</u>** two singular point case<br>
만약 $C$ 내부에 두 개의 singular points, 또는 그보다 많은 singular points가 있다면 어떻게 될까?

**<u>Sol.</u>**<br>
Divide integral into two smaller contour integrals!

$$
\begin{aligned}
  \oint_{C} {f(z) dz} &= \oint_{C_1} {f(z) dz} + \oint_{C_2} {f(z) dz} \\
  &= 2{\pi}i{\left( \underset{z=z_1}{\textrm{Res}} f(z) + \underset{z=z_2}{\textrm{Res}} f(z) \right)}
\end{aligned}
$$

$n$개의 singular point가 있을 때에도 하나의 singular point를 포함하는 contour 적분으로 분리해 계산하면 된다.

<br>

**<u>Theorem.</u>** Caucy's residue theorem<br>
Let $C$ be a simple closed contour with CCW orientation. IF $f(z)$ is analytic inside of $C$ and on $C$ except for a finite number of singular points $z_k$ $(k=1, 2, \dots, n)$ inside $C$, THEN

$$
\oint_{C} {f(z) dz} = 2{\pi}i \left( \sum^{n}_{k=1} {\underset{z=z_k}{\textrm{Res}} f(z)} \right)
$$

<hr>

**<u>Example.</u>** <br>

$$
\oint_{C} {\frac{e^{z}-1}{z^3} dz}
$$

where $C$ is the circle $\lvert z \rvert = 1$ with the positive orientation.

**<u>Sol.</u>** <br>
Set $f(z)=\frac{e^{z}-1}{z^3}$, THEN singular point is $z=0$.

Express $f(z)$ as the Laurent series with cetner $z=0$!

$$
\begin{aligned}
  \frac{e^{z}-1}{z^3} &= \frac{1}{z^3} \left( \left( 1+z+\frac{z^2}{2!}+\cdots \right) -1 \right)\\
  &= \frac{1}{z^3} \left( z+\frac{z^2}{2!}+\cdots \right) \\
  &= \frac{1}{z^2} + \frac{1}{2!}\frac{1}{z} + \frac{1}{3!} + \frac{1}{4!}z + \cdots
\end{aligned}
$$

THEN, $b_1=\frac{1}{2!}$.

Therefore, by residue thm,

$$
\begin{aligned}
\oint_{C} {\frac{e^{z}-1}{z^3} dz} &= 2{\pi}i \times b_1 \\
&= 2{\pi}i \times \frac{1}{2!} = {\pi}i
\end{aligned}
$$

$\blacksquare$

<br>

**<u>Example.</u>** <br>

$$
\oint_C {\frac{4z-5}{z(z-1)} dz}
$$

where $C$ is the circle $\lvert z \rvert = 2$ with the positive orientation.

**<u>Sol.</u>** <br>
생-략

<hr>

### 맺음말

Residue Thm을 잘 활용하면, 실수 영역에서 풀면 복잡했던 적분들을 복소수 영역에서 쉽게 풀 수 있다!!

가우스 적분이 좋은 예이다.

$$
\int^{\infty}_{-\infty} {e^{-z^2} dz}
$$

<hr>

[^1]: Talyer 정리에 의하면, analytic 함수는 양의 차수를 가진 다항식으로 전개할 수 있으므로 $b_1=0$이다. 그래서 residue thm은 singular point 여부와 상관없이 기술할 수도 있다.


