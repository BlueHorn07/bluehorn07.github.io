---
title: "Factor Rings & Ideals"
layout: post
tags: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br>


<span class="statement-title">Definition.</span> Ring homomorphism<br>

<div class="statement" markdown="1">

Let $R$, $R'$ be rings

A map $\phi: R \longrightarrow R'$ is a **<u>ring homomorphism</u>**

- $\phi(a+b) = \phi(a) + \phi(b)$; group homomorphism
- $\phi(a \cdot b) = \phi(a) \cdot \phi(b)$; semi-group homomorphism

</div>

<br>

<span class="statement-title">Example.</span> Projection homomorphism<br>

Let $R_1$, $R_2$, ..., $R_n$ be rings.

The map $\pi_i: R_1 \times R_2 \times \cdots \times R_n \longrightarrow R_i$ defined by $\pi_i (r_1, r_2, \dots, r_n) = r_i$ is a homormophism.

"projection onto the $i$-th component"

<br>
<hr>

<span class="statement-title">Theorem.</span><br>

<div class="statement" markdown="1">

Let $\phi$ be a homo- of a ring $R$ onto a ring $R'$.

1\. $$\phi(0_{R}) = 0_{R'}$$

2\. $\phi(-a) = -\phi(a)$

3\. If $S \le R$, then $\phi(S) \le R'$

4\. If $S' \le R'$, then $\phi^{-1} (S') \le R$

5\. If $1 \in R$, then $\phi(1)$ is unity of $\phi(R)$

</div>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

3번 명제만 증명을 해보자.

<details markdown="1">
<summary>3번 명제에 대한 증명</summary>

(1) Closure

For $\phi(x), \phi(y) \in \phi(R)$, 

$\phi(x) + \phi(y) = \phi(x+y)$

$\phi(x)\cdot\phi(y) = \phi(x \cdot y)$

\* comment: 처음에 $x, y \in \phi(R)$로 시작해서 증명을 복잡하게 생각했다. $\phi$와 함께 바로 원소 $\phi(x), \phi(y)$를 잡으면 정말 쉽게 증명할 수 있는 명제다!

</details>


</div>

<br>
<hr>

<span class="statement-title">Definition.</span> kernel of ring homomorphism<br>

<div class="statement" markdown="1">

Let $\phi: R \longrightarrow R'$ be a ring homomorphism.

The sub-ring $\phi^{-1} (0') = \\{ r \in R \mid \phi(r) = 0' \\}$

is the **<u>kernel</u>** of $\phi$; $\ker \phi$.

</div>

<br>

<span class="statement-title">Example.</span><br>

$\mathbb{R} \not\cong \mathbb{C}$ as a ring isomorphism.

<br>
<hr>

### Ideal

<span class="statement-title">Theorem.</span><br>

<div class="statement" markdown="1">

Let $H$ be a sub-ring of ring $R$; $H \le R$.

Multiplication of additive cosets of $H$ is **<u>well-defined</u>**

$$
(a + H)(b + H) := ab + H
$$

$\iff$ $aH \subseteq H$, $Hb \subseteq H$ for all $a, b \in R$.

</div>

<span class="statement-title">proof.</span><br>

<details>
<summary>($\impliedby$)</summary>

<div class="math-statement" markdown="1">

($\impliedby$) Supp. $ah, hb \in H$ for all $a, b \in R$ and all $h \in H$.

Let $h_1, h_2 \in H$ so that $a + h_1$, $b + h_2$ are **representatives** of cost $a+H$, $b+H$ containing $a$ and $b$.

Then, 

$$
(a+h_1)(b+h_2) = ab + ah_2 + h_1 b + h_1 h_2
$$

처음 가정에 의해 $ah_2, h_1 b, h_1 h_2 \in H$이므로 $(a+h_1)(b+h_2) \in (ab + H)$이다. $\blacksquare$

</div>

</details>

<details>
<summary>($\implies$)</summary>

<div class="math-statement" markdown="1">

($\implies$) Supp. multiplication of cosets is well-defined.

Let $a \in R$, and consider coset product $(a+H)(0 + H)$.

Then,

$$
\begin{aligned}
    (a + H)(0 + H) &= a0 + aH + H0 + HH \\
                   &= 0 + aH + 0 + H \\
                   &= aH + H \\
                   &= a0 + H  = H \quad (\textrm{by definition of operation})
\end{aligned}
$$

위의 식에서 $aH$는 $H$의 원소가 되어야 한다.

따라서 $aH \subseteq H$가 된다!

마찬가지로 반대로 $H(b+H)$를 진행하면 $Hb \subseteq H$를 확인할 수 있다.

$\blacksquare$

</div>

</details>

<br>

Group Theory에선 **<u>Normal subgroup</u>**이 **<u>Factor group</u>**의 연산을 잘 정의하는 데에 중요한 조건이 되었다.

마찬가지로 Ring Theory에서도 좋은 **<u>Normal sub-ring</u>**을 골라 **<u>Factor Ring</u>**을 정의할 수 있다!!

바로 위의 정리는 Factor Ring 연산이 well-defined 되기 위해서

for $a, b \in R$

- $aH \subseteq H$
- $Hb \subseteq H$

를 만족해야 함을 진술한다.

<br>

<span class="statement-title">Definition.</span> Ideal<br>

<div class="statement" markdown="1">

A subgroup $N$ of a ring $R$ is an "ideal", when

$$
aN \subseteq N \quad \textrm{and} \quad Nb \subseteq N \quad \forall \; a, b \in R
$$

이 subgroup $N$을 ideal $I$로 표현하자.

</div>

<br>

<span class="statement-title">Definition.</span> Factor Ring<br>

<div class="statement" markdown="1">

Let $I$ be an ideal of a ring $R$.

Then $R/I$ is a ring.

※ Note: Ideal $I$의 정의상 $(I, +) \trianglelefteq (R, +)$이다.

</div>

<br>

※ **TODO**: Check $R/I$ is a ring

- the set of additive left coset is abelian?
- the set of additive left coset is semi-group?
- Associativity?
- Distributive Law?

<br>
<hr>

<span class="statement-title">Theorem.</span> canonical homomorphism on ring<br>

Let $I \trianglelefteq R$, then

$$
\begin{aligned}
    \phi: R &\longrightarrow R / I \\
            r & \longmapsto rI
\end{aligned}
$$

<br>

<span class="statement-title">Theorem.</span> FHT on ring<br>

$$
R / {\ker \phi} \cong \phi[R]
$$


