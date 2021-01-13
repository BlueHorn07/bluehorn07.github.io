---
title: "Google Colab"
layout: post
tags: ["pytorch"]
use_math: true
---

### 서론
이 포스트는 제가 개인적인 용도로 정리하는 용도의 글 입니다. 

<hr>

- GPU 무료 사용 가능!
- 간단하게 생각하면, "Jupyter Notebook in Google Drive"!
- Github friendly!!
- 기존 Jupyter Notebook `.ipynb`을 그대로 colab으로 변환해 사용할 수 있는가?
- 거의 대부분의 python 라이브러리가 설치 되어 있음!!
- **90분간** 미사용시 중지
- **최대 12시간** 연속 사용 가능 <small>(그러니 12시간 이상 걸리는 작업을 돌리기는 힘들다는 말!)</small>

만약 직접 설치되지 않은 라이브러리를 써야 한다면,

```
!pip install [package]
```

로 직접 설치할 수 있음!!

### GPU 연결

`수정(Edit)` → `노트 설정(note setting)` → 여기에서 GPU 설정!

#### Colab의 사용량 한도란 무엇인가요?

<div class="statement" markdown="1">
Colab에서는 보장된 리소스 또는 무제한 리소스를 제공하는 대신 경우에 따라 사용량 한도를 동적으로 변경함으로써 무료 리소스를 제공할 수 있습니다. 즉, 전체 사용량 한도, 유휴 시간 제한 기간, 최대 VM 수명, 사용 가능한 GPU 유형 등의 요소가 시간에 따라 달라집니다. Colab은 이러한 한도를 공개하지 않는데, 그 이유 중 하나는 한도가 빠르게 바뀔 수 있으며 실제로도 빠르게 바뀌는 경우가 있기 때문입니다.
</div>


