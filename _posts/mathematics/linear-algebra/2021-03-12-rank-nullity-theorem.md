---
title: "Rank-Nullity Theorem"
layout: post
use_math: true
tags: [set_theory]
---



<br><span class="statement-title">TOC.</span><br>

- $\dim (\mathcal{C}(A)) = \dim (\mathcal{R}(A))$
- $\dim (\mathcal{R}(A)) + \dim (\mathcal{N}(A)) = n$

<hr/>

## Rank-Nullity Theorem

\<**Rank-Nullity Theorem**\>은 선형 대수학의 중요한 정리인 \<Fundamental Theorem of Linear Algebra\>의 정리 중 하나다. 정리에 \<**선형 대수**\>의 핵심과 정수를 담고 있기에 아래에 제시되는 정리들이 \<선형 대수\>에서 어떤 의미를 갖는지를 깨달아야 한다!

### Column Rank = Row Rank

<span class="statement-title">Theorem.</span><br>

The row space $\mathcal{R}(A)$ and column space $\mathcal{C}(A)$ of a matrix $A$ have the same dimension.

$$
\dim (\mathcal{C}(A)) = \dim (\mathcal{R}(A))
$$

<span class="statement-title">*Proof*.</span><br>

Let $A \in \mathbb{R}^{m\times n}$, and $r$ be the dimension of $\mathcal{R}(A)$.

Then, there exist $r$ number of basis on $\mathcal{R}(A)$. Say them as $\mathbf{x}_1, \mathbf{x}_2, \dots, \mathbf{x}_r \in \mathbb{R}^{n}$.

We will check $A[\mathbf{x}_1, \mathbf{x}_2, \dots, \mathbf{x}_r]$ to be linearly independent in $\mathcal{C}(A)$.

Supp. $A[\mathbf{x}_1, \mathbf{x}_2, \dots, \mathbf{x}_r]$ are **not** linearly independent (귀류법),

then there exist not all zeros $c_i \in R$ s.t. $\displaystyle \sum^r_i {c_i A\mathbf{x}_i} = 0$.

However, we can combine each $c_i$ with $\mathbf{x}_i$, (linearity of linear transformation $A$)

$$
\sum^r_i {c_i A\mathbf{x}_i} = \sum^r_i {Ac_i \mathbf{x}_i} = 0
$$

Then, we build a vector $\mathbf{v} = c_1 \mathbf{x}_1, c_2 \mathbf{x}_2, \dots, c_r \mathbf{x}_r \in \mathcal{R}(A)$ and denote above situation as 

$$
\sum^r_i {Ac_i \mathbf{x}_i} = A\mathbf{v} = 0
$$

$A\mathbf{v} = 0$ means $\mathbf{v}$ is orthogonal to every rows in $A$. Howevery, $\mathbf{v} \in \mathcal{R}(A)$. Thus $\mathbf{v}$ should be **zero**. (otherwise, it violates the linear independence of basis $\mathbf{x}_1, \mathbf{x}_2, \dots, \mathbf{x}_r$ of $\mathcal{R}(A)$)

Then, $\mathbf{v} = 0$ means all of $c_i = 0$ ($\because$ $\mathbf{x}_1, \mathbf{x}_2, \dots, \mathbf{x}_r$ are linearly independent.)

So, $A[\mathbf{x}_1, \mathbf{x}_2, \dots, \mathbf{x}_r]$ are linearly independent in $\mathcal{C}(A)$!!

Therefore, there are at least $r$ number of linear independent vectors in $\mathcal{C}(A)$!

$$
\dim (\mathcal{R}(A)) = r \le \dim (\mathcal{C}(A))
$$

By applying same argument to $A^T$, we get 

$$
\dim (\mathcal{R}(A)) \ge \dim (\mathcal{C}(A))
$$

Therefore, 

$$
\dim (\mathcal{R}(A)) = \dim (\mathcal{C}(A))
$$

$\blacksquare$

### Rank + Nullity = $n$

<span class="statement-title">Theorem.</span><br>

For any $A \in \mathbb{R}^{m \times n}$,

$$
\dim (\mathcal{R}(A)) + \dim (\mathcal{N}(A)) = n
$$

<span class="statement-title">*Proof*.</span><br>

(1) Supp. $\text{rank}(A) = n$, then the only solution for $A \mathbf{x} = 0$ is $\mathbf{x} = 0$ ($\because$ All rows are linearly independent.)

Therefore, nullity $\dim (\mathcal{N}(A)) = 0$, and given equation holds.

(2) Supp. $\text{rank}(A) = r < n$.

Then $\exists$ $n-r$ free variables in the solution of $A \mathbf{x} = 0$.

Then, we can easily get $n-r$ number of vectors in $\mathcal{N}(A)$, by one-hot at position of only one free variable. These $n-r$ number of vectors are linearly independent. Also, these forms null space of $A$!! Therefore, $\dim (\mathcal{N}(A)) = n-r$.

Thus,

$$
\text{rank}(A) + \dim (\mathcal{N}(A)) = r + (n-r) = n
$$

$\blacksquare$
