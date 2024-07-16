---
title: "Multiple Integrals"
toc: true
toc_sticky: true
categories: ["Calculus"]
excerpt: "Fubini's Theorem, Jacobian"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다. [미적분학 포스트 전체 보기](/categories/calculus)
{: .notice--info}

# Fubini's Theorem

<div class="theorem" markdown="1">

If $f$ is continuous on the rectangle

$$
R = \{ (x, y) \| a \le x \le b, c \le g \le d\}
$$

then

$$
\underset{R}{\iint} f(x, y) dA = \int_{a}^{b} \int_{c}^{d} f(x, y) dy dx = \int_{c}^{d} \int_{a}^{b} f(x, y) dx dy
$$

</div>

즉, 함수가 직사각형의 정의역 안에서 연속성을 가지는 적당한 함수라면, 적분 순서를 $x$ 먼저 하든, $y$ 먼저 하든 상관 없이 같은 적분 값이 나온다는 걸 말한다.


# Double Integral Over General Region

위의 경우는 적분 영역 $R$이 '직사각형'의 나이스한 형태로 주어진 경우다. 이런 이중 적분은 정말 쉽다. 그러나 많은 경우, 적분 영역은 자유분방한 형태로 존재한다. 특히 $y = g(x)$의 관계를 만족하거나,$x = h(y)$의 관계를 만족하는 적분 영역을 마주하는 경우가 많을 것이다.

![](/images/mathematics/calculus/double-integral-general-region-2.png){: style="max-height: 300px" .align-center }

이 경우는 $x = h(y)$의 꼴로 적분 영역이 표현되는 경우에 해당한다. 그래서 $x$ 축 방향으로 먼저 적분을 수행한 후에, $y$ 축 방향으로 적분을 수행한다.

![](/images/mathematics/calculus/double-integral-general-region-1.png){: style="max-height: 300px" .align-center }

위의 예제는 반대로 $y = g(x)$ 꼴로 표현하여 해결할 수도 있다. 이 경우는 $y$ 축 방향으로 먼저 적분 후 $x$ 축 방향으로 적분을 수행한다.


# Double Integrals in Polar Form

어떤 함수들은 원점을 기준으로 하는 극좌표계로 편리하여, 적분도 극좌표계를 기준으로 수행하는게 더 쉬운 경우도 있다.

![](/images/mathematics/calculus/double-integral-in-polar-form.png){: style="max-height: 200px" .align-center }

위와 같은 경우, 극좌표계로 표현하면

- $R = \\{ (r, \theta) \| 0 \le r \le 1, \; 0 \le \theta \le 2 \pi \\}$
- $R = \\{ (r, \theta) \| 1 \le r \le 2, \; 0 \le \theta \le \pi \\}$

와 같이 $xy$-좌표계에서 직사각형을 표현하는 것처럼 쉽게 영역 $R$를 표현할 수 있다. 그래서 이런 형태의 영역을 "**Polar Rectangle**"이라고 부른다 ㅋㅋ

![](/images/mathematics/calculus/polar-rectangle.png){: style="max-height: 240px" .align-center }

<br/>

이때, $xy$ 좌표계에서의 적분과 달리 극좌표에서의 적분은 아래와 같이 변환이 필요하다.

$$
\underset{R}{\iint} f(x, y) \, dx dy = \underset{R}{\iint} f(r, \theta) \cdot r \cdot dr d\theta
$$

위의 식을 보면, 미분소가 $dx dy = r \cdot dr d\theta$이 되는데,

![](/images/mathematics/calculus/differential-in-polar-form.png){: style="max-height: 240px" .align-center }

위와 같이 Polar Rectangle의 미소변화량을 $dA$를 계산해보면, $r \cdot dr d\theta$의 꼴이 되기 때문이다.


# Triple Integrals

만약 적분 영역이 아주 나이스 하다면, 삼중 적분을 그냥 $x$, $y$, $z$에 데이터 적분을 세 번 하면 된다.

$$
\underset{D}{\iiint} f(x, y, z) dV = \int_{r}^{s} \int_{c}^{d} \int_{a}^{b} f(x, y, z) \, dx \, dy \, dz
$$

당연하게도 현실에서 위와 같이 단순한 경우는 거의 없다. (이젠 익숙하다;;)

<br/>

![](/images/mathematics/calculus/triple-integral-1.png){: style="max-height: 240px" .align-center }

3차원 물체의 위-아래 뚜껑이 어떤 함수로 정해지는 경우다. 이 경우, 아래와 같이 적분 구간에 해당 함수를 넣어주면 된다.

$$
\underset{D}{\iint} \left[ \int_{u_1(x, y)}^{u_2(x, y)} f(x, y, z) \, dz \right] \, dx \, dy
$$

![](/images/mathematics/calculus/triple-integral-2.png){: style="max-height: 240px" .align-center }

이중적분에서 했던 것처럼 $xy$ 평면의 영역 $D$가 $y = g(x)$ 꼴로 두 변수 간에 함수 관계가 있을 수도 있다. 이 경우는 삼중 적분이 아래와 같을 것이다.

$$
\int_{a}^{b} \int_{g_1(x)}^{g_2(x)} \int_{u_1(x, y)}^{u_2(x, y)} f(x, y, z) \, dz \, dy \, dx
$$

![](/images/mathematics/calculus/triple-integral-3.png){: style="max-height: 240px" .align-center }

반대로 $x = h(y)$와 같은 종속 관계도 있을 수도 있다. 삼중 적분 식의 표현은 생략.

주의할 점은 삼중적분의 영역을 정의하는 물체 $V$의 위-아래 뚜껑이 항상 $u(x, y)$와 같이 $z$ 축이 아닐 수도 있다는 점이다. 위-아래 뚜껑이 $y$축을 따라서 존재할 수도 있고, $x$축을 따라서 존재할 수도 있다. 적분 영역이 항상 위의 그림과 같은 형태로 나올 거라고 착각하지 말 것!

# Triple Integrals in Cylindrical Coordinates




# Triple Integrals in Spherical Coordinates

# Jacobian

