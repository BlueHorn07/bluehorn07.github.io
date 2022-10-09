---
title: "Modern Algebra 1"
layout: post
tags: ["Modern Algebra1"]
hidden: true
---

<div class="img-wrapper">
  <img src="{{ "/images/modern-algebra-1/group_meme.jpg" | relative_url }}">
  <br/>
</div>

<div class="math-statement" markdown="1">

[목차]

- [Group](#group)
  - [Fundamental Theorem of Finitely Generated Abelian Group](#fundamental-theorem-of-finitely-generated-abelian-group)
- [Factor Group & Homomorphism](#factor-group--homomorphism)
- [Advanced Group Theory](#advanced-group-theory)
- [Ring & Field](#ring--field)
- [Factor Ring & Ideal](#factor-ring--ideal)
- [Advanced Ring & Field Theory](#advanced-ring--field-theory)
- [Galois Theory](#galois-theory)
- [Appendix](#appendix)

</div>

<hr>

# Group

<div class="statement" markdown="1">

<span class="statement-title">Lagrange Theorem</span><br>

If $H$ is a subgrop of a group $G$, then $\lvert H \rvert \mid \lvert G \rvert$, in other words, $\lvert G \rvert = [G:H] \lvert H \rvert$. 

</div>

- [Cyclic Group]({{"2020/10/18/cyclic-group-and-subgroup.html" | relative_url}})
- Symmetric Group
- Coset
- [Lagrange Theorem]({{"2020/10/14/Lagrange-thm.html" | relative_url}}) 🔥🔥
- Permutation Group
- orbit; cycle; transposition
- Alternating Group

## Fundamental Theorem of Finitely Generated Abelian Group

<div class="statement" markdown="1">

<span class="statement-title">F.T of f.g. Abelian Group</span><br>

Every f.g. abelian group $G$ is isomorphic to direct product of cyclic groups.

$$
G \cong \mathbb{Z}_{(p_1)^{r_1}} \times \mathbb{Z}_{(p_2)^{r_2}} \times \cdots \times \mathbb{Z}_{(p_n)^{r_n}} \times \mathbb{Z} \times \mathbb{Z} \times \cdots \times \mathbb{Z}
$$

Where $p_i$ are primes, not necessarily distinct, and $r_i$ are positive integers.

</div>

- Decomposable & Indecomposable group
- $p$-group [^1]

<hr>

## Factor Group & Homomorphism

- [Homomorphism + 문풀]({{"2020/12/24/Group-Homo-Iso.html" | relative_url}})
- [Cayley's Theorem]({{"2020/12/24/Cayley-Theorem.html" | relative_url}})

<hr>

<div class="statement" markdown="1">

condition for operation well-definedness on Factor group

$$
H \trianglelefteq G \iff gHg^{-1} \subseteq H \quad (\forall g \in G)
$$

</div>

- [Factor Group]({{"2020/12/24/Factor-Group.html" | relative_url}})
  - from normal subgroups
  - from homomorphism
  - Auto-morphism
    - inner automorphism $\sigma_g$

<hr>

<div class="statement" markdown="1">

<span class="statement-title">Fundamental Homomorphism Theorem</span><br>

Let $\phi: G \longrightarrow G'$ be a group homomorphism, THEN

1. $\phi[G]$ is a group.
2. ${G}/{\ker \phi} \cong \phi[G]$

</div>

- [Fundamental Homomorphism Theorem(FHT)]({{"2020/12/25/Fundamental-Homo-theorem.html" | relative_url}}); 1st Isomorphism Thm 🔥🔥
  - [Canonical homomorphism]({{"2020/12/25/Fundamental-Homo-theorem.html#canonical-homomorphism" | relative_url}}) $\gamma: G \longrightarrow G/H \quad (H \trianglelefteq G)$
- [index-2 group is normal]({{"2020/12/25/index-2-Group.html" | relative_url}})
- [Factor Group - Appliaction]({{"2020/12/25/Factor-Group-Application.html" | relative_url}}')
  - [Converse of Lagrange Thm]({{"2020/11/28/Converse-of-Lagrange-Thm.html" | relative_url}})

## Advanced Group Theory

- [Three Isomorphism Theorems]({{"2020/12/25/Isomorphism-Thm.html" | relative_url}}) 🔥
- [Three Sylow Theorems]({{"2020/12/26/Sylow-thm.html" | relative_url}}) 🔥
  - [$p$-group]({{"2020/12/26/Sylow-thm.html#p-group" | relative_url}})
  - [normalizer]({{"2020/12/26/Sylow-thm.html#normalizer" | relative_url}}) of $H$ in $G$; $N_G(H)$
  - [Sylow $p$-group]({{"2020/12/26/Sylow-thm.html#sylow-p-group" | relative_url}})
  - [Application 1]({{"2020/12/26/Sylow-thm-Application-1.html" | relative_url}}) 🔥
  - [Application 2]({{"2020/12/26/Sylow-thm-Application-2.html" | relative_url}}) 🔥
  - [Examples]({{"2020/12/26/Sylow-thm-Application-2.html#sylow-theorem---examples" | relative_url}}) 🔥
    - [Type-1]({{"2020/12/26/Sylow-thm-Application-2.html#type-1" | relative_url}})
    - [Type-2]({{"2020/12/26/Sylow-thm-Application-2.html#type-2" | relative_url}})

# Ring & Field
- Ring [1]({{"2020/12/05/Ring-1.html" | relative_url}}), [2]({{"2020/12/05/Ring-2.html" | relative_url}})
  - commutative ring
  - [Ring homormophism & isomorphism]({{"2020/12/05/Ring-1.html#ring-homomorphism" | relative_url}})
  - Unity; multiplicative identity
  - [division ring]({{"2020/12/05/Ring-2.html#division-ring" | relative_url}})
  - [Field & Skew Field]({{"2020/12/05/Ring-2.html#field--skew-field" | relative_url}})
  - [Quaternion]({{"2020/12/05/Ring-2.html#quternion" | relative_url}})
  - [zero-divisor]({{"2020/12/05/Ring-2.html#zero-divisor" | relative_url}})
  - Bezout's Identity
  - Charasteric of Ring; $\textrm{Char}(R)$
- [Integral Domain]({{"2020/12/06/Field.html" | relative_url}})
  - [Field $\implies$ Integral Domain]({{"2020/12/06/Field.html#field-implies-integral-domain" | relative_url}})
  - [Finite Integral Domain $\implies$ Field]({{"2020/12/06/Field.html#finite-integral-domain-implies-field" | relative_url}})

<hr>

<div class="statement" markdown="1">

<span class="statement-title">Fermat's Little Theorem</span><br>

Let $p$ be a prime, IF $a \in \mathbb{Z}$, and $p \nmid a$, THEN

$$
a^{p-1} \equiv 1 \quad (\textrm{mod} \; p)
$$

</div>

- [Fermat's Little Theorem]({{"2020/12/26/Fermat-little-theorem.html" | relative_url}}) 🔥
  - [Examples]({{"2020/12/26/Fermat-little-theorem.html#examples" | relative_url}})

<div class="statement" markdown="1">

<span class="statement-title">Euler's Theorem</span><br>

If $a \in \mathbb{Z}$, and $(a, n) = 1$, THEN

$$
a^{\varphi(n)} \equiv 1 \quad (\textrm{mod} \; p)
$$

**<u>NOTE</u>**: if $n = p$, then $\varphi(p) = p-1$

</div>

- [Euler's Theorem]({{"2020/12/26/Euler-Thm.html" | relative_url}}) 🔥
  - [Euler phi function]({{"2020/12/26/Euler-Thm.html#euler-phi-function" | relative_url}})
  - [Application: solve modulo equation]({{"2020/12/26/Euler-Thm.html#application-solve-modulo-equation" | relative_url}}) $ax \equiv b$ (mod $n$)
<hr>

- [Quotient Field]({{"2020/12/19/Qutient-Field.html" | relative_url}}) (= Field of Qutients; 분수체)
  - Extend integral domain $D$ into field $F$.
    - Extend $\mathbb{Z}$ into $\mathbb{Q}$

<br>

- [Ring of Polynomials]({{"2020/12/27/Polynomial-Ring.html" | relative_url}})
  - [Evaluation Homomorphism]({{"2020/12/27/Polynomial-Ring.html#evaluation-homomorphism" | relative_url}}); $\phi_\alpha$
  - [Division Algorithm for Polynomial Ring]({{"2020/12/27/Polynomial-Ring.html#division-algorithm-for-polynomial-ring" | relative_url}})
  - [zero of polynomial]({{"2020/12/27/Polynomial-Ring.html#zero-of-polynomial" | relative_url}})
  - [Factor Theorem]({{"2020/12/27/Polynomial-Ring.html#factor-theorem" | relative_url}}); 인수 정리
  - cyclic group embedding

<div class="statement" markdown="1">

<span class="statement-title">Eisenstein Criteria</span><br>

Let $p \in \mathbb{Z}$ be a prime, $f(x) = a_n x^n + \cdots a_0 \in \mathbb{Z}[x]$

IF 

$$
\begin{aligned}
  a_n &\not\equiv 0 \quad (\textrm{mod} \; p) \\
  a_i &\equiv 0 \quad (\textrm{mod} \; p) \quad 0 \le i < n\\
  a_0 &\not\equiv 0 \quad (\textrm{mod} \; p^2) \\
\end{aligned}
$$

THEN, $f(x)$ is irreducible over $\mathbb{Q}$.

</div>

- [Irreducible Polynomials]({{"2020/12/27/Irreducible-poly.html" | relative_url}})
  - Eisenstein Criteria 🔥
- [Non-commutative Examples]({{"2020/12/27/non-commutative-example.html" | relative_url}})
- [Group Rings & Group Algebras]({{"2020/12/27/group-algebra.html" | relative_url}})
  - Group Ring; $R(G)$
  - Group Algebra; $F(G)$
  - The Quaternions; $\mathbb{H}$
  - Wedderburn's Theorem
    - "Every finite division ring is a field."

<hr>

## Factor Ring & Ideal

- [Ring Homomorphism & Factor Ring]({{"2020/12/27/Factor-rings-Ideal.html" | relative_url}})
  - Factor Ring well-definedness
  - [Ideal]({{"2020/12/27/Factor-rings-Ideal.html#ideal" | relative_url}})
- [Maximal & Prime Ideals]({{"2020/12/27/maximal-and-prime-ideal.html" | relative_url}})
  - Ideal + unity = Ring 🔥
  - Maximal Ideal
    - Maximal Ideal makes factor group as field.
  - Prime Ideal
    - Prime Ideal makes factor group as integral domain.
  - Maximal Ideal implies Prime Ideal
- [Prime Field]({{"2020/12/28/Prime-Field.html" | relative_url}})
  - $\textrm{Char}$와 sub-ring / sub-field 사이의 관계
  - $\mathbb{Z}_p$, $\mathbb{Q}$ are Prime Field

<div class="statement" markdown="1">
Prime ideals generalize the concept of primality to more general commutative rings.
</div>

- [Prime & Irreducible element]({{"2020/12/27/Prime-Irreducible-Element.html" | relative_url}})
  - Prime element
  - Irreducible element
- [Principal Ideal]({{"2020/12/27/principal-ideal.html" | relative_url}})
  - Principal Integral Domain; PID


<hr>

## Advanced Ring & Field Theory

- [Unique Factorization Domain]({{"2020/12/27/Unique-Factorization-Domain-1.html#unique-factorization-domain" | relative_url}}); UFD [1]({{"2020/12/27/Unique-Factorization-Domain-1.html" | relative_url}}), [2]({{"2020/12/27/Unique-Factorization-Domain-2.html" | relative_url}})
  - [Associate / Associated element]({{"2020/12/27/Unique-Factorization-Domain-1.html#associates--associated-element" | relative_url}}) 🔥
  - In UFD, Irreducible elt is also a Prime elt.
  - [Every PID is UFD]({{"2020/12/27/Unique-Factorization-Domain-2.html" | relative_url}}) 🔥
- [Fundamental Theorem of Arithmetic]({{"2021/01/17/fundamental-thm-of-arithmetic.html" | relative_url}})
- [Guass's Lemma]({{"2020/12/30/Guass-Lemma.html" | relative_url}})
  - (primitive) $\times$ (primitive) = (primitive)
- [Polynomial over UFD]({{"2021/01/18/polynomial-over-UFD.html" | relative_url}})
- [Euclidean Domain]({{"2021/01/18/Euclidean-domains.html" | relative_url}})
  - Euclidean norm
  - Euclidean Algorithm
- [Gaussian Integers]({{"2021/01/18/Gaussian-Integer.html" | relative_url}}) 🔥
  - Multiplicative norm
- [Fermat's Theorem on Sums of Two Squares]({{"2021/01/18/Fermat-thm-on-sums-of-two-squares.html" | relative_url}}); 페르마의 두 제곱수 정리

<hr>

# Galois Theory

🔥 Continued on *Morden Algebra II* ... 🔥

<br>
<hr>

# Problem Solving
- [PS1]({{"2021/01/18/modern-algebra-1-ps1.html" | relative_url}})

<br>
<hr>

# Appendix

<div class="statement" markdown="1">

For a homormophism $\phi$,

if $\ker \phi = \\{ e \\}$, then $\phi$ is 1-1.

</div>

<div class="statement" markdown="1">

<span class="statement-title">Ring-Domain-Field</span><br>

- Field $\implies$ Integral Domain
- Finite Integral Domain $\implies$ Field
- Finite Division Ring $\implies$ Field <small>(Wedderburn’s Theorem)</small>

</div>

<div class="statement" markdown="1">

<span class="statement-title">헷갈리는 조합 1</span><br>

- Quotient Field
- Factor Ring

</div>

<div class="statement" markdown="1">

<span class="statement-title">헷갈리는 조합 2</span><br>

- **<u>Factor</u>** Ring <small>Ring / Ideal</small>
- **<u>Factor</u>** Theorem
- Unique **<u>Factorization</u>** Domain(UFD)

</div>

<div class="statement" markdown="1">

<span class="statement-title">Homomorphism 모음</span><br>

- canonical homoomprhism <small>(= natural homomorphism)</small>
  - (Group) $\phi: G \longrightarrow G / N$
  - (Ring) $\phi: R \longrightarrow R / I$
- evaluation homomorphism

</div>

<div class="statement" markdown="1">

<span class="statement-title">Maximal Ideal 모음</span><br>

- $p\mathbb{Z}$ is Maximal Ideal.
- Factor Ring from Maximal Ideal = Field
- $F[x]$ is a Field and $p(x) \in F[x]$
  - $\left< p(x) \right>$ is a Maximal Ideal $iff$ $p(x)$ is irreducible in $F[x]$.
</div>

<hr/>

# Study Materials
- 『A First Course in Abstract Algebra』 Fraleigh, 7th ed.
- 『Abstract Algebra』 Dummit & Foote, 3rd ed.

<hr>

[^1]: Sylow Theorem 할 때도 잠깐 나온다!