---
title: "EKS Cluster Setup 후기"
toc: true
toc_sticky: true
categories: ["Kubernetes", "AWS"]
---

AWS CLI를 통해 EKS 클러스터를 띄우고, AWS Access Key를 이용해 접속해보자! 처음에는 'EKS Cluster? 그거 그냥 띄우면 K8s 클러스터 띄우는 거 아닌가?'라고 만만하게 봤는데, 이런... EKS 클러스터를 띄우고 실제 접속까지 하는 과정이 순탄치 않았다. 이번에 예행 연습으로 경험해봤으니 이젠 자신있다!!

<br/>

EKS의 K8s 클러스터는 큰 관점으로 (1) 마스터 노드를 관리하는 클러스터와 (2) 워커 노드를 관리하는 노드 그룹으로 이뤄진다.

# (선택) Bastion 호스트 세팅하기

만약 AWS CLI를 본인 로컬에서 수행한다면, 상관 없는 부분이다. 본인은 Bastion 호스트를 하나 띄워서 해당 호스트에서 AWS CLI로 작업 했다. 이 경우 아래 명령어들로 패키지들을 설치할 필요가 있다.

```bash
# 기본 패키지 설치
sudo apt update && \
  sudo apt install -y jq \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    gettext \
    software-properties-common


# aws-cli 설치
sudo apt-get install awscli
aws --version

# kubectl 설치
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add - && \
    echo "deb https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee -a /etc/apt/sources.list.d/kubernetes.list && \
    sudo apt update && \
    sudo apt install -y kubectl=1.25.0-00
kubectl version
```

# AWS CLI 세팅하기

EKS 클러스터는 콘솔이 아닌 AWS CLI를 사용해 띄울 것이다. EKS 클러스터를 띄우는 권한이 있는 IAM 유저로 AWS CLI를 세팅하자. 이 단계에서는 일단 `Administrator` Role을 부여하는게 편했다.

중요한 점은 EKS 클러스터를 띄우면, 처음에는 해당 클러스터를 띄운 IAM 유저를 제외하고는 해당 EKS 클러스터에 접속할 수 없다는 점이다!! 그래서 AWS CLI 세팅을 어떤 IAM 유저로 하는지가 정말 중요하다!!!

# EKS 클러스터 띄우기

먼저 마스터 노드를 관리하는 EKS 클러스터부터 띄워보자.

먼저 EKS 클러스터에 부여할 IAM Role을 세팅하자. 이름은 `xxxx-eks-cluster-role` 정도로 설정하고 아래의 두 IAM Policy를 붙여주자.

- `AmazonEKSClusterPolicy`
- `AmazonEKSVPCResourceController`

그 이후엔 bastion 호스트에서 아래의 명령어를 치면 된다.

```bash
aws eks create-cluster \
	--region us-west-2 \
	--name xxxx-eks-cluster \
	--kubernetes-version 1.25 \
	--role-arn arn:aws:iam::12345678:role/xxxx-eks-cluster-role \
        --resources-vpc-config subnetIds=subnet-xxxx,subnet-xxxx,subnet-xxxx,subnet-xxxx
```

EKS 클러스터가 잘 띄워졌으면 `kubectl`로 접속해보자! 접속에 사용할 kubeconfig를 아래 명령어로 생성한다.

```bash
aws eks update-kubeconfig
	--region us-west-2
	--name xxxx-eks-cluster
	--kubeconfig ./xxxx-eks-config
```

만들어둔 `xxxx-eks-config`를 사용해서 접속을 테스트 한다.

```bash
$ kubectl get service
NAME         TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)   AGE
kubernetes   ClusterIP   10.111.0.1   <none>        443/TCP   2d19h
```

# EKS 노드그룹 띄우기

다음은 워커 노드를 관리하는 EKS 노드그룹을 띄우자!

<div class="notice" markdown="1">

참고로 노드그룹까지 띄워야 다른 AWS IAM User에게 EKS 클러스터 접속할 권한을 부여 해줄 수 있다!

</div>

이 EKS 노드그룹에도 IAM Role을 부여해야 한다. `xxxx-eks-nodegroup-role` 정도로 설정하고 아래의 IAM Policy를 붙여주자.

- `AmazonEKSWorkerNodePolicy`
- `AmazonEC2ContainerRegistryReadOnly`
- `AmazonEKS_CNI_Policy`

그 이후엔 bastion 호스트에서 아래의 명령어를 치면 된다.

```bash
aws eks create-nodegroup \
	--cluster-name xxxx-eks-cluster \
	--nodegroup-name xxxx-eks-nodegroup \
	--subnets subnet-xxxx subnet-xxxx \
	--node-role arn:aws:iam::12345678:role/xxxx-eks-nodegroup-role
```

# EKS 클러스터 접속 권한 설정

EKS 클러스터는 K8s 클러스터에 접속하는 IAM User/Role의 권한을 `kube-system` 네임스페이스에 있는 `configmap/aws-auth`에서 관리한다. 요 ConfigMap은 EKS 노드그룹이 EKS 클러스터에 붙으면 자동으로 생성되는 리소스다!

`kubectl get configmap aws-auth -n kube-system -o yaml`로 내용을 확인하면 아래와 같다.

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: aws-auth
  namespace: kube-system
data:
  mapRoles: |
    - groups:
      - system:bootstrappers
      - system:nodes
      rolearn: arn:aws:iam::12345678:role/xxxx-eks-nodegroup-role
      username: system:node:{{EC2PrivateDNSName}}
```

처음에는 nodegroup을 만들때 부여한 IAM Role만 등록되어 있다! 이제 다른 IAM 유저와 Role을 등록해보자!

```yaml
data:
  mapRoles: |
    - groups: ...
    - groups:
      - system:masters
      rolearn: arn:aws:iam::12345678:role/Administrator
      username: adminRole
  mapUsers: |
    - groups:
      - system:masters
      userarn: arn:aws:iam::12345678:user/BlueHorn
      username: bluehorn
```

IAM 유저를 등록한다면 `data.mapUsers.groups[*]`에 등록하면 되고, IAM Role을 등록한다면 `data.mapRoles.groups[*]`에 등록한다.

<hr/>

# 후기

## vs. EC2에 직접 K8s 클러스터 구축

CKA 시험 때 연습 했던, 노드 머신에 `kubeadm`으로 직접 노드 등록해서 K8s 클러스터 구축하는 것보다는 훨씬 쉽다! `aws eks` 커맨드를 3번만 입력해서 K8s 클러스터 만들었으니 훨씬 편한셈.

## vs. AWS ECS

이건 사실 AWS ECS랑 K8s랑 비교도 같이 들어갈 수 밖에 없긴 하다. 그러나 AWS에서 셋업하는 것만 비교하면 AWS EKS 쪽이 클러스터를 구축하고 Deployment를 띄우는데 걸리는 시간이 훨씬 빠르다! 이러다가 영영 AWS ECS는 안 쓰게 되버리는 걸까? ㅠㅠ 아직 AWS ECS 띄우는 건 포스트로 정리를 못 했는데 얼른 정리해야 겠다.

## 삽질의 기록...

사실 EKS 클러스터 띄우는 걸 한번에 성공한 건 아니다.

<div markdown="1" style="margin-bottom: 14px">

1\. AWS 콘솔에서 띄우기

EKS 클러스터 띄우면 처음엔 클러스터를 띄운 유저만 접속 가능하단 걸 몰랐어서, AWS 유저의 Access Key 받고 다시 띄워야 했다 ㅠㅠ

</div>

<div markdown="1" style="margin-bottom: 14px">

2\. `eksctl`로 띄우기

왜 인지 모르겠는데 EKS 노드그룹 띄울 때 CloudFormation 오류가 나서 실패했다;; 결국엔 정통 방식인 AWS CLI로 띄웠다!

</div>

## 다음은 뭘 해볼까?

1. ECS 클러스터 셋업 후기 작성
2. EKS 클러스터를 테라폼으로 띄우기
3. EKS 클러스터 접속 권한도 테라폼으로 설정해보기
