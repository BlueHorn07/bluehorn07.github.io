---
title: "Fundamental Theorem of Arithmetic"
toc: true
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

PID가 UFD를 만족함을 증명하는 포스트는 [이곳]({{"2020/12/27/Unique-Factorization-Domain-2.html" | relative_url}})에서 볼 수 있습니다.

<br>
<hr>

<br><span class="statement-title">Theorem.</span> Fundamental Theorem of Arithmetic<br>

<div class="statement" style="text-align:center" markdown="1">

<big>The integral domain $\mathbb{Z}$ is a UFD</big>

</div>

<br><span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

$\mathbb{Z}$의 모든 Ideal은 모두 principal ideal이다.

$n\mathbb{Z} = \left< n \right>$

따라서 $\mathbb{Z}$가 PID이므로 $\mathbb{Z}$는 UFD이다. $\blacksquare$

</div>

Fundamental Theorem of Arithmetic <small>(산술의 기본정리)</small>은 "Unique Factorization Theorem"으로도 불린다.

<br>

산술의 기본정리를 다르게 표현하면 아래와 같다.

<div class="notice" markdown="1">

For a natrual number $n > 2$,

can have an unique factorization.

$$
n = p_1 p_2 \cdots p_r
$$

and it is isomorphic upto re-orderings.

<br>

이것을 좀더 집약해 형태로 표현하면 아래 같다.

$$
n = p_1^{n_1} p_2^{n_2} \cdots p_r^{n_r}
$$

</div>

앞에서는 대수적인 구조를 바탕으로 **산술의 기본정리**를 증명했다면, 이번에는 수론의 관점에서 증명을 해보자!

<br><span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

증명은 귀납법에 의해 진행된다.

(Base case) $n=2$에 대해선 $2=2$, $n=3$에 대해선 $3=3$, $n=4$에 대해선 $4=2^2$의 유일한 소인수분해가 존재한다.

(Induction step) 자연수 $n=N$에서 유일한 소인수분해가 존재한다고 가정하고, $N+1$의 경우를 살펴보자.

\* Case 1: $N+1$가 소수

$N+1$가 소수라면, 이미 유일한 소인수분해를 갖는다.

\* Case 2: $N+1$가 합성수

$N+1$가 합성수이므로 1이 아닌 두 자연수의 곱으로 나타낼 수 있다.

$$
N+1 = n_1 n_2
$$

$N$까지의 모든 자연수에 대해 우리는 유일한 소인수분해를 찾을 수 있었다.

따라서 $n_1$, $n_2$는 모두 uniquely factorize 된다.

$n_1$, $n_2$의 factorization을 곱해 $N+1$의 factorization을 구할 수 있고, 이는 유일하다.

(만약 이 유일성이 의심된다면, 또다른 factorization을 가정하고 두 factorization이 같음을 보이면 된다. 앞의 [UFD2 포스트]({{"2020/12/27/Unique-Factorization-Domain-2.html" | relative_url}})에서 했던 과정과 비슷하다.)

따라서 자연수 $\mathbb{N}$은 UFD이다. $\blacksquare$

</div>

