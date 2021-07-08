---
title: "내가 보려고 만든 'PyTorch' Cheat Sheet"
layout: post
tags: ["Cheat Sheet"]
use_math: true
---

### 서론
이 글은 정리를 위해 개인적인 용도로 작성된 포스트입니다. 지적과 조언은 언제나 환영입니다 ㅎㅎ

<hr/>

- `tensor.unsqueeze(0)`
  - 배치 차원을 추가해줌
  - shape: (2, 4) → shape: (1, 2, 4)
  - 반대로 배치 차원을 제거하고 싶다면, `tensor.view(-1, ...)`

- `torch.Tensor` ≡ `torch.FloatTensor`
  - 👉 [참고](https://newpower.tistory.com/199)

- CUDA 확인

``` py
USE_CUDA = torch.cuda.is_available()
DIVICE = torch.device("cuda" if UES_CUDA else "cpu")
```

<hr/>

- `nn.Embedding`
  - 두 가지 입력을 받음.
    - 단어장의 크기
    - 임베딩 벡터의 차원


