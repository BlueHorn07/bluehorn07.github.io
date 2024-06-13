---
title: "Unique Factorization Domain - 1"
toc: true
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

#### Associates / Associated Element

<span class="statement-title">Definition 1.</span> Associates<br>

<div class="notice" markdown="1">

For two elts $a$, $b$ in a (Ring + Unity),

$a$ and $b$ are **<u>associates</u>** when $a \mid b$ and $b \mid a$. (Divisible by each other)

(cf)

$a \mid b \implies b = ax$

$b \mid a \implies a = by$

$a = by = (ax)y = axy$

따라서 $xy = 1$

</div>

<span class="statement-title">Definition 2.</span> Associates<br>

<div class="notice" markdown="1">

For two elts $a$, $b$ in a (Ring + Unity),

$a$ and $b$ are associates each other,

if one can be obtained from the other by multiplying by some unit.

$\equiv$ $a = bu$ for some unit $u$.

</div>

- [planetmath.org](https://planetmath.org/associates)

<br>

<span class="statement-title">Example.</span> from [StackExchange](https://math.stackexchange.com/a/1312571) <br>

In the Ring of Integers $\mathbb{Z}$, the units are $1$ and $-1$.

If $a, b \in \mathbb{Z}$ are associates, then $a = ub$ for some unit $u$.

Therefore $a = 1 \cdot b = b$ or $a = (-1) \cdot b = -b$.

Two Integers are associates in $\mathbb{Z}$ $\iff$ they are the same up to sign.


<br>
<hr>

#### Unique Factorization Domain

<span class="statement-title">Definition 1.</span> Unique Factorization Domain<br>

<div class="notice" markdown="1">

An **Integral Domain** in which every non-zero elt has a **<u>unique factorization</u>**; <br>
i.e. unique decomposition as the product of prime elements (or irreducible elements).

※ Note: In UFD, every irreducible element is a prime element.

</div>

- [MathWorld/Unique Factorization Domain](https://mathworld.wolfram.com/UniqueFactorizationDomain)

<br>

<span class="statement-title">Definition 2.</span> Unique Factorization Domain<br>

<div class="notice" markdown="1">

- Every $a \in D$ ,which is a non-zero & non-unit, can be written as a product of irreducibles.
- This decomposition is **unique up to reordering**, and **up to associates**.

Assume that two decompositions $a = p_1 \cdots p_n = q_1 \cdots q_m$ (all $p_i$, $q_i$ are irreducibles)

Then, $n=m$, and there exist a permutation $\sigma \in S_n$ s.t. $p_i = q_{\sigma(i)}$ are **associates** for all $i=1, \dots, n$.

</div>

- [herzig's note](https://www.math.toronto.edu/~herzig/UFDs.pdf)

<br>
<hr>

<span class="statement-title">Theorem.</span> [^1] <br>
<div class="notice" markdown="1">

In UFD, Irreducible elt is also a Prime elt.

</div>

일반적으로 Prime element면 Irreducible element다. [link]({{"2020/12/27/Prime-Irreducible-Element#theorem-prime---irreducible" | relative_url}}) <br>
하지만, Irreduciblility가 Primality를 항상 보장하는 것은 아니다.

그런데 UFD에서는 특별히 Irreducibility가 Primality를 보장한다!!


<span class="statement-title">proof.</span><br>
<details>
<div class="math-statement" markdown="1">


Let $p \in D$ be a irreducible element.

Let $p \mid ab$ for some $a, b \in D$.

(Goal) show $p \mid a$ or $p \mid b$

$ab = p\cdot c$ for some $c \in D$.

then, since $a$, $b$, $c$ are in UFD, they can be written as below

$$
ab = (a_1 ... a_n)(b_1 ... b_m) = p \cdot (c_1 ... c_k)
$$

이때 $a$, $b$, $c$의 factorization이 unique 하므로 $p$는 $ab$의 irreducible 중 하나와 associate 해야 한다.

즉, $p \sim a_i$ or $p \sim b_i$.

$\implies$ $p = a_i u$ or $p = b_i u$ for some unit $u$.

Let assume $p \sim a_i \equiv p = a_i u$.

$$
a = a_1 \cdots a_n = a_1 \cdots (pu^{-1}) \cdots a_n
$$

이것은 $p \mid a$를 의미한다.

마찬가지로 $p \sim b_i$를 가정하면, $p \mid b$를 얻는다.

따라서 $p \mid ab$에 대해 $p \mid a$ or $p \mid b$이므로

Irreducible element인 $p$는 Prime element이기도 하다. $\blacksquare$

</div>
</details>

<br>

<span class="statement-title">Corollary.</span><br>
<div class="notice" markdown="1">

$D$: Integral Domain

$D$ is an UFD

$\iff$ Every non-zero elt is a product of finitely many prime elt.

</div>

<br>
<hr>

PID와 UFD 사이의 관계에 대해선 [UFD - 2]({{"2020/12/27/Unique-Factorization-Domain-2" | relative_url}})에서 이어집니다.

<br>
<hr>

[^1]: Dummit & Foote, "Abstract Algebra", 286p