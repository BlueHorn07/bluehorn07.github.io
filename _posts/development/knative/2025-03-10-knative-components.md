---
title: "Knative Components"
toc: true
toc_sticky: true
categories: ["Kubernetes", "knative"]
excerpt: ""
---

[지난 포스트](/2024/06/22/install-knative-serving/)에서는 로컬 Kubernetes 환경에서 Knative를 셋업 해보았다. 이번 포스트에서는 그때 디플로이 한 각 컴포넌트가 어떤 역할을 하는지 살펴보고자 한다.

# Architecture

![](https://knative.dev/docs/serving/images/serving-architecture.png)

Knative는 여러 컴포넌트들이 각자의 역할을 맡아 동작하는 Serverless 프레임워크이다. 그래서 각 컴포넌트가 어떤 역할을 하는지 알아두면 장애가 발생 했을 때, 쉽게 디버그 할 수 있을 것이다.

# Activator

Knative는 서버리스의 컨셉을 제대로 지원하기 위해 "scale-to-zero"를 지원 합니다. 이것은 트래픽이나 리소스 사용량이 없을 때는 워크로드를 "zero" 수준까지 꺼두는 것을 말합니다.

이렇게 scale-to-zero가 된 상태에서 첫 트래픽이 들어오면, 그 트래픽은 서비스로 전달되는 것이 아니라 Activator에게 전달 됩니다. 그러면, Activator는 해당 트래픽을 Autoscaler(뒤에 나옴)에게 전달하고, Autoscaler가 트래픽을 처리하려고 했던 Knative의 타깃 Service를 scale-up 하게 됩니다.

## 요청이 드랍 되는건 아닐까?

저는 zero-scale 상황에서 전달된 첫 트래픽이 드랍 되는게 아닐까 걱정이 되었습니다. 아무리 Serverless 앱이라고 해도, 초기 start-up에 시간이 걸리기 마련이니까요! 🤔

Knative의 Activator는 내부적으로 "큐(Queue)"를 가지고 있어서 0-to-1 상황에서 들어오는 트래픽을 일부 수용할 수 있다고 합니다!

이 "**요청 버퍼링**"은 트래픽을 처리할 수 있는 새로운 pod이 시작할 때까지 요청을 보관합니다.

## 트래픽 버스트 상황에 대응

그리고 이런 요청 버퍼링은 트래픽이 급격히 증가하는 **트래픽 버스트(traffic burst)** 상황에서도 이뤄진다고 합니다. 현재 scale로 감당하기 어려운 큰 트래픽이 순간적으로 발생하게 되면, 요청을 즉시 처리하지 못하게 될 수 있는데, 이때 해당 요청을 Activator가 버퍼링 하고, 이후 pod이 처리할 수 있도록 부하 분산을 수행한다고 합니다.

그리고 Activator는 단순한 요청 큐 역할 뿐만 아니라 추가적인 Pod이 필요하다고 판단되면, 자동으로 새로운 Pod을 생성하도록 트리거 하는 역할도 수행합니다. 그리고 이 과정에서 Knative의 Autoscaler와 협력해 scale-up을 수행 합니다.

이 값은 `target-burst-capacity`의 값으로 설정할 수 있으며 기본값은 "200" 입니다. 전역적으로 설정할 수도 있고, Revision 단위로 설정할 수도 있습니다. [[doc]](https://knative.dev/docs/serving/load-balancing/target-burst-capacity/)

# Autoscaler

Knative의 서비스 트래픽을 모니터링 하고, 필요한 경우 Pod 갯수를 자동으로 조정 합니다.

위에서 소개한 scale-to-zero를 수행하는 컴포넌트가 바로 이 녀석 입니다.

Autoscaler는 Pod의 트래픽을 계속 모니터링 하면서, scale-up과 scale-down을 수행 합니다.

저는 Autoscaler는 항상 Activator에 의해 트리거 되는 줄 알았는데, 그렇지 않고 스스로 독립적으로 auto-scaling을 수행 합니다.

## Activator와 협력

단, Autoscaler가 Activator의 도움이 필요한 때가 있습니다! 바로 0-to-1 상황으로 그동안 pod이 전혀 없어 트래픽이 모니터링 되지 않았고, 이때는 Activator의 트리거가 반드시 필요 합니다.

참고로 앞에서 살펴본 트래픽 버스트가 발생 하면, Activator는 임시 버퍼의 역할만 수행하지, Pod 확장을 직접 트리거 하는 것은 아닙니다. 트래픽 버스트를 해소하기 위한 scale-up은 Autoscaler가 수행하게 됩니다.

## Autoscaler HPA

Knative에서는 2가지의 autosacler를 지원 합니다. 하나는 아까 살펴본 트래픽 기반의 autoscaler, 그리고 하나는 Kubernetes의 HPA를 활용하는 autoscaler 입니다.

```yaml
apiVersion: serving.knative.dev/v1
kind: Service
metadata:
  name: my-service
spec:
  template:
    metadata:
      annotations:
        autoscaling.knative.dev/metric: "concurrency"  # 동시 요청 수 기반 스케일링
        autoscaling.knative.dev/target: "10"  # Pod당 최대 동시 요청 10개
        autoscaling.knative.dev/minScale: "0"  # 필요 없으면 0개로 줄이기 (Scale-to-Zero)
        autoscaling.knative.dev/maxScale: "10"  # 최대 10개까지 확장
      ---
      annotations:
        autoscaling.knative.dev/class: "hpa.autoscaling.knative.dev"  # HPA 기반 스케일링
        autoscaling.knative.dev/metric: "cpu"  # CPU 기반 스케일링
        autoscaling.knative.dev/target: "75"  # CPU 사용량 75% 도달 시 확장
        autoscaling.knative.dev/minScale: "1"  # 최소 Pod 개수 1개 유지
        autoscaling.knative.dev/maxScale: "10"  # 최대 10개까지 확장
```

autoscaler HPA는 CPU 또는 메모리 사용량을 기준으로 pod 갯수를 조정 합니다. 그리고 scale-to-zero를 지원 하지 않고, 최소 1개의 Pod이 유지 되어야 합니다.

HPA 기반의 이 autoscaler는 트래픽 기반의 autoscaler 보다 확장이 느리게 일어납니다. 그 이유는 메트릭을 수집하는 주기와 관련이 있는데요.

- Kubernetes의 `metric-server`의 지표 수집 주기
  - 기본값은 "30초" 입니다.
  - Kubernetes 기본 컴포넌트 입니다.
  - 해당 Pod에 `--metric-resolution=10s` 명령어로 수집 주기를 변경할 수 있습니다.
- HPA가 확장을 결정하는 주기
  - 기본값은 "15초" 입니다.
  - 이것은 Kubernetes HPA의 컴포넌트 입니다.
  - `metric-server`에서 CPU/메모리 사용량을 주기적으로 가져와서 확장 할지 여부를 판단 합니다.
  - `--horizontal-pod-autoscaler-sync-period=5s` 명령어로 수집 주기를 변경할 수 있습니다.

# Controller

Knative의 리소스 전반을 관리하는 컨트롤러 입니다. Knative의 Service와 Configuration, Revision 등을 관리 합니다.

ksvc에 변경이 발생한 것을 감지하고, 이를 바탕으로 새로운 Revision을 생성 및 신규 pod이 생성되도록 합니다.

# Webhook

사용자가 `kubectl create ksvc ...`를 통해 Knative 리소스를 생성하면, 이 요청은 Kubernetes API 서버로 전달 됩니다.

API 서버는 "Admission Webhook"을 호출하여 리소스를 검증 & 변환하도록 하는데요. 이때, Knative의 webhook 서비스가 이 요청을 받아 처리 합니다. 하는 역할을 2가지 인데,

- Validation Webhook
  - 리소스가 올바르게 정의 되었는지 확인 합니다.
  - 잘못된 설정이라면 리소스를 생성하지 않고 거부 합니다.
- Mutation Webhook
  - 설정이 부족하게 들어왔다면, 기본값으로 설정을 채워 줍니다.

## Kubernetes Admission Webhook

예전에 CKA 공부할 때 봤던 것 같은데, 이번데 다시 보니 새롭게 느껴졌다 ㅋㅋ

사용자가 `kubectl create/apply ...`로 리소스 생성을 Kubernetes API에 요청하면, Kubernetes의 Admission Webhook이 요청을 가로 채고, 검증과 변환 작업을 수행 합니다.

검증이 통과 하면, API 서버가 요청을 etcd에 저장하고, 해당 리소스를 실제로 생성합니다.

# 참고자료

- ['inspirit941'님의 블로그](https://blog.naver.com/inspirit941/223753897511)
