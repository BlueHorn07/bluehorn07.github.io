---
title: "Transform of Random Variables"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
excerpt: "선형/비선형 변환을 통해 새로운 확률 변수를 만드는 방법에 대해. Jacobian으로 확률 밀도 함수 보정하기"
---

2025년 마지막 학기 수업인 "확률개론(MATH431)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Introduction to Probability Theory](/categories/introduction-probability-theory)에서 확인하실 수 있습니다 🎲
{: .notice--info}

# 들어가며

2xx 확통과 4xx의 확률개론을 들으면서 많은 이산 확률변수와 연속 확률변수를 살펴보았습니다.

그런데, 세상에는 이것보다 더 많고 다양한 확률변수들과 상황이 존재하고, 이들을 생성하거나 모델링할 때, 확률변수의 "**변환(transform)**"을 하게 됩니다.

이번 포스트에서는 그 과정을 엄밀히 살펴볼 예정입니다!

# Linear Transform

$X \sim \text{Unif}(0, 1)$를 따르는 확률 변수를 $Y = 2X$로 변환해봅시다.

이 확률변수의 PDF는 $[0, 1]$ 사이에서 $f_X(x) = 1$ 였습니다.

이것을 2배로 늘린 $Y = 2X$는 그 범위로 2배가 됩니다. $Y \in [0, 2]$.

이제 이것의 CDF를 구해보면,

$$
F_Y(y) = P(Y \le y) = P(2X \le y)
= P(X \le y/2) = F_X(y/2)
$$

$X$의 CDF는 $F_X(x) = x$ 였으므로, $Y$의 CDF는

$$
F_Y(y) = y / 2 \quad (0 \le y \le 2)
$$

이제 CDF를 미분해서 PDF를 구하면,

$$
f_Y(y) = 1/2 \quad (0 \le y \le 2)
$$


# Non-linear Transform

이번에는 $X \sim \text{Unif}(0, 1)$를 따르는 확률 변수를 $Y = X^2$로 변환해봅시다.

새로운 확률변수 $Y$의 범위는 그대로 $[0, 1]$입니다. 이것의 CDF를 바로 구해보면,

$$
\begin{aligned}
F_Y(y)
&= P(Y \le y) \\
&= P(X^2 \le y) \\
&= P(\vert X \vert \le \sqrt{y}) \\
&= P(X \le \sqrt{y}) + P(-X \le \sqrt{y})
\end{aligned}
$$

이때, $X \sim \text{Unif}(0, 1)$이므로, $X > 0$인 경우만 고려하면 됩니다! 따라서,

$$
F_Y(y) = P(X \le \sqrt{y}) = \sqrt{y}
$$

이제 CDF를 미분해서 PDF를 구하면,

$$
f_Y(y) = \frac{1}{2\sqrt{y}} \quad (0 < y \le 1)
$$

선형 변환은 Uniform 분포를 다시 Uniform 분포로 만들었습니다. 반면에, 비선형 변환은 Uniform 분포를 완전히 다른 형태의 분포로 바꾸었습니다!

## PDF + Jacobian

이것을 Jacobian을 사용해 체계적으로 수행할 수도 있습니다.

일단 변환에 의해 $Y = g(X)$로 표현할 수 있을 때, 함수 $g$가 단조 증가/감소 하는, 미분 가능한 함수라면, 변환한 확률변수 $Y$의 분포는 아래와 같습니다.

<div class="theorem" markdown="1">

$$
\begin{aligned}
f_Y(y)
&= f_X(g^{-1}(y)) \cdot \vert \left(g^{-1}(y)\right)' \vert \\
&= f_X(g^{-1}(y)) \cdot \left\vert \frac{dg}{dy} \right\vert \\
\end{aligned}
$$

</div>

이때, $(g^{-1}(y))'$는 사실 Jacobian 입니다.

$Y = X^2$의 PDF를 Jacobian 방법으로 구하면, $g(y) = \sqrt{y}$ 이므로,

$$
F_Y(y) = f_X(\sqrt{y}) \cdot \vert \frac{1}{2\sqrt{y}}\vert
= 1 \cdot \frac{1}{2\sqrt{y}} = \frac{1}{2\sqrt{y}}
$$

# Multi-variable Transform

이번에는 서로 독립인 두 확률변수를 조합해 새로운 확률 변수를 만드는 경우를 살펴봅니다. 미적분학에서 변수 변환하면 Jacobian을 구해줘야 했듯이 동일하게 수행하면 됩니다!

## Example 1

서로 독립인 두 확률변수 $X$, $Y$로 아래와 같이 새로운 두 확률변수를 정의합니다.

$$
Z = X + Y, \quad W = X - Y
$$

가장 먼저, $(X, Y)$를 $(Z, W)$로 표현 합니다.

$$
\begin{aligned}
X &= (Z + W) / 2 \\
Y &= (Z - W) / 2
\end{aligned}
$$

이것은 역변환 $x(z, w)$와 $y(z, w)$ 입니다.

이제 Jacobian 행렬을 구합니다.

$$
J = \begin{bmatrix}
\partial x / \partial z & \partial x / \partial w \\
\partial y / \partial z & \partial y / \partial w
\end{bmatrix}
=
\begin{bmatrix}
1/2 & 1/2 \\
1/2 & -1/2
\end{bmatrix}
$$

행렬식을 구하면,

$$
\vert \det J \vert = \vert -1/4 - 1/4 \vert = 1/2
$$

이제 최종적으로 Joint PDF를 구하면,

$$
f_{Z, W}(z, w)
= f_{X, Y}\left(\frac{z+w}{2}, \frac{z-w}{2}\right) \cdot \frac{1}{2}
$$

## Example 2

서로 독립인 두 확률변수 $X$, $Y$로 아래와 같이 새로운 두 확률변수를 정의합니다.

$$
Z = X/Y, \quad W = XY
$$

가장 먼저, 역변환 $(X, Y)$를 찾습니다.

$$
\begin{aligned}
X &= \sqrt{ZW} \\
Y &= \sqrt{W/Z}
\end{aligned}
$$

이것은 역변환 $x(w, z)$와 $y(z, w)$입니다.

Jacobian 행렬을 구합니다.

$$
J = \begin{bmatrix}
\partial x / \partial z & \partial x / \partial w \\
\partial y / \partial z & \partial y / \partial w
\end{bmatrix}
=
\begin{bmatrix}
\frac{1}{2} \frac{\sqrt{W}}{\sqrt{Z}} & \frac{1}{2} \frac{\sqrt{Z}}{\sqrt{W}} \\
- \frac{1}{2} \frac{\sqrt{W}}{Z \sqrt{Z}} & \frac{1}{2} \frac{1}{\sqrt{ZW}}
\end{bmatrix}
$$

행렬식을 구하면,

$$
\begin{aligned}
\vert \det J \vert
&=
\left\vert
  \frac{1}{2} \frac{\sqrt{W}}{\sqrt{Z}} \cdot \frac{1}{2} \frac{1}{\sqrt{ZW}}
- \frac{1}{2} \frac{\sqrt{Z}}{\sqrt{W}} \cdot \left(- \frac{1}{2} \frac{\sqrt{W}}{Z \sqrt{Z}} \right)
\right\vert \\
&=
\left\vert
\frac{1}{4} \frac{1}{Z} + \frac{1}{4} \frac{1}{Z}
\right\vert \\
&=
\frac{1}{2Z}
\end{aligned}
$$

이제 최종적으로 Joint PDF를 구하면,

$$
f_{Z, W}(z, w)
= f_{X, Y}\left(\sqrt{zw}, \sqrt{w/z}\right) \cdot \frac{1}{2z}
$$

## Generalize

Multi-variable 확률 변수에서의 변환 과정을 요약하면 아래와 같습니다!

<div class="theorem" markdown="1">

$$
\begin{gather*}
f_{Z, W} (z, w) = f_{X, Y} \left(x(z, w), y(z, w)\right) \cdot \vert \det J \, \vert \\
\\
\text{where} \\
\\
J = \begin{bmatrix}
\partial x / \partial z & \partial x / \partial w \\
\partial y / \partial z & \partial y / \partial w
\end{bmatrix}
\end{gather*}
$$

</div>

하는 방법만 잘 알고 있으면, 별로 어렵지 않습니다 ^^;;

# 맺음말

이어지는 포스트에선 컴퓨터에서 정규 분포를 따르는 랜덤 난수를 만드는 방법을 체계적으로 제안한 "Box-Muller Transform"에 대해서 살펴봅니다!

➡️ [Box-Muller Transform](/2025/05/01/Box-Muller-transform/)
