---
title: "Object Detection Datasets"
layout: post
use_math: true
tags: [AIGS539]
---

## 서론
본 글은 2020-2학기 "컴퓨터 비전" 수업을 듣고, 스스로 학습하면서 개인적인 용도로 정리한 것입니다. 지적은 언제나 환영입니다 :)

<hr>

#### 목록
- PASCAL VOC
  - 2007
  - 2012
- ImageNet
  - ILSVRC (2010 ~ 2017)
- MS COCO
  - 2014
  - 2017

<hr>

## PASCAL VOC

2005년부터 2012년까지 실시된 PASCAL VOC challenge를 위해 개발된 데이터셋이다.

<div style="text-align:center;">
  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT841dRMRT7CMJwzk79UPS49qUJZhcQY2fKHQ&usqp=CAU" style="width:35%;">
  <p>picture from PASCAL VOC</p>
</div>

`PASCAL VOC`데이터셋은 2007년 버전과 2012년 버전이 있다. 둘 모두 **<u>20개 클래스</u>**가 있으며, 2007년 VOC는 **<u>9,963장</u>**, 2012년 VOC는 **<u>11,530장</u>**의 이미지를 제공한다.

본래의 `PASCAL VOC` 데이터의 사이트가 잘 접속이 안 되서 미러 사이트인 아래 사이트를 통해 다운로드 할 수 있다.

[PASCAL VOC 미러 사이트](https://pjreddie.com/projects/pascal-voc-dataset-mirror/)

`PASCAL VOC 2007`은 439MB로 데이터셋 크기가 거대하지 않아서 데이터셋을 체험하는 용도로 쓸만하다.

현재는 모델의 성능을 평가하는 benchmark 용도로만 사용되며, 학습에는 잘 사용하지 않는다고 한다.

<hr>

## ImageNet

`ImageNet` 데이터셋은 **<u>1,000개 클래스</u>**에 **<u>1.2M장의 학습 데이터</u>**, **<u>100K장의 테스트 데이터</u>**를 가진 거대한 데이터셋이다.

<div style="text-align:center;">
  <img src="https://blog.acolyer.org/wp-content/uploads/2016/04/imagenet-fig4l.png" style="width:55%;">
  <p>picture from <a href="https://blog.acolyer.org/2016/04/20/imagenet-classification-with-deep-convolutional-neural-networks/" target="_blank">ADRIAN COLYER's blog</a></p>
</div>

<br>

`ImageNet` 데이터셋을 활용하는 ILSVRC <small>ImageNet Large Scale Visual Recognition Challenge</small> 대회를 주관한다. ILSVRC 대회는 2010년부터 2017년까지 진행되었다.

ILSVRC 대회를 통해 기라성 같은 모델들이 쏟아져 나왔다. 우승한 모델들을 살펴보면,

<div style="text-align:center;">
  <img src="https://miro.medium.com/max/1050/1*DBXf6dzNB78QPHGDofHA4Q.png" style="width:65%;">
  <p>picture from <a href="https://medium.com/analytics-vidhya/cnns-architectures-lenet-alexnet-vgg-googlenet-resnet-and-more-666091488df5" target="_blank">Siddharth Das's blog</a></p>
</div>

**<u>AlexNet</u>**, **<u>VGG</u>**, **<u>ResNet</u>** 등등 정말 컴퓨터 비전 분야를 견인한 간판 모델들을 배출한 대회다. 

<br>

[ImageNet 공식 사이트](http://www.image-net.org/)

다만, 데이터셋 다운로드 과정이 조금 까다롭다.

<br>

그러나 `ImageNet` 데이터셋에는 아래와 같은 문제점이 있었는데

- 이미지 내의 object가 큰 편임
- object가 중앙에 잘 위치해 있음
- 이미지에 존재하는 object의 수가 적음

이런 문제점 때문에 데이터셋 외부의 실제 사진에서는 모델의 정확도가 떨어진다는 지적이 있었다.

<hr>

## MS COCO

<div style="text-align:center;">
  <img src="https://cocodataset.org/images/detection-splash.png" style="width:65%;">
  <p>picture from <a href="https://cocodataset.org/5" target="_blank">MS COCO</a></p>
</div>

`MC COCO`는 `PASCAL VOC`와 `ImageNet` 데이터셋의 문제점을 해결한 **<u>2014년</u>**에 공개된 데이터셋이다.

만 4세 아이가 쉽게 인식할 수 있는 이미지들을 제공한다. 이미지 내부에 다양한 크기의 물체들이 존재하며, 높은 확률로 작은 물체들이 등장한다.

대략 **<u>80개 클래스</u>**[^1]에 **<u>330K장</u>**의 이미지를 제공하며, **<u>1.5M 정도의 object instance</u>**들이 존재한다.

**2014년 버전**과 **2017년 버전**이 존재한다. 클래스 레이블의 수는 둘다 80개로 동일하다.

`COCO 2017`는 `COCO 2014`에서 Train/Val의 비율을 조정한 데이터셋이다. `COCO 2014`에서는 Train/Val을 83K/41K로 나눴다면, `COCO 2017`은 Train/Val을 118K/5K의 비율로 나누었다.

<br>

[MS COCO 공식 사이트](https://cocodataset.org/)

데이터셋의 크기가 Train은 ≥13GB, Val과 Test도 6GB 정도의 거대한 데이터셋이다.

<hr>

### 참고자료
- [다크 프로그래머/컴퓨터 비전 분야의 국제대회 소개](https://darkpgmr.tistory.com/54)
- [ChaCha/Object detection dataset 리뷰](https://chacha95.github.io/2020-02-27-Object-Detection4/)
- [COCO Category 91 vs 80](https://eehoeskrap.tistory.com/368)


<hr>

[^1]: COCO 데이터셋의 클래스 레이블에 대한 정보는 [이곳](https://github.com/amikelive/coco-labels)에서 확인할 수 있다.