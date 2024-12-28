---
title: "Generalized Eigen Values"
toc: true
author: bluehorn_math
toc_sticky: true
categories: ["Linear Algebra"]
excerpt: "중근인 고유값이 있을 때, 그것의 고유벡터를 구하는 방법에 대해서"
---

24년도 2학기에 수강하고 있는 [상미분방정식](/categories/ordinary-differential-equations)에서 다루는 내용 중 일부를 정리하고 있습니다. 선형대수와 관련된 내용이 있어 한번 정리해보고자 합니다.
{: .notice--info}

# 들어가며

먼저 고유 벡터(Eigen Vector)에 대해 살펴봅시다. 고유벡터는 행렬 $A$를 $v$에 작용 했을 때, 벡터의 방향은 변하지 않고 크기만 조정되는 벡터를 말합니다.

$$
Av = \lambda v
$$

그리고 행렬 $A$에 대해 정의되는 이런 벡터를 모아서 정의한 것이 고유공간(Eigen Space)입니다.

$$
E(A) = \left\{ v: Av = \lambda v \right\}
$$

<br/>

나이스한 경우에는 행렬 $A$의 차원이 $n$이라면, $n$개의 고유벡터를 얻어서 고유공간의 차원도 $n$이 될 것입니다. 그러나, 현실은 나이스한 경우만 일어나지 않죠;; 어떤 경우엔 $(A - \lambda I) v = 0$라는 식을 풀어서 얻는 고유값 $\lambda$에서 중근이 발생할 수 있습니다.

대표적으로 꼽을 수 있는게, 아래의 행렬 입니다.

$$
\left(
\begin{matrix}
1 & 1 \\
0 & 1
\end{matrix}
\right)
$$

이런 경우, 특성방정식을 풀어보면, $\lambda = 1$인 중근이 나옵니다. 그리고, 고유 벡터로는 $v = (1, 0)^T$를 얻습니다. 이런 경우를 다루는게 오늘의 포스트입니다.

# Generalized Eigen Vector

일반화된 고유 벡터는 중근이 있는 경우에 필요한 벡터입니다. 먼저, 이걸 구하는 공식부터 얘기하면 아래와 같은 행렬식을 만족하는 $w$ 벡터를 구하면 됩니다.

$$
(A - \lambda I) w = v
$$

실제로 아까 위에서 봤던 행렬 $A$에 대해서 $w$ 벡터를 구해보면... $w = (0, 1)^T$이 나옵니다. 이것은 행렬 $A$의 고유벡터 였던 $v = (1, 0)^T$와 일차독립인 벡터입니다.

그러면 "이게 행렬 $A$의 고유벡터냐??" 라고 하면, 그건 아닙니다!!!

왜냐하면 $Aw \ne \lambda w$가 아니기 때문이죠. 만약, 이걸 만족했다면 $w$는 고유벡터이고 우리가 특성방정식을 풀어 고유값을 계산할 때 찾았어야 합니다. 그래서 벡터 $w$는 고유벡터라는 이름이 아니라 "**일반화된(generalized) 고유벡터**"라고 부릅니다.

<br/>

앞에서 $Aw \ne \lambda w$라고 했습니다. 그렇다면, 벡터 $w$에 행렬 $A$를 적용한 $Aw$는 무엇이 되어야 할까요?? 그 답은 우리가 일반화된 고유벡터를 구할 때, 풀었던 일반화된(?) 특성 방정식에 있습니다.

$$
Aw = \lambda w + v
$$

특성 방정식을 풀면 요렇게 되어야 합니다. 즉, 일반화된 고유벡터에 대한 스케일 변환과, 고유벡터 $v$에 대한 방향 성분이 포함됩니다.

<br/>

처음 일반화된 고유벡터를 공부할 때, "어떻게 이렇게 되어야 하는가...!?"라는 의문이 들었습니다. 이에 대한 증명을 교수님께서 정리해두셨는데, 이를 제가 이해한 방식대로 옮겨보겠습니다.

<div class="proof" markdown="1">

행렬 $A$가 오직 하나의 고유쌍 $(\lambda, v)$를 가진다고 하자. 그리고 벡터 $u$를 고유벡터 $v$와 독립인 어떤 벡터라고 하자. 그러면, 아래의 식이 성립한다.

$$
Au = \mu v + \nu u
$$

이때, $\mu, \nu \in \mathbb{R}$이고 $\mu \ne 0$이다. 이렇게 분해되는 이유는 행렬 $Aw$를 서로 독립인 두 기저 벡터의 선형 결합으로 표현하면 위와 같기 때문이다.

이때, 위의 선형 결합에는 재밌는 성질이 있는데, $\nu$에 대해 항상 $\nu = \lambda$가 성립한다. 여기에서 증명 안의 증명을 잡깐 해야 하는데...

<div class="proof" markdown="1" style="background-color: lightgray;">

만약 $\nu \ne \lambda$라고 하자. 그리고, 양변에 $\frac{\mu}{\nu - \lambda} Av$ 텀을 더해준다. 그러면...

$$
\begin{aligned}
Au + \frac{\mu}{\nu - \lambda} Av
&= \mu v + \nu u + \frac{\mu}{\nu - \lambda} Av \\
&= \mu v + \nu u + \frac{\lambda \mu}{\nu - \lambda} v \\
&= \nu u + \frac{\mu (\nu - \cancel{\lambda})+ \cancel{\lambda \mu} }{\nu - \lambda} v \\
A (u + \frac{\mu}{\nu - \lambda} v) &= \nu (u + \frac{\mu}{\nu - \lambda} v)
\end{aligned}
$$

위의 등식이 의미하는 바는 $\nu$가 고유값라는 것이고, $(u + \frac{\mu}{\nu - \lambda} v)$ 벡터는 그것의 고유벡터라는 것이다.

그러나, 처음에 행렬 $A$가 오직 하나의 고유쌍을 가진다고 했으므로 이는 모순이다. 따라서, $\nu = \lambda$이다. $\blacksquare$

</div>

행복하게도, $\nu = \lambda$, 즉 고유값과 같다는 정보를 얻었다 (야호!) 그러면 식을 다시 써보자.

$$
Au = \mu v + \lambda u
$$

이때, 정규화된 벡터 $w = \frac{1}{\mu} u$를 정의하고, 식을 다시 써보면...

$$
\begin{aligned}
Au &= \mu v + \lambda u \\
A (\cancel{\mu} w) &= \cancel{\mu} v + \lambda (\cancel{\mu} w) \\
A w &= v + \lambda w
\end{aligned}
$$

우리가 처음에 특성방정식으로부터 얻었던 틍식을 얻을 수 있다 ㅎㅎ

</div>

위와 같은 현상을 잘 포장하면 아래와 같이 말할 수 있다.

> 어떤 벡터 $v$에 행렬 $A$를 작용하면, 그 결과는 고유 벡터의 선형 조합으로 표현할 수 있다. 그리고 그 고유 벡터는 고유 공간의 기저를 이룬다.

그런데, 명심할 것은 벡터 $w$는 고유벡터가 아니었다!! 그래서 위의 표현을 아래와 가팅 다듬겠다.

>  어떤 벡터 $v$에 행렬 $A$를 작용하면, 그 결과는 고유 벡터와 **일반화된 고유 벡터**의 선형 조합으로 표현할 수 있다. 그리고 고유 벡터와 일반화된 고유 벡터는 **일반화된 고유 공간**의 기저를 이룬다.

그런데, "일반화된 고유 공간"은 뭘까?

## Generalized Eigen Space

행렬 $A$에 대한 고유 벡터와 일반화된 고유 벡터로 기저를 이룬 공간이다. 수학적 정의를 적으면 아래와 같다.


$$
G(A) = \left\{ v: (A - \lambda I)^k v = 0 \quad \text{for some} \quad k \ge 1 \right\}
$$

위의 정의는 사실 $(A - \lambda I)w = v$라는 정의에서 출발한다. 일반화된 고유벡터를 구하는 등식의 양변에 $(A - \lambda I)$를 한번더 곱해주면...

$$
\begin{aligned}
(A - \lambda I)w &= v \\
(A - \lambda I)(A - \lambda I)w &= (A - \lambda I)v = 0 \\
(A - \lambda I)^2 w &= 0
\end{aligned}
$$

이 되기 때문에, 일반화된 고유 공간에 대한 정의 동치이다.

# Canonical Form

행렬 $A$의 고유값에 집중하기 위해서, 행렬을 표준형(Canonical Form)으로 변환하는 작업을 많이 한다. 행렬을 표준화 한다고 하면, 그걸 대각 행렬과 "비슷한" 형태로 만드는 것을 말한다.

$$
\left(
\begin{matrix}
\lambda & 0  \\
0 & \lambda
\end{matrix}
\right),
\qquad
\left(
\begin{matrix}
\lambda & 1  \\
0 & \lambda
\end{matrix}
\right)
$$

요련 형태로 만든다고 생각하면 된다. 이렇게 만드는 방법을 별로 어렵지 않은데, 변환 $T$를 아래와 같이 정의해서...

$$
T = (v_1, v_1, ..., v_n) \quad \text{where} \quad v_i \in G(A)
$$

기존 행렬 $A$의 좌우에 변환 $T$과 역변환 $T^{-1}$을 적용해 $T^{-1} A T$를 구하면, 표준화된 형태를 얻을 수 있다.

이렇게 되는 이유는 $T^{-1}AT$를 전개해보면 되는데...

<div class="proof" markdown="1">

\* 역변환 $T^{-1} = T^t$임을 이용해야 한다. (문자가 겹쳐서 소문자 $t$로 transpose를 나타냄)

$$
\begin{aligned}
(v_1, v_2)^T A (v_1, v_2)
&= (v_1 A, v_2 A)^T (v_1, v_2) \\
&=
\left(
\begin{matrix}
v_1^T A v_1 & v_1^T A v_2 \\
v_2^T A v_1 & v_2^T A v_2 \\
\end{matrix}
\right) \\
&=
\left(
\begin{matrix}
v_1^T \lambda_1 v_1 & v_1^T \lambda_2 v_2 \\
v_2^T \lambda_1 v_1 & v_2^T \lambda_2 v_2 \\
\end{matrix}
\right) \\
&=
\left(
\begin{matrix}
\lambda_1 & 0 \\
0 & \lambda_2 \\
\end{matrix}
\right)
\end{aligned}
$$

(참... 쉽죠..!? ㅋㅋ)

위의 경우는 고유값이 서로 다른 실근을 갖는 경우를 다뤘는데 만약 중근이 있어 $v_2$가 일반화된 고유 벡터라면,

$$
v_1^T A v_2 = v_1^T (v_1 + \lambda v_2) = 1 + 0 = 1
$$

이 되므로, Jordan Form 형태로 나오게 된다.

$$
\left(
\begin{matrix}
\lambda & 1  \\
0 & \lambda
\end{matrix}
\right)
$$

$\blacksquare$

</div>

## Complex Eigen Value Case

여기서 조금더 나아간다면, 사실 표준형에는 하나가 더 있다!! 바로 복소수의 실근!! 그래서 케이스를 제대로 다 적으면 아래와 같다.

$$
\left(
\begin{matrix}
\lambda & 0  \\
0 & \lambda
\end{matrix}
\right),
\qquad
\left(
\begin{matrix}
\lambda & 1  \\
0 & \lambda
\end{matrix}
\right),
\qquad
\left(
\begin{matrix}
\alpha & \beta  \\
-\beta & \alpha
\end{matrix}
\right)
$$

여기에서 맨 마지막 녀석은 대각 행렬도 아니고 대각-like 행렬도 아니다 🤔

마지막 녀석은 복소수의 고유값을 실수 기저로 표현할 때 저렇게 된다. 실수 기저로 표현하는 자세한 방법은 여기서 스킵할 것이고!! ㅎㅎ 만약 복소수 기저 그대로 고유벡터를 잡고 표준화를 진행했다면, 표준 형태도 아래와 같이 대각 행렬의 형태로 나온다.

$$
\left(
\begin{matrix}
\lambda & 0  \\
0 & \bar{\lambda}
\end{matrix}
\right)
$$

복소수의 켤레 특성 때문에, 고유값이 저렇게 나온다 ㅎㅎ

# 맺음말

본래 상미분방정식 수업에서 나온 내용을 이해하기 위해 잠깐 선형 대수로 넘어온 파트이다 ㅎㅎ

이걸 기반으로 풀어낸 1st Order Linear ODE System에 대한 문제를 풀고 정리한 것이 아래의 포스트이다.

👉 [Jordan Block Case on Systems of ODEs](/2024/10/16/jordan-block-case/)
