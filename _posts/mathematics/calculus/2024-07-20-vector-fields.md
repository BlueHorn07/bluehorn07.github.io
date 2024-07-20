---
title: "Vector Fields"
toc: true
toc_sticky: true
categories: ["Calculus"]
excerpt: ""
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다. [미적분학 포스트 전체 보기](/categories/calculus)
{: .notice--info}

# Gradient Fields

어떤 2변수 함수 $f(x, y)$가 있다고 하자. 이전 챕터에서 이 2변수 함수의 한 점 $(x_0, y_0)$에서 정의한 Gradient Vector를 기억하는가?

$$
\nabla f(x, y) = <f_x(x, y), f_y(x, y)>
$$

이것을 한 점이 아니라 함수 $f(x, y)$의 정의역 전체에서 정의한 것이 "Gradient Field"다. 이것은 스칼라 함수인 $z = f(x, y)$로부터 유도되는 벡터 필드(= 벡터 함수)다.

# Conservative Vector Field, and Potential Function

반대로 어떤 벡터 필드는 그 원본이 어떤 스칼라 함수인 것들이 있다. 이런 벡터 필드를 "Conservative Vector Field"라고 한다. 이들은 Gradient $\nabla$ 되기 전의 원시 스칼라 함수를 찾을 수도 있다.

<div class="definition" markdown="1">

A vector field $\mathbf{F}$ is called a "**conservative vector field**" if it is the gradient of some scala function.

</div>

이런 Conservative Vector Field의 원시 스칼라 함수는 "**Potential Function**"라고 부른다.


# Line Integrals

어떤 곡선을 따라 함수 $f(x, y)$에 대한 적분을 수행하는 것을 "선적분"이라고 한다.

$$
\int_C f(x, y) \, ds = \int_C f(x(t), y(t)) \cdot \sqrt{x'(t)^2 + y'(t)^2} \, dt
$$

선적분 부분은 공부하다가 너무 헷갈려서 별도 포스트로 정리했다. 선적분에 대한 자세한 내용은 아래 포스트 참고 ㅎㅎ

➡️ [Arc Length와 Line Integral](/2024/06/30/arc-length-and-line-integral/)


## Fundamental Theorem for Line Integrals

<div class="definition" markdown="1">

Let $C$ be a smooth curve given by the vector function $\mathbf{r}(t)$ for $a \le t \le b$.

Let $f(x, y)$ be a differentiable function of two or three variables whose gradient vector $\nabla f$ is continuous on $C$.

Then,

$$
\int_{C} \nabla f \cdot d\mathbf{r} = f(\mathbf{r}(b)) - f(\mathbf{r}(a))
$$

</div>
