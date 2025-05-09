---
title: "Newton's Method"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Numerical Analysis"]
excerpt: "비선형 함수의 해를 찾기 위해 접선을 기반으로 선형 근사를 반복하는 수치적 접근"
---

수학과 복수전공을 위해 졸업 마지막 학기에 "수치해석개론" 수업을 듣게 되었습니다. 수학과 졸업시험도 겸사겸사 준비할 겸 화이팅 해봅시다!! 전체 포스트는 "[Numerical Analysis](/categories/numerical-analysis)"에서 확인할 수 있습니다.
{: .notice }

# Neton's Method

비선형 방정식 $f(x) = 0$의 근을 찾기 위한 수치적 방법 입니다.

$$
p_{n+1} = p_n - \frac{f(p_n)}{f'(p_n)}
$$

매우 빠른 수렴 속도를 보장함. Quadratic Converges 이고, 한번 수행할 때마다 정확도가 기하급수적으로 증가함.

단점은 함수 $f(x)$가 미분 가능해야 함. 미분이 존재하지 않거나 계산이 어렵다면 사용하기 어려움.

만약 함수의 미분값 $f'(p_n) = 0$이라면 과정을 지속할 수 없음. 그래서 $f'(x)$가 해 $r$ 근처에서 0이 되지 않아야 함.

잘못된 초기값을 선택하면 발산할 수 있음.

다중근(multiple roots) 문제가 있음.

# Local Convergence

<div class="theorem" markdown="1">

Let $f \in C^2[a, b]$. If $r \in (a, b)$ is s.t. $f(r) = 0$ and $f'(r) \ne 0$,

then there exists a $\delta > 0$ s.t.
Newton's method generates a sequence $\left\\{ p_n \right\\}_{n=1}^{\infty}$
that converges to $r$ for any initial approximation $p_0 \in [r-\delta, r+\delta]$.

</div>

## proof

뉴턴 방법을 FPI으로 해석하여 증명 합니다!!

<div class="proof" markdown="1">

[Step 1] 함수 $g(x)$ 정의

Newton's Method의 반복식은 아래와 같습니다.

$$
p_{n+1} = p_n - \frac{f(p_n)}{f'(p_n)}
$$

이를 함수 $g(x)$로 표현하면,

$$
g(x) = x - \frac{f(x)}{f'(x)}
$$

즉, 뉴턴 방법은 FPI에서 $p_{n+1} = g(p_n)$의 형태를 가집니다.

<hr/>

[Step 2] 함수 $g'(x)$ 미분

$$
\begin{aligned}
g'(x)
&= 1 - \frac{f'(x) \cdot f'(x) - f(x) f''(x)}{[f'(x)]^2} \\
&= 1 - 1 + \frac{f(x) f''(x)}{[f'(x)]^2}
&= \frac{f(x) f''(x)}{[f'(x)]^2}
\end{aligned}
$$

즉, $g'(x)$는 함수 $f(x)$와 그 도함수 $f'(x)$, 이계도함수 $f''(x)$에 의해 결정 됩니다.

<hr/>

[Step 3] 함수 $g'(x)$가 Lipshitz 조건을 만족하는지 확인

우리가 $g(x)$에 대해 알고 있는 사실은 아래와 같습니다.

- Iterative 방법으로 찾으려는 근 $p$에서 $g(p) = 0$을 만족합니다.
- 위의 사실에 의해 근 $p$에서 $g'(p) = 0$도 만족을 합니다.

이때, $g'(x)$가 연속 함수이므로 $p$를 중심으로 하는 어떤 작은 구간 $[p-\delta, p+\delta]$에서 $\| g'(x) \| < k$를 갖도록 $\delta$를 항상 잡을 수 있습니다.

<hr/>

[Step 4] 함수 $g(x)$가 자기 사상을 만족하는지 확인

$x_0 \in [p-\delta, p+\delta]$에 대해 평균값 정리에 의해 아래의 등식이 성립 합니다.

$$
\frac{\| g(x) - g(p) \|}{\| x - p \|} = \| g'(x_0) \|
$$

분수 식을 정리하면

$$
\| g(x) - g(p) \| = \| g'(x_0) \| \cdot \| x - p \|
$$

가 되는데, [Step 3]에서 함수 $g'(x)$가 Lipschitz 성질 $\|g'(x)\| \le k$를 만족한다는 걸 알고 있으니 아래와 같이 부등식을 세울 수 있습니다.

$$
\| g(x) - g(p) \| \le k \cdot \| x - p \|
$$

이때, $g(p) = p$이므로

$$
\| g(x) - p \| \le k \cdot \| x - p \| < \| x - p\| < \delta
$$

이므로 $\| g(x) - p \| < \delta$가 성립 합니다. 이것은 함수 매핑 전후로 $\| x - p\| < \delta$ 성질이 유지된다는 것을 말합니다. 따라서, $g(x)$는 국소 범위 내에서 자기 사상을 만족 합니다.

<br/>

Fixed-point Theorem의 조건을 모두 만족하므로, newton's method로 생성한 sequence $\left\\{ p_n \right\\}_{n=1}^{\infty}$는 국소적으로 수렴하는 성질을 가집니다.

</div>


# Quadratically Convergent

<div class="definition" markdown="1">

Let $e_n$ denote the error at step $n$ of an iterative method, $e_n = \|p_n - r\|$.

The iteration is quadratically convergent if

$$
\lim_{n\rightarrow\infty} \frac{e_{n+1}}{e_n^2} = M < \infty
$$

</div>

참고로 Bisection Method이 경우 "Linearly Convergent" 였고, 이때는 에러가 아래의 식을 만족 했다.

$$
\lim_{n\rightarrow\infty} \frac{e_{n+1}}{e_n^2} = C, \quad 0 C < 1
$$

## vs. Linear Convergent

두 수렴은 수렴식에 대한 것 뿐만 아니라 수렴 패턴이 확실히 다르게 나타납니다.

선형 수렴은 현재 오차가 이전 오차에서 일정 비율로 감소해야 합니다. 만약 이 비율 $C$가 $C > 1$ 였다면, 오차가 발산하고 날 것 입니다. 예를 들어 초기 오차가 $e_0 = 0.1$이고 선형 수렴률이 $0.5$라면,

- $e_1 = 0.5 \cdot 0.1 = 0.05$
- $e_2 = 0.5 \cdot 0.05 = 0.025$
- $e_1 = 0.5 \cdot 0.025 = 0.0125$

이런 식으로 오차가 일정 비율로 꾸준히 감소합니다.

<br/>

그러나 이차 수렴은 초기에는 오차가 천천히 줄지만 오차가 한번 작아지기 시작하면, 급격히 감소 합니다. 초기 오차가 $e_0 = 0.1$이고 $M = 1$이라고 가정하면,

- $e_1 = (0.1)^2 = 0.01$
- $e_2 = (0.01)^2 = 0.0001$
- $e_1 = (0.00001)^2 = 0.0000 0001$

그래서 오차가 0에 수렴하는 속도가 확실히 더 빠릅니다.

그러나 이런 패턴은 초기 오차 $e_0$가 충분히 작은 상황에서만 성립 합니다. 만약 초기 오차 $e_0 > 1$ 였다면, 오히려 오차가 폭발적으로 증가하게 됩니다.

이것 역시 이차 수렴의 성질로, 초기 오차가 너무 크면 이차 수렴이 보장 되지 않습니다. 뉴턴 수렴이 국소 수렴을 하는 이유도 이차 수렴 성질을 갖기 떄문이라고 볼 수 있을 것 같습니다. (닭이냐 달걀이냐)

# Secant Method

뉴턴 방법은 계산을 위해서 함수 $f(x)$은 도함수 $f'(x)$를 반드시 구해야 합니다. 일반적인 상황에서는 괜찮지만, 미분 계산이 어려운 경우에는 사용하기 힘듭니다.

그래서 등장한 것이 "Secant Method(할선법)" 입니다. 이 방법은 $f'(x)$를 아래와 같이 근사 하여 뉴턴 방법을 수행 합니다.

$$
f'(x) \approx \frac{f(x_n) - f(x_{n-1})}{x_n - x_{n-1}}
$$

그래서 공식을 다시 작성하면 아래와 같습니다.

$$
x_{n+1} = x_n - \frac{f(x_n)}{f'(x_n)} \approx x_n - f(x_n) \cdot \frac{x_n - x_{n-1}}{f(x_n) - f(x_{n-1})}
$$

자세한 내용은 "[Secant Method](/2025/03/18/secant-method/)" 포스트에 작성하였습니다!
