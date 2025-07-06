---
title: "Hello, OpenSSL!"
toc: true
toc_sticky: true
categories: ["Develop", "Kubernetes", "Kafka"]
excerpt: "Kafka with SSL을 구축하기 위한 첫걸음! `openssl`로 개인키/공개키를 생성하고, 인증서 서명 요청(CSR)을 만들고, 서명까지 받기!"
---

# 들어가며

회사에서 Kafka 클러스터를 온프레미스로 구축하면서 브로커가 SSL로 통신하도록 구축해야 하는 상황이 있었습니다. 마침 CCDAK 시험을 준비하고 있었고, 이번에 `openssl`라는 도구를 제대로 좀 익혀보고 있어서 관련해서 탐색한 내용을 이렇게 정리해봅니다 ㅎㅎ

SSL/TLS에 대해선 "[전송 계층 보안 SSL/TLS](/2023/01/28/transport-layer-security/)" 포스트에서 정리한 것이 있습니다! 📖

# 기본 사용 방법

`openssl`은 개인키/공개키 쌍을 만드는 것을 포함해 인증서 서명 요청(CSR), 공개 인증서(CRT) 파일을 만드는 것까지! SSL/TLS와 관련된 거의 모든 작업을 수행할 수 있습니다! ㅎㅎ

기본적인 사례들부터 응용 사례들까지 순서대로 따라가봅시다!


## 개인키 생성

```bash
$ openssl genpkey -algorithm RSA -out private.key
$ cat private.key
-----BEGIN PRIVATE KEY-----
MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCFhKZB1BgIYNe
B+hywiTR2/13lA3s3/SZo1wnScVpaOI6aJmi4l0AnLEW7BqyCNCJ63MYARWMZ8cM
...
OoLGksEA5fkakzu8IOeGpl6/
-----END PRIVATE KEY-----
```

## 공개키 추출

그런데 SSL을 하려면, 개인키에 대응되는 공개키가 필요 했습니다. 위의 명령어를 수행하면, 개인키인 `private.key`만 생성되고, 공개키는 생성되지 않는데요! 어디에 있는 걸까요?

정답은 개인키 안에 공개키의 내용이 들어있습니다! 😲 공개키의 값도 `openssl`로 추출할 수 있습니다.

```bash
$ openssl rsa -in private.key -pubout -out public.key
-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwhYSmQdQYCGDXgfocsIk
0dv9d5QN7N/0maNcJ0nFaWjiOmiZouJdAJyxFuwasgjQietzGAEVjGfHDCIp9Yes
GraHOHGJzoDRXKTiR7CY6l90vevyyD9pJSSCThifST23wsjAlIOnBBmdDcbrHWB0
DoU7x33sQwUMWVRWCn7cZR4vaCEn7Fr3YeKpcjXz/woPpr1r9nm2V0oX31YxEt69
TK7GeJQaMNwmfy4acJFyU+a25u0Cf5bz/U+HaWu2Qx0WKaF9CO3bvV2h105A+ARV
fReKr/uWA1jYw/bOo0BAvQzuakRIvQMVJKc1rYnS9lBN5BeT6MYpMsODLqld4sdu
iwIDAQAB
-----END PUBLIC KEY-----
```

왜 공개키는 개인키에서 추출해야 할까요?

그 이유는 개인키와 공개키는 서로 수학적으로 강하게 연결 되어 있어서, 공개키의 값을 개인키만으로 추출할 수 있다고 합니다. 그리고 공개키에는 개인키를 추출하기 위한 정보가 누락 되어 있지만, 개인키에는 공개키를 추출하기 위한 정보까지 함께 있습니다.

이렇게 개인키를 통해 공개키를 추출하도록 하면, 서버는 개인키만 안전하게 보관하면 됩니다! 그리고 공개키는 필요할 때 `openssl`로 생성해서 사용합니다.

## CSR 생성

서버가 가진 공개키에 신뢰할 수 있도록 하기 위해서 인증서(certificate)가 필요합니다. 이 인증서를 정의하기 위해선 인증기관(CA)에게 서버 공개키와 서버의 정보를 포함한 인증서 **인증서 서명 요청(Certificate Signing Request)**를 생성해야 합니다.

`openssl req` 명령어에 `-new`를 붙여서 신규 CSR를 생성하도록 합시다! 이렇게 만들어진 `.csr` 파일을 인증기관(CA)에 보내서 서명을 받을 예정 입니다!

```bash
$ openssl req -new -key private.key -out request.csr

You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [AU]: .
State or Province Name (full name) [Some-State]: .
Locality Name (eg, city) []: .
Organization Name (eg, company) [Internet Widgits Pty Ltd]: .
Organizational Unit Name (eg, section) []: .
Common Name (e.g. server FQDN or YOUR name) []: localhost
Email Address []: .

Please enter the following 'extra' attributes
to be sent with your certificate request
A challenge password []:
An optional company name []:
```

`.csr`을 생성하기 위해 몇가지 메타 정보를 채워야 합니다. 모든 필드를 다 적을 필요는 없습니다! `Common Name(CN)` 항목을 꼭 정확하게 작성해주면 됩니다. 왜냐하면, 브라우저가 SSL 인증서를 검증할 때, `CN` 값을 기준으로 검증하기 때문 입니다!

저는 `CN`을 제외하고는 전부 `.`을 넣어서 빈값으로 넣어줬습니다!

`CN` 필드는 보통 서버에서 사용할 도메인 이름을 적으면 됩니다. `www.example.com`으로 FQDN을 적어도 되고, `*.example.com`와 같이 RegExp를 포함해 작성해도 됩니다.

## Self-signed Certificate 생성

이제 앞에서 만든 `.csr` 파일을 서버가 자체적으로 서명하는 Self-signed Certificate를 만들어봅시다!

```bash
$ openssl x509 -req -in request.csr -days 365 \
    -signkey private.key \
    -out certificate.crt

Certificate request self-signature ok
subject=CN=localhost
```

이렇게 만든 자체 서명 인증서를 운영 환경에서 사용할 수는 없습니다! 왜냐하면, 브라우저에서 자체 서명한 인증서는 신뢰하지 않기 때문입니다. 그래서 웹서버가 이 자체 서명 인증서를 쓰게 되면 `https://localhost:0000`으로 접근은 되지만, "신뢰할 수 없음" 경고가 뜨게 됩니다.

물론 로컬 개발 환경에서는 이를 무시하고 진행하면 됩니다 ㅎㅎ

### 사내 폐쇄 시스템에서 사용

이 부분이 이 글을 쓰게 된 계기 입니다!! ㅎㅎ

외부 CA에 인증서 발급이 어려운 내부용 서비스 또는 폐쇄망에서도 SSL/TLS를 통한 전송 암호화는 필요합니다. 이 경우 자체 서명을 한 인증서를 기반으로 루트 CA를 만듭니다. 그리고 이 자체 서명 인증서를 클러스터를 이루는 모든 서버에 배포하고, 이를 사용하도록 구성 합니다.

저는 회사에서 Kafka 클러스터를 구축할 때, 이 자체 서명 인증서를 루트 CA로 구성해서 사용 했습니다 ㅎㅎ

## 비밀번호 생성

`openssl`로 키와 인증서 도구 뿐만 아니라, 임의의 강력한 비밀번호를 생성하는 것도 가능합니다!

```bash
$ openssl rand 12 | base64
JsQa64t5++7tQ/l+

$ openssl rand -base64 12
GKgjDe3Hmiwvu+vW
```

이렇게 만든 값들을 admin 계정의 비밀번호나 토큰 값으로 사용할 수도 있습니다!


# Self-signed Certificate로 루트 CA 구성하기

위에서 잠깐 언급하기도 했지만, SSL로 통신하는 클러스터를 구성할 때 자체 서명 인증서를 루트 CA로 사용하게 됩니다. 이 과정을 좀더 살펴봅시다!

TODO: 자체 서멍 인증서를 브라우저에 등록해서 신뢰할 수 있도록 만들 수 있을까?


# OpenSSH와 헷갈리지 말기!

둘이 이름이 엄청 비슷합니다... 😅 그래서 저는 처음에

> SSH에서도 `.pem` 키를 만들어서 사용했는데...? 이거랑 다른 건가?

라고 생각 했죠.

일단 가장 큰 차이점은 OpenSSL은 `openssl`라는 이름 그대로의 CLI를 사용하지만, OpenSSH에서는 `ssh`나 `ssh-keygen`과 같은 명령어를 사용합니다. ~~너무 성의가 없는 차이인가...?~~

암튼 전달하고 싶은 것은 OpenSSH는 SSH 보안 접속을 위한 전반적인 시스템이라고 말할 수 있습니다. 그리고 그 과정에서 OpenSSL과 겹치는 부분이 많습니다. 둘다 `.pem` 포맷으로 키를 관리한다는 점. 그리고 암호화 알고리즘으로 RSA 알고리즘 등을 사용할 수 있습니다.

하지만, **OpenSSL**은 `x.509`라는 디지털 인증서과 인증서 기반 체계(PKI)에 중요하다면, **OpenSSH**는 서버에 원격 접속하는 프로토콜이라는 점이 더 중요합니다.

# `keytool`

TODO
