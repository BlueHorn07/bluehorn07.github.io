---
title: "Box-Muller Transform"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
excerpt: "컴퓨터에서 정규 분포를 따르는 랜덤 난수를 만드는 방법에 대해 🖥️"
---

2025년 마지막 학기 수업인 "확률개론(MATH431)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Introduction to Probability Theory](/categories/introduction-probability-theory)에서 확인하실 수 있습니다 🎲
{: .notice--info}

# 들어가며

이번 포스트에서 소개하는 것은 컴퓨터 랜덤 난수와 관련 있습니다! (벌써 재밌음 ㅋㅋ)

(이것도 논쟁이 있지만) 완전히 랜덤 하다는게 무엇일까요? 그리고 컴퓨터는 그 랜덤 난수를 어떻게 만드는 걸까요?

그리고 우리 생활에서 정말정말 유요한 분포인 "정규 분포"를 따르는 랜덤 난수를 컴퓨터에서 만드려면 어떻게 해야 할까요?

이 기법에 대해 연구한 사람들이 Box와 Muller이고, 1958년 이 기법을 출시했습니다. Box-Muller의 이 기법은 초기 컴퓨터 계산학의 기반이 되었다고 합니다!

# Box-Muller Transform

바로 어떻게 하는지 살펴봅시다! 이 기법은 Uniform Distribution을 따르는 난수 두 개를 이용해, 표준 정규 분포 $N(0, 1)$을 따르는 난수를 생성하는 것을 목표로 합니다.

<div class="theorem" markdown="1">

For two independent Uniform Random variables $U_1, U_2 \sim \text{Unif}(0, 1)$

The below transformed random variable follows Standard Normal distributions.

$$
\begin{aligned}
Z_0 &= \sqrt{-2 \ln U_1} \cdot \cos (2 \pi \, U_2) \\
Z_1 &= \sqrt{-2 \ln U_1} \cdot \sin (2 \pi \, U_2)
\end{aligned}
$$

</div>

# Transform Uniform Distribution to Exponential Distribution

Box-Muller Transform을 증명하기 전에, Uniform 분포를 $X \sim \text{EXP}(\lambda)$인 분포로 바꾸는 과정을 먼저 해봅시다!

지수 분포의 PDF는 아래와 같습니다.

$$
f_X(x) = \lambda e^{-\lambda x} \quad x \ge 0
$$

적분한 CDF는

$$
F_X(x) = 1 - e^{-\lambda x}
$$

역변환 샘플링을 위해 CDF의 역함수를 통해 확률변수 $U$를 $X$로 표현해봅시다. <br/>
<small>(참고로 CDF를 쓰는 이유는 CDF는 단조증가 함수이기 때문에 (일반화된) 역함수가 항상 존재합니다.)</small>

$$
\begin{aligned}
U &= 1 - e^{-\lambda X} \\
e^{-\lambda X} &= 1 - U \\
- \lambda X &= \ln (1 - U) \\
X &= - \frac{1}{\lambda} \cdot \ln(1 - U)
\end{aligned}
$$

즉, Uniform 분포로 지수 분포를 유도하려면, 아래와 같이 변환하면 됩니다!

<div class="theorem" markdown="1">

$$
X = - \frac{1}{\lambda} \cdot \ln(1 - U)
$$

</div>

이때, $(1 - U) \sim \text{Unif}(0, 1)$도 성립하기 때문에, 위의 식을 단순화 하여, 아래와 같이 적을 수 있습니다.

<div class="theorem" markdown="1">

$$
X = - \frac{1}{\lambda} \cdot \ln U
$$

</div>

# Derivation

<div class="proof" markdown="1">

2변수 정규 분포는 아래의 PDF를 가집니다.

$$
f(z_0, z_1) = \frac{1}{2\pi} \cdot \exp(- (z_0^2 + z_1^2)/2)
$$

이 PDF에 극좌표 변환을 해봅시다.

$$
\begin{aligned}
z_0 &= r \cos \theta \\
z_1 &= r \sin \theta
\end{aligned}
$$

이때, $r \in [0, \inf)$이고, $\theta \in [0, 2\pi)$ 입니다.

좌표 변환에 의해 Jacobian $\vert \det J \, \vert = r$ 입니다.

따라서, 함수 $f(r, \theta)$는

$$
f(r, \theta)
= \frac{1}{2\pi} \exp(-r^2/2) \cdot r
$$

<hr/>

이것은 두 확률변수 $\theta$와 $r^2$의 결합분포로 Marginal 분포만 모으면 아래와 같습니다.

$$
\begin{aligned}
\Theta & \sim \text{Unif}(0, 2 \pi) \\
R^2 & \sim \text{EXP}(1/2)
\end{aligned}
$$

저는 $r^2$의 분포가 잘 이해가 안 되었는데요.

$$
f_R(r) = r \cdot \exp(-r^2/2)
$$

라는 확률 분포에서 $R^2 = X$로 변수 치환을 하면,

$$
r = \sqrt{x}, \quad 2 r \cdot dr = dx
$$

$$
f_R(r) = \exp(-x/2) \cdot \frac{1}{2} = f_X(x)
$$

따라서, $R^2 = X \sim \text{EXP}(1/2)$가 됩니다!

<hr/>

이전 문단에서 했던, 지수 분포를 Uniform 분포로 표현하는 방법에 의해

$$
r = \sqrt{x} = \sqrt{- 2 \ln U}
$$

<hr/>

마찬가지로 $\theta$에도 $U_2$에 대한 역변환을 하면,

$$
\Theta = 2 \pi \cdot U_2
$$

<hr/>

이제, 모든 것을 종합해 정규분포 확률변수에 적용해봅시다.

$$
\begin{alignat*}{3}
z_0 &= r \cos \theta &=& \sqrt{-2 \ln U_1} \cdot \cos (2 \pi \cdot U_2) \\
z_1 &= r \sin \theta &=& \sqrt{-2 \ln U_1} \cdot \sin (2 \pi \cdot U_2)
\end{alignat*}
$$

$\blacksquare$

</div>

# Experiments

유튜브에서 Box-Muller Transform을 시뮬레이션한 결과를 올린 좋은 영상이 있어서 첨부 합니다!

<iframe width="560" height="315" src="https://www.youtube.com/embed/4fVQrH65aWU?si=QJ3SDkx6GWd_J7Rn" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
