---
title: "Classification, Logistic Regression"
layout: post
use_math: true
tags: [machine_learning]
---


본 글은 2018-2학기 Stanford Univ.의 Andrew Ng 교수님의 Machine Learning(CS229) 수업의 내용을 정리한 것입니다. 지적은 언제나 환영입니다 :)

-- [lecture video](https://youtu.be/het9HFqo1TQ)

<hr>

## Classification
이번 파트에서는 **Classification Problem**에 대해 다룬다. 알아둘 점은 Classification 역시 Regression의 한 종류라는 것이다. 단지 Predicted Value가 연속이 아니라 이산적일 뿐이다. 그리고 이번 파트에서는 Classification Problem 중에서도 $\\{ 0, 1 \\}$의 **Binary Classification Problem**을 다룬다! [^1]

<hr>

### Failure of Linear Regression

앞선 강의에서는 Linear Regression의 hypothesis $h_{\theta}(x) = w^{T} \cdot x + b$로 문제를 해결하고, prediction 하였다. 하지만, Classification에서는 2가지 문제점 때문에 그럴 수 없다.

<big>**P1.**</big> Linear Regression의 경우 입력으로부터 오는 Loss의 총합을 최소화하는 것을 목표로 한다. 따라서 Classification 상황에서 다음과 같은 Boundary를 만들어 낸다.

<div style="text-align: center;">
<img src="https://i.stack.imgur.com/nEC4H.png" style="width: 70%;">
</div>

<big>**P2.**</big> Classification은 $\\{ 0, 1 \\}$의 출력 결과를 원한다. 하지만, Linear Regression에서는 0보다 작거나 1보다 큰 값을 출력해버린다.

기존 모델인 Linear Regression은 Classification에서 적합하지 않다는 것이 밝혀졌다. 우리는 Classification을 위한 새로운 모델이 필요하다!

### Logistic Regression (a.k.a. sigmoid function)

앞에서도 언급 되었듯이 우리는  $\\{ 0, 1 \\}$의 출력 결과를 원한다. 그래서 hypothesis $h_{\theta}(x)$를 다음과 같이 설계할 것이다.

<div>
$$h_{\theta}(x) = g(\theta^{T}x) = \frac{1}{ 1+e^{-\theta^{T}x} } \in [0, 1]$$
</div>

우리는 함수 $g(z)= \frac{1}{ 1+e^{-z} }$와 같은 꼴을 **Logistic function** 또는 **sigmoid function**이라고 부른다.

새롭게 모델링한 함수 $h_{\theta}(x)$는 출력 값이 $[0, 1]$ 사이로 강제된다!

#### Why we choose 'sigmoid' function?

우리는 왜 하필 sigmoid 함수를 사용하는 것일까? 출력 값을 $[0, 1]$ 사이로 강제한다면, sigmoid 말고도 다른 smooth function을 사용할 수도 있는데 말이다.

그 이유는 lecture 4의 [GLM]({{site.baseurl}}/2020/09/12/Lec4-3.html)<sub>Generalized Linear Model</sub>에서 다루게 된다!

<hr>

### Logistic Regression & Probabilistic Approach

$\textrm{MLE} \equiv \textrm{LMS}$를 보이기 위해 몇가지 가정을 했듯이, Logistic Regression에서도 몇가지 가정을 도입할 것이다.

<div>
$$
\begin{split}
P(y=1 \vert x; \theta) &= h_{\theta}(x) \\
P(y=0 \vert x; \theta) &= 1 - h_{\theta}(x) \\
\end{split}
$$
</div>

그리고 이것을 하나의 equation으로 다시 써보면

<div>
$$P(y \vert x; \theta) = (h_{\theta}(x))^y (1-h_{\theta}(x))^{(1-y)}$$
</div>

이제 MLE 과정을 위 식을 바탕으로 다시 해보자!

<div>
$$
\begin{split}
L(\theta) &= P(\vec{y} \vert x; \theta) \\
&= \prod _{ i }^{m} { p(y^{(i)} \vert x^{(i)}; \theta) } \\
&= \prod _{ i }^{m} { \left( h_{\theta}(x^{(i)})\right)^{y^{(i)}} \left( 1- h_{\theta}(x^{(i)})\right) ^ {(1-y^{(i)})}}
\end{split}
$$
</div>

그리고 준식에 $\log$를 취하면

<div>
$$
\begin{split}
l(\theta) &= \log {L(\theta)} \\
&= \sum_{i}^{m} { y^{(i)}\log{h(x^{(i)})} + (1-y^{(i)})\log{\left( 1-h(x^{(i)}) \right)} }
\end{split}
$$
</div>

여전히 우리의 목표는 Likelihood를 Maximize하는 것이다. 그리고 이를 위해 다음과 같은 **GD** 방식을 사용할 것이다.

<div>
$$\theta := \theta + \alpha \nabla_{\theta}(l(\theta))$$
</div>

식을 보면, 기존의 GD과는 달리 Gradient 텀이 바뀌었다. log likelihood의 Gradient를 더하는($+$)는 형태로 바뀌었다.

그 이유는 Likelihood의 경우 Maximize 하는 **Gradient Ascent** 과정이기 때문이다!

이제 Gradient 텀을 구하면

<div>
$$
\begin{split}
\frac{\partial}{\partial \theta_{j}} l(\theta) &= \left( y \frac{1}{g(\theta^{T}x)} - (1-y)\frac{1}{1 - g(\theta^{T}x)} \right) \frac{\partial}{\partial \theta_{j}} g(\theta^{T}x) \\
&= \left( y \frac{1}{g(\theta^{T}x)} - (1-y)\frac{1}{1 - g(\theta^{T}x)} \right) g(\theta^{T}x)(1-g(\theta^{T}x))\frac{\partial}{\partial \theta_{j}} \theta^{T}x \\
&= \left( y (1 - g(\theta^{T}x)) - (1 - y) g(\theta^{T}x) \right) x_j \\
&= (y-h_\theta(x))x_j
\end{split}
$$
</div>

정리하면 결국

<div>
$$\theta := \theta + \alpha (y-h_\theta(x))x_j$$
</div>

Gradient Ascent의 최종적인 형태를 보면, LMS에서의 GD와 상당히 닮아있다! 

<div>
$$
(\textrm{Gradient Ascent}) \\
\theta := \theta + \alpha (y-h_\theta(x))x_j
$$
$$
(\textrm{Gradient Descent}) \\
\theta := \theta - \alpha (y-h_\theta(x))x_j
$$
</div>

하지만, 이 둘은 명백히 다른 알고리즘이다! 왜냐하면, hypothesis $h_{\theta}(x^{(i)})$가 non-linear function인 sigmoid이기 때문이다!

<hr>

LMS과 Classification의 Learning Algorithm이 유사한 형태를 띄는 것은 상당히 흥미롭다. 두 알고리즘의 유사성은 단순히 우연에서 기인하는 것일까? 이 질문에 대한 해답이 [다음 lecture]({{site.baseurl}}/2020/09/12/Lec4-3.html)에 있다!

<br>

<hr>

### 맺음말

이래저래 Binary Classification에서의 Learning Algorithm을 살펴봤다. 이번 문단에서 그 의미만을 간단하게 요약해보자.

- Classification에서는 $\\{ 0, 1 \\}$에 맞추기 위해 **sigmoid**를 $h_{\theta}(x)$로 사용한 새로운 모델을 사용했다.
- 그 모델에서는 Loss 대신 Likelihood를 Maximize 했다.
  - 이를 위해 **Gradient Ascent** 방식으로 $\theta$를 최적화 했다.
  - 놀랍게도 LMS에서의 GD와 그 형태가 유사했다!!

<hr>

[^1]: Binary Classification을 Multi-class를 일반화하게 되면, Generalized Linear Regression의 형태가 된다! (Lecture 4에서 다루게 됨.)



