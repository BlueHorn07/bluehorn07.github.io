---
title: "Java, I want you!"
toc: true
toc_sticky: true
categories: ["Java"]
excerpt: "이번에야 말로 Java와 친해지겠어..."
---

회사에서 Kafka 관련 작업을 집중적으로 하게 되면서, Java를 좀 포괄적으로 알아야 겠다는 생각이 들어서 다시 한번 Java와 친해지기에 도전해봅니다!

# `interface`에 대해

Java에서 클래스가 구현해야 할 메서드를 미리 정의한 것. 실제 구현은 구현체(`implements`)에서 수행한다.

`interface` 끼리는 상속이 가능하다. 그래서 `extends`로 기존 `interface`에 추가적인 스켈레톤 함수를 추가하는 것이 가능하다.

## `implements` 다중 상속이 되지만, `extends`는 단일 상속만 된다.

코드를 보다보면, `extends`도 하면서 `implements`도 하는 것들이 있는데 당황하지 말자...;;

```java
class Animal {
    void eat() {
        System.out.println("먹는다");
    }
}

interface Pet {
    void play();
}

interface Trainable {
    void train();
}

class Dog extends Animal implements Pet, Trainable {
    public void play() {
        System.out.println("놀아요");
    }

    public void train() {
        System.out.println("훈련해요");
    }
}
```

# Java 8 & 11

https://youtu.be/_6YP2FNTt80?si=9emCKpoN38FyStuj

- Java 8
  - `forEach()`를 지원.
  - 람다 표현식을 지원
  - `.stream()`으로 병렬 처리 지원
  - `interface`에서 `default` 메서드 지원
  - `Optional` 타입 지원
    - null-safe 코드를 짜기 위한 기초가 됨
- Java 11
  - `String`에서 추가적인 내장 메서드 추가
    - `.isBlank()`, `.repeat()`
  - 람다 표현식 내부에서도 `var` 키워드 사용 가능
  - 내장 `HttpClient`를 지원
