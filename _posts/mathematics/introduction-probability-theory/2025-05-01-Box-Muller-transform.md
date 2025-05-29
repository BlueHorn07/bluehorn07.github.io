---
title: "Box-Muller Transform"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Probability"]
excerpt: "정규 분포를 컴퓨터로 만드는 방법에 대해 🖥️"
---

2025년 마지막 학기 수업인 "확률개론(MATH431)” 수업에서 배운 것과 공부한 것을 정리한 포스트입니다. 전체 포스트는 [Introduction to Probability Theory](/categories/introduction-probability-theory)에서 확인하실 수 있습니다 🎲
{: .notice--info}

# 들어가며

이번 포스트에서 소개하는 것은 컴퓨터 랜덤 난수와 관련 있습니다! (벌써 재밌음 ㅋㅋ)

(이것도 논쟁이 있지만) 완전히 랜덤 하다는게 무엇일까요? 그리고 컴퓨터는 그 랜덤 난수를 어떻게 만드는 걸까요?

그리고 우리 생활에서 정말정말 유요한 분포인 "정규 분포"를 따르는 랜덤 난수를 컴퓨터에서 만드려면 어떻게 해야 할까요?

이 기법에 대해 연구한 것이 Box와 Muller이고, 이들이 1958년 이 기법을 출시했고, 이것이 초기 컴퓨터 계산학의 기반이 되었다고 합니다!

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


