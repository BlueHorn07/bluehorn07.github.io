---
title: "index-2 Group is normal"
toc: true
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

<div style="text-align: center">
<big>"Any subgroup of index 2 is a normal subgroup."</big>
</div>

<br>

<span class="statement-title">Theorem.</span><br>

<div class="statement" markdown="1">

Let $H \le G$,

If $\lvert G \rvert / \lvert H \rvert = 2$,

Then $H \trianglelefteq G$.

</div>

<br>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

The set of left cosets of $H$ in $G$ froms a partition of $G$.

$$
\begin{equation}
G = H {\cup\mkern-11.5mu\cdot\mkern5mu} {g_i H}
\end{equation}
$$

Then,

$$
\begin{aligned}
    \lvert G \rvert &= \sum_{i \in \Lambda} \lvert {g_i H} \rvert \\
    & = n \times \lvert H \rvert
\end{aligned}
$$

또한, 정리에서 $\lvert G \rvert / \lvert H \rvert = 2$라고 했으므로

$$
\lvert \Lambda \rvert = \lvert G \rvert / \lvert H \rvert = 2 = n
$$

즉, Group $G$가 2개의 left coset으로 분할됨을 의미한다.

그럼 $G/H$는

$$
G/H = \{ H, \; gH \} \quad \textrm{for some} \; g \in G \setminus H
$$

가 된다.

이때, Eq. (1)에서 left coset은 distjoint union으로 $G$를 분할한다.

이 분할은 right coset에 대해서도 마찬가지 이므로

$$
\begin{equation}
    G = H {\cup\mkern-11.5mu\cdot\mkern5mu} {Hg}
\end{equation}
$$

이때, Eq. (1)과 Eq. (2)를 비교해보자.

$g \notin H$이므로 $H \ne Hg$이다. (연산의 닫힘성 위배)

따라서 $gH = Hg$ for some $g \in G \setminus H$이다.

이때, $g \in H$라면, $gH = H = Hg$이므로 두 상황을 합쳐서 진술하면 아래와 같다.

$$
gH = Hg \quad \forall g \in G
$$

이것은 $H$가 Normal subgroup임을 의미한다! $\blacksquare$

</div>

<br>
<hr>

확정적으로 Normal Subgroup을 찾을 수 있는  이 정리는 Symmetric Group $S_n$과 Alternating Group $A_n$ 사이 관계를 명확히 진술한다!

<div class="statement">

$A_n$ is even permutation group.

$A_n = \dfrac{\lvert S_n \rvert}{2}$

따라서 $A_n \trianglelefteq S_n$

</div>
