---
title: "Arc Length와 Line Integral"
toc: true
toc_sticky: true
categories: ["Calculus"]
excerpt: ""
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다. [미적분학 포스트 전체 보기](https://bluehorn07.github.io/categories/calculus)
{: .notice--info}

드디어 미적분학 교재의 마지막 챕터인 "Integrals and Vetor Field"를 공부하고 있다. 미적2를 어렵다고 느끼게 했던 원흉인데, 이번에 다시 공부할 때도 날 괴롭히고 있다... 처음에는 "선적분(line integral)"라는 개념에 대해 다루는데, 여기에서부터 헷갈리기 시작해버린... 결국 마음을 다 잡고 개념들을 정리해보기로 마음 먹었다. 곰곰히 생각해보니 선적분이 Arc Length 계산하는 거랑 비슷한 것 같아서 둘을 같이 한번 살펴보겠다.

# Arc Length

## Parametized Curve

xy 평면 위의 곡선 $C$가 $x = f(t)$, $y = g(t)$로 매개화 되어 있다고 하자. 그러면 곡선의 길이는 아래와 같이 계산할 수 있다.

$$
L = \int_a^{b} \sqrt{(f'(t))^2 + (g'(t))^2} \, dt
$$

또는 표기에 따라 곡선이 $(x(t), y(t))$로 매개화 되었다고 하고 아래와 같이 적기도 한다.

$$
L = \int_a^{b} \sqrt{(x'(t))^2 + (y'(t))^2} \, dt
$$

## Explicit Function

xy 평면 상에 함수 $y = f(x)$가 그리는 곡선 $C$가 있다고 하자. 이때, 곡선의 길이는 아래와 같이 계산할 수 있다.

$$
L = \int_a^{b} \sqrt{1 + (f'(x))^2} \, dx
$$

# Line Integral

xy 평면 위에 $(x(t), y(t))$로 그려지는 곡선 $C$가 있다고 하자. 그리고 어떤 이변수 함수 $f(x, y)$를 생각해보자. 요 이변수 함수를 곡선 $C$ 위에서의 선적분을 하면 아래와 같다.

$$
\int_C f(x, y) \, ds = \int_{a}^{b} f(x(t), y(t)) \cdot \sqrt{(x'(t))^2 + (y'(t))^2} \, dt
$$

위에서 봤던 Arc Length와 선적분을 연관해서 본다면, Arc Length는 "**$f(x, y) = 1$인 선적분**"이라고 볼 수 있다. 즉, 모든 xy 평면 상에서 같은 값을 갖는 함수에 대한 선적분이다.

만약 함수 $f(x, y)$가 xy 평면 상에서의 밀도 함수 $\rho(x, y)$ 였다면, 곡선 $C$에 대한 선적분은 곡선 $C$의 "**무게**"가 될 것이다.

![](/images/mathematics/calculus/line-integral-of-scala-field.png){: .align-center style="height: 400px" }

만약 함수 $f(x, y)$를 z축 상의 어떤 점이라고 본다면, Line Integral은 "**곡선이 그리는 벽의 면적**"을 계산하게 된다.

내가 헷갈렸던 점은 이변수 함수 $f(x, y)$와 곡선 $C$가 뭔가 연관이 있을 거라고 생각한 것 같다. 사실 둘은 완전히 독립적인 존재로 곡선 $C$는 그저 적분 구간을 정의하는 것에 불과하다. 그마저도 계산 과정에서는 매개변수 $t$로 표현되어 버리지만...!

<br/>

표기에 따라서 곡선 $C$를 위치벡터 $\vec{r}(t)$로 표현한다면, Line Integral은 아래와 같이 표현된다.

$$
\int_C f(x, y) \, ds = \int_a^{b} f(\vec{r}(t)) \cdot \| \vec{v}(t) \| \, dt
$$

갑자기 등장한 벡터 $\vec{v}(t)$는 속도 벡터로 위치 벡터 $\vec{r}(t)$를 미분한 벡터이다. 위의 Line Integral을 보면, 미소 변위 $ds$가 $\| \vec{v}(t) \| \cdot dt$로 표현되는게 당연한 듯이 보인다 ㅎㅎ

## Line Integral of a scala field

위에서 이변수 함수 $f(x, y)$로 선적분 한 것을 "Scala Field 위에서의 선적분"라고 부른다. 이변수 함수 $f(x, y)$가 스칼라 값을 출력하기 때문이다.

## Line Integral of a vector field

이변수 함수 $f(x, y)$가 $\mathbb{R}^2 \rightarrow \mathbb{R}^2$하는 벡터 함수이라고 할 때, 이런 벡터 함수에 대한 선적분도 정의할 수 있다.

TDB...

