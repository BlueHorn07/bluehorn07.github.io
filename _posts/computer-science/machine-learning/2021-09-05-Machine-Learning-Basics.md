---
title: "Machine Learning Basics"
toc: true
toc_sticky: true
categories: ["Machine Learning"]
---



"Machine Learning"을 공부하면서 개인적인 용도로 정리한 포스트입니다. 지적은 언제나 환영입니다 :)


<hr/>

<span class="statement-title">Definition.</span> basis function; 기저 함수<br>

$y(\mathbf{x}, \mathbf{w}) = \mathbf{w} \cdot \mathbf{x}$인 Linear Regression식에 input $\mathbf{x}$를 transform 하는 함수를 말함.

$$
y(\mathbf{x}, \mathbf{w}) = \mathbf{w} \cdot \phi(\mathbf{x})
$$

이런 basis function 여러 개를 사용해 Linear Regression[^1] 할 수도 있다.

$$
y(\mathbf{x}, \mathbf{w}) = \sum_{j=1}^{M} \mathbf{w}_j \cdot \phi_j(\mathbf{x})
$$

<span class="statement-title">Example.</span> basis function<br>

- polynomial basis function

$$
\phi_j(x) = (x)^j
$$

- sigmoid basis function

$$
\begin{aligned}
\phi_j(x) = \sigma \left( \frac{x - u_j}{s}\right) \\
\sigma(a) = 1 / (1 + \exp (-a))
\end{aligned}
$$

- gaussian basis function

$$
\phi_j(x) = \exp \left( - \frac{(x - u_j)^2}{2s^2}\right)
$$

<div class="img-wrapper">
  <img src="{{ "/images/computer-science/machine-learning/basis-function.png" | relative_url }}" width="400px">
</div>

<hr/>

### references

- [PRML - Ch.3](http://norman3.github.io/prml/docs/chapter03/1)

<hr/>

[^1]: $x$에 대해서는 비선형 함수이지만, $w$에 대해서 선형이므로 Linear Regression이라고 부른다.
