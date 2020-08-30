---
layout: post
tags: [CMST499]
---

## 서론
본 글은 2020-1학기 포항공대 이충형 교수님의 '시공간과 물질의 철학' 수업의 읽기자료의 내용을 정리한 것입니다. 물리학도는 아니지만 수업의 본질에 다가가고자 이렇게 문서형태로 정리해둡니다. 지적은 언제나 환영입니다 :)

**읽기자료**:<br>
Are There Really Instantaneous Velocities?, Arntzenius, 2000<br>

<hr>

## 중략

### 전자기학과 time-reversal
**주의**: <br>
저자는 지금 `time-reversible`과 `inverse sequence`에 대한 우리의 통념을 비틀고 있다.<br><br>

우리는 지금까지 **뉴턴 역학**에서의 `time-reversible`에 대해 논의해왔다. 마찬가지로 **전자기학**도 `time-reversible`하다고 여겨진다. 전자기학에서는 정말로 `time-reversible` 할까? 논의해보자!<br>

대부분의 교재는 전자기학이 `time-reversible`하다고 기술하고, 우리도 그렇다고 생각한다. [^1] 그럼 정말로 `time-reversible`이 가능한지 어떤 전자기장에서 벌어지는 일들에 대해 그것의 **역과정(inverse sequence)**을 살펴보자. <br>

우리는 `전자기장`에서 `inverse sequene`를 얻기 위해 (1)과 (2)를 적용할 수 있다.<br>
1. 기존의 벌어지는 일을 그대로 역으로 나열한다. <small>*inverts an allowed sequence*</small><br>
2. magnetic field을 방향을 뒤집는다. <small>*flips over the magnetic fields*</small><br>

그러면 우리는 기존의 sequence와 궤적의 모양은 동일하지만 기존 sequence의 `inverse sequence`인 새로운 sequence를 얻게 된다. 그리고 이 sequence는 전자기학을 위배하지 않는다. <br>

어떤 상황인지 잘 이해가 안 된다면, 아래의 그림에서 어떤 상황인지 한번 생각해보자!<br>
(1)과 (2)를 적용하여 자기장과 전류의 방향을 반전시켜도 전자기학을 위배하지 않는다!<br>
![Right_Hand](/assets/img/CMST499/Right_hand_grip_rule.png){: width="200px"}<br><br>

뉴턴 역학에서는 `time-reversal`에 따른 역과정을 얻기 위해 `velocity`에 `time-reversal operation`을 적용했다. 하지만 (우리가 (2)에서 뒤집은) magnetic field는 어떤 것의 `velocity`도 아닌 그 무엇이다! 그래서 우리는 (2)가 가능하다고 쉽사리 보장할 수가 없다.<br>

전자기학이 우리를 구원하나 싶었지만 (2) 과정에서의 약점이 드러나고 말았다... [^2] 게다가 state에 대한 `time-reversal operation`에 반대하는 입장에서는 (2) 과정도 허용하지 않으므로, 그들은 전자기학도 `time-reversal`하지 않다고 주장하게 된다. 이것은 정말 말도 안 되는 주장이다!!<br>

**요약**: <br>
* 전자기학의 `time-reversible` 성질로부터 뉴턴 역학의 `time-reversible`을 주장할 수 없다.<br>
* "뉴턴 역학처럼 전자기학도 `time-reversible`하지 않다"까지 주장함.

<hr>

### 전자기학과 time direction
잠깐 딴 길로는 가는 내용인데,  "전자기학이 time-reversal하지 않다"는 것이 사실이라면, "시간의 절대적 방향이 존재한다<small>*Time has an objective direction*</small>"라고 생각할 수 있을까?<br>

"time-reversal 하지 않다"는 말은 "역과정이 불가능하다"는 말이니까, "물리현상은 한쪽 방향으로만 일어난다"는 말이 되고, 이것은 곧 "시간이 한쪽 방향으로만 흐른다"는 말이 되면서 결국 "시간의 절대적 방향이 존재한다"는 결론을 도출하게 만든다.<br>

저자 Arntzenious는 "시간의 방향성"에 대한 논증에 회의적인 입장을 보인다. 그러니까 저자는 **"시간의 방향성"은 없다**고 생각하는 것이다.<br>

우리는 "time-reversal 하지 않다"면 뭔가 문제가 생긴다는 걸 알았다. 그래서 time-reversal을 보장하기 위해 문제가 되었던 `velocity`에 음수를 붙이는 연산에 대한 의문을 해결하고자 한다.<br>

time-reversal 과정에서 `velocity`가 변한다는 입장을 지지하기 위해 다음과 같이 논증해보자. <br>
1. `velocity`는 위치 변화의 역사에 의해 정의된다. <br>
   <small>*Velocity is defined in terms of histories of position developments*</small>
2. 물체의 어떤 `intrisic state`도 time-reversal 과정에서 변하면 안 된다!<br>
   <small>*No aspect of the intrinsic state of an object should change under a time reversal*</small>

그리고 2번을 뒷받침하기 위해, 물체가 space-time의 한 점에서 `position`이라는 성질 외에 `local intrinsic quantities`[^3]를 가진다고 주장한다. 그리고 이 성질들은 어떤 값의 형태로 측정된다.<br>

`local intrinsic quantities`가 `gauge dependent`하다고까지 나오는데, 저자는 이것을 "이 성질들에 대해 사람들마다 다른 공간 좌표계를 설정할 수 있다"는 뜻으로 설명한다. 물론 우리가 설정하는 이 좌표계와 `space-time coordniate`와는 무관하다. [^4]<br>

그래서 결론적으로 time-reversal에 대한 두 가지 상황에서 모두 `local intrinsic quantity`는 전혀 변하지 않는다는 것이다.<br>

하지만, `velocity`는 공간좌표계에 의존하기 때문에 `local intrinsic quantity`라고 볼 수 없다! 그리고 실제로 `velocity`는 공간좌표계에 따라 변한다. [^5] `velocity`는 `world line`이라는 녀석에 tangent하게 정의하게 되는데, 이 `world-line`이 non-local space-time quantity(=neighborhood quantity)이기 때문이다. <br>

그래서 time-reversal에 대한 두 가지 입장에게 `velocity`의 변화는 단지 "velocity가 world-line에 대해 어떻게 정의되었느냐"로 결정된다고 주장할 수 있게 되고, 이것이 곧 이 논증의 목표인 time-reversal 상황에서 velocity가 바뀔 수 있는 지에 대한 의문을 해소한다.<br><br>


**하지만!!**<br>
저자는 이때까지 한 `local intrinsic`에 대한 논의가 말도 안 된다고 주장한다...<br>

이 주장에 따르면 "어떤 space-time transformation[^6]에서도 local intrinsic 성질인 magnetic field vector가 변하면 안 된다"가 되는데, 이것은 뉴턴 때부터 쌓아올린 이론을 부정하게 된다. <br>

게다가 이것은 "시간이 전자기학에서 절대적인 방향성"을 가진다고 주장하는 꼴이 되고, 나아가 "전자기학에서는 선호되는 '절대적인 정지좌표계'<small>objective preferred rest-frame</small>가 존재한다"고 주장하는 꼴이 된다.<br>

**요약**:<br>
time-reveral에 대한 설명은 성공했는데, 오히려 "시간의 방향성"을 인정하게 된 상황!

뭔가 혼란이 오는데, 내 생각엔 "`local intrinsic property`가 존재한다"는 거짓인 명제를 바탕으로 논증을 진행해서, "전자기학에선 시간이 절대적인 방향성을 가진다."라는 말도 안 되는 결과가 나온 것 같다.

<hr>

### 결론
결국 우리의 문제를 해결하려면, 우리는 앞에서 했던 논증의 방식이 아니라 다른 방식을 채용해서 문제를 해결해야 한다. <br>

그래서, 우리는 전자기장이 *geometric* object이고, 전자기장의 값은 우리가 쓰는 space-time coordinate에 의존하는 값이라고 주장하자. 그리고 전자기장의 값이 velocity 처럼 space-time transformation 상황에서 변하는 값이라고 주장해야 한다.<br>

그리고 이런 *geometric object*에 대해 기술하는 `Differential Goemetry`라는 학문이 있다. 그러니까 이런 학문을 이용해 time-reversal tranformation에서 전자기장의 값과 velocity를 변화를 기술할 수 있고, 나아가 `intrinsic velocity`도 허용할 수 있다.<br><br>

결론적으로 time-reversibility로는 `"impetus" theory`를 부정할 수 없게 되었다!! (해피 엔딩)<br>

이제 우리는 `"impetus" theory`에서 time-reversal에 대한 의문이 해소되었다. 다만, "우리가 `intrinsic velocity`라는 속성의 존재를 긍정할 수 있는지"와 "이 '속성이 위치 변화의 순간 기울기로 정의된다'는 법칙을 추가할 수 있을지"에 대한 의문점은 여전히 남아있다.<br>

아무래도 `"impetus" theory`의 약점은 저 두가지가 전부일 것 같다 ㅋㅋ<br>

<hr>

[^1]: 뜬금없지만 이 사실이 전자기학이 '시간의 절대적 방향이 존재한다'고 주장하는 것은 아니다. 그러니까 전자기학의 `time-reversible` 성질과 '시간의 절대적 방향'과는 별개의 주제라는 것이다. p.s. 그리고 우리의 현재 관심사도 아니다! 뒤에 이어지는 주제에서는 다룬다.

[^2]: magnetic field가 `velocity`가 아니기에 전자기학에서의 `time-reversal` 성질을 바탕으로 뉴턴 역학에서의 `time-reversal`을 주장할 수 없다는 말이다.

[^3]: particle properties, field amplitudes, ...

[^4]: 이 말은 우리가 부여한 공간 좌표계가 오직 `local intrinsic quantities`에 대한 좌표계라는 말이다. space-time과 전혀 상관없이 말이다!

[^5]: 상대속도 개념을 생각해보면, 우리가 어떤 관성 좌표계를 잡느냐에 따라 우리가 보는 물체의 속도는 변한다.

[^6]: 저자는 `velocity boost`를 예로 드는데, 솔직히 이게 뭔지 모르겠다. 찾아보니 뭔가 Lorentz transformation과 관련이 있는 것 같기도? `time-reversal`도 대표적인 space-time transformation이다.