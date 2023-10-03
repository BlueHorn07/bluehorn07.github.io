---
title: "Guass's Lemma"
toc: true
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

잠깐 맥락을 곁들이자면, **"Gauss's Lemma"**는 아래의 명제를 증명하는 과정에서 만나는 중간단계이다.

<div style="text-align:center">

"If $D$ is a UFD, then $D[x]$ is a UFD."

</div>

<br>

아래에 이와 비슷한 느낌의 정리들을 좀 모아봤다.

- $R$ is a **<u>ring</u>** $\implies$ $R[x]$ is also a **<u>ring</u>**.
- $D$ is an **<u>integral domain</u>** $\implies$ $D[x]$ is also an **<u>integral domain</u>**.
- $F$ is a **<u>field</u>** $\iff$ $F[x]$ is a **<u>PID</u>**. ([Thm 27.24]({{"2020/12/27/principal-ideal.html#theorem-2724" | relative_url}}))
  - 이때 모든 Field는 PID이므로 위 명제에서 'field'를 'PID'로 바꿔도 성립한다.
- $D$ is an **<u>integral domain</u>** with an irreducible elt $\implies$ $D[x]$ is **<u>not a PID</u>**.
- $F$ is a **<u>field</u>** $\implies$ $F[x_1, \dots, x_n]$ is **<u>not a PID</u>** for $n \ge 2$.

<br>
<hr>

우리는 지금까지 자연수에서의 GCD를 접했다. 하지만 앞에서 살펴본 '[산술의 기본정리]({{"2021/01/17/fundamental-thm-of-arithmetic.html" | relative_url}})'에 따르면 자연수 집합도 결국은 UFD의 한 예에 불과했다. 자연수 집합에서 정의한 GCD를 UFD로 확장하여 다시 정의해보자.

<span class="statement-title">Definition.</span> GCD in UFD<br>

<div class="notice" markdown="1">

Let $D$ be a UFD, and $a_1$, $a_2$, ..., $a_n$ be non-zero elts of $D$.

$d$ is a GCD of all of $a_i$,

if $d \mid a_i$ for $i=1, ..., n$ and all $d' \in D$ that divides all the $a_i$ also divides $d$.

</div>

자연수 집합에서는 GCD가 유일하게 존재했지만, 자연수 집합를 포괄하는 개념인 UFD에서 GCD는 더이상 유일하지 않다.

UFD에서 두 개의 GCD $d$, $d'$가 존재한다고 가정해보자. 그러면, GCD의 정의에 의해 $d \mid d'$, $d' \mid d$가 성립한다.

즉, 서로 다른 두 GCD $d$, $d'$가 *associate* 하다.

<br>

<span class="statement-title">Simple Example.</span><br>

서로 다른 두 GCD에 대한 예는 생각보다 간단히 찾을 수 있다. 두 정수 $6$와 $-8$에 대한 GCD는 $2$와 $-2$이다.

즉, 정수 $\mathbb{Z}$는 UFD이면서 서로 다른 두 GCD를 찾을 수 있다. 반면에 자연수 $\mathbb{N}$는 UFD이지만, GCD가 유일하게 결정된다.

<br>
<hr>

### Primitive polynomial

<span class="statement-title">Definition.</span> content & primitive part<br>

<div class="notice" markdown="1">

The **<u>content</u>** of (polynomial with integer coeffi-.) = $\gcd$ of it coeffi-.

The **<u>primitive part</u>** of polynomial = quotient of polynomial by its content.

따라서 (polynomial) = (cotent) x (primitive part)

</div>

<br>

<span class="statement-title">Definition.</span> Primitive Polynomial<br>

<div class="notice" markdown="1">

A polynomial is **<u>primitive</u>**, if its content equals 1.

</div>

- [Wikipedia - Primitive polynomial(ring theory)](https://en.wikipedia.org/wiki/Primitive_part_and_content)

<br>
<hr>

### Gauss's Lemma

<span class="statement-title">Lemma 45.23</span><br>

<div class="notice" markdown="1">

If $D$ is a UFD,

then for every non-constant $f(x) \in D[x]$, we have $f(x) = (c)g(x)$, where $c \in D$ and primitive $g(x) \in D[x]$.

(즉, UFD에서 모든 non-constant $f(x)$는 primitive의 상수곱이라는 말이다.)

</div>

<span class="statement-title">proof.</span><br>

<details>
<div class="math-statement" markdown="1">

Let $f(x) \in D[x]$ be a non-constant polynomial; $f(x) = a_0 + a_1 x + \cdots a_n x^n$

Let $c$ be a gcd of all $a_i$.

Then for each $i$, we have $a_i = c \cdot q_i$ for some $q_i \in D$.

By the distributive law, we have $f(x) = (c) g(x)$.

By definition of gcd $c$, the left polynomial $g(x)$ is a primitive polynomial. $\blacksquare$

</div>
</details>

<br>
<hr>

<span class="statement-title">Lemma 45.25</span> Gauss's Lemma<br>

<div class="notice" markdown="1">

If $D$ is a UFD, then a product of two primitive polynomials in $D[x]$ is again primitive.

</div>


<span class="statement-title">proof.</span><br>

<details>
<div class="math-statement" markdown="1">

Let $f(x) = a_0 + a_1 x + \cdots a_n x^n$ and $g(x) = b_0 + b_1 x + \cdots b_m x^m$ be primitive in $D[x]$,

and let $h(x) = f(x)g(x)$.

Let $p$ be an irreducible in $D$.

이미 $f(x)$, $g(x)$가 primitive이므로 $p$가 $a_i$ 전부를, 또 $b_j$ 전부를 나누지는 못 한다.

Let $a_r$ be the first coefficient of $f(x)$ not divisible by $p$;

that is $p \mid a_i$ for $i < r$, but $p \not\mid a_r$.

Similarly, let $b_s$ be the first coefficient of $g(x)$ not divisible by $p$.

The cofficient of $x^{r+s}$ in $h(x) = f(x)g(x)$ is

$$
c_{r+s} = (a_0 b_{r+s} + \cdots + a_{r-1} b_{s+1}) + a_r b_s + (a_{r+1} b_{s-1} \cdots a_{r+s} b_0)
$$

$p \mid a_i$ for $i < r$이므로 $p \mid (a_0 b_{r+s} + \cdots + a_{r-1} b_{s+1})$

마찬가지로 $p \mid b_j$ for $j < s$이므로 $p \mid (a_{r+1} b_{s-1} \cdots a_{r+s} b_0)$

하지만, $p$가 $a_r$, $b_s$를 나누진 못 하므로 $p \not\mid a_r b_s$이다.

종합하면, 어떤 irreducible $p \in D$일지라도 $f(x)g(x)$의 계수를 나누지 못 하는 지점이 있기 때문에 $f(x)g(x)$의 계수는 어떤 irreducible $p$라도 common divisor로 가질 수 없다.

따라서 $f(x)g(X)$는 primitive다. $\blacksquare$

</div>
</details>

<br>
<hr>

이제 이 Guass's Lemma를 활용해 본래의 목적인

<div style="text-align:center">

"If $D$ is a UFD, then $D[x]$ is a UFD."

</div>

를 증명해보자!

다음 포스트: [Poylnomial over UFD]({{"2021/01/18/polynomial-over-UFD.html" | relative_url}})
