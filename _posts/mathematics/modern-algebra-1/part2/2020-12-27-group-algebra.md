---
title: "Group Rings & Group Algebras"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

### Group Rings & Group Algebras

<span class="statement-title">Definition.</span> Group Ring<br>

<div class="notice" markdown="1">

Let $G = \\{ g_i \mid i \in I \\}$ be a **<u>group</u>** under multiplicity, and

let $R$ be a **<u>commutative ring</u>** with non-zero unity.

Let $R(G)$ be the set of all formal sums

$$
\sum_{i \in I} {a_i g_i}
$$

where finite number of $a_i$ are non-zero.

</div>

<span class="statement-title">Properties.</span><br>

- $(R(G), +)$ is abelian group.

$$
\left( \sum_{i \in I} {a_i g_i} \right) + \left( \sum_{i \in I} {b_i g_i} \right) = \sum_{i \in I} {(a_i + b_i) g_i}
$$

- multiplicity is closed.

$$
\left( \sum_{i \in I} {a_i g_i} \right) \cdot \left( \sum_{i \in I} {b_i g_i} \right) = \sum_{i \in I} \left({ \sum_{g_j g_k = g_i} } {a_j b_k}\right) g_i
$$

- Associativity

<div style="text-align: center;">
    생-략
</div>

<br>

Group Ring과 Polynomial의 차이는 power에 있다.

- Polynomial은 power를 $\mathbb{N}$으로 표현하는 반면
- Group Ring은 power를 $g_i \in G$로 표현한다.

<br>

<span class="statement-title">Theorem.</span><br>

<div class="notice" markdown="1">

If $G$ is any group written multiplicatively, and $R$ is a commutative ring with non-zero unity,

then $(R(G), +, \cdot\;)$ is a ring.

</div>

위에서 진행했던 과정들이 이 정리의 증명이 된다.

<br>

<span class="statement-title">Definition.</span> Group Ring & Group Algebra<br>

<div class="notice" markdown="1">

위와 같은 Ring $R(G)$를 "**<u>Group Ring</u>**"라고 한다.

만약 $F$가 Field라면, $F(G)$는 "**<u>Group Algebra</u>**"라고 한다.

</div>

<br>
<hr>

### The Quaternions

**<big>The Quternions $\mathbb{H}$</big>**
- non-commutative division ring
- skew field
- $\mathbb{H} \cong \mathbb{R} \times \mathbb{R} \times \mathbb{R} \times \mathbb{R}$

<span class="statement-title">Properties.</span><br>

- Quaternion addition
- Quaternion multiplication
- Quaternion conjugate; $\bar{q}$
- Quaternion inverse; $(q)^{-1} = \dfrac{\bar{q}}{ {\lvert q \rvert}^2 }$

<br>

<span class="statement-title">Theorem.</span><br>

<div class="notice" markdown="1">

The Quaternions forms a **<u>skew field</u>** under $+$ and $\cdot\;$.

</div>

<br>

<span class="statement-title">Theorem.</span> Wedderburn's Theorem<br>

<div class="notice" markdown="1">

Every finite division ring is a field.

</div>

<details markdown="1">
<summary>Comment</summary>

아무리 생각해봐도 Quaternions로 이루어진 finite division ring을 구상할 수가 없었다 ㅠㅠ ($\mathbb{R} \le \mathbb{H}$ 제외)

추측하건데, Quaternion $H$로는 finite sub-ring을 만들 수 없는게 아닌가 생각하고 있다 ㅠㅠ

(잘 생각해보면, $\mathbb{Z}$나 $\mathbb{Q}$에서도 둘로부터 finite sub-ring을 만드는 건 불가능 하긴 했다 ㅋㅋㅋ)

</details>