---
title: "Transformer(2017) - 2"
layout: post
tags: ["research"]
use_math: true
---


이 포스트는 제가 개인적인 용도로 정리한 글 입니다. 

- "[Attention is all you need](https://arxiv.org/abs/1706.03762)"
  - 2017, Google

Transformer에 대한 첫번째 포스트는 [이곳]({{"2021/01/18/Transformer-1.html" | relative_url}})에서 볼 수 있습니다.

1. [Encoder in Transformer](#encoder-in-transformer)
   1. Self-Attention
   2. FFNN

<br>
<hr>

### Encoder in Transformer

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/transformer9_final_ver.PNG" width="35%">
</div>

앞에서도 언급했듯이 Transformer의 인코더 모델은 다수의 Encoder가 쌓여있는 구조다. 논문에서는 `num_layers=6`으로 설정하여 인코더 모델을 구축하였다.

하나의 인코더 내부에는 두 가지 Layer가 또 존재한다.

- Self-Attention
- Feed Forward Neural Network; FFNN

#### Self-Attention in Encoder Module

기존 Attention의 경우 Query $Q$가 $t$ 시점에서의 디코더의 은닉 상태였다.

하지만, Self-Attention의 경우, 이 Query $Q$가 디코더가 아닌 인코더에서 유래한다. 또한, $t$ 시점이 아닌, 인코더의 전체 시점에 대한 은닉 상태로 Query $Q$를 생성할 수 있다!

Self-Attention을 활용하면, 아래와 같이 문장 내에서 지시하는 대상을 찾도록 유사도를 학습시킬 수 있다고 한다.

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/transformer10.png" width="33%">
</div>

Transformer는 더이상 RNN을 사용하지 않는다. 그래서 '은닉 상태'라는 개념이 없다. 그렇기 때문에 $Q$, $K$, $V$를 은닉 상태가 아닌 다른 방식으로 정의하게 된다.

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/transformer11.PNG" width="34%">
</div>

위의 같이 단어의 embedding vector에 $Q$, $K$, $V$에 대응하는 가중치 $W^Q$, $W^K$, $W^V$를 곱하여 $Q$, $K$, $V$를 얻는다. (가중치는 각 단어에서 공유되는 값인가? 아마 그렇겠지?)

각 단어에 대한 $Q$, $K$, $V$ 값을 구할 수 있다면, 다음 과정은 앞에서 다룬 Attention Mechanism과 완전히 동일하다.

Query $Q$ 벡터는 모든 $K$ 벡터에 대해 Attention score를 구하고, softmax로 Attention distribution를 구한다. 그리고 Attention distribution에 $V$ 벡터를 가중합하여 Attention Value를 구한다. 이 과정을 모든 Query $Q$ 벡터에 대해 반복한다.

이때, 앞에서 다룬 Attetion Mechanism과 달리 Dot-Product Attetion이 아닌, Dot-Product 결과에 특정값을 나누는 $\textrm{score}(q, k) = \frac{q \cdot k}{\sqrt{n}}$를 사용한다. 이런 Attention Function을 **"Scaled Dot-Product Attention"**이라고 한다.

Attention Function으로 Attention Score를 구한 후에는 기존 방식처럼 Softmax를 씌워 Attention Distribution을 구하고, 각 $V$ 벡터와 가중합하여 Attention Value를 얻는다!

[참고한 자료](https://wikidocs.net/31379)에서는 위의 과정을 word 벡터 하나하나가 아니라 word 벡터를 모아 행렬로 취급해 sequence 전체에 대해 한번에 진행할 수 있다고 한다. 자세한 설명은 여기선 생-략

그래서 행렬의 방식으로 결과를 종합하면 아래와 같다.

$$
\textrm{Attention}(Q, K, V) = \textrm{softmax} \left( \frac{Q \cdot K^{T}}{\sqrt{d_k}}\right) \cdot V
$$

뒤에는 Multi-head에 대한 설명이 더 자세히 있던데, 흠... 이해가 안 되니 생략하자.

<br>
<hr>

#### FFNN

생각보다 간단한 모델이다.

그냥 평범한 Neural Network에 불과.

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/transformer20.PNG" width="70%">
</div>

앞에 "Position-wise"라는 말이 붙었는데, 그냥 Fully-connected라는 것과 마찬가지다!

이것 말고도 FFNN 모델에 residual connection과 normalization을 연결해 더 정교하게 만들어 준다.

<br>

이렇게 Self-Attention layer와 FFNN layer로 인코더 모듈 하나를 만든 후에는 인코더 모듈을 쌓아서 Encoders를 만들어 준다.

<br>

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/transformer_from_encoder_to_decoder.PNG" width="70%">
</div>
인코더의 마지막 출력층에서의 결과가 디코더의 입력으로 전달된다.

<br>
<hr>



### Reference
- [트랜스포머(Transformer)](https://wikidocs.net/31379)

