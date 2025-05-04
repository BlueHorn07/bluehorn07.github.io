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

지난 포스트부터 TurboIso 논문을 읽고 내용을 정리하고 있습니다 🏃‍♂️
논문 도입부에서 "Matching Order가 중요하다!"라고 지적 했던 것이 기억 나는데요!
이번 포스트에서는 TurboIso 논문에서 제시한 Matching Order에 대해서 정리해보겠습니다.

# Matching Order Selection



