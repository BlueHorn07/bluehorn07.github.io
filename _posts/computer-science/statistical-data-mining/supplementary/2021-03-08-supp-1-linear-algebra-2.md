---
title: "Supp-1: Linear Algebra - 2"
layout: post
use_math: true
tags: [linear_algebra, applied_statistics]
---


2021-1학기, 대학에서 '통계적 데이터마이닝' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<br><span class="statement-title">TOC.</span><br>

- Eigen value & Eigen vector
- Trace
- Vector Calculus
- Matrix Calculus
  - Quadratic Form

<hr/>

### Eigen value & Eigen vector

<br><span class="statement-title">Definition.</span> eigen value & eigen vector<br>

For $A \in \mathbb{R}^{n \times n}$, $\lambda \in \mathbb{C}$ is called an \<**eigen value**\> of $A$ if

$$
A \mathbf{x} = \lambda \mathbf{x} \quad \text{for some} \; \mathbf{x} \in \mathbb{R} \setminus \{\mathbf{0}\}
$$

Here, $\mathbf{x}$ is called an \<**eigen vector**\> of $A$ associated with $\lambda$.

<br><span class="statement-title">Note.</span><br>

$\det (A - \lambda I_n) = 0$ if and only if $\lambda$ is an \<eigen value\>.

<div class="proof" markdown="1">

<span class="statement-title">*Proof*.</span><br>

$A\mathbf{x} = \lambda\mathbf{x}$에서 정리하면 $(A - \lambda I_n) \mathbf{x} = 0$을 얻는다. 이때, $\mathbf{x}$의 조건에 의해 $\mathbf{x} \ne \mathbf{0}$이다. 즉, $\mathbf{x}$는 non-trivial solution을 갖는다는 말이다.

어떤 행렬 $A$에 대해 $A$가 invertible이라면, $A\mathbf{x} = 0$에서 $\mathbf{x}$는 trivial solution $\mathbf{0}$만을 얻는다. 따라서, $(A - \lambda I_n) \mathbf{x} = 0$에서 $\mathbf{x}$이 non-trivial solution을 가지려면, $(A - \lambda I_n)$이 non-invertible, 즉 $\det(A - \lambda I_n) = 0$이어야 한다!

</div>

<br/>

$\det(A-\lambda I_n)$는 $\lambda$에 대한 $n$-th order polynomial이다. 따라서, $A$는 정확히 $n$개의 complex eigenvalues (up to multiplicity)를 갖는다. (*Fundamental Theorem of Algebra*) 이런 $\det (A - \lambda I_n)$을 \<**특성 다항식 Characteristic Polynomial**\>이라고 부른다!

<br/>

행렬 $A$를 선형변환의 관점에서 살펴본다면, \<eigen vector\>는 <span class="half_HL">선형변환 $A$를 적용했을 때의 결과가 자신의 상수배가 되는 0이 아니 벡터를 말한다.</span>

이런 성질은 \<eigen vector\>가 선형변환 $A$ 아래에서 방향이 보존되고, 크기(scale)만 변하는 벡터임을 의미한다.

<div class="img-wrapper">
  <img src="https://t1.daumcdn.net/cfile/tistory/277D6547525CFB120B" width="250px">
  <p markdown="1">Image from ['다크 프로그래머'](https://darkpgmr.tistory.com/105)</p>
</div>

예를 들어, 지구의 자전운동이라는 3차원 회전변환을 생각할 때, 이 회전 변환에 의해 변하지 않는 \<eigen vector\>는 회적축이 되며, 그대의 \<eigen value\>는 $1$이 된다!

<br/>

\<eigen vector\>와 \<eigen value\>는 뒤에서 살펴볼 \<Eigen Decomposition\> 다른 말로 \<Spectral Decomposition\>에서 주요하게 사용된다. 이때, 모든 정방행렬에 대해 \<Eigen Decomposition\>이 가능한 것은 아니다. 이것이 가능하려면, $n\times n$ 행렬 $A$가 $n$개의 linearly indenpendent eigen vector를 가질 때만 가능하다! (조건이 꽤 까다롭다...!)

👉 [Spectral Decomposition]({{"/2021/03/14/supp-1-linear-algebra-3.html#spectral-decomposition" | relative_url}})

<hr/>

<br><span class="statement-title">Definition.</span> Trace<br>

The sum of all diagonal elements of $A \in \mathbb{R}^{n\times n}$ is called the \<trace\> of $A$, denoted $\text{tr}(A)$.

<br><span class="statement-title">Properties.</span><br>

- $\text{tr}(A) = \text{tr}(A^T)$
- $\text{tr}(A+B) = \text{tr}(A) + \text{tr}(B)$
- $\text{tr}(AB) = \text{tr}(BA)$, for $A \in \mathbb{R}^{n\times p}$, $B \in \mathbb{R}^{p\times n}$ 🎈
- $\text{tr}(AA^T) = \text{tr}(A^TA)$
- $\displaystyle \text{tr}(A) = \sum^n_{i=1} \lambda_i$, where $\lambda_i$'s are eigenvalues of $A$. 🎈

🎈 이모지가 붙은 성질은 특히 중요한 성질입니다! 유도 과정을 꼭 알아야 합니다!

<hr/>

## Matrix Calculus

일차원 변수에 대해서 하던 \<Calculus\>를 더 높은 차원에서 하는 것이 \<**Vector Calculus**\> 또는 \<**Matrix Calculus**\>이다.

<br><span class="statement-title">Definition.</span> Vector Calculus<br>

Let $\mathbf{a} \in \mathbb{R}^{p \times 1}$ represents a mapping $f: \mathbb{R}^p \rightarrow \mathbb{R}$. Then, $\mathbf{a}^T \mathbf{x} = a_1 x_1 + a_2 x_2 \cdots + a_p x_p$

$$
\frac{\partial}{\partial \mathbf{x}} \left( \mathbf{a}^T \mathbf{x} \right) \overset{\text{def}}{=} \left( \frac{\partial}{\partial x_i} \left( \mathbf{a}^T \mathbf{x}\right) \right)_{1 \le i \le p} = \mathbf{a}
$$

위의 상황은 scalar인 $\mathbf{a}^T \mathbf{x}$를 vector $\mathbf{x}$로 미분하는 상황이다!

<br><span class="statement-title">Definition.</span> Matrix Calculus<br>

Let $A \in \mathbb{R}^{n\times p}$ represents a mapping $f: \mathbb{R}^p \rightarrow \mathbb{R}^n$. Then 

$$
A \mathbf{x} = 
\begin{pmatrix}
a_{11} x_1 + a_{12} x_2 + \cdots a_{1p} x_p \\
a_{21} x_1 + a_{22} x_2 + \cdots a_{2p} x_p \\
\vdots \\
a_{n1} x_1 + a_{n2} x_2 + \cdots a_{np} x_p \\
\end{pmatrix}
= \begin{pmatrix}
\mathbf{a}_1^T \mathbf{x} \\
\mathbf{a}_2^T \mathbf{x} \\
\vdots \\
\mathbf{a}_n^T \mathbf{x} \\
\end{pmatrix}
$$

1\. vector-by-vector

$$
\frac{\partial}{\partial \mathbf{x}} \left( A \mathbf{x} \right) \overset{\text{def}}{=} \left( \frac{\partial}{\partial \mathbf{x}} \left( \mathbf{a}_i^T \mathbf{x} \right) \right)_{1 \le i \le n} = \left( \mathbf{a}_i^T \right)_{1 \le i \le n} = A
$$

2\. \<**Quadratic Form**\> Calculus

Let $A \in \mathbb{R}^{n \times n}$, then A \<Quadratic Form\> $\mathbf{x}^T A \mathbf{x}$ is 

$$
\mathbf{x}^T A \mathbf{x} = \sum^n_{i=1} x_i \left( A \mathbf{x}\right)_i = \sum^n_{i=1} x_i \left( \sum^n_{j=1} a_{ij} x_j \right) = \sum^n_{i=1} \sum^n_{j=1} a_{ij} x_i x_j
$$

Take derivative on $\mathbf{x}^T A \mathbf{x}$

$$
\begin{aligned}
\frac{\partial}{\partial \mathbf{x}} \left( \mathbf{x}^T A \mathbf{x} \right) &\overset{\text{def}}{=} \left( \frac{\partial}{\partial x_i} \left( \mathbf{x}^T A \mathbf{x} \right) \right)_{1 \le i \le n} \\
&= \left( \frac{\partial}{\partial x_k} \left( \sum^n_{i=1} \sum^n_{j=1} a_{ij} x_i x_j \right) \right)_{1 \le k \le n}
\end{aligned}
$$

이때, 손으로 스케치해서 확인해보면, $\displaystyle\frac{\partial}{\partial x_k} \left(\mathbf{x}^T A \mathbf{x}\right)$는 $\mathbf{x}^T A \mathbf{x}$를 나열 했을 때 $k$번째 행, $k$번째 열 부분을 미분하는 행위다. 그래서 이것을 잘 정리하면, 아래의 결과를 얻는다.

$$
\frac{\partial}{\partial x_k} \left(\mathbf{x}^T A \mathbf{x}\right) = \sum^n_{i=1} a_{ik}
 x_i + \sum^n_{j=1} a_{kj}x_j = \left(A^T\mathbf{x}\right)_k + \left(A\mathbf{x}\right)_k
$$

따라서, $\displaystyle\frac{\partial}{\partial \mathbf{x}} \left( \mathbf{x}^T A \mathbf{x} \right)$은

$$
\frac{\partial}{\partial \mathbf{x}} \left( \mathbf{x}^T A \mathbf{x} \right) = \left( \left(A^T\mathbf{x}\right)_k + \left(A\mathbf{x}\right)_k \right)_{1\le k \le n} = A^T \mathbf{x} + A \mathbf{x} = \left( A^T + A \right) \mathbf{x}
$$

여기서 만약 $A$가 symmetric matrix라면 아래와 같다.

$$
\frac{\partial}{\partial \mathbf{x}} \left( \mathbf{x}^T A \mathbf{x} \right) = \left( A^T + A \right) \mathbf{x} = 2A \mathbf{x}
$$

2\. \<**Quadratic Form**\> Calculus - (2)

이번엔 $\mathbf{x}$로 한번더 미분을 해보자!

$$
\frac{\partial^2}{\partial \mathbf{x} \partial \mathbf{x^T}} \left( \mathbf{x}^T A \mathbf{x} \right) \overset{\text{def}}{=} \left( \frac{\partial^2}{\partial x_j \partial x_i} \left( \mathbf{x}^T A \mathbf{x} \right) \right)_{1 \le i, \, j \, \le n} = \frac{\partial}{\partial \mathbf{x}}  \left( \left( A^T + A \right) \mathbf{x} \right) = (A^T + A)
$$

<hr/>

이어지는 내용에선 \<Spectral Decomposition\>와 \<Singular Value Decomposition\> 등 선형 변환의 좀더 딥한 내용을 다룬다! 🤯

👉 [Supp-1: Linear Algebra - 3]({{"2021/03/14/supp-1-linear-algebra-3.html" | relative_url}})