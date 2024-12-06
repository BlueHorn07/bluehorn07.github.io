---
title: "Introduction to Makefile"
toc: true
toc_sticky: true
categories: ["Operating System"]
excerpt: "Makefile로 C 프로젝트 똑똑하게 컴파일하고 빌드하기! ✌️"
---

# 들어가며

대학에서 운영체제 수업을 들으면서 Pintos 과제를 진행하고 있습니다. 그 과정에서 새롭게 접하고 이해한 것들을 정리하고 있습니다.

![](/images/computer-science/operating-system/pintos-structure-1.png){: .align-center style="height: 230px" }
Pintos 코드 일부
{: .small .gray .text-center }

운영체제 수업의 Pintos 과제는 C언어나 어셈블리로 코드를 엄청 봐야 합니다 😵‍💫 [Pintos 코드](https://pintos-os.org/cgi-bin/gitweb.cgi?p=pintos-anon;a=tree)를 살펴보면, `Makefile`라는 파일이 있는데요. 산업기능요원으로 근무하면서 회사에서 Python만 주구장창 했던 저로서는 생소한 파일 뭉치 였습니다.

# How to learn?

유튜브에 Makefile을 60초만에 설명하는 영상이 있어서 이걸 먼저 봤습니다 ㅎㅎ

<iframe width="400" height="250" src="https://www.youtube.com/embed/a8mPKBxQ9No?si=jy6pTMLzUp7XALp_" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>

그리고 [losskatsu님의 블로그](https://losskatsu.github.io/programming/c-make/) 글도 이해에 도움이 되었습니다.

# Hello, gcc!

일단 냅다 hello world 코드가 적힌 `main.c` 파일 하나를 적습니다.

```c
#include <stdio.h>

int main() {
  printf("Hello, World!");
  return 0;
}
```

그리고 아래의 `gcc` 커맨드를 입력하면,

```bash
$ gcc main.c
$ ls
main.c
a.out
$ ./a.out
Hello, World!
```

이런 식으로 `a.out`라는 실행 파일이 만들어집니다.

<br/>

`-c` 옵션을 주면, 컴파일만 하게 할 수 있습니다.

```bash
$ gcc -c main.c
$ ls -al
main.c
main.o
```

그러면 `main.o`라는 object 파일이 만들어집니다. `gcc main.c -c`와 같이 컴파일 옵션을 나중에 적어도 상관 없습니다.

<br/>

위에서 만든 object 파일을 executable로 만들고 싶다면,

```bash
$ gcc -o main main.o
$ ls -al
main.c
main.o
main
$ ./main
Hello, World!
```

`gcc main.o`만 입력해도 executable인 `a.out`이 생기지만, 너무 대충 지은 이름이니;; `-o` 옵션으로 출력 이름을 지정 해줍니다. `gcc main.o -o main`와 같이 출력 옵션을 나중에 적어도 상관 없습니다.

# Hello, Makefile!

Makefile은 앞에서 했던 `gcc` 명령어를 담아두는 파일입니다. 우리는 멍청(?)하기 때문에 컴파일할 파일이 많거나 링킹(linking)할 파일이 많다면 일일이 적어두기 힘들 수 있습니다. shell script 파일을 만들어 둘 수도 있겠지만, C언어에서는 `Makefile`라는 좋은 도구를 지원합니다.

빈 `Makefile`을 하나 만들고, 아래와 같이 적어봅니다.

```makefile
hello-compile: main.c
	gcc -c main.c
```

그리고 `make hello-compile`라고 하면, 적어둔 `gcc` 명령어가 실행됩니다. 이어서 executable 파일을 만들기 위한 `make` 명령어도 만들어봅시다. (이때, 명령어 집합을 표시하기 위해 `tab`을 써야 합니다...;; 공백으로 넣으면 `*** missing separator.`를 만나게 됩니다.)

```makefile
hello-compile: main.c
	gcc -c main.c

hello-executable: hello-compile
	gcc -o hello.out main.o
```

그리고 `make hello-executable`라고 하면, `hello.out`라는 executable 파일이 만들어집니다.

<br/>

Makefile의 명령에 `gcc`만 쓸 수 있는건 아닙니다. 아래와 같이 `echo`등 각종 CLI 명령어 집합을 묶어 make 명령어로 등록할 수 있습니다. (어떻게 보면, shell script랑 정말 비슷합니다.)

```makefile
hello:
	echo "Hello, World!"
```

그래서 executable을 만들고, 바로 실행하게 만들 수도 있습니다.

```makefile
hello-executable: hello-compile
	gcc -o hello.out main.o
	./hello-executable
```

# Makefile: Deep Dive

Makefile이 뭔지는 대충 감을 익힌 것 같습니다 😼 이제 Makefile의 세부적인 사항을 좀 살펴보죠!

## Syntax

Makefile에서 정의하는 명령어는 아래와 같은 구조를 따릅니다.

```makefile
targets: prerequisites
	command
	command
	command
```

명령어 셋에 `targets`라는 이름을 부여하고, 그것을 위한 `pre-requisites`를 명시합니다.
prerequisites에는 (1) 파일 이름 (2) 다른 target들이 들어가거나 (3) 아무것도 안 들어갈 수 있습니다.

만약, 파일 이름을 prerequisites로 넣었는데, 해당 파일이 없다면 아래와 같이 오류를 만납니다.

```bash
make: *** No rule to make target `main.c', needed by `hello-compile'.  Stop.
```

보통은 아래와 같이 `gcc`에 사용하는 파일들을 넣어줍니다.

```makefile
hello-compile: main.c
	gcc -c main.c
```

그리고 아무 타깃도 명시하지 않고 `make`를 실행한다면, Makefile에서 가장 첫 번재 타깃 명령어를 자동으로 실행합니다.

## Make clean

Pintos에서는 `make clean`을 사용해 빌드한 파일들을 제거 했습니다. 사실 요 `clean`이란 것도 타깃 명령어입니다. (저는 처음에 이게 reserved keyword 인 줄 았습니다 ㅋㅋ)

```makefile
some_file:
	touch some_file

clean:
	rm -f some_file
```

`clean`은 무엇인자를 지우거나 정리하는 용도로 자주 사용되며, 첫번째 타깃 명령어로 두지 않도록 유의해야 합니다.

## Variables

Shell Script처럼 Makefile에도 변수를 둘 수 있습니다.

```makefile
a := one two   # a is set to the string "one two"
b := 'one two' # Not recommended. b is set to the string "'one two'"
all:
	echo ${a}
	echo ${b}
---
echo one two
one two
echo 'one two'
one two
```

변수에 값 할당은 `:=` 엄니 연산자로 진행하고,


여기서 잠깐! 만약 이 글을 읽는 여러분이 Makefile의 정말 기본적인 것만 알고 싶다면, 여기서 멈춰도 됩니다 ㅎㅎ
{: .notice}

## Multiple Targets

의외로 타깃 이름도 여러 개를 가지도록 설정할 수 있다. `$@`는 make에서 실행하는 타깃 이름을 참조하는 예약 변수이다.

```makefile
all: f1.o f2.o

f1.o f2.o:
	echo $@
# Equivalent to:
# f1.o:
#	 echo f1.o
# f2.o:
#	 echo f2.o
```

## Double-Colon Rules

정말 드물게 사용하긴 하지만, 같은 이름을 가진 타깃을 정의하고 싶을 때 사용한다.

```makefile
all: blah

blah::
	echo "hello"

blah::
	echo "hello again"
```

이 경우, 위에서부터 `blah`라는 이름을 가진 모든 타깃이 순차적으로 실행된다.

## Disable Command Echo

Makefile을 실행해보면, 타깃에서 어떤 명령어를 실행하는지 정의된 명령어를 꼭 출력하고 실행한다. 만약 이걸 보여주고 싶지 않다면, sliencing 옵션을 넣어 `make -s`를 실행하면 된다.

```makefile
all:
	@echo "This make line will not be printed"
	echo "But this will"
```

또는, 명령어 라인의 맨 앞에 `@`를 넣어주면 된다.

# 맺음말

![](/images/computer-science/operating-system/pintos-structure-2.png){: .align-center style="height: 300px" }
Pintos 코드 일부 - 2
{: .small .gray .text-center }

Pintos 코드 구조를 보면서, 과연 `Makefile`이란게 무엇일까라는 생각이 들어 좀 살펴보게 되었다. 어지럽게도 `Makefile`이 4개나 있고, `Make.config`라는 파일도 있다.

데이터 엔지니어로서 앞으로 C/C++을 쓸일이 많을까??라는 생각이 들기도 한다. 그런데 생각해보니 Istio에서 쓰는 "Envoy"가 C로 짜여진 Proxy라고 들었던 것 같다 😮 내가 정말 코어 레벨의 로직을 건드리는 날이 온다면, 이렇게 공부해둔 것도 언젠가 쓸모가 있겠지!!

이것말고도 Makefile에 대한 더 많은 문법이 있지만... 나머지는 필요해지면 적어보겠다 ㅋㅋ 이 포스트는 Makefile에 대한 101이니까!!


# References

- [Learn make in 60 seconds.](https://youtu.be/a8mPKBxQ9No?si=fpPwxVTJVkdGrZQG)
- [로스카츠님의 블로그](https://losskatsu.github.io/programming/c-make/)
- [makefile tutorial](https://makefiletutorial.com/)
