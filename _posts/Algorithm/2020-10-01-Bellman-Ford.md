---
title: "Bellman-Ford Algorithm"
layout: post
use_math: true
tags: ["Algorithm"]
---

## 서론
본 글은 『Algorithms』(Dasgupta et al)의 내용을 바탕으로 Bellman-Ford Algorithm에 대한 내용을 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

## Shortest Path with Negative Edge
Negative edge가 있는 Acyclic Directed Graph에서의 Shortest path를 찾는 **Bellman-Ford Algorithm**에 대한 글입니다.

구현 없이 Bellman-Ford 알고리즘의 이론적인 측면만 다루었습니다. 구현은 [Wikipedia/Bellman-Ford-Algorithm](https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm)의 pseudo-code를 참고해주세요!

### Golden rule in shortest path
shortest path에는 다음의 중요한 성질이 있다.

- Shortest path is on branch of Path Tree
- `update()` gives the correct dist($v$) value when
  - $u$ is the second last node of the shortest path to $v$.
  - dist($u$) is correctly set.

위의 두 성질로부터 Bellman-Ford 알고리즘을 어떻게 유도하는지 살펴보자.

#### Shortest path is on branch of Path Tree

<div style="text-align: center;">
<img src="/assets/img/shortest-path-tree.png" style="width: 70%;"><br>
<p>Graph and its Shortest path tree<br><small>diagram from 『Algorithms』(Dasgupta)</small></p>
</div>

Graph가 Negative Cycle을 갖지 않는다면, Graph의 shortest path는 **Shortest Path Tree**를 이룬다.

#### distance update rule

``` shell
procedure update((u, v) ∈ E)
if dist(v) > dist(u) + w(u, v):
  dist(v) = dist(u) + w(u, v)
```

`update()`[^1] gives the correct dist($v$) value when <br> 
- $u$ is the second last node of the shortest path to $v$. <br>
- dist($u$) is correctly set.

위의 명제는 직관적으로 참이다. 그렇기 떄문에 이 명제에 대해서는 증명하지 않겠다. 다만, **Bellman-Ford 알고리즘**이 이 명제를 어떻게 활용하는지를 살펴보자!

<br>

Bellman-Ford 알고리즘은 시작점 $s$는 `dist(s)=0`로, 나머지 노드는 모두 `INF`로 초기화한다. 이제 시작점 $s$와 연결된 노드들을 살펴보자. *See node $u_i$ s.t. $(s, u_i) \in E$*

`dist(u_i)`의 값은 `update()` 규칙에 따라 `dist(u_i) = dist(s) + w(u, v)`가 된다. 위의 명제를 다시 보자. 만약 dist($u_i$)에 대해 $s$가 second last 노드이고, dist($s$)가 correctly setted라면, dist($u_i$)에는 `update()`를 통해 shortest distance가 저장된다. 

이제, 모든 edge $E$에 대해서 `update()` rule을 적용해보자. dist($s$) 외에는 모두 `INF`로 초기화 되어 있기 때문에 $s$와 연결된 노드들의 `dist` 값만 갱신된다. 이때에 갱신되는 노드들 중 "**적어도 하나**"는 $s$를 second last 노드로 갖는다.[^2] 

따라서 `update()` rule의 첫 iteration을 통해 correctly set된 몇개 노드를 추가로 얻을 수 있다. 

**Shortest Path Tree**는 트리의 성질에 따라 $V-1$ 만큼의 edge를 갖는다. iteration 한번에 shortest path의 한 간격을 커버하는 것이므로 우리는 $V-1$번의 iteration이 필요하다!

#### Finding Negative Cycle
Bellman-Ford 알고리즘은 Negative edge를 허용하지만, **Negative Cycle은 허용하지 않는다**. 그래서 Bellman-Ford 알고리즘의 결과를 보장하지 위해 Negative Cycle이 없음을 확인해야 한다!!

그래서 $V-1$번 ieteration을 진행한 후 한 번 더 iteration을 진행했을 때 `update()` 함수가 호출된다면, 주어진 Graph에서 Negative Cycle이 있다고 판단할 수 있다.

<hr>

## 맺음말

Dijkstra 알고리즘과 달리 Bellman-Ford 알고리즘은 그 원리가 한번에 잘 와닿지 않는 것 같다. 하지만 실전엔 non-Negative edge만 나오는 것은 아니기 때문에 Negative edge 경우 역시 늘 준비해둬야 한다.

<hr>

[^1]: 이 `update()` rule을 *edge-relaxation* 라고도 부른다.
[^2]: $s$와 연결된 모든 노드가 첫 iteration에서도 바로 `dist` 값이 correct set 되는 것이 아니다. 일부 노드는 negative edge가 포함된 다른 경로가 있어 그것이 shortest path가 될 수도 있다.