---
title: "Paper Reading: SymBi, Find Matching"
toc: true
toc_sticky: true
categories: ["Graduation Research"]
excerpt: ""
---

2025년 저는 데이터베이스 랩에 컨택하여 학부 졸업 연구 주제를 받아서 진행하고 있습니다. 저의 주제는 "Continuous Subgraph Matching"과 관련해 코드를 최적화 하고 개선하는 것으로 그래프 쿼리 관련 논문을 읽고, C++ 코드를 튜닝하고 있습니다. 졸업 마지막 학기에 듣는 수업인데 많은 노하우와 경험을 쌓고 졸업하기를 기대하고 있습니다 ㅎㅎ
{: .notice }

# Matching Algorithm

DCS 구조 위에서 모든 positive/negative 매칭을 찾는 방법에 대해서 살펴봅니다.

아이디어는 "Partial Embedding"에서 시작해 그게 "Full Embedding"이 될때까지 점점 키워가는 전략 입니다.

# Extendable vertex

Partial Embedding을 확장할 때, 아무 노드만 선택할 수는 없습니다. 그래서 Embedding 확장에서 선택할 수 있는 노드를 "Extendable vertex"라고 정의 합니다.

<div class="definition" markdown="1">

Given a partial embedding $M$, a vertex $u$ of query graph $q$ is called "extendable"

1. if $u$ is not mapped to a vertex of $g$ in $M$
2. *at least* one neighbor of $u$ is mapped to a vertex of $g$ in $M$

</div>

정의는 아주 직관적인데, (1) 아직 매핑되지 않은 노드 여야 하고, (2) 이미 매핑된 녀석과 연결 되어 있어야 합니다.

# Extendable Candidates $C_M(u)$

Partial Embedding을 확장할 쿼리 노드를 고른 다음에는 그 쿼리 노드를 데이터 그래프의 노드 중 어떤 녀석에 매핑할지도 결정해야 합니다.

일단 매핑 가능한 데이터 노드도 여러 개 있을 텐데, 그걸 "Extendable Candidate"라고 부릅니다.

<div class="theorem" markdown="1">

Given a query vertex $u$, a data vertex $v$ is its "extendable candidate"

if $v$ satisfies the following conditions

- $D_2[u, v] = 1$
  - i.e. it's not filtered in the DCS structure
- For all matched neighbors $u'$ of $u$, $(M(u'), v) \in E(g)$
  - i.e. preserve the connectivity

</div>

이 정의도 아주 직관적이다. 일단 우리가 DCS에서 열심히 구한 $D_2$ 값이 1이어야 한다. 지금까지 모두 이걸 위해서 $N^2_C$, $$N^2_{CC}$$를 정의한 것이다 ㅋㅋ

그리고 지금까지 Partial Embedding에 있던 쿼리 노드가 매핑한 데이터 노드 $M(u')$와 새로 매핑하려는 데이터 노드 $v$ 사이의 연결성이 데이터 엣지로 존재해야 한다. (당연 ㅋㅋ)

<br/>

이 Extendable Candidates를 $C_M(u)$라고 표기하고, 수학적으로 아래와 같이 적습니다.

$$
C_M(u)
= \left\{
v \; : \;
v \in C(u)
\quad \text{and} \quad
D_2[u, v] = 1
\quad \text{and} \quad
\forall u' \in \text{Nbr}_M(u), \; (M(u'), v) \in E(g)
\right\}
$$

표기에서 $\text{Nbr}_M(u)$는 쿼리 노드 $u$와 이웃한 노드 중에서 이미 매핑 $M$에 포함된 것들을 말합니다.

점점 등장하는 기호들이 많아지면서 논문을 읽을 때 헷갈리더라구요 ㅋㅋ 명확히 하기 위해 정리하면

- 당연히 $\text{Nbr}_M(u) \subseteq \text{Nbr}(u)$ 입니다.
- 그리고 당연히 $C_M(u) \subseteq C(u)$ 입니다.


# Find Matches

그래서 Partial Embedding을 확장하는 절차를 간단히 기술하면 아래와 같습니다.

<div class="proof" markdown="1">

1. 주어진 Partial Embedding에서 확장 가능한 모든 쿼리 노드를 찾습니다.
2. 그중 하나의 쿼리 노드를 선택합니다.
   1. 이때, 확장 가능한 여러 쿼리 노드 중에서 어떤 걸 선택할지도 중요 합니다.
   2. 랜덤하게 선택할 수도 있고,
   3. 가치를 평가한 후 "Matching Order"를 부여하여 선택할 수도 있습니다.
3. 확장할 쿼리 노드를 선택 했다면, 데이터 그래프에서도 "확장할 후보"들을 찾는다.
4. 확장할 후보 중에서 하나를 선택하고, 이를 Partial Embedding에 포함하여 확장 시킨다.
5. [1-4] 과정을 반복 한다.

</div>

논문에서는 아래와 같이 코드를 제시 합니다.

![](/images/others/2025-graduation-research/SymBi/algo-5-find-matches.png){: .fill .align-center style="min-width: 300px; width: 80%" }

지금은 이 과정이 재귀의 형태로 진행된다는 것만 확인 합니다.

# Extendable Partial Candidates

Extendable Candidate $C_M(u)$는 쿼리 노드 $u$가 주어지면, 이것에 대한 매핑 가능한 데이터 노드를 찾은 것 이었습니다.

$$
C_M(u)
= \left\{
v \; : \;
v \in C(u)
\quad \text{and} \quad
D_2[u, v] = 1
\quad \text{and} \quad
\forall u' \in \text{Nbr}_M(u), \; (M(u'), v) \in E(g)
\right\}
$$

이것을 변형해서 아래와 같은 보조 집합을 정의 합니다.

논문에서는 $S[u, u']$가 아니라 $S_{u'}$라고 표기하지만, 저는 표기가 너무너무 헷갈려서 이 부분도 저만의 표기로 작성하겠습니다. 그리고 이름도 논문에서는 부여한 적이 없지만 저는 지칭을 좀 하고 싶어서 "Extendable Partial Candidate"라고 이름 붙였습니다. (길다길다...)
{: .notice--warning}

<div class="definition" markdown="1">

쿼리 노드 $u$와 그것의 이웃 $u' \in \text{Nbr}_M(u)$가 있을 때, 매핑 가능한 데이터 노드의 집합을 아래와 같이 정의한다.

$$
S[u, u']
= \left\{
v \; : \;
v \in C(u)
\quad \text{and} \quad
D_2[u, v] = 1
\quad \text{and} \quad
(M(u'), v) \in E(g)
\right\}
$$

</div>

즉, $C_M(u)$를 매핑된 쿼리 노드 $u'$ 별로 분할한 것이 $S[u, u']$ 입니다.

$$
\forall u' \in \text{Nbr}_M(u), \quad S[u, u'] \subseteq C_M(u)
$$

그리고

$$
C_M(u) = \bigcup_{u' \in \text{Nbr}_M(u)} S[u, u']
$$

여기에서 가장 작은 크기를 갖는 "Extendable Partial Candidate"를 찾아서 그 이웃 쿼리 노드 $u'$를 $u_{min}$라고 정의 합니다.

$$
u_{min} = \underset{u' \in \text{Nbr}_M(u)}{\text{argmin}} \left\vert S[u, u'] \right\vert
$$

오케이! 그런데 $u_{min}$을 왜 정의한 걸까요...??

## relation with Child Candidate Array $N^2_{CC}$



$$
\vert S[u, u'] \vert
= N^2_{CC} [u', M(u'), u]
$$


<!-- Dynamic Matching Order 쪽 이번에 제대로 이해하고 싶음. -->
