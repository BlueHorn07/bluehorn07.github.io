---
title: "Coping with NP-hardness"
toc: true
toc_sticky: true
categories: ["Algorithm"]
---



2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr/>

"P and NP" 챕터에서 $\textbf{NP-hard}$, $\textbf{NP-complete}$ 문제를 풀기 위한 빠른 알고리즘이 아직 존재하지 않음을 살펴보았다. 그러나 우리가 마주하는 문제를 선택할 순 없기에, 어떤 경우에는 $\textbf{NP-hard}$ 문제를 직접 풀어야 한다. 이 경우 어떻게 할 것인가? 그저 '아~~ 그 녀석들은 아무리 노력해도 빠르게 만들 수 없어! 그 녀석은 $\textbf{NP-hard}$ 문제거든!' 하면서 넋 놓고 있을 것인가? Nope, 몇 가지 조건들을 희생하면 조금 더 빠른 방법으로 해를 찾을 수 있다!

- Optimality of the solution
- Polynomial running time of the algorithm
- Arbitrary instances of the problem

<br/>

이번 챕터에서는 $\textbf{NP-hard}$ 문제를 다루는 몇 가지 방법을 알아본다.

1. Exhausitive Search (Exponential Search)
   1. [Backtracking]({{"/2022/05/19/bacaktracking.html" | relative_url}})
   2. [Branch-and-Bound]({{"/2022/05/20/branch-and-bound.html" | relative_url}})
2. Approximation Algorithm
3. Heuristic Algorithm
   1. [Local Search]({{"/2022/05/20/local-search.html" | relative_url}})
      1. Simulated Annealing

책에서는 3가지 방식이 제시되었는데, 정규 수업에서는 첫번째 Exhausitive Search와 세번째 Heuristic Algorithm만 다뤘다 🙏

