---
title: "Linear Regression, GD, and Normal Equation"
toc: true
toc_sticky: true
categories: ["Machine Learning"]
---

본 글은 2018-2학기 Stanford Univ.의 Andrew Ng 교수님의 Machine Learning(CS229) 수업의 내용을 정리한 것입니다. 지적은 언제나 환영입니다 :)

-- [lecture video](https://youtu.be/4b4MUYve_U8)

<hr>

# Part I: Linear Regression
Q. 무엇이 **Regression**인가?<br>
A. 예측<small>*predict*</small>하고자 하는 target variable이 *continuous* 하다면, 그 prediction을 **Regression**이라고 한다. (만약 target variable이 *discrete* 하다면, 그 prediction을 **classification**이라고 한다.)

"딥러닝은 prediction goal을 명확하게 제시해야 prediction을 할 수 있다."

"regression은 prediction 하는 여러 방법 중 하나일 뿐이다."

single-var. regression을 하는 경우보다는 **multi-var. regression**을 주로 함.

Q. regression은 어떻게 prediction하는가?<br>
A. 주어진 parameter $\theta$에 대한 hypothesis funciton $h(x; \theta)$의 값이 prediction 값이다.

Q. parameter $\theta$를 어떻게 최적화할까?<br>
A. cost function $J(\theta)$를 정의하고 cost 값을 minimize 하는 방향으로 learning 한다.


## 1. LMS algorithm
<div>
$$J(\theta) = \cfrac { 1 }{ 2 } \sum_{ i=1 }^{ m }{ { \left( { h }_{ \theta }\left( { x }^{ \left( i \right) } \right) - { y }^{ \left( i \right) } \right) }^{ 2 } }$$
</div>

LMS<small>*Least Mean Square*</small>는 cost $J(\theta)$를 minimize하는 적절한 $\theta$를 찾는 방법.

LMS algorithm은 GD<small>*Gradient Descent*</small>를 통해 매 iteration마다 $\theta$를 업데이트한다.

LMS는 cost $J(\theta)$ 때문에 붙은 이름[^1], GD는 LMS에서 iteration algorithm을 담당할 뿐임.

<div>
$${\theta}_j := {\theta}_j - \alpha \cfrac {\partial}{\partial {\theta}_j}J(\theta)$$
</div>

[^2]

$\alpha$는 **learning rate**이다.

$\cfrac {\partial}{\partial {\theta}_j}J(\theta)$는 그냥 편미분 해서 구하면 됨.


### batch GD vs. stochastic GD

batch GD는 $\theta$를 한번 업데이트 하기 위해 training set 전체에 대한 cost 값을 계산한다.

stochastic GD(=SGD)는 training set의 원소 하나를 보고, $\theta$를 한번 업데이트 한다.<br>
→ SGD가 batch GD보다 빠름!

|we update the parameters according to the gradient of the error with respect to that single training example only. |

|Note however that it may never "converge" to the minimum, and the parameters $\theta$ will keep oscillating around the minimum of $J(\theta)$; but in practice most of the values near the minimum will be reasonably good approximations to the true minimum.|

<hr>

## 2. Normal Equation

:warning: only work for 'Linear' regression :warning:

minimize $J(\theta)$ by explicitly taking its derivatives with respect to the ${\theta}_j$'s

### Matrix Derivatives
matrix function $f: \mathbb{R}^{m \times n} \rightarrow \mathbb{R}$

cost function $J(\theta)$가 대표적인 matrix function임.

<div>
$$\nabla_{\theta} J(\theta) = { \left[ \cfrac{\partial J}{\partial \theta_0} \cfrac{\partial J}{\partial \theta_1} ... \cfrac{\partial J}{\partial \theta_n}\right] }^{T}$$
</div>

이번엔 또다른 matrix function인 $\textrm{tr} A$를 살펴보자.

<div>
$$\textrm{tr} A = \sum_{i=1}^{n}{A_{ii}}$$
</div>

cost function $J(\theta)$가 $\mathbb{R}^{n \times 1}$인 열벡터를 입력으로 받는다면, $\textrm{tr} A$는 $\mathbb{R}^{n \times n}$의 정사각 행렬을 입력으로 받는다. $\textrm{tr} A$의 derivative의 형태는 다음과 같다.

<div>
$$\nabla _{ A }f\left( A \right) = \begin{pmatrix} \cfrac { \partial f }{ \partial A_{ 11 } }  & \cdots  & \cfrac { \partial f }{ \partial A_{ 1n } }  \\ \vdots  & \ddots  & \vdots  \\ \cfrac { \partial f }{ \partial A_{ n1 } }  & \cdots  & \cfrac { \partial f }{ \partial A_{ nn } }  \end{pmatrix}$$
</div>

$\textrm{tr} A$에 대한 derivative 공식들을 살펴보자.

(1) $\nabla _{ A } f\left( A \right) = B^T$<br>
(2) $\nabla _{ {A}^{T} } f\left( A \right) = {\left( \nabla _{ A }f\left( A \right) \right)}^{T}$<br>
(3) $\nabla _{ A } \textrm{tr} \left( AB{A^T}C \right) = CAB + {C^T}A{B^T}$

### Least Squares Method

우리의 목표는 global minimum을 찾는 것이다. 따라서

<div>
$$\nabla_{\theta} f(\theta) = 0$$
</div>

이 되는 $\theta$를 찾아야 한다.

global minimum을 찾기 위해 traning set을 모두 모은 행렬 $X$를 다음과 같이 정의하자.

<div>
$$X = \begin{bmatrix} { \left(  { x }^{ (1) } \right)  }^{ T } \\ { \left( { x }^{ (2) } \right)  }^{ T } \\ \vdots  \\ { \left( { x }^{ (m) } \right)  }^{ T } \end{bmatrix}$$
</div>

행렬 $X$의 행 하나하나가 하나의 training vector를 의미한다.

<div>
$$\vec{y} = \begin{bmatrix}  y^{(1)} \\ y^{(1)} \\ \vdots \\ y^{(1)} \end{bmatrix}$$
</div>

열벡터 $\vec{y}$는 전체 training set에 대한 target values의 모음이다.

이제 cost function $J(\theta)$를 행렬 폼에 맞춰 다시 쓰면 다음과 같다.

<div>
$$J(\theta) = \cfrac{1}{2} {\left( X\theta - \vec{y} \right)}^{T} \left( X\theta - \vec{y} \right)$$
</div>

이것을 편미분 하면,

<div>
$$
\begin{split}
\nabla_{\theta} J(\theta) &= \nabla_{\theta} \cfrac{1}{2} {\left( X\theta - \vec{y} \right)}^{T} \left( X\theta - \vec{y} \right) \\
&= \cfrac{1}{2} \nabla_{\theta} \left( {\theta}^{T} X^T - \vec{y}^T \right) \left( X\theta - \vec{y} \right)
\end{split}
$$
</div>

이고, 분배법칙에 따라 분배하면,

<div>
$$
\begin{split}
&= \cfrac{1}{2} \nabla_{\theta} \left( {\theta}^{T} {X^T} X \theta - {\theta}^{T} {X^T} \vec{y} - {\vec{y}^T} X \theta + {\vec{y}^T} \vec{y} \right)
\end{split}
$$
</div>

cost function $J(\theta)$의 값이 scalar이기 때문에, $\textrm{tr}$을 취해도 그대로 scalar이다.

<div>
$$
\begin{split}
&= \cfrac{1}{2} \nabla_{\theta} \textrm{tr} \left( {\theta}^{T} {X^T} X \theta - {\theta}^{T} {X^T} \vec{y} - {\vec{y}^T} X \theta + {\vec{y}^T} \vec{y} \right)
\end{split}
$$
</div>

$\textrm{tr}$ 함수의 성질에 따라 $\textrm{tr}$을 괄호 안으로 넣으면,

<div>
$$
\begin{split}
&= \cfrac{1}{2} \nabla_{\theta} \left( \textrm{tr} \left( {\theta}^{T} {X^T} X \theta \right) - \textrm{tr} \left( {\theta}^{T} {X^T} \vec{y} \right) - \textrm{tr} \left( {\vec{y}^T} X \theta \right) + \textrm{tr} \left( {\vec{y}^T} \vec{y} \right) \right)
\end{split}
$$
</div>

이때, $\textrm{tr} \left( {\theta}^{T} {X^T} \vec{y} \right) = \textrm{tr} \left( {\vec{y}^T} X \theta \right)$ 이고, ${\vec{y}^T} \vec{y}$에는 $\theta$-free하기 때문에 다음과 같이 정리할 수 있다.

<div>
$$
\begin{split}
&= \cfrac{1}{2} \nabla_{\theta} \left( \textrm{tr} \left( {\theta}^{T} {X^T} X \theta \right) - 2 \textrm{tr} \left( {\vec{y}^T} X \theta \right) \right)
\end{split}
$$
</div>

이것을 미분하면,

<div>
$$
\begin{split}
&= \cfrac{1}{2} \left( {X^T} X \theta + {X^T} X \theta  - 2 {X^T} \vec{y}  \right) \\
& = {X^T} X \theta - {X^T} \vec{y}
\end{split}
$$
</div>

이 과정에서 (3) $\nabla _{ A } \textrm{tr} \left( AB{A^T}C \right) = CAB + {C^T}A{B^T}$ 공식이 사용되었다.


<div>
$$
\begin{split}
\nabla _{ A } \textrm{tr} \left( AB{A^T}C \right) &= CAB + {C^T}A{B^T} \\
&= AB + A{B^T} \\
& \left( C = I, A = \theta, B = {X^T}X \right)
\end{split}
$$
</div>

드디어 행렬폼에서의 $\nabla_{\theta} J(\theta)$를 얻었다.

<div>
$$\nabla_{\theta} J(\theta) = {X^T} X \theta - {X^T} \vec{y} = 0$$
</div>

우변을 정리하면,

<div>
$${X^T} X \theta = {X^T} \vec{y}$$
</div>

이 형태를 **Normal Equation**이라고 한다.

다시 $\theta$에 따라 정리하면,

<div>
$$\theta = {\left( {X^T} X \right)}^{-1} {X^T} \vec{y}$$
</div>

따라서, cost function $J(\theta)$를 minimize 하는 $\theta$는 ${\left( {X^T} X \right)}^{-1} {X^T} \vec{y}$ 이다. ■

<hr>

[^1]: LMS algorithm은 **Widrow-Hoff leraning rule**이라고도 불림. 형태는 완전 동일함.
[^2]: $:=$를 assignment의 의미로 사용