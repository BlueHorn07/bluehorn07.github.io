---
title: "Algorithm"
layout: post
use_math: true
tags: ["algorithm"]
hidden: true
---

<br>

🔥 2020-1학기 POSTECH 안희갑 교수님의 『Algorithm(CSED331)』을 수강하면서 공부한 내용을 정리하였습니다. 😁

PS에 대한 아티클은 [요기]({{"/2021/11/01/problem-solving.html" | relative_url}})👀에서 확인하실 수 있습니다 😎

알고리즘 정리를 마무리한 후기는 [요기]({{"/2022/05/21/end-of-algorithm-lecture.html" | relative_url}})에서 확인하실 수 있습니다.

#### 참고 교재
- 『Algorithms』 Dasgupta, international ed.
- [『알고리즘 문제해결전략』](https://book.algospot.com/) 구종만

<div class="math-statement" markdown="1">

[목차]

- [Computational Complexity](#computational-complexity)
- [Divide and Conquery](#divide-and-conquer)
- [Graph Algorithm](#graph-algorithm)
- [Greedy Algorithm](#greedy-algorithm)
- [Dynamic Programming](#dynamic-programming)
- [Network Flow](#network-flow)
- [Linear Programming](#linear-programming)
- [NP & NP-complete](#np--np-complete)
- [Coping with NP-hardness](#coping-with-np-hardness)
- [Appendix](#appendix)

</div>

<br/>
<hr/>

정규수업에서 다루지 않은 내용은 🎈로 표시하였습니다 😉

### Computational Complexity

- [Asymptotic Analysis]({{"/2021/05/14/asymptotic-analysis.html" | relative_url}})
  - Big-O / Big-Omega / Big-Theta
  - little-o / little-omega / little-theta
- [Master Theorem: Recurrence relations]({{"/2021/02/26/divide-and-conquer.html#master-theorem-recurrence-relations" | relative_url}})
- [Fibonacci Number]({{"/2021/05/15/fibonacci-number.html" | relative_url}})
  - Brute Force
  - DP
  - Matrix-based
- [Convex Hull Algorithm]({{"/2021/05/15/convex-hull-algorithm.html" | relative_url}})
  - Brute Force
  - Graham's Scan

### [Divide and Conquer]({{"2021/02/26/divide-and-conquer.html" | relative_url}})

- [Multiplication Algorithm: Karatsuba Algorithm]({{"2021/02/26/multiplication-algorithm.html" | relative_url}})
- [Binary Search]({{"2021/02/27/binary-search.html" | relative_url}})
  - Off-by-One
  - Approximate Matches
- [Merge Sort]({{"/2021/02/27/merge-sort.html" | relative_url}})
  - [Skyline Problem]({{"/2021/09/25/skyline-problem.html" | relative_url}}) 🎈
- [Matrix Multiplication: Strassen Algorithm]({{"/2021/10/19/matrix-multiplication-strassen-algorithm.html" | relative_url}})
- [Quick Selection]({{"/2021/10/21/quick-selection.html" | relative_url}})
- [Closest pair of points]({{"/2021/10/22/closest-pair-of-points.html" | relative_url}})

### Graph Algorithm

- [DFS and BFS]({{"/2021/03/12/dfs-and-bfs.html" | relative_url}})
  - [DFS and BFS - code]({{"/2021/03/13/dfs-and-bfs-code.html" | relative_url}})
- [DFS Tree]({{"/2021/03/13/dfs-tree.html" | relative_url}})
- [Dijkstra's Algorithm]({{"/2021/04/17/dijkstra-algorithm.html" | relative_url}})
- [Bellman-Ford Algorithm]({{"/2021/04/18/Bellman-Ford.html" | relative_url}})
- [DAG & Topological Sort]({{"/2021/04/19/directed-acyclic-graph.html" | relative_url}})

### [Greedy Algorithm]({{"/2021/04/19/greedy-algorithm.html" | relative_url}})

- MST; Minimum Spanning Tree
  - [Kruskal's Algorithm]({{"/2021/04/19/kruskal-and-prim-algorithm.html#kruskals-algorithm" | relative_url}})
  - [Prim's Algorithm]({{"2021/04/19/kruskal-and-prim-algorithm.html#prims-algorithm" | relative_url}})
  - [Disjoint Set & Path Compression]({{"/2021/10/26/disjoint-set-and-path-compression.html" | relative_url}})
- [Intervel Scheduling & Partitioning]({{"/2021/04/20/interval-scheduling-and-partitioning.html" | relative_url}})
- [Huffman Encoding]({{"/2021/10/08/Huffman-encoding.html" | relative_url}})
- [Clustering of Maximum Spacing]({{"/2021/10/29/clustering-of-maximum-spacing.html" | relative_url}})

### [Dynamic Programming]({{"/2021/04/20/dynamic-programming.html" | relative_url}})

- [LIS; Longest Incresaing Subsequences]({{"/2021/04/20/longest-increasing-subsequences.html" | relative_url}})
- [Edit Distance]({{"/2021/04/20/edit-distanace.html" | relative_url}})
  - [Dameraus-Levenshtein Distance]({{"/2021/04/24/Damerau-Levenshtein-distance.html" | relative_url}}) 🎈
- [Knapsack]({{"/2021/04/30/kanpsack.html" | relative_url}})
- [Chain Matrix Multiplication]({{"/2021/05/02/chain-matrix-multiplication.html" | relative_url}})
- [Shortest Reliable Paths]({{"/2021/06/13/shortest-reliable-paths.html" | relative_url}})
- [All Pairs Shortest Paths; Floyd-Warshall]({{"/2021/06/13/all-pairs-shortest-paths.html" | relative_url}})
- [TSP; Traveling Salesman Problem]({{"/2021/06/13/traveling-salesman-problem.html" | relative_url}})
  - 완전탐색
  - DP
- [Independent Sets in Tree]({{"/2021/07/10/independent-sets-in-tree.html" | relative_url}})
- [Weighted Interval Scheduling]({{"/2021/07/12/weighted-interval-scheduling.html" | relative_url}})
- [Segmented Least Squares]({{"/2021/07/12/segmented-least-squares.html" | relative_url}})

### Linear Programming

- [Linear Programming]({{"2021/10/30/linear-programming.html" | relative_url}})
- [Simplex Method]({{"/2021/11/16/simplex-method.html" | relative_url}})

### [Network Flow]({{"/2021/07/16/network-flow.html" | relative_url}})

- [Max-Flow Min-Cut Theorem]({{"/2021/07/16/network-flow.html#residual-network" | relative_url}})
- [Ford-Fulkerson Algorithm & Edmons-Karp Algorithm]({{"/2021/10/03/ford-fulkerson-algorithm-and-edmons-karp-algorithm.html" | relative_url}})
- [Bipartite Matching]({{"/2021/10/04/bipartite-matching.html" | relative_url}})
- Variations of Network Flow Problem
- Dinic Algorithm 🎈

### NP & NP-complete

- [P and NP]({{"/2022/01/14/P-and-NP.html" | relative_url}})
  - [Satisfiability (SAT)]({{"/2022/05/07/satisfiability.html" | relative_url}})
  - [Traveling Salesman Problem (TSP)]({{"/2021/06/13/traveling-salesman-problem.html" | relative_url}})
  - [Hamilton Cycle Problem (HCP)]({{"/2022/03/12/hamilton-cycle-problem.html" | relative_url}})
  - [Balanced Cut]({{"/2022/05/07/balanced-cut.html" | relative_url}})
  - [3D Matching]({{"/2022/05/07/3D-matching.html" | relative_url}})
  - Integer Linear Programming 🎈
  - [Independent Set, Vertex Cover, Clique]({{"/2022/05/08/independent-set-and-vertex-cover-and-clique.html" | relative_url}})
  - [Longest Path, Subset Sum]({{"/2022/05/08/longest-path-and-subset-sum.html" | relative_url}})

- [Reductions]({{"/2022/05/08/reduction-1.html" | relative_url}})
  - [Reduction and NP-complete]({{"/2022/05/08/reduction-1.html" | relative_url}})
    - Mapping Reduction & Polynomial Reduction 🎈
    - [NP-complete and NP-hard]({{"/2022/05/10/NP-complete-and-NP-hard.html" | relative_url}})
  - [Reduction (2)]({{"/2022/05/12/reduction-2.html" | relative_url}})
    - 3-SAT → Independent-Set
    - Independent-Set → Vertex Cover
    - Independent-Set → Clique
    - 3-SAT → Clique
  - [Reduction (3)]({{"/2022/05/13/reduction-3.html" | relative_url}})
    - 3-SAT → 3D-Matching
  - [Reduction (4): Circuit-SAT and Cook-Levin Theorem]({{"/2022/05/14/reduction-4.html" | relative_url}})

### [Coping with NP-hardness]({{"/2022/05/18/coping-with-np-hardness.html" | relative_url}})

- Exhaustive Search
  - [Backtracking]({{"/2022/05/19/bacaktracking.html" | relative_url}})
  - [Branch-and-Bound]({{"/2022/05/20/branch-and-bound.html" | relative_url}})
- Heuristic Algorithm
  - [Local Search]({{"/2022/05/20/local-search.html" | relative_url}})

### Appendix

- [Amortized Analysis]({{"/2021/05/08/amortized-analysis.html" | relative_url}}) 🎈
  - Accounting Method
- [Implementations of Heap]({{"/2021/05/03/implementations-of-heap.html" | relative_url}}) 🎈
  - Heap by unordered array & ordered array
  - Binary Heap
  - [d-ary Heap]({{"/2021/05/03/implementations-of-heap.html#d-ary-heap" | relative_url}})
  - [Binomial Heap]({{"/2021/05/03/implementations-of-heap.html#binomial-heap" | relative_url}})
    - Binomial Tree
  - [Lazy-Binomial Heap]({{"2021/05/03/implementations-of-heap.html#lazy-binomial-heap" | relative_url}})
  - [Fibonacci Heap]({{"/2021/05/03/implementations-of-heap.html#fibonacci-heap" | relative_url}}) 🔥
- FFT; Fast Fourier Transformation 🎈

<hr/>

