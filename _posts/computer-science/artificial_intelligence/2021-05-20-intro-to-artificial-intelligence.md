---
title: "Introduction to AI"
layout: post
tags: ["Artificial Intelligence"]
---


2020-1학기, 대학에서 '인공지능' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

이번 포스트는 대학에서 들은 "인공지능(CSED442)" 수업에서 다루는 내용의 개요를 다룹니다 😁

<hr/>

Machine Learning에는 크게 4가지 모델이 있다.

1. [Reflex-based Model](#reflex-based-model)
2. [State-based Model](#state-based-model)
3. [Variable-based Model](#variable-based-model)
4. [Logic-based Model](#logic-based-model)

"인공지능(CSED422)" 정규 수업에서는 1, 2, 3번 모델에 대해 주요하게 살펴본다 👀

<hr/>

### Reflex-based Model

데이터가 주어지면, 즉각적으로 판단하는 모델이다.

개-고양이 분류, 리뷰의 긍정/부정 여부를 판단하는 \<sentiment analysis\>가 여기에 속한다.

\<Linear Model\>과 \<Neural Network\>의 기본적인 형태가 여기에 속한다.

- Linear Model
  - Linear Regression
  - Linear Classifier
- Neural Network
  - Single-layer Perceptron
  - Multi-layer Perceptron
- Nearest-Neighborhood Model
  - KNN

<hr/>

### State-based Model

"state graph"에서 최적의 action sequence를 찾는 모델이다. 이때, \<**state**\>란 <span class="half_HL">미래의 action을 결정하기 위해 필요한 과거에 대한 정보를 담고 있는 것</span>이다.

\<State-based Model\>의 경우, graph 또는 tree를 기반으로 하기 때문에 적절한 \<search algorithm\>을 선택해야 한다. 그래서 DFS/BFS, Dijkstra, A* Algorithm 등을 폭넓게 사용한다.

모음/공백이 없는 문자에 모음/공백을 넣는 \<text reconstruction\>이나 \<blackjack\>, \<chess\>, \<Pac-Man\>과 같은 게임의 인공지능을 만드는 데에도 쓰는 모델이다.

<div class="img-wrapper" style="display:flex; justify-content:center; align-items:center;">
    <img src="{{ "/images/artificial-intelligence/intro-to-AI-1.png" | relative_url }}" style="float:left; width:48%;">
    <img src="{{ "/images/artificial-intelligence/intro-to-AI-2.png" | relative_url }}" style="float:left; width:48%;">
</div>

<hr/>

### Variable-based Model

"variable"의 모음에 적절한 value를 부여(assign)하는 모델이다. 이때, "제약(constraint)"가 있으며, 이 제약을 만족하면서 "variable"에 적절한 값을 부여해야 한다. \<**CSP; Contraint Satisfaction Problem**\>가 대표적인 \<Varibale-based Model\>의 주요한 문제 해결 대상이다!

\<Map Coloring\>, \<Event Scheduling\>, \<Bayesian Network\> 등을 이 모델로 해결할 수 있다.

<div class="img-wrapper">
  <img src="{{ "/images/artificial-intelligence/intro-to-AI-3.png" | relative_url }}" width="300px">
</div>

<hr/>

### Logic-based Model

주어진 명제들을 바탕으로 "논리적 추론(logical inference)"를 수행하는 모델이다.

정규 수업에서는 다루지 않았다.



