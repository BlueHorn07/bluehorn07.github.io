---
title: "DFS and BFS - code"
toc: true
toc_sticky: true
categories: ["Algorithm"]
excerpt: "DFS, BFS를 구현할 때의 경험들"
---

# 들어가며

2020-1학기, 대학에서 '알고리즘' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

\<DFS\>, \<BFS\>에 대한 기초 백준 문제인 [1260번 - DFS와 BFS](https://www.acmicpc.net/problem/1260) 기준으로 코드를 작성해보았다.

# Set-up

저는 그래프의 edge $E$를 저장할 때, "**인접 리스트(Adjacency List)**" 방식을 선호 합니다. 그리고 방문 여부는 `bool` 타입의 배열 `isVisit[]`로 기록 합니다.

``` cpp
vector<int> edges[MAX];
bool isVisit[MAX];
```

그리고 데이터를 넣는건 요렇게

```cpp
for (int i = 0; i < M; i++) {
  int s, e;
  scanf("%d %d", &s, &e);
  edges[s].push_back(e);
  edges[e].push_back(s);
}
```

# DFS

## Recursion

``` cpp
void dfs(int v) {
  printf("%d ", v);
  isVisit[v] = true;

  for (int i = 0; i < edges[v].size(); i++) {
    int adj = edges[v][i];
    if (!isVisit[adj]) {
      dfs(adj);
    }
  }
}
```

## Stack

``` cpp
void dfs_stack(int s) {
  stack<int> st;
  st.push(s);

  while (!st.empty()) {
    int top = st.top();
    st.pop();

    if(!isVisit[top]){
      isVisit[top] = true;
      printf("%d ", top);

      for (int i = 0; i < edges[top].size(); i++) {
        int adj = edges[top][i];
        if (!isVisit[adj]) {
          st.push(adj);
        }
      }
    }
  }
}
```

# BFS

``` cpp
void bfs(int s) {
  queue<int> q;
  isVisit[s] = true;
  q.push(s);

  while (!q.empty()) {
    int front = q.front();
    printf("%d ", front, q.size());
    q.pop();

    for (int i = 0; i < edges[front].size(); i++) {
      int adj = edges[front][i];
      if (!isVisit[adj]) {
        q.push(adj);
        isVisit[adj] = true;
      }
    }
  }
}
```

# DFS-BFS 호환 코드

몇년 간의 PS 경험에 따르면, \<DFS\>의 구현 코드와 \<BFS\>의 구현 코드의 형태를 거의 일치시킬 수 있는 구현 스타일이 있습니다. 이 스타일은 `stack`을 쓰면 DFS가 되고, `queue`를 쓰면 BFS가 됩니다!

1\. DFS (stack)

``` cpp
void dfs_stack(int s) {
  stack<int> st; // stack 사용
  st.push(s);

  while (!st.empty()) {
    int top = st.top();
    st.pop();

    if(!isVisit[top]){
      isVisit[top] = true;
      printf("%d ", top);

      for (int i = 0; i < edges[top].size(); i++) {
        int adj = edges[top][i];
        if (!isVisit[adj]) {
          st.push(adj);
        }
      }
    }
  }
}
```

2\. BFS (queue)

``` cpp
void bfs_queue(int s) {
  queue<int> q; // queue를 사요!
  q.push(s);

  while (!q.empty()) {
    int front = q.front();
    q.pop();

    if(!isVisit[front]){
      isVisit[front] = true;
      printf("%d ", front, q.size());

      for (int i = 0; i < edges[front].size(); i++) {
        int adj = edges[front][i];
        if (!isVisit[adj]) {
          q.push(adj);
        }
      }
    }
  }
}
```

코드를 잘 살펴보면, Container로 `stack`을 선택하는지, `queue`를 선택하는지에 따라 알고리즘이 바뀐건 볼 수 있습니다.

개인적으로 자주 쓰는 스타일은 아닌데, \<DFS\>, \<BFS\> 코드를 여러 번 구현해보니 이런 패턴도 보이게 되었습니다 ㅎㅎ 😎 하지만 여전히 \<DFS\> 구현은 \<재귀 recursion\>으로 구현하는 걸 선호하는 것 같습니다 😆

