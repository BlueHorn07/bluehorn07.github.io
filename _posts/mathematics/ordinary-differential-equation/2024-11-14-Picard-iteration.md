---
title: "Picard Iteration"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "$X' = F(X)$의 해가 존재하고 유일함을 보이는 과정."
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
- [Picard Iteration](/2024/11/14/Picard-iteration/) 👋
- [Gronwall's Inequality](/2024/11/14/Gronwall-inequality/)
- [Some Preliminaries](/2024/11/16/some-preliminary-the-existence-and-uniqueness-theorem/)

</div>

# Picard Iteration

1차원 미분방정식의 가장 일반적인 형태는 아래와 같습니다.

$$
x' = f(x)
$$

이떄, $f(x)$는 임의의 함수이고, $x(0) = x_0$입니다. 우리의 목표는 이런 미분방정식에 해가 존재하고, 또 그 해가 유일해라는 것을 보이고자 합니다.

<div class="theorem" markdown="1">

[적분방정식 Form]

$$
x(t) = x_0 + \int_{t_0}^t f(x(s)) \, ds
$$

</div>

미분방정식이 방정식에 도함수가 있어서 미분방정식이니 위의 식은 "적분"이 있어서 "적분방정식"라고 합니다. 그리고 위의 식은 그냥 $x' = f(x)$에서 양변을 적분한 것에 불과합니다.

여기에서 Iterative 한 방식으로 solution $x(t)$를 구할 것 입니다!! 방식은 예제를 통해 설명하겠습니다.

## Example

$$
x' = 2t (1 + x), \qquad x(0) = 0
$$

인 형태의 미분방정식이 있다고 가정합시다. 이것을 Picard 방식으로 풀어봅시다. 풀이를 하기 전에 [공데셍](https://vegatrash.tistory.com/49)님의 글이 이해에 많은 도움이 되었음을 밝힙니다.

<div class="proof" markdown="1">

일단 적분 방정식부터 세워봅니다. 이때, 실제 solution $x(t)$과 구분하기 위해 $\phi(t)$라는 함수로 표기를 바꾸었습니다.

$$
\phi(t) = \int_0^t 2s (1 + \phi (s)) \, ds
$$

일단 초기 조건 $x(0) = 0$을 이용해 초기 함수를 $\phi_0(t) = 0$으로 설정하고 첫번째 Iteration을 수행합니다.

$$
\phi_1(t) = \int_0^t 2s (1 + 0) \, ds
= t^2
$$

같은 방법으로 $\phi_2(t)$를 구합니다.

$$
\phi_2(t) = \int_0^t 2s (1 + s^2) \, ds = t^2 + \frac{t^4}{2}
$$

또 구합니다.

$$
\phi_3(t) = \int_0^t 2s (s^2 + \frac{s^4}{2}) \, ds = t^2 + \frac{t^4}{2} + \frac{t^6}{2\cdot 3}
$$

이제는 조금 패턴이 나오는데, 암튼 반복하면 아래와 같은 형태가 됩니다.

$$
\begin{aligned}
\phi_n(t)
&= t^2 + \frac{t^4}{2} + \frac{t^6}{2\cdot 3} + \frac{t^8}{2\cdot 3 \cdot 4} + \cdots + \frac{t^{2n}}{n!} \\
&= \sum_{k=1}^{n} \frac{t^{2k}}{k!}
\end{aligned}
$$

우리는 $\phi(t) = \lim_{n\rightarrow \infty} \phi_n(t)$를 수행하여 이것이 ODE의 solution임을 주장하고 싶다. 그런데, 이를 주장하려면 일단 이 급수가 수렴하는지를 먼저 보여야 한다.

<hr/>

**[급수의 수렴 판정]**

비율판정법을 수행한다.

$$
\lim_{n\rightarrow \infty} \frac{a_{n+1}}{a_n}
= \frac{t^{2n + 2}}{(n+1)!} \cdot \frac{n!}{t^{2n}}
= \frac{t^2}{n+1} = 0 < 1
$$

따라서 급수가 수렴한다. 이때, 위의 극한식은 $t$ 값에 상관없이 수렴하므로 Convergence radius는 전체 영역이다.

<hr/>

사실 요 급수는 지수 함수의 꼴로 표현할 수 있는데, $e^t$이

$$
e^t = 1 + \frac{t}{1} + \frac{t^2}{2!} + \cdots + \frac{t^n}{n!} + \cdots
$$

이므로 급수를 $\phi(t) = e^{t^2} - 1$로 정리할 수 있다.

<hr/>

**[해의 유일성 증명]**

증명을 위해 해가 유일하지 않고, 또 다른 해 $\psi(t)$가 존재한다고 가정한다. 두 함수가 다른 함수이므로 영역 위의 적어도 한 점 $t$에서 $\phi(t) - \psi(t) \ne 0$이다.

$\psi(t)$도 적분방정식의 해이므로 아래의 등식이 성립한다.

$$
\psi(t) = \int_0^t 2s(1 + \psi(s))\, ds
$$

이때, $\phi(t) - \psi(t)$에 대한 식을 세워 이것이 모든 $t$에 대해 non-zero인지 확인해보자.

$$
\phi(t) - \psi(t)
= \int_0^{t} 2s(\phi(s) - \psi(s))\, ds
$$

위의 적분방정식의 양변에 절댓값을 씌우면 아래와 같은 부등식이 성립한다.

$$
\|\phi(t) - \psi(t)\|
= \left\|\int_0^{t} 2s(\phi(s) - \psi(s))\, ds\right\|
\le \int_0^{t} \| 2s (\phi(s) - \psi(s))\| \, ds
$$

이게 성립하는 이유는, 양수-음수 둘다 가능한 함수를 적분한 넓이보다 양수만 가능한 절댓값을 적분한 것의 넓이가 더 크기 때문이다.

적분 내부에 있는 $2s (\phi(s) - \psi(s))$에서 $2s$를 밖으로 꺼내려고 한다. $0 < t < A$인 어떤 상수 $A$를 잡는다면, 아래의 부등식이 만족한다.

$$
\int_0^{t} \| 2s (\phi(s) - \psi(s))\| \, ds
\le 2A \cdot \int_0^{t} \| (\phi(s) - \psi(s))\| \, ds
$$

앞으로의 과정에서 표기의 편의를 위해 $B = 2A$로 대체하여 표기한다. 다시 부등식을 세워보면

$$
\|\phi(t) - \psi(t)\|
\le B \cdot \int_0^{t} \| (\phi(s) - \psi(s))\| \, ds
$$

$U(t) = \|\phi(t) - \psi(t)\|$로 두고 식을 다시 쓰면 아래와 같은데,

$$
U(t)
\le B \cdot \int_0^{t} U(s) \, ds
$$

$U(0) = 0$는 적분범위에 따른 결과이고, $U(t)$ 함수가 절댓값 함수이기 때문에 $U(t) \ge 0$이 성립한다. 이제 미적분학의 기본원리에 의해 양변을 미분하면

$$
U'(t) \le B \cdot U(t)
$$

가 되고, RHS를 LHS로 옮기면

$$
U'(t) - B \cdot U(t) \le 0
$$

이때, 양변에 $e^{-Bt}$를 곱해준다. 이때, $e^{-Bt}$는 Integrating Factor이다.

$$
(e^{-Bt} U'(t) - B e^{-Bt} U(t)) = (e^{-Bt} \cdot U(t))' \le 0
$$

이 된다!! 부등식을 적분하면

$$
\begin{aligned}
e^{-Bt} \cdot U(t) &\le 0 \\
U(t) &\le 0
\end{aligned}
$$

라는 결과가 나온다. 이것은 처음에 관찰한 $U(t) = \|\phi(t) - \psi(t)\| \ge 0$와 일치하려면 $U(t) = 0$이 성립해야 하고, 이는 $\phi(t) = \psi(t)$라는 것을 말한다. 이것은 처음 가정에 모순되므로, $\psi(t)$는 존재하지 않고, $\phi(t)$가 유일한 solution이다. $\blacksquare$

</div>

## Picard Iterations: Theorem statement

예제의 풀이가 길어졌는데, 암튼 Picard Iteration으로 solution을 얻을 수 있을거라고 더욱 믿게 되었다!! 🙂 이제는 이 과정을 엄밀히 정의해보자.

<div class="theorem" markdown="1">

Given ODE $x'(t) = f(t, x(t))$, and initial value $x(0) = x_0$, we can get a solution by iteration as follows

- start as $\phi_0(t) = x_0$
- generate sequence of functions as

$$
\phi_{k+1}(t) = x_0 + \int_0^t f(s, \phi_k(s)) \, ds
$$

this sequence of functions converges to the solution of the given ODE.

</div>

증명은... 패스한다!! ~~나는 컴공과니까!!~~ 어떻게 보면 미분방정식을 "Numerical Method"로 푸는 방식이다. 지금까지 했던 미방 풀이와는 접근 방식이 좀 달라서 어색 했던 것 같다.

# Picard Iteration on ODE System

미분방정식의 가장 일반적인 형태는 $X' = F(X)$ 입니다. 이때, $F(X)$는 임의의 벡터 필드 입니다. 당장 $X' = F(X)$인 시스템을 푸려고 하면 머리가 아프니까 😵‍💫 일단 $x' = f(x)$인 1차원에서 Picard Iteration으로 미분방정식의 해가 존재하고, 유일한지를 밝혔습니다.

## Examples

신기하게도 ODE System에서도 Picard Iteration을 쓸 수 있습니다. 아래와 같은 ODE System을 Picard로 풀어봅시다.

$$
X' = F(X) = \begin{pmatrix}
0 & 1 \\
-1 & 0
\end{pmatrix} X
$$

그리고 Initial Value는 $X(0) = (1, 0)$입니다. 지금가지 4학년 미방에서 많이 본 녀석으로 실제 솔루션은 $X(t) = (\cos t, -\sin t)$로 나옵니다.

<div class="theorem" markdown="1">

Picard Iteration을 수행합시다.

$$
U_0(t) = \begin{pmatrix}
1 \\
0
\end{pmatrix}
$$

$$
U_1(t) = \begin{pmatrix}
1 \\
0
\end{pmatrix}
+ \int_0^t \begin{pmatrix}
0 & 1 \\
-1 & 0
\end{pmatrix}
\begin{pmatrix}
1 \\
0
\end{pmatrix} ds
= \begin{pmatrix}
1 \\
0
\end{pmatrix} + 
\int_0^t \begin{pmatrix}
0 \\
-1
\end{pmatrix} ds
=
\begin{pmatrix}
1 \\
-t
\end{pmatrix}
$$

$$
U_2(t) = \begin{pmatrix}
1 \\
0
\end{pmatrix}
+ \int_0^t \begin{pmatrix}
-s \\
-1
\end{pmatrix} ds
= 
\begin{pmatrix}
1 - t^2/2 \\
- t
\end{pmatrix}
$$

$$
U_3(t)
= \begin{pmatrix}
1 \\
0
\end{pmatrix}
+ \int_0^t \begin{pmatrix}
-s \\
-1 + s^2/2
\end{pmatrix} ds
= \begin{pmatrix}
1 + -t^2/2 \\
-t + t^3/3!
\end{pmatrix}
$$

$$
U_4(t)
= \begin{pmatrix}
1 \\
0
\end{pmatrix}
+ \int_0^t \begin{pmatrix}
-s+s^3/3! \\
-1 + s^2/2
\end{pmatrix} ds
= \begin{pmatrix}
1 - t^2/2! + t^4/4! \\
-t + t^3/3!
\end{pmatrix}
$$

이 과정을 반복하다보면, $U_n(t)$는 아래와 같이 결정된다.

$$
U_n(t)
= \begin{pmatrix}
1 - t^2/2! + t^4/4! + \cdots \\
- (t - t^3/3! + t^5/5! - \cdots)
\end{pmatrix}
$$

함수열을 극한으로 보내면 아래와 같이 수렴한다.

$$
U(t) = \lim_{n\rightarrow \infty} U_n(t)
= \begin{pmatrix}
\cos (t) \\
- \sin (t)
\end{pmatrix}
$$

</div>

# Reference

- [공데셍님의 블로그](https://vegatrash.tistory.com/49)
- Differential Equations, Dynamical Systems & An Introduction to Chaos: Chapter 7.2: Non-linear Systems
