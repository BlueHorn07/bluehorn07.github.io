---
title: "System of ODEs"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: "n차 미분방정식을 n개의 1차 미분방정식 시스템으로 변환하는 방법에 대하여"
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# 들어가며

미분방정식의 끝물에 배우는 기법이다. 미분방정식 강의를 들을 때, 지금까지 했던 내용을 뭔가 반복하는 것 같아서 설렁설렁 들었던 기억이 있는데, "상미분방정식([상미분방정식(MATH412)](/categories/ordinary-differential-equations))"을 공부할 때 다시 만나게 되었습니다 😬

# What is it?

<iframe width="560" height="315" src="https://www.youtube.com/embed/1yLAFsej3to?si=TTGn2y8JJuJQY2mU" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>

어떻게 하는지는 요 강의를 참고합시닷!! 기법 자체는 별로 어렵지 않던 것 같다. (까먹지만 않는다면!) 요 기법을 정리하려고 요 포스트를 쓸 마음을 먹은 것은 아니다 ㅎㅎ

# Simple Harmonic Oscillation

미분방정식을 그래도 조금 풀어봤다면,

$$
x'' = -\omega^2 x
$$

라는 미분방정식에 익숙할 것이다. 요것은 Harmonic Oscillation 상황 중에서도 외력이 없는 "[Simple Harmonic Oscillation](/2024/11/12/harmonic-oscillation/)"에 해당한다.

이것을 System of ODE로 표현하면 아래와 같다.

$$
\begin{pmatrix}
x_0 \\
x_1
\end{pmatrix}'
=
\begin{pmatrix}
0 & 1 \\
-\omega^2 & 0
\end{pmatrix}
\begin{pmatrix}
x_0 \\
x_1
\end{pmatrix}
$$

그런데, 이걸 요런 형태로 표현해도 동일하다!!

$$
\begin{pmatrix}
x_0 \\
x_1
\end{pmatrix}'
=
\begin{pmatrix}
0 & \omega \\
-\omega & 0
\end{pmatrix}
\begin{pmatrix}
x_0 \\
x_1
\end{pmatrix}
$$

우리는 이 ODE의 해가 $x_0(t) = \cos \omega t$임을 알고 있다. 이걸 첫번째 System에 적용해도 성립하고, 두번째 System에 적용해도 성립한다!! 두번째 System에서는 $x_1(t) = \sin \omega t$가 되어 성립한다.

이 상황이 처음에는 이해가 잘 안 되었는데, 같은 System of ODE를 표현하는 방식이 유일하게 존재할 거라고 생각했던 것 같다. 그러나 System of ODEs의 표현은 유일하지 않다. 위와 같은 반례가 존재한다!!

영상에 나왔던 방식을 따르지 않았으니 System of ODEs가 아닌건가?라고 생각이 들 수도 있지만, System of ODEs는 주어진 "n차 미분방정식은 n개의 1차 미분방정식으로 바꾸는 것"라고만 하지 정확히 어떤 방법을 써야 한다는 나오지 않는다. 즉, 이 규칙에만 맞게 **보조 변수**를 도입하면 된다는 것!

# Complex Root Case

Simple Harmonic Oscillation의 경우는 Eigen Value $\alpha \pm \beta i$에서 실수부가 없는 경우이다. 만약 실수부가 존재한다면, System of ODEs로 표현하면 아래와 같다.

$$
\begin{pmatrix}
x_0 \\
x_1
\end{pmatrix}'
=
\begin{pmatrix}
\alpha & \beta \\
-\beta & \alpha
\end{pmatrix}
\begin{pmatrix}
x_0 \\
x_1
\end{pmatrix}
$$

허근에서 실수부가 존재한다면, System of ODEs의 표현 방법은 이것 뿐이다. 그리고 잘 보면 $X' = AX$의 꼴을 가지는데, 기저 변환(change of basis) 후에 표현한 System of ODEs로 해석할 수도 있을 것 같다.

미방 수업을 잘 따라왔다면 이미 알겠지만 Solution은 아래와 같다.

- $x_0(t) = e^{\alpha} \cos \beta t$
- $x_1(t) = e^{\alpha} \sin \beta t$
