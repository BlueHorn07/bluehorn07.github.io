---
title: "CenterNet 실습 (Windows)"
toc: true
toc_sticky: true
categories: ["Computer Vision"]
---



이 글은 제가 [『CenterNet: Objects as Points』](https://github.com/xingyizhou/CenterNet)의 코드를 직접 실습해본 과정을 기록한 포스트 입니다.

실행 환경은 아래와 같습니다.

``` bash
(2021.06.03)
- Windows 🔥
- Python: 3.9.2
- PyTorch: 1.8.0
- CUDA: 10.2
- VS2019
- CenterNet last commit: 2020.06.21
```

GitHub에 게시된 \<CenterNet\>의 [코드](https://github.com/xingyizhou/CenterNet)의 튜토리얼을 **Windows** 환경에서 그대로 실행하면, 아래와 같은 오류를 얻게 됩니다.

``` bash
ModuleNotFoundError: No module named '_ext'
```

이 포스트는 Windows 환경에서 \<CenterNet\>을 돌리고자 하는 분들을 위한 포스트 입니다! 가이드를 따라하기 전, 본인의 실행 환경을 **반.드.시.** 체크해주세요! <small>(제 방법도 시간이 지나면, 정상적으로 동작하지 않을 수도 있습니다 ㅠㅠ)</small>

<hr/>

## Start CenterNet on Windows

가장 먼저 시도해볼 것은 \<CenterNet\>에서 제시하는 `Installation` 문서를 그대로 따라해보는 것 입니다.

👉 [CenterNet/readme/INSTALL.md](https://github.com/xingyizhou/CenterNet/blob/master/readme/INSTALL.md)

이 과정을 그대로 진행했음에도 `demo.py` 실행에 실패했다면, \<CenterNet\>에 Issue로 올라온 ['ausk'님의 이슈](https://github.com/xingyizhou/CenterNet/issues/7)를 따라합니다.

👉 [CenterNet/Issue - CenterNet works ok on Pytorch 1.1 + Cuda10.1 + Win10](https://github.com/xingyizhou/CenterNet/issues/7)

'ausk'님의 이슈를 그대로 진행했음에도 실패했다면, 제가 성공한 방식으로 진행해보세요!

💥 저는 튜토리얼 작성을 위해 GitHub에서 \<CenterNet\> 레포를 다시 다운 받아서 진행했습니다!

💥 Python Interpreter 설정과 `requirements.txt` 설치는 모두 완료되었다고 가정합니다.

### 1. Build nms

`cd src/lib/external/setup.py` 파일에서 `"-Wno-cpp"` 부분을 아래와 같이 주석처리

``` py
extensions = [
    Extension(
        "nms",
        ["nms.pyx"],
        # extra_compile_args=["-Wno-cpp", "-Wno-unused-function"]
    )
]
```

수정 후, 아래의 명령어 실행

``` bash
cd src/lib/external
python setup.py build_ext --inplace

## output
nms.c(7997): warning C4244: '=': 'double'에서 'float'(으)로 변환하면서 데이터가 손실될 수 있습니다.
nms.c(8016): warning C4244: '=': 'double'에서 'float'(으)로 변환하면서 데이터가 손실될 수 있습니다.
nms.c(8059): warning C4244: '=': 'double'에서 'float'(으)로 변환하면서 데이터가 손실될 수 있습니다.
nms.c(8192): warning C4244: '함수': 'double'에서 'float'(으)로 변환하면서 데이터가 손실될 수 있습니다.
...
...
   build\temp.win-amd64-3.9\Release\nms.cp39-win_amd64.lib 라이브러리 및 build\temp.win-amd64-3.9\Release\nms.cp39-win_amd64.exp 개체를 생성하고 있습니다.
코드를 생성하고 있습니다.
코드를 생성했습니다.
```

실행 후, 위와 같이 출력되면 정상적으로 완료된 것. (warning은 무시해도 된다.)

### 2. Clone and Build DCNv2

여기에서 ['ausk'님의 이슈](https://github.com/xingyizhou/CenterNet/issues/7)와 다르게 진행한다.

먼저, 기존 \<CenterNet\>의 `src/lib/models/networks`의 `DCNv2` 폴더를 삭제한다. \<CeterNet\>dms `PyTorch 0.4.1`이라는 아주 구시대 버전의 PyTorch를 쓰고 있고, CenterNet의 레포에는 이것에 맞춘 DCNv2 모델이 들어있다. 그래서 CenterNet 레포의 DCNv2는 `PyTorch 1.x`대와는 전혀 호환 되지 않는다.

['ausk'님의 이슈](https://github.com/xingyizhou/CenterNet/issues/7)에서는 DCNv2의 [origianl repo](https://github.com/CharlesShang/DCNv2)를 다운 받으라고 하지만, 이것 역시 본인은 동작하지 않았다. 대신 아래의 DCNv2 레포를 다운 받아서 기존 DCNv2의 폴더에 넣도록 하자!

👉 [jinfagang/DCNv2_latest](https://github.com/jinfagang/DCNv2_latest) <span style="color: grey"><small>// compatible with PyTorch 1.8+</small></span>

다운 & unzip 후, 경로로 들어가서 아래의 명령어를 실행한다.

``` bash
cd src/lib/models/networks/DCNv2
python setup.py build develop

## output
...
...
코드를 생성하고 있습니다.
코드를 생성했습니다.
copying build\lib.win-amd64-3.9\_ext.cp39-win_amd64.pyd ->
Creating c:\users\hsy4462\miniconda3\envs\pytorch_latest\lib\site-packages\DCNv2.egg-link (link to .)
Removing DCNv2 0.1 from easy-install.pth file
Adding DCNv2 0.1 to easy-install.pth file

Installed c:\users\hsy4462\documents\github\centernet-master\src\lib\models\networks\dcnv2
Processing dependencies for DCNv2==0.1
Finished processing dependencies for DCNv2==0.1
```

위와 같은 출력 결과와 `DCNv2/` 폴더에 `build/`, `DCNv2.egg-info/`, `_ext.cp39-win_amd64.pyd` 등이 생성되었다면, 성공이다!

`DCNv2/` 폴더에 있는 `testcpu.py`, `testcuda.py`를 실행시켜도 온전히 동작하지는 않을 것이다. 그래도 괜찮다. 다음 단계로 넘어가자.

### 3. Run Demo

이제 \<CenterNet\>의 `demo.py`를 실행시켜 잘 동작하는지 확인해보자.

먼저 동작에 필요한 모델을 다운 받는다. CenterNet GitHub의 [MODEL ZOO](https://github.com/xingyizhou/CenterNet/blob/master/readme/MODEL_ZOO.md)에서 다운 받을 수 있다. `ctdet_coco_dla_2x.pth`를 다운받아서 `models/` 폴더에 넣어주자.

이제 아래의 코드를 실행해보자!

``` py
cd src
python demo.py ctdet --demo ../images/17790319373_bd19b24cfc_k.jpg --load_model ../models/ctdet_coco_dla_2x.pth --debug 2
```

정상적으로 실행되면, 아래의 사진들이 나올 것이다!!

<div>
  <div class="img-wrapper" style="display:flex; justify-content:center; align-items:center;">
    <img src="{{ "/images/computer-science/computer-vision/centernet-demo-1.png" | relative_url }}" style="float:left; width:48%;">
    <img src="{{ "/images/computer-science/computer-vision/centernet-demo-2.png" | relative_url }}" style="float:left; width:48%;">
  </div>
  <div class="img-wrapper">
    <img src="{{ "/images/computer-science/computer-vision/centernet-demo-3.png" | relative_url }}">
  </div>
</div>


<div align="center" style="font-size: 3rem">

끄-읕!!

</div>