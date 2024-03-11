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

즉, istiod가 일종의 CA(Certificate Authority)의 역할을 하는 것인데, istiod가 Envoy Proxy의 인증서 발급을 위해 사용하는 인증서와 TLS 키는 `istio-system` 네임스페이스의 `istio-ca-secret`라는 K8s Secret에서 그 정보를 확인할 수 있다.

![](/images/development/istio/istiod-ca-cert-secret.png)
istiod가 사용하는 TLS 키가 요 Secret에 담겨 있다.
{: .small .text-center .gray }

![](/images/development/istio/istio-ca-root-cert-configmap.png)

![](/images/development/istio/istioctl-proxy-config-ca-cert.png)

# 만약 istiod가 없어진다면??

# Legacy 컨트롤 플레인의 아키텍처 (istio 1.5 이전)

https://istio.io/latest/blog/2020/istiod/

