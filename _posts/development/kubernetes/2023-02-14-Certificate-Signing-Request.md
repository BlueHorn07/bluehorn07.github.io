---
title: "Certificate Signing Request"
toc: true
toc_sticky: true
categories: ["Develop", "Kubernetes"]
---

K8s의 CertificateSigningRequest 리소스는 `.csr` 파일과 동일한 녀석이다!

CertificateSigningRequest 리소스를 작성할 때 `spec.request` 항목에 `.csr` 파일의 내용을 base64로 인코딩한 값이 필요하다.

```yaml
apiVersion: certificates.k8s.io/v1
kind: CertificateSigningRequest
metadata:
  name: akshay
spec:
  request: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURSBSRVFVRVNULS # base64 인코딩된 .csr 파일
```

위의 내용대로 CSR 리소스를 만들고, Approve를 해주면 K8s의 certificate가 만들어진다! 해당 내용은 `k get csr -o yaml`을 통해 확인할 수 있다.

```bash
$ k get csr akshay -o yaml
apiVersion: certificates.k8s.io/v1
kind: CertificateSigningRequest
metadata:
spec:
  groups:
  - system:masters
  - system:authenticated
  request: xxxx
  signerName: kubernetes.io/kube-apiserver-client
  usages:
  - client auth
status:
  certificate: this-is-your-cert! # 요기!
```

만약 K8s의 Certificate API를 사용하지 않았다면, `openssl`로 `.crt` 파일까지 만들어야 했을 것이다. 그런 귀찮음을 CSR 리소스가 해결해준 것!

# User 리소스의 이름을 어디에 정의?

RBAC에서 RoleBinding 리소스를 정의하다보면, 이런 User 리소스를 보게 된다.

```yaml
apiVersion: rbac.authorization.k8s.io/v1
# This role binding allows "jane" to read pods in the "default" namespace.
# You need to already have a Role named "pod-reader" in that namespace.
kind: RoleBinding
metadata:
  name: read-pods
  namespace: default
subjects:
# You can specify more than one "subject"
- kind: User
  name: jane # "name" is case sensitive
  apiGroup: rbac.authorization.k8s.io
roleRef:
  # "roleRef" specifies the binding to a Role / ClusterRole
  kind: Role #this must be Role or ClusterRole
  name: pod-reader # this must match the name of the Role or ClusterRole you wish to bind to
  apiGroup: rbac.authorization.k8s.io
```

그런데 K8s는 User 리소스를 따로 정의하지 않는데, User 이름은 어디서 알 수 있는 걸까? 이것도 CSR 리소스를 통해 알 수 있다!!

`k describe csr`을 해보면 아래와 같이 `CN`(Common Name)에 대한 항목이 있는데, 이게 User 리소스의 이름이다!

```bash
$ k describe csr akshay
Name:                akshay
Requesting User:     kubernetes-admin
Signer:              kubernetes.io/kube-apiserver-client
Subject:
         Common Name:    akshay # 요기에서 확인!
```

이 `CN`은 `.csr` 파일을 만들때 정의하는 것이다. 참고로 `CN`과 CSR 리소스의 이름이 다를 수도 있다!
