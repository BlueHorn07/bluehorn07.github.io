---
title: "PyTorch - 모델 커스텀"
toc: true
toc_sticky: true
categories: [PyTorch]
---


본 글은 제가 [PyTorch Turotial](https://pytorch.org/tutorials/)의 [torchvision object detection 부분](https://pytorch.org/tutorials/intermediate/torchvision_tutorial)을 읽고 개인적으로 정리한 글입니다. 지적은 언제나 환영입니다 :)

<span class="statement-title">keyword</span>

- 커스텀 데이터셋을 사용할 때
- `torchvision.models`
  - classification
  - detection / instance segmentation
  - video classification
- RPN 커스텀
- ROI pooler 커스텀
- `torchvision` 모델 vs. 커스텀 모델

<br>

전체 코드는 [이곳](https://gist.github.com/BlueHorn07/2871c7ccb6a848918fb478ceaefbb955)에서 확인할 수 있다.

<br>
<hr>

### 커스텀 데이터셋을 사용할 때

대부분의 모델들을 `COCO Dataset` 형식을 따른다. 하지만, 딥러닝을 활용할 분야는 정말 많고, 데이터셋도 정말 많으므로 필요한 순간에는 커스텀 데이터셋을 사용해야 한다.

예전에는 데이터셋을 쓸 때, 3가지 요소
- `torch.utils.data.Dataset` 상속
- `__getitem__()` 구현
- `__len()__()` 구현

만 하면 커스텀 데이터셋을 바로 쓸 수 있는 줄 알았는데, 위의 조건들은 정말 최소한의 조건이고 사실 몇가지 요소를 더 구현해야 한다!

<br>

모델이 `COCO` 데이터셋에 맞춰져 있다면, 아마 아래와 같은 형식을 따라야 할지도 모른다.

<div class="notice" markdown="1">

- image: a PIL Image of size (H, W)
- target: a dict containing the following fields
  - `boxes` (FloatTensor[N, 4]): the coordinates of the N bounding boxes in [x0, y0, x1, y1] format, ranging from 0 to W and 0 to H
  - `labels` (Int64Tensor[N]): the label for each bounding box. 0 represents always the background class.
  - `image_id` (Int64Tensor[1]): an image identifier. It should be unique between all the images in the dataset, and is used during evaluation
  - `area` (Tensor[N]): The area of the bounding box. This is used during evaluation with the COCO metric, to separate the metric scores between small, medium and large boxes.
  -  `iscrowd` (UInt8Tensor[N]): instances with iscrowd=True will be ignored during evaluation.
</div><br>

그래서 내가 내린 결론은,

- `__getitem__`을 구현할 때, 내가 쓰려는 모델이 어떤 형식의 학습데이터를 요구하는지 잘 파악해야 하고,
- 그리고 어쩌면, 내가 가진 데이터셋을 `COCO` 형식에 맞게 변환하는 **<u>변환자</u>**가 필요할 수도 있다는 것이다.

<br>
<hr>

### `torchvision.models`

pyTorch의 `torchvision`은 자체적으로 유명한 모델들을 내부적으로 가지고 있다!!

이 [링크](https://pytorch.org/docs/stable/torchvision/models)에서 `torchvision`에 어떤 모델들이 있는지 확인할 수 있다.

내가 알고 있고, 앞으로 쓸 것 같은 모델들만 간단히 적어보았다.

<br>

- Classification // 아주 많은 모델들이 구현되어 있다!
  - `VGG`
  - `ResNet`
  - `DenseNet`

``` python
import torchvision.models as models
resnet18 = models.resnet18(pretrained=True)
resnet101 = models.resnet101()
vgg16 = models.vgg16(pretrained=True)
vgg16_bn = models.vgg16_bn()
densenet = models.desenet161(pretrained=True)
```

<br>

- [Object Detection, Instance Segmentation](https://pytorch.org/docs/stable/torchvision/models#object-detection-instance-segmentation-and-person-keypoint-detection)
  - `Faster R-CNN ResNet-50 FPN` // `FPN`은 **<u>Feature Pyramid Network</u>**를 말한다!
  - `RetinaNet ResNet-50 FPN`
  - `Mask R-CNN ResNet-50 FPN`

``` python
faster_rcnn50 = models.detection.fasterrcnn_resnet50_fpn(pretrained=True)
mask_rcnn50 = models.detection.maskrcnn_resnet50_fpn(pretrained=True)
```

<br>

- [Video Classification](https://pytorch.org/docs/stable/torchvision/models#video-classification)
  - `ResNet 3D`

``` python
resnet3d_18 = models.video.r3d_18() # 18 layer ResNet3D model
```

<br>
<hr>

### RPN 커스텀

`torchvision`의 Detection 모델은 `rpn` 부분만 커스텀할 수 있게 했다!

그래서 아래와 같이 `AnchorGenerator` 인스턴스를 생성해 `rpn` 커스텀이 가능하다!

``` python
from torchvision.models.detection.rpn import AnchorGenerator

custom_rpn = # 5x3 anchor patterns
  AnchorGenerator(sizes=((32, 64, 128, 512)), aspect_ratios=((0.5, 1.0, 2.0)))
```

### ROI pooler 커스텀

`torchvision`은 [`ops` 모듈](https://pytorch.org/docs/stable/torchvision/ops)을 통해 컴퓨터 비전에서 자주 사용하는 연산들을 제공한다.

대표적으로 `nms`, `roi_pool`, `roi_align`, `MultiScaleRoIAlign` 등이 있다.

pytorch Tutorial의 [torchvision object detection](https://pytorch.org/tutorials/intermediate/torchvision_tutorial)에선 `MultiScaleRoIAlign`를 roi pooler로 채용하였다.

``` python
roi_pooler =
  torchvision.ops.MultiScaleRoIAlign(featmap_names=[0], output_size=7, sampling_ratio=2)
```

<br>
<hr>

### `torchvision` 모델 vs. 커스텀 모델

튜토리얼에서 제시한 두 가지 방법을 비교해보자.

1\. `torchvision` 모델을 사용


``` python
model = torchvision.models.detection.fasterrcnn_resnet50_fpn(pretrained=True)
```

2\. 모델을 커스텀하여 사용

``` python
num_classes = 2  # 1: person, 0: background
in_features = model.roi_heads.box_predictor.cls_score.in_features

model.roi_heads.box_predictor = FastRCNNPredictor(in_features, num_classes)

backbone = torchvision.models.mobilenet_v2(pretrained=True).featres
backbone.out_channels = 1280

anchor_generator
  = AnchorGenerator(sizes=((32, 64, 128, 256, 512)), aspect_ratios=((0.5, 1.0, 2.0)))

roi_pooler
  = torchvision.ops.MultiScaleRoIAlign(featmap_names=[0], output_size=7, sampling_ratio=2)

model = FasterRCNN(
  backbone,
  num_classes=2,
  rpn_anchor_generator=anchor_generator,
  box_roi_pool=roi_pooler
  )
```

<br>
<hr>

전체 코드는 [이곳](https://gist.github.com/BlueHorn07/2871c7ccb6a848918fb478ceaefbb955)에서 확인할 수 있다.