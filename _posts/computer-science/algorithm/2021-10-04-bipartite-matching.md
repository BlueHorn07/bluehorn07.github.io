---
title: "Bipartite Matching"
toc: true
toc_sticky: true
categories: ["Algorithm"]
---



2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

이번 포스트는 [Network Flow]({{"/2021/07/16/network-flow" | relative_url}}) 포스트의 후속 포스트입니다. Network Flow 문제의 핵심이 되는 정리인 \<Max-Flow Min-Cut Theorem\>에 대해 궁금하다면 이전 포스트를 참고해주세요! 😉

이번 포스트의 코드는 [백준 1298번: 열혈강호 ](https://www.acmicpc.net/problem/11375) 문제를 기준으로 작성되었습니다 👨‍💻

<hr/>

## Introduction to Bipartite Matching

**\<Bipartite Matching; 이분 매칭\>**은 직원의 집합 $P$와 작업의 집합 $J$, 그리고 각 직원들의 작업에 대한 선호가 주어졌을 때 (직원, 작업)의 매칭의 수를 최대한으로 만드는 조합을 도출하는 문제이다.

<div class="img-wrapper">
  <img src="{{ "/images/algorithm/bipartite-matching-1.png" | relative_url }}" width="300px">
</div>

이때, 매칭을 생성할 때는 각 사람은 최대 하나의 작업에 할당될 수 있고, 하나의 작업은 최대 한 사람에게만 할당 받을 수 있다. ~~대충 한 사람이 2가지 일을 못하고, 한 작업에 2명이 못 들어간다는 말~~

(직원, 작업)의 매칭을 진행하다가 더 이상 매칭을 진행할 수 없는 상태를 *maximal matching*이라고 하며, 당연하게도 모든 maximal matching이 maximum matching을 보장하는 것은 아니다!

<hr/>

### Reduction to NF Problem

우리는 \<Bipartitie Matching\> 문제를 아래와 같이 \<reduction; 환원\>시켜 해결할 수 있다.

<div class="img-wrapper">
  <img src="{{ "/images/algorithm/bipartite-matching-2.png" | relative_url }}" width="600px">
</div>

<div class="math-statement" markdown="1">

<span class="statement-title">Reduction to NF</span>

1. Replace each edge to a directed edge from a person to a job of capacity 1.
2. Add a special node $s$ and connect $s$ to every node in People by directed edge of capacity 1.
3. Add a special node $t$ and connect every node in Jobs to $t$ by directed edge of capacity 1.

</div>

위와 같이 \<Bipartite Maching\>을 \<Network Flow\> 문제로 환원하면, 우리는 reduction 위에서의 maximum flow가 아래의 결과를 뱉을 것이라고 기대할 수 있다.

<div class="statement" align="center">

all capacities are 1, so an edge is used(= flow 1) or not.

</div>

그리고 reduction된 NF 문제에서 얻는 maximum total flow가 maximum matching을 유도한다는 것을 간단하게 증명해보자!

<div class="math-statement" markdown="1">

Let $M$ be the set of edges used in the maximum flow of value $k$. Then $M$ is a matching because there is at most on edge in $M$ leaving a one person and entering a one job. (바로 위에서 언급한 성질 때문!) Moreover, $M$ consists of $k$ matching edges!

(귀류법) If there is a matching consisting of more than $k$ matching edges, then there must be a flow of value larger than $k$. That is a contraction!

</div>

<hr/>

### 구현

사실 구현은 앞선 포스트에서 살펴본 \<Ford-Fulkerson Algorithm\>으로 reduction을 잘 구현하면 된다. 별로 어렵지 않다 ㅎㅎ

👉 [Ford-Fulkerson & Edmons-Karp Algorithm]({{"/2021/10/03/ford-fulkerson-algorithm-and-edmons-karp-algorithm" | relative_url}})

### 함께보기

- [3D Matching]({{"/2022/05/07/3D-matching" | relative_url}})

