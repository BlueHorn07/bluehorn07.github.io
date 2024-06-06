---
title: "OpenID Connect; OIDC"
toc: true
toc_sticky: true
categories: ["Develop"]
tags: ["Auth"]
---

<div class="notice" markdown="1">

**기획 시리즈: Web Authentication**

1. Cookie & Session & JWT
2. [OAuth]({{"/2021/05/01/OAuth" | relative_url}})
3. [OpenID Connect(OIDC)]({{"/2021/05/02/OpenID-Connect" | relative_url}}) 👈
4. [SSO]({{"/2021/05/02/SSO" | relative_url}})
5. [SAML]({{"/2021/05/02/SAML" | relative_url}})

</div>

이 글은 **OpenID Connect**를 공부하면서 개인적으로 정리한 글입니다. 지적과 조언은 언제나 환영입니다 ㅎㅎ

<hr/>

\<OpenID Connect; OIDC\>는 **<u>OAuth 2.0</u>**을 확장해서 개발 되었다. 그렇기 때문에 OAuth와 마찬가지로 사용자 인증을 다른 서비스에 위임하는 인증 방식에서 사용되는 <u>프로토콜</u>이라고 할 수 있다.

OIDC는 기본적으로 OAuth 프로토콜을 기반으로 동작하는 프로토콜이다. 그래서 기술적으로는 매우 유사하다고 볼 수 있다. 그러나 OIDC와 OAuth는 사용하는 목적에서 차이가 있다.

| OAuth | OIDC |
|:---:|:---:|
| 인가(Authorization)을 위해 사용 | 인증(Authentication)을 위해 사용 |
| Resource Server에 저장된 사용자 데이터에 접근 | 사용자가 누구인지 확인하기 위해 사용  |
| 다른 플랫폼의 API를 호출하기 위해 `acess_token`을 확보 | 사용자 개인을 식별하는 `id_token`을 확보 |

이때, OIDC에서 얻는 `id_token`은 보통 JWT 방식으로 인코딩 되어 있다.

좀더 자세한 내용은 실습후에 보충하도록 하겠다!

<hr/>

### SAML vs. OAuth vs. OIDC

<div class="img-wrapper">
<img src="https://resilient-networks.com/wp-content/uploads/2017/01/table.png" width="600px">
<p>Image from <a href="https://resilient-networks.com/concept-week-saml-oauth2-openid-connect/">here</a></p>
</div>

<hr/>

#### 참고자료

- ['폴피드'님의 포스트](https://blusky10.tistory.com/347)
- ['Dale Seo'님의 포스트](https://www.daleseo.com/google-oidc/)
  - 구글 OAuth & OIDC를 사용하는 방법을 제시한다 😆
- ['Ethan Ayer'님의 포스트](https://resilient-networks.com/concept-week-saml-oauth2-openid-connect/)