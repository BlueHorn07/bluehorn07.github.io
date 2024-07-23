---
title: "Cayley Theorem"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

군에 대한 일반적인 성질을 기술하기에 명제 자체는 놀랍지만, 별 쓸모는 없는 명제, "Cayley's Theorem"이다!

<br>

<span class="statement-title">Theorem.</span><br>

<div class="notice" markdown="1">

Every group is isomorphic to a subgroup of a suitable permutation group.

$$
G \le S_G
$$

</div><br>


<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

Let $G$ be a group, and $S_G$ be a set of permutations on $G$.

We will show that $G$ is isomorphic to some subgroup of $S_G$.

Chose one of $a \in G$, and consider a simple mapping $\lambda_a$ s.t.

$$
\begin{aligned}
    \lambda_a: G & \longrightarrow G \\
    g & \longmapsto ag
\end{aligned}
$$

We claim that $\lambda_a$ is 1-1 & onto, i.e. $\lambda_a \in S_G$.

(1) $\lambda_a$: 1-1

Supp. $ag = ag'$, THEN

$\implies a^{-1}(ag) = a^{-1}(ag') \implies g = g'$

따라서 $\lambda_a$ is 1-1.

<br>

(2) $\lambda_a$: onto

For $g' \in G$, find $g$ s.t. $g' = ag$, THEN

$a^{-1}g' = g \in G$ is that inverse image of $g'$.

따라서 $\lambda_a$ is onto.

<br>
<hr>

Let $G' := \\{ \lambda_a \mid a \in G\\} \subseteq S_G$.

We claim $G'$ is a subgroup of $S_G$; $G' \le S_G$

(1) $G'$ is closed under opr.

$\lambda_a \cdot \lambda_{a'} = \lambda_{aa'} \in G'$

<br>

(2) Identity

$\lambda_e: g \mapsto g = \textrm{id}_{G'}$

<br>

(3) Inverse

If $\lambda_a \in G'$. THEN there exist an inv. map.

$(\lambda_a)^{-1} = \lambda_{a'} \in G'$

That inv. map $\lambda_{a'}$ is about $a' = a^{-1}$.

<br>

따라서 $G' \le S_G$이다!

<hr>

Finally, we will show $G \cong G'$.

Define a group iso- $\psi$ as

$$
\begin{aligned}
    \psi: G & \longrightarrow G' \\
    a & \longmapsto \lambda_a
\end{aligned}
$$

(1) $\psi$ is 1-1

Supp. $\lambda_a = \lambda_b$, THEN

$\lambda_a (e) = a = b = \lambda_b (e)$

<br>

(2) $\psi$ is onto

Clear! $\lambda_a$ has inv. image $a \in G$ under $\psi$.

<br>

(3) $\psi$ is homo-

$$
\begin{aligned}
    \psi(ab) &= \lambda_{ab} \\
             &= \lambda_a \cdot \lambda_b = \psi(a) \cdot \psi(b) \\
\end{aligned}
$$

<br>

따라서 $G \cong G'$이고, $G' \le S_G$이므로

$$
G \cong G' \le S_G
$$

Every group is isomorphic to a subgroup of a suitable permutation group. $\blacksquare$
</div><br>

