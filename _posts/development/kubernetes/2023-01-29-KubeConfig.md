---
title: "KubeConfig"
toc: true
toc_sticky: true
categories: ["Develop", "Kubernetes"]
---

여러 Kubernetes 클러스터에 접속하기 위해 접속할 클러스터 정보를 KubeConfig에서 관리한다. Cluster와 User를 정의하고, 둘의 조합인 **Context**를 만든다.

# KubeConfig 생성

KubeConfig 파일을 구성한다. `clusters`, `users`, `contexts`를 차례로 정의하면 된다.

```yaml
apiVersion: v1
kind: Config

clusters:
  - name: development
    cluster:
      certificate-authority: fake-ca-file
      server: https://1.2.3.4
  - name: production
    cluster:
      certificate-authority: fake-ca-file
      server: https://5.6.7.8

users:
  - name: developer
    user:
      client-certificate: fake-cert-file
      client-key: fake-key-file
  - name: guest
    user:
      username: guest
      password: password123

contexts:
  - name: dev-frontend
    context:
      cluster: development
      user: deveoper
      namespace: frontend
  - name: prod-guest
    context:
      cluster: production
      user: guest
      namespace: default

current-context: dev-frontend
```

<br/>

<hr/>

# kubectl config

터미널에 설치된 `kubectl`은 `.kube/config`를 기본 KubeConfig 파일로 사용한다. 주요한 명령어를 몇개 살펴보자.

## KubeConfig 확인하기

```bash
# 전체 Config 확인
$ kubectl config view

# 현재 Config만 확인
$ kubectl config view --minify
```

## Context 바꾸기

```bash
$ kubectl config use-context [context-name]
```

## Context 내용 변경

사용할 Namespace를 바꿀 때 주로 쓴다.

```bash
# 현재 Context의 Namespace 변경
$ kubectl config set-context --current --namespace=[NAMESPACE_NAME]

# 특정 Context의 Namespace 변경
$ kubectl config set-context [CONTEXT_NAME] --namespace=[NAMESPACE_NAME]

# Full Format
$ kubectl config set-context CONTEXT_NAME \
  [--cluster=cluster_name] \
  [--user=user_name] \
  [--namespace=namespace_name]
```



<hr/>

# References

- [[kubernetes] 다중 클러스터 접근 구성](https://kubernetes.io/ko/docs/tasks/access-application-cluster/configure-access-multiple-clusters/)