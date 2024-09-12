---
title: "Differential Equation - Basic"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
---

2019-2학기, 대학에서 들은 '미분방정식' 수업을 복습하는 차원에서 정리하게 된 글입니다. 지적은 언제나 환영입니다 :)

## 미분 방정식이란?

먼저 \<미분 방정식\>에 대한 내용을 들어가기에 앞서, 미분 방정식이 무엇인지 확인해보자.

우리에게 $y(t) = \sin t$라는 함수가 주어졌다고 해보자. 그러면, 이 sin 함수인 $y(t)$는 당연히 아래의 식을 만족할 것이다.

$$
y^2 + \cos^2 t = 1
$$

이때, $\cos t = (\sin t)'$임을 알고 있으므로 위의 식을 바꿔쓰면 아래와 같다.

$$
y^2 = (y')^2 = 1
$$

또는 $y'' = -\sin t$임을 활용해 아래와 같은 방정식을 세울 수도 있을 것이다.

$$
y'' + y = 0
$$

우리는 위의 두 방정식과 같이 방정식 안에 \<**derivative**\> 텀이 존재하는 것을 \<**미분 방정식 differential equations**\>라고 부를 것이다.

이때, 미분 방정식에서 가장 높은 derivative의 차수를 DE의 "**order**"라고 한다.

마지막으로 DE를 만족할 수 있는 가능한 모든 $y(t)$를 찾는 것을 우리는 "**solve ODE**"라고 한다.

<hr/>

DE에는 크게 두가지 타입이 있다.

<span class="statement-title">Type I.</span><br>

$$
y' = f(t) \quad \rightarrow \quad y = \int f(t) dt + C
$$

<span class="statement-title">Type II.</span><br>

$$
y' = ky \quad \rightarrow \quad y = Ce^{kt}
$$

초급 수준의 DE는 모두 위의 두 가지 타입을 잘 조합한 것에 불과하기 때문에 식을 잘 정리하면 쉽게 DE를 풀 수 있다.

<hr/>

## Linear ODE

앞에서 ODE의 두 가지 타입을 살펴봤다. 그런데, 이 두 가지 타입은 지금 소개할 **Linear ODE**의 특수한 경우다.

<div class="definition" markdown="1">

<span class="statement-title">Definition.</span> Linear ODE<br>

우리는 아래와 같은 형태의 DE를 \<**Linear ODE**\>라고 부른다.

$$
y' = a(t) y + b(t)
$$

</div>

이런 Linear ODE의 예를 살펴보자.

$$
y' = t^2 y + \sin t
$$

위와 같은 DE는 Type I도, Type II도 아니다. 그래서 아래에 소개할 \<**Integrating Factor**\> 방법으로 식을 변형해 Type I or II로 DE를 reduction 시켜야 한다.

## Integrating Factor

For given ODE, $y' = ay + b$, let's introduce $\mu (t) \ne 0$. Then

$$
y' = ay + b \iff \mu y' = \mu (ay + b)
$$

이제 우변에서 y의 텀을 좌변으로 옮겨주자.

$$
\mu y' = \mu (ay + b) \iff \mu y' - \mu ay = \mu b
$$

이제 $\mu$에 대해 $\mu' = - a \mu$라고 가정하자! (이 부분에서 잘 와닿지 않을 수도 있는데, 이렇게 해도 지금까지의 과정을 violate 하지 않음을 상기하라!)

그러면, 직전의 DE에서 좌변은

$$
\mu y' - \mu ay = \mu y' + \mu' y = (\mu y)'
$$

가 된다. 따라서, 준식은

$$
(\mu y)' = \mu b
$$

가 되므로 우리가 풀기 쉬운 **Type I**이 되었다!! 이제 이 $(\mu y)$에 대한 DE를 풀면

$$
(\mu y)' = \mu b \quad \rightarrow \quad \mu y = \int \mu b + C \quad \implies \quad y = \frac{1}{\mu} \left( \int \mu  b + C \right)
$$

이제 앞에서 가정했던 $\mu' = - a \mu$를 해결하면 된다. 이 DE는 **Type II**이므로 마찬가지로 쉽게 풀 수 있다.

$$
\mu' = -a \mu \quad \iff \quad \frac{\mu'}{\mu} = - a(t) \quad \rightarrow \quad \ln \mu = - \int a(t) \iff \mu = e^{\displaystyle - \int a(t)}
$$

이제 $\mu$에 대한 위의 결과를 대입하면 Linear DE에 대한 solution을 구한 것이다.

$$
y'(t) = a(t) y(t) + b(t) \quad\iff\quad y(t) = e^{\int a(t)} \left( \int \left(e^{-\int a(t)}\right)  b(t) +C \right)
$$
