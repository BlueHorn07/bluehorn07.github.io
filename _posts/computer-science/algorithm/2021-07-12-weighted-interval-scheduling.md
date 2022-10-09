---
title: "Weighted Interval Scheduling"
layout: post
tags: ["algorithm"]
---



2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr/>

이전의 ["Interval Scheduling and Partitioning"]({{ "2021/04/20/interval-scheduling-and-partitioning.html" | relative_url}}) 포스트에서 Interval을 스케쥴 하는 방법에 대해 살펴보았다. 이때는 Greedy 한 방식으로 접근해 문제를 해결했다.

Greedy는 Interval이 모두 동일한 weight를 갖는다면 잘 동작한다. 그러나 이번에는 각 Interval에 weight $W_i$가 있어 가중합을 최대화 하도록 문제를 풀어야 한다. 이 경우에는 Greedy가 아니라 DP로 문제를 해결해야 한다!

<hr/>

먼저 문제를 기술해보자.

<div class="statement" markdown="1">

A set of $n$ jobs, job $j$ starts at $s_j$, finishes at $f_j$ and has weight $w_j$. Find a maximum weight subset of "mutually compatible" jobs.

</div>

먼저 Greedy 방식과 동일한게 interval은 finish time $f_j$를 기준으로 정렬한다. 그리고 함수 $p(j)$를 정의해 job $j$와 compatible 하면서 가장 빠른 job $i$를 구해보자.

- $p(j)$: last index $i < j$ s.t. job $i$ is compatible with $j$.

마지막으로 $DP[j]$를 "value of optimal solution to the problem consisting of jobs from $1$ tot $j$" 정의하면 문제를 해결할 준비가 끝났다! 😎

$DP[j]$를 업데이트 하는 규칙에는 2가지가 있다. 

<div class="math-statement" markdown="1">


1\. select job $j$

then, we cannot use incompatible jobs, so

$$
DP[j] = w_j + DP[p(j)]
$$

2\. not select job $j$

then, just use previous optimal result

$$
DP[j] = DP[j-1]
$$

</div>

위 규칙을 종합하면 아래와 같다.

$$
DP[j] = \max \left\{ w_j + DP[p(j)], \; DP[j-1] \right\}
$$

<hr/>

(아래의 글은 저의 뇌-피셜 입니다 🤪)

\<Interval Scheduling\> 문제에서도 Greedy와 DP가 다르다는 느낌을 받을 수 있다. Greedy는 이 접근이 최적해를 보장함을 완전히 확신하거나 직접 '증명'해야 한다. 이 과정에서 룰이 너무 복잡하다면 DP가 올바른 선택일 수 있다. 반면에 DP는 특별히 '증명'을 할 필욘없다. 문제를 Greedy 만큼 단순하게 해결하진 않지만 가능한 모든 경우를 DAG의 형태로 고려하여 답을 유도하기 때문이다!

\<Interval Scheduling\> 문제와 관련된 백준 문제는 아직 찾지 못 했다. 추후에 찾게 되면 업데이트 하겠다.
