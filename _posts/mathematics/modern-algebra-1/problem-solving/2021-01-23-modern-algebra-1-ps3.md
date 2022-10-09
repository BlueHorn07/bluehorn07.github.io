---
title: "Modern Algebra I - PS3"
layout: post
tags: ["Modern Algebra1", "Problem Solving"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

<div class="statement" markdown="1">

Artin, p.442, sec.2, #2a.

The ring $\mathbb{Z}[\zeta]$, for $\zeta = e^{2 \pi i / 3}$, is a Euclidean Domain.

</div>

<br>

The Norm function $N$ would be complex modulus $\left\| \cdot \right\|$ square.

1. Check $N$ is integer valued on $\mathbb{Z}[\zeta]$.
2. The Division Algorithm works.

<br>

<big>(1) $N$ is integer valued.</big>

$\zeta$는 root of unity 중 하나 이므로 아래가 성립한다.
- $\zeta^3 = 1$
- $\zeta^2 = 1 - \zeta$

따라서 $\mathbb{Z}[\zeta]$의 원소는 아래와 같은 형태로 기술된다.

$$
a + b \zeta \quad \textrm{for some} \quad a, b \in \mathbb{Z}
$$

<small>($1$이 $\left< \zeta \right>$에 포함되어 있기 때문!)</small>

또한, $\zeta = -\frac{1}{2} + \frac{\sqrt{3}}{2}i$이므로, Norm $N$은

$$
\begin{aligned}
  \left| a+b\zeta \right|^2 &= \left| \left(a - \frac{b}{2}\right) + \frac{\sqrt{3}b}{2}i\right|^2 \\
  &= a^2 -ab + \frac{b^2}{4} + \frac{3b^2}{4} \\
  &= a^2 - ab + b^2 \ge (a - b)^2 \ge 0
\end{aligned}
$$

따라서 $N$은 integer valued function이다!

<br>

<big>(2) Division Algorithm</big>

For $a, b \in \mathbb{Z}[\zeta]$ and $a \ne 0$, check that there exist $q, r \in \mathbb{Z}[\zeta]$ s.t.

$$
b = qa + r
$$

where $N(r) < N(a)$.

Let $q$ be the closest point to $b/a$ on $\mathbb{Z}[\zeta]$.

Let $r$ be $r = b - qa$.

Then check that 

$$
\left| r \right| = \left| b - qa \right| = \left| \frac{b}{a} - q \right| \left| a \right|
$$

이때, $\left\| \frac{b}{a} - q \right\|$에 대해 생각해보자.

$\dfrac{b}{a}$를 제대로 구하면, $\dfrac{b}{a} = s + t \zeta \in \mathbb{Q}[\zeta]$이다.

이때 $q$를 $\dfrac{b}{a}$에 가장 가까운 $\mathbb{Z}[\zeta]$로 설정했으므로 

$$
q = x + y\zeta, \quad \textrm{where} \quad \left|x-s\right| \le \frac{1}{2} \quad \textrm{and} \quad \left|y-t\right| \le \frac{1}{2}
$$

따라서 $\left\| \frac{b}{a} - q \right\|$는

$$
\begin{aligned}
\left| \frac{b}{a} - q \right|^2 &= \left| (s+t\zeta) - (x+y\zeta) \right|^2 \\
&= \left| (s-x) + (t-y)\zeta \right|^2 \\
&= \left| \left((s-x) - \frac{(t-y)}{2}\right)+ \frac{(t-y)\sqrt{3}}{2}i \right|^2 \\
&= (s-x)^2 - (s-x)(t-y) + \frac{(t-y)^2}{4} + \frac{3(t-y)^2}{4} \\
&= (s-x)^2 - (s-x)(t-y) + (t-y)^2 \le \frac{1}{4} + \frac{1}{4} + \frac{1}{4} = \frac{3}{4}
\end{aligned}
$$

따라서

$$
\begin{aligned}
  \left| r \right| &= \left| b - qa \right| = \left| \frac{b}{a} - q \right| \left| a \right| \\
  &\le \frac{3}{4} \left| a \right| < \left| a \right|
\end{aligned}
$$

즉, Division Algorithm이 성립하므로 $\mathbb{Z}[\zeta]$는 Euclidean Algorithm이다. $\blacksquare$

<br>
<hr>

앞선 문제도 $\mathbb{Z}[\sqrt{-2}]$가 Euclidean Domain임을 보이는 문제였는데, 이번에도 비슷한 문제였다.

문제를 풀고 나서 든 생각은 지금의 패턴을 일반화하면 $\mathbb{Z}[\omega]$ for any $\omega \in \mathbb{C}$를 Euclidean Domain으로 만들 수 있을지 생각해봤다.

그런데 Euclidean Domain에 대해 검색을 좀 해보니 아래와 같은 조건 아래에서 $\mathbb{Z}[\omega]$가 Euclidean Domain이 되는 것 같다.

<br>

<big>Case 1: $\mathbb{Z}[\sqrt{-n}]$</big>

If $n = 1$ or $n=2$, then $\mathbb{Z}[\sqrt{-n}]$ is an Euclidean Domain.

If $n \ge 3$, then $\mathbb{Z}[\sqrt{-n}]$ is not an Euclidean Domain.

일단 이건 $\dfrac{b}{a} - q$에 대한 Norm을 구해보면, $\dfrac{1 + n}{4}$가 나오는데, 이게 $\dfrac{1+n}{4} < 1$이 되야 해서 그런 걸로 알고 있다.

<br>

<big>Case 2: $\mathbb{Z}[\omega]$</big>

이 경우에는 $\omega$가 cube root of unity가 될때 Euclidean Domain이 된다.

$\omega$가 cube root of unity가 되는 경우는 $\omega = e^{2\pi i / 3}$과 $\omega = e^{4\pi i / 3}$이다.

- [link](https://en.wikipedia.org/wiki/Euclidean_domain#Examples)

<br>

그 외의 경우는 뭔가 직접 $\dfrac{b}{a} - q$를 구해봐야 할 것 같다. 아님 내가 아직 찾지 못한 $\omega \in \mathbb{C}$에 대한 조건이 있을 수도?
