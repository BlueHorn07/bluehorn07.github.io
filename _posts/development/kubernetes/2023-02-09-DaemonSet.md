---
title: "DaemonSet"
layout: post
tags: ["develop", "kubernetes"]
---

K8s 클러스터의 노드 전체에 Pod을 띄울 때 사용하는 "컨트롤러"다.

컨트롤러는 K8s의 기본 오브젝트(Pod)을 생성하고, 이를 관리하는 역할을 한다. ReplicaSet, Deployment, DaemonSet이 컨트롤러 리소스에 속한다.

DaemonSet은 모든 노드에 Pod을 띄워준다. 그래서 로깅(Logging)이나 K8s 네트워크 관련 Pod이 DaemonSet으로 관리된다.

# DaemonSet 생성

```yaml
apiVersion: apps/v1
kind: DaemonSet
metadata:
  name: my-daemonset
  namespace: kube-system
spec:
  selector:
    matchLabels:
      name: my-daemon
  updateStrategy:
    type: RollingUpdate
  template:
    ...
```

# 데몬셋 예시

## CNI Network Plugin

https://github.com/weaveworks/weave/releases/download/v2.8.1/weave-daemonset-k8s.yaml

Network Policy 리소스 등을 기능을 제공하는 Network Plugin은 DaemonSet 리소스로 운영한다. weave net, calico, flannel 등이 CNI Network Plugin이다.

## 모니터링 도구

https://www.datadoghq.com/blog/monitoring-kubernetes-with-datadog/

Datadog과 같은 인프라 모니터링 도구 역시 DaemonSet 리소스로 운영한다.









