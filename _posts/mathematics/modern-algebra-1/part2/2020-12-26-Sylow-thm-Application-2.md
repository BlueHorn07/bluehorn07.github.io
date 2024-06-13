---
title: "Three Sylow Theorems - Application 2"
toc: true
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>
<br>

Three Sylow Theorem은 [이곳]({{"2020/12/26/Sylow-thm" | relative_url}})에서 확인할 수 있다.

<br>
<hr>

#### Theorem 37.7

<span class="statement-title">Theorem 37.7</span><br>

<div class="notice" markdown="1">

For a group of order $pq$ ($p<q$ are prime)

1\. is not simple.

2\. If $q \not\equiv 1$ (mod $p$), then $G$ is cyclic.

</div>


<br>
<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

[첫번째 명제]

Let $n$ be (# of Sylow $q$-subgroups) of $G$,

then by [3rd Sylow Theorem]({{"2020/12/26/Sylow-thm#3rd-sylow-theorem" | relative_url}})

- $n \mid (\lvert G \rvert = pq)$
- $n \equiv 1$ (mod $q$)

그러면, $n$은 $n = kq + 1$의 꼴이 될 것이다.

따라서

$$
\begin{aligned}
    &n = kq + 1 \\
    &\implies (kq + 1) \mid pq \\
    &\implies (kq + 1) \mid p \quad (\because (kq+1) \not\mid q) \\
    &\implies (kq+1) = 1 \quad \textrm{or} \quad (kq+1) = p
\end{aligned}
$$

만약 $(kq+1) = p$라고 가정하자.

이때, $k \ge 1$가 되어야 하는데, 이 경우 $p > q$가 되므로 명제의 조건에 모순이다.

따라서 $(kq+1) = 1$이다.

<br>

이 사실은 $n=1$이라는 것이므로 $G$는 오직 하나의 Sylow q-subgroup을 가진다는 말이다.

또한, 이것은 그 Sylow $q$-subgroup이 **<u>normal subgroup</u>**임을 말한다!!

따라서 $G$는 not simple이다! $\blacksquare$

</div>

<div class="math-statement" markdown="1">

[두번째 명제]

Supp. $q \not\equiv 1$ (mod $p$).

We will show that there exist only Sylow $p$-subgroup of $G$ and it is normal.

<br>

Let $m$ be a (# of Sylow $p$-subgroup).

We will show that $m=1$

By [3rd Sylow Theorem]({{"2020/12/26/Sylow-thm#3rd-sylow-theorem" | relative_url}})

- $m \mid (\lvert G \rvert = pq)$
- $m \equiv 1$ (mod $p$)

따라서 $m = kp + 1$이고, 첫번째 명제에서와 동일한 방법으로

$kp + 1 = 1$ 또는 $kp + 1 = q$이다.

<br>

만약 $kp + 1 = q$라면, 이것은 명제에서 가정한 $q \not\equiv 1$ (mod $p$) 조건에 모순된다!

따라서 $m = kp + 1 = 1$이고, Sylow $p$-subgroup의 수는 단 하나이다.

따라서 Sylow $p$-subgroup은 **normal subgroup**이다.

<br>

Let $H$ be a Sylow $p$-subgroup, and $K$ be a Sylow $q$-subgroup.

앞에서의 논의를 통해 $H \trianglelefteq G$임을 보였고, 첫번째 명제를 증명하는 과정에 $K \trianglelefteq G$임을 보였다.

$H \cap K$은 $H \cap K \le H$, $H \cap K \le K$이므로, **<u>Lagrange Thm</u>**에 의해 $\lvert H \cap K \rvert = 1$이다.

또한, $H \lor K$는 $H$, $K$를 완전히 포함하는 $G$의 부분군이므로 위수가 $pq$를 나눈다.

따라서 $H \lor K = G$이다.

<br>

그러면, [Lemma 37.5]({{"2020/12/26/Sylow-thm-Application-1#lemma-375" | relative_url}})에 의해 $H \times K \cong G$이다.

$H$, $K$가 cyclic group이므로 (위수가 소수 $p$이므로 cyclic이 보장된다.)

$H \times K$ 역시 cyclic이다.

따라서 동형인 $G$ 역시 cyclic이다. $\blacksquare$

</div>

<br>
<hr>

#### Lemma 37.8

<span class="statement-title">Lemma 37.8</span><br>

<div class="notice" markdown="1">

Let $H$, $K$ be finite subgroups of $G$.

Then,

$$
\lvert HK \rvert = \dfrac{\lvert H \rvert \times \lvert K \rvert}{\lvert H \cap K \rvert}
$$

</div>

$\lvert HK \rvert$의 상한을 생각해보자.<br>
$hk = h'k'$인 경우가 발생하지 않는다면, $\lvert HK \rvert = \lvert H \rvert \times \lvert K \rvert$가 될 것이다.

만약 $hk = h'k'$인 경우가 발생한다면, 그것은 얼마나 발생할까? <br>
Lemma 37.8은 그 경우가 정확히 $\lvert H \cap K \rvert$ 만큼 발생한다고 말한다.

<br>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

Let $h_1, h_2 \in H$, $k_1, k_2 \in K$.

만약 $h_1k_1 = h_2k_2$라면,

$\iff h_2^{-1}h_1 = k_2k_1^{-1}$가 될 것이고 이것은 $H \cap K$에 속하게 될 것이다!

<br>

Let $h_2^{-1}h_1 = k_2k_1^{-1} = x \in H \cap K$.

Then, $h_2 = h_1x^{-1}$, $k_2 = x k_1$ for some $x \in H \cap K$가 될 것이다.

<br>

그리고 $y \in H \cap K$에 대해서도 $h_3$, $k_3$를 $h_3 = h_1 y^{-1}$, $k_3 = y k_1$로 잡으면,

마찬가지로 $h_3k_3 = h_1k_1$일 것이다.

따라서 $HK$의 각 원소 $hk \in HK$는 $H \cap K$의 원소 갯수만큼 $hk = h_ik_i$인 $h_ik_i$를 만들 수 있다.

즉, $HK$가 $\lvert H \cap K \rvert$ 만큼의 묶음으로 분할된다는 말이다.

<br>

잘 비틀어 생각하면, $H \times K$를 $H \cap K$의 coset으로 분할한다는 말과 같다.

따라서 정리하면,

$$
\lvert HK \rvert = \dfrac{\lvert H \rvert \times \lvert K \rvert}{\lvert H \cap K \rvert}
$$

$\blacksquare$

</div>

<br>
<hr>

이제 교재에 있는 Sylow Theorem과 관련된 Lemma나 Theorem은 모두 살펴본 셈이다.

이 Sylow Theorem을 제대로 활용하는 문제들을 또 살펴보자!!

<br>
<hr>

### Sylow Theorem - Examples

#### Type-1

<span class="statement-title">Example 1.</span><br>

Let $\lvert G \rvert = p^n$ ($n > 1$, $p$ is prime).

Then, $G$ is not simple.

<div class="math-statement" markdown="1">

By [1st Sylow thm]({{"2020/12/26/Sylow-thm#1st-sylow-theorem" | relative_url}}),

there exists a $p$-subgroup $H$ where $\lvert H \rvert = p^{n-1}$.

이 $p$-subgroup $H$는 $G$에 대해 normal subgroup이다.

따라서 $\lvert G \rvert = p^n$인 group $G$는 simple group이 아니다! $\blacksquare$

</div>

위 결과에 의해 위수가 16인 군은 위수가 8인 normal subgroup을 가지므로 simple group이 아니다!

<br>
<hr>

<span class="statement-title">Example 2.</span><br>

Let $\lvert G \rvert = 15$.

Then, $G$ is cyclic.

<div class="math-statement" markdown="1">

$15 = 3 \times 5$이다.


- 위수가 두 소수 $3$, $5$의 곱으로 표현
- $5 \not\equiv 1$ (mod $3$)

이므로

위에서 증명한 [Theorem 37.7]({{"2020/12/26/Sylow-thm-Application-2#theorem-377" | relative_url}})의 두번째 명제에 의해

$G$는 cyclic group이다. $\blacksquare$

</div>

<br>
<hr>

<span class="statement-title">Example 3.</span><br>

Let $\lvert G \rvert = 20$.

Then, $G$ is not simple.

<div class="math-statement" markdown="1">

$\lvert G \rvert = 20 = 2^2 \times 5$

아래와 같이 (# of Sylow $p$-subgroup)들을 정의하자.

- $n$ := (# of Sylow 2-subgroup)
- $m$ := (# of Sylow 5-subgroup)

그러면, [3rd Sylow Theorem]({{"2020/12/26/Sylow-thm#3rd-sylow-theorem" | relative_url}})에 의해

- $n \mid 20$, and $n \equiv 1$ (mod 2) $\implies$ $2k + 1 \mid 20$
- $m \mid 20$, and $m \equiv 1$ (mod 5) $\implies$ $5l + 1 \mid 20$

<br>

**<u>Claim</u>**: either $n$ or $m$ is 1

먼저 $m$에 대해 살펴보자.

$5l + 1 \mid 20$이므로 $5l + 1$는 1, 2, 4, 5, 10, 20이 가능하다. <br>
이때, $5l + 1$이므로 $5l + 1 = 1$이 되어야 한다.

따라서, $m$ = (# of Sylow 5-subgroup) = 1이므로 Sylow 5-subgroup은 normal subgroup이다.

따라서 $G$는 not simple group이다. $\blacksquare$

</div>

<br>
<hr>

#### Type-2

이 문제부터는 다른 방식으로 not simple을 판별한다!!

<br>

<span class="statement-title">Example 5.</span><br>

Let $\lvert G \rvert = 48$.

Then, $G$ is not simple.

<div class="math-statement" markdown="1">

$\lvert G \rvert = 48 = 2^4 \times 3$

$n$ := (# of Sylow 2-subgroup)라고 정의하자, <br>
그러면 [3rd Sylow Theorem]({{"2020/12/26/Sylow-thm#3rd-sylow-theorem" | relative_url}})에 의해 $n$ = 1 or 3 이다.

<br>

$n = 3$인 경우를 가정해보자.

$G$에서 $H \ne K$인 두 Sylow 2-subgroup을 생각해보자. <br>
또, $H$, $K$가 Sylow 2-subgroup이므로 $\lvert H \rvert = \lvert K \rvert = 2^4$이다.

<br>

앞에서 증명한 [Lemma 37.8]({{"2020/12/26/Sylow-thm-Application-2#lemma-378" | relative_url}})을 이용해 $\lvert H \cap K \rvert = 8$임을 보일 것이다.

$HK$의 위수를 생각해보자.

[Lemma 37.8]({{"2020/12/26/Sylow-thm-Application-2#lemma-378" | relative_url}})에 의해 아래의 식이 성립한다.

$$
\lvert HK \rvert = \dfrac{\lvert H \rvert \times \lvert K \rvert}{\lvert H \cap K \rvert}
$$

이때, 분모의 $H \cap K$는 subgroup들의 intersection이므로 당연히 $G$의 subgroup이 되며, <br>
Lagrange Thm에 의해 $16 = \lvert H \rvert$의 약수가 된다.

만약 $\lvert H \cap K \rvert = 4$라면, $\lvert HK \rvert = \dfrac{16 \times 16}{4} = 64$가 되므로 <br>
$HK \subseteq G$에 위배된다. 따라서 $\lvert H \cap K \rvert \ne 4$.

<br>

만약 $\lvert H \cap K \rvert = 8$라면, $\lvert HK \rvert = 32 < \lvert G \rvert$가 된다.

이때, $\lvert H \cap K \rvert = 8$라는 것은 $H \cap K$의 $H$, $K$에 대한 index가 2가 됨을 말한다.

따라서 $H \cap K \trianglelefteq H, K$이다.

<br>

이번엔 $H \cap K$의 Normalizer $N_G(H \cap K)$를 생각해보자.

$N_G(H \cap K)$는 $H$와 $K$를 모두 포함해야 한다.<br>
따라서 $\lvert N_G(H \cap K) \rvert > 16$이다.

Normalizer는 mother-group에 subgroup이 되므로 <br>
$48 = \lvert G \rvert$의 약수가 되어야 한다.

게다가 $N_G(H \cap K)$는 $H$, $K$를 모두 포함하므로 $HK$도 포함해야 한다. <br>
따라서 $\lvert N_G(H \cap K) \rvert > 32$

따라서

$$
16 < 32 < \lvert N_G(H \cap K) \rvert \mid 48
$$

가 된다.

이에 따라 $\lvert N_G(H \cap K) \rvert = 48$이 되며, <br>
이것은 $N_G(H \cap K) = G$를 의미한다.

따라서 $H \cap K \trianglerighteq G$가 된다.

$G$는 위수가 8인 normal subgroup을 가지므로, simple group이 아니다! $\blacksquare$

</div>

<br>
<hr>

<span class="statement-title">Example 4.</span><br>

Let $\lvert G \rvert = 30$.

Then, $G$ is not simple.


<br>
<hr>

<span class="statement-title">Example 6.</span><br>

Let $\lvert G \rvert = 36$.

Then, $G$ is not simple.


<br>
<hr>

<span class="statement-title">Example 7.</span><br>

Let $\lvert G \rvert = 255$.

Then, $G$ is abelian.
