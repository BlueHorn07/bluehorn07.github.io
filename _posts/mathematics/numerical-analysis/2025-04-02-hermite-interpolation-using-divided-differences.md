---
title: "Hermite Interpolation using divided differences"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: ""
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# 들어가며

이전 포스트에서 "[Hermite Interpolation](/2025/03/31/hermite-interpolation/)" 방법을 살펴보았다. 그러나 이걸 손으로 직접 구하는 것은 분명히 한계가 있다!! 왜냐하면, 공식을 보면 라그랑주의 기저 다항식 $L_i(x)$의 도함수 $L_i'(x)$까지 구해야 하기 때문이다.

그래서 나온게 Divided Difference를 사용한 Hermite approximation 이다.


실제로 HW 문제를 풀었을 때, 에르미트 보간법의 결과와 동일하게 나와서 놀랐다!

