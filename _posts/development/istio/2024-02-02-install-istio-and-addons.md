---
title: "Install Istio and Addons(Prometheus, Kiali)"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: 우당탕탕! Istio, 너 손이 많이 가는 녀석이구나!?
---

![](/images/meme/i-must-study.jpeg){: .align-center style="max-width: 300px" }

24년의 첫 자격증 공부로 Istio를 시작하고 무한 고통 받고 있는 나 자신... Udemy 강의는 쭉 다 들었고, 이젠 로컬 맥북에 Istio를 구축해서 이리저리 핸즈온을 해보고 있다.

이번 포스트는 Istio를 설치하면서 고군분투한 나의 Istio 배포 경험과 비법(?)을 정리해보고자 한다.

# `istioctl` vs. Istio Operator vs. Istio helm chart

Istio는 정말 친절(?)하게도 설치 방법을 3가지나 제공한다 (우와 고마워라 ಠ_ಠ)

하아... 일단 여기서부터 진짜 막막 했는데, 일단 본인이 체감한 설치 난이도 순서는

- (쉬움)
- `istioctl`
- Istio helm chart
- Istio Operator
- (어려움)

Istio Operator는 K8s Operator 패턴에 익숙하지 않다면, 일단 모른척 하고 넘어가자! (그런데, kiali 설치할 때 또 나온다 O=('-'Q))

## `istioctl`로 설치하기

제일 간단한 방법이다! 일단 `istioctl`부터 깔아준다.

```bash
brew install istioctl
```

설치 후엔 `istioctl version`으로 잘 설치되었는지 체크한다.

그 다음은 간단하다. `istio-system` namespace를 만들고, `istioctl install`로 설치하면 끝! 🤙

```bash
$ kubectl get pods -n istio-system

```

Istio는 설치할 때 제공하는 몇가지 구성이 있는데, 옵션을 안 주면 `default`로 설치되고, `istiod`와 기본 `istio-ingressgateway`가 설치된다.

그외에 `demo`, `minimal`도 있는데 필요에 따라서 설치하길

![](/images/istio/istioctl-install-profile.png){: .full }
[Istio: Installation Configuration Profiles](https://istio.io/latest/docs/setup/additional-setup/config-profiles/)

## Istio helm chart

이번에는 istio를 helm chart로 배포하는 방식이다. `istioctl`이 편하긴 하지만, GitOps가 안 되는게 함정 (╥﹏╥)

만약 istio 설치 버전을 yaml 형식의 파일로 버저닝 해야 한다면, helm이 좋다.

본인은 `helmfile`도 함께 써서 구성해서 istio를 설치 했는데, 완성된 `helmfile.yaml` 파일은 아래와 같다.

```yaml
repositories:
  - name: istio
    url: https://istio-release.storage.googleapis.com/charts

releases:
  - name: istio-base
    namespace: istio-system
    chart: istio/base
    version: 1.20.2
    values: []

  - name: istio-istiod
    namespace: istio-system
    chart: istio/istiod
    version: 1.20.2
    needs: [istio-system/istio-base]
    values: []

  - name: istio-gateway
    namespace: istio-system
    chart: istio/gateway
    version: 1.20.2
    needs: [istio-system/istio-istiod]
    values: []
```

istio helm chart에서 제공하는 value 파일도 읽어봤는데, 딱히 커스텀 할 부분 없이 바로 쓰면 된다!

`istioctl`의 `"default"` 구성과 동일하게 설치하려면 저렇게 `istio-base`, `istio-istiod`, `istio-gateway` 3개를 설치해주면 된다.

- `istio-base`는 istio의 CRD가 모여 있는 helm chart이다.
- `istio-istiod`를 설치해야 `istiod`가 디플로이 된다.
- `istio-gateway`도 설치하면, 기본 ingress gateway까지 디플로이!

이렇게 해놓고 다 쓰면 `helmfile destroy`로 깔끔하게 날렸다 ㅎㅎ

https://istio.io/latest/docs/setup/install/helm/

## Istio Operator로 설치하기

요건 K8s의 Operator 패턴으로 Istio를 관리하는 방법이다. 일단 Operator 패턴이 뭔지 잘 모른다면 패스하는 걸 추천한다... (그 다음엔 Addon인 Prometheus & Kiali 설치가 기다리고 있다! [건너뛰기]())

일단 Istio Operator를 띄워야 하는데, `istioctl`과 helm chart로 띄우는 방법이 있다.

```bash
istioctl operator init
```

```bash
helm install istio-operator \
    manifests/charts/istio-operator \
    -n istio-operator
```

요렇게 `istio-operator`라는 namespace에 Istio Operator를 띄워두면, 이제 `IstioOperator` 리소스를 띄울 수 있다! (이름 헷갈리니 주의!)

```bash
kubectl apply -f - <<EOF
apiVersion: install.istio.io/v1alpha1
kind: IstioOperator
metadata:
  namespace: istio-system
  name: example-istiocontrolplane
spec:
  profile: demo
EOF
```

TODO: 요거 설치 후의 istio-opreator 쪽에 pod 새로 생긴거 있는지 체크해서 첨부 필요


<hr/>

# Addon 설치: Prometheus & Kiali

![](/images/meme/i-do-it.jpeg){: .align-center style="max-width: 500px" }

휴우... 겨우겨우 Istio를 클러스터에 띄웠다. Istio 이 녀석 보통 내기가 아니었다... 이제 istio를 운영하는데 필요한 addon을 설치해보자! 여러 addon이 있지만, 필수적인 건 Prometheus와 Kiali 둘이다.

## 가장 간단한 방법

Istio에선 너무나도 고맙게도 Istio Addon을 띄우기 위한 yaml 파일을 Github에 올려뒀다!! [source <i class="fab fa-fw fa-github" aria-hidden="true"></i>](https://github.com/istio/istio/tree/master/samples/addons) 그래서 요기에 있는 addon 파일을 그냥 `k apply -f` 하면 된다.

```bash
export PROMETHEUS_ADDON=https://raw.githubusercontent.com/istio/istio/release-1.20/samples/addons/prometheus.yaml
kubectl apply -f $PROMETHEUS_ADDON -n istio-system

export KIALI_ADDON=https://raw.githubusercontent.com/istio/istio/1.20.2/samples/addons/kiali.yaml
kubectl apply -f $KIALI_ADDON -n istio-system
```

꼭 `istio-system` namespace에 설치해야 한다!
