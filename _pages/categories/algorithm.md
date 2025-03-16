---
title: "Algorithm"
permalink: /categories/algorithm
toc: true
toc_sticky: true
---

🔥 2020-1학기 POSTECH 안희갑 교수님의 "**Algorithm(CSED331)**" 수업을 수강하면서 공부한 내용을 정리하였습니다. 😁

PS에 대한 아티클은 ["Problem Solving" 페이지](/category/problem-solving)👀에서 확인하실 수 있습니다 😎

알고리즘 정리를 마무리한 후기는 [요기](/2022/05/21/end-of-algorithm-lecture)에서 확인하실 수 있습니다.

정규수업에서 다루지 않은 내용은 🎈로 표시하였습니다 😉
{: .notice}

## Computational Complexity

- [Asymptotic Analysis](/2021/05/14/asymptotic-analysis)
  - Big-O / Big-Omega / Big-Theta
  - little-o / little-omega / little-theta
- [Master Theorem: Recurrence relations](/2021/02/26/divide-and-conquer#master-theorem-recurrence-relations)
- [Fibonacci Number](/2021/05/15/fibonacci-number)
  - Brute Force
  - DP
  - Matrix-based
- [Convex Hull Algorithm](/2021/05/15/convex-hull-algorithm)
  - Brute Force
  - Graham's Scan

## [Divide and Conquer](2021/02/26/divide-and-conquer)

- [Multiplication Algorithm: Karatsuba Algorithm](2021/02/26/multiplication-algorithm)
- [Binary Search](2021/02/27/binary-search)
  - Off-by-One
  - Approximate Matches
- [Merge Sort](/2021/02/27/merge-sort)
  - [Skyline Problem](/2021/09/25/skyline-problem) 🎈
- [Matrix Multiplication: Strassen Algorithm](/2021/10/19/matrix-multiplication-strassen-algorithm)
- [Quick Selection](/2021/10/21/quick-selection)
- [Segment Tree](/2022/07/17/segment-tree/) 🎈
- [Closest pair of points](/2021/10/22/closest-pair-of-points)

## Graph Algorithm

- [DFS & BFS](/2021/03/12/dfs-and-bfs)
  - [DFS & BFS 구현](/2021/03/13/dfs-and-bfs-implementation)
- [DFS Tree](/2021/03/13/dfs-tree)
- [Dijkstra's Algorithm](/2021/04/17/dijkstra-algorithm)
- [Bellman-Ford Algorithm](/2021/04/18/Bellman-Ford)
- [DAG & Topological Sort](/2021/04/19/directed-acyclic-graph)

## [Greedy Algorithm](/2021/04/19/greedy-algorithm)

- MST; Minimum Spanning Tree
  - [Kruskal's Algorithm](/2021/04/19/kruskal-and-prim-algorithm#kruskals-algorithm)
  - [Prim's Algorithm](2021/04/19/kruskal-and-prim-algorithm#prims-algorithm)
  - [Disjoint Set & Path Compression](/2021/10/26/disjoint-set-and-path-compression)
- [Interval Scheduling & Partitioning](/2021/04/20/interval-scheduling-and-partitioning)
- [Huffman Encoding](/2021/10/08/Huffman-encoding)
- [Clustering of Maximum Spacing](/2021/10/29/clustering-of-maximum-spacing)

## [Dynamic Programming](/2021/04/20/dynamic-programming)

- [LIS; Longest Increasing Subsequences](/2021/04/20/longest-increasing-subsequences)
- [Edit Distance](/2021/04/20/edit-distanace)
  - [Dameraus-Levenshtein Distance](/2021/04/24/Damerau-Levenshtein-distance) 🎈
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

## Linear Programming

- [Linear Programming](2021/10/30/linear-programming)
- [Simplex Method](/2021/11/16/simplex-method)

## [Network Flow](/2021/07/16/network-flow)

- [Max-Flow Min-Cut Theorem](/2021/07/16/network-flow#residual-network)
- [Ford-Fulkerson Algorithm & Edmons-Karp Algorithm](/2021/10/03/ford-fulkerson-algorithm-and-edmons-karp-algorithm)
- [Bipartite Matching](/2021/10/04/bipartite-matching)
- Variations of Network Flow Problem
- Dinic Algorithm 🎈

## NP & NP-complete

- [P and NP](/2022/01/14/P-and-NP)
  - [Satisfiability (SAT)](/2022/05/07/satisfiability)
  - [Traveling Salesman Problem (TSP)](/2021/06/13/traveling-salesman-problem)
  - [Hamilton Cycle Problem (HCP)](/2022/03/12/hamilton-cycle-problem)
  - [Balanced Cut](/2022/05/07/balanced-cut)
  - [3D Matching](/2022/05/07/3D-matching)
  - Integer Linear Programming 🎈
  - [Independent Set, Vertex Cover, Clique](/2022/05/08/independent-set-and-vertex-cover-and-clique)
  - [Longest Path, Subset Sum](/2022/05/08/longest-path-and-subset-sum)

- [Reductions](/2022/05/08/reduction-1)
  - [Reduction and NP-complete](/2022/05/08/reduction-1)
    - Mapping Reduction & Polynomial Reduction 🎈
    - [NP-complete and NP-hard](/2022/05/10/NP-complete-and-NP-hard)
  - [Reduction (2)](/2022/05/12/reduction-2)
    - 3-SAT → Independent-Set
    - Independent-Set → Vertex Cover
    - Independent-Set → Clique
    - 3-SAT → Clique
  - [Reduction (3)](/2022/05/13/reduction-3)
    - 3-SAT → 3D-Matching
  - [Reduction (4): Circuit-SAT and Cook-Levin Theorem](/2022/05/14/reduction-4)

## [Coping with NP-hardness](/2022/05/18/coping-with-np-hardness)

- Exhaustive Search
  - [Backtracking](/2022/05/19/bacaktracking)
  - [Branch-and-Bound](/2022/05/20/branch-and-bound)
- Heuristic Algorithm
  - [Local Search](/2022/05/20/local-search)

## Appendix

- [Amortized Analysis](/2021/05/08/amortized-analysis) 🎈
  - Accounting Method
- [Implementations of Heap](/2021/05/03/implementations-of-heap) 🎈
  - Heap by unordered array & ordered array
  - Binary Heap
  - [d-ary Heap](/2021/05/03/implementations-of-heap#d-ary-heap)
  - [Binomial Heap](/2021/05/03/implementations-of-heap#binomial-heap)
    - Binomial Tree
  - [Lazy-Binomial Heap](2021/05/03/implementations-of-heap#lazy-binomial-heap)
  - [Fibonacci Heap](/2021/05/03/implementations-of-heap#fibonacci-heap) 🔥
- FFT; Fast Fourier Transformation 🎈

## 참고 교재
- 『Algorithms』 Dasgupta, international ed.
- [『알고리즘 문제해결전략』](https://book.algospot.com/) 구종만
