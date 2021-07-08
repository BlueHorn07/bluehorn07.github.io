---
title: "내가 보려고 만든 '웹보안' Cheat Sheet"
layout: post
tags: ["develop", "security", "Cheat Sheet"]
---

### 서론
이 글은 **웹보안**를 공부하면서 개인적으로 정리한 글입니다. 지적과 조언은 언제나 환영입니다 ㅎㅎ

<hr/>

<span class="statement-title">End Point.</span><br>

약간 경우에 따라서 의미하는 바가 조금씩 달라지는 것 같다.

1\. API에서의 End Point

`req`가 최종적으로 도달하는 URI

2\. 일반적인 커뮤니케이션 프로세스에서의 End Point

"an endpoint is simply one end of a communication channel" = 커뮤니케이션 채널의 한쪽 끝!

즉, End Pont는 `req`/`res`를 주고 받는 과정에서 내부 과정들, 인터넷 통신이나 프레임워크 동작 같은 과정을 생략하고, 통신의 두 끝점을 볼때 이르는 표현인 것 같다!

- ['토니파키'님의 포스트](https://toneyparky.tistory.com/6)

<hr/>

<span class="statement-title">IAM; Identity and Access Management.</span><br>
