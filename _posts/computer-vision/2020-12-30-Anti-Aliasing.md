---
title: "Anti-Aliasing"
toc: true
toc_sticky: true
categories: ["Computer Vision"]
---


이 글은 정리를 위해 개인적인 용도로 작성된 포스트입니다. 지적과 조언은 언제나 환영입니다 ㅎㅎ

<br>
<hr>

### Aliasing이란?

<div markdown="1">
<div class="img-wrapper" style="float:left; margin-right:12px;">
<img src="https://w.namu.la/s/afb4e324aefb780bdfd6121feec59092f85ac76ef8637364629a13b59381a80babe1be4d9d24501eb9e2f069682fb98d62d2ef97efd84724970712b22553d717bc1cddc7190c9eb827dee637f22016fd819c405a03a133a66293c7fa5a28ffee" style="width:180px;">
</div>

<div markdown="1">
**<u>Aliasing</u>**은 컴퓨터 그래픽에서 물체의 가장자리 부분이 매끄럽지 않고 계단처럼 보이는 현상을 말한다.

Aliasing 현상은 그림의 가장자리에서 가장 심하게 부각된다.

이런 Aliasing을 처리하기 위해 **<u>Anti-Aliasing</u>** 기법을 사용하게 된다.

일반적으로는 계단의 사이사이 픽셀에 "그림의 가장자리 색과 배경값의 중간값"을 부여하는 방식을 사용한다.

다만, Anti-Aliasing을 적용하게 되면, 모서리는 매끄러워지지만, 그만큼 추가적인 컴퓨팅 연산을 요구한다.

<br>

Aliasing을 좀더 formal하게 정의한 것은 아래와 같다.

<div class="notice" markdown="1">
디지털 샘플링에서 (샘플링 주파수)가 (원본 신호의 최대 주파수)의 2배 보다 낮은 경우, 인접한 스펙트럼이 겹쳐서 출력이 왜곡되는 현상
<div class="img-wrapper">
<img src="https://zone.ni.com/images/reference/en-XX/help/371361R-01/guid-932173fe-d89c-4bc0-a3ed-980f99bcc06b-help-web.png" style="width:270px;">
</div>

"For a given sampling frequency, the maximum frequency you can accurately represent without aliasing is the **<u>Nyquist frequency</u>**. The Nyquist frequency equals one-half the sampling frequency, as shown by the following equation."

$$
f_N = \frac{f_s}{2}
$$

</div>

사실 컴퓨터 그래픽스에서 발생하는 Aliasing은 **<u>limited granularity</u>**에 의해 발생한다.

<div class="notice" markdown="1">

"Digital imprecision generated in the process of converting analog information into digital space is due to the limited granularity of digital numbering space. In computer graphics, aliasing is seen as [pixelation](https://en.wikipedia.org/wiki/Pixelation)."

\- from [Wikipedia](https://en.wikipedia.org/wiki/Digital_artifact#:~:text=In%20computer%20graphics%2C%20aliasing%20is,result%20in%20undesirable%20visual%20artifacts.)

</div>

#### Digital Artifact

<div class="notice" markdown="1">

"**<u>Digital Artifact</u>** is any undesired or unintended alteration in data introduced in a digital process."

</div>

Aliasing를 이런 Artifact에 의한 현상 중 하나로 간주한다.

</div>
</div>

### Anti-Aliasing

<div class="img-wrapper" >
<img src="https://w.namu.la/s/48142ac09ed4e24d09c9839da762a3dc2875dc3c45be03c85076061758523e95eba7abef9fd0e92684a3b4f12ca161b53da44072b12063c6263d8c401e3f48af5479d0504c686459681e228847bef2e0c259425159005b82a062e811c9f89a4c" style="width:400px;">
</div>

Anti-Aliasing은 계단현상인 Aliasing을 해결하기 위한 방법이다.

Anti-Aliasing은 위 사진처럼 같은 타일이 수업이 반복되는 것을 멀리서 바라볼 때, 큰 효과를 볼 수 있다.

가장 왼쪽이 Anti-Aliasing을 적용하지 않았을 때고, 가운데와 우측의 그림이 Anti-Aliasing을 적용한 그림이다.

- [나무위키/Anti-Aliasing](https://namu.wiki/w/%EC%95%88%ED%8B%B0%EC%97%90%EC%9D%BC%EB%A6%AC%EC%96%B4%EC%8B%B1)에서 더 많은 Anti-Aliasing 기법들을 살펴볼 수 있다.



#### Anti-Aliasing Filter (AAF)

결국 이미지 관점에서 발생하는 Aliasing 문제를 해결하려면, 중간값을 취하는 것과 같이 bluring 작업이 필요하다.

가장 간단한 방법으로는 계단 현상을 완화할 부분에 **<u>Gaussian Kernel</u>**을 적용하는 것이다.

<div class="img-wrapper">
<img src="http://www.dspguide.com/graphics/F_24_5.gif" style="width:45%">
<img src="http://www.dspguide.com/graphics/F_24_7.gif" style="width:45%">
<p>Image from <a href="http://www.dspguide.com/ch24/3.htm">here</a></p>
</div>

참고로 2D Filter는 두 개의 1D Filter로 분해 가능하다.

그래서 (2D Filter를 한번 적용)하는 것과 (1D Filter를 두번 적용)하는 것은 같은 결과를 뱉는다.

참고로 `Anti-Aliasing Filter(AAF)`나 `Low-pass Filter(LPF)`나 `blur Filter`나 모두 동일한 말이다.

#### `torchvision.transform.GaussianBlue()`

code-level에서는 이미지를 어떻게 Bluring 할 수 있는지 살펴보자.

`PyTorch`에서는 Gaussian Blur를 할 수 있는 `transform`을 제공한다.

그래서 `torch.utils.data.Dataset`을 정의할 때, `transform` 항목의 인자로 `GaussianBlur()`를 넘길 수 있다.

``` python
torchvision.transform.GaussianBlur(kernel_size, sigma=(0.1, 2.0))
```

\- [PyTorch doc/GaussianBlur()](https://pytorch.org/docs/stable/torchvision/transforms#torchvision.transforms.GaussianBlur)

