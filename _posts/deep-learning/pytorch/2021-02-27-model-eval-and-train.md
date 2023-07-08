---
title: "[PyTorch] `model.eval()` 의미"
toc: true
toc_sticky: true
categories: ["PyTorch"]
---

<br/>

딥러닝 모델의 코드를 살펴보다 보면 Evaluation 부분에서 꼭 이런 코드가 등장한다.

``` py
def evaluation(model, criterion, ...):
    model.eval()
    criterion.eval()
    ...
```

무슨 의미인지 궁금해서 찾아보니, `nn.Module`에서 train time과 eval time에서 수행하는 다른 작업을 수행할 수 있도록 switching 하는 함수라고 한다. [stackoverflow](https://stackoverflow.com/a/60018731)

train time과 eval time에서 다르게 동작해야 하는 대표적인 예들은

- `Dropout` Layer
- `BatchNorm` Layer

등등이 있다고 한다.

`.eval()` 함수는 evaluation 과정에서 사용하지 않아야 하는 layer들을 알아서 off 시키도록 하는 함수인 셈이다.

evaluation/validation 과정에선 보통 `model.eval()`과 `torch.no_grad()`를 함께 사용한다고 한다.

``` py
# evaluate model:
model.eval()

with torch.no_grad():
    ...
    out_data = model(data)
    ...
```

eval/val 작업이 끝난 후에는 잊지말고 train mode로 모델을 변경해줘야 한다. 이것은 `.train()` 함수를 실행시키면 된다.

``` py
# after eval/val, and in training step
model.train()
```

<br/>

PyTorch 공식 문서에서 `.eval()`에 대한 자세한 내용을 확인할 수 있다. [nn.Module.eval()](https://pytorch.org/docs/stable/generated/torch.nn.Module.html#torch.nn.Module.eval)

