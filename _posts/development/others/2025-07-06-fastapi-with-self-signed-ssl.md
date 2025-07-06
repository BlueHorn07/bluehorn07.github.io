---
title: "FastAPI with Self-signed SSL Certificate"
toc: true
toc_sticky: true
categories: ["Develop"]
excerpt: "자체 서명한 인증서로 FastAPI에 Warning 없이 SSL 적용하기!"
---

# 들어가며

"[Hello, OpenSSL!](/2025/07/05/hello-openssl/)" 포스트에서 잠깐 언급하기도 했지만, SSL로 통신하는 클러스터를 구성할 때 자체 서명 인증서를 루트 CA로 사용하게 됩니다. 이 과정을 좀더 살펴봅시다!

우리의 목표는 로컬에서 실행하는 FastAPI에 접속했을 때, SSL로 통신하도록 세팅하는 것 입니다. 브라우저에 접속했을 때, 아래와 같이 아무런 경고가 뜨지 않아야 합니다.

![](/images/development/fast-api-with-ssl.png){: .align-center style="max-width: 400px"}

일단 FastAPI 앱을 아래와 같이 작성 합니다.

```py
from fastapi import FastAPI
from fastapi.responses import PlainTextResponse
import uvicorn

app = FastAPI()

@app.get("/", response_class=PlainTextResponse)
def read_root():
    return "Hello, World!"

if __name__ == "__main__":
    uvicorn.run("main:app", host="0.0.0.0", port=8443)
```

그리고 실행을 위해 `pip install fastapi uvicorn`으로 설치도 합니다.

지금 상태는 HTTP로 통신하는 FastAPI 입니다.

# 자체 서명한 인증서를 사용하기

가장 먼저 self-signed 인증서를 사용해 SSL을 구성해보겠습니다.

```bash
# 개인키 생성
$ openssl genrsa -out server.key 2048

# 인증서 서명 요청 생성
$ openssl req -new -key server.key -out server.csr \
  -subj "/CN=localhost"

# 자체 서명 인증서 생성
$ openssl x509 -req -in server.csr -days 365 \
    -signkey server.key \
    -out server.crt
```

이제 이 인증서를 사용하도록 FastAPI 코드를 약간 수정 합니다.

```py
# 변경 전
if __name__ == "__main__":
  uvicorn.run(
    "main:app", host="0.0.0.0", port=8443
  )

# 변경 후
if __name__ == "__main__":
  uvicorn.run(
    "main:app", host="0.0.0.0", port=8443,
    ssl_keyfile="ssl/server.key", ssl_certfile="ssl/server.crt"
  )
```

`python3 main.py`로 FastAPI를 실행하고 브라우저에 접속해보면...

![](/images/development/fast-api-with-ssl-but-warn-1.png){: .align-center style="max-width: 400px"}

이런... Warning이 뜨고 맙니다... 🤦 이유는 크롬 브라우저가 유저들의 안전한 인터넷 이용을 위해 자체 서명한 인증서를 신뢰하지 않도록 되어 있기 때문입니다.

"Advanced" 버턴을 누르고, 계속 진행하기를 한다면 HTTPS로 접속은 가능하지만,

![](/images/development/fast-api-with-ssl-but-warn-2.png){: .align-center style="max-width: 400px"}

이렇게 주소창에 `Not Secure`라는 경고 문구를 계속 봐야 합니다 😵


# 루트 CA가 서명한 인증서를 사용하기

로컬에서 자체 서명한 인증서로도 FastAPI 앱을 `Not Secure`로 너무너무 띄워보고 싶었습니다...! 그래서 열심히 찾고, 또 GPT의 도움을 받아서 겨우 방법을 찾아냈습니다 ㅎㅎ

일단 크게 2가지를 진행해야 하는데,

1. 루트 CA의 인증서를 발급하고, 이것으로 서버 인증서를 서명해야 합니다.
  - 이때, 루트 CA의 인증서는 자체 서명이고, 서버 인증서는 자체 서명이 아닙니다. (루트 CA가 서명)
2. 크롬 브라우저에 루트 CA의 인증서를 등록

## 루트 CA의 인증서를 발급하고, 이걸로 서명하기

자체 서명 인증서를 직접 사용하는 것은 권장 되지 않습니다. 자체 서명 인증서는 루트 CA를 구성하는 용도로 사용해야 합니다.

루트 CA는 더이상 상위 인증 기관이 없기 때문에, 어쩔 수 없이 자체 서명한 인증서를 만들게 됩니다.

```bash
# 루트 CA의 개인키
$ openssl genrsa -out rootCA.key 4096

# 루트 CA의 인증서 생성 (self-signed)
$ openssl req -x509 -new -key rootCA.key -sha256 -days 3650 -out rootCA.crt \
  -subj "/C=KR/ST=Seoul/L=Seoul/O=MyOrg/OU=Dev/CN=MyLocalRootCA"
```

이제 서버에서 사용할 인증서와 인증서 서명 요청을 만들어야 합니다.

```bash
# 서버용 개인키
$ openssl genrsa -out server.key 2048

# 서버용 인증서 서명 요청
$ openssl req -new -key server.key -out server.csr \
  -subj "/C=KR/ST=Seoul/L=Seoul/O=MyOrg/OU=Dev/CN=localhost"
```

그리고 서버 인증서에 대한 `SAN`(Subject Alternative Name)을 설정하기 위해 `server.ext` 파일을 아래와 같이 생성 합니다.

```toml
authorityKeyIdentifier=keyid,issuer
basicConstraints=CA:FALSE
keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
subjectAltName = @alt_names

[alt_names]
DNS.1 = localhost
IP.1 = 127.0.0.1
```

마지막으로 아래 명령어로 서버의 인증서를 생성해줍니다!

```bash
openssl x509 -req -in server.csr -CA rootCA.crt -CAkey rootCA.key -CAcreateserial \
-out server.crt -days 365 -sha256 -extfile server.ext
```

저는 이걸 진행하면서 `SAN` 설정이 꼭 필요한지 그리고 `server.ext` 파일이 꼭 필요한지 의문이었는데요. 위의 명령어에서 `-extfile` 부분을 빼고 같은 과정을 진행하면, 후속 과정을 제대로 진행해도 서버 인증서를 사용할 때 여전히 에러를 겪게 됩니다 😅


## 크롬 브라우저에 루트 CA의 인증서를 등록

이제 크롬 브라우저가 제가 자체 서명한 루트 CA 인증서를 신뢰하도록 설정해야 합니다.

`chrome://certificate-manager` <small>(링크 동작이 안 되어서 직접 입력 하시길!)</small>

요 주소로 접속하면, 크롬 브라우저에서 현재 신뢰하고 있는 CA 인증서 목록을 확인할 수 있습니다.

![](/images/development/chrome-cert-manager.png){: .align-center style="max-width: 500px"}

저는 MacOS의 `Keychain Access`에 등록된 인증서가 브라우저에도 동일하게 신뢰 하도록 설정 되어 있습니다.

그리고 "Custom" 항목에도 보이듯 제가 발급한 루트 CA의 인증서를 이곳에 등록 했습니다.

![](/images/development/chrome-custom-cert.png){: .align-center style="max-width: 500px"}

여기까지 하고, FastAPI를 실행한 후 크롬 브라우저에 접속해보면...

![](/images/development/fast-api-with-ssl.png){: .align-center style="max-width: 400px"}

짜잔!! 이제는 로컬 인증서를 사용해도 더이상 `Not Secure` 문구가 보이지 않습니다!! 🥳

## 크롬 브라우저에서만 됩니다!

당연하겠지만, 로컬의 루트 CA 인증서를 크롬 브라우저에 등록한 것이기 때문에 다른 시스템에서는 SSL 인증이 제대로 되지 않습니다.

예를 들어 아래와 같이 터미널에서 `curl` 통해서 접속을 해봐도

```bash
$ curl -X GET https://localhost:8443/
curl: (60) SSL certificate problem: unable to get local issuer certificate
More details here: https://curl.se/docs/sslcerts.html

curl failed to verify the legitimacy of the server and therefore could not
establish a secure connection to it. To learn more about this situation and
how to fix it, please visit the web page mentioned above.
```

인증서를 신뢰하지 못하고 에러를 뿜습니다!! 이렇듯 현재 구성은 오직 크롬 브라우저 환경에서만 동작하는, 반쪽짜리 솔루션 입니다.

# Keychain Access에 등록하기

TODO: 요전 내일 이어서 작성해보겠습니다... ㅎㅎ
