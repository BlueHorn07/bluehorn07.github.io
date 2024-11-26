---
title: "Gronwall's Inequality"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "미분방정식에서 만나는 부등식. 간단하지만 강력한 도구!!"
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
- [Gronwall's Inequality](/2024/11/14/Gronwall-inequality/) 👋
- [Some Preliminaries](/2024/11/16/some-preliminary-the-existence-and-uniqueness-theorem/)

</div>

# Gronwall's Inequality

미분방정식에 대해 성립하는 일반적인 형태의 부등식입니다. 처음에는 이게 어떤 의미인지 잘 와닿지 않아서 이해하는데 시간이 좀 걸렸습니다. 다른 작업들을 좀 하다가 다시 돌아오니 머리가 맑아졌는지 이제 이해가 좀 되네요 ㅎㅎ

일단 이 부등식에는 (1) 미분 폼(form)가 (2) 적분 폼이 있습니다. 일단 쉬운 버전은 미분 폼입니다. 미분폼부터 보는게 쉬운 길인 것 같습니다.

## Differential Form

우리는 $u'(t) = \beta(t) u(t)$라는 미분방정식이 있을 때, 이것을 푸는 방법을 잘 알고 있습니다. 그냥 양변에 $u(t)$를 나누고 적분하면

$$
\ln (u(t)) = \int \beta(t) + C
$$

가 되고, 여기에 지수함수 $\ln$을 우변으로 넘겨주면

$$
u(t) = u(0) \exp \left( \int_0^t \beta(s) \, ds \right)
$$

가 됩니다. Gronwall은 위의 등식이 **부등식이 되어도 성립한다**고 말합니다!!

<div class="theorem" markdown="1">

$u(t)$ and $\beta(t)$ are real-valued continuous functions. If $u'(t)$ is differentiable and satisfies the below inequality

$$
u'(t) \le \beta(t) u(t)
$$

then, $u(t)$ is bounded by the solution of the corresponding differential equation $v'(t) = \beta(t) v(t)$:

$$
u(t) \le u(0) \exp \left( \int_0^t \beta(s) \, ds \right)
$$

\* Remark: there's no assumption on the sign of $u(t)$ ans $\beta(t)$.

</div>

지금까지 미분방정식을 하면서, 부등식에 대해서는 거의 다룬바가 없었습니다. 그런데, Gronwall 부등식은 지금까지 배운 미분방정식의 결과를 부등식으로 바꾸면 되는 단순한 도구지만, 미분방정식의 다양한 문제들을 해결하는 데에 사용할 수 있습니다.

### Proof

<div class="proof" markdown="1">

주어진 부등식을 등식으로 만족하는 연속 함수 $v(t)$가 있다고 가정하자. 그리고 함수 $v(t)$의 초기값 $v(0) = 1$이다.

$$
v(t) = \exp \left( \int_0^t \beta(s) \, ds \right)
$$

이 함수를 $u(t)$ 함수에 나눈 $u(t) / v(t)$를 미분한 결과를 살펴보면

$$
\left(\frac{u(t)}{v(t)}\right)'
= \frac{u'(t) v(t) - u(t) v'(t)}{(v(t))^2}
= \frac{u'(t) v(t) - u(t) \beta(t) v(t)}{(v(t))^2}
$$

위의 식에서 $u'(t) \le \beta(t) u(t)$라는 부등식을 적용하면

$$
\frac{u'(t) v(t) - u(t) \beta(t) v(t)}{(v(t))^2}
\le \frac{\beta(t) u(t) v(t) - u(t) \beta(t) v(t)}{(v(t))^2}
= 0
$$

즉, 아래의 부등식이 성립한다.

$$
\left(\frac{u(t)}{v(t)}\right)' \le 0
$$

이것은 $u(t) / v(t)$가 non-positive 함수이고, 초기값 $u(0) / v(0)$에 bounded 되어 있음을 말한다. 따라서,

$$
\frac{u(t)}{v(t)} \le \frac{u(0)}{v(0) = 1} = u(0)
$$

위의 부등식에서 $v(t)$를 양변에 곱하면, Gronwall 부등식의 결과를 얻는다.

</div>

## Integral Form

처음엔 적분폼부터 봤는데, 개인적으로 미분폼부터 봐야 정리가 이해 되는 것 같습니다. 일단 가장 기본적인 형태부터 보겠습니다.

### Simplest

<div class="theorem" markdown="1">

Let $u(t)$ be real-valued continuous functions. And if $u(t)$ satisfies the below inequality

$$
u(t) \le C + \int_0^t K \, u(s) \, ds
$$

then,

$$
u(t) \le C e^{Kt}
$$

</div>

사실 미분폼이랑 되게 형태가 비슷한데, 처음에 주어진 부등식을 미분하면, $u'(t) \le k u(t)$가 나오고, 요걸 잘 적분하면 $u(t) \le u(0) e^{kt}$가 나오기 때문이다. 물론 이건 엄밀한 증명은 아니고 직관적으로 보면 그렇다~~~

<div class="proof" markdown="1">

[case 1: $C > 0$]

부등식의 우변과 같은 함수 $v(t)$가 존재한다고 하자. 그러면

$$
u(t) \le v(t) = C + \int_0^t K u(s) \, ds
$$

$v(t)$를 미분하면

$$
v'(t) = k u(t)
$$

이제 위의 식에 양변을 $v(t)$로 나누면

$$
\frac{v'(t)}{v(t)} = \frac{k u(t)}{v(t)}
$$

가 되는데, $u(t) \le v(t)$의 관계로 인해 $u(t) / v(t) \le 1$이다. 따라서

$$
\frac{v'(t)}{v(t)} = \frac{k u(t)}{v(t)} \le K
$$

양변을 적분하면,

$$
\begin{aligned}  
\ln (v(t)) &\le \ln v(0) + Kt \\
\ln (v(t)) &\le \ln C + Kt \\
v(t) &\le C \cdot e^{Kt}
\end{aligned}
$$

이때, $u(t) \le v(t)$이므로

$$
u(t) \le v(t) \le C \cdot e^{Kt}
$$

</div>

### A bit general


<div class="theorem" markdown="1">

Let $\alpha(t), \beta(t), u(t)$ be real-valued continuous functions, and $\alpha(t)$ is **non-decreasing**. And if $u(t)$ satisfies the below inequality

$$
u(t) \le \alpha(t) + \int_0^t \beta(s) u(s) \, ds
$$

then,

$$
u(t) \le \alpha(t) \exp \left( \int_0^t \beta(s) \, ds \right)
$$

</div>




# References

- [Youtube: Dr Peyam](https://youtu.be/vP3Bd_c_EEw?si=fy-Vt1_jb3bDRk3F)
- [Wikipedia](https://en.wikipedia.org/wiki/Gr%C3%B6nwall%27s_inequality)
