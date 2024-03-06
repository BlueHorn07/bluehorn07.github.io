---
title: "Istio Operator 꼼꼼히 살펴보기"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: "Istio Operator 문서에서 있는 대부분의 것들을 찾아본 후기"
last_modified_at: 2024-03-06
---

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