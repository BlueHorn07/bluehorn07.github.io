---
title: "Latent Matrix Factorization"
toc: true
toc_sticky: true
categories: ["Recommendation Algorithm"]
---

2021-1학기, 대학에서 '과제연구' 수업에서 진행하는 개인 프로젝트를 위해 개인적으로 정리한 포스트입니다. 지적과 교류는 언제나 환영입니다 :)

### Model Design

<div class="img-wrapper">
  <img src="https://t1.daumcdn.net/cfile/tistory/99A2523D5C910CD90B" width="400px">
  <p>
  (출처: <a href="https://yeomko.tistory.com/5?category=805638">갈아먹는 추천 알고리즘</a>)
  </p>
</div>

먼저 셋업을 좀 살펴보자.

행렬 $R \in \mathbb{R}^{N_u \times N_i}$은 평점 행렬로, 각 유저가 아이템에 대해 매긴 평점 정보가 들어있다. 이때, $N_u$는 총 유저의 수, $N_i$는 총 아이템의 수를 의미한다.

이제 이 행렬 $R$를 \<latent factor matrix\> $X$, $Y$로 분해해보자! <span class="half_HL">이때 "**latent factor의 차원**"을 정해야 하는데, $N_f$라고 설정해두자!</span> 보통 50에서 200 사이로 설정한다고 한다. 그래서 MF를 진행하면, 행렬 $X$, $Y$는 각각 $X \in \mathbb{R}^{N_f \times N_u}$, $Y \in \mathbb{R}^{N_f \times N_i}$가 된다.

\<Latent factor matrix\> $X$, $Y$는 각각 우리가 학습시키고자 하는 대상이다. 이 행렬들은 처음에 아주 작은 랜덤값으로 초기화된다. <small>(💥 행렬 $R$의 값을 쪼개어 생성하는 것이 아니다!)</small>

<div class="img-wrapper">
  <img src="https://t1.daumcdn.net/cfile/tistory/990FBA3A5C910D0A1A" width="400px">
  <p>
  (출처: <a href="https://yeomko.tistory.com/5?category=805638">갈아먹는 추천 알고리즘</a>)
  </p>
</div>

이제 우리는 factor matrix $X$, $Y$를 통해 평점 행렬의 prediction인 $\hat{R}$을 유도할 것이다. 방법은 간단한데, 그냥 $X$와 $Y$를 곱해주면 된다.

$$
\hat{R} = X^T \times Y
$$

이때, 행렬 $\hat{R}$의 원소인 $\hat{r}_{ui}$는 유저 $u$가 아이템 $i$에 대해 내리는 평점을 prediction한 것이다.

$$
\hat{r}_{ui} = x_u^T \times y_i
$$

즉, 사용자의 latent vector $x_u$와 아이템의 latent vector $y_i$를 곱해 평점을 추론하는 것이다. 그래서 <span class="half_HL">LMF 모델의 목표는 $$\hat{r}_{ui}$$가 최대한 $$r_{ui}$$와 가까워지도록 Latent Factor Matrix $$X$$, $$Y$$의 값을 조정하는 것이라고 보면 된다!</span>

<hr/>

### How to Train?: Loss

이제 모델을 학습시키기 위한 파트다. 방법은 strightforward 한데, 그냥 $$\hat{r}_ui$$와 $r_{ui}$ 사이의 값이 가까워지도록 두 값의 차이값을 minimize 하면 된다!

$$
L(X, Y) = \sum_{u, i} \left( r_{ui} - x_u^T y_i \right)^2
$$

Regularization 텀을 추가해주면 아래와 같다.

$$
L(X, Y) = \sum_{u, i} \left( r_{ui} - x_u^T y_i \right)^2 + \lambda \left( \sum_u \left| x_u \right|^2 + \sum_i \left| y_i \right|^2 \right)
$$

<hr/>

### Optimization

Loss Function을 디자인 했으니 이제 Optimization만 달성하면 된다. 두 가지 방법이 있는데, \<**Gradient Descent**\>와 \<**Alternating Least Squares**\>, 두 가지 알고리즘이 있다. 각각 어떻게 동작하는지 살펴보자!

#### Gradient Descent

GD는 그냥 Loss 함수 $L(X, Y)$를 미분하고 이에 대한 Gradient 값을 back-propagation 해주면 된다. 실제 동작은 딥러닝 프레임워크를 사용하면 쉽게 달성할 수 있다.

간단하게 $x_u$에 대해서만 GD를 적용해보자.

$$
\begin{aligned}
\frac{\partial}{\partial x_u} L(x_u, y_i) &= \frac{\partial}{\partial x_u} \left( \sum_i \left( r_{ui} - x_u^T y_i \right)^2  + \lambda \left( \left| x_u \right|^2 + \sum_i \left| y_i \right|^2 \right)\right) \\
&= \sum_i 2 \left( r_{ui} - x_u^T y_i \right) (-y_i) + \lambda (2 x_u)
\end{aligned}
$$

이제 이 Gradient 값으로 weight update를 진행하면 된다.

$$
x_u \leftarrow x_u + \alpha \cdot \left( \sum_i \left( r_{ui} - x_u^T y_i \right) (y_i) - \lambda x_u \right)
$$

💥 본인은 $y_i$의 차원 때문에 유도를 하고도 위의 식이 조금 헷갈렸는데, 셋업을 다시 보니까, $y_i$가 $N_f$ 차원의 열벡터였다 ㅋㅋㅋ

Gradient Descent 방식의 단점은 최적화를 시키는 과정이 너무 느리고, 많은 반복이 필요하다. 또, Global minimum이 아닌 local minimum에 stuck할 가능성이 있다 등등의 단점이 있다. ~~단점이 있긴 있다~~ 두번째 방법인 \<**Alternating Least Squares**\>는 이런 문제를 스마트하게 해결한다! 😎

<hr/>

#### Alternating Least Squares

\<Alternating Least Squares\>의 컨셉은 <span class="half_HL">$X$, $Y$ 둘 중 하나를 고정시키고, 다른 하나를 최적화 시킨다</span>는 것이다. 이 과정을 번갈아가면 반복, 즉 alternating 하면서 짧은 시간 내에 최적의 $X$, $Y$를 찾아낸다! (두 행렬을 한꺼번에 최적화시키는 것은 어렵다 💫)

먼저 \<ALS\>의 loss는 아래와 같이 정의된다.

$$
L(X, Y) = \sum_{u, i} c_{ui} \cdot \left(p_{ui} - x_u^T y_i \right)^2 + \lambda \left( \sum_u \left| x_u \right|^2 + \sum_i \left| y_i \right|^2 \right)
$$

\<GD\>에서의 Loss와 조금 달라졌는데, $c_{ui}$와 $p_{ui}$가 추가되었다. 이 두 값은 평점을 선호도(preference) $p$와 신뢰도(confidence) $c$로 나누어 접근한 것이라고 설명한다.

<br/>

먼저, 선호도 $p_{ui}$는 아래와 같이 정의된다. 평점 $r_{ui}$의 값에 의해 정의된다.

$$
p_{ui} = \begin{cases}
  1 & \text{if} \; r_{ui} > 0 \\
  0 & \text{if} \; r_{ui} = 0
\end{cases}
$$

이것은 유저가 평점을 남겼다면($r_{ui} > 0$), 유저가 선호도를 가진다는 것을 표현한 식이다. (\<ALS\>는 Implicit Dataset에서 이기 때문에 사용자가 선호와 비선호를 구분하지 않는다!)

<br/>

다음으로 신뢰도 $c_{ui}$는 아래와 같이 정의된다. 실제론 선호하지만, 평점이 없는 데이터를 위해 도입한 값이다.

$$
c_{ui} = 1 + \alpha r_{ui}
$$

우리는 선호도 $p_{ui}$를 통해 평점은 없는 데이터를 모두 0으로 바꾸었다. 그러나 여기에는 실제론 선호하지만, 평점이 없는 경우도 있을 수 있다. \<ALS\>에서는 이 경우를 데이터의 신뢰도가 낮은 것으로 해석한다!

\<ALS\>는 신뢰도 변수를 도입해 평점이 없는 데이터에 대한 예측도 전체 Loss Function에 영향을 주도록 만들었다. 이것은 평점이 없는 데이터를 모두 학습에서 제외한 \<Explicit Dataset\>과는 대조되는 점이다! 😲

신뢰도 변수 $c_{ui}$에는 평점 $r_{ui}$가 있는데, 이것을 통해 평점이 없는 데이터에는 낮은 $c$ 값을 부여해 loss에 포함하되 학습에 미치는 영향이 작도록 만들었다.

<br/>

'갈아먹는 추천 알고리즘'의 저자분께서는 신뢰도 변수 $c_{ui}$를 도입하는 이유를 "Implicit Dataset에 평점이 없는 데이터가 훨씬 많아, 실제 데이터셋은 훨씬 sparse한 matrix"라고 설명해주셨다. 만약, 평점이 없는 데이터를 배제하고 학습을 진행한다면, 이것은 학습에 대한 올바른 접근이 아니며, 설명 가중치가 낮더라도 그 수가 월등히 많기 때문에 학습에 유의미한 영향을 미치게 된다고 합니다.

이번에는 실제로 \<ALS\>의 동작을 살펴보자!

<div class="math-statement" markdown="1">

1\. 먼저 사용자와 아이템의 Latent Factor 행렬을 아주 작은 랜덤값으로 초기화 한다.

2\. 둘 중 하나를 상수로 고정시켜, Loss Function을 Convex Function으로 만든다.

3\. Loss를 편미분 한다. 미분 값을 0으로 만드는 행렬을 계산한다.

4\. [2-3] 반복

</div>

아이템의 Latent Factor를 고정하고, 사용자의 LF를 최적화 시켜보자.

아이템 행렬을 고정하고, \<ALS\>의 Loss를 사용자 $x_u$에 대해 미분하면 아래와 같다.

$$
\begin{aligned}
\frac{\partial L(x_u)}{\partial x_u}
&= \frac{\partial }{\partial x_u} \left[ \sum_{i} c_{ui} \cdot \left(p_{ui} - x_u^T y_i \right)^2 + \lambda \left( \left| x_u \right|^2 + \sum_i \left| y_i \right|^2 \right) \right] \\
&= \left[\sum_{i} c_{ui} \cdot 2 \left( p_{ui} - x^T_u y_i \right) ( -y_i)\right] + 2 \lambda x_u = 0
\end{aligned}
$$

식을 정리하면,

$$
\left(\sum_i c_{ui} \cdot x_u^T y_i \cdot y_i\right) + \lambda x_u = \sum_i c_{ui} \cdot p_{ui} \cdot y_i
$$

식을 $x_u$에 대해 정리해야 하기 때문에, scalar $x_u^T y_i$를 $y_i^T x_u$로 바꿔준다.

$$
\left(\sum_i c_{ui} \cdot y_i^T x_u \cdot y_i\right) + 2 \lambda x_u = \sum_i c_{ui} \cdot p_{ui} \cdot y_i
$$

이제 좌변을 $x_u$에 대해 묶어준다.

$$
\begin{aligned}
\left(\sum_i c_{ui} \cdot y_i^T x_u \cdot y_i\right) + 2 \lambda x_u
&= \left(\sum_i c_{ui} \cdot y_i \cdot y_i^T x_u \right) + 2 \lambda x_u \\
&= \left(\sum_i c_{ui} \cdot y_i y_i^T \right) x_u + 2 \lambda x_u \\
&= \left[ \left( \sum_i c_{ui} \cdot y_i y_i^T \right) + 2 \lambda I \right] x_u
\end{aligned}
$$

위의 식에는 합(合)으로 이루어져 있어 식을 결과를 얻는데 조금 불편하다. 그래서 아래의 과정을 통해 식을 좀더 단순화해보자!

아이템 행렬 $Y = [y_1, y_2, \cdots, y_i]$에 대해 $Y \times Y^T$는 아래와 같다.

$$
YY^T
= \left[ y_1 y_1^T + y_2 y_2^T + \cdots + y_i y_i^T \right] = \sum_i y_i y_i^T
$$

하지만, 위의 식에는 신뢰도 $c_{ui}$가 빠져있다. 이것은 diag matrix를 통해 쉽게 해결할 수 있다!

Let $C_u$ be

$$
C_u = \begin{pmatrix}
  c_{u1} & 0 & 0 & 0 \\
  0 & c_{u2} & 0 & 0 \\
  0 & 0 & \ddots & 0 \\
  0 & 0 & 0 & c_{ui} \\
\end{pmatrix}
$$

then, $YC_uY^T$ is

$$
YC_uY^T
= \left[ c_{u1} y_1 y_1^T + c_{u2} y_2 y_2^T + \cdots + c_{ui} y_i y_i^T \right] = \sum_i c_{ui} y_i y_i^T
$$

우변도 식을 정리해보자.

$$
\begin{aligned}
Y^T C_u p_u
&= [y_1, \dots, y_i] \begin{pmatrix}
  c_{u1} & 0 & 0 & 0 \\
  0 & c_{u2} & 0 & 0 \\
  0 & 0 & \ddots & 0 \\
  0 & 0 & 0 & c_{ui} \\
\end{pmatrix} \begin{pmatrix}
  p_{u1} \\
  \vdots \\
  p_{ui}
\end{pmatrix} \\
&= [c_{u1}y_1, \dots, c_{ui}y_i] \begin{pmatrix}
  p_{u1} \\
  \vdots \\
  p_{ui}
\end{pmatrix} \\
&= p_{u1}c_{u1}y_1 + \cdots + p_{ui}c_{ui}y_i = \sum_i c_{ui}p_{ui}y_i
\end{aligned}
$$

이제 위의 결과를 바탕으로 (준식)을 다시 기술해보자.

$$
\begin{aligned}
\left[ \left( \sum_i c_{ui} \cdot y_i y_i^T \right) + \lambda I \right] x_u
&= \sum_i c_{ui} \cdot p_{ui} \cdot y_i \\
(YC_uY^T + \lambda I) x_u
&= Y^T C_u p_u
\end{aligned}
$$

이제 $x_u$를 얻기 위해 좌변의 행렬을 우변으로 옮기면,

$$
x_u = (YC_uY^T + \lambda I)^{-1} \cdot Y^T C_u p_u
$$

식을 약간 다듬으면,

$$
x_u = (Y^TC_uY + \lambda I)^{-1} \cdot Y^T C_u p_u
$$

끝!! 이렇게 구한 $x_u$로 사용자 행렬 $X$를 업데이트 하면 된다!!

다음에는 사용자 행렬 $X$를 고정하고, 아이템 행렬 $Y$에 대해 동일한 작업을 수행해 $y_i$를 구하면,

$$
y_u = (X^TC_iX + \lambda I)^{-1} \cdot X^T C_i p_i
$$

<br/>

보통 반복 횟수를 10~15회 정도로 진행하면, 데이터 크기와 sparse 정도에 따라 횟수는 조정된다고 한다.

\<ALS\> 알고리즘에 대한 구현은 '갈아먹는 추천 알고리즘'의 저자 '형준킴'님의 포스트를 참고하길 바란다.

👉 [갈아먹는 추천 알고리즘 [5] ALS 구현하기](https://yeomko.tistory.com/8?category=805638)

<hr/>

### references

- [갈아먹는 추천 알고리즘 [3]](https://yeomko.tistory.com/5?category=805638)
- [갈아먹는 추천 알고리즘 [4]](https://yeomko.tistory.com/4?category=805638)
- ['vvakki_'님의 포스트](https://velog.io/@vvakki_/Matrix-Factorization-2)