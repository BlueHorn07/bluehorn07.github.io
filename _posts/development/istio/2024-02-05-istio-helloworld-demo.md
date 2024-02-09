---
title: "Istio 'helloworld' 데모"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: Istio 첫걸음. Hello Istio! 그리고 Vritual Service, Destination Rule도 안녕!
---

![](/images/meme/hell-kitty.JPG){: .fill }

이번 포스트에서는 Istio에서 제공하는 [helloworld 예제](https://github.com/istio/istio/tree/master/samples/helloworld)를 활용해 Istio의 Virtual Service와 Destination Rule의 기능들을 직접 실험해본다. 🧪

# 사전 준비

일단 hello 예제를 띄울 네임스페이스에 label을 부여해서 envoy sidecar가 붙을 수 있도록 만들자.

```bash
kubectl label ns default istio-injection=enabled
```

그리고 앞선 포스트를 참고해 Istio와 Kiali, Prometheus Addon을 설치한다.

➡️ [Install Istio and Addons(Prometheus, Kiali)](https://bluehorn07.github.io/2024/02/02/install-istio-and-addons/)

