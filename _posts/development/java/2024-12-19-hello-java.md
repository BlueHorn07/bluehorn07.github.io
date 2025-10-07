---
title: "Hello, Java!"
toc: true
toc_sticky: true
categories: ["Java"]
excerpt: "☕️"
---

# Java에는 표준 스펙과 구현체가 있음.

Java란 이런이런 기능을 만족해야 한다는 표준 스펙이 있음! 그리고 그런 스펙에 맞게 구현한 "구현체"가 있음.

- OpenJDK (Oracle)
- Corretto (Amazon)

구현체 마다 동작은 동일하지만, 내부적으로 각자의 입맛에 맞게 최적화한 부분들이 있을 것임. Corretto 같은 경우는 AWS 서비스랑 궁합이 좋다고 함.

Java 표준 스펙에는 `javac` 컴파일러, Run Lib, JVM과 같은 것들이 있어야 한다고 명시 되어 있음.

# Java 컴파일

`javac` 요게 Java에서의 컴파일러임. C에서의 `gcc` 같은 녀석임.

`java`는 java 컨파일된 `.class` 파일을 실행하는 녀석임.

# Static Method & Variable

클래스 인스턴스가 아니라 클래스 자체에 고정된 멤버임. 그래서 클래스 인스턴스를 만들지 않고도 Method나 Variable을 호출할 수 있습니다.

`static` Variable은 모든 클래스에서 공유하게 됩니다. 메모리 세그먼트 관점에서 보면 `DATA` 세그먼트에 저장되는 변수라고 볼 수 있고, 클래스라는 영역에 바인딩된 Global Variable로 받아들이면 될 것 같습니다.

Static와 non-static Method의 차이는 그 Method를 호출할 때, `this` 키워드가 필요하냐 아니냐 입니다. 만약 `this`를 통해 클래스 인스턴스가 갖는 값에 접근할 필요가 있다면, 그건 instance method 입니다. 만약 그럴 필요가 없다면 static method로 구현해도 됩니다!

# CLASSPATH

`java`와 `javac`에서 어떤 클래스를 호출할 것인지 지시하는 환경 변수 입니다.

Kafka Connector를 띄울 때, 어떤 Connector Plugin을 써야 할지도 요 `CLASSPATH` 환경 변수로 경로를 전달하였습니다. [[ref]](/2024/12/17/kafka-connect-standalone-mode/)

만약 CLI 환경에서 사용한다면,

```bash
$ javac --class-path /path/to/class MyClass.java
$ java --class-path /path/to/class MyClass
```

와 같은 형식으로 사용합니다.


# 배열 선언

```java
ArrayList<Integer> numbers = new ArrayList<>();
```

이때, `int`라는 정수형이 있는데, 왜 `Integer`를 사용했느냐임. 이유는 `ArrayList`는 Java의 Generic 클래스이기 때문에, 객체 타입만 지원한다고 함. 그래서 `int` native 타입이 아니라 `Integer`라는 객체 타입을 써야 한다고 함.


# `java.lang` 패키지

- `System`
- `Integer`, `Double`, `String`
- `Object`

"패키지"지만, 모든 자바 어플리케이션에서 자동으로 `import` 됨. 그래서 `System.out.println()`을 쓸 때,


# Java 11

[[10분 테코톡] 호호의 Java 11](https://youtu.be/LcIyHlE2NlA?si=W9-4PvfezZYmLYZW)



# 어떻게 공부하고 있는지?

인프런에서 요 강의가 무료로 풀려있길래 일단 요 강의로 시작 했습니다.

"[김영한의 자바 입문 - 코드로 시작하는 자바 첫걸음](https://www.inflearn.com/course/%EA%B9%80%EC%98%81%ED%95%9C%EC%9D%98-%EC%9E%90%EB%B0%94-%EC%9E%85%EB%AC%B8)"

무료라서 부담감 없이 시작해본 것 같고, 처음에 Java라는 언어와 생태계를 설명하는 부분이 참 좋았습니다. 그 외의 뒷부분은 개발을 완전 처음 하는 사람 위주로 맞춰져 있어서 빠르게 넘겼습니다. 위의 강사분이 설명하는 것들 중에는 중급 ~ 고급 부분이 제가 더 재밌게 공부 해볼 수 있는 주제들인 것 같습니다 ㅎㅎ

지금은 백준에서 문제를 풀면서 언어의 맛을 느껴보고 있습니다 ㅎㅎ

# 백준에서 Java 입출력

백준을 풀 때는 입출력을 받기 위해 고려해야 할 점들이 항상 있다.

## Scanner

```java
Scanner sc = new Scanner(System.in)
int a = sc.nextInt();
int b = sc.nextInt();
System.out.println(a + b);
sc.close();
```

## BufferedReader, BufferedWriter

백준 문제를 풀다보면, 분명 로직과 시간복잡도가 최적인데도 "시간초과"가 뜨는 경우가 있다.

이것은 `Scanner`와 `System.out.print()`가 입출력을 처리하는 시간이 느려서 그런 것으로 "버퍼링" 기술을 쓰는 `BufferedReader`와 `BufferedWriter`를 사용하면 된다. 요 버퍼링은 이번에 복학해서 들은 운영체제(OS) 수업에서 배웠던 내용인데, 이렇게 금방 다시 만나다니 ㅋㅋ 반갑다.

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

int n = Integer.parseInt(br.readLine());

br.close();
bw.flush();
bw.close();
```

이때, 주의할 점은 `bw.write(int n)`와 같이 정수를 `bw.write()` 할 경우 이걸 숫자 String이 아니라 아스키코드로 해석해 출력한다. 그래서 정수 출력 때는 `String.valueOf(int n)`으로 String으로 변환한 후에 출력 해줘야 한다.

<hr/>

# Spring

https://start.spring.io/

- Spring Boot!
  - 내부에 tomcat 서버가 있다!
  - 그냥 Spring만 쓸 때는 tomcat 서버까지 직접 운영해야 했음.
- Component Scan `@Component`


## Spring Boot가 짱이다

그냥 Spring을 쓰게 되면, 내장 서버가 없어서 별도의 tomcat 서버를 띄워야 함.

반면에 Spring Boot에는 tomcat 서버가 내장 되어 있음(embedded server). 그래서 서빙 레이어를 따로 디플로이 할 필요 없음.

## Java Bean

아래와 같은 규칙을 따르는 Java 클래스를 지칭함. 이런 규칙들을 Java Bean 표준이라고 부르는 것.

- 매개변수가 없는 기본 생성자가 있어야 함.
- 모든 멤버 변수는 `private`으로 선언해야 함.
- Getter와 Setter 메서드를 제공해야 함.
- Java Bean 객체를 파일에 저장하거나 네트워크 전송을 위해 `java.io.Serializable` 인터페이스를 구현해야 함.

이렇게 하는 이유는 Java에서 객체의 재사용 성을 높이고, 더 쉽게 다루기 위해서임.

## JDBC Integration Test

- `@SpringBootTest`
  - 요 Annotation은 Test 코드에서 Spring 컴포넌트를 DI로 주입 받기 위해서
- `@Transactional`
  - JDBC에 쿼리를 실행한 후에, `COMMIT` 없이 `ROLLBACK`을 실행해서 해당 내용이 DB에 반영되지 않도록!

요거는 Unit Test랑은 다른 개념임. Integration Test는 다른 컴포넌트와 상호작용까지 테스트에 포함되어 있음.

# JDBC, JDBC Template, JPA, Spring Data JPA



# AOP(Aspect-oriented Programming)

