---
title: "Traveling Salesman Problem"
toc: true
toc_sticky: true
categories: ["Algorithm"]
---



2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :) 전체 포스트는 [Algorithm](/categories/algorithm) 포스트에서 확인하실 수 있습니다.

<hr/>

\<**외판원 순회문제; Traveling Salesman Problem**\>은 대표적인 **non-polynomial problem**이다. 문제는 아래와 같다.

<div class="statement" markdown="1" align="center">

"Find a tour that start and ends at $1$, <span style="color:red">visits all cities exactly once</span>, and has minimum total length."

</div>

사실 항상 시작점으로 돌아오기 때문에 $1$번 노드에서 시작하든 다른 노드에서 시작하든 상관은 없다.

이 문제는 \<완전 탐색\>으로 푸는 접근과 \<DP\>로 푸는 두 가지 방식이 존재한다. 당연히 \<DP\>로 푸는게 \<완전 탐색\>보다 더 빠르지만, 구현이 조금 더 tricky 하다.

<hr/>

### 완전 탐색

『알고리즘 문제해결전략』의 1권에서는 이 문제에 대한 완전탐색 접근법을 제시한다. 앞에서부터 도시를 하나씩 추가해 경로(path)를 만들어, 가능한 모든 경로를 만들어 이 중에서 최소 비용을 출력한다.

종만북에서 제시된 알고리즘을 [벡준 10971번: 외판원 순회 2](https://www.acmicpc.net/problem/10971) 문제에 맞춰서 작성하면 아래와 같다.

``` cpp
// path: 지금까지 만든 경로
// visited: 도시의 방문 여부
// currentLength: path의 경로로 만든 길이
long long shortestPath(vector<int> &path, vector<bool> &visited, long long currentLength) {
  // base case: 모든 도시를 방문했다면, 시작 도시로 돌아가고 종료
  if (path.size() == N) {
    int lastEdge = W[path.back()][path[0]];
    return lastEdge ? currentLength + lastEdge : LLONG_MAX;
  }

  long long ret = LLONG_MAX;

  // 방문 가능한 모든 도시를 방문
  for (int next = 0; next < N; next++) {
    if (visited[next]) continue;

    int here = path.back();
    if (W[here][next] == 0) continue;

    path.push_back(next);
    visited[next] = true;
    long long cand = shortestPath(path, visited, currentLength + W[here][next]);
    ret = min(ret, cand);

    visited[next] = false;
    path.pop_back();
  }
  return ret;
}
```

전체탐색으로 가능한 모든 방문 조합을 모두 탐색한다! 코드를 구성하는 과정에서 "조합"의 테크닉을 사용했다. `path`, `visited`를 인자로 넘겨야 한다는 생각을 하는게 어려운 것 같다 😲 이 코드는 bottom-up 재귀 방식으로 TSP를 구현했다.

그러나 \<완전 탐색\>은 성능면에서 좋지 않다. 그래서 이 접근으로 [백준 2098번: 외판원 순회](https://www.acmicpc.net/problem/2098)를 풀게 되면 <span style="color: red">**시간 초과**</span>를 받게 된다 😥

<hr/>

### Dynamic Programming + Bitmask

\<TSP\>를 \<DP\>로 풀기 위한 알고리즘의 뼈대는 아래와 같다.

<div class="proof" markdown="1">

Let $C(j, S)$ the length of shortest path when visit $j$ at last and visit each node in $S$ exactly once.

이때, $C(j, S)$는 아래의 식으로 구할 수 있다.

$$
C(j, S) = \min_{i \in S, \; i \ne j} \left\{ C(i, S - \{j\}) + d_{ij}\right\}
$$

그럼 우리의 목표는 $C(i, \\{ 1, 2, \dots, n \\}) + d_{i1}$ 중 가장 짧은 거리를 구하는 것이다! 이를 구하기 위한 알고리즘을 코드로 기술하면 아래와 같다. 코드는 ['hsp1116'님의 포스트](https://hsp1116.tistory.com/40)를 참조했다.

</div>

TSP의 DP 알고리즘을 구현하기 위해선 [비트마스크(bitmask) 기법]({{"/2022/04/29/bitmask-technique" | relative_url}})에 대해 알아야 한다. 해당 포스트를 먼저 살펴보고 오자.

``` cpp
// curr: 마지막으로 방문한 노드 (=here)
// visited: 방문한 노드를 기록한 비트마스크
long long shortestPath(int curr, int visited) {
  // 모두 방문
  if (visited == ((1 << N) - 1)) {
    int lastEdge = W[curr][0];
    return lastEdge ? lastEdge : LLONG_MAX;
  }

  long long ret = LLONG_MAX;

  for (int next = 1; next < N; next++) {
    if (visited & (1 << next)) continue;
    if (W[curr][next] == 0) continue;

    long long out = shortestPath(next, visited | (1 << next));
    if(out == LLONG_MAX) continue;

    ret = min(ret, out + W[curr][next]);
  }

  return ret;
}
```

사실 처음에 제시했던 완전탐색 코드와 크게 다르지는 않다. 그러나 함수의 인자로 `visited`를 비트마스크로 받는다는 점. 그리고 TSP의 결과를 얻기 위해서 구체적인 방문 순서가 담긴 경로 `path`가 필요없으니 마지막으로 방문한 노드인 `curr`만 받는다는 점이 주목할 만하다! 🤩

여기에 \<DP\>를 얹으면 아래와 같다.

``` cpp
long long dp[MAX][1 << MAX];

// curr: 마지막으로 방문한 노드 (=here)
// visited: 방문한 노드를 기록한 비트마스크
long long shortestPath(int curr, int visited) {
  // 모두 방문
  if (visited == ((1 << N) - 1)) {
    int lastEdge = W[curr][0];
    return lastEdge ? lastEdge : LLONG_MAX;
  }

  if (dp[curr][visited] != 0) return dp[curr][visited];

  long long ret = LLONG_MAX;

  for (int next = 1; next < N; next++) {
    if (visited & (1 << next)) continue;
    if (W[curr][next] == 0) continue;

    long long out = shortestPath(next, visited | (1 << next));
    if(out == LLONG_MAX) continue;

    ret = min(ret, out + W[curr][next]);
  }

  dp[curr][visited] = ret;
  return ret;
}
```

처음에는 비트마스크를 쓰기 위한 범위가 $2^{16}$이나 되어서 선언 가능한 배열의 범위를 훨씬 벗어난다고 생각했는데, 위의 코드에서는 `dp[MAX][1 << MAX]`와 같은 방식으로 해결했다. 처음에는 이것 때문에 배열이 아니라 `map` 형식으로 DP를 할까 고민도 많이 했다.

또, 아직 비트마스크의 기본이 되는 테크닉들이 익숙하지 않아서 먼저 비트마스크 문제를 좀더 풀고 오리지널 기술로 습득하는게 필요해보인다.

\<TSP\>는 워낙 유명하고 중요한 문제라서 풀어봤는데, 많은 영감을 얻은 것 같다 ㅎㅎ

#### 시간 복잡도

DP 알고리즘은 $O(n^2 2^n)$의 효율을 보인다고 한다. 이건 Brute Force의 $O((n-1)!)$ 보다 약간 좋은 수준이며 DP를 썼음에도 여전히 exponential time algorithm인 상황이다.

<hr/>

### TSP (Search Problem)

TSP 문제에 budget $b$를 도입하여 Search Problem의 형태로 바꾸고자 한다.

<div class="statement" markdown="1" align="center">

"Find a tour that start and ends at $1$, <span style="color:red">visits all cities exactly once</span> with <span style="color:red">total cost $b$ or less</span>. <br/>
If there's no such tour, then report 'no tour'."

</div>

우리가 맨 처음 살펴봤던 TSP 문제는 최적해를 찾는 optimization problem이었다. 그런데 이번에 정의한 TSP는 search problem이다. TSP(optimization)과 TSP(search)를 서로의 문제로 환원 될 수 있다.

- TSP(optimization) 알고리즘을 통해 TSP(search) 문제에 답할 수 있다.
- TSP(search) 알고리즘을 통해 tour가 존재하는 지점까지 budget $b$를 binary search 하면 TSP(optimization) 문제에 답할 수 있다.

그렇담 **왜 굳이 TSP(search)를 정의한 걸까?** 그것은 TSP 문제의 solution $T$을 correctness를 검증하기 위해서다. TSP 문제를 검증하기 위해선 (1) $T$가 tour인가? (2) $T$의 total length가 budget $b$ 이하인가?를 검증해야 하는데, TSP(optimization)은 solution $T$가 주어졌을 때 $T$의 total length가 정말 optimal인지 답하기 어렵기 때문에 budget $b$를 통한 (2)번 질문을 만든 것이다.

### Problem Solving

- [벡준 10971번: 외판원 순회 2](https://www.acmicpc.net/problem/10971)
- [백준 2098번: 외판원 순회](https://www.acmicpc.net/problem/2098)

### 함께보기

- [Bitmask Technique]({{"/2022/04/29/bitmask-technique" | relative_url}})
- [Hamilton Cycle Problem]({{"/2022/03/12/hamilton-cycle-problem" | relative_url}})
- [P and NP]({{"/2022/01/14/P-and-NP" | relative_url}})


<hr/>

#### reference

- ['hsp1116'님의 포스트](https://hsp1116.tistory.com/40)