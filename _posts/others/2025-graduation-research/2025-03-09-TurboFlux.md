---
title: "Paper Reading: TurboFlux"
toc: true
toc_sticky: true
categories: ["Graduation Research"]
excerpt: ""
---

2025년 저는 데이터베이스 랩에 컨택하여 학부 졸업 연구 주제를 받아서 진행하고 있습니다. 저의 주제는 "Continuous Subgraph Matching"과 관련해 코드를 최적화 하고 개선하는 것으로 그래프 쿼리 관련 논문을 읽고, C++ 코드를 튜닝하고 있습니다. 졸업 마지막 학기에 듣는 수업인데 많은 노하우와 경험을 쌓고 졸업하기를 기대하고 있습니다 ㅎㅎ
{: .notice }

> Kyoungmin Kim, In Seo, Wook-Shin Han, Jeong-Hoon Lee, Sungpack Hong, Hassan Chafi, Hyungyu Shin, and Geonhwa Jeong. 2018. TurboFlux: A Fast Continuous Subgraph Matching System for Streaming Graph Data.

# 들어가며

졸업연구를 진행하면서, Symbi(2021)에서 기대만큼의 성능 차이가 잘 보이게 되어서 TurboFlux(2018) 논문에 대해서도 실험을 진행하게 되었습니다. 그래서 논문에 대한 파악이 필요하게 되었는데요! 이 포스트에서 논문을 이해하기 위한 과정을 기록 합니다.

참고로 유튜브에 TurboFlux의 저자가 발표한 10분짜리 영상도 있는데, 처음에 컨셉을 잡는데 도움이 되었습니다! [[Youtube]](https://youtu.be/0BAyhMs5ggg?si=0pGdzG-hO79Bu2DE)

# SJ-Tree

논문에 SJ-Tree(2013)에 대한 내용이 나와서 간단하게 요약합니다! 논문을 이해하는데 곁지식 정도라서 컨셉만 이해하고 넘어갔습니다.

일단 SJ-Tree는 주어진 쿼리 그래프를 작은 서브 그래프로 "**분해(decomposition)**" 합니다. 분해한 서브 쿼리 사이에는 공통 노드가 있으며, 이후 이것을 기준으로 계층 조인을 수행 합니다.

![](/images/others/2025-graduation-research/SJ-Tree/query-decomposition.png){: .fill .align-center style="min-width: 300px; width: 80%" }

분해로 만들어진 서브 그래프 마다 매칭 결과를 Partial Solution으로 저장합니다. 그리고 업데이트 연산 $\Delta o_i$가 생길 때마다, 이 Partial Solution을 업데이트 하고, 이것을 계층적으로 조인하여 최종 솔루션을 찾아낸다고 합니다. 이런 컨셉에서 "SJ(Subgraph Join)"-Tree의 이름이 유래된 것 입니다.

계층적으로 조인 하는 과정은 아래와 같습니다. 위의 캡처에서 $g_2$ 상황을 기준으로 설명하겠습니다.

![](/images/others/2025-graduation-research/SJ-Tree/graph-g2.png){: .fill .align-center style="min-width: 300px; width: 50%" }

1. 먼저 가장 리프 노드인 $q_{111}$과 $q_{112}$를 계층 조인 하여 $q_{11}$를 구합니다.
   1. 각각이 $q_{111} \Join q_{112} = 2 \times 100 = 200 = q_{11}$이 됩니다.
2. 마찬가지로 $q_{11}$과 $q_{12}$를 계층 조인하여 $q_1$을 구합니다.
   1. $q_{11} \Join q_{12} = 200 \times 110 = 22,000 = q_1$
3. 마지막으로 $q_1 \Join q_2$하여 원본 쿼리 $q$에 대한 매칭을 찾습니다.
   1. 이때는 $q_1 \times q_2 = 22,000$가 아니라, $q = 200$이 됩니다.
   2. 이유는 $B - D$ 연결 중에서 딱 한 경우만 $D - E$ 연결을 가지기 때문 입니다.
   3. 그래서 $q_{111} \times q_{112} \times 1 \times 1 = 200$이 됩니다.

그런데, SJ-Tree의 단점은 아주 큰 공간 복잡도가 필요하다는 것 입니다. 쿼리를 서브 쿼리로 분해하기 때문에, 이 서브 쿼리에 대한 Partial Solution을 유지하는 것만 해도 아주 큰 공간이 필요합니다. 캡쳐에서는 Partial Solution이 $1$, $200$과 같은 숫자로 나왔지만, 실제로는 (해시) 테이블 형태로 저장 됩니다.


SJ-Tree의 공간 복잡도는 아래와 같다고 합니다.

$$
O(\vert V(q) \vert  \times \vert E(g) ^ {E(q)} \vert)
$$

<br/>

TurboFlux는 SJ-Tree의 단점을 해결하고자 "DCG(Data Centric Graph)"라는 자료구조를 새롭게 제안 합니다.

이 자료구조는 SJ-Tree 보다 더 작은 공간으로 동작하며, 조인을 기반으로 하는 SJ-Tree와 달리 DCG를 그래프 순회하는 것으로 CSM의 해를 찾아냅니다.


# Introduction to Data-centric method

기존의 서브 그래프 매칭은 각 쿼리 노드마다 후보 데이터 노드들을 기억 해두었습니다. 그런데, TurboFlux에서는 반대로 "**각 데이터 노드마다 어떤 쿼리 노드가 매칭 가능한지를 저장해보자**"고 합니다. 이것이 바로 "**Data-centric method**" 입니다!

즉, 데이터 노드마다 매칭할 수 있는 "candidate query vertices"를 관리합니다.
그리고 매핑을 계산하는 중간 결과 역시 "data-centric" 방식으로 저장한다고 합니다.

## DCG Graph Nodes

이어지는 부분부터 쿼리 그래프 $q$를 사이클을 제거해 쿼리 트리 $q'$로 만듭니다. 만약 쿼리 그래프에 사이클이 있는 경우는 논문의 후반후에 다룰거라고 합니다.

![](/images/others/2025-graduation-research/TurboFlux/DCG-g0-fig1,2.png){: .fill .align-center style="min-width: 300px; width: 100%" }

위의 그림은 DCG 그래프의 예시 입니다. 보면, 데이터 중심 그래프이기 때문에 데이터 그래프 $g_0$의 노드가 모두 그대로 존재합니다.

## DCG Graph Edges

![](/images/others/2025-graduation-research/TurboFlux/DCG-edge-states.png){: .fill .align-center style="min-width: 300px; width: 100%" }
논문 저자의 [발표 영상](https://youtu.be/0BAyhMs5ggg?si=vJu4HkiY8yW93hGo)에서 가져와서 화질이 좀 안 좋습니다 ㅠㅠ
{: .align-caption .text-center .small .gray }

DCG의 각 엣지는 $(v, u', v')$의 형태로 표현 되는데, 이것은 아래 3가지 상태를 가질 수 있습니다.

- Explicit
- Implicit
- NULL

Explicit과 Implicit 둘다 $v'$에 대한 후보 쿼리 노드 $u'$가 존재한다는 것을 나타냅니다. 이때, 후보 쿼리 노드가 존재하려면,

- 데이터 그래프의 시작 노드 $v_s$에서 $v$를 거쳐 $v'$까지 도달하는 경로
- 쿼리 그래프의 시작 노드 $u_s$에서 $P(u')$를 거쳐 $u'$까지 도달하는 경로

두 경로가 같은 구조(노드 레이블, 거쳐온 노드 갯수와 순서)를 가지고 있음을 의미 합니다.

이것을 아래와 같이 표기할 수 있습니다.

$$
\begin{aligned}
v_s &\rightarrow v.v' \\
&\equiv \\
u_s &\rightarrow P(u').u'
\end{aligned}
$$

그리고 Explicit과 Implicit의 차이는

![](/images/others/2025-graduation-research/TurboFlux/explicit-edge.png){: .fill .align-center style="min-width: 300px; width: 80%" }
논문 저자의 [발표 영상](https://youtu.be/0BAyhMs5ggg?si=vJu4HkiY8yW93hGo)에서 가져와서 화질이 좀 안 좋습니다 ㅠㅠ
{: .align-caption .text-center .small .gray }

$u'$의 모든 서브 트리가 $v'$의 서브 트리와 매칭 된다면, **Explicit**이라고 분류 하고,

![](/images/others/2025-graduation-research/TurboFlux/implicit-edge.png){: .fill .align-center style="min-width: 300px; width: 80%" }
논문 저자의 [발표 영상](https://youtu.be/0BAyhMs5ggg?si=vJu4HkiY8yW93hGo)에서 가져와서 화질이 좀 안 좋습니다 ㅠㅠ
{: .align-caption .text-center .small .gray }

$u'$의 서브 트리 중에 $v'$의 서브 트리와 매칭 되지 않는 것이 있다면, **Implicit**이라고 분류 합니다.

논문의 다이어그램에서는 이를 Edge 색깔로 구분하는데, <span style="color: #8a2be2; font-weight: bold;">Explicit이면 보라색 실선</span>, <span style="color: #9acd32; font-weight: bold; text-decoration: dashed underline;">Implicit이면 연두색 점선</span>으로 그려집니다.

참고로 NULL 엣지는 가상의 상태입니다. 이것은 데이터 노드와 매칭 되는 쿼리 노드가 전혀 없거나 아직 매칭 되는 쿼리 노드를 찾지 못 했음을 표현합니다.




### Example

![](/images/others/2025-graduation-research/TurboFlux/DCG-g0-fig1,2.png){: .fill .align-center style="min-width: 300px; width: 100%" }

다시 캡처를 가져와서 DCG 구조를 이해해봅시다.

먼저 Explicit 엣지부터 살펴봅시다. $u_0 \rightarrow u_1.u_2$ 경로는 $v_0 \rightarrow v_2.v_4$ 경로와 매칭 됩니다. 그리고 $u_2$와 $v_4$ 둘다 서브 트리가 없는 리프 노드 입니다. 따라서, $(v_2, u_2, v_4)$는 DCG에서 Explicit 엣지가 됩니다.


Implicit 엣지도 살펴봅시다. $u_0 \rightarrow u_1.u_3$ 경로와 $v_0 \rightarrow v_2.v_{104}$ 경로와 매칭 됩니다. 그러나, $u_3$에는 서브 트리 $(u_3, u_4)$가 있고, $v_{104}$에는 서브 트리가 없습니다. 따라서, $(v_2, u_3, v_{104})$는 DCG에서 Implicit 엣지가 됩니다. 이는 $v_{104}$가 $u_3$와 부분적으로만 매칭 되며, 추가 검증이 필요함을 의미 합니다.

![](/images/others/2025-graduation-research/TurboFlux/DCG-g2-fig2.png){: .fill .align-center style="min-width: 300px; width: 50%" }

위의 그림은 매칭이 존재하는 상황 입니다. Positive 매칭은 데이터 노드의 루트 노드인 $v_s$에 매칭된 $u_0$ 엣지가 "Explicit"이 되면 매칭이 발생합니다!

DCG에서 엣지는 $(v, u', v')$ 형태로 표현 됩니다. 그런데, 데이터 노드의 $v_s$가 쿼리 노드의 $u_s$와 매칭 될 때는 가상의 데이터 노드 $v_s^\ast$가 사용 됩니다. 그래서 시작 노드에 대한 매칭은 $(v_s^\ast, u_s, v_s)$로 표현 합니다. 하지만, $v_s^\ast$는 가상의 노드이기 때문에 DCG 그래프에 해당 노드를 저장하거나 시각화 하지는 않습니다.

### Compare Space Complexity

논문의 초반에 DCG는 SJ-Tree 보다 더 좋은 공간 복잡도를 가진다고 하였습니다. 실제로 논문의 Fig 2를 기준으로 두 자료구조가 저장하는 데이터를 비교해보면,

- DCG of $g_0$
  - 213개의 엣지를 저장
- SJ-Tree of $g_0$
  - 11,311개의 부분 솔루션을 저장

이것은 DCG가 SJ-Tree보다 훨신 적은 공간을 사용하면서도 매칭 정보를 유지할 수 있음을 뒷받침 합니다.

### Bitmap Representation

DCG 자료구조에서 각 데이터 노드는 "쿼리 그래프의 노드 수 만큼" 비트맵을 가집니다. 이 비트맵의 $i$번째 비트는 쿼리 노드 $u_i$에 대응되고, $i$번째 비트맵의 값이 `true`라면, 해당 데이터 노드와 $u_i$ 노드 사이에 Implicit 엣지가 존재한다고 표시 합니다.

다만, Explicit 엣지는 이 비트맵이 아니라 별도의 자료구조에 저장한다고 합니다!







---


DCG는 minimal representation이다?

- Uses the idea of TurboIso, and modifies it to solve continuous subgraph matching efficiently
- Maintains an auxiliary data structure called "Data-centric Graph(DCG)" to maintain the intermediate results efficiently.



