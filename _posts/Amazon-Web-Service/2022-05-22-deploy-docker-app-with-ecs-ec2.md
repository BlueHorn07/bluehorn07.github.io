---
title: "ECS/EC2로 Docker App 배포하기"
layout: post
tags: [AWS]
use_math: true
---

### 서론
이 글은 정리를 위해 개인적인 용도로 작성된 포스트입니다. 지적과 조언은 언제나 환영입니다 ㅎㅎ

<hr/>

Docker Image에 담긴 App을 AWS ECS를 이용해 배포해보자! 👏 

배포를 진행할 Dockerfile과 Docker Image는 본인이 미리 준비한 상태여야 한다. 또, VPC, 서브넷, 보안그룹과 같은 AWS의 네트워킹의 기본을 알고 있어야 한다. (모른다면 44Bits의 [VPC 포스트](https://www.44bits.io/ko/post/understanding_aws_vpc)를 정독하고 오자.) 또, AWS IAM의 개념과 EC2 인스턴스를 생성하고 관리하는 것에 익숙해야 한다.


## AWS ECS 기본 용어

먼저 AWS ECS에 대한 기본 용어를 알아야 하는데, 아래 용어들이 뭔지 서로 구분할 수 있어야 한다.

- 클러스터(cluster)
- 컨테이너 인스턴스(container instance)
- 태스크(task)와 태스크 정의(task definition)
- 서비스(service)

44Bits의 ["AWS ECS로 시작하는 컨테이너 오케스트레이션"](https://www.44bits.io/ko/post/container-orchestration-101-with-docker-and-aws-elastic-container-service) 포스트에 ECS에 대한 개념이 아주 친절하게 잘 설명되어 있다. ECS 배포를 시도하기 전에 꼭 읽고 오자. 44Bits의 포스트에선 AWS CLI를 이용해 ECS 배포를 시도하는데, 본인은 AWS 콘솔을 이용해 배포를 진행해보겠다

## AWS ECS 배포

### 단일 인스턴스, 단일 태스크 배포

먼저 하나의 태스크를 배포해보겠다.

<div class="img-wrapper">
  <img src="{{ "/images/amazon-web-service/ecs-deploy-1.png" | relative_url }}" width="90%">
</div>

#### 클러스터와 컨테이너 인스턴스 생성

ECS 콘솔에서 "클러스터" 탭 -> "클러스터 생성" 클릭

<div class="img-wrapper">
  <img src="{{ "/images/amazon-web-service/ecs-deploy-2.png" | relative_url }}" width="90%">
</div>

"EC2 Linux + 네트워킹" 선택, AWS Fargate를 쓰고 싶다면, 왼쪽의 "네트워킹 전용"을 선택한다.

<br/>

<div class="img-wrapper">
  <img src="{{ "/images/amazon-web-service/ecs-deploy-3.png" | relative_url }}" width="100%">
</div>

이름은 `ecs-single-deploy-demo` (추후에 멀티 인스턴스 배포를 할 때와 구분되어야 함), 인스턴스 유형은 가장 싼 `t2.small`. 인스턴스 갯수는 꼭 `1`로 설정한다. 인스턴스 2개 이상인 경우와 비교하기 위해 이번에는 1개만 쓴다.

<br/>

<div class="img-wrapper">
  <img src="{{ "/images/amazon-web-service/ecs-deploy-4.png" | relative_url }}" width="100%">
</div>

평범한 EC2 인스턴스 생성할 때처럼 컨테이너 인스턴스를 만들 때도 VPC와 서브넷, 보안그룹(Security Group) 설정을 해줘야 한다. 새로 생성할 수도 있고, 기존의 VPC를 쓸 수도 있다. 본인은 나중에 정리하기 귀찮아서 default VPC에 연결했다. 서브넷은 적당히 원하는 만큼 추가해준다. 보안그룹도 지금 새로 생성할 수도 있고, 기존 SG를 쓸 수도 있다.

<br/>

<div class="img-wrapper">
  <img src="{{ "/images/amazon-web-service/ecs-deploy-5.png" | relative_url }}" width="100%">
</div>

마찬가지로 해당 컨테이너 인스턴스가 쓸 IAM role도 설정해줘야 한다. 이때, IAM role에는 `AmazonECSTaskExecutionRolePolicy`라는 policy가 있어야 한다. 만약 없다면 후다닥 하나를 만들고 오자! 컨테이너 인스턴스로 태스크를 실행할 것이기 때문에 `AmazonECSTaskExecutionRolePolicy`가 필요한 것이다.

<br/>

<div class="img-wrapper">
  <img src="{{ "/images/amazon-web-service/ecs-deploy-6.png" | relative_url }}" width="100%">
</div>

클러스터가 잘 만들어졌다면 성공! "클러스터 보기"를 눌러 클러스터에 접속한 후 "ECS 인스턴스" 탭에서 컨테이너 인스턴스가 잘 생성된 걸 확인하자.

<div class="img-wrapper">
  <img src="{{ "/images/amazon-web-service/ecs-deploy-7.png" | relative_url }}" width="100%">
</div>

<div class="img-wrapper">
  <img src="{{ "/images/amazon-web-service/ecs-deploy-8.png" | relative_url }}" width="100%">
</div>

이 컨테이너 인스턴스는 EC2 인스턴스이기 때문에 EC2 콘솔에 들어가면 새로운 EC2 인스턴스가 생성된 걸 확인할 수 있다. 만약, 인터넷에 접속할 수 있는 VPC라면 public IP도 가지고 있을 것이다. 또, **컨테이너 인스턴스이기 때문에 EC2 인스턴스에 도커가 이미 깔려있다!**

### 태스크 정의

태스크 정의는 "태스크"의 구성이 정의도어 있다. **클러스터와 독립적인 존재**다. 

ECS 콘솔의 "작업 정의" 탭에서 "새 작업 정의 생성" 클릭. EC2 인스턴스 위에서 돌릴 것이니 호환성으로 `EC2` 선택.

<div class="img-wrapper">
  <img src="{{ "/images/amazon-web-service/ecs-deploy-9.png" | relative_url }}" width="100%">
</div>


## 멀티 인스턴스 배포



인스턴스 갯수 2개 이상이면 LB 설정을 해줘야 하나?

음.... 귀찮다!! 시험 보고 난 다음에 정리하자!

### 로드 밸런서 설정

ALB는 트래픽을 지정된 타깃 그룹(Target Group)으로 보내줌.

로드 밸런서와 타킷 그룹을 준비해야 함. 로드 밸런서와 타깃 그룹은 독립적으로 생성할 수 있음.

