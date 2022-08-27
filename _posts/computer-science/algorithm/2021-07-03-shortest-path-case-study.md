---
title: "Shortest Path Case Study"
layout: post
use_math: true
tags: ["Algorithm"]
---



2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

\<최단경로(shortest path)\>라는 주제로 생각해볼 수 있는 여러 경우를 정리해보았습니다 😎

<hr/>

### Source And Destination

<div class="statement" markdown="1">

1. Single source and single destination shortest path problem
2. Single source shortest path problem
3. Single destination shortest path problem
4. All pairs shortest path problem

</div>

<big>1. Single source and single destination shortest path problem</big>

간단하다. 그냥 [\<Dijkstra Algorithm\>](https://bluehorn07.github.io/computer_science/2021/04/17/dijkstra-algorithm.html)으로 해결하면 된다 😉

사실 2번째 케이스인 "Single source shortest path problem"도 \<Dijkstra Algorithm\>을 돌리면 된다 ㅋㅋㅋ

<br/>

<big>3. Single destination shortest path problem</big>

사실 이것도 \<Dijkstra Algorithm\>으로 해결 된다 ㅋㅋㅋ 그래프의 directed edge의 방향을 모두 반대로 바꾼 후, destination을 출발점으로 삼아서 "2. single source shortest path problem"을 풀면 된다! 😉

<br/>

<big>4. All pairs shortest path problem</big>

이 녀석은 \<Dijkstra Algorithm\>이 아닌 [\<Floyd-Warshall Algorithm\>](https://bluehorn07.github.io/computer_science/2021/06/13/all-pairs-shortest-paths.html)으로 풀어야 한다! DP로 구현하며, 자세한 내용을 포스트를 읽어보자! 🎈

<hr/>

("Shortest Path에 제약 조건이 들어간 경우"는 추후 추가 예정 🎈)


