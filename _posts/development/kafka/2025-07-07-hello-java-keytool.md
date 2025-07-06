---
title: "Hello, Java Keytool"
toc: true
toc_sticky: true
categories: ["Kafka", "Java"]
excerpt: ""
---

# 들어가며

Kafka 클러스터에 SSL 인증을 구축하는 걸 해보려고 합니다. Kafka 문서와 자료를 따라가고 있는데, `keytool`이란걸 사용해서 SSL 인증서를 구축하더라구요! 그래서 SSL/TLS에 대한 이론을 다시 보고, `openssl` 도구를 사용하는 법까지 다시 살펴보고 익히고 있었습니다.'

이제 드디어 Java의 `keytool`에 대해서 정.복. 하고자 합니다! ㅎㅎ 아좌잣!

# `keytool`이란?

Java에서 제공하는 인증서 및 키 관리 도구 입니다. JDK 개발 도구에 함께 포함되어 있습니다.

`openssl`에서는 키를 `.pem`, `.key`, `.crt`와 같이 파일 중심으로 관리 했습니다. Java `keytool`은 키를 "저장소"에 보관하는 방식으로 관리 합니다.

그리고 이 저장소 시스템은 크게 "Keystore"와 "Truststore" 두 가지 종류로 나뉘게 됩니다.
이 둘에 대해서는 `keytool` 사용법을 먼저 살펴보면서 뒤에서 같이 살펴보겠습니다!

시작하기 전에 다시 강조하자면, `keytool`은 `openssl`과 조합해 사용하는 도구 입니다. `openssl` 없이 `keytool`만 사용해서 작업하는 것은 불가능하거나 아주 어렵습니다.

# 개인키와 자체 서명 인증서를 keystore에 저장

먼저 아래 명령어로 개인키와 인증서를 생성 합니다.

```bash
openssl genrsa -out server.key 2048
openssl req -new -key server.key -out server.csr \
  -subj "/CN=localhost"
openssl x509 -req -in server.csr -signkey server.key -out server.crt -days 365
```

`.key`와 `.crt` 파일을 `.p12`로 묶어줍니다.

```bash
$ openssl pkcs12 -export -inkey server.key -in server.crt -out server.p12 -name myalias

Enter Export Password:
Verifying - Enter Export Password:
```

마지막으로 `.p12`를 `keytool`을 사용해 keystore에 import 합니다.

```bash
$ keytool -importkeystore -destkeystore keystore.jks -srckeystore server.p12 \
-srcstoretype PKCS12 -alias myalias

Importing keystore server.p12 to keystore.jks...
Enter destination keystore password:
Re-enter new password:
Enter source keystore password:
```

## 왜 개인키와 자체 서명 인증서를 같이 저장하는 걸까?

그러게요, 개인키만 따로 저장하는게 아니라, 귀찮게 자체 서명 인증서까지 만드는 이유가 뭘까요?

이유는 자체 서명 인증서가 곧 개인키에 대응하는 공개키이기 때문입니다. Java의 Keystore 저장소는 이 쌍을 함께 관리할 수 있도록 설계된 구조 입니다.

개인키만 가지고 있다면 아무것도 증명을 못 합니다. 인증서가 함께 있어야 누군가에게 자신을 부가 정보와 함께 증명할 수 있습니다.

그리고 아직 CA 서명된 인증서를 발급 받지 않은 상태이기 때문에, 자체 서명 인증서만 만들 수 있는 상황 입니다.

만약, 루트 CA가 수립되었다면, 루트 CA에게 인증서 서명을 다시 받고, 기존 Keystore에 저장된 예전 인증서는 루트 CA에게 서명 받은 인증서로 교체 합니다.

# truststore에 신뢰할 루트 CA를 등록

TODO?
