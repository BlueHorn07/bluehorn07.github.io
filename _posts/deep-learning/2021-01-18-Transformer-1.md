---
title: "Transformer(2017) - 1"
layout: post
tags: ["research"]
use_math: true
---


이 포스트는 제가 개인적인 용도로 정리한 글 입니다. 

- "[Attention is all you need](https://arxiv.org/abs/1706.03762)"
  - 2017, Google

1. [Attentionn Mechanism](#attention-mechanism2015)
2. [Transformer](#transformer2017)
3. [Three Attentions in Transformer](#three-attentions-in-transformer)

<br>
<hr>

[이곳](https://wikidocs.net/22893)에서 많은 도움을 받았습니다! 포스트에 사용된 사진은 대부분 좌측의 링크에서 가져온 것 입니다!

### Attention Mechanism(2015)

#### 기존 seq2seq 모델의 한계

seq2seq 모델은 인코더-디코더 구조로 이루어져 있음. 하지만, 인코더에서 입력 sequence를 하나의 context vector로 압축하는 과정에서 입력의 정보가 일부 손실됨.

그리고 RNN의 고질적인 문제인 입력 시퀀스가 길어지면, 출력 시퀀스의 정확도가 떨어진다는 문제도 발생함.

이것을 해결하기 위해 제시된 것이 **Attention Mechanism**!!

#### Attention Mechanism


<div class="statement" markdown="1">

"Attention의 기본 아이디어는 디코더에서 출력 단어를 예측하는 매 시점(time step)마다, 인코더에서의 전체 입력 문장을 다시 한번 참고한다는 점이다.

단, 입력 문장 전체를 전부 동일한 비율로 참고하는 것이 아니라, 해당 시점에서 예측해야 할 단어와 연관이 있는 입력 단어 부분을 좀더 집중(atteion)해서 보게 된다!"

</div>

##### Attention Function

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/22893/%EC%BF%BC%EB%A6%AC.PNG" width="50%">
</div>

<div class="statement" markdown="1">

**Attetion Function**은 주어진 Query $Q$에 대해 모든 키 $K$와의 "유사도"를 각각 구한다. 그리고 이 유사도를 키와 묶여있는 값 $V$에 반영해준다. 출력인 Attention Value는 이 값 $V$에 유사도를 반영한 결과를 모두 더해서 리턴한 것이다.

</div>

이제 $Q$, $K$, $V$를 좀더 구체화 해보자.

- Query $Q$: $t$ 시점의 디코더 셀에서의 은닉 상태
- Keys $K$: 모든 시점의 인코더 셀의 은닉 상태
- Values $V$: 모든 시점의 인코더 셀의 은닉 상태

#### Dot-Product Attention

Attention을 적용하는 방법에도 여러 종류가 있는데, "Dot-Product Attention"은 그중에서도 가장 간단한 방식이다.

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/22893/dotproductattention1_final.PNG" width="50%">
</div>

본래 디코더에선 $t$ 시점에서의 출력 단어를 예측하기 위해 두 가지 입력값이 필요했다.

1. $t-1$ 시점의 은닉상태; $s_{t-1}$
2. $t-1$ 시점의 출력단어

그런데 Attention Mechanism에서는 출력 단어 예측에 **Attention Value** $a_t$를 추가하여 출력 단어를 예측하게 된다!

<br>

<big>Step 1: Calculate Attention Scores</big>

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/22893/dotproductattention2_final.PNG" width="50%">
</div>

인코더의 은닉상태 $h_i$와 디코더의 현재 시점 $t$에서의 은닉상태를 $s_t$라고 하자. 그리고 논의의 편의를 위해 $h_i$와 $s_t$의 차원은 같다고 가정한다.

Dot-Product를 통해 인코더의 은닉 상태 각각이 디코더의 현재의 은닉 상태 $s_t$와 얼마나 유사한지 판단한다. 이 유사도 값을 Attention Score라고 한다.

$$
\textrm{score}(s_t, h_i) = {s_t}^T \cdot h_i
$$

$s_t$와 인코더의 모든 은닉 상태와의 Attention score를 모아서 벡터로 표현한 것을 $e^t$라고 표현하자. 그러면 $e^t$는 아래와 같다.

$$
e^t = [{s_t}^T h_1\, , \, \dots \, , \, {s_t}^T h_N]
$$

<br>

<big>Step 2: Get Attention Distribution by Softmax</big>

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/22893/dotproductattention3_final.PNG" width="60%">
</div>

$e^t$에 Softmax 함수를 적용해, 확률 분포 **Attention Distribution**을 얻는다. <br>
이때, 각각의 값은 Attention Weight가 된다.<br>
<small>(간단하게 생각하면, 그냥 $e^t$를 normalize한 것이다.)</small>

$$
\alpha^t = \textrm{softmax}(e^t)
$$

<br>

<big>Step 3: Get Attention Value</big>

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/22893/dotproductattention4_final.PNG" width="60%">
</div>

각 인코더의 은닉 상태 $h_i$와 앞에서 얻은 Attention Weight를 곱한다. 이후 그 모두를 더해준다.

간단하게 생각하면 Attention Weight으로 가중합을 하는 것이다.

$$
a_t = \sum^{N}_{i=1} { {\alpha^t}_i \cdot h_i }
$$

이 Attnetion value$a_t$는 인코더의 문맥을 포함하고 있다고 하여 **"Context Vector"**라고도 불린다. seq2seq에선 인코더의 마지막 은닉 상태를 Context Vetor라고 부른 것과는 다르다!

<br>

<big>Step 4: Concatenate Attention Value and Decoder Hidden State</big>

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/22893/dotproductattention5_final_final.PNG" width="60%">
</div>

디코더의 은닉 상태인 $s_t$에 Attention Value $a_t$를 Concatenate하여 하나의 벡터 $v_t$로 만든다.

이 $v_t$를 입력으로 삼아 $\hat{y}$를 예측한다.

그 이후는 일반적인 RNN과 동일하다.

<br>
<hr>

### Transformer(2017)

RNN의 문제점을 해결하기 위해 Attention 모델이 제시되었다. Transformer에서는 Attention을 새로운 시각으로 접근한다.

<div style="text-align:center">
<big>"Attention을 디코더의 hidden state를 보정하는 용도로만 쓰는게 아니라<br>아예 Attention으로 동작하는 인코더-디코더를 만들어보자!"</big>
</div>

<br>

<span style="color:red">Transformer는 RNN을 사용하지 않는다!</span> 그러니 기존의 seq2seq처럼 인코더-디코더 구조는 그대로 가지고 있다. 다만, 기존 인코더-디코더 구조를 완전히 따르고 있는 건 아닌데, 인코더-디코더가 1:1로 존재하는 것이 아니라 **다수:다수로 존재하는 구조**이기 때문이다!!

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/transformer2.PNG" width="26%">
<img style="margin: 5px" src="https://wikidocs.net/images/page/31379/transformer4_final_final_final.PNG" width="60%">
</div>

그래서 다수:다수 구조를 고려하여 "Encoder-Decoder"가 아니라 **"Encoders-Decoders"**라고 표현한다.

#### Positional Encoding

Tranformer는 기존 인코더-디코더 모델과는 달리 입력 Sequence를 그대로 받는 구조가 아니라 **"Positional Encoding"**로 전처리된 값을 입력으로 받는다.

기존 RNN에서는 Sequence를 단어별로 순차적으로 입력 받기 때문에 단어의 위치 정보(Positional Information)이 알아서 담기게 된다. 물론 그럼에도 불구하고 RNN에서도 Encoding 과정은 거친다! 이를 **"워드 임베딩(Word Embedding)"**이라고 한다. [link](https://wikidocs.net/33520)

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/transformer5_final_final.PNG" width="65%">
<br>
<img style="margin:15px" src="https://wikidocs.net/images/page/31379/transformer6_final.PNG" width="65%">
</div>

이제 Transformer에서 어떤 방식으로 Positional Encoding Vector를 생성하는지 살펴보자.

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/transformer7.PNG" width="40%">
</div>

Transformer는 $(\textrm{pos}, i)$를 정보를 바탕으로 Positional Encoding을 생성한다. 이때, $\textrm{pos}$는 입력 sequence 내에서 단어의 위치를 의미하고, $i$는 embedding vector의 차원 내에서의 인덱스를 의미한다.

$\textrm{pos}$에 대한 Positional Encoding은 아래의 함수에 의해 얻을 수 있다.

$$
\begin{aligned}
    \textrm{PE}(\textrm{pos}, 2i) &= \sin \left( \left({\textrm{pos} / 10000}\right)^{2i/d_{\textrm{model}}} \right) \\
    \textrm{PE}(\textrm{pos}, 2i + 1) &= \cos \left( \left({\textrm{pos} / 10000}\right)^{2i/d_{\textrm{model}}} \right)
\end{aligned}
$$

(위의 그림에선 $d_{\textrm{model}}=4$로 설정하였지만, 실제 논문에선 512로 설정되어 있다고 한다.)

위와 같은 Positional Encoding 방식을 사용하면, 입력 sequence에 대한 순서 정보가 보존된다고 한다! 이를 통해 같은 단어일지라도 문장 내의 위치에 따라서 Transformer의 입력으로 들어가는 Enbedding Vector의 값이 달라지는 것이다!

<br>
<hr>

### Three Attentions in Transformer

Transformer에선 3가지 방식으로 Attention을 사용한다.

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/attention.PNG" width="30%">
</div>

그림에서 회색, 초록색, 파란색의 화살표는 각각 Query, Key, Value를 의미한다. (아마도)

먼저 **"Self-Attention"**이란 무엇인지 살펴보자. Self-Attention은 Query와 Key, Value 동일한 곳에서 유래한다면, "Self-Attention"이라고 한다. 예를 들어 3번째 Attention Model의 경우 Query가 디코더에서 유래하기 때문에 Self-Attention이 아니다. [Attention Mechanism](#attention-mechanism)에서 살펴본 모델도 Self-Attention Model이 아니다.

1번째 모델의 경우, Self-Attention이며 인코더에서 이루어진다. 반면에 2번째와 3번째 Attention Model의 경우 디코더에서 이루어진다. <br>
<small>(2번째는 Self-Attention이긴 한데, Q, K, V가 모두 디코더의 벡터로 이루어지는 것이다.)</small>

Transformer는 위의 3가지 Attention을 모두 사용한다! 각각이 Transformer 아키텍처의 어디에서 사용되는지 표현하면 아래와 같다.

<div class="img-wrapper">
<img src="https://wikidocs.net/images/page/31379/transformer_attention_overview.PNG" width="65%">
</div>

<br>
<hr>

두번째 포스트에선 Transformer 모델의 인코더와 디코더, 그리고 3가지 Attention Model에 대해 더 자세히 살펴봅니다.

- [Trasnformer(2017) - 2]({{"2021/01/18/Transformer-2.html" | relative_url}})

### Reference
- [트랜스포머(Transformer)](https://wikidocs.net/31379)


