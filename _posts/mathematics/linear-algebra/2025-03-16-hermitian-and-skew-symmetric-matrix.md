---
title: "Hermitian Matrix, and Skew-symmetric Matrix"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Linear Algebra"]
excerpt: ""
---

복수전공하고 있는 수학과의 학부 졸업시험을 위해 2024년 10월부터 선형대수를 다시 공부하고 있습니다. (현재진행형... 🏃‍♂️‍➡️) 선형대수에 대한 전체 포스트 목록은 "[Linear Algebra](/categories/linear-algebra)"에서 확인하실 수 있습니다!
{: .notice }

# 들어가며

지난 포스트에서 이차 형식에 대해 살펴보았습니다 ㅎㅎ 이번 포스트도 이차 형식에 대해 살펴보는데요!
이차형식에서 보이는 2가지 특수 케이스를 살펴봅니다!

# Hermitian Matrix

지금까지는 이차형식 $Q(\mathbf{x})$를 실수 벡터 영역에서 정의하고 살펴보았습니다.
이것을 "복소수" 영역까지 확장하고, 여기 위에서 다시 Positive Definite를 정의할 수 있습니다!

<div class="definition" markdown="1">

**Positive Definite** when for all non-zero $\mathbf{z} \in \mathbb{C}$,

$$
Q(\mathbf{z}) = \mathbf{z}^T A \mathbf{z} > 0
$$

</div>

# Skew-symmetric Matrix

