---
title: "Factor Group"
toc: true
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

군론에서는 두 가지 종류의 Factor Group이 존재한다.

1. Factor Group from Normal Subgroup
2. Factor Group from Homomorphism

<br>
<hr>

Factor Group을 정의하려면, 먼저 Factor Group에서 사용할 "**coset 간의 연산**"을 정의해야 한다!

<span class="statement-title">Theorem.</span><br>

<div class="statement" markdown="1">

Let $H \le G$, THEN the left coset multiplication is well-defined by equation

$$
\begin{equation}
    (aH)(bH) := abH
\end{equation}
$$

</div>

이때, 위의 식 (1)이 성립하여 연산이 well-defined이 되기 위해선

<div class="statement" markdown="1" style="text-align: center;">
"The left & right coset conincide, so that $aH = Ha \quad \forall a \in G$"
</div>

조건이 만족되어야 한다!! 이 조건은 $H$가 $G$의 **normal subgroup**임을 말한다!!

<br>
<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

($\implies$) Supp. that $(aH)(bH) = abH$ is well-defined.

To show $aH = Ha$,

$$
\begin{aligned}
    (aH)(a^{-1}H) = aa^{-1}H = eH = H \\
\end{aligned}
$$

이때, $aHa^{-1} \cdot H = H$에서 좌변의 결과가 $H$에 다시 들어가야 하므로 $aha^{-1} \in H$일 것이다. 따라서 $aHa^{-1} \subseteq H$

반대로 $a^{-1}Ha \cdot H = H$에 대해서는 $a^{-1}Ha \subseteq H$의 결과를 얻는다.

두 사실을 잘 조합하면,

$$
\begin{aligned}
    aHa^{-1} \subseteq H & \implies aH \subseteq Ha \\
    a^{-1}Ha \subseteq H & \implies Ha \subseteq aH
\end{aligned}
$$

따라서 $aH = Ha$의 결과를 얻는다. 즉, left & right coset이 일치하는 normal subgroup $H$이다!

<hr>

반대 방향에 대해서도 증명을 해보자!

($\impliedby$) Supp.that $aH = Ha \quad \forall a \in G$.

To show "$(xH)(yH) = xyH$ is well-defined",

Let $xH = x'H$, and $yH = y'H$.

Then, we have to show $(xH)(yH) = (x'H)(y'H)$; i.e. $xyH = x'y'H$.

<br>

From $xH = x'H$, $xe = x'h_1$ for some $h_1 \in H$, <br>
and from $yH = y'H$, $ye = y'h_2$ for some $h_2 \in H$.

Then, $xy = (x'h_1)(y'h_2)$에서 $H$가 normal subgroup이므로 $(x'h_1)(y'h_2) = x'y'h_1h_2$.

따라서 $xyH = x'y'h_1h_2H = x'y'H$.

따라서 $H$가 normal subgroup이면, Factor Group operation은 잘 정의된다! $\blacksquare$

</div>

<br>
<hr>

이제 본격적으로 Factor Group을 만들어보자!!


<span class="statement-title">Theorem.</span><br>

<div class="statement" markdown="1">

Let $H \le G$ be a normal subgroup,

Then the set of cosets of $H$ forms a **<u>factor group</u>** $G/H$ ($G$ mod $H$) under the binary operation.

$$
(aH)(bH) = abH
$$

</div>

<br>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

실제로 $G/H$가 Group인지 확인하면 된다.

1. Closed under opr; 당연쓰
2. Associativity; 당연쓰
3. Identity; $H$
4. Inverse; $(aH)^{-1} = a^{-1}H$

</div>

<br>
<hr>

#### Normal Subgroup

Normal Subgrop은 $aH = Ha \quad (\forall a \in G)$로 정의되지만, 이 조건과 동치인 조건들이 몇몇 있다.


대표적으로

$$
\begin{equation}
    aHa^{-1} \subseteq H \quad (\forall a \in G)
\end{equation}
$$

이다.


<div class="math-statement" markdown="1">

부등호 방향이 한 방향이라 $aHa^{-1} = H$ 조건을 이끌어 내기에는 부족해보일지도 모른다. 하지만,

$\forall a\in G$이므로 Eq.(2)에 $a$ 대신 $a^{-1}$을 넣어도 식이 성립한다. 따라서

$$
\begin{aligned}
    aHa^{-1} \subseteq H &\quad (\forall a \in G) \\
    a^{-1}Ha \subseteq H &\quad (\forall a \in G) \\
\end{aligned}
$$

그리고 기존의 Eq.(2)에서 양변에 $a^{-1}$, $a$를 취하면, 아래의 부등식을 얻는다.

$$
\begin{aligned}
    aHa^{-1} \subseteq H &\implies a^{-1}(aHa^{-1})a \subseteq a^{-1}(H)a \\
    &\implies H \subseteq a^{-1}Ha
\end{aligned}
$$

$a^{-1}Ha \subseteq H$, $H \subseteq a^{-1}Ha$이므로 $a^{-1}Ha = H$이다. 즉, Normal Subgroup이다! $\blacksquare$

</div>

<br>
<hr>

#### Factor Group from Homomorphism

이번엔 Homomorphism $\phi$를 통해 Factor Group을 정의해보자!

<span class="statement-title">Theorem.</span><br>

<div class="statement" markdown="1">

Let $\phi: G \longrightarrow G'$ be a group homormophism.

Then, $\ker \phi = \\{ g \in G \mid \phi(G) = e'\\}$ is a **<u>normal subgroup</u>**.

</div>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

We will show $g (\ker \phi) g^{-1} \subseteq \ker \phi \quad \forall g \in G$.

Let's do a conjugation on $x \in \ker \phi$, $gxg^{-1}$

Then,

$$
\begin{aligned}
    \phi(gxg^{-1}) = \phi(g) e' \phi(g^{-1}) = \phi(g) \phi(g^{-1}) = e'
\end{aligned}
$$

따라서 $gxg^{-1} \in \ker \phi$이다!

따라서 $g(\ker \phi)g^{-1} \subseteq \ker \phi \quad \forall g \in G$. $\blacksquare$

</div>

<hr>

<span class="statement-title">Property.</span><br>

<div class="statement" markdown="1" style="text-align: center;">

Every subgroup of abelian is normal.

</div>

<br>
<hr>

### Mappings in Group Theory

1. Homo-morphism
2. Iso-morphism
3. Auto-morphism
4. Endo-morsphim

<br>

<span class="statement-title">Homo-morphism.</span><br>

pass


<span class="statement-title">Iso-morphism.</span><br>

Homo-morphism + (1-1 & onto)


<span class="statement-title">Auto-morphism.</span><br>

Iso-morphism + (self mapping; $\phi: G \longrightarrow G$)


<span class="statement-title">Endo-morphism.</span><br>

Homo-morphism + (self mapping)

<hr>

#### Inner Automorphism

<div class="statement" markdown="1">

Let $G$ be a group, and $g \in G$.

Define $\sigma_g$ as

$$
\begin{aligned}
    \sigma_g: G &\longrightarrow G \\
              x & \longmapsto gxg^{-1}
\end{aligned}
$$

Then, $\sigma_g$ is a **auto-morphism**.

</div>

실제로 $\sigma_g$가 Auto-morphism인지에 대한 부분을 너무 쉬워서 생-략 하겠다.

이때, $\sigma_g$는 $g \in G$가 하나 주어질 때마다 $\sigma_g$를 생성할 수 있으므로 "**Inner Auto-morphism**"이라고 한다!
