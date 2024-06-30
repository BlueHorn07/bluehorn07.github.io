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



# Tangent Vector, Normal Vector, Binormal Vector



# Many shapes on 3-dim space

![](/images/mathematics/calculus/x2+y2=z.png)

![](/images/mathematics/calculus/x2+y2=z2.png)