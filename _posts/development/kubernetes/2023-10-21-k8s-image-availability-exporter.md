---
title: "K8s Image Available Exporter"
toc: true
toc_sticky: true
categories: ["Kubernetes"]
excerpt: Alert if an image used in Kubernetes cannot be pulled from container registry
---

# Image Registry 모니터링

K8s 클러스터에서 python Pod을 띄우려는 당신! 그런데, 우연히도 Dockerhub의 장애가 발생해버리고... 당신이 띄우려는 Pod은 이미지를 받아오지 못해 `ImagePullBackOff`에 빠져버린다... Dockerhub의 안정성을 철썩 같이 믿어버린 당신은 설마 이 단계에서 오류가 날 거라고는 생각하지도 못한채...

![](/images/development/kubernetes/dockerhub-outage.png){: .align-center style="width: 80%;"}
터져버린 Dockerhub...
{: .text-center}

K8s는 Image를 저장하는 "Registry" 단에서 장애가 생기면 Pod을 띄우는 것조차 못하는 상태에 직면한다. 그래서 K8s 클러스터를 운영한다면 Dockerhub, AWS ECR, Github ghcr 같은 Image Registry의 상태를 모니터링 하며 Registry 장애가 K8s 클러스터 운영에 영향을 주진 않을지 주시할 필요가 있을 것이다.

그러나 매번 상태 페이지에 들어가서 확인하기도 힘들고, Registry 전체 장애가 아니라 본인이 사용하는 Image의 Registry에만 장애가 생길 수도 있을 것이다. 그러나 K8s 클러스터를 운영할 정도면 사용하는 이미지도 각양각색 일터 Pod에서 사용하는 이미지들이 수십개는 될 것이고, 클러스터에 떠있는 모든 이미지를 나열해서 정리하는 것도 일이다.

이런 귀찮은 작업을 대신 해주는 Prometheus Exporter가 있으니 바로 "[Kubernetes Image Availability Exporter](https://github.com/deckhouse/k8s-image-availability-exporter)"다!

# K8s Image Availability Exporter

K8s Image Availability Exporter, 줄여서 k8s-iae exporter는 K8s 클러스터애서 사용하는 모든 이미지 종류를 수집해 해당 Image의 상태를 모니터링 하는 Exporter다. 에를 들어, K8s 클러스터에서 `python:3.9`, `public.ecr.aws/docker/library/node` 등을 사용하는 Pod들이 존재한다면, 그들에 대한 상태를 모니터링 한다는 것이다.

기존에는 사람이 K8s 클러스터 전체 Pod을 확인하며 사용하는 Image를 정리해야 했다면, K8s iae exporter는 그 목록을 알아서 추출한다!

그래서 K8s iae exporter만 띄워두면, K8s 클러스터의 Pod image의 상태가 알아서 감시되고, 그 정보가 Prometheus에 저장된다!


# 이거 정말 필요한 걸까?

놉! 본인은 요 Exporter가 굳이 필요 없다고 생각한다! 구구절절 요 Exporter를 설명했지만... 이게 무슨 말인가 ㅋㅋ

일단 k8s iae exporter가 해당 이미지의 장애를 포착했다고 해서, 그걸 가능한 다른 태그로 Pod yaml을 변경해주는 그런 대응을 하는게 아니다. 그냥 "어? 이 이미지, 이 태그 지금 못 씀. 수고요." 정도 말해줄 뿐이다.

Pod을 띄울 당시에는 해당 Image Registry에 접근 가능한 상태였는데, 잠시 Registry에 장애가 발생해도 알람이 간다. 사실 Pod을 띄워서 잘 돌고 있고, 당장 다시 띄울 예정도 없다면, 이미 잘 도는 Pod의 Image Availability를 모니터링 할 필욘 거의 없다. 잠시 장애가 생겨도 그들도 금방 복구하게 된다. (사실 그쪽에서 장애가 발생하면, 우린 복구 될 때까지 기다리는 수 밖에 없다... ㅋㅋㅋ ㅠㅠ)

또, 띄우려는 Pod의 이미지가 unavailable 하다면 분명 `ImagePullBackoff` 에러가 발생하는데, 보통 이러면 Pod 띄우는 사람이나 K8s 클러스터 모니터링 하는 사람이 먼저 발견하고 대응한다.


# 이 녀석 진짜 별론가...?

놉, 그건 아닌 것 같다. 처음 요 Exporter가 공개되었을 때 기뼈한 사람도 있다.

![](/images/development/kubernetes/k8s-iae-exporter-response.png){: .align-center style="width: 80%;"}

`quay.io`를 위한 Exporter가 등장했다고 좋아한 사람도 있다 ㅋㅋ 본인은 거의 쓴 적이 없는데, `quay.io`도 Dockerhub, ECR 처럼 Image Registry 중 하나다. 그런데 좀... 장애가 많은 것 같은... 그런 Registry인 것 같다 ㅋㅋ

본인 팀에서 K8s iae exporter를 검토하게 된 이유도 `quay.io`의 잦은 장애 때문이었다... ㅋㅋ

어쨋든 Registry 모니터링 측면에선 유용성이 있으니, 필요하다면 한번 사용해보자!

# 이걸 개발한 deckhouse에 대해서

요 k8s iae exporter는 [deckhouse](https://deckhouse.io/)란 회사에서 개발했는데, "NoOps Kubernetes platform"라는 슬로건으로 서비스를 제공하고 있다.

사이트에 들어가서 살펴보면, K8s 클러스터 운영과 관리 서비스를 전문적으로 제공하는 것 같다. spark cluster를 쉽게 운영/관리하기 위해 Databricks를 채택하는 것처럼, K8s 클러스터 솔루션을 제공하는 것 같았다.

![](/images/development/kubernetes/deckhouse-solution.png){: .align-center}

예를 들어, AWS EKS를 운영할 때도 csi provider를 직접 관리하게 되는데, deckhouse 솔루션은 그런 클러스터 운영에 필요한 provider를 자동으로 관리해주는 것 같았다. ~~그런 것들 직접 관리하면서 장애 한번 나봐야 실력이 많이 느는데... ㅋㅋ~~

> Kubernetes is an extremely dynamic system. When operating the infrastructure in the K8s cluster, we always assume that any pod (or even a node!) might be deleted at any moment. To improve resilience, we are testing the system using various chaos engineering approaches. Mainly, we randomly kill Kubernetes nodes to see whether our applications are ready for pod restarts.

왜 이런 짓을 하는지... 암튼 여러 회사의 K8s 운영 해봤을 테니, 여러 문제와 여러 고민들을 해결한 회사라고 생각한다. k8s iae exporter도 만들걸 보면 K8s에 대해선 전문가 일 것 같다.


# 참고자료

- [Announcing k8s-image-availability-exporter](https://medium.com/flant-com/prometheus-exporter-to-check-kubernetes-images-availability-26c306c44c08)