---
title: "Markov Process & Markov Chain"
toc: true
toc_sticky: true
categories: ["Machine Learning"]
---

"Machine Learning"을 공부하면서 개인적인 용도로 정리한 포스트입니다. 지적은 언제나 환영입니다 :)

# Introduction to Markov Property

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Markov Property<br>

A random process has the \<**Markov property**\> if the conditional probability distribution of future states of the process depends only upon the present state.

For sequential states $S_i$, the conditional probability follows

$$
p(S_{t+1} \mid S_0, S_1, \dots, S_t) = p(S_{t+1} \mid S_t)
$$

</div>

이런 \<Markov property\>를 가진 Random Process를 \<**Markov Process**\>라고 한다! [\<Markov Chain; 마르코프 연쇄\>](https://en.wikipedia.org/wiki/Markov_chain), [\<Brownian motion\>](https://en.wikipedia.org/wiki/Brownian_motion) 등이 \<Markov Process\>에 속한다.

<br/>

## vs. Memoryless Property

\<Markov Property\>는 과거(past)의 상태와 독립이라는 성질 때문에 "memoryless"라고 한다. 그런데, **"확률과 통계(MATH230)"** 정규 수업 시간에는 \<Memoryless property\>를 아래와 같이 정의했다.

$$
P(X > n + m \mid X \ge m) = P(X > n)
$$

두 개념은 서로 관련이 있는 것일까? 일단 두 개념이 정의된 상황을 명확히 봐야 할 필요가 있다.

\<**Markov Property**\>는 Random Process, 즉 sequence of RV의 dependency에 대해 묘사하고 있다. 반면에 \<**Memoryless Property**\>는 어떤 RV의 분포에 대해 묘사하고 있다. 즉, 둘이 정의된 상황 자체가 다르다.

즉, <span class="half_HL">\<Markov Property\>는 Random Process가 갖는 Memoryless에 대한 성질</span>이다. <span class="half_HL">\<Memoryless Property\>는 Random Value가 갖는 Memoryless에 대한 성질</span>이다.

<hr/>

## Markov Chain

\<Markov Chain; 마르코프 체인, 마르코프 연쇄\>는 \<Markov Property\>를 가진 **'Discrete'** Random Process를 의미한다.

만약 일기예보를 \<Markov Chain\>으로 모델링할 수 있다면, 오늘의 날씨를 통해 내일의 날씨를 확률적으로 예측하고, 다시 내일의 날씨 정보를 통해 모레의 날씨를 확률적으로 예측할 수 있게 된다.

\<Markov Process\>에서는 상태(State) $S$를 통해 각 과정을 묘사하고, 또 각 상태에서의 전이(transition)에 대한 확률을 아래와 같이 \<transition diagram\>으로 표현할 수 있다.

<div class="img-wrapper">
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Markovkate_01.svg/330px-Markovkate_01.svg.png" width="280px">
  <p><a href="https://en.wikipedia.org/wiki/Markov_chain">Wikipedia - Markov Chain</a></p>
</div>

또는 transition에 대한 정보를 행렬로 표현할 수도 있다. 이를 \<transition matrix; 전이 행렬\>이라고 한다.

<div style="width: 300px; margin: 0 auto" markdown="1">

|오늘/내일|맑음|흐림|
|:-------:|:--:|:--:|
|맑음|0.3|0.7|
|흐림|0.4|0.6|

</div>

\<Markov Chain\>의 장점은 <span class="half_HL">\<transition matrix\>를 $n$번 곱해 $n$일 후의 상태를 전이 확률을 알 수 있다</span>는 것이다! 예를 들어 모레의 전이 확률을 알고 싶다면, \<transition matrix\> $P$를 두번 곱하면 된다.

$$
P^2 = P P = \begin{pmatrix}
  0.3 & 0.7 \\
  0.4 & 0.6
\end{pmatrix} \begin{pmatrix}
  0.3 & 0.7 \\
  0.4 & 0.6
\end{pmatrix}
= \begin{pmatrix}
  0.37 & 0.63 \\
  0.36 & 0.64
\end{pmatrix}
$$

여기까지의 설명은 \<Markov Chain\>에 대한 정말 쉽고 간단한 설명이다. 위의 예시는 State $S$가 Discrete이고, Time 역시 Discrete이다. 그러나 \<Markov Chain\>의 개념을 확장하면, State-space가 continuous이거나 Time-space continuous한 경우도 생각해볼 수 있다. 🤩 더 자세한 내용은 [Wikipedia - Markov Chain](https://en.wikipedia.org/wiki/Markov_chain#Types_of_Markov_chains)을 살펴보자.


<hr/>

**요약.**

- \<Markov Property\>: Random Process에서의 Memorylessness에 대한 성질
- \<Markov Chain\>: Discrete Random Process with Markov Property
- \<Markov Model\>: \<Markov Chain\>을 따르는 모델


**더 읽을거리.**

- [HMM; Hidden Markov Model](https://en.wikipedia.org/wiki/Hidden_Markov_model)

<hr/>

# References

- [PuzzleData](https://www.puzzledata.com/blog190423/)