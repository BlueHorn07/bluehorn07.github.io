---
title: "Linux IP Commands"
layout: post
tags: ["develop"]
---

| 명령어 | 설명 |
|--|--|
| `link` | 네트워크 인터페이스 CRUD |
| `addr` | 네트워크 IP 주소 CRUD |
| `route` | |
| `netns` | |

<hr/>

# ip link

현재 시스템의 네트워크 디바이스들을 확인할 수 있다.

```bash
# `ip link show`와 동일
$ ip link 
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN mode DEFAULT group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
...
```

실제 명령어로는 모든 인터페이스를 확인할 수 있지만, 이 포스트에선 1번 디바이스인 루프백(`lo`) 인터페이스의 정보만 살펴보자.

- `mtu 65536`
  - `mtu`는 Maximum Transmission Unit의 약자
  - 루프백 인터페이스로 최대 `65536 byte`[^1]의 패킷 보낼 수 있다. 만약 이 사이즈를 넘는다면, 패킷은 작은 조각으로 쪼개어 전송될 것이다.
- `qdisc nonqueue`
  - `qdisc`는 Queuing Discipline의 약자
  - 인터페이스가 다음 패킷을 전송하는 정책을 말한다.
  - `noqueue`의 의미는 인터페이스가 아무런 큐도 사용하지 않음을 말한다.
- `state UNKNOWN`
  - 네트워크 인터페이스의 상태로는 `UP`, `DOWND`, `UNKNOWN`이 가능하다. `UP`이면 활성화, `DOWN`이면 비활성화 상태다. 
- `mode DEFAULT` 
- `group default`
- `qlen 1000`
  - `qlen`은 Transimission Queue Lenght의 약자다
  - 1000개 패킷까지 큐에 저장되고, 1001개 패킷부터 드롭된다.

- `link/loopback`
  - 루프백 인터페이스를 말한다. 
- `brd`
  - Broadcast를 의미한다.
  - 여기서는 네트워크 인터페이스 이므로 MAC 주소의 연결 정보가 담겨있다.

<hr/>

# ip addr

```bash
$ ip addr 
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
...
```

네트워크 인터페이스 별로 할당된 IP 주소들을 볼 수 있다. `ip link` 명령어의 형식과 유사하지만, 마지막에 IP 주소에 대한 CIDR 값이 보인다.

출력값을 잘 보면 익숙한 이름인 `127.0.0.1` 주소가 보인다. 로컬 호스트의 주소다! 즉, 우리가 `127.0.0.1` 했을 때, 로컬 호스트에 데이터를 전송할 수 있는 이유가 루프백 인터페이스에 할당되어 있기 때문이다!

정확히는 `127.0.0.1/8` CIDR이 루프백 인터페이스에 할당되어 있기 때문에 `127.0.0.1 ~ 127.255.255.255` 범위의 모든 IP 주소는 로컬 호스트를 가리킨다.

`inet`은 네트워크 레이어의 프로토콜로 `inet`이면 ipv4를 `inet6`면 ipv6이다.

`lft`는 Lifetime의 약자로 루프백은 IP 주소가 평생 유효하게 설정 되어 있다.


## 루프백 주소 추가하기

```bash
$ ip addr add 192.168.40.1/32 dev lo
```




<hr/>

# References

- [[stack exchange] ip link and ip addr output meaning](https://unix.stackexchange.com/questions/335077/ip-link-and-ip-addr-output-meaning)
- [[stack exchange] How to understand (the output of) ifconfig or ip addr show [closed]](https://unix.stackexchange.com/questions/465563/how-to-understand-the-output-of-ifconfig-or-ip-addr-show)

<hr/>

[^1]: 65 Kb