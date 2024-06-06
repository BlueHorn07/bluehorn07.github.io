---
title: "Naive Bayes Classifier"
toc: true
toc_sticky: true
categories: ["Applied Statsitcs"]
---

2021-1학기, 대학에서 '데이터 마이닝' 수업을 듣고 공부한 바를 정리한 글입니다. 지적은 언제나 환영입니다 :)

<div class="statement" markdown="1" align="center">

<span class="statement-title" style="font-size: large">Bayes' Theorem</span><br>

$$
\frac{P(\text{data} | \text{parameter}) \cdot P(\text{parameter})}{P(\text{data})} = P(\text{parameter} \mid \text{data})
$$

$$
\frac{\text{Likelihood} \cdot \text{Prior probability}}{\text{Evidence}} = \text{Posterior probability}
$$

</div>

<div class="statement" markdown="1" align="center">

"One assumption taken is the <span style="color:red;">strong independence</span> assumptions btw the features."

</div>

### Naive Assumption

"In a supervised learning situation, Naive Bayes classifiers are trained very efficiently. Naive Bayes classifiers need a small training data to estimate the parameters needed for classification. Naive Bayes classifiers have simple design and implementation, and they can applied to many real life situations."

\<**Naive Bayes Classifer**\>는 Supervised Learning Model이다. Conditional Probability를 활용해 분류를 진행하며 Maximum Likelihood를 달성할 수 있다!

<hr/>

### Naive Bayes Classifier

우리의 목표는 데이터의 feature가 주어졌을 때 각 label에 대한 확률인 Posterior Probability $p(c_k \mid \mathbf{x})$를 구하는 것이다.

이때, 베이즈 정리를 통해 Posterior Probability를 아래와 같이 Conditional Probability로 분해할 수 있다.

$$
p(c_k \mid \mathbf{x}) = \frac{p(c_k) \cdot p(\mathbf{x} \mid c_k)}{p(\mathbf{x})}
$$

이때, Evidence에 해당하는 $p(\mathbf{x})$는 output $y$와 독립이다. 따라서, 우리는 Evidence를 무시하고 아래와 같이 $y$를 추정할 수 있다.

$$
\begin{aligned}
y
&= \underset{c_k}{\text{argmax}} \; p(c_k \mid \mathbf{x}) \\
&= \underset{c_k}{\text{argmax}} \; p(c_k) \cdot p(\mathbf{x} \mid c_k)
\end{aligned}
$$

만약 Prior $p(c_k)$의 확률이 모두 같다면, 사전 확률에 대한 부분도 생략할 수 있다 😁

Data $\mathbf{x}$가 feature $a_1, a_2, \dots, a_n$으로 구성되어 있다고 가정하고, 위의 식들을 다시 써보자.

$$
\begin{aligned}
p(c_k \mid a_1, \dots, a_n)
&= \frac{p(c_k) \cdot p(a_1, \dots, a_n \mid c_k)}{p(a_1, \dots, a_n)} \\
&\propto p(c_k) \cdot p(a_1, \dots, a_n \mid c_k)
\end{aligned}
$$

이때, \<NB Classifier\>에서는 각 feature가 **독립(independent)**하다고 가정한다. 따라서,

$$
\begin{aligned}
p(a_1, \dots, a_n) &= p(a_1) \cdots p(a_n) \\
p(a_1, \dots, a_n \mid c_K) &= p(a_1 \mid c_k) \cdots p(a_n \mid c_k)
\end{aligned}
$$

식을 다시 써보면,

$$
\begin{aligned}
p(c_k \mid a_1, \dots, a_n)
&\propto p(c_k) \cdot p(a_1, \dots, a_n \mid c_k) \\
&= p(c_k) \cdot p(a_1 \mid c_k) \cdots p(a_n \mid c_k) \\
&= p(c_k) \cdot \prod^n_{i=1} p(a_n \mid c_k)
\end{aligned}
$$

만약 위의 식에서 $\propto$를 제거하고 등식으로 바꾸면 아래와 같다.

$$
p(c_k \mid a_1, \dots, a_n)
= \frac{\displaystyle p(c_k) \cdot \prod^n_{i=1} p(a_n \mid c_k)}{\displaystyle\sum_i p(c_i) p(\mathbf{x} \mid c_i)}
$$

<hr/>

### Gaussian Naive Bayes

\<Gaussian NB\> 모델을 Likelihood $p(\mathbf{x} \mid c_k)$가 Gaussian 분포를 따른다고 가정한다. 그런데, 이때 $\mathbf{x}$의 각 feature가 모두 독립이므로 우리는 각 feature에 대한 Gaussian Likelihood를 아래와 같이 정의해 사용한다.

$$
p(a_i \mid c_k) = \frac{1}{\sqrt{2\pi \sigma_{i, c_k}^2}} \cdot \exp \left( - \frac{(a_i - \mu_{c_k})}{2 \sigma_{i, c_k}^2} \right)
$$

위의 Likelihood function을 활용해 식을 다시 기술하면 아래와 같다.

$$
\begin{aligned}
p(c_k \mid a_1, \dots, a_n)
&\propto p(c_k) \cdot p(a_1, \dots, a_n \mid c_k) \\
&= p(c_k) \cdot \prod^n_{i=1} p(a_n \mid c_k) \\
&= p(c_k) \cdot \prod^n_{i=1} \frac{1}{\sqrt{2\pi \sigma_{i, c_k}^2}} \cdot \exp \left( - \frac{(a_i - \mu_{c_k})}{2 \sigma_{i, c_k}^2} \right)
\end{aligned}
$$

<hr/>

#### 참고자료

- ['opengenus'의 포스트](https://iq.opengenus.org/gaussian-naive-bayes/)
- ['ratsgo'님의 포스트](https://ratsgo.github.io/machine%20learning/2017/05/18/naive/)
- ['heung-bae-lee'님의 포스트](https://heung-bae-lee.github.io/2020/04/14/machine_learning_08/)