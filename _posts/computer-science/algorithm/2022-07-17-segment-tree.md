---
title: "Segment Tree"
layout: post
use_math: true
tags: ["Algorithm"]
---

<br/>

세그먼트 트리는 배열 위에서 빠르게 "구간 연산" 또는 "갱신 연산"을 수행해야 할 때 유용하다. 본 포스트는 세그먼트 트리에 대한 자세한 이론 보다는 코드 레벨와 함께 세그먼트 트리를 이해하고 기록하고자 작성했다. 세그먼트 트리에 대한 이론은 유튜브 영상 "[[알고리즘 강의] 세그먼트 트리](https://youtu.be/XaodfglnhVs)"를 참고하자.

- Segment Tree란?
- Segment Tree의 구조
- 코드는 어떻게 짜야할지
- 어떻게 활용할지
  - 패턴이 있다!
- 사실 나도 요건 안지 얼마 안 된 녀석이다!

<hr/>

# Segment Tree란?

세그먼트 트리(Segment Tree)는 배열에서 점 연산 또는 구간 연산을 수행할 때 쓸 수 있는 "자료구조"다. 처음에는 세그먼트 트리에 대한 정의보다는 구체적인 상황을 바탕으로 이해해야 한다.

배열 `arr`에 대해 아래의 2가지 연산을 수행해보자.

1. 구간 `left`, `right` (`left < right`)에 대해 구간합 `arr[left] + ... + arr[right]` 출력
2. 배열의 i번째 수를 `v`로 바꾸기: `arr[i] = v`

1번, 2번 연산을 각각 **구간합**, **점 갱신** 연산이라고 이름 붙이겠다.

이 문제를 Brute Force로 접근해보자. 구간합은 한번 연산에 $O(N)$, 점 갱신은 $O(1)$의 시간이 걸린다. 연산을 $M$번 수행한다면, 최대 $O(NM)$의 비용이 든다. 

그러나 $O(NM)$ 시간 복잡도는 $N$ 또는 $M$이 매우 큰 경우에는 시간이 너무 오래 걸린다. 그런데! 세그먼트 트리를 이용하면 구간합을 $O(\log N)$, 점 갱신을 $O(\log N)$에 수행할 수 있어 $O(M \log N)$의 비용으로 모든 연산을 처리할 수 있다!!

# Segment Tree의 구조

Segment Tree는 트리 형태의 자료구조이다. 구체적으론 Full Binary Tree이다. 모든 노드가 0개 혹은 2개의 자식 노드를 갖는 트리를 말한다. 그래서 부모-자식 노드 사이에 `node <-> node*2, node*2+1`의 관계가 성립한다. 

세그먼트 트리에서 각 노드는 아래와 같은 의미를 갖는다.

- 리프 노드
  - 배열에서 그 수 자체다. 
  - `arr[i]`라는 말
- 구간 노드
  - 왼쪽 자식과 오른쪽 자식의 합을 저장
  - `tree[i] = tree[2*i] + tree[2*i+1]`

배열 갯수 $N$이 10인 경우 트리는 아래와 같다.


<div class="img-wrapper">
  <img src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/blog/seg1.png" width="90%">
  <p><a href="https://www.acmicpc.net/blog/view/9">백준 블로그 - 세그먼트 트리</a></p>
</div>

세그먼트 트리에서 노드에 표시된 숫자 또는 범위는 해당 노드가 저장한 구간합의 범위이다. 예를 들어, 노드에 표시된 범위가 `5~7`이라면, `arr[5] + arr[6] + arr[7]`의 의미이다. 그럼 당연하게도 세그먼트 트리의 루트노드는 전체 배열의 합을 저장한다.

## Segment Tree 초기화

세그먼트 트리는 Full Binary Tree여야 한다. 리프 노드가 $N$개인 Full Binary Tree의 높이 $H$는 $H = \lceil \log N \rceil$이다. 그리고 이를 구현하기 위해 $(2 \times H - 1)$개 만큼의 노드가 필요하다. 이를 바탕으로 초기화 코드를 작성하면 아래와 같다.

```cpp
  int h = (int) ceil(log2(n));
  int tree_size = (1 << (h+1));
  vector<llong> tree(tree_size);
```

이제 세그먼트 트리 `tree`의 노드에 값을 할당해야 한다. 리프 노드인지 중간 노드인지에 따라 값을 할당 해주면 된다.

```cpp
// main
vector<llong> tree;
tree.assign(tree_size, 0);

init(arr, tree, 1, 0, N-1);


llong init(vector<llong> &arr, vector<llong> &tree, int node, int start, int end) {
  if (start == end) // 리프 노드임.
    return tree[node] = arr[start];
  
  int mid = (start + end) / 2;
  
  llong left = init(arr, tree, node * 2, start, mid);
  llong right = init(arr, tree, node * 2 + 1, mid + 1, end);
  
  tree[node] = left + right;
  return tree[node];
}
```

초기화 함수 `init()`에서 `node`는 값을 할당하고자 하는 노드를 의미한다. `start`와 `end`는 해당 `node`가 커버하는 범위를 의미한다.


## 구간합

세그먼트 트리의 성질에 따라 구간 노드의 값을 조합해 구간합을 구할 수 있다. 이를 위해 `sum()` 함수를 작성하자.

먼저 변수 이름을 분명히 하자. 여기서 `start ~ end`는 트리 노드 `node`가 커버하는 범위이다. 우리가 구간합을 구하고자 하는 범위는 `left ~ right`이다.


```cpp
llong sum(vector<llong> &tree, int node, int start, int end, int left, int right) {
  // query하는 범위 (left, right)와 노드 범위 (start, end)가 겹치지 않음.
  if (left > end || right < start) 
    return 0;
  
  // 구해야하는 합의 범위는 [left, right]인데, 
  // [start, end]는 그 범위에 모두 포함된다.
  // 그러면 node의 자식도 모두 포함되기 때문에 
  // 더 이상 호출을 하는 것은 비효율적이다.
  if (left <= start && end <= right)
    return tree[node];
  
  int mid = (start + end) / 2;
  llong leftSum = sum(tree, node * 2, start, mid, left, right);
  llong rightSum = sum(tree, node * 2 + 1, mid+1, end, left, right);
  
  return leftSum + rightSum;
}
```


## 점 갱신

점 갱신은 배열 `arr[i]`가 갱신되는 그 값이 아니라 차이값인 `diff`를 이용해 노드의 값을 갱신 한다. 이때, 해당 리프 노드가 포함된 모든 구간 노드들을 갱신해줘야 한다.


```cpp

// main
llong diff = new_value - arr[idx];
arr[idx] = new_value;
update(tree, 1, 0, N-1, idx, diff);


void update(vector<llong> &tree, int node, int start, int end, int index, llong diff) {
  // index가 노드 범위 (start, end)와 겹치지 않음.
  if (index < start || end < index) 
    return;
  
  tree[node] += diff;
  
  // 리프 노드는 start == end임.
  // 리프가 아니라면, 아래의 자식노드도 업데이트 해줘야 함.
  if (start != end) {
    int mid = (start + end) / 2;
    update(tree, node * 2, start, mid, index, diff);
    update(tree, node * 2 + 1, mid+1, end, index, diff);
  }
}
```

<hr/>

# 맺음말

세그먼트 트리는 그 존재감 때문에 일단 시작하는게 어렵지 맘 잡고 공부하면 금방 익숙해진다. "배열 위에서의 구간 연산, 점 연산"이라는 특징 때문에 조금의 감만 있으면 어떤 문제를 세그먼트 트리로 풀어야 하는지 눈치 챌 수 있다!

시간이 날 때 Multiset의 세그먼트 트리 구현과, Lazy Segment Tree에 대해 공부하고 포스트를 작성해보겠다 😉


# 관련 문제

- [백준 2042번: 구간 합 구하기](https://www.acmicpc.net/problem/2042)


# References

- [BAEKJOON 블로그 - 세그먼트 트리](https://www.acmicpc.net/blog/view/9)
- [[알고리즘 강의] 세그먼트 트리](https://youtu.be/XaodfglnhVs)