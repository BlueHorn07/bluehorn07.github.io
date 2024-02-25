---
title: "Istio TLS Network 관련 사전 지식"
toc: true
toc_sticky: true
categories: ["Kubernetes", "Istio", "Network"]
excerpt: Istio로 Network Mesh를 다루는자, TLS를 완벽히 알고 있어야 할 것이니라 🧞‍♂
---

## SSL vs. TLS

TLS의 옛날 이름이 SSL이다. (`SSL 3.0`이 `TLS 1.0`과 대응된다.)

요건 인터넷 네트워크 보안에 대한 짧은 역사를 봐야 하는데, 대충 요약하면

- `SSL`은 원래 넷이스케이프 회사에서 관리하던 프로토콜이었음.
- 넷이스케이프의 힘이 약해지며넛, 보안 프로토콜의 관리 주체가 공식 기구로 넘어감.
- 그 이후부터 `TLS`라는 이름으로 출시 중.
- `SSL`라는 이름은 이젠 완전히 안녕!!

만화로 쉽게 이해 가능: https://howhttps.works/ko/https-ssl-tls-differences/

## TLS vs HTTPS

HTTP 통신인데, TLS 방식으로 이동하는 데이터가 암호화 되어 이동하는 경우를 말함.

"S"가 SSL이 아니라 'Secure'를 의미한다는 거에 주의!

###  다른 프로토콜 통신에도 TLS가 사용될 수 있는가?

(ex: TCP, UDP) 등에도 TLS가 사용될 수 있는가?

## TLS tunnel이란?

## TLS Termination

> the process of decrypting traffic encrypted with TLS.

용어 그대로다! TLS 방식이 종료되는 것, 즉 TLS 암호화 된 데이터를 복호화 하여 un-encrypted 상태로 바꾸는 것을 말한다.

### TLS Termination Proxy

![](https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/SSL_termination_proxy.svg/960px-SSL_termination_proxy.svg.png)

TLS Termination을 수행하는 proxy를 말한다.

웹서버에 Nginx를 띄워서 Reverse Proxy로 많이 사용하는데, Nginx를 TLS Termination Proxy로 사용할 수도 있다.

이렇게 구성할 경우, client와 proxy 사이에는 `HTTPS`로 통신하지만, proxy와 내부 어플리케이션은 `HTTP`로 통신한다.

proxy-application 사이도 `HTTPS`로 통신하면 좋겠지만, 이 경우 App 단에서도 TLS Cert를 관리하고, 암호/복호화 비용이 추가 발생하는 단점 때문에 보통 proxy-application 사이 통신은 TLS 암호화 하지 않는다.

https://en.wikipedia.org/wiki/TLS_termination_proxy

### TLS Pass-through proxy

> forward encrypted TLS traffic between clients and servers without terminating the tunnel.

이 녀석은 client에서 날라오는 TLS 암호화 된 데이터를 복호화 하지 않고, application에 바로 전달한다. 이 경우,


https://gateway-api.sigs.k8s.io/api-types/backendtlspolicy/

https://www.ssl2buy.com/wiki/ssl-passthrough-work

### unterminated TLS traffic

> Describes match conditions and actions for routing "unterminated TLS traffic" (TLS/HTTPS) The following routing rule forwards unterminated TLS traffic arriving at port 443 of gateway called “mygateway” to internal services in the mesh based on the SNI value.


## TLS Orignation

> TLS origination occurs when an Istio proxy (sidecar or egress gateway) is configured to accept unencrypted internal HTTP connections, encrypt the requests, and then forward them to HTTPS servers that are secured using simple or mutual TLS. This is the opposite of TLS termination where an ingress proxy accepts incoming TLS connections, decrypts the TLS, and passes unencrypted requests on to internal mesh services.

# Istio Gateway TLS Mode

https://istio.io/latest/docs/ops/configuration/traffic-management/tls-configuration/#gateways

## Inbound Traffic

## Outbound Traffic

### Double Encryption

# mTLS
