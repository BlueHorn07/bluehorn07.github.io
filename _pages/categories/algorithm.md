---
title: "Algorithm"
permalink: /categories/algorithm
toc: true
toc_sticky: true
---

🔥 2020-1학기 POSTECH 안희갑 교수님의 『Algorithm(CSED331)』을 수강하면서 공부한 내용을 정리하였습니다. 😁

PS에 대한 아티클은 [요기]({{"/category/problem-solving" | relative_url}})👀에서 확인하실 수 있습니다 😎

알고리즘 정리를 마무리한 후기는 [요기]({{"/2022/05/21/end-of-algorithm-lecture" | relative_url}})에서 확인하실 수 있습니다.

정규수업에서 다루지 않은 내용은 🎈로 표시하였습니다 😉
{: .notice}

## Computational Complexity

- [Asymptotic Analysis]({{"/2021/05/14/asymptotic-analysis" | relative_url}})
  - Big-O / Big-Omega / Big-Theta
  - little-o / little-omega / little-theta
- [Master Theorem: Recurrence relations]({{"/2021/02/26/divide-and-conquer#master-theorem-recurrence-relations" | relative_url}})
- [Fibonacci Number]({{"/2021/05/15/fibonacci-number" | relative_url}})
  - Brute Force
  - DP
  - Matrix-based
- [Convex Hull Algorithm]({{"/2021/05/15/convex-hull-algorithm" | relative_url}})
  - Brute Force
  - Graham's Scan

## [Divide and Conquer]({{"2021/02/26/divide-and-conquer" | relative_url}})

- [Multiplication Algorithm: Karatsuba Algorithm]({{"2021/02/26/multiplication-algorithm" | relative_url}})
- [Binary Search]({{"2021/02/27/binary-search" | relative_url}})
  - Off-by-One
  - Approximate Matches
- [Merge Sort]({{"/2021/02/27/merge-sort" | relative_url}})
  - [Skyline Problem]({{"/2021/09/25/skyline-problem" | relative_url}}) 🎈
- [Matrix Multiplication: Strassen Algorithm]({{"/2021/10/19/matrix-multiplication-strassen-algorithm" | relative_url}})
- [Quick Selection]({{"/2021/10/21/quick-selection" | relative_url}})
- [Closest pair of points]({{"/2021/10/22/closest-pair-of-points" | relative_url}})

## Graph Algorithm

- [DFS and BFS]({{"/2021/03/12/dfs-and-bfs" | relative_url}})
  - [DFS and BFS - code]({{"/2021/03/13/dfs-and-bfs-code" | relative_url}})
- [DFS Tree]({{"/2021/03/13/dfs-tree" | relative_url}})
- [Dijkstra's Algorithm]({{"/2021/04/17/dijkstra-algorithm" | relative_url}})
- [Bellman-Ford Algorithm]({{"/2021/04/18/Bellman-Ford" | relative_url}})
- [DAG & Topological Sort]({{"/2021/04/19/directed-acyclic-graph" | relative_url}})

## [Greedy Algorithm]({{"/2021/04/19/greedy-algorithm" | relative_url}})

- MST; Minimum Spanning Tree
  - [Kruskal's Algorithm]({{"/2021/04/19/kruskal-and-prim-algorithm#kruskals-algorithm" | relative_url}})
  - [Prim's Algorithm]({{"2021/04/19/kruskal-and-prim-algorithm#prims-algorithm" | relative_url}})
  - [Disjoint Set & Path Compression]({{"/2021/10/26/disjoint-set-and-path-compression" | relative_url}})
- [Intervel Scheduling & Partitioning]({{"/2021/04/20/interval-scheduling-and-partitioning" | relative_url}})
- [Huffman Encoding]({{"/2021/10/08/Huffman-encoding" | relative_url}})
- [Clustering of Maximum Spacing]({{"/2021/10/29/clustering-of-maximum-spacing" | relative_url}})

## [Dynamic Programming]({{"/2021/04/20/dynamic-programming" | relative_url}})

- [LIS; Longest Incresaing Subsequences]({{"/2021/04/20/longest-increasing-subsequences" | relative_url}})
- [Edit Distance]({{"/2021/04/20/edit-distanace" | relative_url}})
  - [Dameraus-Levenshtein Distance]({{"/2021/04/24/Damerau-Levenshtein-distance" | relative_url}}) 🎈
- [Knapsack]({{"/2021/04/30/kanpsack" | relative_url}})
- [Chain Matrix Multiplication]({{"/2021/05/02/chain-matrix-multiplication" | relative_url}})
- [Shortest Reliable Paths]({{"/2021/06/13/shortest-reliable-paths" | relative_url}})
- [All Pairs Shortest Paths; Floyd-Warshall]({{"/2021/06/13/all-pairs-shortest-paths" | relative_url}})
- [TSP; Traveling Salesman Problem]({{"/2021/06/13/traveling-salesman-problem" | relative_url}})
  - 완전탐색
  - DP
- [Independent Sets in Tree]({{"/2021/07/10/independent-sets-in-tree" | relative_url}})
- [Weighted Interval Scheduling]({{"/2021/07/12/weighted-interval-scheduling" | relative_url}})
- [Segmented Least Squares]({{"/2021/07/12/segmented-least-squares" | relative_url}})

## Linear Programming

- [Linear Programming]({{"2021/10/30/linear-programming" | relative_url}})
- [Simplex Method]({{"/2021/11/16/simplex-method" | relative_url}})

## [Network Flow]({{"/2021/07/16/network-flow" | relative_url}})

- [Max-Flow Min-Cut Theorem]({{"/2021/07/16/network-flow#residual-network" | relative_url}})
- [Ford-Fulkerson Algorithm & Edmons-Karp Algorithm]({{"/2021/10/03/ford-fulkerson-algorithm-and-edmons-karp-algorithm" | relative_url}})
- [Bipartite Matching]({{"/2021/10/04/bipartite-matching" | relative_url}})
- Variations of Network Flow Problem
- Dinic Algorithm 🎈

## NP & NP-complete

- [P and NP]({{"/2022/01/14/P-and-NP" | relative_url}})
  - [Satisfiability (SAT)]({{"/2022/05/07/satisfiability" | relative_url}})
  - [Traveling Salesman Problem (TSP)]({{"/2021/06/13/traveling-salesman-problem" | relative_url}})
  - [Hamilton Cycle Problem (HCP)]({{"/2022/03/12/hamilton-cycle-problem" | relative_url}})
  - [Balanced Cut]({{"/2022/05/07/balanced-cut" | relative_url}})
  - [3D Matching]({{"/2022/05/07/3D-matching" | relative_url}})
  - Integer Linear Programming 🎈
  - [Independent Set, Vertex Cover, Clique]({{"/2022/05/08/independent-set-and-vertex-cover-and-clique" | relative_url}})
  - [Longest Path, Subset Sum]({{"/2022/05/08/longest-path-and-subset-sum" | relative_url}})

- [Reductions]({{"/2022/05/08/reduction-1" | relative_url}})
  - [Reduction and NP-complete]({{"/2022/05/08/reduction-1" | relative_url}})
    - Mapping Reduction & Polynomial Reduction 🎈
    - [NP-complete and NP-hard]({{"/2022/05/10/NP-complete-and-NP-hard" | relative_url}})
  - [Reduction (2)]({{"/2022/05/12/reduction-2" | relative_url}})
    - 3-SAT → Independent-Set
    - Independent-Set → Vertex Cover
    - Independent-Set → Clique
    - 3-SAT → Clique
  - [Reduction (3)]({{"/2022/05/13/reduction-3" | relative_url}})
    - 3-SAT → 3D-Matching
  - [Reduction (4): Circuit-SAT and Cook-Levin Theorem]({{"/2022/05/14/reduction-4" | relative_url}})

## [Coping with NP-hardness]({{"/2022/05/18/coping-with-np-hardness" | relative_url}})

- Exhaustive Search
  - [Backtracking]({{"/2022/05/19/bacaktracking" | relative_url}})
  - [Branch-and-Bound]({{"/2022/05/20/branch-and-bound" | relative_url}})
- Heuristic Algorithm
  - [Local Search]({{"/2022/05/20/local-search" | relative_url}})

## Appendix

- [Amortized Analysis]({{"/2021/05/08/amortized-analysis" | relative_url}}) 🎈
  - Accounting Method
- [Implementations of Heap]({{"/2021/05/03/implementations-of-heap" | relative_url}}) 🎈
  - Heap by unordered array & ordered array
  - Binary Heap
  - [d-ary Heap]({{"/2021/05/03/implementations-of-heap#d-ary-heap" | relative_url}})
  - [Binomial Heap]({{"/2021/05/03/implementations-of-heap#binomial-heap" | relative_url}})
    - Binomial Tree
  - [Lazy-Binomial Heap]({{"2021/05/03/implementations-of-heap#lazy-binomial-heap" | relative_url}})
  - [Fibonacci Heap]({{"/2021/05/03/implementations-of-heap#fibonacci-heap" | relative_url}}) 🔥
- FFT; Fast Fourier Transformation 🎈

## 참고 교재
- 『Algorithms』 Dasgupta, international ed.
- [『알고리즘 문제해결전략』](https://book.algospot.com/) 구종만
