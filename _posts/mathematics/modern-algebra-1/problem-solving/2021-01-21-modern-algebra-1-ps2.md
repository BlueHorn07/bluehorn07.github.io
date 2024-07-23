---
title: "Modern Algebra I - PS2"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Modern Algebra1", "Problem Solving"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

<div class="notice" markdown="1">

Consider $\mathbb{Z}[\sqrt{-2}]$ as a sub-ring of $\mathbb{C}$ and with the norm $N(a+b\sqrt{-2}) = a^2 + 2b^2$.

(a) The ring $\mathbb{Z}[\sqrt{-2}]$ is a Euclidean domain.

(b) Find a generator for the ideal $\left< 85, -11+4\sqrt{-2} \right>$.

</div>

<br>

<big>(a) The ring $\mathbb{Z}[\sqrt{-2}]$ is a Euclidean domain.</big>

<div class="math-statement" markdown="1">

$\mathbb{Z}[\sqrt{-2}]$가 Euclidean Algorithm을 만족하는지 확인해야 한다.

For $a, b \in \mathbb{Z}[\sqrt{-2}]$ with $a \ne 0$.

Check the existence of $q, r \in \mathbb{Z}[\sqrt{-2}]$ with $N(r) < N(a)$ s.t.

$$
b = qa + r
$$

Let $q$ be the point in $\mathbb{Z}[\sqrt{-2}]$ closest to $b/a$.<br>
<small>(이때 $b/a$는 mother-ring인 $\mathbb{C}$에서 정의되는 녀석이다.)</small>

Let $r$ be $r = b - qa$. Then it is immediate that $b = qa + r$.

Now we must show that this $r$ satisfies $N(r) < N(a)$.

$$
\left| r \right| = \left| b - qa \right| = \left| \frac{b}{a} - q \right| \left| a \right|
$$

이때 $\left\| \frac{b}{a} - q \right\|$는, 우리가 앞에서 $q$를 $b/a$에 대한 closest point on $\mathbb{Z}[\sqrt{-2}]$로 잡았기 때문에 아래의 부등식이 성립한다.

$$
\left| \frac{b}{a} - q \right| \le \left| (1 + \sqrt{-2})/2 \right| = \frac{\sqrt{3}}{2}
$$

따라서

$$
\left| r \right| = \left| \frac{b}{a} - q \right| \left| a \right| \le \frac{\sqrt{3}}{2} \left| a \right| < \left| a \right|
$$

따라서 $N(r) < N(a)$이 성립하므로,

$\mathbb{Z}[\sqrt{-2}]$는 Euclidean Domain이다. $\blacksquare$

</div>

<big>(b) Find a generator for the ideal $\left< 85, -11+4\sqrt{-2} \right>$.</big>

<div class="math-statement" markdown="1">

(a)에서 $\mathbb{Z}[\sqrt{-2}]$가 Euclidean Domain임을 보였으므로 Euclidean Algorithm을 활용해 GCD를 구할 수 있다!

$85$가 $-11+4\sqrt{-2}$보다 더 크므로 $85$를 $-11+4\sqrt{-2}$로 나눠준다.

$$
85 = q (-11 + 4 \sqrt{-2}) + r
$$

여기에서 어떻게 $q$를 구할지 고민을 많이 했는데, 결국은 (a)에서 정의한 대로 $q$를 the closest point로 잡으면 되는 거였다.

그래서 $\frac{85}{-11+\sqrt{-2}}$를 구해야 하는데, 이건 일반적인 Complex Division을 하면 된다.

$$
\frac{85}{-11+4\sqrt{-2}} = \frac{85}{-11+4\sqrt{-2}} \frac{-11-4\sqrt{-2}}{-11-4\sqrt{-2}} = \frac{-11 \cdot 85}{153} + \frac{-4 \cdot 85}{153} \sqrt{-2}
$$

그 다음은 초급 연산이라 몫을 구해서 the closest point on $\mathbb{Z}[\sqrt{-2}]$를 찾으면 $-6 -2 \sqrt{-2}$가 된다!

따라서

$$
\begin{aligned}
85 &= (-6-2\sqrt{-2}) (-11 + 4 \sqrt{-2}) + r \\
    &= (-6-2\sqrt{-2}) (-11 + 4 \sqrt{-2}) + (3 + 2\sqrt{-2})
\end{aligned}
$$

이제 나머지과 Divisor에 대해 다시 Euclidean Algorithm을 적용하면

$$
-11 + 4 \sqrt{-2} = (-1 + 2\sqrt{-2})(3 + 2\sqrt{-2})
$$

따라서 $\gcd (85, -11+4\sqrt{-2}) = (3 + 2\sqrt{-2})$가 된다!

$\therefore \left< 85, -11+4\sqrt{-2} \right> = \left< 3 + 2\sqrt{-2} \right>$. $\blacksquare$

</div>
