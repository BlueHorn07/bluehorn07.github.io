---
title: "Three Sylow Theorems - Application 1"
toc: true
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>
<br>

Three Sylow Theorem은 [이곳]({{"2020/12/26/Sylow-thm.html" | relative_url}})에서 확인할 수 있다.

<br>
<hr>

<span class="statement-title">Example.</span><br>

A group of order 15 is not simple.

여기서의 'simple'은 위수 15를 갖는 군의 normal subgroup이 오직 trivial subgroup만을 갖는다는 말이다. [^1]


<br>
<span class="statement-title">Sol.</span><br>

<div class="math-statement" markdown="1">

$15 = 5 \times 3$

Let $P_3$, $P_5$ be Sylow 3-subgroup & Sylow 5-subgroup.

We claim that either $P_3$ or $P_5$ is **<u>normal</u>** to $G$.

<br>

(proof by contradiction)

Supp. both $P_3$ and $P_5$ are not normal to $G$.

<br>

$P_5$에 대해서 먼저 살펴보자.

먼저 **<u>1st Sylow Thm</u>**에 의해 $G$는 위수가 5인 subgroup을 적어도 하나를 가진다.

그리고 **<u>3rd Sylow Thm</u>**에 의해 (# of 5-subgroup)은 mod 5에서 1과 합동이다.<br>
따라서 (# of 5-subgroup)는 1 도는 6 또는 11이다.

이들 중, 1만이 15를 나눌 수 있으므로 $G$는 위수가 5인 단 하나의 5-subgroup을 갖는다. (**<u>3rd Sylow Thm</u>**)

그러나 만약 $P_5$이 단 하나만 존재한다면, **<u>2nd Sylow Thm</u>**에 의해 이것은 $P_5$가 normal subgroup임을 의미한다.

$p$가 동일한 두 Sylow $p$-subgroup $P_5$, $P_5'$에 대해 둘은 conjugate 관계이다.

<br>

이때, $G$에 대한 inner auto-morphism을 생각해보자.

그럼 For $g \in G$에 대해 $\sigma_g(x) = gxg^{-1} \quad (\forall g \in G)$로 homo-morphism이 정의된다.

이때, $P_5$를 $\sigma_g$에 태우게 되어 얻는 $\sigma_g (P_5)$는 **<u>2nd Sylow Thm</u>**에 의해 여전히 Sylow 5-subgroup이다.

모든 $g \in G$에 대해 $\sigma_g$를 구해 $P_5$를 태워도 여전히 $P_5$라는 결과를 얻는다.

따라서 $P_5$는 normal subgroup이다!

<br>

마찬가지의 방법으로 $P_3$에 대해서도 해볼 수 있다.

**<u>3rd Sylow Thm</u>**에 의해 (# of 3-subgroup)은 1, 4, 7, 11, 14이다. 이중에 15를 나누는 것은 1 뿐이다.

따라서 $G$에서 $P_3$는 단 하나 뿐이다. 따라서 $P_3$는 normal subgroup이다.

<br>

따라서 "both $P_3$ and $P_5$ are not normal to $G$."라는 명제는 거짓이다!!

"either $P_3$ or $P_5$ is normal to $G$."가 올바른 표현이다.

따라서 $G$는 trivial subgroup 외에도 다른 noraml subgroup을 갖는다!! $\blacksquare$

</div>

<br>
<hr>

#### Lemma 37.5

<span class="statement-title">Lemma.</span><br>

<div class="statement" markdown="1">

Let $H, K \trianglelefteq G$.

Supp. $H \cap K = \\{ e \\}$, and $H \lor K = G$.

Then, $H \times K \cong G$.

</div>

<br>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

먼저 $hk = kh$임을 보이자.

$$
\begin{aligned}
    hk(kh)^{-1} &= hkh^{-1}k^{-1} \\
                &= (hkh^{-1})k^{-1} \in K \\
                &= h(kh^{-1}k^{-1}) \in H
\end{aligned}
$$

따라서 $hk(kh)^{-1} \in H \cap K$이다.

이때, 조건에서 $H \cap K = \\{ e \\}$라고 했으므로

$hk(kh)^{-1} = e$이고, 따라서 $hk = kh$이다.

<br>

$H \times K \cong G$를 보이기 위해 homomoprhism $\phi$를 하나 정의하자.

$$
\begin{aligned}
    \phi: H \times K &\longrightarrow G \\
            (h, k) &\longmapsto hk
\end{aligned}
$$

그러면, $\phi$에 대해 아래가 성립하므로

$$
\begin{aligned}
    \phi((h, k)(h', k')) &= \phi(hh', kk') \\
                         &= hh'kk' = h(h'k)k' \\
                         &= hkh'k' \\
                         &= \phi(h, k)\phi(h', k')
\end{aligned}
$$

$\phi$는 homomorphism이다.

<br>

이제 $\phi$의 kernel에 대해 생각해보자.

만약 $\phi(h, k) = e$라면, $hk = e$인 원소가 $\ker \phi$에 속할 것이다.

이때, $h = k^{-1}$이므로 $h = k^{-1} \in H \cap K$이다. 따라서 $h = k^{-1} = e$이다.

따라서 $\ker \phi = \\{ (e, e) \\}$ 하나 뿐이므로, $\phi$는 1-1이다.

<br>

조건에서 $H \trianglelefteq G$라고 했으므로 $H \lor K = HK$가 된다. (by [Lemma 34.4]({{"2020/12/25/Isomorphism-Thm.html#lemma-344" | relative_url}}) & [join]({{"2020/12/25/Isomorphism-Thm.html#h-join-k" | relative_url}}))

또, 가정에서 $H \lor K = G$라고 했으므로, $H \lor K = HK = G$가 된다.

따라서 $\phi$의 이미지인 $\phi[H \times K] = HK$가 곧 $G$ 전체가 된다.

따라서 $\phi$는 onto이다.

<br>

$\phi$가 homo-, 1-1 & onto이므로 $\phi$는 isomorphism이다.

따라서 $H \times K \cong G$이다!

</div>

이 명제가 Lemma인 이유는 아무래도 Isomoprhism 파트에서 다뤘던 [Lemma 34.4]({{"2020/12/25/Isomorphism-Thm.html#lemma-344" | relative_url}})로부터 쉽게 유도할 수 있기 때문인 것 같다.

또 원래 목표인 $H \times K \cong G$는 사실 $H \times K \cong G = H \lor K = HK$이므로 사실상 $H \times K \cong HK$임을 보이는 명제였다.

<br>
<hr>

<span class="statement-title">Theorem.</span><br>

<div class="statement" markdown="1">

Let $p$ be a prime,

then every group $G$ of order $p^2$ is abelian.

</div>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

$G$가 cyclic이면 당연히 가환이므로, $G$가 cyclic group이 아니라고 가정하자.

그리고 **<u>1st Sylow Thm</u>**에 의해 $G$에는 $1, p, p^2$의 위수를 갖는 subgroup이 존재한다.

Let $a$ be an elt of $G$ of order $p$. (Cauchy의 정리에 의해 존재성이 보장)

그러면,

$$
\left<a\right> = \{ e, a, a^2, \dots, a^{p-1} \} < G
$$

$b \in G \; \setminus \left<a\right>$인 원소를 생각해보자.

두 원소 $a$, $b$로 만든 순환군 $\left<a\right>$, $\left<b\right>$를 생각해보자.

이때, $\left<a\right> \cap \left<b\right> = \\{ e \\}$여야 한다.

만약 $c \ne e \in \left<a\right> \cap \left<b\right>$라면, $c$로 $\left<a\right>$도 생성할 수 있고, $\left<b\right>$도 생성할 수 있으므로 $\left<a\right> = \left<b\right>$가 된다. 이것은 $b \notin \left<a\right>$에 모순이다.

<br>

**<u>1st Sylow Thm의 두번째 진술</u>**에 의해 $\left<a\right>$는 위수 $p^2$을 갖는 $G$의 subgroup에 대해 normal subgroup이다. 이때, $G$에선 $G$ 만이 위수 $p^2$를 가지므로 $\left<a\right> \trianglerighteq G$이다.

마찬가지로 $\left<b\right>$ 역시 $G$에 normal subgroup이다.

<br>

이번에는 $\left<a\right> \lor \left<b\right>$를 생각해보자.

$\left<a\right> \lor \left<b\right>$에서 $\left<a\right>$는 proper subgroup이다.

즉, $\left<a\right> \subset \left<a\right> \lor \left<b\right>$이다.

이때, $\left<a\right>$의 위수가 $p$이므로 $\left<a\right> \lor \left<b\right>$의 위수는 $p^2$이어야 한다. 따라서 $\left<a\right> \lor \left<b\right> = G$이다.

<br>

우리가 위수 $p^2$인 $G$에서 얻은 사실을 정리하면 아래와 같다.

- $\left<a\right>, \left<b\right> \trianglerighteq G$
- $\left<a\right> \cap \left<b\right> = \\{ e \\}$
- $\left<a\right> \lor \left<b\right> = G$

따라서 앞서 살펴본 Lemma에 따르면

$G \cong \left<a\right> \times \left<b\right> \cong \mathbb{Z}_p \times \mathbb{Z}_p$이다.

즉, $G$가 두 cyclic group의 direct produce와 동형이므로 $G$는 가환군이다!! $\blacksquare$

</div>

<br>
<hr>

<div class="img-wrapper">
  <img src="{{ "/images/modern-algebra-1/group_meme.jpg" | relative_url }}">
</div><br>

아직 Sylow Theorem에 대한 Application이 더 남았다...

[Sylow Theorem - Application 2]({{"2020/12/26/Sylow-thm-Application-2.html" | relative_url}})에서 확인하자.

<br>
<hr>

[^1]: index 2를 갖는 simple subgroup과 헷갈리지 말자!