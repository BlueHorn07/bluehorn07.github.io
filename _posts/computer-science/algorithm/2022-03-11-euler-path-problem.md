---
title: "Euler Path Problem"
toc: true
toc_sticky: true
categories: ["Algorithm"]
---



2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

\<Euler Path Problem\>은 이산 수학의 기본적인 문제다. 알고리즘이 정말 간단하니 한번 살펴보자.

<hr/>

<div class="img-wrapper">
  <img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Konigsberg_bridges.png" width="240px">
</div>

<div class="statement" markdown="1">

Given a graph, find a path that contains each edge exactly once.

</div>

즉, <span style="color: red">펜을 한번도 떼지 않고 주어진 그래프를 그대로 그려낼 수 있느냐</span>에 대한 질문이다.

문제를 제시한 오일러는 그래프가 Euler Path를 가지기 위해선 두 가지 조건을 만족해야 한다고 주장했다.

1. graph is connected (당연)
2. every verte must have even degree (단, start/end vertex는 odd degree 가능)

<hr/>

## Euler Path Theorem

<div class="theorem" markdown="1">

<span class="statement-title">Theorem.</span><br>

A connected undirected graph has an <u><b>Eulerian cycle</b></u> iff every vertex has even degree.

</div>

<div class="proof" markdown="1">

<span class="statement-title">*Proof*.</span> $\implies$ <br>

Path가 vertex를 <u><b>지나는</b></u> 상황을 생각해보자. 그러면, 해당 vertex에 들어가는 간선, 해당 vertex에서 나가는 간선이 무조건 필요하다. 그래서 <span style="color: red">Path가 vertex를 한번 지날 때마다, 해당 vertex의 2개의 degree가 보장된다</span>는 것을 말한다.

Euler cycle은 그래프의 모든 vertex를 지나게 된다. 그래서 Euler cycle이 지나는 모든 정점은 path가 vertex를 방문하는 횟수의 2배 만큼의 정점을 반드시 가지고 있다. 이때, Euler cycle은 graph의 모든 간선 즉, vertex의 모든 edge를 지나므로 path가 vertex를 방문하며 훑고 남은 edge는 존재하지 않는다.

따라서, Euler cycle을 갖는 graph의 vertex는 모두 even degree를 갖는다. $\blacksquare$

</div>

<div class="proof" markdown="1">

<span class="statement-title">*Proof*.</span> $\impliedby$ <br>

1. 한 vertex에서 시작해 인접한 vertex 중 하나를 방문한다. 이 과정을 유한번 반복하면, 언젠가 출발했던 vertex로 돌아오게 된다! (even degree graph의 성질에 의해 cycle이 반드시 존재함)

2. 만약 우리가 시작 vertex로 돌아왔을 때 graph의 모든 정점을 방문한 것이라면, 그 cycle이 바로 Euler cycle이다!
3. 만약 아직 방문하지 않은 edge가 남았다면, 직전의 cycle을 기존 그래프에서 지워준다. cycle을 지웠기 때문에 그래프의 간선도 짝수개 만큼 사라진다.
4. 위의 과정을 모든 정점을 방문할 때까지 반복한다.

$\blacksquare$

</div>

<hr/>

## 시간 복잡도

주어진 그래프가 Euler cycle인지는 $O(V)$ 만에 판단할 수 있다. 단순히 각 정점의 degree 갯수가 모두 짝수 개인지만 세면 되기 때문이다.

주어진 그래프에서 Euler cycle을 찾는 것 역시 마찬가지다. 점정 하나를 정해 DFS/BFS로 정점을 방문하며 cycle을 생성하면 된다. cycle이 생성되면 해당 cycle을 그래프에서 지워준다. 이 과정을 반복해 방문한 정점의 순서가 바로 Euler Cycle이다. 각 정점을 한번씩 방문하게 되기 때문에 $O(V)$의 시간에 해결할 수 있다.

<hr/>

## 맺음말

\<Euler Path Problem\>과 비슷하게 \<Hamilton Path Problem\>도 존재한다. 이 경우는 모든 정점을 한번씩만 방문하는 방법이 존재하는지 판별해야 한다. \<Euler Path Problem\>과 달리 poly-time으로 해결되는 알고리즘이 존재하지 않는다. 다음 포스트에서 이 문제에 대해 살펴보겠다.

👉 [Hamilton Cycle Problem]({{"/2022/03/12/hamilton-cycle-problem.html" | relative_url }})
