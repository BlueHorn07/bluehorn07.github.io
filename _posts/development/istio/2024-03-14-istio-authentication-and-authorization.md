---
title: "Istio의 Authentication & Authorization"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio"]
excerpt: ""
last_modified_at: 2024-03-14
---

![](/images/meme/dump-head.png)

istio를 사용하면 Authentication과 Authorization을 Envoy Proxy 레벨에서 수행할 수 있다. 이건 Auth & Authz 로직을 어플리케이션에서 구현이 없어도 워크로드를 보호할 수 있도록 하는 기능 같다!!

# 사전 준비: bookinfo 예제

https://bluehorn07.github.io/2024/02/10/istio-book-info-demo/

helloworld는 왜 인지 모르겠지만, 잘 안 되네...

bookinfo로 해보자.

```bash
$ kubectl apply \
    -n default \
    -f https://raw.githubusercontent.com/istio/istio/1.20.2/samples/helloworld/helloworld.yaml
```

```yaml
$ kubectl apply -f - <<EOF
apiVersion: networking.istio.io/v1alpha3
kind: Gateway
metadata:
  name: helloworld-gateway
spec:
  selector:
    istio: ingressgateway
  servers:
  - port:
      number: 80
      name: http
      protocol: HTTP
    hosts:
    - "*"
EOF
```

Gateway

VirtualService

```yaml
$ kubectl apply -f - <<EOF
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: helloworld-vs
spec:
  hosts:
  - "*"
  gateways:
  - helloworld-gateway
  http:
  - match:
    - uri:
        exact: /hello
    route:
    - destination:
        port:
          number: 5000
        host: helloworld.default
EOF
```

curl -X GET http://192.168.64.2/hello


curl http://istio-ingressgateway.istio-system:8080/hello
curl http://helloworld.default:5000/hello

# Authentication

https://istio.io/latest/docs/reference/config/security/request_authentication/

```yaml
$ kubectl apply -f - <<EOF
apiVersion: security.istio.io/v1beta1
kind: RequestAuthentication
metadata:
 name: frontend
 namespace: default
spec:
  selector:
    matchLabels:
      app: productpage
  jwtRules:
  - issuer: "testing@secure.istio.io"
    jwksUri: "https://raw.githubusercontent.com/istio/istio/master/security/tools/jwt/samples/jwks.json"
EOF
```

# Authorization



```yaml
$ kubectl apply -f - <<EOF
apiVersion: security.istio.io/v1beta1
kind: AuthorizationPolicy
metadata:
  name: require-jwt
  namespace: default
spec:
  selector:
    matchLabels:
      app: productpage
  action: ALLOW
  rules:
  - from:
    - source:
       requestPrincipals: ["testing@secure.istio.io/testing@secure.istio.io"]
EOF
```

```bash
$ curl -X GET http://192.168.64.2/productpage
RBAC: access denied
```

```bash
$ curl -X GET http://192.168.64.2/productpage \
    --header "Authorization: Bearer helloworld"
Jwt is not in the form of Header.Payload.Signature with two dots and 3 sections
```

```bash
$ export TOKEN=$(curl -k https://raw.githubusercontent.com/istio/istio/master/security/tools/jwt/samples/demo.jwt -s)
$ echo $TOKEN
```

![](/images/development/istio/istio-jwt-decoded.png)
https://jwt.io/

```bash
$ curl -X GET http://192.168.64.2/productpage \
    --header "Authorization: Bearer ${TOKEN}"
# 응답을 제대로 받았다!
```

`RequstAuthentication`만 설정 했을 때는 JWT 없어도 요청이 갔음. `AuthorizationPolicy`까지 설정해야 JWT 토큰이 없거나 올바른 토큰이 아니면 바로 리젝 당함.

A request that does not contain any authentication credentials will be accepted but will not have any authenticated identity

# oauth2-proxy랑 비슷한 것 같음

![](https://raw.githubusercontent.com/oauth2-proxy/oauth2-proxy/master/docs/static/img/logos/OAuth2_Proxy_horizontal.svg)

어떻게 보면, [oauth2-proxy](https://github.com/oauth2-proxy/oauth2-proxy)



# 참고자료

- [](https://istiobyexample.dev/jwt/)
- [](https://github.com/GoogleCloudPlatform/istio-samples/tree/master/security-intro)
  - 예제를 보면, 무슨 GCP 클라우트에 워크로드를 올리라는데, 해당 부분은 무시하고 나머지 부분만 잘 따라해보자.

