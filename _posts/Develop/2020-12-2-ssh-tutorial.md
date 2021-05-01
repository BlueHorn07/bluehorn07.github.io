---
title: "SSH key tutorial"
layout: post
tags: ["develop"]
---

### 서론
이 글은 제가 SSH 키를 생성하고 등록한 방법을 정리하기 위해 작성한 글입니다. 지적과 조언은 언제나 환영입니다 ㅎㅎ

- [퀴즈 링크](https://github.com/BlueHorn07/bluehorn07.github.io/tree/master/assets/files/quiz/SSH-tutorial-quiz.pdf)

**<u>Note</u>**<br>
- 클라이언트 환경은 **<u>Windows</u>**입니다.
- 서버 환경은 [vultr](https://www.vultr.com/)[^1]의 **<u>Ubuntu</u>**입니다.

<hr>

### 도입말

vultr나 AWS와 같이 서버를 구매해 사용하는 경우, 그 서버에 원격으로 접속해야 한다. 이때 직접 서버 컴퓨터에서 터미널을 열어 명령을 주는 것과 달리, 로컬 컴퓨터에서 서버에 **<u>원격 접속</u>**해 명령을 내릴 수 있는데, 이때 쓰는 것이 **SSH**이다.

**SSH**는 **<u>Secure Shell</u>**의 약자이다. Shell<small>쉘</small>은 터미널의 일종이라고 생각하면 된다.

SSH로 서버에 원격 접속할 때, 우리는 아래의 명령어로 접속하게 된다.

``` bash
ssh 아이디@[호스트주소]
# ssh root@123.456.789.0
```

즉, 로컬 컴퓨터에서 `ssh` 프로그램을 실행해 서버에 접속한다는 말이다.

그러니 **SSH**는 추상적인 것이 아니라 원격 접속을 하게 해주는 프로그램 정도로 이해하면 될 것이다.

<hr>

### SSH로 서버에 접속하기

먼저 과정을 나열하면 아래와 같다.

1. 클라이언트에서 ssh-key 생성
   - 클라이언트가 public key와 private key를 얻음 
2. 클라이언트의 public key 파일을 서버로 전송
3. 서버는 전송 받은 public key를 서버에 등록

SSH 접속을 처음할 때 항상 헷갈렸던 것이 ssh-key를 누가 생성해야 하느냐 였다. 

결론은 ssh-key는 서버가 아닌 클라이언트 컴퓨터에서 생성해주는 것이다!

<hr>

**<u>Note</u>**<br>
- 윈도우라면 아래의 과정을 `CMD`에서 하든 `git bash`에서 하든 상관이 없다.

#### (클라이언트) ssh-key 생성

``` bash
$ ssh-keygen -t rsa
```

옵션을 입력하는 것이 나오는데, ssh-key 파일의 이름을 지정할 것이 아니라면 모두 Enter를 쳐준다.

그러면 ssh-key 파일이

``` bash
C:/Users/[유저이름]/.ssh
```

에 생성된다.

<br>

사실 이 단계에서 바로 `ssh` 접속을 시도해도 접속이 잘 된다.

``` bash
# vultr의 경우
$ ssh root@123.456.789.0
```

이렇게 입력하면 

```
$ ssh root@123.456.789.0
$ The authenticity of host '[HostName]' can't be established.
ECDSA key fingerprint is [fingerprint].
Are you sure you want to continue connecting (yes/no/[fingerprint])?
```

여기에서 `yes`를 입력하고, vultr 서버의 비밀번호까지 입력하면 서버에 접속할 수 있다!!

그리고 `.ssh/` 폴더에 `known_hosts` 파일이 생성되어 클라이언트가 접속한 서버에 대한 정보가 기록된다!

이렇게 하면, 서버에 ssh 접속이 완료된 것이다!! ㅎㅎ

<hr>

#### 서버에 public key 등록하기

위의 방식으로 서버에 SSH 접속할 순 있지만, 매번 SSH 접속을 할 때마다 서버의 비밀번호를 입력해줘야 한다는 번거로움이 있다...!

매 접속마다 서버에 비밀번호를 입력하는 번거로움을 덜기 위해, 서버에 클라이언트의 **<u>public key</u>**를 등록하는 방법을 사용해보자.

<br>

먼저 이를 위해 클라이언트의 public key를 서버에 전송해줘야 한다. ~~직접 타이핑하지는 말자...~~

`scp` 명령어를 통해 서버로 파일을 전송할 수 있다.

```
$ scp [보낼 파일] [서버에서의 아이디]@[호스트 주소]:[저장할 이름]
$ scp id_rsa.pub root@123.456.789.0:id_rsa.pub
```

`id_rsa.pub`를 전송한 후에 서버에 접속해 파일이 잘 전송되었는지 확인한다.

<br>

``` 
# ls
id_rsa.pub
```

본인은 서버의 `root` 사용자에게 파일을 보냈기 때문에 `/root` 폴더에 이 파일이 생성되었다.

<br>

이제 서버에서 다음의 명령어를 입력한다.

```
# mkdir .ssh
```

`.ssh/` 폴더를 만든다. 리눅스에선 폴더 이름 앞에 `.`이 붙으면 숨김파일로 취급한다.

<br>

```
# ls -al
```

로 잘 생성되었는지 확인

<br>

```
# chmod 700 .ssh
```

`chmod 700`으로 `.ssh/` 폴더에 대한 권한을 변경해준다. `.ssh/` 폴더는 보안과 관련된 부분이기 때문에 반드시 필요한 작업이다.

다시 `ls -al`로 확인해 `.ssh/` 폴더에 대한 설정이 `drdw------`로 바뀌었다면 잘 진행된 것이다! :)

<br>

이제 전송받은 `id_rsa.pub` 파일을 `.ssh/`의 `authorized_keys`에 등록해보자.

```
# cat id_rsa.pub >> .ssh/authorized_keys
```

참고로 `>>`는 리눅스에서 append를 의미한다. 그래서 `id_rsa.pub`의 내용을 `authorized_keys`에 덧붙이는 과정이라고 보면 된다.

<br>

이것으로 클라이언트이 public key를 서버에 등록하는 작업을 완료했다! ><

<br>

클라이언트에서 다시 ssh 접속해보자.

``` 
$ ssh root@123.456.789.0
```

이번엔 이전과 달리 비밀번호를 입력하지 않고도 바로 서버에 ssh 접속할 수 있다!!

<hr>

### 맺음말

이제 아주아주 손쉽게 서버에 ssh 접속할 수 있게 되었다 ㅎㅎ

`VS Code`는 서버에 ssh로 접속해 파일을 바로 바꿀 수 있는 기능도 제공한다!! 

<hr>

**<u>오류에 대한 조언</u>**<br>
만약 오류가 난다면 아래의 과정을 점검해보자.

- 내가 접속하려는 서버 유저에 해당하는 디렉토리에 `.ssh/` 폴더가 잘 생성되어 있고, `authorized_keys`가 잘 있는지
- 서버의 유저 이름을 정확히 알고 있는지
  - 유저 이름은 서버에서 `whoami` 명령어로 얻을 수 있다.
- public key 등록 없이 처음 SSH 접속할 때, 내가 비밀번호를 맞게 입력했는지
- 클라이언트에서 접속하려는 **port**가 ssh 전용으로 열려있는지 확인한다. 만약 ssh가 아닌 다른 프로그램이 해당 port를 점유하고 있다면, 해당 port로는 ssh 접속을 할 수 없다!

**<u>그 외 기타 등등</u>**<br>
- 서버에서 `who` 명령어를 입력하면 서버에 접속하고 있는 모든 접속에 대한 정보를 얻을 수 있다!

<hr>

### 참고자료
- [생활코딩/egoing - SSH Key - 비밀번호 없이 로그인](https://opentutorials.org/module/432/3742)
  - 2013년도 자료이지만, 여전히 잘 된다!

<hr>

[^1]: 참고로 Vultr에선 사이트에서 서버 설정에 들어가 직접 ssh-key를 등록할 수 있다!


