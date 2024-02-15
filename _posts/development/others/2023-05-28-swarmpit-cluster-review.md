---
title: "Swarmpit 클러스터 구축기"
toc: true
toc_sticky: true
categories: ["Develop"]
tags: ["Docker"]
---

Docker에서 제공하는 컨테이너 오케스트레이션 기능인 Docker Swarm, 그리고 Swarm Cluster를 관리하는 Web UI인 Swarmpit을 사용해보고 실험해본 걸 정리해보았다.

# 왜 도커 스웜인가?

<div class="img-wrapper">
  <img src="{{ "/images/development/docker-swarm/docker-swarm-and-swarmpit-logo.png" | relative_url }}" width="320px">
</div>

컨테이터 오케스트레이션 도구로 유명한 양대산맥은 Kubernetes와 AWS ECS 둘이다.
회사에선 Kubernetes를 사용하고 있어서 동아리에서도 그대로 K8s를 쓰면 좋겠지만... 비용이 너무 비싸다... ㅠㅠ 또 K8s는 클러스터 동작 원리와 리소스들, 그리고 K8s 패턴을 익히는데 시간이 많이 들어서 동아리 수준에서는 운영과 유지/보수가 도저히 안 될거라고 생각했다.

다음으론 AWS ECS를 검토 했는데... 사실 가장 AWS-naitive한 도구이지만 작업 정의(Task Definition)라던가 ECS Service를 세팅하는게 도저히 손에 익질 않았다. 왜 그런가 이유를 생각해보면 ECS는 도커 배포를 정의하는게 모두 AWS 콘솔 상에서 이뤄져서 전체 구조를 보는걸 방해하는 것 같다. 그냥 '어... 가이드를 따라가다보니 됐네!'의 느낌이었다.

이렇게 K8s와 ECS 중에 고민하다가 친구가 썼던 "도커 스웜"이 생각 났다! 주말 이틀을 써서 도커 스웜을 익히고 직접 스웜 클러스터를 띄워서 배포를 위한 CD를 구축하면서 감을 익혔는데 너무너무 만족 스러웠다!! 자세한 내용은 뒤의 후기에 적도록 하고, 이제 가장 docker-native한 컨테이너 오케스트레이션 도구인 도커 스웜에 대해 살펴보자!


# 클러스터 셋업

먼저 마스터 노드를 세팅해야 한다. 마스터 노드는 `t3.small`(2vCPU, 2GB Mem)에서 진행했다. 더 낮은 타입의 인스턴스에서도 세팅 해봤는데, `small` 정도는 되어야 Docker Swarm과 Swarmpit을 안정적으로 운영할 수 있었다.

가장 먼저 노드에 Docker를 설치해야 하는데, 설치 방법은 [요 포스트](https://velog.io/@osk3856/Docker-Ubuntu-22.04-Docker-Installation)를 참고하자!

설치 후엔 `docker swarm init`으로 스웜 모드를 켜주면 된다!

```bash
$ docker swarm init
Swarm initialized: current node (xxxxx) is now a manager.

To add a worker to this swarm, run the following command:

    docker swarm join --token xxxx 172.31.48.43:2377

To add a manager to this swarm, run 'docker swarm join-token manager' and follow the instructions.
```

Docker Swarm 클러스터에 워커를 등록하기 위해선 이 토큰 정보가 필요하니 따로 메모해두자!

이제 [Swarmpit 홈페이지](https://swarmpit.io/)에서 Swarmpit 실행 명령어를 확인하고, 입력한다.

```bash
docker run -it --rm \
  --name swarmpit-installer \
  --volume /var/run/docker.sock:/var/run/docker.sock \
  swarmpit/install:1.9
```

작성 기준으론 `1.9` 버전이 stable 버전이었다. 다만, Swarmpit 웹에서 Config를 생성하는 쪽에 오류가 있어서 `1.9` 버전 대신에 `edge` 버전을 설치해 사용했다. 확인해보니 `v1.10-850a7f9` 버전을 쓰고 있었다.
설치 후에 `888` 포트로 접속하면 요런 swarmpit에 접속할 수 있다!

<br/>

<div class="img-wrapper">
  <img src="{{ "/images/development/docker-swarm/swarmpit-resource.png" | relative_url }}" width="100%">
</div>

요게 Swarmpit 만을 실행 했을 때의 리소스 정보다! 요걸로 대략적인 스펙을 확인할 수 있는데,

- CPU: 0.75
- Mem: 530 Mb
- Disk: 3.8 GB

이 정도라면 2 vCPU, 1GB Mem의 `t3.micro` 수준으로도 Swarmpit을 띄워서 Swarm 클러스터를 운영할 수 있지만... 처음에 `t3.micro`로 마스터 노드를 세팅해 사용해보니 메모리 쪽에서 부하가 있는지 Swarmpit 웹이 죽어버리는 일이 너무 잦았다. 결국 `t3.small` 사이즈로 올려서 사용했더니 Swarmpit 웹 장애가 해결되었다.

## Graviton에서는 Swarmpit이 안 뜬다...

ARM 기반의 AWS Gravition에서는 Swarmpit을 띄우려고 해도 뜨질 않았다...

```text
[ec2-user@ip-xxxx]$ docker service ls
ID             NAME                MODE         REPLICAS   IMAGE                      PORTS
296nchia0reb   swarmpit_agent      global       1/1        swarmpit/agent:latest
aa124s3a8dvn   swarmpit_app        replicated   0/1        swarmpit/swarmpit:latest   *:888->8080/tcp
ps4wx21usdrh   swarmpit_db         replicated   0/1        treehouses/couchdb:2.3.0
byy7k2bfjun7   swarmpit_influxdb   replicated   1/1        influxdb:1.7
```

확인 해보면 `couchdb:2.3.0`이 뜨질 않는데, 요게 ARM이랑 안 맞는 것 같다...

그래도 Worker 노드에 join하는건 Graviton 인스턴스도 가능했다!

<hr/>

# Docker Stack & Service

Docker Stack과 Service 둘다 Docker Swarm에서 도입된 개념이다. Docker Stack 안에 여러 Docker Service가 있는 구조다. 사실 Docker Stack은 docker-compose와 거의 비슷한 문법을 따른다. 아래는 간단한 server-db의 docker-compose 파일이다.

```yaml
version: '3.8'
services:
  api:
    image: my-server
    ports:
      - "4000:4000"
  database:
    image: mysql
    ports:
      - "3306:3306"
```

docker-compose 파일을 보면 `services` 항목이 있는데 이게 곧 "Docker Service"다! 또, 지금의 docker-compose 파일을 그대로 사용해서 Docker Stack을 생성할 수도 있다!

Docker Stack/Service는 docker-compose과 비교해서 더 강력한 기능들을 제공한다! 도커 스웜을 사용하면서 경험한 그 기능들을 몇개 소개해보겠다!

## Docker Service: deploy

Docker Service는 deploy 항목이 있어 CD(Continuos Deploy)가 가능하다! 도커 스웜의 가장 큰 메리트 바로 이 `autoredeploy` 기능이다!

```yaml
version: '3.8'
services:
  server:
    image: my-server:latest
    deploy: # 여기를 주목!
      labels:
        swarmpit.service.deployment.autoredeploy: 'true'
      placement:
        constraints:
         - node.role != manager
         - node.labels.application == my_app
```

<div class="img-wrapper">
  <img src="{{ "/images/development/docker-swarm/docker-swarm-service-deployment.png" | relative_url }}" width="75%" style="min-width: 360px">
</div>

`swarmpit.service.deployment.autoredeploy` 기능은 도커 이미지의 업데이트를 모니터링 해서 자동으로 최신 이미지로 컨테이너를 새로 Deploy 한다.

<div class="img-wrapper">
  <img src="{{ "/images/development/docker-swarm/docker-swarm-node-label.png" | relative_url }}" width="75%" style="min-width: 360px">
</div>

`placement`로는 해당 도커 컨테이너가 스웜 클러스터의 어느 노드에 떠야 하는지, 그 조건을 설정할 수 있다. 위의 코드에서는 서버가 (1) manager 노드가 아니고 (2) `my_app` 어플리케이션 전용 노드에만 컨테이너가 뜰 수 있다.


## Docker Service: secret

어플리케이션을 띄울 때 Credential을 주입하는 건 늘 머리 아픈 일이다. 그렇다고 컨테이너를 정상적으로 띄우기 위해 Credential을 도커 이미지에 포함한다거나, 컨테이너를 띄운 후에 컨테이너에 접속해서 Credential을 세팅하는 것도 번거로운 일이다.

도커 스웜 이전의 docker-compose에서는 `env_file` 항목을 사용해서 로컬에 있는 `.env` 파일을 환경 변수로 주입할 수 있었다.

```yaml
version: '3.8'
services:
  server:
    image: my-server:latest
    env_file:
      - .env.production
```

그러나 스웜 클러스터를 사용하게 되면서 Docker Service가 여러 노드에 분산해서 뜰 수 있게 되면서 `env_file`에서 가져가는 `.env` 파일이 마스터 노드에 있어야 하는지 워커 노드에 있어야 하는지도 어렵다.

도커 스웜에서는 Docker Secret을 도입해 여러 Swarm Stack/Service에서 먼저 정의된 Secret을 가져다 쓸 수 있도록 했다. 코드를 통해 살펴보자.

```yaml
version: '3.8'
services:
  server:
    image: my-server:latest
    secrets:
     - source: server_dotenv
       target: /usr/src/app/.env

secrets:
  server_dotenv:
    external: true
```

<div class="img-wrapper">
  <img src="{{ "/images/development/docker-swarm/docker-swarm-secret.png" | relative_url }}" width="75%" style="min-width: 360px">
</div>

이렇게 Swarmpit에서는 Secret을 생성할 수 있는데, `target` 경로를 잘 설정해서 도커 컨테이너의 특정 경로로 주입할 수 있다!

도커 Secret 말고 도커 Config라는 것도 있는데, Config는 plaintext를 저장할 때 쓰는 리소스다. Swarmpit에서도 생성 후에 평문 값을 볼 수 있고 수정도 가능하다. (Secret은 오직직 값 사용과 삭제만 가능하다)

<hr/>

# 네트워크 설정

어플리케이션을 도커 컨테이너로 띄운 후에 외부에서 어플리케이션에 접속할 수 있도록 설정하는 것도 중요하다. 보통 컨테이너로 어플리케이션을 띄우면 2가지를 직접 세팅해줘야 하는데 (1) 포트 매핑(port mapping) (2) Load Balancer 세팅이다.

포트 매핑이야 도커 컨테이너, docker-compose, 도커 스택까지 일관된 형식으로 정의한다. `[host-port]:[container-port]`

단, 하나의 host port에는 하나의 컨테이너만이 점유해야 하기 때문에 노드에 떠있는 어플리케이션 간에 포트 매핑이 겹치지 않도록 유의 해야 한다. 만약 하나의 노드에 prod app과 dev app을 동시에 띄우고자 한다면 prod app과 dev app을 노출하는 포인트를 달리 해야 할 것이다. (K8s에서는 포트가 겹쳐도 됐는데 ㅠㅅㅠ)

두번째인 Load Balancer 세팅에 대해서는 본인은 그렇게 규모가 크지도 않고 프론트와 서버, prod와 dev 모두 하나의 서버에 띄워도 별로 부하가 없기 때문에 사용하는 노드에 nginx를 설치해서 Load Balancer로 사용하고 있다. 단, "돈이 여유롭다면" AWS ALB를 세팅해서 노드 외부에 Load Balancing을 위임 해도 좋다. (K8s에서는 Ingress가 전부? 해주는데 ㅠㅠ)

<hr/>

# 사용 후기

K8s에 더 익숙한게 사실이지만 이번 경우에선 너무 오버 스팩으로 보였다. 도커 스웜도 K8s 못지 않게 필수적인 기능들은 제공하고 있으니 결과적으론 좋은 선택이었음!
도커 스웜 단독으로 써도 쓸만하지만 본인은 Swarmpit이 진짜 최고의 장점이다. 클러스터의 상태도 모니터링하고 App Deploy까지 웹에서 가능하니 너무 편하다... `docker-stack.yml`로 코드 베이스가 있기 때문에 ECS 콘솔에 비해서도 더 쉽게 익힐 수 있었다!

다만, Graviton 시리즈의 노드는 Swarmpit 설치도 안 되고, 워커로 쓰니 CPU/Mem 사용률이 나오지 않는 문제가 있지만... 이건 뭐 ARM 아키텍처의 문제니... 어쩔 수 없다. (ARM 너무 불편...)

이번에 써보고 너무 좋아서 학교 동아리 PoApper의 어플리케이션 전부를 Swarm 클러스터에서 돌릴 예정이다. 이제껏 "pm2" 아니면 "AWS CodeDeploy"로 배포-운영하고 있었는데, 부원들이 좋은 도구로 도커 스웜과 Swarmpit을 잘 써줬으면 좋겠다...!

<hr/>

# References

- [Ubuntu에 Docker 설치](https://velog.io/@osk3856/Docker-Ubuntu-22.04-Docker-Installation)
- [docker-compose vs. Docker Stack](https://log-laboratory.tistory.com/191)
