---
title: "Gradient Filter (code)"
toc: true
toc_sticky: true
categories: ["Computer Vision"]
---


본 글은 2020-2학기 "컴퓨터 비전" 수업을 듣고, 스스로 학습하면서 개인적인 용도로 정리한 것입니다. 지적은 언제나 환영입니다 :)

<hr>

<div>
  <img src="/images/computer-science/AIGS539/gradient-filter-1.png">
</div>

<br>

수업 내용 중, Convolution filter를 이용해 이미지의 Edge를 추출하는 방법으로 **<u>Gradient filter</u>**가 소개되었다.

실제로 어떤 결과가 생기는지 흥미가 생겨 직접 코드로 확인해보자.

<hr>

**<u>Gradient filter</u>**는 `openCV`의 함수들을 이용해 정말 쉽게 구현할 수 있다!

먼저 전체 코드는 아래와 같다.

``` python
import cv2

IMG_PATH = "origin.png"

if __name__ == "__main__":
  # load image
  img = cv2.imread(IMG_PATH)

  # make image gray
  img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

  # take gradient
  ## Gaussian blurring
  blurred = cv2.GaussianBlur(img, (3, 3), 0)

  ## sobel filter
  sobelX = cv2.Sobel(blurred, cv2.CV_8U, 1, 0, ksize=3)
  sobelY = cv2.Sobel(blurred, cv2.CV_8U, 0, 1, ksize=3)

  ## merge two gradients
  gradient = sobelX + sobelY

  cv2.imshow("Gradient Image", gradient)
  cv2.waitKey()
  # cv2.imwrite("Gradient Image", gradient)
```

코드의 로직을 하나씩 살펴보자.

<br>

이미지의 Edge만 살펴볼 것이기 때문에 RGB 이미지를 gray 이미지로 바꿔준다.

``` python
  # make image gray
  img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
```

<br>

먼저 입력된 이미지에 Gaussian 필터를 적용해 blurring한다. Gaussian 필터는 적요하면 Noise를 제거할 수 있어, 더 nice한 결과를 얻을 수 있다.

``` python
  ## Gaussian blurring
  blurred = cv2.GaussianBlur(img, (3, 3), 0)
```

<br>

이제 이미지에 Gradient를 적용하는 부분이다. Gradient filter를 **Sobel filter**라고도 하는데, `openCV`의 `cv2.Sobel()` 함수를 사용하면 된다!

``` python
  ## sobel filter
  sobelX = cv2.Sobel(blurred, cv2.CV_8U, 1, 0, ksize=3)
  sobelY = cv2.Sobel(blurred, cv2.CV_8U, 0, 1, ksize=3)
```

`sobelX`는 수평 성분을 제거한 이미지로, 수직 Edge를 검출한다. 반대로 `sobelY`는 수직 성분을 제거한 이미지로, 수평 Edge를 검출한다.

<div style="text-align: center;">
  <img src="/images/computer-science/AIGS539/gradient-filter-sobelX.png" style="width: 45%; padding-right: 5px">
  <img src="/images/computer-science/AIGS539/gradient-filter-sobelY.png" style="width: 45%; padding-left: 5px">
</div>

<br>

이제 수직/수평 Edge를 검출한 것을 합쳐주면 된다.

``` python
  ## merge two gradients
  gradient = sobelX + sobelY
```

<div style="text-align: center;">
  <img src="/images/computer-science/AIGS539/gradient-filter-gradient-result.png" style="width: 60%;">
</div>

<hr>

출력된 결과를 보면 알 수 있듯이 `cv2.Sobel()`같은 `openCV`의 함수들을 잘 이용하면 이미지의 Edge를 쉽게 검출할 수 있다.

수업 PPT에 있는 사진은 결과가 회색 배경이던데, 본인의 결과는 검은 배경으로 출력이 된다. 아마 전처리 방식이 조금 다른 것 같다!

<br>

심심해서 본인 로고에도 적용해봤다 ㅎㅎ

<div style="text-align: center;">
  <img src="/images/computer-science/AIGS539/gradient-filter-logo-gradient.png">
</div>