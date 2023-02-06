---
title: "Linux Network 1편"
layout: post
tags: ["develop"]
---

스위치, 브릿지, 라우터, 게이트웨이

# 기본 지식

`192.168.1.0` 주소는 사적인 용도의 IP 주소 대역이다. 

# IP Addressing

네트워크 인터페이스에 CIDR 대역을 부여해 해당 네트워크 인터페이스에 접근할 수 있도록 한다..

```bash
$ ip addr add 192.168.1.10/32 dev eth0
$ ip addr show dev eth0

$ ping 192.168.1.10
```

# Gateway

```bash
$ ip route
```

# Bridge

```bash
$ ip link add name mybr0 type bridge
$ ip link set mybr0 up
```


Q. `docker0` 인터페이스는 어떤 CIDR 대역을 받았을까?


b 


모든 IP 주소는 네트워크 인터페이스에 소속된다. 예를 들어, 로컬 호스트를 의미하는 `127.0.0.1` 주소는 루프백(`lo`) 인터페이스에 할당되어 있다.

# References

- [[youtube] KodeKloud Linux Network](https://youtu.be/9nCIjLlLVzY)
- [](https://dnr2144.tistory.com/93)
- [](https://titanic1997.tistory.com/4)
- [](https://ubuntu.com/server/docs/network-configuration)
- [](https://velog.io/@koo8624/Linux-Linux-Virtual-networking-Interface)
- [](https://www.44bits.io/ko/post/container-network-2-ip-command-and-network-namespace)