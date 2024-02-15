---
title: "Network Devices"
toc: true
toc_sticky: true
categories: ["Develop", "Network"]
---

이 포스트는 [[Youtube] Hub, Bridge, Switch, Router - Network Devices](https://youtu.be/H7-NR3Q3BeI) 영상의 내용을 바탕으로 작성된 것임을 밝힘니다.

- [리피터](#리피터)
- [허브](#허브)
- [브릿지](#브릿지)
- [스위치](#스위치)
- [라우터](#라우터)

# 리피터

출발지의 신호를 증폭해 도착지로 보내는 장치

<div class="img-wrapper">
  <img src="{{ "/images/development/kubernetes/network-devices-1.png" | relative_url }}" width="100%">
</div>

# 허브

출발지의 신호를 증폭해 연결된 모든 장치에 보내는 장치.
허브를 통해 Network Topology를 단순화 할 수 있다!

<div class="img-wrapper">
  <img src="{{ "/images/development/kubernetes/network-devices-2.png" | relative_url }}" width="100%">
</div>

# 브릿지

오직 2개의 포트만 있어서, 한쪽에서 받은 신호가 반대편으로 가야 할때 반대쪽으로 신호를 보내는 장치.
2개의 네트워크를 서로 연결하고, 분리시킨다.

<div class="img-wrapper">
  <img src="{{ "/images/development/kubernetes/network-devices-3.png" | relative_url }}" width="100%">
</div>

# 스위치

허브와 브릿지를 결합한 장치. 허브처럼 여러 개의 포트가 있지만, 출발지의 신호를 연결된 목적지로만 전달한다.

네트워크 안(within network)의 커뮤니케이션의 역할을 맡는다.

<div class="img-wrapper">
  <img src="{{ "/images/development/kubernetes/network-devices-4.png" | relative_url }}" width="100%">
</div>

# 라우터

네트워크와 네트워크 사이(between network)의 커뮤니케이션을 맡는다. 스위치와 비슷하지만, Security, Filtering, Redirecting 같은 기능들을 수행할 수 있다. (일반적인 스위치는 Filtering을 하지 못한다.)

<div class="img-wrapper">
  <img src="{{ "/images/development/kubernetes/network-devices-5.png" | relative_url }}" width="100%">
</div>

네트워크의 트래픽이 네트워크 외부로 빠져나가고, 또 네트워크 외부의 트래픽이 네트워크로 들어오게 되는 게이트웨이(Gateway)의 역할을 맡는다.

<div class="img-wrapper">
  <img src="{{ "/images/development/kubernetes/network-devices-6.png" | relative_url }}" width="100%">
</div>
