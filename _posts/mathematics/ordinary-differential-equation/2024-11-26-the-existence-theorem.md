---
title: "The Existence Theorem"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "Picard Iteration으로 해의 존재성 증명하기"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# 들어가며

![](/images/meme/panic.png){: .align-center style="max-width: 300px" }

경고하는데 여기서부터 진짜 완전히 새로운 내용입니다...;; 지금까지는 미분방정식의 심화 버전을 하는 느낌이었다면, 여기서부터 진짜 `MATH4xx` 과목의 위엄이 뭔지 작살나게 느낄 수 있습니다 ㅋㅋ

이 챕터의 목표는 ODE의 solution이 존재(Existence)하고 그리고 유일(Uniqueness)하다는 것을 보이는 것입니다. 그런데 저는 감자(🥔)니까 그 주변 곁다리부터 다가가보도록 하겠습니다.

<div class="proof" markdown="1">

**[Existence and Uniqueness의 곁다리들]**

순서는 상관없습니다.

- [Lipschitz Constant](/2024/11/14/Lipschitz-constant/)
- [Picard Iteration](/2024/11/14/Picard-iteration/)
- [Gronwall's Inequality](/2024/11/14/Gronwall-inequality/)
- [Some Preliminaries](/2024/11/16/some-preliminary-the-existence-and-uniqueness-theorem/)

</div>



# The Existence and Uniqueness Theorem

<div class="theorem" markdown="1">

Consider the initial value problem

$$
X' = F(X), \quad X(0) = X_0
$$

where $X_0 \in \mathbb{R}^n$. Supp. that $F: \mathbb{R}^n \rightarrow \mathbb{R}^n$ is $C^1$.

Then there exists a "unique" solution of this initial value problem. More precisely, there exists $a > 0$ and a unique solution

$$
X: (-a, a) \rightarrow \mathbb{R}^n
$$

of this differential equation satisfying the initial condition $X(0) = X_0$.

</div>

이때, $C^1$은 "Continuously Differentiable Function"입니다. 그리고 $F(X)$는 벡터 필드로 

$$
F(X) = (f_1, (x_1, ..., x_n), ..., f_n(x_1, ..., x_n))
$$

을 만족합니다.

# Road to the theorem

우리의 목표는 위의 정리를 이해하고, 증명해보는 것입니다. 내용이 어려울 수도 있겠지만, 포기하지 않고 전지해봅시다! 🏃‍♂️‍➡️ 내용을 다 이해하지 못 해도 괜찮다!! (나에게 하는 말 ㅋㅋ)

## Continuous Differential Functions are Locally Lipschitz

<div class="theorem" markdown="1">

Supp. that the function $F: \Omega \rightarrow \mathbb{R}^n$ is $C^1$.

Then $F$ is locally Lipschitz.

\* 이때, 함수의 정의역 $\Omega$는 콤팩트 집합이다.

</div>

<div class="proof" markdown="1">

Let $x_0 \in \Omega$ and let $B_{\epsilon} := \left\\{ x: \| x - x_0 \| \le \epsilon \right\\}$ with small $\epsilon > 0$ s.t. $B_{\epsilon} \subset \Omega$.

$B_{\epsilon}$이 convex 하므로, $B_{\epsilon}$ 안에 존재하는 두 점 $Y$, $Z$를 연결하는 straight line 역시 $B_{\epsilon}$ 안에 존재하게 됩니다. 그리고 그 line는 아래와 같이 정의됩니다.

$$
Y + s U \in B_{\epsilon}
$$

(이때, $U$는 방향 벡터로 $U = Z - Y$, 그리고 $s$는 $0 \le s \le 1$라는 매개변수.)

이 straight line 위에서의 함숫값을 $\psi(s) = F(Y + sU)$라고 정의해봅시다. (미적2에서 요런 line integral이 떠오르네요) 이걸 미분하면 Chain Rule에 의해,

$$
\psi'(s) = DF_{Y_{sU}}(U)
$$

가 됩니다. 이제 처음에 잡았던 두 점 $Y, Z \in B_{\epsilon}$에 대한 두 함수값의 차이인 $F(Z) - F(Y)$를 확인해봅시다.

$$
\begin{aligned}
F(Z) - F(Y) &= \psi(1) - \psi(0) \\
&= \int_0^1 \psi'(s) \, ds \\
&= \int_0^1 DF_{Y+sU}(U) \, ds
\end{aligned}
$$

이때, 집합 $B_{\epsilon}$가 compact 하므로, 그 정의역 안에서 함수값 $F(X)$은 Minimum과 Maximum이 존재합니다. 그리고 $B_{\epsilon}$에 대한 Jacobian의 노름 $K$를 정의합니다: $K = \sup_{x \in B_{\epsilon}} \| DF_x \| < + \infty$.

그러면, 위의 함수값 차이에 대한 식은 아래와 같은 부등식을 만족합니다.

$$
\|F(Z) - F(Y)\| = \| \int_0^1 DF_{Y+sU}(U) \, ds \| \le \int_0^1 K \| U \| \, ds = K \| Z - Y \|
$$

위의 부등식을 잘 정리하면, 아래와 같이 Lipschitz 부등식에 대한 결과를 얻게 됩니다.

$$
\frac{\| F(Z) - F(Y) \|}{\| Z - Y \|} \le K
$$

$\blacksquare$

</div>

## Integral form of the differential equation

우리가 지금까지 살펴본 미분방정식은 $X' = F(X)$의 꼴이었습니다. 그런데, 이것을 매개변수화 하고, 적분 꼴로 바꾸면 미분방정식과 동치인 적분방정식을 얻을 수 있습니다.

입력 변수 $X$를 $t$로 매개변수화 하면, $X'(t) = F(X(t))$를 만족하게 됩니다. $X(0) = X_0$. 그리고 이것을 적분꼴로 바꾸면

$$
X(t) = X_0 + \int_0^t F(X(s)) \, ds
$$

해의 존재성 증명을 위해 우리는 요 적분꼴을 사용할 예정입니다.

## Assumptions to prove

존재성 증명을 위해 아래와 같이 세팅합니다.

- 초기값 $X_0$를 중심으로하고, 반지름 $\rho > 0$인 closed ball $O_\rho$를 정의함.
- 벡터 필드 $F(X)$가 $O_\rho$ 안에 대해 Lipschitz Constant $K$를 가짐.
- 벡터 필드 $F(X)$의 상한이 $O_\rho$ 안에 존재하는데, 이를 $M$이라고 함.
- 구간 $J = [-a, a]$를 정의하는데, $a$는 $a < \min \\{ \rho/M, 1/K \\}$여야 함. 

## Function Sequence

$J = [-a, a]$ 범위 안에서 함수 $U_0, U_1, ...$를 정의합니다. 이 함수들은 [Picard Iteration](/2024/11/14/Picard-iteration/)에 의해 정의되는 함수열입니다.

초기엔 $U_0(t) = X_0$입니다. Iteration을 한번 돌면,

$$
U_1(t) = X_0 + \int_0^t F(U_0(s)) \, ds = X_0 + t F(X_0)
$$

로 정의됩니다. 이때, $U_t(t)$가 다시 $O_{\rho}$에 속하는지 확인해봅시다. 모든 $U_k(t) \in O_\rho$를 만족해야 같은 조건 위에서 Iteration을 계속할 수 있기 때문입니다.

$$
\| U_1(t) - X_0 \| = \| t \| \cdot \| F(X_0) \| \le a \cdot M < \rho
$$

이것은 $U_1(t)$각 $X_0$를 중심으로 하는 닫힌 원 $O_{\rho}$에 속한다는 것을 말합니다: $U_1(t) \in O_{\rho}$.

이 과정을 Picard Iteration 방식에 따라 반복하면, 귀납법에 의해 $U_k(t)$는 아래와 같이 정의되고

$$
U_{k+1}(t) = X_0 + \int_0^t F(U_{k}(s)) \, ds
$$

각 $U_k(t)$는 $O_{\rho}$에 포함되게 됩니다. (이것을 보이는 것은 위의 등식을 정리하면 됩니다.)

따라서, 함수열 $\{ U_{k+1} (t) \}$는 $J = [-a, a]$ 위에서 well-defined 입니다.

## Convergence of Function Sequence

위의 과정에서 함수열 $\{ U_{k+1} (t) \}$이 well-defined인 것을 확인 했습니다. 이제 $U_{k}(t)$가 solution인 $X(t)$에 수렴한다는 것을 보여야 합니다. 함수열이 수렴하는지 보이기 위해 아래의 것을 보여야 하는데

$$
\lim_{k\rightarrow\infty}\| U_{k+1}(t) - U_{k}(t) \| < + \infty
$$



# References

https://youtu.be/Zxr6Wekwxh0?si=k3uo7A_srkM8Us7R

https://people.math.wisc.edu/~aseeger/319/notes2.pdf
^읽어봐야 함