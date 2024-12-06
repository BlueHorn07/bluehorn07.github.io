---
title: "Stack Frame"
toc: true
toc_sticky: true
categories: ["Operating System"]
excerpt: "함수 호출이 호출되는 과정 살펴보기. SP, BP, LP(IP) 레지스터들의 값이 어떻게 변경되고, 복구되는지 그 원리에 대해."
---

3년간 산업기능요원으로 복무 하고, 복학해 컴공과 수업을 듣고 있습니다. 그동안 많이 배웠다고 생각했는데, 여전히 부족한 부분이 많네요 ^^;; 역시 세상도 넓고 공부의 길도 넓은 것 같습니다.
{: .notice--info}

# 들어가며

분명 "마이크로 프로세서" 수업 때 어셈블리를 하면서 배웠던 것 같은데, 군복무를 하고 오니까 다 까먹었다 ㅋㅋ 운영체제 수업을 들으면서 이 부분을 다시 보게 될 일이 생겼는데, 잘 설명한 자료도 있지만 내 입맛에 맞게 이리저리 조정해 글로 정리해본다 ㅎㅎ

아, 미리 말해두지만 C언어와 `x86-gcc` 컴파일러를 기준으로 설명한다. 그리고 여기서 설명하는 `push`, `pop` 같은 명령어는 assembly 명령어를 의미하며, `x86-gcc`에서 사용하는 CISC 명령어셋을 따른다. 어셈블리 문법은 "Intel 문법"을 따랐음도 밝힌다.

# Stackframe이란??

일단 Stackframe 자체는 스택 메모리에 존재하는 공간 중 일부이다. 함수콜이 발생할 때, 그 함수의 life cycle 동안 사용하는 스택 메모리 공간인데, 여기에 함수의 인자(args)와 각종 지역변수와 중간 연산 결과들이 저장된다.

![](/images/computer-science/operating-system/stack-frame-1.png){: style="max-width: 100%" }

스택 메모리에서 함수콜과 관련된 부분이기 때문에 "call stack"라고 부르기도 한다.

# Registers

스택 프레임이 동작하는 과정에서 사용하는 레지스터들부터 살펴보자. 일단 이름만 간단히 살펴본다.

## Overview

![](/images/computer-science/operating-system/stack-frame-2.png){: style="max-width: 100%" }
Stack Frame의 이해를 돕기 위해 "아주 많이" 추상화된 버전입니다.
{: .small .gray .text-center }

- Stack Pointer; SP
  - 스택의 가장 아래(lowest)의 주소값을 가리키는 레지스터이다.
  - 대충 current, latest 위치를 가리키는 레지스터이다.
  - `push` 명령어를 수행하면, SP 값이 감소한다. (스택은 위에서 아래로 자라니까!): SP - 4
  - `pop` 명령어를 수행하면, SP 값이 증가한다. SP + 4
  - 함수 종료될 때, 스택 메모리를 정리하는데도 사용한다.
    - 요 부분은 뒤에서 함수가 종료되는 상황을 얘기할 때 다룬다.
- Base Pointer; BP
  - 스택 프레임의 시작 위치를 가리키는 레지스터이다.
  - 함수가 실행되는 동안 BP는 그 프레임의 시작 위치에 고정되어 그 값이 변하지 않는다.
  - 함수 내의 매개 변수를 참조할 때, 기준점으로 사용한다.
    - 요 부분도 뒤에서 매개변수에 대해 얘기할 때 다룬다.
  - 함수 내의 지역 변수를 참조할 때, 기준점으로 사용한다.
    - 요것도 뒤에서 지역변수에 대해 얘끼할 때 다룬다.
- Link Pointer; LP 또는 Index Pointer; IP
  - 함수 호출이 종료된 후 복귀할 코드 주소를 담는 레지스터
  - 함수 호출이 발생하면, 그 지점의 코드 주소를 LP에 저장한다.
  - 함수가 종료되면, LP에 저장된 코드 주소로 이동한다. (`jmp lp`)
  - 처음 보면 헷갈릴 수도 있는데, LP가 담는 주소값은 스택 영역이 아니라 코드 영역이다!!

## 사용되는 시나리오

![](/images/computer-science/operating-system/stack-frame-3.png){: style="max-width: 100%" }
{: .small .gray .text-center }

함수가 호출되고, 리턴 되는 과정에서 위의 레지스터들이 어떻게 동작하는지 살펴봅시다. 위의 그림과 함께 과정을 따라가봅시다. (Intel 문법을 기준으로 작성 되어 있습니다.)

1. 함수 호출 직전 (caller saves)
   1. 호출 함수는 함수의 인자 args가 스택에 저장함. (`push 100`)
   2. 함수를 호출한 지점의 코드 주소를 스택에 저장함.
2. 함수 실행 직전
   1. 이전 함수의 BP 값을 스택에 저장함. (`push rbp`)
   2. 그리고 BP 값을 현재의 SP 값으로 업뎃함. (`mov rbp, rsp`)
   3. 함수의 로컬 변수들을 확보함. (`sub rsp, N`)
3. 함수 실행 중
   1. BP 값은 함수가 실행되는 동안 스택 프레임의 시작 위치를 가리키며 고정됨.
   2. SP는 현재 스택의 위치를 나타냄. 변수값을 스택에 저장하거나(`push`), 스택에서 빼오면(`pop`), 그 값이 줄어들거나 늘어남.
4. 함수 종료 시
   1. SP를 저장해둔 BP 위치로 이동시킴. (`mov rsp, rbp`)
   2. 스택에 저장해뒀던 caller 함수의 BP를 복구함. (`pop rbp`)
   3. 스택에 저장해뒀던 복귀 주소를 사용해 코드 주소로 복귀함.

# Access Stack

## Allocate stack space for local vars

위의 과정 중에 2.3 단계에서 일어나는 함수의 로컬 변수를 확보하는 과정을 좀더 살펴보자.

```c
// example to understand function's local variables
void square(int n) {
  int a;
  double b;
  int arr[100];

  // more complex code
  for (int i=0; i < 10; i++) {
    printf("%d", i);
  }

  return n * n;
}
```

본인이 Stack Frame을 처음 알아가면서 이해가 안 되었던 부분은 "로컬 변수를 확보할 공간이 얼마인 줄 알고, `sub rsp, N`를 하는 거지?"라는 생각이었다. 로컬 변수는 코드를 짜는 과정에서 어떤 곳에서든지 등장할 수 있다. 위의 코드처럼 함수의 처음에 사용하는 로컬 변수들을 정의하고 시작할 수도 있고, 코드를 짜는 과정에서 `(for int i=0; ...)`처럼 자연스럽게 선언되는 경우도 있다.

이리저리 찾아보니 `sub rsp, N` 동작에서 쓰는 `N`은 컴파일 타입에 결정된다고 한다! 컴파일러가 코드를 정적으로 분석하여 함수에서 쓰는 로컬 변수들을 전부 모은다. 그리고 그것들은 각 변수 타입에 맞춰서 `int`변 4바이트, `double`이면 8바이트, `int arr[100]`이면 400 바이트... 이런 식으로 로컬 변수의 값이 저장되기 위한 값을 계산해서 `N` 값을 구한다고 한다.

## Access Args and Local vars

Base Pointer는 재밌는 특성을 가지고 있는데, BP 기준으로 위에는 N개의 함수 args가 위치하고, 아래는 M개의 로컬 변수가 스택 공간 상에 존재한다. 그래서 함수 코드에서 함수 args나 로컬 변수에 접근하려면, BP를 기준으로 `mov eax, [bp + 8]`하거나 `mov ebx, [bp - 4]`해서 접근한다.

그런데 여기서 궁금증이 생긴건, "어셈블리 코드는 각 로컬 변수가 BP 기준으로 몇 offset 만큼 떨어져 있는지를 어떻게 알고 `[bp - 4]`라고 찍어주는 거지?"라는 생각이 들었다.

이것도 이리저리 찾아보니, 정답은 컴파일 타임에 있었다 ㅎㅎ 컴파일러는 각 지연 변수들이 BP 기준 얼만큼 떨어진 공간에 위치하는지 그 offset 정보를 담아서 관리한다. 이를 *Offset Table* 또는 *Symbol Table*라고 한다.

컴파일러는 C 코드를 어셈블리고 바꿀때, 각 로컬 변수들을 `[bp - ??]`로 치환한다. `??` 값은 앞에서 언급한 Offset Table에 의해 결정된다.


# 함수 종료와 시작

## 함수가 종료될 때의 동작

일단 SP를 원래 위치로 복원해야 한다. 이것은 아래 어셈블리 명령어로 수행할 수 있다.

```
mov sp, bp    ; BP 값을 SP로 복사해 스택을 정리
```

이제 BP 값을 caller의 BP 값으로 복원한다. 이것도 아래의 명령어로 수행할 수 있다.

```
pop bp
```

위의 과정을 통해 BP가 가리키던 곳에 저장되어 있던 caller BP 값이 BP 레지스터에 저장된다. `pop`이 호출되었기 때문에, SP는 자동으로 SP += 4 처리 된다 ㅎㅎ 어셈블리에서는 요 `mov`와 `pop` 과정을 합쳐서 `leave`라는 명령어로 제공하기도 한다.

<br/>

이제 스택에 저장했던 `return_addr`를 LP 레지스터에 넣어서 함수 호출이 종료될 때, caller의 코드 주소로 이동해야 한다. 이것은 `ret` 명령어로 수행할 수 있다!

`ret` 명령어를 좀더 풀어 쓰면, 아래와 같을 것이다.

```
pop lp
jmp lp
```

즉, `pop` 하고 `jmp` 하는 과정을 묶어준게 `ret`인 셈!!

<br/>

이후에 Caller 쪽에서 할당했던 args를 `pop` 해주는 과정도 필요한데, 아래의 함수 호출 할 때의 동작과 같이 살펴보자 ㅎㅎ


## 함수를 호출할 때의 동작

함수를 호출할 때 Stack Frame 세팅을 위해 일련의 어셈블리 명령어들이 호출되는데, Caller가 수행하는 부분과 Callee가 수행하는 부분으로 나뉜다.

```
; Caller Part
push 100
call square(int)

; Callee Part
push bp
mov bp, sp
sub sp, N
```

1. 함수가 호출 되기 전에 Caller에서 함수에 전달되는 args를 스택에 `push` 한다.
2. `call`로 함수 코드로 이동한다.
   1. 이때, `call` 명령어의 코드 주소를 stack frame에 저장하는 `push lp` 과정도 함께 수행된다.
   2. 즉, `call` 명령어가 `push lp; jmp square`인 셈.
3. 여기서부터 Callee의 어셈블리 블록에서 실행되는 파트다.
4. `push bp`로 이전 stack frame의 BP를 스택에 저장한다.
5. `mov bp, sp`로 BP의 값을 현재 stack frame을 위한 것으로 교체한다.
6. `sub sp, N`으로 지역 변수를 할당한다.

<br/>

함수 Termination 파트까지 해서 같이 보면 아래와 같다.

```
; Caller Initialize Part
push 100
call square(int)

; Callee Initialize Part
push bp
mov bp, sp
sub sp, N

; Callee do something...
...

; Callee Termination
leave
ret

; Caller Termination (remove args)
pop
```

`ret`을 실행해서 Caller는 함수 호출에서 돌아온 후에는, `pop`으로 스택에 넣어뒀던 함수 args를 빼주는 과정이 일어난다!

## 함수 리턴값은 eax 레지스터에 저장된다.

만약 함수에 리턴값이 존재한다면, 그 값은 `eax` 레지스터에 저장된다.

```
; Callee Termination
mov eax, 42
leave
ret

; Caller Termination (remove args)
pop
```

함수가 `ret`으로 종료되기 전에 `mov eax, xxx`를 통해 `eax` 레지스터에 담긴다. 그러면 콜러 함수는 `eax`에 저장된 리턴 값을 사용하거나, 콜러 함수의 스택에 어떤 변수로 저장한다.

리턴 값은 다른 레지스터가 아니라 항상 `eax`에 저장되는데, 이것은 x86 아키텍처에서 "Calling Convention"이기 때문이다. 해당 규약에서는 함수 리턴 값은 `eax` 레지스터에 담도록 정의하고 있다. 사실 `eax` 레지스터는 "누산기(Accumulator) 레지스터"라는 이름으로, 산술 연산의 결과를 담기 위해 디자인된 레지스터이지만, 요 함수 리턴 값을 저장하는 용도로도 사용되는 것이다.

# Overall ASM Code

```
square(int):
  push bp
  mov bp, sp
  ; Do multiplication
  leave
  ret

main:
  push bp
  mov bp, sp
  push 100
  call square(int)
  pop
```



# References

- [MIT OpenCourse](https://youtu.be/00KTZ7t_rWw?si=42G4NkLQyLkJT_dl)
- [youjin님의 블로그](https://u0jin.tistory.com/6)
- [godbolt - online C compiler](https://godbolt.org/)
