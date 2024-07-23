---
title: "Factor Group - Application"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Modern Algebra1"]
---


2020-2학기, 대학에서 '현대대수1' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<hr>

<span class="statement-title">Theorem.</span><br>

<div class="notice" markdown="1">

Let $H \times K$ be a direct product of group $H$, $K$.

Then, $\overline{H} = H \times \\{ e_K \\}$ is a normal subgroup of $H \times K$.

이때,

$$
H \times K / {\overline{H}} = H \times K / H \times \{ e_K \} \cong K
$$

</div>

<span class="statement-title">proof.</span><br>

<div class="math-statement" markdown="1">

2번째 명제만 증명하겠다.

Homomorphism $\phi$를 하나 정의하자.

$$
\begin{aligned}
    \phi: H \times K & \longrightarrow K \\
            (h, k) & \longmapsto k
\end{aligned}
$$

이때, homomorphism의 kernel을 생각해보자. 그러면, $\ker \phi = (H, e_K)$이다.

FHT에 따르면,

$$
H \times K / {\ker \phi} \cong \phi[ H \times K ]
$$

이다.

따라서

$$
\begin{aligned}
    H \times K / \ker \phi &\cong \phi[H \times K] \\
    H \times K / (H, e_K) = H \times K / \overline{H} &\cong K
\end{aligned}
$$

$\blacksquare$

</div>


<br>
<hr>

<span class="statement-title">Theorem.</span><br>

<div class="statemeht" markdown = "1">

A factor group of cyclic is also cyclic.

</div>

<br>
<hr>

아주아주아주 중요한 문제다!!

<span class="statement-title">example.</span><br>

$$
\mathbb{Z}_4 \times \mathbb{Z}_6 / <(2, 3)> \; \cong \; ?
$$

<span class="statement-title">Sol.</span><br>

<div class="math-statement" markdown="1">

먼저 주어진 factor group $\mathbb{Z}_4 \times \mathbb{Z}_6 / <(2, 3)>$의 위수를 구해보자.

$$
\lvert \mathbb{Z}_4 \times \mathbb{Z}_6 / <(2, 3)> \rvert = 24/2 = 12
$$

직접 계산을 해보면, $\lvert <(2, 3)> \rvert = 2$임을 확인할 수 있다.

<br>

이 이후에는 cyclic group인 $\mathbb{Z}_4 \times \mathbb{Z}_6$의 factor group이므로 $\mathbb{Z}_4 \times \mathbb{Z}_6 / <(2, 3)>$ 역시 cyclic group이어야 한다.

이때, "F.T. of f.g. abelian"을 활용해 위수가 12인 Cyclic Grouop을 찾아보면 아래의 두 Group이 된다.

- $\mathbb{Z}_3 \times \mathbb{Z}_4$
- $\mathbb{Z}_2 \times \mathbb{Z}_2 \times \mathbb{Z}_3$

<br>

이번에는 $H = <(2, 3)>$의 left coset들 중 하나를; 직접 살펴보자.

$(1, 0) + H \in \mathbb{Z}_4 \times \mathbb{Z}_6 / <(2, 3)>$

$(1, 0) + H$의 위수는

- $\left((1, 0) + H\right) + \left((1, 0) + H\right) = \left((2, 0) + H\right) \ne H$
- $(3, 0) + H \ne H$
- $(4, 0) + H = H$

따라서 $\lvert (1, 0) + H \rvert = 4$이다.

<br>

이제 앞에서 "F.T. of f.g. abelian"에서 얻은 두 cyclic group 중 위수 4의 원소를 갖는 group을 찾아보자.

$\mathbb{Z}_2 \times \mathbb{Z}_2 \times \mathbb{Z}_3$는 위수가 1, 2, 3, 6인 원소만을 갖는다. 따라서 이 cyclic group은 우리가 찾는 동형인 Group이 아니다!

$\mathbb{Z}_3 \times \mathbb{Z}_4$의 경우, 위수가 4인 원소를 갖는다! 따라서 $\mathbb{Z}_3 \times \mathbb{Z}_4$가 우리가 찾고자 하는 동형인 Cyclic Group이다!!

따라서

$$
\mathbb{Z}_4 \times \mathbb{Z}_6 / <(2, 3)> \; \cong \; \mathbb{Z}_3 \times \mathbb{Z}_4
$$

$\blacksquare$

</div>

<br>
<hr>

마찬가지의 방법으로 아래의 문제도 풀어보자.

<span class="statement-title">example.</span><br>

$$
\mathbb{Z} \times \mathbb{Z} / <(1, 1)> \; \cong \; ?
$$

<span class="statement-title">Sol.</span><br>

<div class="math-statement" markdown="1">

$<(1, 1)>$로 생성되는 left coset들을 생각해보자. 그러면

- ...
- $\Delta = -1$: ..., (-1, 0), (0, 1), (1, 2), ...
- $\Delta = \;\;\; 0$: ..., (-1, -1), (0, 0), (1, 1), ...
- $\Delta = +1$: ..., (0, -1), (1, 0), (2, 1), ...
- ...

그러면, 대략 이 묶음이 $\mathbb{Z}$ 만큼 존재하게 된다.

따라서

$$
\mathbb{Z} \times \mathbb{Z} / <(1, 1)> \; \cong \; \mathbb{Z}
$$

$\blacksquare$

</div>
