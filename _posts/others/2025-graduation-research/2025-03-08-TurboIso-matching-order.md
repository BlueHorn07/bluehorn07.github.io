---
title: "Paper Reading: TurboIso, Matching Order"
toc: true
toc_sticky: true
categories: ["Graduation Research"]
excerpt: ""
---

2025년 저는 데이터베이스 랩에 컨택하여 학부 졸업 연구 주제를 받아서 진행하고 있습니다. 저의 주제는 "Continuous Subgraph Matching"과 관련해 코드를 최적화 하고 개선하는 것으로 그래프 쿼리 관련 논문을 읽고, C++ 코드를 튜닝하고 있습니다. 졸업 마지막 학기에 듣는 수업인데 많은 노하우와 경험을 쌓고 졸업하기를 기대하고 있습니다 ㅎㅎ
{: .notice }

> Wook-Shin Han, Jinsoo Lee, and Jeong-Hoon Lee. 2013. TurboISO: Towards UltraFast and Robust Subgraph Isomorphism Search in Large Graph Databases

# 들어가며

[지난 포스트](/2025/03/08/TurboIso-construction/)부터 TurboIso 논문을 읽고 내용을 정리하고 있습니다 🏃‍♂️
논문 도입부에서 "Matching Order가 중요하다!"라고 지적 했던 것이 기억 나는데요!
이번 포스트에서는 TurboIso 논문에서 제시한 Matching Order에 대해서 정리해보겠습니다.

## 복습

TurboIso는 Subgraph Matching 문제를 풀기 위해 아래와 과정을 수행 했습니다.

1. 쿼리 그래프를 NEC 트리로 변환
2. NEC 트리를 따라 DFS 탐색을 수행
3. 각 NEC 노드마다 매칭 가능한 데이터 노드를 수집, "Candidate Subregion"을 생성

# Matching Order Selection

## Matching Order is Important

쿼리 그래프 $q$가 $n$개 노드를 가지고 있다면, 매칭을 찾기 위해 데이터 그래프에서 탐색해야 하는 검색 공간(= 경우의 수)는 아래와 같습니다.

$$
\vert C(u_1) \vert \times \cdots \times \vert C(u_n) \vert
$$

이때, $C(u_i)$는 쿼리 노드 $u_i$와 매칭 가능한 후보 데이터 노드의 집합(Candidate Subregion) 입니다.

<br/>

좋은 매칭 순서는 중간 단계에서의 경우의 수가 적게 되는 순서를 말합니다. 그래야 탐색이 빨라지고, 매칭이 존재하지 않는 상황을 조기에 확인하고 과정을 종료할 수 있습니다.

# SPath Selectivity

기존의 SPath(2010) 방식은 아래와 같이 선택도 함수 $sel(p)$를 정의하고, 이 중에서 가장 높은 값을 갖는 경로 $p$를 선택해 우선 매칭을 합니다.

$$
sel(p) = \frac{2^{\vert V(p) \vert}}{\prod_{u \in V(p)} \vert C(u) \vert}
$$

$p$는 쿼리 그래프 안에서의 탐색 경로(= 매칭 오더), $V(p)$는 경로에 포함된 쿼리 노드의 집합, 그리고 $\vert C(u) \vert$는 쿼리 노드에 매칭 가능한 데이터 노드의 갯수를 말합니다.

![](/images/others/2025-graduation-research/TurboIso/fig-6-query-graph.png){: .fill .align-center style="min-width: 260px; width: 60%" }

참고로 경로 $p$는 쿼리 트리르 순회하는 순서를 경로 분해한 요소 입니다.

SPath의 선택도 함수는 좋은 경로를 수치화 하려고 했습니다. 그래서,

- 분자 $2^{\vert V(p) \vert}$
  - 경로 $p$에 포함된 정점 수가 많을수록 좋다.
  - 더 긴 경로를 매칭하면, 필터링이 더 잘 일어나서 결과를 빨리 좁힐 수 있을 것이다.
- 분모 $\prod \vert C(u) \vert$
  - 각 쿼리 노드의 후보 갯수의 곱
  - 후보가 많으면, 많을수록 매칭이 어려워진다.
  - 선택도가 낮아지도록 한다.

TurboIso는 SPath의 이 선택도 함수가 적절하지 않다고 지적 합니다.<br/>
<small>(제가 SPath에 대한 논문을 읽은게 아니라서 SPath의 함수가 잘 와닿지 않네요... ㅠㅠ, 일단 제가 느끼기로는 서로 길이가 다른 경로에 대해서 위의 선택도 함수로는 두 경로를 비교하는게 잘 안 될 것 같다는 느낌 정도만 있습니다 🤔)</small>

> Note that the denominator represents the join cardinality of the candidate sets for all query vertices in $p$.
> However, this over-estimate leads to significant errors in estimating the join cardinality.

단순히 각 쿼리 정점을 곱하는 건, 진짜 매칭 가능한 경우의 수(Join Cardinality)를 과대 평가한다고 합니다. 실제로 매칭 후보 $C(u)$ 전부가 매칭되는게 아니기 때문이죠! 결국, SPath에서 분모 부분은 **Maximum** Join Cardinality를 기준으로 선택도를 정의하기 때문에, 부정확한 선택도 계산이 이뤄진다는 점을 지적합니다.

# TurboIso Selectivity

SPath 선택도의 단점을 개선하기 위해, TurboIso는 직접 후보 영역을 탐색하고, 후보 정점의 수를 카운트 합니다. 즉, 이론적인 추정이 아니라 정확한 데이터를 기반으로 판단합니다.

그리고 SPath에서 사용하는 $C(u)$는 전체 데이터 그래프 상에서 매칭 가능한 노드의 집합이기 때문에, 범위가 데이터 그래프 전체 입니다. 그러나 TurboIso에서는 "매칭이 일어날 가능성이 있는 부분"만으로 Candidate Region을 정의 했기 때문에, 더 견고 합니다. 즉, $\vert C(u) \vert$ 대신 $\vert CR_i(u', -) \vert$를 사용합니다.

<br/>

![](/images/others/2025-graduation-research/TurboIso/fig-6-matching-order.png){: .fill .align-center style="min-width: 260px; width: 100%" }

쿼리 그래프를 순회하는 과정은 3가지 경로($p_1$, $p_2$, $p_3$)로 경로 분해 됩니다.
이때, 각 경로의 리프 노드 $u_2$, $u_3$, $u_4$와 매칭되는 데이터 노드의 갯수가 Candidate Region 별로 계산 되어 있습니다: $\vert CR_1(u', -) \vert$.

Subgraph Matching을 확인하기 위해, 리프 노드의 매칭 후보가 가장 적은 경로부터 선택합니다. $CR_1$ 영역에 대해 수행할 때는, $p_3$부터 선택해 매칭을 확인하고, $CR_2$ 영역에서도 $p_3$부터 선택해 매칭을 확인합니다.

$p_3$에 대한 매칭을 확인하고 나면, 그 다음으로 매칭 후보가 적은 경로를 선택 합니다. $CR_1$에서는 $p_1$을, $CR_2$에서는 $p_2$를 선택 합니다. 종합하면, 아래와 같습니다.

$$
\begin{aligned}
CR_1&: p_3 \rightarrow p_1 \rightarrow p_2 \\
CR_2&: p_3 \rightarrow p_2 \rightarrow p_1 \\
\end{aligned}
$$

순서로 매칭 오더가 결정 됩니다.

## When non-tree edge exist

NEC 노드 $u'$에 대해 $j$개의 non-tree 엣지가 존재한다고 하자. 그러면, $u'$에 대한 Candidate vertex의 갯수를 $(j+1)$로 나눠줍니다.

<small>논문에서 예시도 들어주긴 했는데, 뭔 말인지 전혀 모르겠네요 ㅠㅠ</small>

## When NEC vertex contains many vertices

NEC 노드에 여러 쿼리 노드가 대응 될 수 있습니다. 이 경우, NEC 트리 상에서는 경로가 하나지만, 실제 쿼리 그래프에서는 여러 경로가 중첩되어 존재합니다.

![](/images/others/2025-graduation-research/TurboIso/fig-3-NEC-matching-order.png){: .fill .align-center style="min-width: 260px; width: 100%" }

예를 들어, Fig 3에서 NEC에서 경로 $u'_1 \rightarrow u'_3$는 단일 경로 이지만, 쿼리 그래프에서는 이것이 $u_1 \rightarrow u_3$와 $u_1 \rightarrow u_4$ 두 개에 해당 합니다.

이것은 단순히 $\vert CR(u'_3, -) \vert$만 보고 판단할 수 없다는 것을 말합니다.

이런 경우는 $CR(u'_3, -) = \left\\{ v_2, v_3, v_4, v_5 \right\\}$ 중에서 NEC 노드의 크기 만큼을 뽑는 "조합(combination)"을 해야 합니다.

$$
{\vert CR(u'_3, -) \vert \choose \vert u'_3.NEC \vert}
= {4 \choose 2} = 6
$$

이 됩니다.

<br/>

그러나 이 방식은 경로 $p$의 리프 노드의 NEC 노드가 다중 쿼리 노드인 경우만, 이렇게 핸들링 할 수 있습니다. 경로의 중간 NEC가 다중 쿼리 노드인 경우는 실제 매칭 수를 정확히 알 수 없다고 합니다.

그러나 위에서 사용한 조합식도 정확하지 않을 수 있습니다. 왜냐하면, $u_3$와 $u_v$는 같은 DFS 경로 상에서 같은 부모 노드로부터 내려와야 하기 때문 입니다. 만약, 두 경로가 다른 부모 노드에서 내려온다면, over-estimate가 발생할 수 있습니다.

그래서 TurboIso에서 사용하는 최종 보정식은 아래와 같습니다.

$$
\sum_{v \, \in \, CR(P(u'), -)} {\vert CR(u', v) \vert \choose \vert u'.NEC \vert }
$$

각 요소를 설명하면,

- $P(u')$는 NEC 노드의 부모 노드 입니다.
- $CR(P(u'), -)$는 그 부모 노드에 매칭 가능한 데이터 정점의 집합 입니다.
- $CR(u', v)$는 부모 데이터 정점 $v$에 연결된 자식 데이터 정점들 중, $u'$에 매칭 가능한 것들의 모임 입니다.

이렇게 해서, 같은 부모 노드를 가질 때의 조합을 계산하도록 합니다.

# 맺음말

![](/images/meme/oh-that-is-not-easy.png){: .fill .align-center style="width: 100%; max-width: 400px"}

사실 뒤에 좀더 내용이 남았는데요! TurboIso(2013)는 지금 2025년 기준으로는 옛날 알고리즘이고,
TurboFlux(2018)과 SymBi(2021) 알고리즘을 이해하기 위한 배경 지식을 얻기 위해 이 정도만 읽고 마무리 하려고 합니다.

TurboIso에 나왔던

- NEC
- Candidate Region
- NLF Filter
- Matching Order
- Selectivity

등등의 내용은 다른 Subgraph Matching 논문과 그래프 관련 연구를 할 때, 좋은 인사이트가 될 것 같습니다 ㅎㅎ

