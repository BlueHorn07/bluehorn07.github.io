---
title: "Transformer(2017) - 3"
layout: post
tags: ["research"]
---


이 포스트는 제가 개인적인 용도로 정리한 글 입니다. 

- "[Attention is all you need](https://arxiv.org/abs/1706.03762)"
  - 2017, Google

<br>

- [Transformer - 1]({{"2021/01/18/Transformer-1.html" | relative_url}})
- [Transformer - 2]({{"2021/01/18/Transformer-2.html" | relative_url}})

1. [Decoder in Transformer](#decoder-in-transformer)
   1. Self-Attention
   2. Encoder-Decoder Attention
   3. FFNN

<br>
<hr>

### Decoder in Transformer

인코더 모듈은 내부적으로 Self-Attention과 FFNN의 sub-layer로 구성되었다면, 디코더의 경우는 3가지 sub-layer로 구성된다. 각각을 살펴보자.

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/decoder.PNG" width="35%">
</div>

#### Self-Attention

인코더에서 sequence에 Positional Encoding을 적용한 행렬을 입력으로 받았다. 디코더 역시 정답 sequence에 Positional Encoding을 적용한 행렬을 입력으로 받는다.

하지만 이렇게 정답 sequencce를 입력 받는 것은 seq2seq의 컨셉트인 "디코더는 추론한 단어를 다시 입력으로 넣어 한번더 추론하는 교사 강요(Teacher Forcing)를 통해 학습한다."를 위반하게 된다.

기존 RNN에서는 입력을 매 시점마다 순차적으로 받았지만, Transformer는 정답 전체를 입력으로 받는 꼴이기 때문에 문제가 되는 것이다.

Transformer의 디코더는 입력에 포함된, 나중에 예측해야 할 단어들을 참고하지 못하도록 Masking으로 그 단어들을 가려버린다. 이것을 "Look-ahead mask"라고 한다.

이런 "LAM"는 디코더의 첫번째 sub-layer인 Self-Attention 층에서 이루어진다. 디코더의 Self-Attention 층은 인코더의 Self-Attnetion과 동일한 연산을 수행하지만, 오직 다른 점은 디코더의 경우 마스킹이 적용된 Attention Score 행렬을 사용한다는 것이다.

대략 아래와 같은 느낌이다.

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/%EB%A3%A9%EC%96%B4%ED%97%A4%EB%93%9C%EB%A7%88%EC%8A%A4%ED%81%AC.PNG" width="30%">
</div>

마스킹 된 Attention Score 행렬의 각 행을 살펴보면, 자기 자신과 그 이전 단어들만을 참고할 수 있게 되었다.

<br>
<hr>

#### Encoder-Decoder Attention

앞에서 살펴봤던 두 번의 Attention 모두 $Q$, $K$, $V$가 모두 한 모듈 안에서 유래하는 Self-Attention 이었다. 디코더의 두 번재 layer에서는 Query $Q$가 디코더에서 유래하는 일반적인 Attention을 적용한다.

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/%EB%94%94%EC%BD%94%EB%8D%94%EB%91%90%EB%B2%88%EC%A7%B8%EC%84%9C%EB%B8%8C%EC%B8%B5.PNG" width="20%">
</div>

디코더의 두 번째 layer로 두 개의 화살표가 들어오는데, 이것은 $K$, $V$로 인코더의 마지막 층에서 온 행렬로부터 얻는다.

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/%EB%94%94%EC%BD%94%EB%8D%94%EB%91%90%EB%B2%88%EC%A7%B8%EC%84%9C%EB%B8%8C%EC%B8%B5%EC%9D%98%EC%96%B4%ED%85%90%EC%85%98%EC%8A%A4%EC%BD%94%EC%96%B4%ED%96%89%EB%A0%AC_final.PNG" width="70%">
</div>

디코더의 첫번째 Attention의 결과로부터 Query $Q$를 얻는다. 이때 Query $Q$는 디코더의 입력에 Attention 가중합이 적용된 상태라고 볼 수 있다.

<br>

마지막으로 디코더의 FFNN의 경우 인코더의 FFNN과 동일하니 생략하겠다. 

이후에는 인코더와 마찬가지로 디코더 모듈을 쌓아서 "Decoders"를 만든다.

<br>
<hr>

드디어 Transformer의 구조를 모두 살펴봤다!!

마무리할 겸 Transformer의 구조를 다시 살펴보자.

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/transformer_attention_overview.PNG" width="60%">
</div>

(본 글의 모든 자료는 [딥러닝을 이용한 자연어 처리 입문](https://wikidocs.net/31379)로부터 유래하였습니다!)

### Reference
- [트랜스포머(Transformer)](https://wikidocs.net/31379)

