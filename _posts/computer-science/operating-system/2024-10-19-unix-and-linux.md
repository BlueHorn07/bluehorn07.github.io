---
title: "Unix and Linux"
toc: true
toc_sticky: true
categories: ["Operating System"]
excerpt: "운영체제의 계보 🌳 운영체제 수업을 이해하기 위한 첫걸음."
---

3년간 산업기능요원으로 복무 하고, 복학해 컴공과 수업을 듣고 있습니다. 그동안 많이 배웠다고 생각했는데, 여전히 부족한 부분이 많네요 ^^;; 역시 세상도 넓고 공부의 길도 넓은 것 같습니다.
{: .notice--info}

# 들어가며

제가 궁금했던 질문들을 좀 미리 적어보겠습니다.

- Unix랑 Linux가 뭐가 다른 거임?
- MacOS는 둘 중에 어떤 거임?
- Windows는 둘 중에 어떤 거임?
- Ubuntu는 둘 중에 어떤 거임?
- POSIX 규격을 따른다는데 그게 무슨 말임?
- BSD는 또 무슨 말임?? Pintos 과제할 때는 4.4BSD를 구현해야 했었음.
- Debian은 또 뭐임??

<br/>

<iframe width="560" height="315" src="https://www.youtube.com/embed/hy4OeVCLGZ4?si=4-1yC7Kv6y7su3FS" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>

<br/>

위의 질문들을 이해하는데 가장 도움이 된 영상입니다. 운영체제 초보자인 제 수준에 딱 맞는 영상이었습니다 ^^

# Unix

운영체제의 개요를 듣다보면, 여러 유저가 컴퓨터를 사용하기 위한 '시분할시스템'... 옛날에는 개인용 컴퓨터가 없고 기업용, 연구용 서버 컴퓨터만 있었다는 옛날 얘기부터 듣는다. Unix는 바로 이런 시점에 태어난 운영체제다!

당대에도 많은 운영체제들이 있었을 텐데, Unix가 성공한 이유는 뭘까? 어떤 좋은 기능들을 제공했기에 성공한 걸까? 하는 궁금증이 생긴다. 그래서 Unix가 가졌던 특징을 나열해보면 아래와 같다.

- 멀티태스킹과 멀티유저를 지원한다.
- 플랫폼 독립적이고, 여러 하드웨어에 설치하여 사용할 수 있었다.
  - Unix 초기에는 어셈블리로 개발했지만
  - 이후에는 모두 C 언어로 재작성 되었습니다.
  - C언어는 플랫폼에 독립적인 고급 언어이기 때문에, 특정 하드웨어에 종속되지 않습니다.
- 단순하고 모듈화 되어 있습니다.
  - "하나의 프로그램은 한 가지 작업만 잘 수행해야 한다."라는 철학을 기반으로, 각 기능이 작은 프로그램으로 분리되어 제공되었습니다.
  - `ls`, `cat` 등 많은 명령어는 각각이 작은 프로그램으로 특정 기능 하나만을 수행합니다.
  - 만약 명령어 사이를 연결하고 싶다면, 파이프(`|`)를 붙여 조합하면 됩니다.
- 파일 시스템의 일관성
  - 모든 것을 파일로 취급합니다.
  - 간단하게는 파일 디렉터리도 파일로 존재합니다.
    - 그외 것들(네트워크 소켓, 장치 드라이버)도 파일로 존재한다는데, 요건 제대로 이해 못 했습니다 ㅋㅋ
- 스크립팅과 쉘 환경을 제공
  - Shell Script를 통해 일련의 명령어를 묶어서 파일로 만들 수 있습니다.
- 네트워킹과 인터넷 지원
  - TPC/IP 프로토콜을 사용하기 위한 코드가 Unix에 내장되어 있습니다.
- 오픈소스 철학
  - Unix는 본래 연구와 교육 목적으로 공개 배포 되었습니다.
  - 이 덕분에 Unix를 기반으로 하는 BSD와 같은 파생 버전과, Unix의 컨셉을 바탕으로 개발한 Linux가 탄생할 수 있었습니다.
- 안정성
  - Unix에는 커널이라는 개념이 존재합니다. 커널이 하드웨어에 딱 붙어서 중요한 작업들을 실행합니다.
  - 커널의 기능에 접근하고 싶다면, System Call 인터페이스로 접근해야 합니다.
  - 그리고 코드의 실행을 User mode와 Kernel mode로 구분합니다.


# Linux의 등장

<p style="text-align: center"><a href="https://commons.wikimedia.org/wiki/File:Tux.svg#/media/File:Tux.svg"><img src="https://upload.wikimedia.org/wikipedia/commons/3/35/Tux.svg" alt="Tux the penguin" height="200px" width="200px"></a><br>By <a rel="nofollow" class="external text" href="http://www.isc.tamu.edu/~lewing/">Larry Ewing</a>, <a rel="nofollow" class="external text" href="http://www.home.unix-ag.org/simon/">Simon Budig</a>, <a rel="nofollow" class="external text" href="https://github.com/garrett/Tux">Garrett LeSage</a> - <a rel="nofollow" class="external free" href="https://isc.tamu.edu/~lewing/linux/">https://isc.tamu.edu/~lewing/linux/</a>, <a rel="nofollow" class="external free" href="http://www.home.unix-ag.org/simon/penguin/">http://www.home.unix-ag.org/simon/penguin/</a>, <a rel="nofollow" class="external text" href="https://github.com/garrett/Tux">garrett/Tux</a> on GitHub, <a href="http://creativecommons.org/publicdomain/zero/1.0/deed.en" title="Creative Commons Zero, Public Domain Dedication">CC0</a>, <a href="https://commons.wikimedia.org/w/index.php?curid=753970">Link</a></p>

"리누스 토르발스"씨가 개발한 운영체제입니다. Unix는 1971년에 출시되었고, Linux는 1991년에 출시되었습니다. (거의 20년이나 차이가 있었네요.)

Linux는 Unix의 철학을 따라 만들어졌습니다. 위에서 살펴본 Unix의 거의 모든 특징을 계승합니다. 그러나, Linux는 Unix에서 파생된 것은 아닙니다. Linux가 처음 개발될 때, Unix의 코드를 참고하지 않고 오직 컨셉: "모든 것은 파일이다", "하나의 프로그램은 한 가지 작업만"과 같은 철학만 따릅니다.

그래서 Linux는 *Unix-like* 운영체제로 불립니다. Unix와 거의 동일한 기능이지만, Unix는 아니라는 거죠. 우리가 Unix를 사용하든 Linux를 사용하든 공통적으로 `ls`, `cat`, `chmod` 명령어를 사용할 수 있고, 동일한 기능을 위해 구현 되었습니다.

## Linux 배포판(Distro)

Linux 자체는 "커널"입니다. 커널이라 함은 Linux가 하는 역할이 프로세스 관리, 메모리 관리, 네트워크 통신 등의 기능을 수행한다는 것이죠. 반면 어떤 것이 "운영체제"다라고 불리려면, 각종 유틸리티 라이브러리와 패키지 관리자 또는 GUI나 그외의 유용한 도구들을 지원해야 합니다.

Linux에서 파생된 다양한 운영체제들은 Linux의 배포판(Distribution, Distro)이며, Linux 커널을 기반으로 각종 유틸리티를 제공합니다. 예를 들어, 대표적인 Linux 배포판인 Ubuntu는 `apt` 패키지 매니저와 따론 Ubuntu GUI도 제공합니다. 이런 것들은 Linux의 것이 아닙니다.

세상에는 다양한 Linux 배포판이 출시되었습니다.

- Arch Linux
  - Linux 커널 기반에 운영체제로서 필요한 최소한의 것을 갖춘 것.
- Debian
  - `apt`, `dpkg`와 같은 패키지 관리자 제공
  - 높은 안정성을 추구
- Ubuntu
  - Debian의 2004년 버전에서 파생된 운영체제.
  - 안정성을 우선시 하는 Debian과 달리 더 최신 기능을 빠르게 제공하는 것에 초점.
  - Debian과 많은 구조를 공유함. 예를 들어, Ubuntu에서도 `apt`를 사용함.

# BSD의 등장

Berkeley Software Distribution의 약자로 1977년 버클리에서 Unix 소스코드를 기반으로 만들어진 운영체제입니다. 앞에서 본 Linux가 1991년 출시이니 둘이 완전히 다른 녀석이죠.

이 운영체제에 대해 적게 된 이유는 MacOS가 요 BSD를 기반으로 하는 운영체제이기 때문입니다. 물론 BSD를 완전히 따라한 건 아니고, BSD의 일종인 FreeBSD의 소스 코드를 기반으로 애플에서 발전시킨 OS가 MacOS 입니다. 그래서 MacOS에서도 `ls`, `cd`와 같은 Unix의 명령어를 사용할 수 있습니다.

BSD라는 이름은 운영체제도로 사용되지만, 소프트웨어 라이선스에도 사용됩니다. BSD 라이선스로 배포된 코드는 

- 자유롭게 사용할 수 있고
- 자유롭게 수정할 수 있고
- 수정한 걸 자유롭게 배포할 수 있고
- 자유롭게 상업적으로 이용할 수 있습니다.
- 그리고 이렇게 사용할 경우에도 소스 코드에 대한 공개 의무가 없습니다.

이런 이유 때문에, MacOS에서 BSD를 기반으로 한게 아닐까 하는 생각이 있습니다. MacOS는 오픈소스로 운영되지 않고 있죠.

# POSIX

Portable Operating Systems Interface의 약자인데, 이름은 딱히 중요하지 않습니다. IEEE에서 발행하는 인증으로, 운영체제라면 갖춰야 하는 몇가지 자동화된 테스트케이스를 시험하고, 통과하는 인증을 발급합니다.

테스트케이스로는

- Standard C Operations
- Multitasking
- Error States
- Command line & Commands

등을 테스트 한다고 합니다. 그래서 만약 어떤 운영체제가 POSIX 인증을 받았다고 하면, 꽤 검증된 운영체제임을 말합니다. MacOS도 POSIX 인증을 받은 운영체제 중 하나입니다.

다만, POSIX 인증 자체는 이것이 운영체제가 Unix를 기반으로 하거나 이건 Unix 파생이야!라고 하는 걸 어느 것도 보장하지 않습니다. 다만, 운영체제가 우리가 기대하는 많은 기능들을 아주 잘 제공한다고 신뢰할 수 있게 합니다.


# SUS

Single Unix Specification의 약자입니다. 이 녀석은 운영체제가 Unix 운영체제의 표준을 따른다는 걸 보증하는 인증입니다. 이 SUS 인증을 받아야 공식적인 Unix 운영체제로 인정 받습니다.

POSIX도 여러 테스트 케이스를 제공하듯이 SUS도 비슷하게 운영체제의 기능을 검증하는 테스트케이스가 있어 운영체제의 동작을 검증합니다. 우리가 아는 운영체제 중에는 MacOS가 SUS 인증을 받았습니다.


# 맺음말

내용을 종합하며, 처음에 제가 했던 궁금증의 답을 적어보겠습니다. 질문의 순서는 제가 조금 조정 했습니다.

- Unix랑 Linux가 뭐가 다른 거임?
  - Linux는 Unix에서 영감을 받은 OS임. (Unix의 철학에 따라 만들어졌다고도 함.)
  - 기술적으로는 Unix가 아님. 그러나 Unix-like라고 부름.
- BSD는 또 무슨 말임?? Pintos 과제할 때는 4.4BSD를 구현해야 했었음.
  - 버클리 대학에서 Unix 소스코드를 기반으로 개발한 운영체제
  - BSD 자체에 대한 것보다는 요게 BSD 라이선스의 시작이라는 점.
  - 그리고 BSD 라이선스는 코드 사용과 수정이 자유롭고, 상업적으로 이용해도 되는데 소스코드도 공개 안 해도 된다는 점이 주목할 부분.
- MacOS는 둘 중에 어떤 거임?
  - Unix에 속함. 정확하게는 BSD를 기반으로 한 Unix.
- Windows는 둘 중에 어떤 거임?
  - Unix도 아니고 Linux도 아님!!! 😲
  - 1990년에 출시된 Windows NT라는 자체 커널을 기반으로 함.
  - 그래서 Windows가 다른 Unix/Linux 계열 OS와 아주 많이 다른 것임.
- Debian은 또 뭐임??
  - Linux 커널을 기반으로 하는 배포판 중 하나임.
- Ubuntu는 둘 중에 어떤 거임?
  - Debian을 기반으로 하는 Linux 배포판임.
  - Debian의 2004년 버전에서 파생된 녀석임.
  - 그래서 둘이 많은 패키지와 구조를 공유하고, 공통적으로 `apt`를 패키지 관리 시스템으로 사용함.
- POSIX 규격을 따른다는데 그게 무슨 말임?
  - 간단히 말하면, 운영체제의 동작을 신뢰할 수 있다는 보증

<br/>

같이 회사를 다니는 분 중에, 개인 노트북에 Arch Linux를 설치해서 사용하시던 분도 있습니다. 또, 온프레미스 서버를 운영하는 다른 회사 지인은 직접 깡통 컴퓨터에 운영체제 설치부터 본인이 하기도 한다고 들었습니다. 이렇게 보면, AWS에서 버튼 하나로 EC2 머신을 띄우고 하는게 편했지만, Unix나 Linux 이런 개념과 역사에 대해선 멀어지게 된 것 같습니다.

암튼 앞으로 어떤 일을 할지는 모르겠지만, 역시 세상에는 공부하고 개발할게 참 많은 것 같네요 🏃 이런 맛에 개발자 하는 거죠^^
