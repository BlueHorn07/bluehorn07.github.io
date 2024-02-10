---
title: "Istio 'Bookinfo' 데모"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: Istio의 공식 예제 따라서 실습해보기.
---

![](/images/meme/evangelion-sinji-do-that.png){: .fill .align-center style="width: 400px" }

이번 포스트에선 Istio 공식 문서로 나와있는 [Bookinfo 예제](https://istio.io/latest/docs/examples/bookinfo/)를 따라서 실습 해보고, 그 과정을 정리해보고자 한다. 📚

일단 book info 예제의 전체 구조는 요렇다.

![](https://istio.io/latest/docs/examples/bookinfo/withistio.svg)

# 일단 띄우고 보자

```bash
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/bookinfo/platform/kube/bookinfo.yaml
```

![](/images/development/istio/bookinfo-kgp.png){: .fill }

뭔가가 잔뜩 떴다!

Bookinfo는 일종의 웹 앱이다. 그래서 외부에서 접근할 수 있도록 `IngressGateway` 리소스와 물려보자.

```bash
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/bookinfo/networking/bookinfo-gateway.yaml
```

이제 default ingress gateway의 K8s Service로 접근할 수 있는 External IP로 접근해보자.

![](/images/development/istio/bookinfo-mainpage.png){: .fill }
