---
title: "Fermat's Theorem on Sums of Two Squares"
toc: true
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br>

앞에서 다룬 Guassian Integer와 Multiplicative Norm에서의 현상을 잘 이용하면, "페르마의 두 제곱수 정리"를 증명할 수 있다!!

<br>
<hr>

<br><span class="statement-title">Theorem 47.10</span> Fermat's $p=a^2+b^2$ Theorem<br>

<div class="statement" markdown="1">

Let $p \ne 2$ be a prime in $\mathbb{Z}$.

Then $p = a^2 + b^2$ for $a, b \in \mathbb{Z}$

$\iff$ $p \equiv 1$ (mod $4$).

</div>

<br><span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

($\implies$)

First, Supp. that $p = a^2 + b^2$.

Now $a$ and $b$ cannot both be even or both be odd since $p$ is an odd number.

If $a = 2r$ and $b = 2s + 1$,

then $a^2 + b^2 = 4 r^2 + 4(s^2+s)+1$.

So $p \equiv 1$ (mod $4$).

</div>

<div class="math-statement" markdown="1">

($\impliedby$)

Supp. that $p \equiv 1$ (mod $4$).

finite field $\mathbb{Z}_p$의 multiplicative group을 생각해보자.

그러면, $({\mathbb{Z}_p}^{\*}, \times)$는 order $p-1$의 cyclic group이다.

이때 $p \equiv 1$ (mod $4$)에 의해 $4$는 $p-1$의 divisor임이 유도된다.

따라서 ${\mathbb{Z}_p}^{\*}$는 multiplicative order가 $4$인 원소 $n$을 포함한다. <small>(cyclic group의 경우 Lagrange thm의 역이 성립)</small>

그리고 $n^2$는 multiplicative order $2$를 가진다. <br>
<small>(간단하게 생각하면, $n$이 뛰는 스텝을 절반만 뛰기 때문에 $4/2=2$의 위수를 갖는 것)<br>
(addidtive group에서 $n$과 $2n$의 위수와 상통하는 부분)</small>

따라서 $n^2$의 위수가 2이므로 ${\mathbb{Z}_p}^{\*}$에서 $n^2 = -1$이 된다.

따라서 $\mathbb{Z}$에선 $n^2 \equiv -1$ (mod $p$)가 된다.

즉, $\mathbb{Z}$에서 $p \mid (n^2 + 1)$가 된다.

<br>

$p$와 $n^2 + 1$의 관계를 이번엔 $\mathbb{Z}[i]$에서 바라보면 아래와 같다.

$$
\begin{aligned}
    p &\mid (n^2 + 1) \\
    p &\mid (n+i)(n-i)
\end{aligned}
$$

Supp. $p$ is irreducible in $\mathbb{Z}[i]$, (귀류법)

then $p$ would have to divide $n+i$ or $n-i$.

If $p$ divides $n+i$, then $n+i = p(a+bi)$ for some $a, b \in \mathbb{Z}$.

허수부의 계수만 비교하면, $1 = pb$라는 식을 얻는데 이것은 불가능하다!

마찬가지로 $n-i$에 대해서도 불가능하다는 결과를 얻는다.

따라서 처음에 가정한 "$p$ is irreducible"은 거짓이다!

<br>

$p$가 $\mathbb{Z}[i]$에서 irreducible이 아니므로, $p = (a+bi)(c+di)$가 된다. <small>($(a+bi)$, $(c+di)$ 모두 unit이 아님!)</small>

여기서 norm을 취하면, $p^2 = (a^2 + b^2)(c^2 + d^2)$ where neither $a^2 + b^2 = 1$ nor $c^2 + d^2 = 1$.

이때, $a^2 + b^2$가 $(a+bi)(a-bi)$로 factorization 되므로, $p = a^2 + b^2$가 되어 성립한다. $\blacksquare$<br>
<small>($p = (a+bi)(c+di) = a^2 + b^2$가 되므로 앞의 조건을 모두 만족한다!)</small>

</div>

