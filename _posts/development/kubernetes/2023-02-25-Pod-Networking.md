---
title: "Pod Internal Networking"
layout: post
tags: ["develop", "kubernetes"]
---

K8s의 Pod에서 함께 돌아가는 container들을 서로 네트워크를 공유한다!

두 컨테이너가 하나의 Pod 위에서 실행되는 multi container Pod을 생각해보자. 두 컨테이너는 어떻게 통신할 수 있을까? Docker Network를 자동으로 만들어주는 docker-compose의 경우를 생각하면, 컨테이너 이름으로 서로 통신할 수 있긴 했다. K8s Pod에서도 컨테이너 이름으로 통신할 수 있을까?

K8s Pod에서는 컨테이너 이름으로 통신하는게 아니라 `loalhost:[port]`로 통신해야 한다. docker-compose처럼 컨테이너 이름으로 통신하는게 아니다. 사실 K8s Pod은 내부적으로 하나의 호스트로 취급하기 때문이다. 그래서 마치 호스트 컴퓨터에서 `docker run`하는 것과 같이 `localhost`로 접근하는 것이다.

예시를 통해 이런 점을 좀더 살펴보자.

## Pod 내부에선 동일 포트 사용 불가

아래와 같이 동일 포트를 쓰는 두 컨테이너를 하나의 Pod으로 실행시킬 수는 없다.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: multi-container-pod
spec:
  containers:
  - name: container1
    image: nginx:alpine
  - name: container2
    image: nginx:alpine
```

이 경우, `container2`에서 이미 점유하고 있는 포트를 쓰려고 한다고 오류가 난다.

## Pod 내부에선 localhost로 통신

이번에는 `container2`를 6379 포트를 쓰는 `redis`로 바꿔보자.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: multi-container-pod
spec:
  containers:
  - name: container1
    image: nginx:alpine
  - name: container2
    image: redis:alpine # redis느 `6379` 포트에서 실행된다
```

그리고 `nc` 명령어로 `localhost`에 해당 포트가 열려 있는지 확인해보자.

```bash
$ k exec -it multi-container-pod -- nc -vz localhost 80
localhost ([::1]:80) open
$ k exec -it multi-container-pod -- nc -vz localhost 6379
localhost ([::1]:6379) open
```

`80`, `6379` 포트를 열라고 명시하지도 않았는데, `localhost`에서 해당 포트들이 열려 있다.

즉, K8s Pod은 하나의 호스트 머신으로써 container들을 돌리고 있는 것이다!
