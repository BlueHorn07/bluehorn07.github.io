---
title: "Unique Factorization Domain - 2"
layout: post
tags: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

UFD에 대한 첫번째 포스트는 [이곳]({{"2020/12/27/Unique-Factorization-Domain-1.html" | relative_url}})에서 볼 수 있습니다.

<hr>

이 포스트의 목표는 <big>"Every PID is UFD."</big>를 보이는 것이다.

그 전에 PID에서 만족하던 성질과 UFD에서 만족하던 성질을 나열해보자. 둘다 Irreduciblility와 Primality가 동치다.

<br>

<span class="statement-title">Properties.</span> PID<br>

- For a Field $F$,  Every $F[x]$ is PID.
- <span style="color:red">Irreducible element $\equiv$ Prime element</span>

<br>

<span class="statement-title">Properties.</span> UFD<br>

- <span style="color:red">Irreducible element $\equiv$ Prime element</span>

<br>

증명은 두 가지 step으로 진행된다.

먼저 PID가 UFD의 **첫번째 조건**인 

<div style="text-align:center">
<big>“UFD의 모든 non-zero & non-unit 원소는 finite number of irreducibles로 factorization된다.”</big>
</div><br>

를 만족함을 증명한다. [link](#1st-condition)

그리고 UFD의 **두번째 조건**인

<div style="text-align:center">
<big>“Factorization is same upto reordering and associates”</big>
</div><br>

를 증명한다!! [link](#2nd-condition)

<br>
<hr>

본격적으로 증명을 하기 전에 몇가지 명제를 먼저 증명하자. 명제를 증명하기 위해 필요한 바탕이 꽤 많으니 집중해서 한다.

<br>

<span class="statement-title">Proposition.</span><br>
<div class="statement" markdown="1">

$D$: Integral Domain

$\implies$ $D[x]$: Integral Domain

</div>

<span class="statement-title">proof.</span><br>
<details>
<div class="math-statement" markdown="1">

Integral Domain임을 보이기 위해 zero-divisor가 없음을 보여야 한다.

그래서 $f(x), g(x) \in D[x]$에 대해 $f(x)g(x) = 0$이라면, $f(x) = 0$ 또는 $g(x) = 0$임을 보여야 한다.

Let two non-zero polynomial $f(x), g(x) \in D[x]$.

$$
\begin{aligned}
  f(x) = \sum^{n}_{i=0} a_i x^i \quad (a_n \ne 0)\\
  g(x) = \sum^{m}_{i=0} b_i x^i \quad (b_m \ne 0)
\end{aligned}
$$

then, for $f(x)g(x)$, the cofficient of term $x^{n+m}$ is $a_n b_m \ne 0$.

Therefore $f(x)g(x)$ never be zero.

This means $D[x]$ is an Integral Domain. $\blacksquare$

</div>
</details>

<br>
<hr>

<span class="statement-title">Theorem 23.20</span><br>

<div class="statement" markdown="1">

If $F$ is a Field,

then every non-constant polynomial $f(x) \in F[x]$ can be factored into a **<u>product of irreducible poylnomials</u>**.

This factorization is unique up to orerding and associates.

</div>

<span class="statement-title">proof.</span><br>
<details>
<div class="math-statement" markdown="1">

Let $f(x) \in F[x]$ be a non-constant polyno-.

If $f(x)$ is 'not' irreducible, then $f(x) = g(x)h(x)$ for some lower degress polynomials.

If $g(x)$ and $h(x)$ are irreducible, we stop here.

If not, at least one of them factors into lower degress polno-s.

Continuing this process, then we get 

$$
f(x) = p_1 (x) p_2 (x) \cdots p_r (x)
$$

<br>

(Uniqueness) 

Supp. $f(x) = p_1 (x) \cdots p_r (x) = q_1 (x) \cdots q_s (x)$.

$p_1(x)$는 $f(x)$를 divide 하므로 식의 우변을 divide한다. 따라서 $p_1 (x)$는 적어도 하나의 $q_i (x)$를 divide한다. 


<div class="statement" markdown="1">

사실 위의 사실은 irreducibility의 정의를 확장한 것이다. 

irreducible polyno- $f(x)$에 대해 $f(x) \mid r(x) s(x)$라면, $f(x) \mid r(x)$ 또는 $f(x) \mid s(x)$이다.

이것을 두 개 polyno-의 product가 아니라 $s$개 polyno-의 product로 확장시켜 적용한 것이 위의 명제다.

</div>

$p_1 (x) \mid q_i (x)$가 성립한다고 가정하자. 이때, $q_i (x)$가 irreducible polynomial이기 때문에 $p_1 (x) = u_1 q_i (x)$이다. (for some unit $0 \ne u_1 \in F[x]$)

이때, Polynomial Field에서는 곱에 대한 교환법칙이 성립하므로 논의의 편의를 위해 $q_i (x)$를 $q_1 (x)$로 두자. $p_1 (x) = u_1 q_1 (x)$

이제 factorization $p_1 (x) \cdots p_r (x) = q_1 (x) \cdots q_s (x)$에 위의 관계식을 적용하고 소거하자. 그러면

$$
\begin{aligned}
  p_1 (x) \cdots p_r (x) &= q_1 (x) \cdots q_s (x)$ \\
  (u_1 q_1 (x)) \cdots p_r (x) &= q_1 (x) \cdots q_s (x) \\
  u_1 \cdots p_r(x) &= 1 \cdots q_s (x)
\end{aligned}
$$

(논의의 편의상 $r < s$라고 가정하자. 반대로 잡아도 상관은 없다.)

이 과정을 반복하면,

$u_1 \cdots u_r = 1 \cdots q_{r+1} (x) \cdots q_s (x)$가 된다.

이때에 위의 식이 성립하기 위해선 우변의 polyno- 부분이 없어야 하므로 $r = s$가 되어야 한다.

따라서 irreducible polyno- $f(x)$의 factorization은 ordering과 associates에 대해 unique 하게 존재한다. $\blacksquare$

</div>
</details>

<br>
<hr>

### 1st Condition

#### Ascending Chain Condition (ACC)

이번에는 **집합론**에서도 등장하는 명제를 다룬다. 생각보다 중요한 명제다.

<span class="statement-title">Lemma 45.9</span><br>
<div class="statement" markdown="1">

$R$: commutative ring + unity.

Let $N_1 \subseteq N_2 \subseteq \cdots $ be an **<u>ascending chain of ideals</u>** $N_i$ in $R$.

Then, $N = \cup_i N_i$ is an **<u>Ideal</u>** in $R$.

</div>

<span class="statement-title">proof.</span><br>
<details>
<div class="math-statement" markdown="1">

Let $a, b \in N$. 

Then, there are ideals $N_i$ and $N_j$ in ideal chain, with $a \in N_i$ and $b \in N_j$.

Now either $N_i \subseteq N_j$ or $N_i \supseteq N_j$.

Let's assume $N_i \subseteq N_j$, so both $a$ and $b$ are in $N_j$.

This implies $a \pm b$ and $ab$ are also in $N_j$, and also be in $N$.

Take $a = 0$, then $0 \in N$, and $b \in N$ implies $-b \in N$.

Thus $N$ be a sub-ring of $R$.

<br>

"Check $N$ is an ideal."

For $a \in N$, $r \in R$, we must have $a \in N_i$ for some $N_i$.

Since $N_i$ is an ideal, $ar = ra \in N_i$, and also in $\cup_i N_i = N$.

Therefore, $N$ is an Ideal. $\blacksquare$

</div>
</details>

<br>

Lemma 45.9로부터 PID에 대한 ACC<small>(Ascedning Chain Condition)</small>을 유도할 수 있다!!

<span class="statement-title">Lemma 45.10</span><br>
<div class="statement" markdown="1">

Let $D$ be a PID.

If $N_1 \subseteq N_2 \subseteq \cdots$ is an **ascending chain of ideals** $N_i$,

then there exist a positive integer $r$ s.t. $N_r = N_s$ for all $s \ge r$.

<br>

Equivalently, every **strictly ascending chain of ideals** in a PID is of finite length.

</div>

그래서 우리는 아래와 같이 기술한다.

<div style="text-align:center">

<big>"The <b>ACC</b><small>(Ascending Chain Condition)</small> holds for ideals in a <b>PID</b>."</big>

</div>

<span class="statement-title">proof.</span><br>
<details>
<div class="math-statement" markdown="1">

By Lemma 45.9, we know that $N = \cup_{i} N_i$ is an ideal of $D$.

Since, now, $D$ is a PID, $N = \left< c \right>$ for some $c \in D$.

Since $N = \cup_{i} N_i$, we must have $c \in N_r$ for some $r \in \mathbb{Z}^{+}$.

Therefore, for $s \ge r$, we have

$$
\left< c \right> \subseteq N_r \subseteq N_s \subseteq N = \left< c \right>
$$

Thus $N_r = N_s$ for $s \ge r$.

</div>
</details>


<br>

이 정리로부터 아래의 성질이 유도된다.

- $b$ divides $a$ $\iff$ $\left< a \right> \subseteq \left< b \right>$.
- $a$ and $b$ are associates $\iff$ $\left< a \right> = \left< b \right>$.

이 성질을 이용하면, PID가 UFD가 되기 위한 첫번째 조건인

"UFD의 모든 non-zero & non-unit 원소는 finite number of irreducibles로 factorization된다."

를 증명할 수 있다!!

<br>
<hr>

<span class="statement-title">Theorem 45.11</span> proof of 1st condition<br>

<div class="statement" markdown="1">

Let $D$ be a PID.

Every non-zero & non-unit elt in $D$ is a product of irreducibles.

</div>

<span class="statement-title">proof.</span><br>

<details>
<div class="math-statement" markdown="1">

Let $a$ be a non-zero & non-unit elt in $D$.

(Step 1) show $a$ has at least one irreducible factor.

If $a$ is already irreducible, we are done!

If $a$ is not an irreducible, 

then $a = a_1 b_1$, where neither $a_1$ nor $b_1$ is a unit. <br>
<small>(만약 $a_1$와 $b_1$이 둘다 unit이라면, $a$가 unit이 되기 때문이다.)</small>

앞에서 보인 성질에 의해 

$$
\left< a \right> \subseteq \left< a_1 \right>
$$

이다.

이때, $\left< a \right> = \left< a_1 \right>$가 되는 경우는 $a$와 $a_1$이 associate 하는 경우다. 하지만 이럴 경우 $b_1$이 unit이 되므로 모순이다.

따라서 등호를 뺀 $\left< a \right> \subset \left< a_1 \right>$가 성립한다.

<br>

이제 $a_1$에서 시작해 위의 과정을 계속해서 적용하면, **strictly ascending chain of ideals**를 얻는다.

$$
\left< a \right> \subset \left< a_1 \right> \subset \left< a_2 \right> \subset \cdots
$$

앞에서 보인 ACC (Lemma 45.10)에 의해 이 chain은 어떤 $\left< a_r \right>$에서 끝나며 $a_r$은 irreducible해야 한다.<br>
<small>(PID에서 모든 principal ideal은 prime elt에 의해 유도된다. 이때 PID에선 prime과 irreducible이 동치이기 때문에 $a_r$은 prime이면서 irreducible이다!)</small>

따라서 $a$는 irreducible factor $a_r$을 갖는다.

<br>

우리가 유도한 바를 정리하자.

for non-zero & non-unit elt $a$, $a = p_1 c_1$ for an irreducible $p_1$ and $c_1$ not a unit.

In this way, we want do same thing on $c_1$; $c_1 = p_2 c_2$.

Then we get this ascedning chain of ideals.

$$
\left< a \right> \subset \left< c_1 \right> \subset \left< c_2 \right> \subset \cdots
$$

By Lemma 45.10, this chain must terminate at for some $p_r$.

Therefore $a = p_1 p_2 \cdots p_r$.

This means, in PID, non-zero & non-unit can be factorized into a product of irreducibles. $\blacksquare$

</div>
</details>

<br>
<hr>

### 2nd Condition

앞에서도 언급했던 정리인데 다시 한번 살펴보자! 우리의 목표는 아래의 정리를 일반화하는 것이다.

<span class="statement-title">Theorem 23.20</span><br>

<div class="statement" markdown="1">

If $F$ is a **<u>Field</u>**, then every non-constant polynomial $f(x) \in F[x]$ can be factored in $F[x]$ into a **<u>product of irreducible polynomials</u>**.

The product of irreducible poylnomials is unique except for order and unit.

</div>

<br>
<hr>

#### Maximal ~ Irreducible

<span class="statement-title">Theorem 27.25</span><br>

<div class="statement" markdown="1">

An ideal $\left< p(x) \right> \ne \\{0\\}$ of $F[x]$ is **<u>maximal</u>**

$\iff$ $p(x)$ is **<u>irreducible</u>** over $F$.

</div>

<br>

<span class="statement-title">Lemma 45.12</span> (Generalization of Thm 27.25)<br>

<div class="statement" markdown="1">

An ideal $\left< p \right>$ in a PID is **<u>maximal</u>** 

$\iff$ $p$ is an **<u>irreducible</u>**.

</div>

<span class="statement-title">proof.</span><br>

<details>
<summary>($\implies$)</summary>
<div class="math-statement" markdown="1">

Supp. $\left< p \right>$ be a maximal ideal of PID $D$.

Supp that $p=ab$ in $D$.

Then, $\left< p \right> \subseteq \left< a \right>$.

(Case 1) Supp. that $\left< p \right> = \left< a \right>$.

Then $p$ and $a$ would be associates, so $b$ must be a unit.<br>
($b$가 $p$-$a$ 사이 unit의 역할을 하는 것)

(Case 2) If $\left< p \right> \ne \left< a \right>$

then we must have $\left< a \right> = \left< 1 \right> = D$,

since $\left< p \right>$ is **maximal**.

Then $a$ and $1$ are associates, so $a$ is a unit.

Thus, if $p = ab$, either $a$ or $b$ must be a unit.

This means $p$ is an irreducible of $D$. $\blacksquare$

</div>
</details>

<details>
<summary>($\impliedby$)</summary>
<div class="math-statement" markdown="1">

Supp. $p$ is an irreducible in $D$.

If $\left< p \right> \subseteq \left< a \right>$, we must have $p = ab$.

(Case 1) If $a$ is a unit, (왜냐하면 $p$가 irreducible)

then $\left< a \right> = \left< 1 \right> = D$.

(Case 2) If $a$ is not a unit,

then $b$ must be a unit. (왜냐하면 $p$가 irreducible)

So there exist $u \in D$ s.t. $bu = 1$.

Then $pu = a(bu) = a$, so $\left< a \right> \subseteq \left< p \right>$.

우리가 처음에 $\left< p \right> \subseteq \left< a \right>$를 가정했으므로 결국 $\left< p \right> = \left< a \right>$가 된다.

<br>

정리하면, $\left< p \right> \subseteq \left< a \right>$는

(Case 1) $\left< a \right> = D$ 또는 

(Case 2) $\left< p \right> = \left< a \right>$ and $\left< p \right> \ne D$ ($a$ is not unit) 또는

$p$ would be a unit ($a$, $b$ 모두 unit일 때)

<br>

이것은 결국 $\left< p \right>$보다 크다고 가정한 ideal $\left< a \right>$이 $D$ 자체가 되거나 $\left< p \right>$ 자신이 된다는 말이기 때문에 $\left< p \right>$가 **<u>Maximal Ideal</u>**임을 의미한다. $\blacksquare$

</div>
</details>

<br>
<hr>

#### Irreducible ~ Prime

<span class="statement-title">Theorem 27.27</span><br>

<div class="statement" markdown="1">

Let $p(x)$ be an **irreducible polynomial** in $F[x]$. 

If $p(x)$ divides $r(x)s(x)$ for $r(x), s(x) \in F[x]$,

then either $p(x) \mid r(x)$ or $p(x) \mid s(x)$.

</div>

<br>

<span class="statement-title">Lemma 45.13</span> (Generalization of Thm 27.27)<br>

<div class="statement" markdown="1">

In a PID, if an **irreducible** $p$ divides $ab$,

then either $p \mid a$ or $p \mid b$.

</div>

<span class="statement-title">proof.</span><br>

<details>
<div class="math-statement" markdown="1">

Let $D$ be a PID, and Supp. that for an **irreducible** $p \in D$ we have $p \mid ab$.

Then $(ab) \in \left< p \right>$.

또한 앞에서 "Lemma 45.12"에서 **<u>PID의 irreducible elt는 maxial ideal을 생성함</u>**을 확인했다.

Since every **maximal ideal** in PID is a **prime ideal** by "Corollary 27.16",

$(ab) \in \left< p \right>$ implies that either $a \in \left< p \right>$ or $b \in \left< p \right>$.

그리고 이것은 either $p \mid a$ or $p \mid b$를 유도한다. $\blacksquare$

</div>
</details>

<br><span class="statement-title">Corollary 45.14</span> (Generalization of Lemma 45.13)<br>

<div class="statement" markdown="1">

If $p$ is an irreducible in a PID and $p \mid a_1 a_2 \cdots a_n$ for $a_i \in D$.

Then $p \mid a_i$ for at least one $i$.

</div>

사실 위에서 언급한 성질은 "**Primality**"에 대한 것이다.

즉, PID의 irreducible이 prime을 유도한다는 명제가 "Lemma 45.13"이다.

<br>

<br><span class="statement-title">Example 45.16</span><br>

<div class="example" markdown="1">

Let $F$ be a Field, and $D$ be the sub-domain $F[x^3, xy, y^3]$ of $F[x, y]$.

Then $x^3$, $xy$, $y^3$ are **<u>irreducible</u>** in $D$, but

$$
(x^3)(y^3) = (xy)(xy)(xy)
$$

Since $xy$ divides $x^3y^3$ but not $x^3$ or $y^3$, 

$xy$ is not a prime.

In similar way, neither $x^3$ nor $y^3$ is a prime.

</div>

위 예제는 Integral Domain에서는 irreducible이 prime을 의미하지 않을 수도 있다는 것을 보여준다!

<br>
<hr>

이제 우리가 목표로 했던 정리, "Thm 23.20"을 일반화한 명제를 살펴보자!!

<br><span class="statement-title">Theorem 45.17</span> (Generalization of Thm 23.20)<br>

<div class="statement" style="text-align:center" markdown="1">

<big>Every PID is a UFD.</big>

</div>

<br><span class="statement-title">proof.</span><br>

<details>
<div class="math-statement" markdown="1">

Thm 45.11 shows that if $D$ is a PID, then each non-zero & non-unit $a \in D$ has a factorization into irreducibles. (1st Condition)

$$
a = p_1 p_2 \cdots p_r
$$

이제 남은 것은 위의 irreducible factorization에 대한 '유일성'을 보이는 것이다.

Let $a = q_1 q_2 \cdots q_s$ be another irreucible factorization.

위와 같이 또다른 irreducible factorization을 가정한다면,

$$
p_1 \mid (q_1 q_2 \cdots q_s)
$$

가 되며, 이것은 $p_1 \mid q_j$ for some $j$를 의미한다. (Cor 45.14)

$q_j$의 순서를 적당히 바꿈으로써 우리는 $j=1$라고 가정할 수 있고, 따라서 $p_1 \mid q_1$가 된다.

그러면, $q_1 = p_1 u_1$ for some unit $u_1$.

따라서 irreducible factorization은 아래와 같이 다시 쓸 수 있다.

$$
p_1 p_2 \cdots p_r = p_1 u_1 q_2 \cdots q_s
$$

Integral Domain $D$ 아래에서의 소거법에 의해

$$
p_2 \cdots p_r = u_1 q_2 \cdots q_s
$$

가 된다.

이 과정을 반복하면, 아래의 결과를 얻는다.

$$
1 = u_1 u_2 \cdots u_r \cdot q_{r+1} \cdots q_s
$$

가정에 의해 $q_j$는 모두 irreducible이므로 위의 등식이 만족하기 위해선 $r=s$가 되어야 한다.

<br>

종합하면 PID 아래에서 모든 non-zero & non-unit elt는 모두 unique irreducible factorization을 갖는다.

그리고 Lemma 45.13에 의해 PID에선 irreducible이 prime이기 때문에 PID의 원소는 unique prime factorization을 가진다.

따라서 PID는 UFD이다. $\blacksquare$

</div>
</details>

<br>
<hr>

PID가 UFD임을 말하는 Theorem 45.17를 통해 우리는 정수 $\mathbb{Z}$에 대한 가장 근본적인 명제인 **"Fundamental Theorem of Arithmetic"**을 유도할 수 있다!! [link]({{"2021/01/17/fundamental-thm-of-arithmetic.html" | relative_url}})

