---
title: "칸토어 집합"
toc: true
author: bluehorn_math
categories: ["Calculus", "Set Theory"]
excerpt: "길이는 0이면서, 집합의 크기는 실수 전체와 반직관적인 집합"
---

# 정의

<a title="127 &quot;rect&quot;, Public domain, via Wikimedia Commons" href="https://commons.wikimedia.org/wiki/File:Cantor_set_in_seven_iterations.svg"><img width="512" alt="Cantor set in seven iterations" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Cantor_set_in_seven_iterations.svg/512px-Cantor_set_in_seven_iterations.svg.png?20101225221326" class="align-center"></a>

<div class="definition" markdown="1">

닫힌 구간 $[0, 1]$을 3등분 하자. 가운데인 $(1/3, 2/3)$ 구간을 제거한다. 1/3 길이의 남은 두 구간에 대해서도 같은 작업을 하여 가운데 구간을 제거한다. 이 과정을 무한번 반복한다.

</div>

# 길이

칸토어 집합을 만들 때, 각 단계에서 빠지는 구간의 길이는 $1/3$, $2/9$, $4/27$, ... 만큼씩 제거된다. 이것을 모두 합하면 아래와 같다.

$$
\sum_{n=1}^{\infty} = \left( \frac{2}{3} \right)^{n-1} \cdot \frac{1}{3} = \frac{1}{3} \cdot \left( \frac{1}{1-2/3}\right) = 1
$$

이 된다. 즉, 칸토어 집합의 길이는 초기 길이 $1$에서 제거된 구간의 길이 $1$을 빼서 $0$이 된다.

그러나 칸토어 집합의 원소의 갯수는 구간 $[0, 1]$, 즉 실수 전체 $\mathbb{R}$과 같다. (추후에 증명 예정.) 즉, 길이는 $0$인데, 집합 크기는 $\mathbb{R}$인 반직관적인 상황이 발생한다.

# 집합의 크기

(추후에 집합론 과목을 다시 볼 때 정리해보겠습니다...)

