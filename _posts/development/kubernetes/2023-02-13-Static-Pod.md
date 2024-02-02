---
title: "Static Pod"
toc: true
toc_sticky: true
categories: ["Develop", "Kubernetes"]
---

노드의 kubelet 데몬에 의해 직접 관리되는 Pod으로, yaml 파일을 통해 생성/수정/삭제한다.


# Static Pod의 생성

일반 Pod을 생성하는 것과 동일한 Pod yaml 파일을 사용한다.
다른 점은 `kubectl`이 아니라 Pod yaml 파일을 `/etc/kubernetes/manifests/` 디렉토리 아래에 위치 시킨다는 것이다.


# 마스터 노드의 컴포넌트와 Static Pod

Static Pod으로 Pod을 띄우면, `kubectl`로 해당 Pod을 삭제할 수 없다. 이런 점 때문에 클러스터의 핵심인 controlplane의 컴포넌트들: apiserver, controller-manager, etcd 등을 Static Pod으로 생성하고 관리한다.

## 미러 파드(mirror pod)

Static Pod은 api-server가 아니라 kubelet 데몬이 관리하는 것이다. 그러나 `kubelet get pods`를 통해서 etcd Pod 등 Static Pod들을 확인할 수 있다. 이것은 kubelet 데몬이 api-server에 Static Pod에 대한 미러 파드(mirror pod) 생성을 요청하기 때문이다. 즉, 우리가 `kubelet get pods`를 통해 보는 etcd Pod은 Static Pod의 본체가 아니라 분신인 셈이다!

그래서 `kubelet delete pod ...`을 하더라도 미러 파드가 잠시 내려갈 뿐, **본체인 Static Pod은 내려가지 않는다!**


# What if no controlplane?

만약 kube-api-server와 master node가 없다고 생각해보자. 개별 노드가 Pod을 생성할 수 있을까? 방법은 Static Pod을 사용하는 것이다!

노드의 `/etc/kubernetes/manifests/` 경로에 Pod yaml 파일을 생성하면, 노드의 kubelet 데몬이 파일을 확인하고, 해당 Pod을 생성할 것이다.

즉, controlplane이 없어도 개별 노드에서 Pod을 생성/수정/삭제 할 수 있는 것이다. 그러나 컨트롤 리소스인 ReplicaSet, Deployment, Daemonset과 네트워크 리소스인 Service는 생성하지 못한다.

