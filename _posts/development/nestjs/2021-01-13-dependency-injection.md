---
title: "Dependency Injection"
toc: true
toc_sticky: true
categories: [NestJS]
---


본 글은 제가 `NestJS` 프레임 워크를 통해 개발하면서 깨달은 노하우를 기록한 것입니다. 제가 제시한 방법보다 더 좋은 방법이 있을 수도 있습니다. 지적은 언제나 환영입니다 :)
{: .notice }

# 들어가며

Dependency Injection은 `NestJS` 프레임워크에서 단골로 등장하는 용어다. Dependency Injection(DI)는 소프웨어 설계의 "Design Pattern" 중 하나인데, '의존성(Dependency)'과 '주입(Injection)'을 함께 사용하는 설계 방법이다. 그래서 두 개념에 대한 이해가 선행되어야 한다!

누가 5살 꼬마에게 의존성 주입에 대해 설명하려면 어떻게 해야 하는지 질문글을 올린 적이 있는데, 답변으로 아래와 같이 달렸다고 한다 ㅋㅋ

![](/images/development/dependency-injection.png){: style="max-width: 100%" }
[How to explain dependency injection to a 5-year-old?](https://stackoverflow.com/questions/1638919/how-to-explain-dependency-injection-to-a-5-year-old)
{: .small .gray .text-center }

# 의존성; Dependency

<div class="notice" markdown="1">

&nbsp; "**Dependency** is a relationship between components or classes that can be thought of as a '*uses*' relationship."

Class $A$ uses Class $B$ <br>
$\equiv$
Class $A$ is dependent on Class $B$ <br>
$\equiv$
Class $B$ is a dependency of Class $A$ <br>

<small>직관적으로 말하면 Class $A$가 Class $B$를 내부 변수로 가진다고 말할 수 있을 것 같다.</small>

</div>

코드 레벨에서도 살펴보자!

``` java
public class PetOwner{
  private AnimalType animal;

  public PetOwner() {
    this.animal = new Dog();
  }
}
```

<p style="color:gray">// code fragment from <a href="https://gmlwjd9405.github.io/2018/11/09/dependency-injection">here</a></p>

이 코드에서 `PetOwner` 객체는 `AnimalType`의 객체를 만들기 위해 `Dog` 객체에 "의존"한다.

소프트웨어를 만드는 과정에서 '의존성'은 피할 수 없다! <small>(의존성 없는 소프트웨어가 어디 있겠는가)</small>

하지만, `PetOwner`와 `Dog` 사이에서는 `PetOwner`가 사용할 `animal`이라는 객체 생성과 기능이 모두 `Dog`에 의존한다. 즉, 아주 긴밀하게 연결(**tight coupling**) 되어 있다고 볼 수 있다.

이런 상황은 디자인 패러다임 중 하나인 "Low Coupling"에 부합하지 않는다.

왜냐하면, Coupling이 존재한다면 어떤 모듈의 API나 행동이 바뀌다면, 그 모듈에 의존한 다른 모듈까지 코드를 변경해줘야 할 가능성이 생기기 때문이다.

즉, `Dog` 객체를 수정하면, `PetOwner`까지 덩달아 수정해야 할지도 모른다.

# 주입; Injection

"주입"은 객체 생성이 내부가 아니라 "외부"에서 진행하여 그것을 사용할 객체에 주입하는 것을 말한다.

위의 예시 코드에서는 `this.animal` 객체의 생성을 `PetOwner` 객체의 "내부"에서 진행했다.

그래서 이번엔 "주입"으로 코드를 수정해보자.

``` java
public class PetOwner{
  private AnimalType animal;

  public PetOwner(AnimalType animal) {
    this.animal = animal;
  }
}
```

수정 이후에는 `PetOwner`의 생성자에서 `animal`에 대한 인자를 받는 방식으로 바뀌었다.

이는 객체 내부가 아닌 "외부"에서 `AnimalType`의 객체을 만들어 `PetOwner` 객체에 주입하는 꼴이다!

이렇게 만들 경우, `PetOwner`와 `Dog` 사이의 couping은 옅어지게 된다!!

오히려 `Dog` 대신에 `Cat`이나 `Rabbit` 같은 새로운 형태의 `AniamlType` 클래스를 정의 해줄 수 있다!! <small>(짱인데?)</small>

<br>

사실 위와 같이 "의존성"이 발견되는 부분을 "주입"으로 해결해주면 "**의존성-주입**"이 된다!! 🙂

위의 코드에서는 간단하게 함수 인자로 설정했지만, 객체를 생성의 `Factory`를 사용할 수도 있을 것 같다!!


# 제어의 역전과 의존관계 역전

여기까지 알면, DI에 대해 충분히 이해한 것이다. 이제 조금 소프트웨어 설계를 곁들인 개념들을 살펴보자. **Design Principle**에 대해 익숙하다면, 당연한 것들을 기술하는 것과 불과하다고 느낄 것이다.

## 제어의 역전

> &nbsp; Principle that promotes components that relinquish control of aspects of the code execution to external modules to obtain "**week coupling**". <small>\* relinquish: give up</small>

예를 들어, Sorting 함수의 Comparator가 대표적인 "제어의 역전(Inversion of Control; IoC)"이다!

``` java
List<T>.Sort(IComparator<T>);
```

List의 `Sort` 함수는 실제 Sorting을 수행할 구체적인 방법/기준을 Sort API를 사용하는 Client 단에서 맡김으로써 Control의 일부를 포기하였다!

Client는 `IComparator` dependency에 대한 implementation, creation, lifetime을 모두 직접 관리한다!

이 "제어의 역전"과 DI에 대한 관계는 아래 문장에서 소개된다.

> &nbsp; "Dependency injection is a software design pattern that implements ***<u>inversion of control</u>*** for resolving dependencies. A '**dependency**' is an object that can be used. An '**injection**' is the passing of a dependency to a dependent object that would use it. <small>\- Wikipedia</small>

## 의존관계 역전의 원칙

"**의존관계 역전의 원칙(Dependency Inversion Principle; DIP)**"는 객체 사이에 의존관계를 맺을 때의 가이드 라인이다.

DIP는 의존관계를 맺을 때 변화하기 쉬운 것 또는 자주 변하는 것 보다는 변화하기 어려운 것, 거의 변하지 않는 것에 의존하라는 원칙이다!

이때, 변함에 대한 구분 기준은

- 설계도 `interface`이면 변하지 않는 것, 아직 변하지 않은 것
- 구현체 `implementation`은 이미 변한 것

정도로 이해하면 될 것 같다.

앞에서의 예제를 빌려오자면, 구현체인 `Dog`에 의존하는 것이 아니라 `AnimalType` 자체와 같은 추상적인 `interface`에 의존하라는 것이다!!

`interface`에 의존함으로써 소프트웨어는 좀더 유연한 시스템이 된다.

좀더 엄밀히 기술해보자면,

<div class="notice" markdown="1">

A. 하이-레벨 모듈은 로우-레벨 모듈에 의존해서는 안 된다. 둘다 추상에 의존해야 한다.<br>
<small>A. High-level module should not depend upon low-level module. Both should depend upon abstractions.</small>

B. 추상은 상세를 의존해서는 안 된다. 상세는 추상을 의존해야 한다.<br>
<small>B. Abstractions should not depend upon details. Details should depend upon abstraction.</small>

</div>

"의존 관계 역전"에서 '**역전**<small>inversion</small>'의 의미는 모든 것을 로우-레벨 모듈에 의존하는 전통적인 설계에서 하이-레벨 모듈에 의존하는 방식으로 '역전'되었다는 의미를 담고 있다.

<br>

모델 디자인이 DIP를 만족하는 설계라면, '의존성 주입'을 쉽게 수용하는 코드를 작성할 수 있다. `implementation`이 아니라 `interface`에 의존되어 있기 때문에, 의존성을 손쉽게 주입할 수 있다.

<br/>

자! 이제 처음에 봤던 짤을 다시 한번 보자.

![](/images/development/dependency-injection.png){: style="max-width: 100%" }
[How to explain dependency injection to a 5-year-old?](https://stackoverflow.com/questions/1638919/how-to-explain-dependency-injection-to-a-5-year-old)
{: .small .gray .text-center }


# 참고자료
- [[Design Pattern] DI란 (Dependency Injection)](https://gmlwjd9405.github.io/2018/11/09/dependency-injection)
- [Dependency Injection 이란?](https://medium.com/@jang.wangsu/di-dependency-injection-%EC%9D%B4%EB%9E%80-1b12fdefec4f)
- [의존 역전 원칙, DIP](https://defacto-standard.tistory.com/113)
- [의존 관계 역전의 원칙](https://vandbt.tistory.com/42)