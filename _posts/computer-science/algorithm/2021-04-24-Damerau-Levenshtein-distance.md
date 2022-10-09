---
title: "Damerau–Levenshtein distance"
layout: post
tags: ["algorithm"]
---



이 포스트는 백준 [15484번: 최소편집2](https://www.acmicpc.net/problem/15484)와 [2326번: 최소 편집 문제2](https://www.acmicpc.net/problem/2326)에서 쓰는 알고리즘인 \<Damerau–Levenshtein distance\>에 대해 소개하는 포스트입니다. 지적은 언제나 환영입니다 :)

<hr/>

## Damerau–Levenshtein distance

[\<편집 거리; edit distance\>]({{"/2021/04/20/edit-distanace.html" | relative_url}})는 대표적인 DP 문제이다. 백준에서는 [15483번: 최소편집](https://www.acmicpc.net/problem/15483) 문제를 통해 \<edit distance\>에 대한 문제를 풀 수 있다.

먼저 \<edit distance\>의 상황을 살펴보면, 두 String `s1`, `s2`에 대해

1. 삽입(Insertion)
2. 삭제(Deletion)
3. 교체(Replacement)

세 가지 연산을 통해 두 문자를 같게 만들 수 있는 최소 횟수를 계산한다.

그런데, [최소편집](https://www.acmicpc.net/problem/15483) 문제의 다음 문제인 [최소편집2](https://www.acmicpc.net/problem/2326)에서는 하나의 연산이 더 추가된다.

4\. 교환(Trasposition with adjacent character): 두 인접한 글자의 위치를 서로 바꿀 수 있다.

문제를 보고, 처음에는 꽤 할만 하다고 생각해서 단순히 "교환했을 때 matching cost가 0이 되고, 또 그때의 DP 값이 작다면, Transpotion!"인 식으로 4번째 연산을 처리해봤는데, <span style="color: red">**WA**</span>를 받았었다. 

검색해보니, 'Transpotion' 연산은 앞의 경우랑은 다르게 쉽게 계산되지 않고, \<[Damerau–Levenshtein distance](https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance)\>[^1]를 통해 구해야 한다.

\<DL-distance\>는 오타 수정(misspel correction)에서 활용하는 알고리즘이다. DL-distance에서 쓰는 4가지 경우는 우리가 흔히 하는 오타의 경우들이며, 구글 검색에서 검색을 잘못 입력해도 적절히 원래의 형태로 변환해주는 것 역시 이런 DL-distance로 유사도를 판단하기 때문이다.

<hr/>

### Algorithm

DL-distance를 구하는 알고리즘에는 \<OSA distance; Optimal String Alignment distance\>와 \<DL-distance with adjacent transposition\>, 두 가지 알고리즘이 있다. 이때, 후자를 true DL-distance라고 여긴다.

OSA와 DL 모두 transposition correction을 지원한다. 그러나 \<OSA distanace\>의 경우 "no substring is edited more than once"라는 졔약이 있다. 본인은 대충 "transposition한 substring 사이에 insertion을 포함한 다른 연산을 수행할 수 없다"라고 제약을 이해했다.

이 제약이 왜 critical 한지는 Wikipedia에 제시되는 예시를 통해 이해할 수 있다.

👉 [example from Wikipedia](https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance#Algorithm)

OSA 알고리즘은 구현하기도 쉽고, 또 본인이 처음 시도한 접근일 만큼 직관적이기 때문에 여기서 따로 설명하지는 않겠다 😉 이번 포스트에서는 true DL-distance만 한번 제대로 살펴보자!

<hr/>

### Algorithmic Detail

이 부분은 ['구데타마 구데타마'님의 포스트](https://jaimemin.tistory.com/591)의 포스트를 많이 참고했다. (단, 구데타마 님과는 다르게, Wikipedia에 제시된 DL-distance 알고리즘을 그대로 이해하고 해석해서 기술하였다.)

먼저, 삽입, 삭제, 교체에 대한 부분은 기존의 \<edit distance\>와 동일하다.

추가된 transposition 연산은 아래와 같이 수행된다. `s1 = "ca"`, `s2="abc"`의 예시를 통해 살펴보자.

<div class="img-wrapper">
  <img src="{{ "/images/algorithm/DL-distance-1.png" | relative_url }}" width="300px">
</div>

먼저, 현재 `i=2`, `j=3`라고 하자. 즉, `s1[i=2] = 'a'`, `s2[j=3]='c'`인 상황이다.

이때, `s2`에서 `j=3`보다 작으면서, `s1[i=2]='a'`와 같은 문자를 갖는 곳의 위치를 `l`이라고 하자! 반대로 `s1`에서 `i=2`보다 작으면서, `s2[j=3]='c'`와 같은 문자를 갖는 곳의 위치를 `k`라고 하자! 위의 예시에서는 `l=0`, `k=0`이다.

<div class="img-wrapper">
  <img src="{{ "/images/algorithm/DL-distance-2.png" | relative_url }}" width="300px">
</div>

이때, transposition은 3단계를 통해 수행된다.

1. `s1[k]`와 `s1[i]` 사이를 전부 없애고 (cost = i-k-1)
2. `s1[k]`와 `s1[i]`를 교환하고 (cost = 1)
3. `s2[l]`과 `s2[j]` 사이에 있는 모든 문자를 삽입 (cost = j-l-1)

따라서, transposition의 비용은 총합 `(i-k-1) + 1 + (j-l-1)`이 된다!

이것을 코드로 표현하면,

``` c++
dp[i][j] = min(insertion, deletion, replacement, dp[k][l] + (i-k-1) + 1 + (j-1-1))
```

이 되는 것이다

<hr/>

자! 이제 코드 레벨에서 살펴보자. 코드는 Wikipedia의 pseudo-code를 이해하고 그대로 `C++`로 옮겨 작성하였다. 코드에서 `k`, `l`를 계산하기 위해 `da[]`, `db`를 도입하는 테크닉을 사용했다는 점도 주목할만 하다 🤩

재귀 없이 Button-Up 방식으로 구현했다.

``` c++
#include <bits/stdc++.h>
#define MAX 1005

using namespace std;

int dp[MAX][MAX];
int da[26]; // s1에서 현재 i보다 작으면서, s2[j]와 같은 문자를 갖는 곳의 위치; $k$

int Damerau_Levenshtein(string a, string b) {
  // initialization
  int maxDist = a.size() + b.size();

  dp[0][0] = maxDist;
  for (int i = 1; i <= a.size() + 1; i++) {
    dp[i][0] = maxDist;
    dp[i][1] = i - 1;
  }

  for (int j = 1; j <= b.size() + 1; j++) {
    dp[0][j] = maxDist;
    dp[1][j] = j - 1;
  }

  fill_n(da, 26, 1);

  // DP process
  for (int i = 2; i < a.size() + 2; i++) {
    // db: s2에서 현재 j보다 작으면서, s1[i]와 같은 문자를 갖는 곳의 위치; $l$
    int db = 1; 
    for (int j = 2; j < b.size() + 2; j++) {
      int k = da[b[j - 2] - 'a']; 
      int l = db;
      int cost;
      if (a[i - 2] == b[j - 2]) {
        cost = 0;
        db = j; // 현재 s[i]와 같은 곳의 위치를 db에 기록!
      } else {
        cost = 1;
      }
      dp[i][j] = min(dp[i - 1][j - 1] + cost,
                     min(dp[i][j - 1] + 1,
                         min(dp[i - 1][j] + 1,
                             dp[k - 1][l - 1] + (i - k - 1) + 1 + (j - l - 1))));
    }
    da[a[i - 2] - 'a'] = i;
  }

  return dp[a.size() + 1][b.size() + 1];
}

int main() {
  string s1, s2;
  cin >> s1;
  cin >> s2;

  cout << Damerau_Levenshtein(s1, s2);

  return 0;
}
```

디버그를 위해 출력함수도 만들어 뒀다.

``` c++
void printDP(int N, int M) {
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      printf("%d ", dp[i][j]);
    }
    printf("\n");
  }
  printf("da: ");
  for (int k = 0; k < 26; k++) {
    printf("%d ", da[k]);
  }
  printf("\n");
  printf("==========================\n");
}
```

!! [15484번: 최소편집2](https://www.acmicpc.net/problem/15484)를 해결했다!

<div class="img-wrapper">
  <img src="{{ "/images/algorithm/DL-distance-3.jpg" | relative_url }}" width="100%">
</div>

위의 코드에서 약간만 수정하면, [2326번: 최소 편집 문제2](https://www.acmicpc.net/problem/2326)도 쉽게 <span style="color: #009874">**AC**</span>를 받을 수 있다 ㅎㅎ

#### 참고자료

- [Wikiepedida - Damerau–Levenshtein distance](https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance)
- ['구데타마 구데타마'님의 포스트](https://jaimemin.tistory.com/591)

<hr/>

[^1]: 대충 [디메라우-리벤슈테인] 거리라고 읽으면 될 것 같다. [이곳](https://youtu.be/GFQytXDVK4Y?t=66)의 발음을 참고했다.