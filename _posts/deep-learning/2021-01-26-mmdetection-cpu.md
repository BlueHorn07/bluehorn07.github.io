---
title: "mmdetection + CPU"
layout: post
tags: ["research"]
---


이 포스트는 제가 개인적인 용도로 정리한 글 입니다. 

`mmdetection`을 CPU에서 테스트해 볼 일이 있는데, `mmdetection` 공식 doc에 설명이 부족해서 이곳에 별도로 정리해둡니다!

- open-mmlab/mmdetection - [link](https://github.com/open-mmlab/mmdetection)

<br>
<hr>

### `mmdetection`이란?

`pytorch`로 작성된 Object Detection과 Instance Segmentation의 다양한 모델들을 정리한 플랫폼이다. 정말 다양한 모델들을 지원하고 있다!!

<hr>

### Get Start only with 'CPU'

딥러닝을 하시는 대부분의 분들을 GPU가 있고, 본인 역시 GPU를 소유하고 있지만, 어떤 경우에는 CPU로 모델을 테스트하고 싶을 수 있다.

그래서 본인은 `mmdetection` 모델을 CPU로 시작하고자 했다.

이 [링크](https://github.com/open-mmlab/mmdetection/blob/master/docs/get_started.md)를 통해 들어가면 `mmdetection`을 시작하는 방법이 설명되어 있다.

CPU로 `mmdetection`을 실행하기 위한 대부분의 과정은 동일하나, 몇몇 부분에서 조금 다르다. 

<br>

#### Installation

아래의 과정을 따라가자.

1\. Create a conda virtual environment and activate it.

```
conda create -n open-mmlab python=3.7 -y
conda activate open-mmlab
```

2\. Install PyTorch and torchvision

```
conda install pytorch torchvision -c pytorch
```

<small markdown="1">p.s. 여기서 `-c` 옵션은 `channel`을 의미하는데, `pytorch`에 맞춰진 채널을 사용해 설치하는 옵션이다.</small>

3\. `mmcv-full` 설치

```
pip install mmcv-full
```

4\. Clone the MMDetection repository.

```
git clone https://github.com/open-mmlab/mmdetection.git
cd mmdetection
```

5\. Install build requirements and then install MMDetection.

```
pip install -r requirements/build.txt
pip install -v -e .
```

위의 과정을 완료하면, `mmdetection`을 설치해 CPU로 사용할 수 있다!

단, CPU만으로 `mmdetection`을 사용하게 되면, 최신 테크닉을 쓰는 요즘 모델을 쓰는게 불가능할 수 있다!! `mmdetection`의 문서에 해당 내용이 나와 있으니 CPU 버전으로 본인이 쓸 모델이 가능한지 확인하자.

<br>

#### Verification

설치가 제대로 되었는지 확인해보자.

`mmdetection`의 공식 문서에서는 아래와 같은 검증 코드를 제공한다.

``` py
from mmdet.apis import init_detector, inference_detector

config_file = 'configs/faster_rcnn/faster_rcnn_r50_fpn_1x_coco.py'
# download the checkpoint from model zoo and put it in `checkpoints/`
# url: http://download.openmmlab.com/mmdetection/v2.0/faster_rcnn/faster_rcnn_r50_fpn_1x_coco/faster_rcnn_r50_fpn_1x_coco_20200130-047c8118.pth
checkpoint_file = 'checkpoints/faster_rcnn_r50_fpn_1x_coco_20200130-047c8118.pth'
# device = 'cuda:0'
device = 'cpu' # cpu로 테스트할 경우, 이 부분을 꼭 바꿔줘야 한다!
# init a detector
model = init_detector(config_file, checkpoint_file, device=device)
# inference the demo image
inference_detector(model, 'demo/demo.jpg')
```

검증 코드를 실행하기 위해선, **"반드시"** 코드에 제시된 [링크](http://download.openmmlab.com/mmdetection/v2.0/faster_rcnn/faster_rcnn_r50_fpn_1x_coco/faster_rcnn_r50_fpn_1x_coco_20200130-047c8118.pth)를 통해 학습된 모델을 다운 받아 `checkpoint/`  폴더에 넣어야 한다!!

또한, `device` 설정을 `device='cpu'`로 바꿔줘야 한다! 

<br>

이 코드를 그대로 실행하면, 아무런 시각적 결과를 얻지 못 한다... 😥

그래서 위의 코드를 아래와 같이 수정해줘야 한다.

``` py
from mmdet.apis import show_result_pyplot, init_detector, inference_detector

... # 중략

# inference the demo image
result = inference_detector(model, 'demo/demo.jpg')

show_result_pyplot(model, 'demo/demo.jpg', result, 0.3)
```

<br>

위의 코드가 잘 실행 되었다면, 아래와 같은 결과를 얻는다.

<div class="img-wrapper">
    <img src="https://i.imgur.com/dkmpAzH.png" width="75%">
</div>

<br>

p.s. 본인은 실행했을 때, 아래와 같은 Warning을 받았다.

```
mmdetection\mmdet\datasets\utils.py:60: UserWarning: "ImageToTensor" pipeline is replaced by "DefaultFormatBundle" for batch inference. It is recommended to manually replace it in the test data pipeline in your config file.
  'data pipeline in your config file.', UserWarning)
```

그런데, 실행 자체에는 큰 문제가 없는 것 같다!



