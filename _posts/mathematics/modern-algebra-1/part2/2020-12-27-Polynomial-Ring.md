---
title: "Polynomial Ring"
layout: post
tags: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>
<br>

<span class="statement-title">Definition.</span> Polynomial from Ring $R$<br>

<div class="statement" markdown="1">

Let $R$ be a ring, 

a polynomial $f(x)$ with coefficients in $R$ is an infinite formal sum

$$
\sum^{\infty}_{i=0} {a_i x^i} = a_0 + a_1 x + a_2 x^2 + \cdots 
$$

where $a_i \in R$, and for finite number of $a_i \ne 0$.

</div>

<span class="statement-title">Properties.</span><br>

1\. **polynomial addition**

$$
f(x) + g(x) = \sum^{\infty}_{i=0} {a_i x^i} + \sum^{\infty}_{i=0} {b_i x^i} = \sum^{\infty}_{i=0} {(a_i + b_i) x^i}
$$

2\. **polynomial multiplication **

$$
\begin{aligned}
    f(x) \cdot g(x) &= \left( \sum^{\infty}_{i=0} {a_i x^i} \right) \cdot \left( \sum^{\infty}_{i=0} {b_i x^i} \right) \\
    &= \sum^{\infty}_{k=0} \left( \sum^{k}_{i+j = k} {a_i b_j } \right) x^k \\
    &= \sum^{\infty}_{i=0} \left( \sum^{i}_{j = 0} {a_{i-j} b_j } \right) x^k
\end{aligned}
$$

<br>
<hr>

<span class="statement-title">Theorem.</span><br>

<div class="statement" markdown="1">

If $R$ is a ring, also $R[x]$ is a ring.

If $R$ is a commutative ring, also $R[x]$ is a commutative ring.

If $R$ has the unity, also $R[x]$ has the unity.

</div>

<br>
<hr>

#### Evaluation Homomorphism

<span class="statement-title">Theorem 22.4</span> The Evaluation Homomorphism for Field Theory; 체론을 위한 대입 준동형사상<br>

<div class="statement" markdown="1">

Let $F$ be a subfield of a field $E$, and $\alpha \in E$.

The map $\phi_\alpha: F[x] \longrightarrow E$ is defined by

$$
\phi_\alpha (a_0 + a_1 x + a_x x^2 + \cdots) = a_0 + a_1 \alpha + a_2 \alpha^2 + \cdots
$$

then $\phi_\alpha$ is a **homomorphism**.

또한 $\phi_\alpha(x) = \alpha$이며, 

$\phi_\alpha$ maps $F$ isomorphically by the identity map; <br>
for $a \in F$, $\phi_\alpha(a) = a$

즉, $\phi_\alpha$ is the "evaluation at $\alpha$".

</div>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

Show $\phi_\alpha$ is a ring homomorphism.

(1) $\phi_\alpha(f+g) = \phi_\alpha(f) + \phi_\alpha(g)$

(2) $\phi_\alpha(f \cdot g) = \phi_\alpha(f) \cdot \phi_\alpha(g)$

(1), (2)를 잘 체크해보면 OK

</div>

<br>
<hr>

#### Division Algorithm for Polynomial Ring

<span class="statement-title">Theorem.</span> Division Algorithm for Polynomial Ring<br>

<div class="statement" markdown="1">

Let $F$ be a field.

$f(x) = a_n x^n + \cdots + a_0$

$g(x) = b_m x^m + \cdots + b_0$

then, there exist a unique $q(x), r(x) \in F[x]$

s.t. $f(x) = q(x) \cdot g(x) + r(x)$ with $\deg(r(x)) < m$.

</div>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

(Case 1: $n < m$)

If $n < m$, then $f(x) = 0 \cdot g(x) + f(x)$ with $\deg(f(x)) = n < m$.

<br>

(Case 2: $n \ge m$)

Let's think of a set $S = \\{ f(x) - g(x)s(x) \mid s(x) \in F[x] \\}$.

<br>

만약 $0 \in S$라면, $f(x) - g(x)s(x) = 0$이 되는 $s(x) \in F[x]$가 존재한다.

만약 $0 \notin S$라면, $S$의 원소 중 가장 차수가 작은 다항식 $r(x)$를 선택하자.

그러면, $r(x) \in S$에 대응하는 적당한 $q(x) \in F[x]$가 존재하여 

$$
f(x) = q(x) \cdot g(x) + r(x)
$$

를 만족한다.

이때, 우리는 $r(x)$의 차수가 $m$보다 작다는 것을 보여야 한다.

$r(x)$를 아래와 같이 설정하자.

$$
r(x) = c_t x^t + c_{t-1} x^{t-1} + \cdots + c_0
$$

그리고 (proof by contradiction)을 위해 $t \ge m$으로 두자.

그러면, 아래의 다항식을 생각해보자.

$$
\begin{equation}
    \left(f(x) - q(x)g(x)\right) - (c_t / b_m) x^{t-m} g(x) = r(x) - (c_t / b_m) x^{t-m} g(x)    
\end{equation}
$$

이때 $(c_t / b_m) x^{t-m} g(x)$는 최고 차항이 $(c_t / b_m) x^{t-m} \cdot b_m x^m = c_t x^t$가 된다.

즉, Eq.(1)은 $r(x) - (c_t x^t + \cdots)$가 되므로 

Eq.(1)은 차수가 $t$보다는 작으면서 $S$에 원소가 된다.

$$
\begin{aligned}
    \left(f(x) - q(x)g(x)\right) - (c_t / b_m) x^{t-m} g(x) &= r(x) - (c_t / b_m) x^{t-m} g(x) \\
    &= f(x) - g(x) \cdot \left( q(x) + (c_t / b_m) x^{t-m} \right) \in S
\end{aligned}
$$

이것은 $r(x)$이 $S$의 원소 중 가장 작은 차수를 가진다는 사실에 모순이다!

따라서 $r(x)$의 차수는 $g(x)$의 차수 $m$보다 작아야 한다. $\blacksquare$

</div>

<div class="math-statement" markdown="1">

(Uniqueness)

유일성을 보이기 위해 $f(x)$를 두 가지 방식으로 표현해보자.

- $f(x) = q_1(x) \cdot g(x) + r_1(x)$
- $f(x) = q_2(x) \cdot g(x) + r_2(x)$

두 식을 빼면,

$$
\begin{aligned}
    f(x) - f(x) &= g(x) \cdot \left( q_1(x) - q_2(x) \right) + \left( r_1(x) - r_2(x) \right) \\
    g(x) \cdot \left( q_1(x) - q_2(x) \right) &= r_1(x) - r_2(x)
\end{aligned}
$$

이때, $q_1(x) - q_2(x) \ne 0$라면, $g(x) \cdot \left( q_1(x) - q_2(x) \right)$의 차수는 $g(x)$의 차수 $m$보다 더 크게 된다.

그런데, $r_1(x) - r_2(x)$는

- $r_1(x) - r_2(x) = 0$이거나
- $r_1(x) - r_2(x)$의 차수는 원래 $g(x)$의 차수 $m$보다 작았으므로 
 
우변의 차수는 $m$보다 작게 된다.

따라서 $q_1(x) - q_2(x) \ne 0$이 될 수 없으므로 $q_1(x) = q_2(x)$가 된다.

좌변이 0이 되었으므로 우변 역시 0이 되어 $r_1(x) = r_2(x)$가 된다.

따라서 $F[x]$에 대한 Division Algorithm의 결과는 유일하다. $\blacksquare$
</div>

<br>
<hr>

#### zero of polynomial

<span class="statement-title">Definition.</span>zero of polynomial; polynomial의 근<br>

<div class="statement" markdown="1">

Let field $F$ be a sub-field of field $E$, and $\alpha \in E$.

For a ring $F[x]$, $f(x) \in F[x]$, and $\phi_\alpha: F[x] \longrightarrow E$ is an **evaluation homormophism** from [Theorem 22.4]({{"2020/12/27/Polynomial-Ring.html#evaluation-homomorphism" | relative_url}})

if $f(\alpha) = 0$, then $\alpha$ is a zero of $f(x)$.

</div>

<br>
<hr>

### Factor Theorem

<span class="statement-title">Corollary.</span> Factor Theorem; 인수 정리<br>

<div class="statement" markdown="1">

For $a \in F$, 

$a$ is zero of $f(x) \in F[x]$ $\iff$ $(x - a) \mid f(x)$ in $F[x]$.

<br>

다르게 표현하면,

$a$ is zero of $f(x) \in F[x]$ $\iff$ $f(x) = (x-a)g(x)$ for some $g(x) \in F[x]$.

그리고


For $f(x) \in F[x]$, 

$f(a) = 0$ $\iff$ $(x - a) \mid f(x)$ in $F[x]$.

</div>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

($\impliedby$) Supp. $(x-a) \mid f(x)$ in $F[x]$.

$\implies$ $f(x) = (x-a)g(x)$ for some $g(x) \in F[x]$

$\implies$ evaluation $x$ at $a$, then $f(a) = (a-a) \cdot g(a) = 0 \cdot (a) = 0$

즉, $a$ is zero of $f(x)$. $\blacksquare$

</div>

<div class="math-statement" markdown="1">

($\implies$) Supp. $f(a) = 0$.

$f(x)$를 $(x-a)$로 나누자. 그러면, 앞에서 보인 [Ring Division Algorithm]({{"2020/12/27/Polynomial-Ring.html#division-algorithm-for-polynomial-ring" | relative_url}})에 의해

$\implies$ $f(x) = q(x)(x-a) + r(x)$ where $q(x), r(x) \in F[x]$

이때, $(x-a)$의 차수가 1이므로 $\deg (r(x)) < 1$이 된다.

따라서 $r(x) = r$로 상수가 된다.

$f(x) = q(x)(x-a) + r$

<br>

이제 evaluation $x$ at $a$를 적용하면, 처음의 가정 $f(a) = 0$에 의해

$f(a) = q(a) (a-a) + r = 0 + r = 0$이 된다.

즉, $f(x) = q(x) (x- a)$가 되므로

$(x-a) \mid f(x)$가 된다. $\blacksquare$

</div>

<br>
<hr>

##### Corollary 23.5

<span class="statement-title">Corollary 23.5</span><br>

<div class="statement" markdown="1">

A non-zero poly-. $f(x) \in F[x]$ of $\deg = n$ has at most $n$ zeros in field $F$.

</div>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

(Induction on $\deg f(x)$)

Let $a_1$, ..., $a_k$ be zeros of $f(x)$

$f(a_1) = 0$ $\implies$ $f(x) = (x-a_1) q_1(x)$

그리고 $\deg g_1 = \deg f - 1 = n-1$이 된다.

<br>

이 과정을 반복하면, 더이상 해를 갖지 않는 지점 $q_r(x)$에 도달할 것이다.

$$
f(x) = (x-a_1) \cdots (x-a_r) q_r(x)
$$

$f(x)$의 차수가 $n$이므로 우변에는 많아야 $n$개의 $(x-a_i)$가 등장할 것이므로 $r \le n$을 얻는다.

따라서 (# of zeros of $f(x)$)는 많아야 $n$개다. $\blacksquare$

</div>

<br>
<hr>

##### Corollary 23.6

<span class="statement-title">Corollary 23.6</span><br>

<div class="statement" markdown="1">

$(F^{*}, \cdot\;)$는 Field $F$에 대한 곱셈군이다.

이때, $(F^{*}, \cdot\;)$의 모든 finite subgroup은 모든 cyclic group이다.

만약 Field $F$가 finite field라면, $(F^{*}, \cdot\;)$는 Cyclic이다!

</div>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

Let $G \le (F^{*}, \cdot\;)$.

If $G$ is cylic, then $G$ is finite abelian. 

따라서 F.T. of f.g. abelian에 의해 $G$를 적당한 finite cyclic group의 direct product로 표현할 수 있다.

$$
G \cong \mathbb{Z}_{d_1} \times \mathbb{Z}_{d_2} \times \cdots \mathbb{Z}_{d_r}
$$

(이때, $d_i$는 소수들의 곱이다.)

<br>

cylic group $\mathbb{Z}_{n_i}$ 각각을 곱셈군으로서 생각하자.

$m = \gcd(d_i)_{i \in \Lambda}$로 정의하면,

$m \le d_1 d_2 \cdots d_r$이 된다.

<br>

$a_i \in \mathbb{Z}_{d_i}$에 대해,

$(a_{n_i})^{d_i} = 1$가 성립한다.

$d_i \mid m$이므로 $(a_{n_i})^m = 1$가 성립한다.

이것은 $G$를 $$\mathbb{Z}_{d_1} \times \mathbb{Z}_{d_2} \times \cdots \mathbb{Z}_{d_r}$$ 아래에서 해석한 것이다. 이것을 다시 $G$에서 해석하면 아래와 같다.

For $\alpha \in G$, $\alpha^m = 1$

따라서 모든 $G$의 원소가 $x^{m} - 1$의 해가 된다.

<br>

우리는 $x^m - 1$에 대해 $\lvert G \rvert$ 만큼의 해가 있음을 알고 있다. 그리고 $\lvert G \rvert = d_1 d_2 \cdots d_r$이다. (왜냐하면, $$G \cong \mathbb{Z}_{d_1} \times \mathbb{Z}_{d_2} \times \cdots \mathbb{Z}_{d_r}$$이기 때문이다.)

이때, $x^m - 1$의 차수 $m$은 앞에서 보인 [Corollary 23.5]({{"2020/12/27/Polynomial-Ring.html#corollary-235" | relative_url}})에 의해 $m \ge \lvert G \rvert = d_1 d_2 \cdots d_r$이다.

<br>

- $m \le d_1 d_2 \cdots d_r$ (by $\gcd$)
- $m \ge d_1 d_2 \cdots d_r$ (by Lemma 23.5)

가 성립하므로 $m = d_1 d_2 \cdots d_r$이다.

이것이 성립한다는 것은 $d_i$들이 모두 서로소 라는 말이다.

따라서 

$$
\mathbb{Z}_{d_1} \times \mathbb{Z}_{d_2} \times \cdots \mathbb{Z}_{d_r} \cong \mathbb{Z}_m \cong G
$$

가 되므로 $G$는 cyclic group이다. $\blacksquare$

</div>

<br>
<hr>

<span class="statement-title">Examples.</span><br>

For $(\mathbb{R}^{*}, \cdot \;)$,

$$
\begin{aligned}
    \{-1, +1 \} \cong \mathbb{Z}_2 \le (R^{*}, \cdot\;)
\end{aligned}
$$

<br>

For $(\mathbb{C}^{*}, \cdot \;)$,

$$
\begin{aligned}
    \{\pm 1, \pm i \} \cong \mathbb{Z}_4 \le (R^{*}, \cdot\;)
\end{aligned}
$$

이것을 

"$\mathbb{Z}_4$ can be embedded into $(\mathbb{C}^{*}, \cdot \;)$."

$$
\mathbb{Z}_4 \hookrightarrow (\mathbb{C}^{*}, \cdot \;)
$$

라고 한다.

<br>

**(Generalization)**

For $(\mathbb{C}^{*}, \cdot \;)$,

$$
\begin{aligned}
    \mathbb{Z}_n \hookrightarrow (\mathbb{C}^{*}, \cdot \;)
\end{aligned}
$$

임을 보여라.

<hr>

#### Reference
- [gardnerr's note](https://faculty.etsu.edu/gardnerr/4127/notes/IV-22.pdf)