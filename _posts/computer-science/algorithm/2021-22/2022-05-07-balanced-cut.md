---
title: "Balanced Cut"
toc: true
toc_sticky: true
categories: ["Algorithm"]
---



2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :) 전체 포스트는 [Algorithm](/categories/algorithm) 포스트에서 확인하실 수 있습니다.

<hr/>

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Cut<br>

A set of edges whose removal leaves a graph **disconnected**.

</div>

포스트를 앞에서부터 천천히 따라왔다면, [Network Flow]({{"/2021/07/16/network-flow" | relative_url}}) 챕터에서 Network Flow 문제가 Minimum Cut Problem과 dual problem 관계임을 공부했을 것이다.

<div class="notice" markdown="1">

<span class="statement-title">Problem.</span> Minimum Cut Problem<br>

Given a graph and a budget $b$, find a cut with at most $b$ edges.

<small>\* search problem의 형태로 기술 되었다.</small>

</div>

이 문제는 Network Flow 알고리즘으로 정말 쉽게 풀 수 있다.

- 각 edge에 1의 capacity를 할당
- source 노드 $s$를 정한다.
- 나머지 $n-1$개 노드에 대해 그들은 sink 노드 $t$로 설정해 $n-1$번 Network Flow 알고리즘을 돌린다.
- 그 중 가장 적은 비용의 cut을 minimum cut으로 삼는다.

<br/>

그러나 이렇게 쉬운 형태라면 NP에 등장할 수 없다. Balanced Cut 문제는 분할하는 두 그래프의 **균형**이 맞아야 한다.

<div class="notice" markdown="1">

<span class="statement-title">Problem.</span> Balanced Cut Problem<br>

Given a graph with $n$ vertices and a budget $b$, find a cut $(A, B)$ such that $\left\| A \right\|, \left\| B \right\| \ge n/3$ and at most $b$ edges in the cut.

<small>\* search problem의 형태로 기술 되었다.</small>

</div>

즉, 비용 $b$를 맞추면서 분할된 그래프 $A$, $B$의 크기가 비슷해야 하는 것이다! Balanced Cut Problem은 Minimum Cut Problem과 달리 poly-time algorithm이 존재하지 않는다.

### 함께보기

- [Network Flow]({{"/2021/07/16/network-flow" | relative_url}})
- [P and NP]({{"/2022/01/14/P-and-NP" | relative_url}})
