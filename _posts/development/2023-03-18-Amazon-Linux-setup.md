---
title: "AWS Amazon Linux 2 개발 셋업"
toc: true
toc_sticky: true
categories: ["Develop", "AWS"]
---

이 글은 정리를 위해 개인적인 용도로 작성된 포스트입니다. 지적과 조언은 언제나 환영입니다 ㅎㅎ

EC2 비용을 줄이기 위해 `t4g` 시리즈의 인스턴스를 사용했더니 "Graviton"이 `ARM` 아키텍처란 걸 뒤늦게 깨달았습니다 😭
`x86` 기반에서 기존에 쓰던 설치 명령어로는 설치가 잘 안 되는 문제도 있었습니다. 그래서 AWS Graviton으로 인스턴스를 구성하면서 겪은 트러블 슈팅을 기록하고자 합니다.

# amazon-linux-extras

Amazon Linux 2에서는 `amazon-linux-extras` 명령어를 통해 서버를 운영하는데 필요한 도구들을 쉽게 설치할 수 있습니다! Amazon Linux가 처음이라면 요 명령어부터 익혀야 합니다! (2023.05 업데이트: "Amazon Linux 2023"에는 `amazon-linux-extras` 명령어가 빠졌다고 합니다 [stackoverflow](https://superuser.com/questions/1777045/amazon-linux-extras-command-not-found) 🥲 그래서 Amazon Linux 2023에서는 직접 `yum`으로 Docker를 설치해줘야 합니다!)

```bash
$ sudo amazon-linux-extras list
  0  ansible2                 available    [ =2.4.6  =2.8  =stable ]
  1  httpd_modules            available    [ =1.0  =stable ]
  2  memcached1.5             available    \
        [ =1.5.1  =1.5.16  =1.5.17 ]
  5  postgresql10             available    [ =10  =stable ]
  7  R3.4                     available    [ =3.4.3  =stable ]
  8  rust1                    available    [ =stable ]
 14  libreoffice              available    [ =5.3.6.1  =stable ]
 16  docker=latest            enabled      \
        [ =18.06.1  =18.09.9  =stable ]
 17  mate-desktop1.x          available    [ =stable ]
 18  GraphicsMagick1.3        available    \
        [ =1.3.29  =1.3.32  =1.3.34  =stable ]
 19  tomcat8.5                available    \
        [ =8.5.32  =8.5.38  =8.5.40  =8.5.42  =8.5.50  =stable ]
 20  epel                     available    [ =7.11  =stable ]
 21  testing                  available    [ =1.0  =stable ]
 22  ecs                      available    [ =stable ]
 23  corretto8                available    \
        [ =1.8.0_202  =1.8.0_212  =1.8.0_222  =1.8.0_232  =1.8.0_242
          =stable ]
 24  golang1.11               available    \
        [ =1.11.3  =1.11.11  =1.11.13  =stable ]
 25  squid4                   available    [ =4  =stable ]
 27  java-openjdk11           available    [ =11  =stable ]
 28  lynis                    available    [ =stable ]
 30  BCC                      available    [ =0.x  =stable ]
 31  nginx1=latest            enabled      [ =stable ]
 32  ruby2.6=latest           enabled      [ =2.6  =stable ]
 33  mock                     available    [ =stable ]
 34  postgresql11             available    [ =11  =stable ]
 36  python3.8                available    [ =stable ]
 37  lustre2.10               available    [ =stable ]
 38  haproxy2                 available    [ =stable ]
 39  collectd                 available    [ =stable ]
 40  R4                       available    [ =stable ]
  _  kernel-5.4               available    [ =stable ]
 42  selinux-ng               available    [ =stable ]
 43  php8.0                   available    [ =stable ]
 44  tomcat9                  available    [ =stable ]
 45  unbound1.13              available    [ =stable ]
 46  mariadb10.5              available    [ =stable ]
 47  kernel-5.10=latest       enabled      [ =stable ]
 48  redis6                   available    [ =stable ]
 49  ruby3.0                  available    [ =stable ]
 50  postgresql12             available    [ =stable ]
 51  postgresql13             available    [ =stable ]
 52  mock2                    available    [ =stable ]
 53  dnsmasq2.85              available    [ =stable ]
 54  aws-nitro-enclaves-cli   available    [ =stable ]
 55  livepatch                available    [ =stable ]
 56  kernel-5.15              available    [ =stable ]
 57  postgresql14             available    [ =stable ]
 58  firefox                  available    [ =stable ]
 59  lustre                   available    [ =stable ]
 60  php8.1                   available    [ =stable ]
 61  awscli1                  available    [ =stable ]
```

## ruby 설치

```bash
# 루비 설치
sudo amazon-linux-extras list | grep ruby
sudo amazon-linux-extras install ruby2.6
```

## docker 설치

```bash
sudo amazon-linux-extras list | grep docker
sudo amazon-linux-extras install docker

sudo usermod -a -G docker ec2-user
sudo setfacl -m user:ec2-user:rw /var/run/docker.sock
```

위의 과정을 모두 완료한 후에 터미널을 닫은 후 다시 접속 해줍니다!

## docker-compose 설치

```bash
sudo curl \
    -L https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o /usr/bin/docker-compose
sudo chmod +x /usr/bin/docker-compose
```

## nginx 설치

```bash
sudo amazon-linux-extras list | grep nginx
sudo amazon-linux-extras install nginx1
```

## aws-cli 설치

```bash
sudo amazon-linux-extras awscli1
```
