---
title: "Fermat's Little Theorem"
layout: post
use_math: true
tags: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<div class="img-wrapper">
  <img src="{{ "/images/modern-algebra-1/group_meme.jpg" | relative_url }}">
</div>

<hr>

우리는 $\mathbb{Z}_n$이 Ring임을 이미 알고 있다.

그런데, 이 $\mathbb{Z}_n$은 사실 아래의 Factor Ring으로부터 유도되는 Ring이다!

$$
\mathbb{Z} / n \mathbb{Z} \cong \mathbb{Z}_n
$$

<br>

그리고 우리는 $\mathbb{Z}_p$의 경우 Ring보다 상위 단계인 **<u>Field</u>**가 됨을 확인하였다!!

$\mathbb{Z}_p$에서 곱셈에 대한 군인 $\mathbb{Z}^{\*}_p$를 생각해보자.

$$
\mathbb{Z}^{*}_p = \{1, 2, ..., p-1 \}
$$

즉, $\lvert \mathbb{Z}^{*}_p \rvert = p-1$이다.

이때, $a \in \mathbb{Z}^{\*}_p$에 대해 $a$의 위수는 $\mathbb{Z}^{\*}_p$의 위수의 약수이므로 $\lvert a \rvert \mid (p-1)$가 된다.

따라서 $a^{p-1} = 1$이 된다.

이것을 $\mathbb{Z}$에서 다시 쓰면 아래와 같다.

$$
a^{p-1} \equiv 1 \quad (\textrm{mod} \; p)
$$

이때, $a$를 $\mathbb{Z}^{\*}_p$에서 뽑았으므로 $a \not\equiv 0$ (mod $p$)이다. 즉, $p \not\mid a$이다.

위의 사실을 잘 정리하여 기술한 것이 바로 Fermat' Little Theorem이다.

<br>
<hr>

### Fermat's Little Theorem

<span class="statement-title">Theorem.</span> Fermat's Little Theorem<br>

<div class="statement" markdown="1">

Let $p$ be a prime, 

If $a \in \mathbb{Z}$, and $p \not\mid a$,

then $p \mid (a^{p-1} + 1)$ $\iff$ $a^{p-1} \equiv 1$ (mod $p$).

<br>

이때, 만약 $p \mid a$라면, <br>
$a^{p-1} \equiv 0$ (mod $p$)가 된다!

</div>

<span class="statement-title">Corollary.</span><br>

<div class="statement" markdown="1">

If $a \in \mathbb{Z}$, then $a^p \equiv a$ (mod $p$).

</div>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

(Case 1) $p \mid a$

(Case 2) $p \not\mid a$

</div>

<br>

#### Examples

<span class="statement-title">Example 1.</span><br>

$8^{103} \equiv \; ?$ (mod 13)

<span class="statement-title">Sol.</span><br>

<div class="math-statement" markdown="1">

By Fermat-, $8^{12} \equiv 1$ (mod 13)

$\implies$ $(8^{12})^{8} \cdot 8^7 \equiv 1 \cdot 8^7$ (mod 13)

$\implies$ $8^7 \equiv (-5)^7 \equiv (25)^3 \cdot (-5)$

이때, $25 \equiv -1$ (mod 13) 이므로

$\implies$ $(-1)^3 (-5) \equiv 5$ (mod 13)

</div>

<br>

<span class="statement-title">Example 2.</span><br>

$2^{11213} \equiv \; ?$ (mod 11)

<span class="statement-title">Sol.</span><br>

<div class="math-statement" markdown="1">

By Fermat-, $2^{10} \equiv 1$ (mod 11)

$(2^{10})^{1121} \cdot 2^3 \equiv 1 \cdot 8$ (mod 11)

</div>

<br>
<hr>

<span class="statement-title">Example 3.</span><br>

Show that, for $\forall \; n \in \mathbb{N}$, $15 \mid (n^{33} - n)$.

<span class="statement-title">Sol.</span><br>

<div class="math-statement" markdown="1">

$15 \mid (n^{33} - n)$. 즉, $n^{33} - n = n(n^{32} - 1) \equiv 0$ (mod 15)임을 보이면 된다.

<br>

이때, $15 = 3 \times 5$이고, Fermat의 정리에 따르면

- for $3 \not\mid n$, $n^2 \equiv 1$ (mod 3)
- for $5 \not\mid n$, $n^4 \equiv 1$ (mod 5)

<br>

이제 $n^{32}$를 Fermat 정리를 이용해 표현하면 아래와 같다.

- if $3 \not\mid n$, $n^{32} \equiv (n^2)^{16} \equiv 1$ (mod 3)
- if $5 \not\mid n$, $n^{32} \equiv (n^4)^{8} \equiv 1$ (mod 5)

<br>

케이스를 나누어 $n^{32} - 1$을 분석해보자.

<br>

1\. $3 \not\mid n$ and $5 \not\mid n$

then, $3 \mid (n^{32} - 1)$ and $5 \mid (n^{32}-1)$을 의미한다.

따라서 $15 \mid (n^{32} - 1)$을 얻는다.

따라서 $(n^{32} - 1) \equiv 0$ (mod 15)이다.

<br>

2\. $3 \mid n$ and $5 \mid n$

이것은 $15 \mid n$를 의미하므로 $15 \mid n(n^{32}-1)$이다.

<br>

3\. $3 \not\mid n$ and $5 \mid n$

$3 \mid (n^{32} - 1)$이므로 $3 \mid n(n^{32} - 1)$이다. 또한, $5 \mid n(n^{32} - 1)$이므로

$15 \mid n(n^{32}-1)$이다.

<br>

4\. $3 \mid n$ and $5 \not\mid n$

3\.의 경우와 마찬가지이다.

</div>

<br>
<hr>

Fermat's Little Theorem은 Ring의 Multiplicative group으로부터 쉽게 유도할 수 있는 성질이었다 ㅎㅎ

하.지.만.!

Euler 형님이 우리를 위해 FLT를 일반화 해주셨다 ㅠㅠ

[Euler's Theorem]({{"2020/12/26/Euler-Thm.html" | relative_url}})
