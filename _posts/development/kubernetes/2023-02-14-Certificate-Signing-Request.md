---
title: "Certificate Signing Request"
layout: post
tags: ["develop", "kubernetes"]
---

CertificateSigningRequest 리소스는 `.csr` 파일과 동일한 녀석이다?

실제로 `.csr` 파일을 base64로 인코딩한 값이 CertificateSigningRequest 리소스를 작성할 때 필요하다.

```yaml
apiVersion: certificates.k8s.io/v1
kind: CertificateSigningRequest
metadata:
  name: my-user
spec:
  request: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURSBSRVFVRVNULS # base64 인코딩된 .csr 파일
```





https://coffeewhale.com/kubernetes/authentication/x509/2020/05/02/auth01/

https://kubernetes.io/docs/reference/access-authn-authz/certificate-signing-requests/