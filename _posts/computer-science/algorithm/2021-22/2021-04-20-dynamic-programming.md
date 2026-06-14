---
title: "Dynamic Programming"
toc: true
toc_sticky: true
categories: ["Algorithm"]
---

2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :) 전체 포스트는 [Algorithm](/categories/algorithm) 포스트에서 확인하실 수 있습니다.

<hr/>

DP는 정말 알고리즘 PS를 하면서, 빠질 수 없는 주제다!! 실제 동작하는 많은 알고리즘이 DP의 테크닉을 사용하고 있으며, 우리는 DP가 가져다주는 효율성을 늘 누리고 있다.

DP의 컨셉은 아래와 같다.

<div class="notice" markdown="1">

1\. Identifying a collection of subproblems.

2\. Tackling them one by one.<br/>
2\.1\. smallest first.<br/>
2\.2\. using the result of small ones to solve the larger ones.<br/>
2\.3\. repeat this until solve the whole problems!

</div>

좀더 추상화한 관점에서 DP를 바라보면, <span class="half_HL">"DAG ordering on btw subproblems"</span>, and <span class="half_HL">"smaller subproblem을 푸는 방식이 bigger subproblem을 푸는 데에 그대로 사용된다"</span>라고 말할 수 있다.

<br/>

DP 테크닉의 중심은 \<Memoization\>이라고 할 수 있다. subproblem의 결과를 바탕으로 problem을 풀기 때문에 당연히 subproblem의 결과를 저장(memo)할 필요가 있고, 이때 쓰는 테크닉이 바로 \<Memoization\>이다. 간단히 말하자면, \<Memoization\>은 그냥 <span class="half_HL">subproblem의 결과를 배열에 저장한다!</span>이다. 이 Memoization을 어떻게 할껀지 고민하는 것 역시 DP에서 중요한 부분이기 때문에 많은 연습이 필요하다.

<hr/>

이번 챕터에서 다루는 문제들은 아래와 같다.

- [LIS; Longest Increasing Subsequences](/2021/04/20/longest-increasing-subsequences)
- [Edit Distance](/2021/04/20/edit-distanace)
- [Knapsack](/2021/04/30/kanpsack)
- [Chain Matrix Multiplication](/2021/05/02/chain-matrix-multiplication)
- [Shortest Reliable Paths](/2021/06/13/shortest-reliable-paths)
- [All Pairs Shortest Paths; Floyd-Warshall](/2021/06/13/all-pairs-shortest-paths)
- [TSP; Traveling Salesman Problem](/2021/06/13/traveling-salesman-problem)
  - 완전탐색
  - DP
- [Independent Sets in Tree](/2021/07/10/independent-sets-in-tree)
- [Weighted Interval Scheduling](/2021/07/12/weighted-interval-scheduling)
- [Segmented Least Squares](/2021/07/12/segmented-least-squares)

(상당히 많은 문제가 DP의 테크닉을 사용한다. 그만큼 DP가 중요하다는 말!)