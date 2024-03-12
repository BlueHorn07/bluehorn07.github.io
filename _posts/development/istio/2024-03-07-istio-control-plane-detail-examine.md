---
title: "Istio의 istiod 꼼꼼히 살펴보기"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "Istiod 너는 뭐하는 녀석이나... 문서도 보고 커뮤니티에도 물어봐서 얻은 깨달음 💡"
last_modified_at: 2024-03-09
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

Envoy Proxy의 인증서는 수명이 24시간 뿐이고, 이것은 12시간 마다 갱신된다.

# 만약 istiod가 없어진다면??

# Legacy 컨트롤 플레인의 아키텍처 (istio 1.5 이전)

https://istio.io/latest/blog/2020/istiod/

