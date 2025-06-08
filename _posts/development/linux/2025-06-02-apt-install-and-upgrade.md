---
title: "apt install & apt upgrade"
toc: true
toc_sticky: true
categories: ["Linux"]
excerpt: ""
---

Data Engineer로서 개발을 계속하면서, Linux 시스템에 대한 더 깊은 이해가 필요하다는 걸 깨달았습니다. 종종 Linux 관련 정리할 내용이 생기면 요기에 적어보겠습니다 ㅎㅎ 전체 포스트는 "[Linux](/categories/linux/)"에서 확인하실 수 있습니다.

# apt란?

Linux에서 사용하는 패키지 매니저 입니다. "Advanced Package Tool"의 약자 입니다!


# apt update

패키지 인덱스를 업데이트 하는 명령어 입니다. apt라는 패키지 관리자가 최신 버전의 패키지 목록을 인터넷에서 다운로드해 로컬에 저장합니다!

보통 `/etc/apt/sources.list` 경로에 저장한다고 합니다.

단, 이것은 실제로 패키지를 설치하거나 업그레이드 하는 작업은 아닙니다. 그 작업은 `apt upgrade`가 수행합니다!

# apt upgrade

시스템에 이미 설치된 패키지를 대상으로, 로컬의 패키지 인덱스를 사용해 가능한 최신 버전으로 업그레이드 합니다.

단, 기존에 설치된 패키지의 의존성 구조를 바꾸지 않는 범위 내에서 수행합니다.

# apt clean

APT는 패키지를 설치할 때, `.deb` 패키지 파일을 `/var/cache/apt/archives` 디렉토리에 저장합니다. 이 경로 아래의 모든 `.deb` 파일을 삭제하는 것이 `apt clean` 명령어 입니다.

이 명령어는 이미 설치된 패키지에는 영향을 주지 않습니다.

저는 `ubuntu` 기반 Docker Image를 사용할 때, `apt install` 후에 `apt clean`으로 도커 이미지를 경량화 할 때, 사용 했습니다.

```dockerfile
FROM ubuntu:latest
RUN apt update
RUN apt install -qq -y git vim curl && \
    apt clean
RUN apt install -qq -y zsh && \
    apt clean
```

# apt autoremove

설치한 패키지로 `apt remove`로 제거한 후에, 그것이 의존하던 패키지를 완전히 지우는 명령어 입니다.

패키지를 설치할 때, 같이 설치된 의존 패키지들은 부모 패키지가 삭제 될 때, 함께 삭제 되지 않고 남아 있습니다. 이런 고아 패키지를 모두 정리해주는 명령어가 `apt autoremove` 입니다.

# MacOS Package Manager: Homebrew

Homebrew는 Git을 기반으로 동작하는 Mac의 패키지 관리자 입니다.

`brew update`를 하면, 내부적으로 `git pull`을 수행해 Homebrew의 인덱스를 최신화 합니다.

단, 이것도 실제 업그레이드를 하려면 `brew upgrade`를 수행해야 합니다!

마찬가지로 `brew cleanup`과 `brew autoremove` 명령어도 지원 합니다!

애초에 `brew`가 `apt`와 같은 기존 패키지 매니저의 영향을 많이 받았기 때문에, 명령어와 동작 방식도 거의 동일하다고 합니다 ㅎㅎ
