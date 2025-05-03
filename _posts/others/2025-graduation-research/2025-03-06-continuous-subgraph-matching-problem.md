---
title: "Continuous Subgraph Matching Problem"
toc: true
toc_sticky: true
categories: ["Graduation Research"]
excerpt: ""
---

2025년 저는 데이터베이스 랩에 컨택하여 학부 졸업 연구 주제를 받아서 진행하고 있습니다. 저의 주제는 "Continuous Subgraph Matching"과 관련해 코드를 최적화 하고 개선하는 것으로 그래프 쿼리 관련 논문을 읽고, C++ 코드를 튜닝하고 있습니다. 졸업 마지막 학기에 듣는 수업인데 많은 노하우와 경험을 쌓고 졸업하기를 기대하고 있습니다 ㅎㅎ
{: .notice }

# Introduction

![](/images/others/2025-graduation-research/continuous-subgraph-matching.png){: .fill .align-center style="min-width: 300px; width: 80%" }

지난 포스트에서는 "[Subgraph Matching Problem](/2025/03/05/subgraph-matching-problem/)"에 대해서 살펴보았습니다. 이것은 정적(static) 상황에서 접근하는 문제로 쿼리 그래프와 데이터 그래프 둘다 그 형상이 변하지 않습니다.

"Continuous" Subgraph Matching은 데이터 그래프가 Update Stream $\Delta o_i$에 의해 형상이 변합니다! 그리고 그런 상황 속에서 쿼리 그래프 매칭이 추가로 발생하는지, 삭제 되는지를 빠르게 찾는 것을 목표로 합니다.

<br/>

제일 위에 나와있는 예시에서부터 시작해봅시다. 기존 데이터 그래프에 $\Delta o_1$과 $\Delta o_2$의 그래프 업데이트가 발생하였습니다. 이로 인해 쿼리 그래프의 매칭이 "200"개 생성 되었습니다! CSM 문제는 이런걸 찾는 문제 입니다!

# Graph Update Stream

정의부터 시작합시다!

<div class="definition" markdown="1">

A sequence of update operations $(\Delta o_1, \Delta o_2, \dots, )$

where

$$
\Delta o_i := (\text{op}, v, v')
$$

- $\text{op} = +$
  - edge insertion -> find positive matching
- $\text{op} = -$
  - edge deletion -> find negative matching

</div>

# Related Works

- IncIsoMat (2013)
- GraphFlow (2017)
- SJ-Tree (2015)
- TurboFlux (2018)
- SymBi (2021) ([[1]](/2025/03/07/SymBi-setup-DCS/), [[2]](/2025/03/07/SymBi-update-DCS/), [[3]](/2025/03/08/SymBi-find-matching/))

이 중에서 TurboFlux와 SymBi가 제가 졸업 연구로 참영한 연구실에서 게시한 논문 입니다.
추후에 이 둘도 읽고 내용을 정리하겠습니다.

# 맺음말

이제 본격적으로 "Subgraph Matching"과 "Continuous Subgraph Matching" 문제를 접근한 논문들을 읽고, 내용을 정리해보겠습니다!

