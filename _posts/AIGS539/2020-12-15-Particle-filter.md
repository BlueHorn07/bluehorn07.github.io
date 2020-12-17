---
title: "Particle Filtering"
layout: post
use_math: true
tags: [AIGS539]
---

## 서론
본 글은 2020-2학기 "컴퓨터 비전" 수업을 듣고, 스스로 학습하면서 개인적인 용도로 정리한 것입니다. 지적은 언제나 환영입니다 :)

<hr>

### Particle Filtering

**<u>Definition.</u>**<br>
<div class="statement">
  Tool for tracking the state of a dynamic system(= change in time) modeled by a Bayesian network.
</div>

<div class="statement">
  if you have a model of how the system changes in time, possibly in response to inputs, and a model of what observations you should see in particular states, you can use <b><u>particle filters</u></b> to track your belief state. 
</div>

<br>

우리는 Particle Filter를 BBox prediction에 사용할 것이다. 그래서 

<div class="statement" style="text-align: center;">
"Particle" = BBox
</div>

가 된다.

<hr>

#### Bayes Filter

**<u>Particle Filter</u>**는 사실 **<u>Bayes Filter</u>**의 일종이다.

**<u>Definition.</u>**<br>
<div class="statement">
  Used for estimating the state of a dynamical system from sensor measurements. <br>
  "Bayes Filter" works under the process of Predict/update cycle.
</div>

Bayes Filter의 예로는 이 포스트에서 살펴보는 **<u>Particle Filter</u>**와 **<u>Kalman Filter</u>**가 있다.

<br>

<hr>

아래는 Particle Filter의 과정을 도식화한 것이다.

샘플링한 BBox가 얼마나 Ground truth와 비슷한지에 따라 weight를 다르게 부여한다.

<div class="img-wrapper">
  <img src="https://1.bp.blogspot.com/-MHEOooeFWsU/XvnXAsWm70I/AAAAAAAACaI/Q5Ef9yZa6ykO-12j85St7XkWxtt9b1pOwCK4BGAsYHg/w640-h454/particle%2Bfilter.jpg" style="width:60%;">
</div>

위 그림에서 검은색 원들은 Particle을 의미한다. 이 Particle에는 위치 데이터 `(x, y)`와 weight `w`에 대한 정보가 포함된다. weight 값이 클수록 원의 크기가 크게 표현되어 있다.

##### (1) Resampling
weight를 이용해 particle을 weighted sampling 한다.

이 과정을 거치면, 각 Particle이 $1/N$의 weight를 Uniform하게 가지게 된다.

##### (2) Prediction
resampling해서 얻은 결과를 랜덤하게 흩뜨리는 과정이다.

state transition $p(x_t \mid x_{t-1})$을 적용하는 부분이다. 

칼만 필터의 경우, 여기에서 Linear model assumption을 적용한다.

$$
p(x_t \mid x_{t-1}) = Ax^{t-1} + b + \epsilon \quad ( \epsilon \sim N(0, \Sigma))
$$

하지만, Particle Filtering은 Sequential Bayesian Modeling에 의해 진행한다고 한다. (아마도?) 아직 이 부분이 잘 와닿지 않는다 ㅠㅠ

##### (3) Measures 
prediction한 particle인 BBox가 실제 G.T.와 얼마나 비슷한지 Similarity를 측정하는 부분이다.

##### (4) Update
Similarity를 바탕으로 weight를 업데이트해준다.

<hr>

### 참고자료
- [쉽게 설명한 파티클 필터(particle filter) 동작 원리와 예제](https://ryanclaire.blogspot.com/2020/06/particle-filter-principle.html)