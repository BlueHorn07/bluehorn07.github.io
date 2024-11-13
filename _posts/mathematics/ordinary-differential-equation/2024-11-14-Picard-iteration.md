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

[수렴 판정]

ING....

</div>


# n-dim Picard Iteration

미분방정식의 가장 일반적인 형태는 $X' = F(X)$ 입니다. 이때, $F(X)$는 임의의 벡터 필드 입니다. 당장 $X' = F(X)$인 시스템을 푸려고 하면 머리가 아프니까 일단 $x' = f(x)$인 1차원에서 Picard Iteration으로 미분방정식의 해가 존재하고, 유일한지를 밝혔습니다.



# Reference

- [공데셍님의 블로그](https://vegatrash.tistory.com/49)

