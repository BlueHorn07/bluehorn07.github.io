---
title: "SphereNet(2018)"
layout: post
tags: ["research"]
use_math: true
---

### 서론
이 포스트는 제가 개인적인 용도로 정리한 글 입니다. 

<br>
<hr>

**SphereNet**: Learning Spherical Representations for Detection and Classification in Omnidirectional Images (ECCV 2018) [pdf](https://openaccess.thecvf.com/content_ECCV_2018/papers/Benjamin_Coors_SphereNet_Learning_Spherical_ECCV_2018_paper.pdf)

- unofficial [github](https://github.com/ChiWeiHsiao/SphereNet-pytorch): 공식 구현은 아니고 다른 사람이 직접 구현한 모델이다. 

### Kernel Sampling Method

<div class="quote" markdown="1">
&emsp; "The central idea of SphereNet is to lift local CNN operations from the regular image domain to the sphere surface where omnidirectional images can be represented without distortions."
</div>

<div class="img-wrapper">
<img src="https://i.imgur.com/5pLQuGX.png" width="40%">
</div>
<div class="quote" markdown="1">
&emsp; "This is achieved by represneting the **kernel** as a **small patch tangent to the spehre.**"
</div>

- $S$: unit sphere
- $S^2$: its surface
- $\mathbf{s} = (\phi, \theta) \in S^2$
  - 점 $\mathbf{s}$가 $S^2$ 위에 있어, 좌표가 위도/경도로 표현된다는 말은 곧 "equirectangular image 위에 있다"는 말이다.
- $\Pi$: tangent plane located at $$\mathbf{s}_{\Pi} = (\phi_{\Pi}, \theta_{\Pi})$$
  - $\mathbf{x}$ : a point on $\Pi$ by its coordinates $\mathbf{x} \in \mathbb{R}^2$
  - $\Pi_0$: tangent plane located at $\mathbf{s} = (0 ,0)$.

<div class="quote" markdown="1">
&emsp; "A point $s$ on the sphere is related to its tangent plane coordinates $\mathbf{x}$ via a gnomonic projection."

<div style="text-align:center;">
($\mathbf{s}$: point on sphere) $\equiv$ ($\mathbf{x}$ : cooridnate on tanget plane)
</div>
</div>

<span class="statement-title">Sampling at the center</span>

Equirectangular의 중심에서 샘플링 = step size $\Delta_{\theta}$, $\Delta_{\phi}$ 만큼 sampling 함.

$$
\begin{aligned}
    \mathbf{s}_{(0, 0)} &= (0, 0) \\
    \mathbf{s}_{(\pm 1, 0)} &= (\pm \Delta_{\theta}, 0) \\
    \mathbf{s}_{(0, \pm 1)} &= (0, \pm \Delta_{\phi}) \\
    \mathbf{s}_{(\pm 1, \pm 1)} &= (\pm \Delta_{\theta}, \pm \Delta_{\phi})
\end{aligned}
$$

<details>
<summary>실제 논문에서의 notation</summary>
<div class="statement" markdown="1">

$$
\begin{aligned}
    \mathbf{s}_{(0, 0)} &= (0, 0) \\
    \mathbf{s}_{(\pm 1, 0)} &= (\pm \Delta_{\phi}, 0) \\
    \mathbf{s}_{(0, \pm 1)} &= (0, \pm \Delta_{\theta}) \\
    \mathbf{s}_{(\pm 1, \pm 1)} &= (\pm \Delta_{\phi}, \pm \Delta_{\theta})
\end{aligned}
$$

아래의 공식들과 notation이 약간 안 맞아서, 포스트의 방식으로 $\phi$와 $\theta$ 순서를 바꿨다.

</div>
</details>
<br>

이때, 각 sampling point들이 tangent plane $\Pi_0$의 어디에 있는지 계산할 수 있다. [gnomonic projection]({{"2020/09/24/Gnomonic-Projection.html#how-to-map-with-gnomonic-projection" | relative_url}})을 이용해 계산한 결과는 아래와 같다.

<div class="statement">

$$
\begin{aligned}
    x(\theta, \phi) &= \frac{\cos \phi \sin (\theta - \theta_{\Pi_0})}{\sin \phi_{\Pi_0} \sin \phi + \cos \phi_{\Pi_0}\cos \phi \cos (\theta - \theta_{\Pi_0})} \\
    \\
    y(\theta, \phi) &= \frac{\cos \phi_{\Pi_0} \sin \phi - \sin \phi_{\Pi_0}\cos \phi \cos (\theta - \theta_{\Pi_0})}{\sin \phi_{\Pi_0} \sin \phi + \cos \phi_{\Pi_0}\cos \phi \cos (\theta - \theta_{\Pi_0})}
\end{aligned}
$$

</div>

<br>

그래서 Sampling pattern $$\mathbf{s}_{(j, k)}$$는 곧, 아래와 같은 kernel pattern $\mathbf{x}_{(j, k)}$를 유도한다.

$$
\begin{aligned}
  \mathbf{x}_{(0, 0)} &= (0, 0) \\
  \mathbf{x}_{(\pm 1, 0)} &= (\pm \tan \Delta_{\theta}, 0) \\
  \mathbf{x}_{(0, \pm 1)} &= (0, \pm \tan \Delta_{\phi}) \\
  \mathbf{x}_{(\pm 1, \pm 1)} &= (\pm \tan \Delta_{\theta}, \pm \sec \Delta_{\theta} \tan \Delta_{\phi})
\end{aligned}
$$

<details>
<summary>공식 유도</summary>
<div class="math-statement" markdown="1">

위의 공식이 잘 와닿지 않아서 직접 유도해보려고 한다.

<div class="statement" markdown="1">

먼저 $\mathbf{s}_{(0, 0)} = (0, 0)$인 경우를 살펴보자.

이때 $(\theta_{\Pi_0}\, , \phi_{\Pi_0}) = (0, 0)$이다.

이제 공식에 대입해보자.

$$
\begin{aligned}
    x(0, 0) &= \frac{\cos 0\sin (0 - 0)}{\sin 0 \sin 0+ \cos 0\cos 0\cos (0- 0)} = 0\\
    \\
    y(0, 0) &= \frac{\cos 0 \sin 0- \sin 0\cos 0\cos (0- 0)}{\sin 0 \sin 0+ \cos 0\cos 0\cos (0- 0)} = 0
\end{aligned}
$$

그럼 분자의 텀이 모두 0이 되기 때문에, 위에서 유도한 $\mathbf{x}_{(0, 0)} = (0, 0)$의 결과가 나온다.

</div>

일단 다음 과정을 진행하기 전에 $(\phi_{\Pi_0}\, , \theta_{\Pi_0}) = (0, 0)$에 맞춰서 공식을 refine 하자.

$$
\begin{aligned}
    x(\theta, \phi) &= \frac{\cos \phi \sin (\theta - 0)}{\sin 0 \sin \phi + \cos 0\cos \phi \cos (\theta - 0)} \\
    &= \frac{\cos \phi \sin \theta}{\cos \phi \cos \theta} = \frac{\sin \theta}{\cos \theta} = \tan \theta \\
    \\
    y(\theta, \phi) &= \frac{\cos 0 \sin \phi - \sin 0\cos \phi \cos (\theta - 0)}{\sin 0 \sin \phi + \cos 0\cos \phi \cos (\theta - 0)} \\
    &= \frac{\sin \phi}{\cos \phi \cos \theta} = \frac{\tan \phi}{\cos \theta}
\end{aligned}
$$

<div class="statement" markdown="1">

이번에는 $$\mathbf{s}_{(\pm 1, 0)} = (\pm \Delta_{\theta}, 0)$$의 경우를 살펴보자.

이때, $(\theta_{\Pi_0}\, , \phi_{\Pi_0}) = (0, 0)$이다.

공식에 대입하면 아래와 같다.

$$
\begin{aligned}
    x(\pm \Delta_{\theta}, 0) &= \frac{\sin \theta}{\cos \theta} = \frac{\sin (\pm \Delta_{\theta})}{\cos (\pm \Delta_{\theta})} = \pm \tan \Delta_{\theta}\\
    \\
    y(\pm \Delta_{\theta}, 0) &= \frac{\tan 0}{\cos \pm \Delta_{\theta}} = 0
\end{aligned}
$$

그래서 위에서 유도한 $$\mathbf{x}_{(\pm 1, 0)} = (\pm \tan \Delta_{\theta}, 0)$$의 결과가 나온다.

</div>

</div>
</details>

<br>

<span class="statement-title">Inverse gnomonic projection</span>

앞에서는 kernel의 중심을 $$\mathbf{s}_{\Pi_0} = (0, 0)$$으로 잡았다면, 이번에는 $$\mathbf{s}_{\Pi} = (\theta_{\Pi}, \phi{\Pi})$$로 잡자.

이때, kernel paattern $\mathbf{x} = (x, y)$에서 sampling pattern $\mathbf{s} = (\theta, \phi)$는 아래와 같이 유도할 수 있다.

<div class="statement">

$$
\begin{aligned}
  \theta(x, y) &= \theta_{\Pi} + \tan^{-1} {\left( \frac{x \sin \nu}{\rho \cos \phi_{\Pi} \cos \nu - y \sin \phi_{\Pi} \sin \nu} \right)} \\
  \phi(x, y) &= \sin^{-1} {\left( \cos \nu \sin \phi_{\Pi} + \frac{y \sin \nu \cos \phi_{\Pi}}{\rho} \right)} \\
  \textrm{where} \quad & \rho = \sqrt{x^2 + y^2} \quad \textrm{and} \quad \nu = \tan^{-1} \rho
\end{aligned}
$$

</div>

수식이 많이 복잡한데, 간단한 케이스인 $\mathbf{s}_{\Pi_0} = (0, 0)$에서 살펴보자.

<div class="statement">

$$
\begin{aligned}
  \theta(x, y) &= 0 + \tan^{-1} {\left( \frac{x \sin \nu}{\rho \cos 0 \cos \nu - y \sin 0 \sin \nu} \right)} \\
  &= \tan^{-1} {\left( \frac{x \sin \nu}{\rho \cos \nu} \right)} \\
  \phi(x, y) &= \sin^{-1} {\left( \cos \nu \sin 0 + \frac{y \sin \nu \cos 0}{\rho} \right)} \\
  &= \sin^{-1} {\left( \frac{y \sin \nu}{\rho} \right)} \\
  \textrm{where} \quad & \rho = \sqrt{x^2 + y^2} \quad \textrm{and} \quad \nu = \tan^{-1} \rho
\end{aligned}
$$

</div>

사실 이 공식은 앞에서 살펴본,  sampling pattern $\mathbf{s} = (\theta, \phi)$에서 kernel paattern $\mathbf{x} = (x, y)$를 유도하는 공식의 역함수다.

<br>

<div class="quote">
&emsp; "Several recent works also consider adapting the sampling locations of convolutional networks, ... Unlike our work, these methods need to learn the sampling locations during training, ... In contrast, we take advantage of the geometric properties of the camera to inject this knowledge explicitly into the network architecture."
</div>

<div class="img-wrapper">
<img src="https://i.imgur.com/QDTnn5x.png" width="80%"><br>
<img src="https://i.imgur.com/4LCEWNN.png" width="80%">
</div>

<div class="quote">
&emsp; "it is strightforward to allow a filter to sample data across the image boundary. This eliminates any discontinuities ... and improves recognition of objects (which are spllit at the sides of an equirectangular image representation) or (which are positioned very close to the poles)."
</div>

<div class="quote">
&emsp; "In our experimental evaluation, we demonstrate how an (object detector trained on perspective images) can be successfully applied to the omnidirectional case."
</div>

#### Implementation

<div class="quote">
&emsp; "<b>Implementation:</b> As the sampling locationas are fixed according to the geometry of the spherical image representation, they can be <b><u>precomputed</u></b> for each kernel location at every layer of the network."
</div>

<div class="quote">
&emsp; "it is sufficient to calculate and store the sampling locations once per row and then translate them. We store the sampling locations in look-up tables."
</div>

Equirectangular Image에서 row에 대한 정보가 같다면 모두 동일한 kernel sampling deviation을 가진 kernel을 사용한다. 그래서 이것을 row에 따라 계산하여 look-up table에 저장한 후 꺼내 쓴다는 말이다.

<br>
<hr>

### Experiement

#### Spherical Image Classification

Conv layer와 Pool layer를 SphereConv와 SpherePool로 교체해주면 된다. 

평-범

#### Spherical Object Detection

***Spherical Single Shot MultiBox Detector***(Sphere-SSD)를 제시한다. 

<div class="quote">
&emsp; "in contrast to the original SSD, <b><u>anchor boxes are now placed on tangent planes of the sphere</u></b> and are defined in terms of spherical coordinates of their respective tangent plane."
</div>

<div class="img-wrapper">
<img src="https://i.imgur.com/K2sFJIw.png" width="70%">
</div>

<div class="quote">
&emsp; "In order to match anchor boxes to ground-truth detections, we select the anchor box closest to each ground-truth box. During inference, we perform NMS. For evaluation, we use the IoU of (two polygonal regions) which are constructed from the (gnomonic projections of evenly spaced points along the rectangular BBox on the tangent plane)."
</div>

흥미로운 점은 IoU를 비교하기 위해 두 polygonal region을 동일한 tangent plane에 매핑하여 그 상태에서 IoU를 구했다는 점이다. Equirectangular Image에서 IoU를 구하지 않은 점이 흥미롭다.

<br>
<hr>

#### Classification: Omni-MNIST

생-략

#### Ojbect Detection: FlyingCars

<div class="img-wrapper">
<img src="https://i.imgur.com/fk1gomq.png" width="65%">
</div>

360 이미지에 대한 Dataset이 부족하여 논문에서 자체적으로 *FlyingCars* Dataset을 만들었다고 한다.

단순하게 360 이미지에 3D car model을 붙인 형식이라고 한다.

<div class="img-wrapper">
<img src="https://i.imgur.com/jUYvv5M.png" width="65%">
</div>

기존 360 이미지 Detection 방법보다 개선된 결과가 도출되었다.

주목할 점은 Detection된 이미지를 살펴보면, Equirectangular의 Discontinuity 문제를 완벽하게 해결하고 있다는 점이다!!


#### Transfer Learning: OmPaCa

기존의 perspective dataset에서 학습된 모델에 Spherial Layer를 적용한 실험이다.

논문에선 실험을 위해 *Omnidirectional Parked Cars*(OmPaCa) Dataset을 새롭게 제시한다.

KITTI Dataset[^1]에서 학습시킨 perspective SSD model을 Sphere-SSD 모델로 변환하여 fine-tune 하였다.

<div class="img-wrapper">
<img src="https://i.imgur.com/fdJsq0m.png" width="65%">
</div>




<hr>

[^1]: UC Berkeley에서 공개한 "자율주행용 학습 데이터"이다.
