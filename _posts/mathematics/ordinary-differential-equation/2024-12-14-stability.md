---
title: "Stability"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Differential Equations"]
excerpt: ""
---

복수전공하고 있는 수학과의 졸업시험을 위해 학부 수학 과목들을 다시 공부하고 있습니다만... 미분방정식은 졸업시험 대상 과목이 아니라는 걸 나중에 알게 되었습니다... OTL... 그래도 이왕 시작한 거 다시 복습 좀 해봅시다! 🏃 [미분방정식 포스트 전체 보기](/categories/differential-equations)
{: .notice--info}

# Stable

Fixed Point가 Stable 지점인지 Unstable 지점인지 수학적으로 정의 해보는 파트입니다.

<div class="theorem" markdown="1">

$X^{\ast}$ is a stable fixed point if for every neighborhood $O \subset \mathbf{R}^n$ of $X^{\ast}$,

there exist neighborhood $O_1 \subset O$ s.t. the solution $X(t)$ with $X(0) = X_0 \in O_1$ is defined and remains in $O$ for all $t > 0$.

</div>

# Asymptotically Stable

Stable 성질을 만족하면서, Solution Curve $X(t)$가

$$
\lim_{t\rightarrow \infty} X(t) = X^{\ast}
$$

를 만족하는 경우를 말한다.

# Visualizations

## Stable vs. Asymptotically Stable

https://logancollinsblog.com/2018/01/27/notes-on-dynamical-systems/

요 블로그에 있는 플롯이 두 개념을 비교하는데, 도움이 되었다.

Center의 경우는 Fixed point 주변을 Solution Curve가 돌기만 할 뿐 수렴하지는 않는다. 반면에 Stable Node 경우와 Stable Spiral 경우는 Fixed point로 Solution Curve가 수렴한다!

## Kind of Fixed Points

<p><a href="https://commons.wikimedia.org/wiki/File:Fixed_Points.gif#/media/File:Fixed_Points.gif"><img src="https://upload.wikimedia.org/wikipedia/commons/0/00/Fixed_Points.gif" alt="Fixed Points.gif" height="800" width="800"></a><br>By <a href="//commons.wikimedia.org/wiki/User:Berto" title="User:Berto">Jacopo Bertolotti</a> - <a rel="nofollow" class="external free" href="https://twitter.com/j_bertolotti/status/1634148351296806914">https://twitter.com/j_bertolotti/status/1634148351296806914</a>, <a href="http://creativecommons.org/publicdomain/zero/1.0/deed.en" title="Creative Commons Zero, Public Domain Dedication">CC0</a>, <a href="https://commons.wikimedia.org/w/index.php?curid=129562629">Link</a></p>

# Theorem

이젠 하도 많이 봐서 당연할 수도 있지만, 아래 정리가 성립한다.

<div class="theorem" markdown="1">

If $n$-dimensional system $X' = F(X)$ has an fixed point $X^{\ast}$ and the eigenvalues of the linearized system at $X^{\ast}$ have negative real part.

Then $X^{\ast}$ is asymptotically stable.

</div>

