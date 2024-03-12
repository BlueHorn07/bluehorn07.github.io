---
title: "Istio의 컨트롤 플레인 꼼꼼히 살펴보기"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "Istiod 너는 뭐하는 녀석이나... 문서도 보고 커뮤니티에도 물어봐서 얻은 깨달음 💡 Galley, Citadel, Mixer가 난무하던 레거시 컨트롤 플레인 구조 파악하기 👻"
last_modified_at: 2024-03-13
---

![](/images/meme/nothing-know.png){: .align-center }

istio 서비스 메쉬는 `istiod`라는 컨트롤 플레인 컴포넌트에 의해서 제어되고 관리 된다. ICA 자격증을 공부하면서 Istio 아키텍처를 꼼꼼히 살펴 보았고, 그러면서 얻은 깨달음과 궁금증들을 이 포스트에 담아보고자 한다.

# istiod는 뭘 하는 녀석인가

정말 많은 일들을 한다!! 대충 나열을 해보자면

- Sidecar Injection
- Configuration Management
- Certificate Management
- Service Discovery

## Sidecar Injection

![](https://charlesxu.io/assets/images/istio-short/cover2.jpg)
https://charlesxu.io/istio-short/
{: .small .text-center .gray }

istio 서비스메쉬는 sidecar 기반의 서비스메쉬이다. 그래서 서비스 메쉬의 기능이 잘 동작하려면 메쉬에 존재하는 Pod들에 Envoy Proxy가 Sidecar로 붙어있어야 한다.

Envoy Proxy는 Pod이 디플로이 될 때 주입(inject) 되는데, 이걸 하는게 `isitod`다!!

## Envoy Configuration Compile

istio에는 네트워크 트래픽을 제어하기 위한 각종 리소스들이 있다: `VirtualService`, `DestinationRule`, `AuthorizationPolicy`, `Sidecar` 등등. 이런 리소스가 생성/수정/삭제 될 때마다 그걸 Envoy Proxy에서 이해할 수 있도록 Envoy 문법으로 컴파일하고 Envoy Proxy에 업데이트 하는 것도 istiod 컴포넌트의 역할이다.

## Certificate Management

![](https://tetrate.io/wp-content/uploads/2023/03/image-20-1.png){: .align-center style="max-width: 360px" }
출처: [Tetrate 블로그](https://tetrate.io/blog/how-are-certificates-managed-in-istio/)
{: .small .text-center .gray }

istio 서비스 메쉬 내에서 마이크로서비스 사이 통신을 암호화 하는 `mTLS`를 적용하기 위해선, 모든 Envoy Proxy에 고유의 인증서(cert)를 발급해줘야 한다.

각 Envoy Proxy가 가진 TLS 공개/비밀키을 인증하는 것도 istiod가 하는 일이다!!

### Root CA 인증서 확인하기

즉, istiod가 일종의 CA(Certificate Authority)의 역할을 하는 것인데, istiod가 Envoy Proxy의 인증서 발급을 위해 사용하는 인증서와 TLS 키는 `istio-system` 네임스페이스의 `istio-ca-secret`라는 K8s Secret에서 그 정보를 확인할 수 있다.

![](/images/development/istio/istiod-ca-cert-secret.png)
istiod가 사용하는 TLS 키가 요 Secret에 담겨 있다.
{: .small .text-center .gray }

사실 `ca-cert.pem`과 `root-cert.pem`은 같은 값이다. 실제로 아래 명령어로 둘을 비교해보면 동일하다는 걸 알 수 있다!

```bash
kubectl get secret istio-ca-secret -n istio-system -o json | jq '.data."ca-cert.pem"' -r | base64 -d | openssl x509 -noout -text
kubectl get secret istio-ca-secret -n istio-system -o json | jq '.data."root-cert.pem"' -r | base64 -d | openssl x509 -noout -text
```

그리고 istio는 모든 K8s 네임스페이스에 `istio-ca-root-cert`라는 ConfigMap을 배포하는데, 여기엔 CA(Certificate Authority)인 istiod의 인증서가 담겨 있다.

![](/images/development/istio/istio-ca-root-cert-configmap.png)

요 인증서 정보는 아래 명령어로 확인할 수 있는데, 아까 살펴본 `istio-system` 네임스페이스의 Secret인 `istio-ca-cert`의 `ca-cert.pem`, `root-cert.pem`과 값이 동일하다!!

```bash
kubectl get cm istio-ca-root-cert -o json | jq '.data."root-cert.pem"' -r | openssl x509 -noout -text
```

그 이유는 mTLS 과정에서 client-server 둘다 가진이 가진 인증서가 유효하다는 것을 증명할 때, configmap에 저장된 root CA를 가지고 검증하기 때문이다!!

### Envoy Proxy의 인증서 확인하기

각 Envoy Proxy가 가진 인증서에 대한 정보는 `istioctl proxy-config secret <pod-name>`으로 확인할 수 있다.

![](/images/development/istio/istioctl-proxy-config-ca-cert.png)

캡쳐를 보면, `default`가 Envoy Proxy가 가지는 인증서이고, `ROOTCA`가 istiod의 인증서이다.

Envoy Proxy의 인증서는 수명이 24시간 뿐이지만, 12시간 마다 갱신된다.

# 만약 istiod가 없어진다면??

## 일단 istio 설치는 제대로 안 됩니다.

"[Istio Operator 꼼꼼히 살펴보기](https://bluehorn07.github.io/2024/03/05/istio-operator-detail-examine/)" 포스트에서도 확인 했지만, `components.pilot.enabled: false`로 설정하고 `istioctl install`을 실행하면 ingress/egress gateway가 뜨지 않고 멈춰버린다!!

## istiod가 있던 상태에서 istiod가 없어진다면?

아래 커맨드로 잘 떠있는 `istiod` 디플로이를 날려보자.

```bash
$ kubectl delete deploy istiod -n istio-system
```

일단 Envoy Sidecar가 붙어있던 기존 Pod들은 아무 영향 없이 잘 떠있다. 또, Envoy Sidecar가 있던 Pod들에 통신도 잘 된다!!

그런데 문제는 istiod가 없어진 이후에 Pod이 디플로이가 되면 발생한다.

일단 `istio-injection=enabled` label이 붙은 네임스페이스에는 신규 Pod이 뜨지 않는다!! 이것은 `istio-injection`을 수행할 istiod가 없기 때문에 발생하는 문제다!!

또, Envoy Sidecar가 붙었던 워크로드는 istiod가 없어지면서 Service Discovery 기능을 상실하게 된다. 그래서 새로운 Service를 띄워서 엔드포인트가 추가되어 아래와 같은 에러와 함께 통신이 불가능하다.

```
upstream connect error or disconnect/reset before headers. retried and the latest reset reason: remote connection failure, transport failure reason: delayed connect error: 113
```

단, Envoy Sidecar가 없는 non-istio 워크로드에서는 정상적으로 접근한다!

암튼 결론은 istiod가 없어지면 대재앙이라는거...

# Legacy 컨트롤 플레인의 아키텍처 (istio 1.5 이전)

![](/images/development/istio/istio-old-architecture.png)

istio가 처음 출시 되었을 때만 해도 istio의 구조는 지금과는 많이 달랐다. 특히 컨트롤 플레인이 많이 다른데, 지금은 `istiod` 하나만 있는 모노리틱 컴포넌트지만, 1.5 이전 버전에서는 "pilot", "galley", "citadel", "mixer"로 구성된 MSA 구조 였다. (참고로 istio 1.5 버전은 2020년 3월에 발표 되었다.)

istio가 컨트롤 플레인을 MSA 구조에서 `istiod` 단일 구조로 전환한 것에 대한 이야기는 istio의 공식 블로그에 자세히 담겨 있다.

➡️ [Introducing istiod: simplifying the control plane](https://istio.io/latest/blog/2020/istiod/)

istio의 엣날 구조를 알아보는게 무슨 필요가 있을까 싶긴 하지만 istio를 공부하다보면 옛날 구조에 대한 용어도 꽤 나오기에 이 문단에 정말 간단하게 훑고 가보고자 한다.

## Pilot

> Traffic Management, and Service Discovery

지금도 그렇고 예전에도 그렇고 istio에서 가장 핵심이 되는 컴포넌트다.

일단 istio의 각종 Traffic Managemenet API(`VirtualService`, `DestinationRule`, `Gateways`, `ServiceEntry`, `Sidecar`)가 동작 할 수 있도록 Envoy에 주입하는 역할을 pilot이 한다!!

게다가 Service Discovery 역할도 수행하는데, K8s 클러스터와 istio 서비스 메쉬에 새로운 인스턴스가 디플로이 되면, 해당 정보를 반영한 새로운 Envoy 규칙을 각 Envoy Poroxy에 전파한다.

## Galley

> Configuration Management

`VirtualService`, `DestinationRule` 같이 K8s 리소스로 정의한 것들을 읽어서 검증하고, Envoy Proxy가 이해 가능한 형태로 변환하는 역할을 수행한다.

Pilot이랑 다루는 리소스가 겹쳐서 좀 헷갈리긴 하는데, 일단 Pilot은 라우팅과 트래픽 정책을 Envoy에 전파하고 적용하는 역할을 담당한다. 반면, Galley는 구성을 검증하고 중앙에서 관리하는 역할에 집중한다.

참고로 Galley는 "비행기나 선박에 승객에게 식사나 음료 서비스를 위한 기내 공간"을 말한다.

## Citadel

> Certificate Generation

이 녀석이 하던 일은 명확하다. istio 워크로드 사이에 mTLS 통신을 세팅 하기 위해서 Certificate를 싸인 하고 주입하는 것이다.

참고로 Citadel은 "성채"라는 뜻이다.

## Mixer

![](https://istio.io/v1.4/docs/reference/config/policy-and-telemetry/mixer-overview/topology-without-cache.svg){: .align-center style="max-width: 520px"}
[istio 1.4: Mixer Configuration Model](https://istio.io/v1.4/docs/reference/config/policy-and-telemetry/mixer-overview/#configuration-model)
{: .small .text-center .gray }

> Collect Telemetry, and Access Control

이 녀석도 이해 하는게 만만치 않았다. 일단 Mixer는 두가지 기능을 수행한다: "Telemtry"와 "Policy Check"다.

Telemtry 역할은 Envoy가 보고하는 metric과 log 정보를 모아서 처리한다. 이는 Istio의 Tracing & Audit과 관련 있다. 또한, Mixer가 모은 지표는 Prometheus나 Datadog과 같은 외부 백엔드로 보낼 수도 있다. 또, 다수의 외부 백엔드를 두는 것도 가능하다. (여러 외부 백엔드를 지원하기 위해 "Adaptor Pattern"을 채택 한 것!)

Access Control 역할은 `AuthorizationPolicy`를 평가해 두 워크로드가 통신 가능한지 여부를 Envoy에 리턴 해주는 것을 말한다.

그외에도 서비스의 총 처리량을 정책으로 지정해 그 처리량 이상으로 요청을 못받게 하거는 것도 가능하다.

### Adaptor Pattern

![](https://refactoring.guru/images/patterns/diagrams/adapter/solution-en-2x.png?id=5846ed9b88cad0220993f79bdfe817a8){: .align-center style="max-width: 400px"}
[Design Guru: Adapter](https://refactoring.guru/design-patterns/adapter)
{: .small .text-center .gray }

디자인 패턴 중에 하나인 어댑터 패턴은 제공 되는 데이터를 다른 백엔드에서 처리할 수 있도록 그쪽 백엔드의 형식으로 변환해주는 패턴을 말한다. Prometheus의 "[Exporter](https://prometheus.io/docs/instrumenting/exporters/)"도 Prometheus 지표 형식으로 변환해주는 어댑터의 예시인 것 같다.

Mixer는 어댑터 패턴을 채용해 정말 다양한 외부 백엔드에 데이터를 쏠 수 있었다.

- Metric
  - Datadog
  - CloudWatch
  - Prometheus
  - StatsD
  - Stackdriver
- Logging
  - Fluentd
  - Stackdriver
- Trace Span
  - Zipkin
  - Stackdriver
- Authorization
  - Any OIDC identity providers
- Quota
  - Redis Quota
  - Memory Quota

어후 이렇게 보니까 istiod 단일 구조로 간게 진짜 다행일 정도... 특히 Mixer는 진짜 🐶같다

# 참고자료

- [istio 1.4: Architecture](https://istio.io/v1.4/docs/ops/deployment/architecture/)
  - istio 1.5 직전의 구조에 대한 문서라 그런지 각 컴포넌트에 대한 설명이 정말 자세하다. 정말 많은 도움이 되었다!! 👍
- [Introducing istiod: simplifying the control plane](https://istio.io/latest/blog/2020/istiod/)
  - istio가 왜 MSA 구조에서 단일 구조로 전환 했는지, 늘 궁금했는데 요 블로그 포스트에 잘 정리 되어 있다 ㅎㅎ
- [Istio Service Mesh Workshop](https://www.istioworkshop.io/03-servicemesh-overview/istio-architecture/)
