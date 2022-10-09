---
title: "Hamilton Cycle Problem"
layout: post
tags: ["algorithm"]
---



2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr/>

<div class="statement" markdown="1">

Given a graph, find a cycle that passes through every vertex exactly once.

</div>

즉, <span style="color: red">펜을 한번도 떼지 않고 모든 정점을 방문할 수 있냐</span>에 대한 질문이다.

DP 챕터에서 다룬 [\<**외판원 순회문제; Traveling Salesman Problem**\>]({{"/2021/06/13/traveling-salesman-problem.html" | relative_url}})와 비슷하다. 그러나 외판원 문제는 해밀턴 사이클을 찾되, 그 중에서 **가장 작은 비용**을 갖는 사이클을 찾아야 한다. 즉, <span style="color: red">외판원 문제는 해밀턴 사이클 문제에서 최적해에 대한 조건이 더 붙은 것</span>이다.

간선(edge)을 모두 방문해야 하는 [\<**오일러 경로 문제; Euler Path Problem**\>]({{"/2022/03/11/euler-path-problem.html" | relative_url}})와 비교하면, 정점(vertex)을 방문하는 해밀턴 경로 문제 쪽이 더 쉬워 보일지도 모른다. 보통 그래프에서 간선이 정점보다 수가 많기 때문이다. 그러나 해밀턴 경로 문제 쪽이 훨-씬 어렵고, poly-time algorithm도 존재하지 않는다. 그것은 <span style="color: red">정점을 한번씩만 방문하는 것이 간선을 한번씩만 방문하는 것보다 더 강한 조건</span>이기 때문이다!

### NP 문제인가?

\<외판원 문제\>와 마찬가지로 \<해밀턴 경로 문제\> 문제도 class NP에 속한다. solution $S$가 주어졌을 때 검산은 poly-time에 가능하지만, solution을 찾는 poly-time 알고리즘이 존재하지 않는다. 그래서 \<해밀턴 경로 문제\>도 NP 문제에 해당한다. P, NP에 해당 내용은 "[P and NP]({{"/2022/01/14/P-and-NP.html" | relative_url}})" 포스트를 참고하자.

### 함께보기

- [Traveling Salesman Problem]({{"/2021/06/13/traveling-salesman-problem.html" | relative_url}})
- [P and NP]({{"/2022/01/14/P-and-NP.html" | relative_url}})

