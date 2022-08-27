---
title: "Converse of Lagrange Thm"
layout: post
use_math: true
tags: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

## (Review) Lagrange Thm

<div class="statement" markdown="1">

For a group $G$, and subgroup $H \le G$, $\lvert H \rvert \mid \lvert G \rvert$

</div>

Lagrange 정리는 Group $G$와 subgroup $H$ 사이의 관계를 기술한 정리이다. 

하지만, 일반적으로 Lagrange 정리의 역은 성립하지 않는다. 이번 포스트에선 Lagrange 정리의 역에 대한 반례인 $A_4$에 대해 다룬다.

<hr>

## Converse of Lagrange Thm

Lagrange 정리의 역은 다음과 같다.

<div class="statement" markdown="1">

Group $G$에 대해, $\lvert G \rvert$의 약수를 order로 갖는 subgroup이 항상 존재한다.

</div>

그러나 Lagrange 정리의 역은 거짓이다! 그 반례를 살펴보자.

<hr>

#### $A_4$ has no subgroup of order 6

$A_4$의 order는 $\frac{24}{2}=12$이다. 하지만, $A_4$는 order 6인 subgroup을 가지지 않는다!

<br>

<div class="math-statement" markdown="1">

(pf) **proof by contradiction**

Supp. $A_4$ has a subgroup $H$ of order 6.

We will draw a contradiction.

subgroup $H$의 index를 살펴보자.

$$
\left[ A_4 : H \right]= \left\lvert \frac{A_4}{H} \right\rvert = \frac{12}{6} = 2
$$

즉, $H$의 index가 2이므로 $H$는 $A_4$의 **Normal subgroup**이다[^1]; $H \triangleleft A_4$

<br>

THEN, $A_4 = H {\cup\mkern-13mu\cdot\mkern5mu} \sigma H$이고, $\dfrac{A_4}{H} = \\{e, a \\} = \\{ H, \sigma H\\}$ for all $\sigma \ne H$

이때, factor group $\frac{A_4}{H}$의 order가 2이므로 $a^2=e$가 되어야 한다.

그러면, $(\sigma H)^2=\sigma^2 H = H$이므로 $\sigma^2 \in H$가 된다. (by 연산의 닫힘성)

즉, $\forall \sigma \notin H$, $\sigma^2 \in H$가 된다.

이때, $\forall \sigma \in H$에 대해서도 $\sigma^2 \in H$가 되므로, 종합하면 

$$
\forall \sigma \in A_4, \: \sigma^2 \in H
$$

<br>

$\sigma \in A_4$인 $\sigma$는 세 가지 형태를 가진다.

1. $\sigma=(1) \implies \sigma^2 = (1) \in H$
2. $\sigma = (i \; j)(x \; y) \implies \sigma^2 = (1) \in H$
3. $\sigma = (i \; j \; k) \implies \sigma^2 = (i \; k \; j) \in H$

[^2]

따라서 모든 3-cycle이 $H$에 속하게 된다. (+ identity인 $(1)$도 포함)

\# of 3-cycle = $\binom{4}{3} \times 2 = 8$[^3]

여기에 identity인 $(1)$까지 포함하면, $\lvert H \rvert = 8+1 = 9$

이것은 $\lvert H \rvert = 6$이라는 가정에 모순된다!

따라서 $A_4$에서 $\lvert H \rvert = 6$인 subgroup은 존재하지 않는다.

</div>

<hr>

[^1]: "subgroup의 index가 2이면, Normal subgroup이다."라는 정리를 활용한 부분이다.

[^2]: $A_4$에는 even permutation만 존재하기 때문에 odd permutation인 $(w \; x \; y \; z)$는 고려하지 않는다.

[^3]: 집합 $\\{i, j, k\\}$에서 $(i \; j \; k)$와 $(i \; k \; j)$가 가능하므로 $\binom{4}{3}$에서 $\times 2$를 해준다.