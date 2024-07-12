---
title: "Multi-dimensional Functions: Problem Solving"
toc: true
toc_sticky: true
categories: ["Calculus"]
excerpt: ""
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다. [미적분학 포스트 전체 보기](/categories/calculus)
{: .notice--info}

# Limit of 2-dimensional function

<div class="statement" markdown="1">

A function $f(x, y)$ approaches the limit $L$ as $(x, y)$ approaches $(x_0, y_0)$

$$
\lim_{(x, y) \rightarrow (x_0, y_0)} f(x, y) = L
$$

if for every $\epsilon > 0$, there exists a corresponding $\delta > 0$ s.t. for all $(x, y)$ in the domain of $f$,

$\|f(x, y) - L\| < \epsilon$ whenever $0 < \sqrt{(x-x_0)^2 + (y-y_0)^2} < \delta$.

</div>

이변수 함수에서의 극한을 입델 논법으로 정의한 문장이다. 극한값 $L$ 근방의 어떤 값을 잡아도, 그에 대응하는 $\sqrt{(x-x_0)^2 + (y-y_0)^2} < \delta$ 범위를 잡아서, 그 안의 모든 $(x, y)$에 대해 함숫값이 $\epsilon$ 범위 안에 들어가는 $\delta$를 결정할 수 있음으로 이변수 함수의 극한을 정의한다.

![](/images/mathematics/calculus/limit-of-the%20two-dimensional-function.png){: .align-center style="max-height: 300px" }

반대로 $\delta$의 근방(disk)를 잡았을 때, 그 안의 어떤 점의 집합이 $\epsilon$ 범위를 벗어나는 경우가 있다면, 함수의 극한값을 결정할 수 없거나 제시한 극한값이 올바르지 않다고 말할 수 있다.


## Examples of the limit non-exsistance

<div class="problem" markdown="1">

Does $f(x, y) = \frac{y}{x}$ has a limit of $\lim_{(x, y) \rightarrow (0, 0)} f(x, y)$?

</div>

<div class="proof" markdown="1">

일단 함수 $f(x, y)$의 도메인은 분모의 $x$ 때문에 $x \ne 0$인 $\mathbb{R}^2$ 영역 전체이다.

먼저 $x \ne 0$인 $(x, 0)$ 영역 전체를 살펴보자. 그러면, 분자의 값이 0이기 때문에 극한값 $L$은 $L = 0$이 될 수 있다.

그러나 $y = x$인 상황을 생각해보면, $f(x, x) = \frac{x}{x} = 1$이 된다. 즉, 앞에서 $L = 0$인 것과 일치하지 않는다.

이것은 어떤 $\delta$ 범위의 disk를 정의하고, 극한값을 $L = 0$ 또는 $L = 0$ 둘 중 하나로 잡아도 $\epsilon$ 범위를 벗어나게 된다는 것을 말한다.

사실 $y = ax$ 관계를 만족한다면, 극한값은 어떤 실수 $a$가 된다. 어떤 방향으로 $(0, 0)$에 접근해도 극한값이 달라지므로 극한값은 존재하지 않는다.

</div>

<br/>

<div class="problem" markdown="1">

아래의 함수가 원점 $(0, 0)$에서 연속인지 결정하라.

$$
f(x, y) = \begin{cases}
\frac{2xy}{x^2 + y^2} & (x, y) \ne (0, 0) \\
0 & (x, y) = (0, 0)
\end{cases}
$$

</div>

<div class="proof" markdown="1">

원점에 $x$ 축을 따라 접근한다면, $(x, 0)$에 대해서 함숫값은 $0$으로 근접한다.

반대로 $y$ 축을 따라 접근한다면, $(0, y)$에 대해서도 함숫값은 $0$으로 근접한다.

하지만, $y = x$ 직선을 따라 접근한다면, $(x, x)$에 대해서 함숫값은 $1$로 근접한다. 이것은 $y = ax$ 조건을 만족하는 어떤 직선을 따라 접근하더라도 $\frac{2a}{a^2 + 1}$의 값으로 근접한다.

즉, 함숫값이 어떤 하나로 결정되지 않으므로 $(0, 0)$에서 함숫값이 존재하지 않고, 원점에서 "불연속"이다.

</div>

## Two Path Test for Non-existence of a Limit

함수가 어떤 점에서 극한이 존재하지 않음을 보이는 쉬운 방법으로 아래 방법이 정립되어 있다. $\delta$ 범위의 disk에서 2가지 방향으로 접근했을 때 극한값이 다르다면, 함수는 해당 점에서 극한이 존재하지 않는다고 말할 수 있다.

<div class="problem" markdown="1">

If a function $f(x, y)$ has different limits along two different paths in the domain of $f$ as $(x, y)$ approaches $(x_0, y_0)$, then $\lim_{(x, y) \rightarrow (x_0, y_0)} f(x, y)$ does not exist.

</div>

이때, 주의할 점은 명제의 역은 성립하지 않는다. 만약 2가지 방향으로 접근 했을 때 두 극한값이 같다고 해서 함수가 그 극한값을 가진다고 말할 수 없다!! 이것은 앞에서 살펴본 $f(x, y) = \frac{2xy}{x^2 + y^2}$ 함수 경우에서도 볼 수 있는데, $x$ 축, $y$ 축 방향으로 접근했을 때의 극한값이 둘다 $0$이었지만, $y = ax$ 방향 접근할 때의 극한값이 $0$과 다른 값을 가진다.


## Limit on Polar Coordinate

만약 rectangular coordinates에서 원점에 대한 극한 $\lim_{(x, y) \rightarrow (0, 0)} f(x, y)$에 대한 극한을 구하기 어렵다면, polar coordinate를 사용해 극한을 구하는 방법을 고려해볼만 한다. 이 경우, 함수 $f(x, y)$를 $f(r, \theta)$의 형태로 표현하고, $r \rightarrow 0$으로 보내는 방식으로 원점에 대한 극한을 구한다.

<div class="theorem" markdown="1">

Given $\epsilon > 0$, there exists a $\delta > 0$ s.t. for all $r$ and $\theta$,

if $\| r \| < \delta$, then $\| f(r, \theta) - L\| < \epsilon$.

If such an $L$ exists, then

$$
\lim_{(x, y) \rightarrow (0, )} f(x, y) = \lim_{r \rightarrow 0} f(r\cos \theta, r\sin \theta) = L
$$

</div>

예제와 함께 극좌표를 사용한 극한 계산을 살펴보자.

<div class="problem" markdown="1">

$$
\begin{aligned}
&\lim_{(x, y) \rightarrow (0, 0)} \frac{x^3}{x^2 + y^2} \\
&= \lim_{r \rightarrow 0} \frac{r^3 \cos^3 \theta}{r^2} \\
&= \lim_{r \rightarrow 0} r \cos^3 \theta \\
&= 0
\end{aligned}
$$

이것을 확인하려면, $r \cos^3 \theta$의 $r \rightarrow 0$에 대한 극한값 $L$이 0으로 수렴하는지를 체크해야 한다.

Let's find $\delta > 0$ which satisfies the below statement

IF $\| r \| < \delta$, then $\| r \cos^3 \theta  - 0\| < \epsilon$ for any given $\epsilon > 0$, and $r$ and $\theta$.

We know the range of $\cos \theta$, the below inequality satisfies

$$
\| r \cos^3 \theta  - 0\| \le \| r \| < \delta
$$

If we take $\delta$ as $\delta = \epsilon$, then the statement of limit holds.

</div>

<br/>

극좌표 변환을 통해 극한값이 존재하지 않음도 확인할 수 있다.

<div class="problem" markdown="1">

$$
\frac{x^2}{x^2 + y^2} = \frac{r^2 \cos^2 \theta}{r^2} = \cos^2 \theta
$$

이 경우는 $\| r \|$ 값이 아무리 작더라도 $\cos^2 \theta$에 의해 값이 0부터 1사이 값을 갖게 된다. 즉, 수렴하지 않는다.

</div>

<br/>

그러나 때로는 극좌표 변환으로 구하는 극한이 잘못된 결론을 이끌게 할 수도 있다.

<div class="problem" markdown="1">

$$
\begin{aligned}
&\frac{2x^2y}{x^4 + y^2} \\
=& \frac{2 r^3 \cos^2 \theta \sin \theta}{r^4 \cos^4 \theta + r^2 \sin^2 \theta} \\
=& \frac{2 r \cos^2 \theta \sin \theta}{r^2 \cos^4 \theta + \sin^2 \theta}
\end{aligned}
$$

이때, 분자의 $r$ 때문에 $r \rightarrow 0$으로 가면 0으로 수렴한다고 생각할 수 있다. 하지만, **그건 틀렸다!!**

만약 극한이 $y = x^2$의 경로로 원점에 접근한다면, $r \sin \theta = r^2 \cos^2 \theta$가 되고, 이것을 대입하면,

$$
\begin{aligned}
& \frac{2 r \cos^2 \theta \sin \theta}{r^2 \cos^4 \theta + \sin^2 \theta} \\
=& \frac{2 r \cos^2 \theta \cdot r \cos^2 \theta}{r^2 \cos^4 \theta + r^2 \cos^4 \theta} \\
=& \frac{2r^2 \cos^4 \theta}{2 r^2 \cos^4 \theta} \\
=& 1
\end{aligned}
$$

즉, 원점에서의 극한값은 $1$이 된다.

</div>

# Differentiability of 2-dimension

## Partial Derivatives exist, but not continuous

$$
f(x, y) = \begin{cases}
0, & xy \ne 0 \\
1, & xy = 0
\end{cases}
$$

위와 같은 함수를 생각해보자. 이 함수는 $x$축, $y$축과 원점에서는 1의 값을 갖고 나며지 영역에서는 모두 0의 값을 갖는다.

원점 $O$에서의 함수의 극한을 생각해보자.

(1) 만약 $x$축 또는 $y$축을 따라서 원점에 접근한다면, 극한값은 $1$이 된다.<br/>
(2) 하지만 $y = x$ 또는 (1)의 경로를 제외한 다른 방식으로 접근한다면, 극한값은 $0$이 된다.

즉, 원점 $O$에서 함수의 극한은 존재하지 않는다.

반면에 원점에서의 Partial Derivative는 존재한다. 둘다 $x$축, $y$축 위에서 값의 변화가 없으므로 Partial Derivative의 값은 0, 변화없음이다.

$$
\frac{\partial f}{\partial x} = \frac{\partial f}{\partial y} = 0
$$


## Differentiability implies Continuity

$y = f(x)$ 꼴의 함수에서도 어떤 점에서의 미분가능성은 해당 점에서의 연속성을 보장했다. 하지만, 위의 예에선 원점에서 편도함수(Partial Derivative)가 존재했지만, 원점에서 연속성은 갖지 못했다. 미분가능성이 연속성을 보장하는 것은 $y = f(x)$ 함수꼴에서만 성립하는 명제인 것인가?

이에 대한 답변은 "**다차원(multi-dimension)에서도 미분가능성은 연속성을 보장된다.**"라고 말할 수 있다.

위의 예제는 편도함수의 존재 여부는 함수의 미분가능성과 전혀 별개의 속성이라 교훈을 가장 간단한 형태로 말해준다.



