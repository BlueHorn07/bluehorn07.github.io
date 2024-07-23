---
title: "Lagrange Theorem"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

**키워드**
- equivalent relation에 의한 분할
- coset $aH$
- index $[G:H]$
- (Lagrange Thm) $\lvert H \rvert \mid \lvert G \rvert$

<hr>

## Lagrange Theorem

<div class="notice" markdown="1">

If $H$ is a subgrop of a group $G$, then $\lvert H \rvert \mid \lvert G \rvert$, in other words, $\lvert G \rvert = [G:H] \lvert H \rvert$.

</div>

### equivalent relation과 coset
우리는 집합 위에 정의된 **equivalent relatio**n $\sim$가 집합을 분할한다는 사실을 알고 있다. 그리고 $\sim$에 의해 집합을 분할하는 부분집합을 **equivalent class**라고 한다.

equivalent relation $\sim_{L}$을 다음과 같이 정의하자.

$$
a \sim_{L} b \iff a^{-1}b \in H
$$

그러면, $a \in G$에 대해 $\sim_{L}$에 의해 $a$와 relate 되는 것들의 집합인 equivalent class $\bar{a}$는 다음과 같다.

<div style="text-align: center;">
$$\bar{a} = \{x \mid a \sim_{L} x\} = \{x \mid a^{-1}x \in H\} = \{x \mid a^{-1}x = h \in H\}$$
</div>

이때 $a^{-1}x = h \in H$에서 양변에 $a$를 곱하면, $x = ah \in aH$가 된다. 따라서

<div style="text-align: center;">
$$\bar{a} = \{x \mid x = ah \in aH\} \subseteq aH$$
</div>

$\bar{a} \supseteq aH$는 $x = ah \in aH$로 잡으면 손쉽게 보일 수 있으므로 생략하겠다.

따라서 $\bar{a} = aH$이며, 이것을 **(left) coset**이라고 한다.

coset $aH$의 형태만 익숙하다면, $aH$가 단순히 $H$에 $a \in G$를 곱한 집합이라는 느낌을 받게 된다. 그러나 $aH$는 집합과 원소 사이의 곱의 의미보다는 **equivalent class**임을 꼭 기억해야 한다.

### equivalent class에 의한 partition
앞에서 말했듯 equivalent relation은 equivalent class로 집합을 분할한다. 따라서 relation $\sim_{L}$의 equivalent class $aH$는 집합 $G$의 분할의 일부분이다.

우리는 모든 $aH$에 대해서 $\lvert H \rvert = \lvert aH \rvert$임을 보일 수 있다.

함수 $\phi : H \rightarrow aH$를 $\phi(h) = ah$로 정의하면 $\phi$는 1-1 & onto이다.

i) [1-1] <br>
$\phi(h_1) = \phi(h_2) \implies ah_1 = ah_2 \implies h_1 = h_2$

ii) [onto] <br>
For $x = ah \in aH$, there exist an inverse image $h \in H$ s.t. $\phi(h)=ah$.

1-1 & onto인 $\phi : H \rightarrow aH$가 존재하므로 $\lvert H \rvert = \lvert aH \rvert$이며, $H$의 모든 coset은 $H$와 동일한 cardinality를 갖는다.[^1]

군 $G$를 coset의 분할로 다시 쓰면

<div style="text-align: center;">
$$G = H {\cup\mkern-11.5mu\cdot\mkern5mu} {a_1}H {\cup\mkern-11.5mu\cdot\mkern5mu} {a_2}H {\cup\mkern-11.5mu\cdot\mkern5mu} \cdots$$
</div>

우리는 $G$가 유한군인 경우를 살펴볼 것이므로 가능한 coset $aH$의 수는 역시 유한하다. 이때 equivalent class인 두 coset $aH$와 $bH$에 대해 $a \sim_{L} b$ 즉, $a^{-1}b \in H$라면 $aH = bH$임을 이용하여 가능한 모든 coset의 조합에서 중복을 제거할 수 있다. 중복을 제거한 distinct coset의 갯수를 $m$이라고 하자.

이제 증명을 결론을 내려보자. 유한군 $G$는 $m$개의 distinct coset $aH$들로 분할된다. 따라서 $G$의 cardinality는 다음과 같다. $a_1 = e$라고 하자.

<div style="text-align: center;">
$$G = {a_1}H {\cup\mkern-11.5mu\cdot\mkern5mu} {a_2}H {\cup\mkern-11.5mu\cdot\mkern5mu} \cdots {\cup\mkern-11.5mu\cdot\mkern5mu} {a_m}H$$
$$|G| = |{a_1}H| + |{a_2}H| + \cdots + |{a_m}H| = m |H|$$
</div>

따라서 $\lvert H \rvert \mid \lvert G \rvert$가 성립한다! $\blacksquare$

### Index of subgroup
앞에서 정의한 # of distinc coset인 $m$을 $[G:H]$로 정의하자. 그러면, $\lvert G \rvert = m \lvert H \rvert = [G:H] \lvert H \rvert$가 된다.

$[G:H]$를 index of subgroup $H$라고 하며, Lagrange theorem의 결과인 $\lvert G \rvert = [G:H] \lvert H \rvert$로 정의한다. $\blacksquare$

또는 $H \leq G$을 통해 $[G:H] = \cfrac{\lvert G \rvert}{\lvert H \rvert}$로 간단하게 쓸 수 있다.

<hr>

## 맺음말
Lagrange Theorem은 초급 군론에서 핵심인 정리이다. 그만큼 자주 등장하고, 이후의 내용에 중요한 역할을 하기 때문에 정리를 바로 증명할 수 있도록 숙달하는 것이 좋다.

Lagrange Theorem 증명에서 핵심이 되는 아이디어는 "equivalent class로 군 $G$를 분할하는 것"이다. Lagrange Theorem을 증명하는 과정에서 coset이라는 개념이 새롭게 등장하지만 relation $\sim_{L}$의 equivalent class에 'coset'이라는 이름이 붙었을 뿐 본질은 equivalent class다.

Lagrange Theorem 증명의 흐름은 아래와 같다.
- equivalent relation $\sim_{L}$ 정의
- equivalent class $aH$ 정의
- equivalent relation의 특징인 집합의 분할 이용
- 그 과정에서 $\lvert H \rvert = \lvert aH \rvert$임을 증명
- number of distinct coset을 정의하는데에 필요한 $aH = bH$의 조건 제시
- 군 $G$를 distinct coset의 union으로 표현
- 이를 바탕으로 $\lvert G \rvert$를 $m\lvert H \rvert$로 표현
- 결론인 $\lvert H \rvert \mid \lvert G \rvert$ 제시

<hr>

### 참고자료
- [Wikipedia - Lagrange's theorem (group theory)](https://en.wikipedia.org/wiki/Lagrange%27s_theorem_(group_theory))

<hr>

[^1]: 일반적으로 equivalent relation에 의한 분할이 동일한 크기의 equivalent class로 분할됨을 보장하지는 않는다. 단지 equivalent class들이 pairwise disjoint하고, 그들의 union이 원래 집합이 됨을 말할 뿐이다. $G = P_1 {\cup\mkern-11.5mu\cdot\mkern5mu} P_2 \cdots P_n$