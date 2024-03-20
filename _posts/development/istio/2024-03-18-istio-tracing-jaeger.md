---
title: "Istio Tracing: Jaeger"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: ""
last_modified_at: 2024-03-16
---

![](https://upload.wikimedia.org/wikipedia/en/a/ab/J%C3%A4germeister_logo.svg){: .align-center style="max-width: 280px"}
얘거마이스터, 허브향이 나는 리큐르지만 예거밤으로 해서 먹으면 맛있다!
{: .small .text-center .gray }

# Jaeger Addon 띄우기

```bash
$ kubectl apply \
    -f https://raw.githubusercontent.com/istio/istio/release-1.21/samples/addons/jaeger.yaml
```
