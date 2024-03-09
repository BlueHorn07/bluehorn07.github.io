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

## cni(Container Network Interface)

https://istio.io/latest/docs/setup/additional-setup/cni/

Pod 내부에서 workload container와 envoy proxy 사이 네트워크 설정을 도와주는 네트워크 플러그인이다.

![](https://istio.io/latest/docs/setup/additional-setup/cni/cni.svg){: .align-center style="max-width: 400px" }

요 컴포넌트를 활성화 하면, `istio-cni-node` 데몬셋 리소스가 설치 된다.

istio가 Pod에 envoy proxy를 주입할 때 그냥 넣는게 아니라 `istio-init`라는 Init Container를 먼저 주입한다. 이 Init Container는 workload container로 in/out 하는 network traffic을 envoy proxy로 리다이렉션 하도록 세팅한다.

실제로 Pod을 `describe` 해보면 아래와 같이 구성되어 있음을 볼 수 있다.

```yaml
Init Containers:
  istio-init:
    Image:         docker.io/istio/proxyv2:1.20.3
    Args: [
      # 뭔가를 하고 있다!!
      "istio-iptables",
      "-p", "15001",
      "-z", "15006",
      "-u", "1337",
      "-m", "REDIRECT",
      "-i", "*",
      "-x", "''",
      "-b", "*",
      "-d", "15090,15021,15020",
      "--log_output_level=default:info"
    ]
    State:          Terminated
      Reason:       Completed
Containers:
  helloworld:
    Image:          docker.io/istio/examples-helloworld-v1
    Port:           5000/TCP
    Ready:          True
  istio-proxy:
    Image:         docker.io/istio/proxyv2:1.20.3
    Args: [
      "proxy", "sidecar",
      "--domain", "$(POD_NAMESPACE).svc.cluster.local",
      "--proxyLogLevel=warning",
      "--proxyComponentLogLevel=misc:error",
      "--log_output_level=default:info"
    ]
```

`cni` 플러그인을 사용할 경우, 이런 `istio-init` 컨테이너를 주입하지 않고도 workload container의 트래픽이 envoy proxy로 리다이렉션 된다고 한다!! 그래서 Init Container가 쓰는 초기 CPU/Mem 리소스를 절약하고, Pod이 디플로이 되는 랜딩 타임이 줄어든다고 한다!!

그런데, istio cni plugin을 쓴다고 해서 Init Container가 완전히 없어지는 건 아니다!! envoy proxy로 트래픽 리다이렉션이 잘 설정되었는지 확인하는 `isito-validation`라는 Init Container가 대신 주입 되어 체크를 한다! 만약, validation이 실패하면 Pod이 디플로이 되지 않는다.

또, 본인의 로컬 K8s에서 `cni` 플러그인을 활성화 했을 때는 이상하게도 Pod 디플로이가 안 되는 현상을 경험 했다 🤔

![](/images/development/istio/istio-cni-need-existing-cni-plugin-question.png)

뭔가 istio `cni` plugin 말고도, 다른 CNI Plugin(ex: calico)이 설치된 상태에서 같이 써야 하는 건가 생각해서 일단 istio 커뮤니티에 질문을 올려뒀다!

## ztunnel

요건 Istio의 새로운 서비스 메쉬 패러다임인 [Ambient Mesh](https://istio.io/latest/blog/2022/introducing-ambient-mesh/)를 위해 필요한 컴포넌트다!!

![](https://istio.io/latest/blog/2022/introducing-ambient-mesh/ambient-secure-overlay.png)

Ambient Mesh를 간단하게 설명하면, 모든 Pod에 Envoy Proxy를 주입해야 했던 기존 방식에서 `ztunnel`라는 컴포넌트를 통해서 Istio의 서비스 메쉬를 구현하는 아키텍쳐이다. 즉, 이젠 Envoy Proxy가 더이상 없다는 말씀!!

`ztunnel` 컴포넌트는 모든 노드에 뜨는 데몬셋 리소스로 Ambient Mesh를 적용하기 위해 필요한 컴포넌트다.

# 컴포넌트 커스텀 하기

istio 컴포넌트를 상황과 환경에 맞게 커스텀 하여 서비스 메쉬를 더욱 유연하게 운영할 수도 있다!!

- 안정성 향상을 위해 Replica 수를 조정하거나
- 트래픽 변화에 유연하게 대응하기 위해 HPA Auto-scaling을 설정하거나
- 기본 istio-system 네임스페이스 대신 다른 네임스페이스에서 작동하도록 구성하거나
- 컴포넌트의 리소스 Req/Limit을 세밀하게 조정하여 각 서비스의 성능을 최적화하거나
- 로깅 및 모니터링 설정 해서 Istio 구성 요소와 트래픽 흐름에 상세한 정보를 얻거나

등등을 할 수 있다.

## Gateway Replica 조정

istio의 Gateway 컴포넌트는 클러스터 외부와 맞닿은 요소이기 때문에 안정성이 중요하다. 그래서 단일 Replica로 운영하는 것보다 Multiple Replica로 운영하는게 서비스 메쉬를 더 안정적으로 운영할 수 있다.

Gateway 컴포넌트의 Replica는 `k8s.replicaCount`에서 조정하면 되는데

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
```

이것과 함께 HPA auto-scaling과 겹치면 안 되어서 아래와 같이 `values` 항목에서 `autoscaleEnabled: false`로 설정해줘야 Replica 조정한게 반영 되었다.

```yaml
  values:
    gateways:
      istio-ingressgateway:
        autoscaleEnabled: false
      istio-egressgateway:
        autoscaleEnabled: false
```

# Mesh Configuration

`meshConfig` 옵션으로 istio 서비스 메쉬의 세부 구성을 조정할 수 있다!!

## Outbound Traffic Policy

서비스 메쉬 바깥으로 나가는 트래픽에 대해서 `ServiceEntry`에 등록된 도메인만 나가도록 할지 아님 등록되지 않아도 나갈 수 있게 설정하는 옵션이다. 요건 Egress Gateway를 살펴볼 때 봤다!! "[Istio: Egress Gateway](https://bluehorn07.github.io/2024/02/15/istio-egress-gateway/)"

제일 간단한 녀석인데, 기본값은 `ALLOW_ANY`이고, 만약 등록된 도메인만 허용하고 싶다면 `REGISTRY_ONLY`로 설정하면 된다!

## Root Namespace

서비스 메쉬 내의 모두 적용할 속성을 정의하는 네임스페이스이다. 이곳에 정의한 Istio 리소스는 서비스 메쉬 내의 모든 리소스에 적용된다!! 기본 값은 `global.istioNamespace`의 것을 따르는데, 따로 설정하지 않았다면 `istio-system`일 것이다.

에를 들어 이 네임스페이스에 아래와 같은 `Sidecar`를 설정 했다면, 모든 리소스는 오직 자기가 속한 네임스페이스의 안에서만 서로 통신할 수 있다.

```yaml
apiVersion: networking.istio.io/v1alpha3
kind: Sidecar
metadata:
  name: default
  namespace: istio-system
spec:
  egress:
  - hosts:
    - "./*"
    - "istio-system/*"
```

그래서 만약 `istio-system`에 `VirtualService`나 `DestinationRule` 등을 띄웠다면, 그게 서비스 메쉬의 모든 워크로드에 영향을 주기 된다. 그래서 `istio-system` 네임스페이스에 뭔가를 띄울 때는 항상 주의하자!!

# `istioctl`과 IstioOperator

## `istioctl`은 일종의 IstioOperator 컨트롤러다.

이름에서부터 "Operator"가 들어가는 `IstioOperator`는 Operator 패턴에 따라 IstioOperator 컨트롤러 `IstioOperator` 리소스의 상태를 관리하는게 자연스러운 패턴이라고 여겨진다.

그런데 `istioctl install`로 Istio 환경을 구축하면, IstioOperator 컨트롤러는 없지만 IstioOperator는 떠있다!! 어떻게 보면 `istioctl`이 IstioOperator 컨트롤러의 역할을 하는 것 같다.

그래서 처음엔 `IstioOperator` 리소스는 만들어졌는데, 그걸 관리하는 컨트롤러가 없어서 혼란스러웠다. 그래서 이런 결론을 내리게 된 것 같다.

## `istioctl`을 통해 배포된 IstioOperator 리소스 관리하기

`istioctl install`로 `istio`를 띄우면 `installed-state`라는 이름으로 `IstioOperator` 리소스가 생성된다.

요 리소스는 `istioctl`로 띄워서 그런지 IstioOperator 컨트롤러가 요 리소스를 관리하지 못 했고, 오직 `istioctl`로만 조작할 수 있었다.

그런데 방법이 없는 건 아니다!! `annotations`에 있는 `install.istio.io/ignoreReconcile`을 `false`로 바꿔주면 됐다!! 변경 후에는 별도로 띄운 컨트롤러가 해당 리소스의 변경 대로 istio 구성을 반영하기 시작 했다 😁
