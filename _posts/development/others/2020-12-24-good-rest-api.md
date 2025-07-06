---
title: "좋은 REST API"
toc: true
toc_sticky: true
categories: ["Develop"]
robots: noindex
sitemap: false
---


이 글은 YouTube에 게시된 이응준 연사님의 "그저 그런 REST API로 괜찮은가" 세미나를 보고 개인적인 용도로 기록하기 위해 작성한 글입니다 ㅎㅎ

<br>
<hr>

<div class="img-wrapper">
  <iframe width="560" height="315" src="https://www.youtube-nocookie.com/embed/RP_f5dMoHFc" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</div><br>

몰려있던 시험들이 끝나고 약간의 여유가 생겨서 '나중에 보기' 목록에 묵혀 둔 영상들을 살펴보던 중 요것을 보게 되었다. 47분의 짧지 않은 분량이지만, 연사님께서 정말 쉽고 재미있게 강연을 해주셔서 듣는 내내 지루하지 않았다!!

서버 개발자로서 "`REST`를 만족하는 서버를 만들어야 한다!"는 소리를 많이 들었고, 개인적으로는 `HTTP Method`를 쓰고, `HTTP StateCode`를 잘 쓰면 `RESTful` 서버를 개발하고 있다고 생각했다.

<br>

세미나는 `REST`의 창시자인 Roy Fielding 형님이 추구하는 `REST`를 명확하게 전달한다. 그리고 지금의 통용되는 `REST API`는 사실 정확하게 `REST`하지는 않다는 것을 말한다.

그리고 진정으로 `RESTful`한 API를 만드려고 한다면, 아래와 같은 조건을 만족해야 한다고 R. Fielding 형님은 주장한다.

- Uniform Interface
  - Identification of Resources
    - Resource를 구분(identify)하는 "좋은" 이름을 부여하라는 말.
  - Manipulation of Resources through Representations
    - 서버가 전달하는 정보가 어떻게 표현되는지 명시하라.
    - HTTP 헤더에 `Content-Type: test/html` 등을 사용하라는 말
  - **Self-descriptive message**
  - **HATEOAS**

<br>

위의 두 가지 조건은 통용되는 `REST API`들도 나름 잘 지키고 있다. <br>
~~본인도 `Content-Type` 조건은 잘 안 지켰다. 반성한다 ㅠㅠ~~

**<u>Self-descriptive message</u>**

새롭게 알게 된 이 조건은 `GET` body에 포함된 정보에 대한 충분한 설명(description)이 HTTP Message에 담겨야 한다는 것이다. 하지만 세미나를 통해 예시를 확인해도 이 조건이 왜 필요한지 공감하지는 못 했다.

사실 **<u>Self-descriptive message</u>**든 **<u>HATEOAS</u>**든 본인은 둘다 이게 정말 필요한지 와닿진 않는다. 아직 개발 경험이 부족한 건지, 웹 근본주의자들이 중요시하는 웹-붕괴 현상에 대해 둔감한 건지 말이다.

<br>

아마 이 세미나의 가장 큰 교훈은 **<u>웹 호환성</u>**에 대한 내용이다. 제목은 정말 `REST`에 대한 패러다임을 부쉬는 문장이었지만, 막상 내용을 들여다보니 `REST`는 "웹 호환성"이라는 궁극의 가치를 지키기 위한 '가이드 라인'의 역할을 하기위해 더 강력한 `REST`를 권장한다는 거였다.

<div class="statement" style="text-align: center;" markdown="1">
  *클라이언트와 서버가 각각 독립적으로 진화한다.*
</div>

왜 W3C의 멤버들이 HTML를 다듬는지 6-7년이나 되는 시간이 걸렸는지는 그만큼 웹이라는 생태계가 개발자는 물론 현대인의 생활에도 밀접하게 관련이 되어 있기 때문에 함부로 바꿀 수 없다는 거다.

이런 점은 정말 `Python`이나 `TensorFlow`와 같은 개발자들만 사용하는 생태와는 대조적인 것 같다. ~~개인적으로 `TF`는 `TF2.0`이 출시되면서 극혐이 되었다~~

<br>

별 기대 없이 봤던 세미나였는데, 웹개발에 대한 정말 좋은 이야기를 들은 것 같아 기분이 좋다 ㅎㅎ


#### Reference
- [What RESTful actually means](https://codewords.recurse.com/issues/five/what-restful-actually-means)
  - 위의 포스트에서 `REST`에 대해 잘 다루고 있어서 세미나 내용을 글로 다시 이해하는 데에 도움이 많이 됐다 ㅎㅎ
