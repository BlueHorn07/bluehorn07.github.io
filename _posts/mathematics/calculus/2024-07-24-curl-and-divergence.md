---
title: "Divergence and Curl"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Calculus"]
excerpt: ""
---


복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다. [미적분학 포스트 전체 보기](/categories/calculus)
{: .notice--info}

![](/images/meme/nothing-know.png){: .align-center style="max-height: 400px;" }
어쩌면 지금 이 상태일지도...
{: .align-caption .text-center .small .gray }

드디어!! 미적2의 꽃이라고 할 수 있는 발산(Divergence)과 회전(Curl)이다!! 두 개념 모두 벡터장을 유체의 흐름이라고 보고, 그 유체가 한 점에 대해 팽창/압축 하고 있는지, 아니면 한 점에서 유체가 얼마나 빠르게 회전하고 있는지를 측정하는 지표이다. 🌊


# Divergence

<div class="definition" markdown="1">

If $\mathbf{F} = P \, \mathbf{i} + Q \, \mathbf{j} + R \, \mathbf{k}$ is a vector field on $\mathbb{R}^3$,

and the below partial derivatives exist

$$
\frac{\partial P}{\partial x} \text{ , } \frac{\partial Q}{\partial y} \text{ , } \frac{\partial R}{\partial z}
$$

then the "divergence of $\mathbf{F}$" is a scala value defined by

$$
\text{div } \mathbf{F}
= \frac{\partial P}{\partial x} + \frac{\partial Q}{\partial y} + \frac{\partial R}{\partial z}
$$

</div>

발산(divergence)는 벡터장에 대해 정의하는 스칼라 함수이다. Gradient Vector $\nabla$와의 내적으로 식을 더 쉽게 표현할 수 있다.

$$
\text{div } \mathbf{F} = \nabla \cdot \mathbf{F}
$$

## Geometric meaning

벡터장을 유체의 흐름 속이라고 표현한다면, 발산(div)는 한 점 $(x, y, z)$에 모이는(압축) 방향으로 유체가 흐르는지, 아니면 점에서 나가는(팽창)하는 방향으로 유체가 흐르는지를 수치화 하는 값이다.

- $\text{div }\mathbf{F} > 0$
  - 한 점에서 유체가 팽창한다: *outflowing-ness*.
- $\text{div }\mathbf{F} < 0$
  - 한 점에서 유체가 압축된다: *inflowing-ness*.
- $\text{div }\mathbf{F} = 0$
  - 한 점이 유체에 전혀 압력을 받고 있지 않다: *incompressible*.

<br/>

발산에 대한 식을 3차원 공간에 대한 것으로 적었지만, 2차원 평면에서 정의한 벡터장에 대해서도 발산(divergence) 값을 계산할 수 있다.


![](/images/mathematics/calculus/divergence-negative.png){: .align-center style="max-height: 400px;" }
[Gilbert Strang - Calculus Vol 3.](https://open.umn.edu/opentextbooks/textbooks/calculus-volume-3): Negative Divergence
{: .align-caption .text-center .small .gray }

위의 벡터장은 $\mathbf{F}(x, y) = \left<-x, -y\right>$로 div를 계산해보면, $-2$가 된다. 따라서 모든 점이 압축하는 방향으로 압력을 받는다.

<br/>

![](/images/mathematics/calculus/divergence-zero-1.png){: .align-center style="max-height: 400px;" }
[Gilbert Strang - Calculus Vol 3.](https://open.umn.edu/opentextbooks/textbooks/calculus-volume-3): Zero Divergence
{: .align-caption .text-center .small .gray }

반면에 압축을 전혀 받지 않는 벡터장도 있다. $\mathbf{F}(x, y) = \left<-x, y\right>$인 벡터장은 div를 계산해보면 $0$이 된다. 따라서 모든 점에서 압촉을 받지 않는다.

<br/>

![](/images/mathematics/calculus/divergence-zero-2.png){: .align-center style="max-height: 400px;" }
[Gilbert Strang - Calculus Vol 3.](https://open.umn.edu/opentextbooks/textbooks/calculus-volume-3): Zero Divergence
{: .align-caption .text-center .small .gray }

또, 위와 같이 유체의 흐름이 **상수**인 경우도 유체가 받는 압력이 $0$으로 계산된다.


## Source-free Field

벡터장이....


# Curl

<div class="definition" markdown="1">

If $\mathbf{F} = P \, \mathbf{i} + Q \, \mathbf{j} + R \, \mathbf{k}$ is a vector field on $\mathbb{R}^3$, and the partial derivatives of $P$, $Q$ and $R$ all exists,

then the "curl of $\mathbf{F}$" is the vector field on $\mathbb{R}^3$ defined by

$$
\text{curl } \mathbf{F}
= \left( \frac{\partial R}{\partial y} - \frac{\partial Q}{\partial z} \right) \mathbf{i}
+ \left( \frac{\partial P}{\partial z} - \frac{\partial R}{\partial x} \right) \mathbf{j}
+ \left( \frac{\partial Q}{\partial x} - \frac{\partial P}{\partial y} \right) \mathbf{k}
$$

</div>

식이 조금 복잡한데, 위의 식을 외우기 보다는 외적으로 표기한 아래의 식을 습득하는게 더 유용하다.

$$
\text{curl } \mathbf{F} = \nabla \times \mathbf{F}
$$

즉, Gradient 벡터와 벡터장을 외적한 것이 회전(curl)이다.

$$
\nabla \times \mathbf{F} =
\left|
\begin{matrix}
\mathbf{i} & \mathbf{j} & \mathbf{k} \\
\frac{\partial}{\partial x} & \frac{\partial}{\partial y} & \frac{\partial}{\partial z} \\
P & Q & R
\end{matrix}
\right|
$$

## Geometric meaning



# Curl and Div

