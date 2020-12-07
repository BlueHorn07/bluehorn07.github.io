---
title: "ResNet (code)"
layout: post
use_math: true
tags: [AIGS539]
---

## 서론
본 글은 2020-2학기 "컴퓨터 비전" 수업을 듣고, 스스로 학습하면서 개인적인 용도로 정리한 것입니다. 지적은 언제나 환영입니다 :)

<hr>

`pyTorch`를 이용해 `ResNet`을 구현한 [github/KellerJordan](https://github.com/KellerJordan/ResNet-PyTorch-CIFAR10/blob/master/model.py)의 코드에 대한 개인적인 분석글입니다.

`ResNet32`을 구현한 것으로 추정됩니다.

<div style="text-align: center;">
  <img src="https://miro.medium.com/max/1050/1*chbylvv0Lts1hKEuOJix6g.png" style="width: 85%;">
  <p> picture from <a href="https://towardsdatascience.com/resnets-for-cifar-10-e63e900524e0">Pablo Ruiz's blog</a></p>
</div>

## ResNet20 코드 분석

### ResNet20

``` python
class ResNet(nn.Module):
  ...  
  self.layers1 = self._make_layer(n, 16, 16, 1)
  ...

  def _make_layer(self, layer_count, channels, channels_in, stride):
    return nn.Sequential(
      ResBlock(channels, channels_in, stride, ...),
      *[ResBlock(channels) for _ in range(layer_count-1)])

class ResBlock(nn.Module):
  ...
```
`ResNet` 모듈 하나만 만들어 모델을 구축한 것이 아니라 `ResBlock` 모듈을 만들어 사용한 점이 눈에 띈다.

즉, `nn.Module`을 상속 받은 모듈 내부에 또 다른 모듈을 심어서 모델 구조를 디자인할 수 있음을 보여준다! (굳이 따지자면, dependency를 부여했다는 말)

<br>

`ResBlock` 모듈을 `nn.Sequential`를 이용해 이어붙였다.

``` python
def _make_layer(self, layer_count, channels, channels_in, stride):
  return nn.Sequential(
      ResBlock(channels, channels_in, stride, ...),
      *[ResBlock(channels) for _ in range(layer_count-1)])
```

`[ResBlock(channels) for _ in range(layer_count-1)]` 이 부분을 보면 알 수 있듯 내부에 위치한 `ResBlock`에선 채널수가 유지된다.

<br>

코드에서는 `layer_count`로 변수값으로 지정되어 있는데, default 값은 `5`라고 한다.

그래서 `_make_layer` 함수는 채널수를 두 배로 늘리는 `ResBlock`과 채널수가 유지되는 `4`개의 `ResBlock`을 생성한다.

각 `ResBlock`은 2개의 conv layer를 갖는데, 따라서 `_make_layer`가 10개의 conv layer를 생성함을 알 수 있다.

<br>

또, `[ResBlock(channels) for _ in range(layer_count-1)]`는 inline for문을 채용해 코드를 경량화 했다.

<br>

그리고 `nn.Sequential()` 내부에 `*[]`를 사용했는데, 실제로 list 타입에 `*`를 붙여서 `nn.Sequential()`에 전달할 수 있다고 한다. 아래는 예시 코드

``` python
import torch.nn as nn
net = nn
layers = [nn.Linear(2, 2), nn.Linear(2, 2)]                                                  
net = nn.Sequential(*layers)
print(net)
```

<br>

이 ResNet 코드는 `_make_layer()` 함수를 세번 정도 호출한다.

``` python
class ResNet(nn.Module):
  def __init__(self, ...):
    ...
    self.layers1 = self._make_layer(n, 16, 16, 1)
    self.layers2 = self._make_layer(n, 32, 16, 2)
    self.layers3 = self._make_layer(n, 64, 32, 2)
    ...
```

<br>

<hr>

다이어그램으로 표현한 구조와 코드를 비교해보자.

<div style="text-align: center;">
  <img src="https://miro.medium.com/max/1050/1*chbylvv0Lts1hKEuOJix6g.png" style="width: 85%;">
  <p> picture from <a href="https://towardsdatascience.com/resnets-for-cifar-10-e63e900524e0">Pablo Ruiz's blog</a></p>
</div>

``` python
class ResNet(nn.Module):
  def forward(self, x):
    out = self.conv1(x)
    out = self.norm1(out)
    out = self.relu1(out)
    out = self.layers1(out) # in: 16, out: 16
    out = self.layers2(out) # in: 16, out: 32
    out = self.layers3(out) # in: 32, out: 64
    out = self.avgpool(out)
    out = out.view(out.size(0), -1)
    out = self.linear(out) # in: 64, out: 10
    return out
```

`ResNet`의 총 conv layer 수를 따지면, 

1 + (10 + 10 + 10) + 1 = 32

그래서 이 코드는 `ResNet32`를 구현한 것이다!

<hr>

### ResBlock

`ResNet`의 꽃은 **<u>skip connection</u>**이 구현된 `ResBlock` 부분이다.

``` python
class ResBlock(nn.Module):
  ...
  def forward(self, x):
    residual = x # store residual
    out = self.conv1(x)
    out = self.bn1(out)
    out = self.relu1(out)
    out = self.conv2(out)
    out = self.bn2(out)
    out += residual # skip connection!
    out = self.relu2(out)
    return out
```

본인은 이 코드를 보고나서야 비로소 ResNet이 완전히 이해가 되었다 ㅎㅎ

참고로 `ResBlock`에서 사용된 layer 수는 2개이다.

<hr>

#### residual projection options

이 구현에선 `residual`을 바로 더하는 게 아니라 `self.proejction`을 한번 거치게 하는 옵션도 구현을 했다.

``` python
class ResBlock(nn.Module):
  def __init__(self, num_filters, ...):
    ...
    if res_option == 'A':
      self.projection = IdentityPadding(num_filters, channels_in, stride)
    elif res_option == 'B':
      self.projection = ConvProjection(num_filters, channels_in, stride)
    elif res_option == 'C':
      self.projection = AvgPoolPadding(num_filters, channels_in, stride)
    ...
  def forward(self, x):
    ...
    if self.projection: # residual projection!
      residual = self.projection(x)
    ...
```

<br>

각각 2차원의 `residual` 이미지를 처리하는 옵션들로

- `residual` 이미지를 그대로 보내기도 하고; `IdentityPadding()`
- `residual` 이미지를 Convolution 하기도 하고; `ConvProjection()`
- `residual` 이미지를 Avgerage Pooling 하기도 한다; `AvgPoolPadding()`

residual projection 옵션들에 대한 더 자세한 내용은 이 [링크](https://github.com/KellerJordan/ResNet-PyTorch-CIFAR10/blob/204803ca5be4143ee9ab4ae5e165318af45fff50/model.py#L80)를 통해 확인할 수 있다!

<br>

<hr>

KellerJordan의 ResNet은 모델 구현을 깔끔하게 잘 해두어서 정말 좋은 코드라고 생각한다 ㅎㅎ

<br>

`ResNet`을 구현한 또다른 코드도 있다.

[github/kuangliu](https://github.com/kuangliu/pytorch-cifar/blob/master/models/resnet.py)

이 코드에선 `ResNet18`, `ResNet34`, `ResNet50`, `ResNet101`, `ResNet152`까지 모두 구현되어 있다.

이 코드도 모듈 분리를 잘 해두어 깔끔한 편이지만, 주석이 부족한 점이 아쉽다.
