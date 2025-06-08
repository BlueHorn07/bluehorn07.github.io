---
title: "Filesystem Hierarchy Standard"
toc: true
toc_sticky: true
categories: ["Linux"]
excerpt: ""
---

Data Engineer로서 개발을 계속하면서, Linux 시스템에 대한 더 깊은 이해가 필요하다는 걸 깨달았습니다. 종종 Linux 관련 정리할 내용이 생기면 요기에 적어보겠습니다 ㅎㅎ 전체 포스트는 "[Linux](/categories/linux/)"에서 확인하실 수 있습니다.

# Introduction

리눅스와 유닉스 계열 시스템에서 디렉터리 구조를 표준화한 문서 및 규약 입니다. 루트 경로에서 `ls /` 명령어로 구조를 확인해볼 수 있습니다!

![](/images/development/linux/filesystem-hierarchy-standard.png){: .fill .align-center style="width: 400px" }

문득 이 폴더들이 어떤 이유로 있는 건지 궁금해져서 내용을 찾아보고 여기에 정리해봅니다 ㅎㅎ

# System Core Directories

시스템 부팅과 운영에 필요한 핵심 리소스가 모여 있는 디렉토리 입니다.

- `/bin`
  - 리눅스 기본 명령어들(`ls`, `mv` 등)이 있는 경로 입니다.
  - 이 경로에 있는 명령어는 모든 사용자가 사용할 수 있습니다.
- `/sbin`
  - 시스템의 관리자 명령어의 모음 입니다.
  - 예를 들어, 유저 추가, 유저 삭제 기능이 있습니다.
  - `root` 사용자 전용 폴더 입니다.
- `/boot`
  - 부팅 관련 파일이 있습니다.
- `/lib`
  - 공유 라이브러리의 모음 입니다.
  - `apt` 명령어가 사용하는 공용 라이브러리 등이 이곳에 위치 합니다.

# Users and Applications

- `/home`
  - 사용자의 홈 디렉토리 입니다.
  - `adduser`로 유저를 추가하면, 해당 유저 이름으로 `/home/{USERNAME}` 경로가 자동으로 생성 됩니다.
- `/root`
  - 루트 사용자 전용 디렉토리 입니다.
- `/usr`
  - user의 약자가 아니라!! "Unix System Resource"의 약자 입니다.
  - 일반 사용자가 실행할 수 있는 프로그램이 설치 되는 공간 입니다.
  - 시스템이 사용하는 공유 프로그램, 라이브러리, 문서, 설정 등이 이곳에 모입니다.

# Setting and Status Management

- `/etc`
  - 시스템의 설정 파일들이 위치 합니다.
- `/var`
  - 로그, 캐시 등 변동성이 있는 데이터가 쌓입니다.
  - `apt`의 캐시도 이곳에 쌓입니다.
- `/run`
  - 부팅 후 런타임 정보가 이곳에 담깁니다.
  - 메모리 기반 디렉토리이고, 휘발성을 갖습니다.
  - 시스템이 실행 중에 잠시 필요한 정보를 담는 공간 입니다. (ex. lock)

# Temporary and Others

- `/tmp`
  - 임시 파일 저장소 입니다.
  - 재부팅 하면 삭제되는 공간 입니다.
  - 저는...
    - Airflow에서 실행하는 작업이 임시 파일들을 저장해야 할 때 `/tmp` 경로를 사용했습니다.
    - Kafka Connect에서 offset을 로컬에 저장할 때 `/tmp` 경로를 사용 했습니다.
- `/dev`
  - Device의 약자 입니다.
  - 현재 시스템에 연결된 장치에 대한 정보가 담깁니다.
- `/proc`
  - 시스템에서 실행 중인 프로세스에 대한 정보가 담김니다.
- `/mnt`
  - 외부 장치 마운트용 디렉토리
