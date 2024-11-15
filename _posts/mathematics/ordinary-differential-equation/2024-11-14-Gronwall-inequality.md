---
title: "Gronwall's Inequality"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: ""
---

(주의) 아직 작성 중인 포스트입니다! 🙏
{: .notice--info}


복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# 들어가며

![](/images/meme/panic.png){: .align-center style="max-width: 300px" }

경고하는데 여기서부터 진짜 완전히 새로운 내용입니다...;; 지금까지는 미분방정식의 심화 버전을 하는 느낌이었다면, 여기서부터 진짜 `MATH4xx` 과목의 위엄이 뭔지 작살나게 느낄 수 있습니다 ㅋㅋ

이 챕터의 목표는 ODE의 solution이 존재(Existence)하고 그리고 유일(Uniqueness)하다는 것을 보이는 것입니다. 그런데 저는 감자(🥔)니까 그 주변 곁다리부터 다가가보도록 하겠습니다.

<div class="statement" markdown="1">

**[Existence and Uniqueness의 곁다리들]**

순서는 상관없습니다.

- [Lipschitz Constant](/2024/11/14/Lipschitz-constant/)
- [Picard Iteration](/2024/11/14/Picard-iteration/)
- [Gronwall's Inequality](/2024/11/14/Gronwall-inequality/) 👋

</div>

# Gronwall's Inequality

<div class="theorem" markdown="1">

Suppose $u(t)$ satisfies

$$
\u(t) \le A + B \int_0^t u(s) \, ds
$$

for all $t \in [0, \alpha]$, and $A \ge 0$ and $

then for all $t \in [0, \alpha]$

$$
u(t) \le A e^{kt}
$$

</div>

미분폼이 있고, 적분폼이 있고

