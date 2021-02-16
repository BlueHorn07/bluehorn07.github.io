---
title: "PS를 위한 준비"
layout: post
tags: []
use_math: true
---

### 서론
이 글은 정리를 위해 개인적인 용도로 작성된 포스트입니다. 지적과 조언은 언제나 환영입니다 ㅎㅎ

<br>
<hr>

인턴 준비를 위해 다시 PS를 시작하게 되었다. 그런데, 맨날 JS, Python, Java로만 코딩하다가 갑자기 C++로 코딩하려니 너무 헷갈려서 이번 기회에 그냥 이 포스트에 헷갈리는 걸 다 적을 생각이다.

### Visual Studio 관련

- [scanf_s 오류 해결](https://bymakers.tistory.com/6)
- [bits/stdc++.h 준비](https://miniolife.tistory.com/11)
  - 주의할 점은 `#include<bits/stdc++.h>`를 해도, `using namespace std;`는 써줘야 한다!
- [주석 단축기 변경](https://wotres.tistory.com/entry/Visual-Studio-%EC%A3%BC%EC%84%9D-%EB%8B%A8%EC%B6%95%ED%82%A4-%EB%B0%8F-%EB%B3%80%EA%B2%BD-%EB%B0%A9%EB%B2%95)

<br/>

개인적으로 Visual Studio는 너무 old-fashioned라 코딩하기 정말 힘든 것 같다.

그럴 땐 갓-Brain, Jet-Brain 社에서 만든 Clion을 쓰면 된다.

Clion에서도 설정만 조금 해주면 금방 세팅하고 PS를 즐길 수 있다!!



### C언어 디테일 관련

- 소수로 값을 출력할 때는, 유효 자릿수를 꼭 확인하라. ~~종만북 첫번째 문제를 기억하자~~
  - 그냥 마음편하게 `printf("%.10f", out);`으로 처리하면 될 듯
- 마찬가지로 소수 출력할 때, `float`이나 `double` 모두 `%f`로 출력한다. `%lf`는 `long double` 출력할 때만 씀.

- `cin`, `cout` 속도 향상, [link](https://algwang.tistory.com/10)

``` cpp
ios_base :: sync_with_stdio(false);
cin.tie(NULL);
cout.tie(NULL);
```

- `string` 타입 입력 받을 때는 `scanf()`를 쓸 수 없다. 그냥 `cin`으로 입력받거나, `string` 전용 입력 함수를 써야 한다.