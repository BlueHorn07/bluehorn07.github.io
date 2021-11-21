---
title: "Web Design Tips & Tricks"
layout: post
tags: ["develop", "Tips & Tricks"]
---

<br/>

<div class="img-wrapper">
  <img src="{{ "/images/others/layout.png" | relative_url }}" width="600px">
</div>

<hr/>

<span class="statement-title">styled-component.</span><br>


**styled-component**는 CSS가 이미 있는 컴포넌트를 사용하는 것임 😲

styled-component를 사용하면, sass를 설치하거나 webpack 작업을 할 필요도 없음! (개꿀이라는 말)

컴포넌트에 스타일을 적용하기 위해 별도의 css 파일을 운용하지 않아도 됨!

styled-component는 `props`를 받아서 동적으로 CSS 값을 바꿔줄 수도 있음!!

``` js
...
  <Button danger />
...
const Button = styled.button`
  background-color: ${props => (props.danger ? "red" : "green")}
`
```

styled-component 재활용

``` js
const AnchorButton = Button.withComponent("a").extend`
  text-decoration: none;
`
```

👉 [노마드 코더 - React Styled Components 강좌](https://youtu.be/HqIFTMvtVgc)


<hr/>


- [Figma](https://www.figma.com) 🔥

- [unDraw](https://undraw.co/illustrations) 🔥
  - 깔끔한 이미지
  - 색감 변경 가능

- [Unsplash](https://unsplash.com/) 🔥
  - 무료 고퀄 이미지

- [ReactExmaples.com](https://reactjsexample.com/) 🔥
  - 리액트 컴포넌트 테마 모음 사이트!

- [SiteInspire](https://www.siteinspire.com/)
  - 웹디자인 모음

- [LOLCOLORS](https://www.webdesignrankings.com/resources/lolcolors/)
  - 색 조합 추천
  - 색감 검색 X

- [visme.co/blog](https://visme.co/blog/website-color-schemes/)
  - 색감 참고

- [BrandCrowd](https://www.brandcrowd.com/)
  - 로고 자동 생성 사이트


- [shields.io](https://shields.io/)
  - Shield를 생성해주는 사이트

- [노마드코더 - 웹 서비스 구현의 모든 것! 무료 제작툴 39개 몽땅 알려드림!](https://youtu.be/u3Ph_M2bySg)
  - 뒷부분에 다양한 클라우드 서비스 등도 소개함!! 🤩
