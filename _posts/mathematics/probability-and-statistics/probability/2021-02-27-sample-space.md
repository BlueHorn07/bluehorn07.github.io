---
title: "Sample Space"
layout: post
use_math: true
tags: ["probability"]
---


2021-1학기, 대학에서 '확률과 통계' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- Set-up
- Counting Sample Points
  - Permutation
  - Combination

<hr>

### Set-up

<br><span class="statement-title">Definition.</span> Experimenet<br>

any process that generates a set of data.

<br><span class="statement-title">Definition.</span> Sample Space $S$<br>

The set of all possible outcomes of a statistical experiments.

Each outcome in a sample space $S$ is called a **\<sample point\>**.

<br><span class="statement-title">Definition.</span> Event<br>

Any subset of sample space.

ex: event $A$ that \{the outcome when a die is tossed is divisible by 3\}.

\<Event\>를 정의함으로써 우리는 outcome 전체가 아닌 관심 있는 일부 outcome의 집합을 특정하게 된다. 나아가 Event는 일종의 집합이기 때문에 집합에서 쓰는 다양한 연산자들, $A^c$, $A \cap B$, $A \cup B$, $A \setminus B$ 등을 사용해 더 다양한 Event 집합들을 살펴볼 수도 있다!

<hr/>

### Counting Sample Points

Sample Space의 원소인 Sample Points를 세는 것은 \<확률\>을 정의하는 데에 좋은 접근이다! 이 부분에선 Sample Points를 세는 규칙들에 대해서 소개한다.

<br><span class="statement-title">Rule.</span> Product Rule<br>

If an operation can be performed in $n_1$ ways, and if for each of these ways a second operation can be performed in $n_2$ ways, then the two operations can be performed together in $n_1 n_2$ ways.

\<곱의 규칙 Product Rule\>을 간단하게 생각하면 아래와 같은 문제라고 생각할 수 있다.

<div class="img-wrapper">
  <img src="{{ "/images/probability-and-statistics/sample-space-1.jpg" | relative_url }}" width="230px">
</div>

<br><span class="statement-title">Rule.</span> Inclusion-Exclusion Principle<br>

\<포함-배제 원리\>라고 불리는 이 기법은 유한 집합 사이의 합집합의 원소의 갯수를 세는 기법이다. 다르게 말하면, 경우의 수를 세는 문제에서 \<합의 규칙 Additive Rule\>이라고 할 수 있다.

$$
\left| A \cup B \right| = \left| A \right| + \left| B \right| - \left| A \cap B \right|
$$

<hr/>

#### Permutation

앞에서 살펴본 \<Product Rule\>은 $n_1$, $n_2$ 두 가지 경우에 대한 경우의 수를 세는 규칙이었다. 만약 이것을 $k$개 만큼의 가짓수로 확장하면, \<Generalized Product Rule\>을 얻을 수 있다.

\<순열 Permutation\>은 이 \<Generalized Product Rule\>을 통해 얻는 결과 중 하나다.

<br><span class="statement-title">Definition.</span> Permutation<br>

A \<Permutation\> is an arrangement of all or part of a set of objects.

$n$개 원소의 집합 내에서 우리는 $n$개 원소의 arrangement를 생각할 수도 있지만, $r \le n$개 원소의 arrangement를 생각해볼 수도 있다.

이것을 잘 정리하면 아래와 같다.

<br><span class="statement-title">Theorem.</span> Permutation $_nP_r$<br>

The number of permutations of $n$ distinct objects taken $r$ at a time is

$$
_nP_r = \frac{n!}{(n-r)!}
$$

<br><span class="statement-title">Theorem.</span> circular permutation<br>

\<circular permutation\>이라는 재밌는 상황도 있는데, 이번엔 $n$개 원소를 원형으로 배열하는 가짓수를 말한다. 이 경우, 원이 한칸씩 회전해도 동일한 원형 배열이기 때문에 전체 가짓수에서 $n$번 만큼의 반복을 제외시켜야 한다. 따라서 $(n-1)!$ 만큼의 경우의 수가 존재한다.

<hr/>

#### Combination

\<조합 Combination\>은 "순서"를 무시하고 경우의 수를 셀 때 사용하는 접근이다.

<br><span class="statement-title">Theorem.</span> Combination $_nC_r$<br>

The number of combinations of $n$ distinct objects taken $r$ at a time is 

$$
_nC_r = {n \choose k} = \frac{n!}{r!(n-r)!}
$$

<br><span class="statement-title">Theorem.</span> Pascal's Triangle<br>

\<파스칼의 삼각형 Pascal's Triangle\>이라고 불리는 이 공식은 \<조합\>에서 아래와 같은 식이 성립함을 기술한다.

$$
{n \choose k} = {n-1 \choose k-1} + {n-1 \choose k}
$$

증명은 생각보다 간단한데, $n$ 원소 중에 특정 원소 $a$를 미리 뽑았느냐 안 뽑았느냐로 가짓수를 나누어 유도하면 된다.

- $a$를 이미 선택한 경우, 남은 $n-1$개 원소 중 $k-1$개를 선택하면 된다. 
 
$$
{n-1 \choose k-1}
$$

- $a$를 배제하고 선택하는 경우, 남은 $n-1$개 원소 중 $k$개를 선택하면 된다.

$$
{n-1 \choose k}
$$

본인의 경우, 식에서 $n-1$ 부분이 공통되는 걸 보고, 이를 통해 위의 아이디어를 떠올렸다.