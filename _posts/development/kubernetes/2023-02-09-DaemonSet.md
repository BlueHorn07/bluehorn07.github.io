---
title: "DaemonSet"
toc: true
toc_sticky: true
categories: ["Develop", "Kubernetes"]
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

(DaemonSet에 대한 내용은 아니지만) Service 검색 애드온인 "coreDNS"가 동작하려면 이런 네트워크 플러그인이 설치되어 있어야 한다.


## 모니터링 도구

https://www.datadoghq.com/blog/monitoring-kubernetes-with-datadog/

Datadog과 같은 인프라 모니터링 도구 역시 DaemonSet 리소스로 운영한다.


## kube-proxy

![K8s Cluster](https://i.stack.imgur.com/5LX2K.jpg)

https://github.com/openshift/cluster-network-operator/blob/master/bindata/kube-proxy/kube-proxy.yaml

K8s 클러스터에서 Service 오브젝트가 동작하는 것을 가능케 하는 존재다. kube-proxy는 새로운 Service가 생길 때마다 해당 서비스에 바인딩된 Pod으로 트래픽이 갈 수 있도록 규칙을 만들어 저장한다.

즉, Service 리소스가 정상 동작하려면, kube-proxy DaemonSet이 잘 구축되어 있어야 한다. Service 리소스는 실제로 존재해 노드의 자원을 점유하는 존재가 아니라 kube-proxy의 규칙일 뿐이기 때문이다!


## kubelet은 DaemonSet이 아니다

[[stackoverflow] Is the kubernetes kubelet a DaemonSet?](https://stackoverflow.com/questions/60007041/is-the-kubernetes-kubelet-a-daemonset)

K8s 클러스터에 대한 아키텍처 그림을 보면, 각 노드에 kubelet와 kube-proxy가 존재하는 것을 볼 수 있다. 앞에서 kube-proxy는 DaemonSet으로 동작한다는 것을 봤는데, kubelet도 그럴까? 답은 **"kubelet은 Pod이 아니다"**!

kubelet은 Pod이 아니라 데몬 프로세스(daemon process)다. K8s 클러스터 동작할 수 있게 하는 프로세스로 (1) 노드를 클러스터에 등록하고 (2) controlplane으로 부터 요청 받은 리소스 생성 요청을 수행하는 역할을 한다.
