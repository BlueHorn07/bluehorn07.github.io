---
title: "Maximal Ideal & Prime Ideal"
layout: post
use_math: true
tags: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

<span class="statement-title">Theorem.</span> Ideal + unity = Ring<br>

<div class="statement" markdown="1">

Let $R$ be a ring with unity.

If 
- $I \trianglelefteq R$ 
- $1 \in I$

then $I = R$.

</div>

<span class="statement-title">proof.</span> <br>

<details>
<div class="math-statement" markdown="1">

Let $r \in R$, and $1 \in I$

by definition of Ideal $I$, $rI \subseteq I$

$r \cdot 1 \in I \implies r \in I \implies R \subseteq I \implies R = I$

$\blacksquare$

</div>
</details>

<br>

<span class="statement-title">Corollary.</span> <br>

<div class="statement" markdown="1">

Field $F$ contains no proper non-trivial ideals.

즉, Field가 가지는 Ideal은 $\\{ 0 \\}$, $F$ 둘 뿐이다라는 말이다!

</div>

<span class="statement-title">proof.</span> <br>

<details>
<div class="math-statement" markdown="1">

Let Ideal $I \triangleleft F$ be a proper ideal.

Supp. $I \ne \\{ 0 \\}$ to be non-trivial ideal.

For $i \in I$, there exist it inverse $i^{-1}$ in $F$. <small>(Ring과 달리 inverse element가 존재한다.)</small>

since $I$ is ideal, $i^{-1}I \subseteq I$.

따라서 $i^{-1} i = 1 \in I$

Ideal $I$에 대해 $1 \in I$라면, 위에서 증명한 정리에 의해 $I = F$가 된다.

이것은 $I$가 proper ideal이라는 처음 가정에 모순이다!

따라서 $F$에는 proper ideal이 존재하지 않는다. $\blacksquare$

</div>
</details>

<br>
<hr>

### Maximal Ideal

<span class="statement-title">Definition.</span> <br>

<div class="statement" markdown="1">

$M$: maximal ideal of ring $R$

if

- $M \ne R$
- $M < N \triangleleft R$ implies $N = R$ <small>($M$보다 큰 ideal은 unit ideal인 $R$ 뿐이다.)</small>

</div>

<br>

<span class="statement-title">Example.</span> <br>

$p$: prime

Show $p\mathbb{Z} \triangleleft \mathbb{Z}$

즉, $p\mathbb{Z}$는 Maximal Ideal이다.

<br>

<span class="statement-title">proof.</span> <br>

<details>
<div class="math-statement" markdown="1">

$\mathbb{Z} / p\mathbb{Z} \cong \mathbb{Z}_p$

이때, $p\mathbb{Z}$는 simple group이다.

아래와 같은 정리에 따르면 $p\mathbb{Z}$는 maximal normal subgroup이 된다.

<div class="statement" markdown="1">

$M$ is a **<u>maximal normal subgroup</u>** of $G$ $\iff$ $G/M$ is simple.

</div>

$\mathbb{Z}$가 abelian이므로 모든 subgroup은 normal subgroup이다.

앞의 논의에서 $p\mathbb{Z}$가 maximal normal subgroup임을 확인했다.

이때, $p\mathbb{Z}$는 $Z$의 ideal이기도 하기 때문에, $p\mathbb{Z}$는 maximal ideal이다. $\blacksquare$

</div>
</details>

<br>

#### Maximal Ideal generates Field

<span class="statement-title">Theorem.</span> <br>

<div class="statement" markdown="1">

$R$ : commutative ring + unity

$M \triangleleft R$ : maximal ideal

$\iff$ $R / M$ is a Field.

</div>

<span class="statement-title">proof.</span><br>

<details>
<summary>($\implies$)</summary>
<div class="math-statement" markdown="1">

($\implies$) Supp. $M$ is a Maximal Ideal.

(Goal) $R/M$ is a Field.

Since $M$ is an Ideal, $R/M$ is a Ring.

Also, $R$ is commutative, $R/M$ is a Commutative Ring.

(Check) inverse exist?

For $r \notin M$, $\overline{r} \ne \overline{0}$, and $\overline{r} \in R/M$.

Let $\overline{r} \cdot \overline{s} = \overline{1}$

$$
\begin{aligned}
    \overline{r} \overline{s} &= \overline{1} \\
    \overline{rs} &= \overline{1} \\
    \overline{rs} - \overline{1} &= \overline{0} \\
    \overline{rs - 1} &= \overline{0}
\end{aligned}
$$

$$
\begin{aligned}
    rs - 1 &\in M \\
    -1 &\in M - rs \\
    1 &\in (-M) + rs \\
    1 &\in M + rs \\
    1 &\in M + (r)
\end{aligned}
$$

$rs$를 $(r)$로 바꾸었다. $(r)=rR$로 $r$로 생성된 [Principal Ideal]({{"2020/12/27/principal-ideal.html" | relative_url}})이다.

Claim. $M + (r)$은 Ideal이다.

<div class="statement" markdown="1">

$r(M + (r)) = rM + r(r) = M + (r)$

$(M+(r))r = Mr + (r)r = M + (r)$

</div>

$M$과 새롭게 정의한 $M + (r)$을 비교해보자.

$M + (r)$은 $M$을 완전히 포함하는 ideal이고, $r \notin M$이므로 아래의 식이 성립한다.

$$
M < M + (r) \trianglelefteq R
$$

이때 $M + (r)$이 ideal이면서 $1$를 포함하므로 $M + (r) = R$이다.

즉, $\overline{r}$의 inverse인 $\overline{s}$를 가정하고 유도한 결과가 maximal ideal $M$의 정의에 부합한다.

따라서 $(\overline{r})^{-1} = \overline{s} \in R / M$이므로

$R / M$은 Field이다. $\blacksquare$

p.s. 교수님이 수업 때 하신 증명인데 뭔가 이상하게 마음에 안 든다 ;;

</div>
</details>

<details>
<summary>($\impliedby$)</summary>
<div class="math-statement" markdown="1">

Supp. $R/M$ : Field

Let $M < N \trianglelefteq R$.

Then, For $r \in N \setminus M$, $\overline{r} \ne M$ and $\overline{r} \in R/M$.

이때, $R/M$이 Field이므로, $\overline{r} \cdot \overline{s} = \overline{1}$인 $\overline{s} \in R/M$가 존재한다. ($s \in R$)

Claim. coset $M + (r) = M + rR$ is an Ideal.

<div class="statement" markdown="1">

(앞에서 확인했던 방식대로 Ideal임을 확인하면 된다.)

</div>

따라서 $M + (r)$은 Ideal이다.

<br>

$s \in R$이므로 $1 \in M + (r)$이 된다.

<div class="statement" markdown="1">

$M$ is a Maximal Ideal $\implies$ $0 \in M$.

$0 + r \cdot s = 1$ for some $s \in R$. 

</div>

Ideal이 $1$을 포함하고 있으므로 $M + (r) = R$이 된다.

이때, $M < N$이고, $r \in N \setminus M$이므로

$M + (r) \subseteq N$이다.

그런데, $M + (r) = R$이므로 $R \subseteq N$이다.

따라서 $N = R$이다.

<br>

즉, $M < N \trianglelefteq R$에 대해 $N = R$이 되므로

$M$ is a Maximal Ideal. $\blacksquare$


</div>
</details>

<br>

<span class="statement-title">Corollary.</span><br>

<div class="statement" markdown="1">

$R$: commutative ring + unity

$R$ is a Field

$\iff$ $R$ has no proper non-trivial ideal.

</div>

앞에서 살펴봤던 Corollary에서 왼쪽 방향에 대한 명제가 추가된 따름 정리다!!

<details>
<summary>($\impliedby$)</summary>
<div class="math-statement" markdown="1">

Supp. the only ideals in $R$ is $\\{ 0 \\}$ and $R$.

(Goal) $R$ is a Field $\equiv$ inverse 有

Consider an ideal $rR$

then $\\{ 0 \\} < rR \trianglelefteq R$

$R$에는 ideal이 $R$ 하나 뿐이라고 가정했으므로 $rR = R$.

이때, $1 \in R$이므로 $1 \in rR$.

이것은 $1 = r \cdot s$ for some $s \in R$임을 말한다.

따라서 $r \in R$에 대한 inverse가 존재하므로 $R$은 Field이다. $\blacksquare$

</div>
</details>


<br>
<hr>

### Prime Ideal

<span class="statement-title">Definition.</span> Prime Ideal<br>

<div class="statement" markdown="1">

Let $R$ be a commutative ring, and $N \trianglelefteq R$.

When $N$ is a "**<u>prime ideal</u>**", then

for $a, b \in R$, $ab \in N$ implies $a \in N$ or $b \in N$.

</div>

(확실하진 않음.)

$N \trianglelefteq R$이 prime ideal이라면, $a \in N$은 prime elt over $R$이다?

<br>
<hr>

#### Prime Ideal generates Integral Domain

<span class="statement-title">Theorem.</span><br>

<div class="statement" markdown="1">

$R$ : commutative ring + unity.

$N \triangleleft R$ : Prime Ideal

$\iff$ $R/N$ is an integral domain.

</div>

<span class="statement-title">proof.</span><br>

<details>
<summary>($\implies$)</summary>
<div class="math-statement" markdown="1">

Supp. $N$ is a Prime Ideal, and $\overline{a} \cdot \overline{b} = \overline{0}$ for some $a, b \in R$.

(Goal) $\overline{a} = \overline{0}$ or $\overline{b} = \overline{0}$ in $R/N$.

$$
\begin{aligned}
    &\overline{a} \overline{b} = \overline{ab} = \overline{0} = N \\
    &\implies ab \in N \\
    &\implies a \in N \quad \textrm{or} \quad b \in N \qquad (N \; \textrm{is a Prime Ideal})
\end{aligned}
$$

만약 $a \in N$라면, $\overline{a} = \overline{0}$이 된다.

이것이 곧 $R/N$이 Integral Domain임을 의미한다. $\blacksquare$

</div>
</details>

<details>
<summary>($\impliedby$)</summary>
<div class="math-statement" markdown="1">

Supp. $R/N$ is an Integral Domain.

(Goal) $N$ : Prime Ideal

Let $a, b \in R$ s.t. $ab \in N$.

(Goal) show $a \in N$ or $b \in N$

Since $ab \in N$, $\overline{ab} = \overline{0}$ in $R/N$.

Since $R/N$ is an integral domain, $\overline{a} = 0$ or $\overline{b} = 0$.

따라서 $a \in N$ or $b \in N$.

이것은 $N$이 Prime Ideal임을 의미한다. $\blacksquare$

</div>
</details>

<br>
<hr>

#### Maximal Ideal implies Prime Ideal

<span class="statement-title">Theorem.</span><br>

<div class="statement" markdown="1">

Any Maximal Ideal of commutative ring is also a Prime Ideal.

</div>

<span class="statement-title">proof.</span><br>

<details>
<div class="math-statement" markdown="1">

Any Field is an Integral Domain.

($N$ : Maximal Ideal) $\iff$ ($R/N$ : Field) 

$\implies$ ($R/N$ : Integral Domain) $\iff$ ($N$ : Prime Ideal)

따라서 Maximal Ideal은 Prime Ideal이다. $\blacksquare$

</div>
</details>


