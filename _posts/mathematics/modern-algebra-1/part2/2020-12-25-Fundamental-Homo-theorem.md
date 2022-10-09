---
title: "Fundamental Homomorphism Theorem"
layout: post
tags: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

FHT를 살펴보기 전에 간단한 Factor Group Homomorphism에 대해 살펴보자.

#### Canonical Homomorphism

<span class="statement-title">Theorem.</span><br>

<div class="statement" markdown="1">

Let $H \trianglelefteq G$, and define a mapping $\gamma: G \longrightarrow G/H$ where $\gamma(x) = xH$. 

Then, $\gamma$ is a group homormophism, with $\ker \gamma = H$.

</div>

<span class="statement-title">proof.</span><br>

증명은 정말 간단하다. 

(1) $\gamma$가 homomorphism임을 보이고, (2) $\gamma$의 kernel이 $H$임을 보이면 된다.

증명이 너무 쉬워서 여기에서는 생-략한다.

<br>

주목할 점은 이 homormophism $\gamma$에 이름이 붙었다는 것이다.

"**<u>Canonical homormophism</u>**"이라는 이름이다!

<br>
<hr>

이제 Homo-morphism 파트에서 가장 중요하고, 응용도 많이 되는 FHT에 대해 살펴보자!

<span class="statement-title">Theorem.</span> Fundamental Homormophism Theorem (FHT)<br>

<div class="statement" markdown="1">

Let $\phi: G \longrightarrow G'$ be a group homo-.

Then, 

1. $\phi[G]$ is a group.
2. $G / {\ker \phi} \cong \phi[G]$

</div>

<br>
<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

1\. $\phi[G]$ is a group

$\phi[G]$가 Group의 성질을 잘 만족하는지 확인하면 된다.

(1) closed under opr.

$\phi(g_1) \cdot \phi(g_2) = \phi(g_1 g_2)$

(2) associativity

생-략

(3) identity

$\phi(e) = e'$ is identity in $\phi[G]$.

(4) inverse

$\left(\phi(g)\right)^{-1} = \phi(g^{-1}) \in \phi[G]$

</div>

<div class="img-wrapper">
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Diagram_of_the_fundamental_theorem_on_homomorphisms.svg/440px-Diagram_of_the_fundamental_theorem_on_homomorphisms.svg.png" style="width:40%;">
</div>


<div class="math-statement" markdown="1">

2\. $G / {\ker \phi} \cong \phi[G]$

두 Group의 동형을 보이기 위해 mapping $\mu$를 아래와 같이 정의하자.

$$
\begin{aligned}
    \mu: G / {\ker \phi} & \longrightarrow \phi[G] \\
                   g(\ker \phi) & \longmapsto \phi(g)
\end{aligned}
$$

$\mu$가 iso-morphism인지 확인하자!

<br>

(1) $\mu$ is a homo-.

$$
\begin{aligned}
    \mu (g_1 K \cdot g_2 K) &= \mu (g_1 g_2 K) = \phi(g_1 g_2) \\
    \mu (g_1 K) \cdot \mu (g_2 K) &= \phi(g_1) \cdot \phi(g_2) = \phi(g_1 g_2)
\end{aligned}
$$

따라서 $\mu$는 Homo-이다.

<br>

(2) $\mu$ is 1-1 & onto

(i) $\mu$ is onto

For $\phi(a) \in \phi[G]$, there's an inverse image of it. It is $a(\ker \phi)$.

(ii)

Supp. $\mu(aK) = \mu(bK)$, Then

$$
\begin{aligned}
    \mu(aK) = \mu(bK) &\implies \phi(a) = \phi(b) \\
                      &\implies \phi(b)^{-1} \phi(a) = e' \\
                      &\implies \phi(b^{-1} a) = e' \\
                      &\implies b^{-1} a \in K = \ker \phi \\
                      &\implies b^{-1}a K = K \\
                      &\implies aK = bK
\end{aligned}
$$

<br>

well-definedness도 잊지 말고 확인하자!

(3) $\mu$ is well-defined.

Supp. $aK = bK$, Then

$$
\begin{aligned}
    aK = bK &\implies b^{-1} a K = K \\
            &\implies b^{-1} a \in K = \ker \phi \\
            &\implies \phi(b^{-1}a) = e' \\
            &\implies \phi(b^{-1}) \phi(a) = e' \\
            &\implies \phi(a) = \phi(b) \\
            &\implies \mu(aK) = \phi(a) = \phi(b) = \mu(bK)
\end{aligned}
$$

</div>

<hr>

### Application



<hr>

#### Reference
- [Matthew Macauley](http://www.math.clemson.edu/~macaule/classes/m20_math4120/slides/math4120_lecture-4-03_h.pdf)
