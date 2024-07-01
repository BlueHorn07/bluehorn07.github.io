---
title: "Vector and Space: Problem Solving"
toc: true
toc_sticky: true
categories: ["Calculus"]
excerpt: ""
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다. [미적분학 포스트 전체 보기](/categories/calculus)
{: .notice--info}

# 직선 정의하기

## 2차원에서

평면 상의 두 점 $(x_1, y_1)$, $(x_2, y_2)$에 대해 두 점을 지나는 직선은 아래와 같이 정의된다.

$$
y = \frac{y_2 - y_1}{x_2 - x_1} (x - x_1) + y_1
$$

## 3차원에서

공간 상의 두 점 $(x_1, y_1, z_1)$, $(x_2, y_2, z_2)$에 대해 두 점을 지나는 직선은 아래와 같이 정의된다.

$$
\frac{x-x_1}{x_2 - x_1} = \frac{y-y_1}{y_2 - y_1} = \frac{z-z_1}{z_2 - z_1} 
$$

다시 하려고 보니 기억이 안 났었다 ㅋㅋㅋ ㅠㅠ ~~고등학교 때 배웠던 건데...~~

# 평면 정의하기

평면을 정의하는 방법은 여러 가지가 있지만, 여기서는 법선 벡터(normal vector) $\vec{n} = (a, b, c)$와 한 점 $(x_0, y_0, z_0)$를 지나는 평면으로 구해보겠다.

$$
a (x - x_0) + b(y-y_0) + c(z-z_0) = 0
$$

내적을 사용해서도 표현할 수 있는데, 한 점 $(x_0, y_0, z_0)$를 시점으로 갖는 벡터 $\vec{p} = (x - x_0, y - y, z-z_0)$를 생각해보자. 그러면 평면은 아래와 같이 내적으로 정의된다.

$$
\vec{n} \cdot \vec{p} = 0
$$

# Vector Function and Space Curve

함숫값이 벡터인 함수를 말함.

$$
\vec{r} (t) = \left( f(t), g(t), h(t) \right)
$$

연속인 벡터 함수는 공간 상에서 곡선을 그릴 수 있음.

![](/images/mathematics/calculus/vector-function-and-space-curve.png)

## Tangent Vector

![](/images/mathematics/calculus/tangent-vector.png)

벡터 함수 $\vec{r}(t)$를 term-by-term으로 미분한 함수를 말한다.

$$
\vec{r}'(t) = \left( f'(t), g'(t), h'(t) \right)
$$

공간 곡선의 한 점에 접하는 직선의 방향이라고 볼 수 있다. Tangent Vector의 방향만 알고 싶을 때는 Unit Tangent Vector를 사용한다.

$$
\vec{T}(t) = \frac{\vec{r}'(t)}{\left| \vec{r}'(t) \right|}
$$


TODO: tangent vector 값이 클수록 어떻다는 거 말할 필요 있음.

r(t)와 T(t)가 수직하는 경우는 어떤 경우인지도 업근 필요.


## Normal Vector

Unit Tangent Vector $\vec{T}(t)$에 대해 수직하는 법선 벡터(Normal Vector)를 찾고자 한다. 그런데 유일하게 결정되는 Unit Tangent Vector와 달리 $\vec{T}(t)$에 수직하는 법선 벡터는 무수히 많다...!

이때, 접선 벡터 $\vec{T}(t)$에 대해선 아래의 등식이 만족한다.

$$
\vec{T}(t) \cdot \vec{T}'(t) = 0
$$

즉, 접선 벡터 $\vec{T}(t)$를 또 하나의 공간 곡선으로 보고, 그것의 접선 벡터 $\vec{T}'(t)$를 구해보면, 그 둘이 수직 관계에 있다는 것이다...!

이 사실을 바탕으로 아래와 같은 법선 벡터를 정의해보자.

$$
\vec{N}(t) = \frac{\vec{T}'(t)}{\left| \vec{T}'(t) \right|}
$$

접선 벡터의 접선 벡터로 정의한 요 법선 벡터를 "Principal Unit Normal Vecor" $\vec{N}(t)$라고 부른다. 편하게 "unit normal"라고도 부른다.


## Binormal Vector

서로 수직하는 두 벡터를 외적하면, 그 두 벡터에 수직하는 또 다른 벡터를 얻을 수 있다는 사실을 기억하는가!! 앞에서 서로 수직하는 두 벡터 $\vec{T}(t)$, $\vec{N}(t)$를 구했으니, 외적을 이용해 또 다른 수직 벡터를 구할 수 있다!!

$$
\vec{B}(t) = \vec{T}(t) \prod \vec{N}(t)
$$

접선 벡터와 법선 벡터의 외적으로 유도되는 이 벡터를 "**종법선 벡터(Binormal Vector)**"라고 부른다. 아마 두 벡터에 둘(bi-) 다 수직(normal)인 관계를 만족해서 "Binormal"라는 이름이 붙은게 아닐까...?



# Many shapes on 3-dim space

![](/images/mathematics/calculus/x2+y2=z.png)

![](/images/mathematics/calculus/x2+y2=z2.png)