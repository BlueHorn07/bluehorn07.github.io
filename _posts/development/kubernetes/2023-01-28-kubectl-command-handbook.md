---
title: "kubectl 명령어 핸드북"
toc: true
toc_sticky: true
categories: ["Develop", "Kubernetes"]
---

# kubectl CRUD

- Create
  - `kubectl create`
  - `kubectl run` (Pod을 생성)
  - `kubectl apply`*
- Read
  - `kubectl get`
  - `kubectl describe`c
- Update
  - `kubectl edit`
  - `kubectl replace`
  - `kubectl apply`*
- Delete
  - `kubectl delete`
- Others
  - `kubectl logs`
  - `kubectl exec`

<br/>

큰 틀에서 형식은 아래와 같다.

```
$ kubectl [command] [resource] [resource-name] [options]
```

단, Pod에 대한 명령어는 리소스를 명시할 필요가 없다.

- `kubectl run`은 Pod을 생성한다.
- `kubectl logs`는 Pod의 로그를 확인한다.
- `kubectl exec`는 Pod의 컨테이너에 접속한다.

<hr/>

# 리소스 업데이트

## in-place 업데이트

`kubectl apply`, `kubectl edit`은 리소스를 in-place로 업데이트한다.

단, in-place 업데이트는 아래의 속성이 변경될 때만 가능하다.

- `spec.containers[*].image`
- `spec.initContainers[*].image`
- `spec.tolerations`

그외의 변경은 직접 yaml

## destructive 업데이트

in-place 업데이트가 불가능한 경우다. `kubectl replace --force` 명령어로 기존 리소스를 삭제하고 다시 만든다.

```
$ kubectl replace -f [file.yaml] --force
```

기존 리소스 정보를 `kubectl get -o yaml`로 띄운 후 참고하면서, "다른 터미널"을 열어 yaml 파일을 만들어 가면 수월하다.



<hr/>

# Reference

- [[kubernetes] 리소스 인플레이스(in-place) 업데이트 ](https://kubernetes.io/ko/docs/concepts/cluster-administration/manage-deployment/#%EB%A6%AC%EC%86%8C%EC%8A%A4-%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%8A%A4-in-place-%EC%97%85%EB%8D%B0%EC%9D%B4%ED%8A%B8)
- [[kubernetes] 파괴적(disruptive) 업데이트](https://kubernetes.io/ko/docs/concepts/cluster-administration/manage-deployment/#%ED%8C%8C%EA%B4%B4%EC%A0%81-disruptive-%EC%97%85%EB%8D%B0%EC%9D%B4%ED%8A%B8)