---
title: "웹보안 Tips & Tricks"
layout: post
tags: ["develop", "security", "Tips & Tricks"]
---


이 글은 **웹보안**를 공부하면서 개인적으로 정리한 글입니다. 지적과 조언은 언제나 환영입니다 ㅎㅎ

<hr/>

<span class="statement-title">End Point.</span><br>

상황에 따라서 의미하는 바가 조금씩 달라지는 것 같다.

1\. API에서의 End Point

`req`가 최종적으로 도달하는 URI

2\. 일반적인 커뮤니케이션 프로세스에서의 End Point

"an endpoint is simply one end of a communication channel" = 커뮤니케이션 채널의 한쪽 끝!

즉, End Pont는 `req`/`res`를 주고 받는 과정에서 내부 과정들, 인터넷 통신이나 프레임워크 동작 같은 과정을 생략하고, 통신의 두 끝점을 볼때 이르는 표현인 것 같다!

- ['토니파키'님의 포스트](https://toneyparky.tistory.com/6)

<hr/>

<span class="statement-title">Session vs. JWT</span><br>

**Session**은 서버 측에서 사용자를 관리하는 Auth 전략임! 세션 식별자(session.id)를 쿠키에 저장해 요청 때마다 사용함.

"Session Store"를 통해 생성된 Session을 관리함. Store는 파일 디렉토리가 될 수도 있고, DataBase를 사용할 수도 있음.

[단점] 서버가 Session에 대한 정보를 저장하기 때문에 요청이 많아지면, 서버 측에 부하가 걸림.

<br/>

**JWT**은 쿠키 기반 인증 방식을 보완한 Auth 전략임. JWT Token을 쿠키에 저장해 사용함. JWT Secret Key로 암호화 했기 때문에 쿠키가 탈취 되어도 유의미한 정보를 얻을 수 없음.

Fabecook, Google 등의 OAuth의 경우에서도 사용하는 Auth 전략임. // 확장성이 우수!

[단점] JWT 토큰의 길이가 길어서 인증 요청이 많아질수록 네트워크 부하가 심해짐.

[단점] Session 방식에서는 서버 측에서 특정 사용자의 Auth를 강제로 만료시킬 수 있었는데, JWT 방식은 쿠키 기반이기 때문에 한번 토큰이 발급되면 유효기간 전까지는 만료시킬 수 없음.

JWT의 단점을 극복하기 위한 전략으로는 **Refresh Token**이 등장함!

- [생활코딩: Express-Session-Auth](https://opentutorials.org/module/3648)
- [우아한 테크코드: 인증 방식 : Cookie & Session vs JWT](https://woowacourse.github.io/tecoble/post/2021-05-22-cookie-session-jwt/)

<hr/>

<span class="statement-title">Serialize vs. Deserialize</span><br>

- **직렬화(serialize)**: 프로그램의 object 데이터를 외부 파일 또는 DB에 write 하거나 전송하는 것
- **역직력화(deserialize)**: 외부 파일 또는 DB의 데이터를 프로그램 내의 object로 read 하는 것

<hr/>

<span class="statement-title">IAM; Identity and Access Management.</span><br>

