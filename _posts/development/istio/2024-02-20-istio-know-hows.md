---
title: "Istio: 직접 경험하면서 익힌 노하우들"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: 
---

# 머릿말

<hr/>




# 만약 Istio 컴포넌트들이 혼자서만 존재한다면?

Istio에는 `VirtualService`, `DestinationRule`, `Gateway`, `ServiceEntry` 등등의 리소스가 엮여서 사용되는 경우가 정말 많다. 특히 `VS`-`DR`-`GATEWAY` 조합은 진짜 많이 사용하는 것 같다.

그런데, 각각의 리소스가 그 구성 안에서 어떤 역할을 하고, 어떤 것들을 할 수 있는지 온전히 이해하려면, 그 리소스만 단독으로 띄웠을 때 어떻게 되는지를 알아두는게 좋다고 생각한다.

