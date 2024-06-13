---
title: "Group Homo/Iso-morphism + 문풀"
toc: true
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

전부 다 정리하지는 않고, 흥미로운 일부 예제만 정리해둔다.

<br>

<span class="statement-title">Example.</span><br>

Show $(\mathbb{R}^{\*}, \cdot) \not\cong (\mathbb{C}^{\*}, \cdot)$.

<br>
<span class="statement-title">Sol. 1</span><br>

<div class="math-statement" markdown="1">

Supp. $\exists$ an iso- $\phi: \mathbb{C}^{\*} \longrightarrow \mathbb{R}^{\*}$.

THEN, there exist an elt $x \in \mathbb{C}^{\*}$ s.t. $\phi(x) = -1$.

Because $x$ is complex number, there exist a  complex root of $x$, $\sqrt{x} \in \mathbb{C}^{\*}$.

THEN,

$$
\begin{aligned}
\phi \left( \left( \sqrt{x} \right)^2 \right) &= \phi(x) = -1 \\
&= \left( \phi(\sqrt{x}) \right)^2 = -1
\end{aligned}
$$

이때, $\phi(\sqrt{x}) \in \mathbb{R}^{\*}$이므로 $\left( \phi(\sqrt{x}) \right)^2 > 0$이다. 하지만 우변이 음수이므로 모순이다!

따라서 처음에 가정한 iso- $\phi: \mathbb{C}^{\*} \longrightarrow \mathbb{R}^{\*}$는 존재하지 않는다. $\blacksquare$

</div><br>

$\phi$를 반대 방향으로 잡아서 증명을 할 수도 있다. 그러나 이 경우는 좀더 테크니컬 하다.

<span class="statement-title">Sol. 2</span><br>

<div class="math-statement" markdown="1">

Supp. $\exists$ an iso- $\phi: \mathbb{R}^{\*} \longrightarrow \mathbb{C}^{\*}$.

THEN, it is true that $\phi(1) = 1$; <small>identity maps to identity</small>

THEN,

$$
\begin{aligned}
    \phi(1) &= \phi(-1 \cdot -1) \\
            &= \phi(-1) \phi(-1) = 1
\end{aligned}
$$

따라서 $\phi(-1) = -1$이다.

만약 $\phi$가 iso- 라면 $\sqrt{-1} = i \in \mathbb{C}^{\*}$에 대해서도 대응하는 원소 $x$가 $\mathbb{R}^{\*}$에 존재할 것이다.

그렇다면,

$$
\begin{aligned}
    -1 = \sqrt{-1} \sqrt{-1} = \phi(x) \phi(x) = \phi(-1)
\end{aligned}
$$

이므로 $\mathbb{R}^{\*}$ 아래에서 $x^2 = -1$ 식이 성립한다.

하지만, $x \in \mathbb{R}^{\*}$에 대해 $x^2 > 0$이므로 $x^2 = -1$는 모순이다!

따라서 처음에 가정한 iso- $\phi: \mathbb{R}^{\*} \longrightarrow \mathbb{C}^{\*}$는 존재하지 않는다. $\blacksquare$

</div>

<br>
<hr>

가벼운 문풀 문제들을 풀어보자.

<br>
<span class="statement-title">Problem.</span> 1<br>

Q. 무한군에서 유한군으로 대응하는 non-trivial homo-는 불가능하다. (T/F)

A. False; 가질 수 있다.

$$
\begin{aligned}
    \phi: \mathbb{Z} &\longrightarrow \mathbb{Z}_2 \\
                  n  & \longmapsto n \quad (\textrm{mod}\; 2)
\end{aligned}
$$

<br>
<span class="statement-title">Problem.</span> 2<br>

non-trivial homo- $\phi$가 존재하는지 여부를 밝혀라.

$$
\phi: S_4 \longrightarrow S_3
$$

A. Define $\phi$ as

$$
\begin{aligned}
    \phi: S_4 & \longrightarrow S_3 \\
        \textrm{even} & \longmapsto (1) \\
        \textrm{odd} & \longmapsto (1 \; 2)
\end{aligned}
$$


<br>
<hr>

Homo- 가 존재함을 보이는 건 머리를 잘 굴려서 생각해내면 되는데, Homo- 가 존재하지 않음을 보이는 건 어떤 정리를 이용해야만 한다.

아래의 정리를 증명해보자.

<br>
<span class="statement-title">Theorem.</span><br>

<div class="notice" markdown="1">

Let $\phi$ be a group homo-, Show that

If $\lvert G \rvert < \infty$, then

1. $\lvert \phi[G] \rvert < \infty$
2. $\lvert \phi[G] \rvert$ divides $\lvert G \rvert$

</div>

<br>
<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

1번은 $\phi$가 well-defined 함수라면, <br>
$G$의 원소 하나를 $G'$의 원소 하나로 대응시킬 것이기 때문에 당연히 $\lvert \phi[G] \rvert < \infty$가 된다.

</div>

<details>
<summary>2번 명제에 대한 증명</summary>
<div class="math-statement" markdown="1">

2번은 $\lvert \phi[G] \rvert$가 $\lvert G \rvert$의 약수라는 점에서 힌트를 얻어 Lagrange Thm을 생각해내고, "그럼 $\lvert \phi[G] \rvert$와 동형일 subgroup $H$가 존재하지 않을까"라고 생각해서 해결하였다.

그래서 우리의 목표는 $\phi[G] \cong H$ for some $H \le G$를 만족하게 하는 iso-인 $\psi$를 찾는 것이 된다.

$\psi$를 아래와 같이 정의해보자.

$$
\begin{aligned}
    \psi: \phi[G] &\longrightarrow H \subseteq G\\
               g' &\longmapsto \textrm{inv. of} \; g'
\end{aligned}
$$

즉, $\psi$를 $\phi^{-1}$로 설정한 것이다!

사실 homo- $\phi$에 대해서는 아래의 두 명제가 성립한다.

1. $H \le G \implies \phi[H] \le G'$
2. $H' \le G'\implies \phi^{-1}[H'] \le G$

이 명제를 잘 조합해보면 되는데,

$G \le G$이므로 $\phi[G] \le G'$이다.

여기서 $\phi^{-1}$를 취하면, $\phi^{-1} \left[ \phi [G] \right] \le G$가 된다.

즉, 우리가 찾으려는 $H$는 사실 $\phi^{-1} \left[ \phi [G] \right] = \psi \left[ \phi [G] \right]$인 것이다.

<br>

그렇담 우리는 $\lvert \phi[G] \rvert = \lvert \phi^{-1} \left[ \phi [G] \right] \rvert$만 보이면 충분하다.

만약 서로 다른 $g'_1, g'_2 \in \phi[G]$를 다시 $G$로 보낼 때, $\phi^{-1}(g'_1) = \phi^{-1}(g'_2) = g \in G$라고 가정하자. 이것은 $g \in G$가 $\phi$에 의해 두 가지 원소로 매핑된다는 것이므로 $\phi$의 well-defined에 모순이다.

따라서 $\phi^{-1}$는 1-1이다. 이것은 곧 $\lvert \phi[G] \rvert = \lvert \phi^{-1} \left[ \phi [G] \right] \rvert$를 의미한다.

<br>

$\phi^{-1} \left[ \phi [G] \right] \le G$이므로 Lagrange Thm에 의해 $\lvert \phi^{-1} \left[ \phi [G] \right] \rvert \mid \lvert G \rvert$이다.

이때, $\lvert \phi[G] \rvert = \lvert \phi^{-1} \left[ \phi [G] \right] \rvert$이므로 $\lvert \phi[G] \rvert \mid \lvert G \rvert$이다. $\blacksquare$

</div>
</details>

