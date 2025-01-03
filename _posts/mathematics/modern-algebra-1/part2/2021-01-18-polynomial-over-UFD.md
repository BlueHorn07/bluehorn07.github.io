---
title: "Polynomial over UFD"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

primitive function에 대한 곱이 닫혀있음을 말하는 **<u>Gauss's Lemma</u>**는 [이곳]({{"2020/12/30/Gauss-Lemma" | relative_url}})에서 볼 수 있습니다.

<br>
<hr>

<br><span class="statement-title">Theorem 23.20</span><br>

<div class="notice" markdown="1">

If $F$ is field, then every non-constant polynomial $f(x) \in F[x]$ can be factored in $F[x]$ into a product of irreducible polynomials.

The irreducible factorization is unique except for order and for unit.

</div>

<br>

이번 파트에서는 Field의 초입에 다뤘던 Integral Domain $D$를 확장해 만들었던 "[Quotient Field]({{"2020/12/19/Qutient-Field" | relative_url}})"에 대한 성질을 사용한다!

증명의 개요를 서술하면 아래와 같다.

1. $D$ : UFD   /   $F$: Quotient Field of $D$
2. By "Thm 23.20", $F[x]$ is a UFD. <small>(cf. Field → PID → UFD)</small>
3. If $f(x)$ is irreducible in $D[x]$, then $f(x)$ is also irreducible in $F[x]$.
4. Therefore $f(x) \in D[x]$ can be factored into unique factorization.
5. $D[x]$ is UFD. $\blacksquare$

아래에 기술되는 Lemma는 'step-3'에 대해 서술한다.

<br>
<hr>

<br><span class="statement-title">Lemma 45.27</span><br>

<div class="notice" markdown="1">

Let $D$ be a UFD, and $F$ be a Quotient field of $D$.

Let $f(x) \in D[x]$, where $\deg f(x) > 0$.

1. If $f(x)$ is irreducible in $D[x]$, then $f(x)$ is also an irreducible in $F[x]$.

2. If $f(x)$ is primitive in $D[x]$ and irreducible in $F[x]$, then $f(x)$ is irreducible in $D[x]$.

</div>

<br><span class="statement-title">proof.</span><br>
<details>
<div class="proof" markdown="1">

Supp. that a non-constant $f(x) \in D[x]$ factors into polynomials of lower degree in $F[x]$ for $r(x), s(x) \in F[x]$.

$$
f(x) = r(x)s(x)
$$

Then, since $F$ is a **<u>Quotient field</u>** of $D$, each coefficient in $r(x)$ and $s(x)$ is of the form $a/b$ for some $a, b \in D$.

By clearing denominators, we can get

$$
(d)f(x) = r_1(x) s_1(x)
$$

for $d \in D$.

By "Lemma 45.23", $f(x) = (c)g(x)$, $r_1(x) = (c_1)r_2(x)$, and $s_1(x) = (c_2) s_2(x)$ for primitive polynomaials $g(x), r_2(x), s_2(x)$.

Then,

$$
(dc)g(x) = (c_1c_2)r_2(x)s_2(x)
$$

and by "Lemman 45.25<small>(Gauss's Lemma)</small>", $r_2(x)s_2(x)$ is primitive.

By "Lemma 45.23", $c_1 c_2 = dcu$ for some unit $u$ in $D$. <br>
(non-constant인 $(dc)g(x)$를 content와 primitive로 분리하면, $dcu$와 $r_2(x)s_2(x)$의 파트로 분리된다는 말이다.)

그 결과,

$$
(dc)g(x) = (dcu)r_2(x)s_2(x)
$$

따라서

$$
f(x) = (c) g(x) = (cu) r_2(x) s_2(x)
$$

위의 과정을 통해 Quotient Field polynomial $F[x]$ 아래에서 $f(x) \in D[x]$인 $f(x)$가 factorization 된다는 것을 확인했다.

이는 $f(x) \in D[x]$에서 irreducible이라, $F[x]$에서도 irreducible임을 보장한다.

또한, 만약 $f(x) \in D[x]$에서 primitive이고, $F[x]$에서 irreducible이라면, $f(x)$는 $D[x]$에서도 irreducible이다. 왜냐하면, $D[x] \subseteq F[x]$이기 때문!

$\blacksquare$

</div>
</details>

<br>


<br><span class="statement-title">Lemma 45.28</span><br>

<div class="notice" markdown="1">

If $D$ is a UFD, and $F$ is Quotient field of $D$,

then a non-constant $f(x) \in D[x]$ factors into a product of two polynomials of lower degrees $r$ and $s$ in $F[x]$

$\iff$ it has a factorization into polynomials of the same degrees $r$ and $s$ in $D[x]$.

</div>

<br><span class="statement-title">proof.</span><br>
<details>
<div class="proof" markdown="1">

($\implies$)

앞의 "Lemma 45.27"에 의해 만약 $f(x)$가 $F[x]$에서 reducible 하다면, $D[x]$에서 reducible 함을 보였다. (1번 명제의 대우)

<br>

($\impliedby$)

$D[x] \subseteq F[x]$이므로 명제의 역도 자연스럽게 성립한다.

</div>
</details>

<br>
<hr>

<br><span class="statement-title">Theorem 45.29</span><br>

<div class="statement" style="text-align:center" markdown="1">

<big>If $D$ is a UFD, then $D[x]$ is a UFD.</big>

</div>

<br><span class="statement-title">proof.</span><br>

<div class="proof" markdown="1">

Let $f(x) \in D[x]$ for a non-zero and non-unit $f(x)$.

If $f(x)$ has zero-degrees, we are done.

Supp. that $\deg f(x) > 0$.

Let $f(x) = g_1(x) g_2(x) \cdots g_r(x)$ be a factroziation of $f(x)$ in $D[x]$. <br>
($r$ would be smaller than $\deg f(x)$)

이제 각 $g_i(x)$를 content와 primitive polynomial로 분해해보자.<br>
→ $g_i(x) = c_i h_i (x)$

primitive function인 각 $h_i(x)$는 irreducible이다. (why?)

따라서

$$
f(x) = c_1 h_1 (x) \cdots c_r h_r (x)
$$

$h_i (x)$는 이미 irreducible이므로, 각 $c_i \in D$에 대해 irreducible factorization을 진행하면, 우리는 $f(x) \in D[x]$에 대한 irreducible factorization을 얻는다.

($f(x) \in D[x]$에 대한 irreducible factorzation의 유일성 증명에 대해선 추후에 업데이트 하겠다.)

$\blacksquare$

</div>

<br>
<hr>

#### Applications

<br><span class="statement-title">Corollary 45.30</span><br>

<div class="notice" markdown="1">

If $F$ is a field and $x_1, \cdots, x_n$ are indeterminatnes, then $F[x_1, \cdots, x_n]$ is a UFD.

</div>

<br><span class="statement-title">proof.</span><br>

<div class="proof" markdown="1">

By "Theorem 23.20", $F[x_1]$ is a UFD.

By "Theorem 45.29", $(F[x_1])[x_2] = F[x_1, x_2]$ is a UFD.

이 과정을 반복하면, $F[x_1, \cdots, x_n]$가 UFD라는 결과를 얻을 수 있다. $\blacksquare$

</div>


<br><span class="statement-title">Example 45.31</span><br>

"Note every UFD is a PID."

<div class="proof" markdown="1">

Let $F$ be a field, and $x$ and $y$ be indeterminates.

Then $F[x, y]$ is a UFD.

$F[x, y]$에서 상수항이 0인 모든 polynomial을 모은 집합 $N$을 생각해보자.

이 집합 $N$은 Ideal이다.

하지만, $N$은 principal ideal은 아니다!

따라서 $F[x, y]$는 UFD이지만, PID가 아니다. $\blacksquare$

</div>

<br>

<span class="statement-title">Exercise 46.12.</span> $\mathbb{Z}[x]$ is UFD, but not a PID.
