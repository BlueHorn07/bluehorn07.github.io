---
title: "Greedy Algorithm"
toc: true
toc_sticky: true
categories: ["Algorithm"]
---


2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

\<Greedy Algorithm; 탐욕 알고리즘\>의 아이디어는 간단하다.

<div class="statement" markdown="1" style="text-align:center; font-size: large;">


thinking ahead sv. choosing immediate advantage

목적을 달성하기 위해 지금 명확하고 즉각적인 <span style="color: red">눈앞의 이득</span>을 취한다.

</div>

<Greedy Algorithm\>이 사용되는 예는 다양하다. Weighted Graph에서 Minimum Spanning Tree를 생성하거나, 일정을 스케쥴링 하거나, 또는 문서를 압축하기 위한 Huffman Encoding 등에서 \<Greedy Algorithm\>을 사용한다.

개인적으로 문제를 풀 때, Greedy로 풀지, DP로 풀지 좀 헷갈리는 상황들이 있는데, 본인은 보통 Greedy로 풀었을 때의 반례 위주로 생각하는 편이다. 만약 Greedy로 풀었을 때, 반례가 있다면 Greedy로 접근하지 않고 다른 방법을 생각해본다.

때로는 Greedy와 DP를 함께 써야 하는 경우도 있다. 두 알고리즘이 배타적인 것이 아니라, 문제해결의 도구일 뿐이기 때문에 둘의 아이디어를 함께 사용해 문제를 해결할 수도 있다 😁

<hr/>

이번 챕터에서 다루는 문제들은 아래와 같다.

- MST; Minimum Spanning Tree
  - [Kruskal's Algorithm]({{"/2021/04/19/kruskal-and-prim-algorithm#kruskals-algorithm" | relative_url}})
  - [Prim's Algorithm]({{"2021/04/19/kruskal-and-prim-algorithm#prims-algorithm" | relative_url}})
  - Disjoint Set
    - Path Compression
- [Intervel Scheduling & Partitioning]({{"/2021/04/20/interval-scheduling-and-partitioning" | relative_url}})
- [Huffman Encoding]({{"/2021/10/08/Huffman-encoding" | relative_url}})
- Clustering of Maximum Spacing

