---
title: "Persistent Volume & Persistent Volume Claim"
layout: post
tags: ["develop", "kubernetes"]
---

Pod 컨테이너의 볼륨을 직접 정의할 수도 있다. 그러나 클러스터로 관리되는 K8s에선 이 방식을 권장하지 않는다. 대신, 컨테이너 볼륨 개념을 추상화하여 **클러스터 리소스**인 Persistent Volume을 통해 Pod 컨테이너가 쓸 수 있는 볼륨을 관리한다.

# 컨테이너 볼륨은 노드 디스크를 사용한다

컨테이너 볼륨 중 `emptyDir` 유형과 `hostPath` 유형은 Pod이 실행되는 노드의 디스크를 사용한다. 그러나 K8s는 여러 노드가 모인 클러스터로 관리되기 때문에 Pod 역시 하나의 노드에 묶인 존재가 아니라 유동적이다. 그러나 데이터가 노드의 디스크에 존재한다면, 노드 장애 등의 상황에서 데이터가 유실 될 수 있다.

# Persistent Volume이란

Pod이 요구하는 볼륨 명세 = PVC

...
