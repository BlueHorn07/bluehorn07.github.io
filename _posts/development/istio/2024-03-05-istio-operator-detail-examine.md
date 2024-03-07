---
title: "Istio Operator 꼼꼼히 살펴보기"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "Istio Operator 문서에서 있는 대부분의 것들을 찾아본 후기 🔥"
last_modified_at: 2024-03-06
---

![](/images/meme/after-the-test-its-heaven.png){: .align-center style="max-width: 300px;" }
내가 왜 ICA 시험을 신청 했을까...
{: .small .text-center .gray }

`IstioOperator` 리소스는 클러스터에 구성한 istio 서비스 메쉬의 명세가 되는 리소스다. 늘 헷갈리는 거지만, istio-operator controller가 따로 있고, `IstioOperator`가 따로 있다. istio-op controller가 `IstioOperator` 리소스를 모니터링 하면서 Istio 서비스 메쉬에 반영하는 것이다.

암튼 `IstioOperator` 리소스는 istio 서비스 메쉬의 기반이 되는 리소스다. 왠지 시험 때 이것저것 시킬 것 같아서 완전 꼼꼼히 살펴보고 정리해봤다. (꼼꼼의 ISTJ)

# `istioctl`로 띄운 `IstioOperator`를 yaml로 관리하기

K8s 클러스터에 Istio 서비스 메쉬를 구성하는 방법이 여러가지가 있지만! (`istioctl`, helm chart, istio-operator로 설치할 수 있다!! 자세한 건 [정리 해둔 이 글](https://bluehorn07.github.io/2024/02/02/install-istio-and-addons/)을 참고)

본인은 보통 `helmfile.yaml` 만들고 하기 귀찮아서 그냥 `istioctl install`로 설치하고 `istioctl uninstall --purge`로 없앤다 ㅋㅋㅋ (로컬이니까~~)

```bash
$ istioctl instal
...
# io = istiooperator
$ kubectl get io -n istio-system
NAME              REVISION   STATUS   AGE
installed-state                       47h
```

그런데 `IstioOperator`도 이것저것 커스텀 해서 띄울 수 있다는 사실!!! 제공되는 Install Profile들만으로는 커스텀에 한계가 있었다.

물론 `--set` 파라미터로 커스텀 할 수 있지만... 커스텀 해서 테스트 해볼게 한두 가지가 아닌 걸...

```bash
$ istioctl install \
  --set "components.egressGateways[0].enabled=true" \
  --set "components.egressGateways[0].name=istio-egressgateway"
```

그런데 `istioctl`도 yaml 파일을 `-f` 옵션으로 입력 받을 수 있다!!!

그런데 주의할 점이 있는데, `metadata.name` 부분 없이 yaml을 만들어줘야 한다!!

```yaml
# custom-istio-op.yaml
apiVersion: install.istio.io/v1alpha1
kind: IstioOperator
# metadata.name가 없어야 한다!!
# metadata: ...
spec:
  profile: default
  components:
    egressGateways:
    - name: istio-egressgateway
      enabled: true
```

이렇게 말이다!! 이렇게 안 하면 `istioctl install -f custom-istio-op.yaml` 할 때 그 이름으로 또 다른 `IstioOperator`가 생겨버렸다!!

```bash
$ istioctl install -f custom-istio-op-with-metadata-name.yaml
$ k get io -n istio-system
NAME                          REVISION   STATUS   AGE
installed-state                                   47h
installed-state-custom-name                       47h
```

이렇게 말이다!! 그래서 꼭 `metadata.name` 없이 custom yaml을 만들어야 한다.

# Components 속성 자세히 보기

자!! 이제 본격적으로 `IstioOperator`를 씹듣맛즐 해보자!!

## base

```yaml
apiVersion: install.istio.io/v1alpha1
kind: IstioOperator
spec:
  profile: default
  components:
    base:
      enabled: false # base 컴포넌트가 없으면 Istio CRD도 없다.
```

`base` 컴포넌트는 istio 서비스메쉬의 CRD 정의들을 가지고 있다. istio 서비스메쉬가 동작하기 제일 근본 컴포넌트로 K8s 클러스터에 istio 서비스메쉬를 초기에 세팅한다면 이걸 꼭 `enabled: true`로 해야 한다.

물론 helm chart로 CRD를 미리 설치 했다면 `base.enabled: false`로 설정하고 띄워도 무방할 듯? 그러나 웬만하면 이녀석은 `enabled: true`로 해두자.

## pilot

```yaml
apiVersion: install.istio.io/v1alpha1
kind: IstioOperator
spec:
  profile: default
  components:
    pilot:
      enabled: true # istiod 컴포넌트를 띄운다
```

istiod를 디플로이 하는 옵션이다. 만약 `enabled: false`라면 istiod가 뜨지 않는다!!

참고로 `pilot.enabled: false`인 상태에서 Ingress/Egress Gateway를 띄우려고 한다면 **디플로이에 실패**한다!! 🙅

istiod가 하는 일이 모든 Envoy Proxy를 Injection/Deploy 하고, 또 Istio 리소스들을 컴파일 해서 Envoy Proxy에 Config를 동기화 해주는 "컨트롤 플레인"인데, 머리가 없으니 몸통이 제대로 동작 할리가 없다.

## ingress/egress gateways

요건 Ingress/Egress Gateway를 추가로 띄워보거나 했다면 익숙할 것이다.

```yaml
apiVersion: install.istio.io/v1alpha1
kind: IstioOperator
spec:
  profile: default
  components:
    ingressGateways:
      - enabled: true
        name: istio-ingressgateway
        namespace: default
    egressGateways:
      - enabled: true
        name: istio-egressgateway
```

이리저리 커스텀 가능!! Gateway 커스텀은 뒤에서 좀더 자세히 다룰 예정이니 지금은 넘어가자!!

## cni

## ztunnel

# Multiple Replica Gateway 실험

```yaml
apiVersion: install.istio.io/v1alpha1
kind: IstioOperator
spec:
  profile: default
  components:
    ingressGateways:
    - enabled: true
      name: istio-ingressgateway
      namespace: istio-system
      k8s:
        replicaCount: 2
    egressGateways:
    - enabled: true
      name: istio-egressgateway
      namespace: istio-system
      k8s:
        replicaCount: 2
  values:
    gateways:
      istio-ingressgateway:
        autoscaleEnabled: false
      istio-egressgateway:
        autoscaleEnabled: false
```

# `istioctl` 관련

### `istioctl`은 일종의 IstioOperator-controller다

사실 `IstioOperator`를 운영하는 정석은 IstioOperator-controller를 띄워서, `IstioOperator` 리소스에 변경이 생기면 controller가 그걸 반영해주는 거라고 생각한다.

그런데 `istioctl install`로 Istio 환경을 구축하면, IstioOperator-controller가 없지만 IstioOperator는 떠있다!!

그래서 어떻게 보면 `istioctl`이 IstioOperator-controller의 역할을 하는 것 같다. 설치 할 때 `--set profile=demo` 이렇게 주면, 새로운 `IstioOperator`가 생기는게 아니라 기존에 띄웠던 `IstioOperator`에 반영이 된다.

`istioctl install`로 설치되는 `IstioOperator`에 대한 설명에 아래에서 계속...

### `istioctl`로 띄운 `IstioOperator` 리소스는 오직 `istioctl`으로만 관리 가능

`istioctl install`로 `istio`를 띄우면 기본적으로 `installed-state`라는 이름으로 `IstioOperator` 리소스가 생성된다.

요 리소스는 `istioctl`로 직접 띄운 것이기 때문에 IstioOperator-controller가 요 리소스를 관리하지 못 한다. 오직 `istioctl`로만 조작할 수 있다.

그런데 방법이 없는 건 아니다!! `annotations`에 있는 `install.istio.io/ignoreReconcile`을 `false`로 바꿔주면 된다!
