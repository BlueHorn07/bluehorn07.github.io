---
title: "ServiceAccount"
layout: post
tags: ["develop", "kubernetes"]
---

k8s의 Account는 api-server에 요청을 보내는 주체이다. UserAccount는 `kubectl`로 api-server에 요청을 보낸다. ServiceAccount는 Pod이 api-server에 요청을 보낼 때 사용한다. Pod이  보내는 요청의 예시로는 "ConfigMap의 값을 읽기", "실행 중인 Pod 목록과 성능 지표를 확인하고 모니터링 하기", "특정 Batch Job을 돌릴 목적으로 Pod 생성하기" 등이 있을 것이다.

# UserAccount 복습

사실 k8s에 "UserAccount"라는 이름의 오브젝트는 존재하지 않는다. 그러나 "ServiceAccount" 오브젝트와 짝을 맞추기 위해 통상적으로 "UserAccount"라는 표현을 사용하는 것 같다.

UserAccount는 `kubectl` CLI를 통해 api-server에 요청을 보내는 주체이다. `kubectl`은 `~/.kube/config` 파일에 정의된 KubeConfig를 읽는데, 이곳에 api-server에 접속하기 위해 인증서 키 정보가 담겨있다.

# ServiceAccount의 동작

UserAccount도 api-server에 요청을 보내기 위해선 인증 정보가 필요했다. ServiceAccount도 마찬가지로 api-server에 요청을 보내려면 인증 정보가 필요하다. **ServiceAccount는 인증 토큰 `token`을 통해 api-server에 요청을 보낸다.**

## ServiceAccount의 Secret

ServiceAccount 리소스가 생성되면, 자동으로 SA에 대응되는 Secret 리소스가 생성된다. 이때, Secret 리소스의 타입은 `kubernetes.io/service-account-token`이고, 이름도 알아서 지어준다. 이 모든것을 kube-controller-manager의 일부인 **Token Controller가 알아서 해준다.**

SA의 Secret 리소스를 확인하면 아래와 같다.

```text
Name:           build-robot-secret
Namespace:      default
Labels:         <none>
Annotations:    kubernetes.io/service-account.name: build-robot

Type:   kubernetes.io/service-account-token

Data
====
ca.crt:         1338 bytes
namespace:      7 bytes
token:
ABCDEFGHIJK...
```

여기서 `token` 값을 Pod이 사용해 api-server 인증에 사용한다.

# Pod에 ServiceAccount 세팅하기

Pod에서 `spec.serviceAccountName`에 SA의 이름을 적으면 끝!

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-pod
spec:
  serviceAccountName: build-robot
  ...
```

# ServiceAccount의 토큰을 쓰는 UserAccount?

ServiceAccount 리소스는 쌍을 이루는 자동으로 생성된 Secret 리소스가 있음을 살펴봤다. 이때, Secret 리소스의 `token`을 인증 정보로 사용하는 UserAccount를 만들 수 있다 ㅋㅅㅋ

KubeConfig의 `users.user[*].token`에 값을 넣어주면 된다.

```yaml
apiVersion: v1
kind: Config
...
users:
  - name: token-user
    user:
      token: THIS_IS_SECRET_TOKEN_OF_SERVICEACCOUNT
```

<hr/>

# References

- [[kubernetes] Configure Service Accounts for Pods](https://kubernetes.io/docs/tasks/configure-pod-container/configure-service-account/)
- [[kubernetes] 서비스 어카운트 관리하기](https://kubernetes.io/ko/docs/reference/access-authn-authz/service-accounts-admin/)

