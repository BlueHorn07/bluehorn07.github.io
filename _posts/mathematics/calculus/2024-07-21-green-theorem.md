---
title: "Green Theorem"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Calculus"]
excerpt: ""
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다. [미적분학 포스트 전체 보기](/categories/calculus)
{: .notice--info}

미적2에서 가장 중요한 챕터가 어디냐고 물으면 바로 "그린 정리"와 "스토스크 정리"다. 사실상 지금까지 배운 미적1과 미적2의 내용은 모두 이 정리를 다루기 위한 빌드업에 불과했단 것... ㅋㅋ
그만큼 이 부분이 중요하고, 이걸 이해하기 위해 그 많은 내용들을 공부한 거라는 사실. 여기까지 이해하기를 포기하지 않고 온게 뿌듯하기도 하다... 🥺 암튼 중요한 내용이기 정신 바짝 차리고 본론으로 ㄱㄱ!

# Green Theorem

그린 정리를 한마디로 요약하면 아래와 같다.

> 어떤 특별한 벡터 함수에 벡터 선적분과 이중적분을 연결하는 정리.

좀더 정확히 정의하면, 아래 두 적분을 연결하는 졍리다.

- simple closed curve $C$ 위에서 정의한 벡터 선적분
- 동일한 curve $C$로 정의한 영역 $D$ 위에서 정의한 이중적분

정리의 명제 형태로도 살펴보자.

<div class="theorem" markdown="1">

Let $C$ be a positively oriented, piecewise-smooth, simple closed curve in the plane.

Let $D$ be the region bounded by $C$.

If $P$ and $Q$ have continuous partial derivatives on an open region that contains $D$, then

$$
\int_C \mathbf{F} \cdot d\mathbf{r}
= \int_C P \, dx + Q \, dy
= \iint_D \left( \frac{\partial Q}{\partial x} - \frac{\partial P}{\partial y}\right) \, dA
$$

</div>

## Curve Orientation

그린 정리를 기술한 위의 명제에서 *"positively oriented"*라는 표현이 등장한다. 수학에서는 Curve의 방향을 positive, negative로 정의하는데 CCW 방향을 positive orientation으로 설정한다.

![](/images/mathematics/calculus/curve-orientation.png){: .align-center style="max-height: 400px;" }
[CLP Calculus Textbook](https://personal.math.ubc.ca/~CLP/CLP4/)
{: .align-caption .text-center .small .gray }

positive orientation을 CCW로 설정하고, TNB 좌표를 잡으면, 법선 벡터 $\mathbf{N}$이 항상 커브 안쪽으로 향하게 된다.

![](/images/mathematics/calculus/normal-vector-direction.png){: .align-center style="max-height: 300px" }
[CLP Calculus Textbook](https://personal.math.ubc.ca/~CLP/CLP4/)
{: .align-caption .text-center .small .gray }

요렇게 말이다!!

## Curve Boundary

또, 수학적 표기로 영역 $D$를 감싸하는 positive oriented curve를 편미분 기호를 사용해 아래와 같이 표현한다.

$$
\partial D
$$

위의 표기를 사용해 그린 정리를 다시 정의하면 아래와 같다.

<div class="theorem" markdown="1">

$$
\iint_D \left( \frac{\partial Q}{\partial x} - \frac{\partial P}{\partial y}\right) \, dA
= \int_{\partial D} P \, dx + Q \, dy
$$

</div>

# Application of Green Theorem

그린 정리는 경계 위에서의 벡터 선적분과 경계로 만들어지는 영역의 이중적분을 연결하는 정리였다. 이를 활용해

- 벡터 선적분을 계산하기 위해 이중 적분을 수행한다.
- 반대로 이중 적분을 계산하기 위해 벡터 선적분을 수행한다.

## 이중 적분을 벡터 선적분으로 계산

<div class="problem" markdown="1">

Find the area enclosed by the ellipse

$$
\frac{x^2}{a^2} + \frac{y^2}{b^2} = 1
$$

</div>

타원의 넓이를 구하는 문제다. 이를 이중 적분으로 정의하면 아래와 같다.

$$
\iint_D 1 \, dA
$$

이를 그린 정리의 관점에서 보면, $P$, $Q$가 아래 조건을 만족하는 이중 적분을 수행하는 것과 같다.

$$
\frac{\partial Q}{\partial x} - \frac{\partial P}{\partial y} = 1
$$

위의 경우를 만족하는 $P$, $Q$는 여러 가지 경우가 하다.

1. $P(x, y) = 0, \, Q(x, y) = x$
2. $P(x, y) = -y, \, Q(x, y) = 0$
3. $P(x, y) = - y / 2, \, Q(x, y) = x / 2$

따라서, 아래의 식이 성립한다.

$$
A = \oint_C x \, dy = \oint_C (- y) \, dx = \frac{1}{2} \oint_C x \, dy - y \, dx
$$

위의 3가지 방식은 어떤 걸 선택해도 상관 없다 ㅎㅎ (직접 해봄) 제일 쉬울 것 같은, (1)번 방식으로 풀이해보자.

매개화에 의해 $x = a \cos t$, $y = b \sin t$이다. 적분을 치환하면,

$$
\begin{aligned}
A &= \oint_C x \, dy \\
&= \int_{0}^{2\pi} (a \cos t) (b \cos t) \, dt \\
&= ab \int_{0}^{2\pi} \cos^2 t \, dt \\
&= ab \int_{0}^{2\pi} \frac{\cos 2t + 1}{2} \, dt \\
&= ab \frac{2\pi}{2} = ab \pi
\end{aligned}
$$

# Not simply-connected 영역에서의 적분

그린 정리의 명제를 잘 살펴보면, 곡선 $C$에 대한 조건이 아래와 같이 적혀있다.

> positively oriented, piecewise-smooth, simple closed curve

즉, 곡선이 simply-connected인 필요는 없다!!

![](/images/mathematics/calculus/not-simply-connected-region.png){: .align-center style="max-height: 300px" }
[CLP Calculus Textbook](https://personal.math.ubc.ca/~CLP/CLP4/)
{: .align-caption .text-center .small .gray }

곡선이 요렇게 "**도넛 모양**"을 이루더라도 그린 정리가 성립한다!!

