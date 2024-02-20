---
title: "Istio: Egress Gateway"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: Istio로 Service Mesh를 나가는 트래픽도 모니터링 하기 💂
---

![](/images/meme/let-me-out.png){: .fill .align-center style="max-width: 400px"}

어림없지! Istio를 쓰면 서비스 메쉬를 나가는 트래픽도 모니터링 한다!!
{: .gray .small .text-center }

<br/>

# Egress Gateway란?

![](/images/development/istio/istio-network-architecture.png){: .fill }

Istio로 서비스 메쉬를 구성하면, 클러스터 **바깥으로 나가는 트래픽** 흐름도 모니터링 할 수 있다!! 그 과정에서 사용하는게 egress-gateway 컴포넌트와 istio의 `ServiceEntry` 리소스다!

# 일단 데모부터 해보죠!!

이번 예제는 Istio 공식 문서의 "[Egress Gateway](https://istio.io/latest/docs/tasks/traffic-management/egress/egress-gateway/)" 문서를 바탕으로 실험해보았다.

## egress-gateway 컴포넌트 뛰우기

`istioctl`에서 `"demo"` Profile로 istio를 설치했다면, egress-gateway 컴포넌트가 없는 상태일 것이다. 그래서 아래 명령어로 직접 egress-gateway 컴포넌트를 뛰워줘야 한다.

```bash
$ istioctl install --set=spec.components.egressGateways[0].name="istio-egressgateway" --set=spec.components.egressGateways[0].enabled="true" 
```

https://istio.io/latest/docs/reference/config/networking/service-entry/

istio egress gateway와 연관지어서 설명 필요

