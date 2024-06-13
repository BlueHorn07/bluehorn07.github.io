---
title: "Games on AWS 2023 리뷰"
toc: true
toc_sticky: true
categories: ["AWS"]
excerpt: 발로란트, Marvel Sanp의 아키텍처와 AWS Serverless 트렌드, K8s Pod/Node Scaling
---

![](/images/development/Games-on-AWS-2023/hero.jpeg){: .align-center style="width: 40%;"}

작년에 이어 올해도 "Games on AWS"에 다녀왔다. 늘 삼성 코엑스에서 진행했었는데, 이번에는 판교에서 진행했다!

오랜만에 아침 일찍 지하철 타고 이동하려니 너무 피곤했다... ㅠㅠ

![](/images/development/Games-on-AWS-2023/table-of-content.png)

세션은 전반적으로 Game에 관련된 주제들인데, 흐음 의외로 작년 GoW 보다 들을게 없는 느낌이었다...;;

그래도 참석 했던 세션들을 재밌게 들었기에 세션 내용과 생각들을 정리해보겠다.

<hr/>

# 키노트 세션

## Marvel Snap

게임 "Marvel Snap"의 개발사인 "Second Dinner"에서 참석했다! 마블 IP로 만든 TCG 형식의 게임인데, 지하철 타고 출근하던 시절엔 출근길에 사람이 요 게임 하던걸 정말 많이 봤었다 ㅋㅋㅋ

키노트에선 "Second Dinner" 개발사와 "Marve Snap"에 대한 설명과 게임 개발 사이클과 AWS 좋아요~ 정도의 얘기만 했었다. 흥미가 생겨서 이후에 오후 세션에 참석도 했는데, 그 세션이 정말 재밌었다 ㅎㅎ

## Riot Games

![](https://images.contentstack.io/v3/assets/bltb6530b271fddd0b1/blt79971d6ef53d8a5f/5e8cdeaa07387e0c9bfff0c5/IMAGE_4.jpg){: .align-center style="width: 100%;"}

또 놀랍게도! Riot Games에서도 GoW 키노트 발표를 했다! LoL로 유명한 Riot Games이지만, 세션에서 재밌게 들었던 부분은 발로란트(Valorant) 개발 이야기 였다.

![](https://technology.riotgames.com/sites/default/files/netcodegiffinal.gif){: .align-center style="width: 100%;"}

"Peeker's Advantages"란 내용에 대해 설명했는데 요게 정말 재밌었다. FPS 장르에서 발생하는 현상인데, 그 이유는 서버와 상태를 싱크하는 과정에서 발생하는 Latency 때문이다.

peeker가 이동했다는 정보가 서버에 저장된 후에 holder가 서버로부터 peeker의 위치 정보를 받아오기 때문에 latency가 어쩔 수 없이 발생한다. 단, 발로란트에서 이런 peeker latency는 평균 40~70 ms 수준이라고 하며, 인간의 평균 반응 속도가 240 ms 수준이기 때문에 Peeker's Advantage로 얻는 효과를 그리 크지 않을 수 있다 ㅋㅅㅋ

## 그외

네오위즈와 NC의 발표가 이어졌는데, 네오위즈의 경우는 그동안 몇 년에 걸쳐 온프레미스 인프라에서 AWS 인프라로 이전 했다는 경험담이었고, NC는 자체 개발한 LLM인 "Varco LLM"에 대한 홍보가 기억에 남는다. NC 개발 LLM은 AWS Bedrock 통해서 사용할 수 있다고 한 것 같은데, 이번에 AWS Bedrock을 처음 봐서 한번 써보고 싶어졌다 ㅎㅎ

![](https://d2908q01vomqb2.cloudfront.net/2a459380709e2fe4ac2dae5733c73225ff6cfee1/2023/09/13/Picture1-8.png){: .align-center style="width: 100%;"}

AWS 기술 블로그에선 NC Varco LLM으로 한국어 chatbot 만드는 예제도 있다 ㅎㄷㄷ

- [VARCO LLM과 Amazon OpenSearch를 이용하여 한국어 Chatbot 만들기](https://aws.amazon.com/ko/blogs/tech/korean-chatbot-using-varco-llm-and-opensearch/)

<hr/>

# Marvel Snap - AWS 서버리스 만으로 올해의 모바일 게임 만들기

![](https://play-lh.googleusercontent.com/2dMbfjxLpac-DsDXIiiUASBinyhsHDcTbU5bpkGsH5Jxn29_ggpu739OMEtbrlra408=w526-h296-rw){: .align-center style="width: 100%;"}

지하철에서 많이 봤던 Marvel Snap ㅎㅎ 회사에도 요 게임 하는 사람이 있어서 구경 했었는데, 정말 잘 만든 게임이었다!! 하스스톤 개발자 출신이 만들었다는데 와... 하스를 이렇게 만들어주지... ㅠㅠ

제일 흥미로웠던 건, 게임 운영을 처음부터 끝까지 "Serverless" 하나로 모두 하고 있다는 거다!! Serverless가 장점이 있다고 해도 분명 한계가 존재할 터인데... hmm...

## Provisioned Concurrency

Marvel Snap에서는 중요한 기능에 대해선 "Provisioned Concurrency" 기능을 적용해 Serverless라도 빠른 응답을 제공한다고 한다. 즉, Lambda가 가진 "Cold Start"를 해결하는 방법이다.

물론 Lambda Container를 대시 시켜두기 때문에 기본 모드 보다 비용은 더 나간다.

## Lambda Optimization: Static Initialization

또, Serverless의 경우 내부적으로 초기 호출 후엔 Micro Container?가 살아있어 그후 호출부터는 응답 시간이 아주 줄어든다고 한다. 그래서 람다 코드를 어떻게 구성하느냐에 따라서 그후 응답 시간이 달라진다.

예를 들어, 아래 두 코드는 2nd request에서의 실행 시간이 다른다.

```py
# 1st code
def returnUsersVer1():
  db = database.connect(...)
  return db.getUsers()
```

```py
# 2nd code
db = database.connect(...)

def returnUsersVer2():
  return db.getUsers()
```

두 코드의 차이는 `db`라는 변수를 함수 아래의 local 변수로 두느냐 아님 global 변수로 두느냐이다. AWS Lambda에선 같은 execution environment를 갖는 두 lambda invocation 사이에선 global 변수의 값을 유지하기 때문에, 첫 요청에선 두 함수의 실행시간이 같아도, 주번째 요청부터는 `db` 변수를 global 변수로 지정한 Lambda 코드가 더 빠르다!

## AWS GameLift FlexMatch: Serverless Matching

TCG 게임의 경우, 상대와 맞붙기 때문에 매칭을 할 때 어떤 상대와 매칭 시켜주느냐가 중요하다. 예를 들어, 초보자와 고수 유저가 매칭 된다면, 초보 유저는 초반부터 게임에 대한 의욕을 잃어버릴 것이다.

전통적인 매칭 게임에서 매칭만을 전문적으로 하는 서버, 마이크로 서비스 또는 상용 매칭 서비스를 사용한다. 그런데, Marvel Snap에서는 매칭 조차 Serverless 서비스로 해결했다!!

AWS GameLift의 FlexMatch에선 매칭에 필요한 Configuration 설정만으로 정교한 매칭을 제공할 수 있다. 예를 들어, 아래와 같이 규칙셋을 지정할 수 있다.

```json
// 출처: https://docs.aws.amazon.com/ko_kr/gamelift/latest/flexmatchguide/match-examples.html
"rules": [{
    "name": "FairTeamSkill",
    "description": "The average skill of players in each team is within 10 points from the average skill of all players in the match",
    "type": "distance",
    // get skill values for players in each team and average separately to produce list of two numbers
    "measurements": [ "avg(teams[*].players.attributes[skill])" ],
    // get skill values for players in each team, flatten into a single list, and average to produce an overall average
    "referenceValue": "avg(flatten(teams[*].players.attributes[skill]))",
    "maxDistance": 10 // minDistance would achieve the opposite result
}, {
    "name": "EqualTeamSizes",
    "description": "Only launch a game when the number of players in each team matches, e.g. 4v4, 5v5, 6v6, 7v7, 8v8",
    "type": "comparison",
    "measurements": [ "count(teams[cowboys].players)" ],
    "referenceValue": "count(teams[aliens].players)",
    "operation": "=" // other operations: !=, <, <=, >, >=
}],
```

규칙을 보면, `measurements`, `referenceValue`에 SQL 구문이 있어 사용자 상태 정보에 따라 적절한 매칭 경험을 제공해줄 수 있다!

## 총평

Marvel Snap의 Serverless 아키텍처는 AWS SA가 극찬할 정도의 아키텍쳐 였다. 어쩌면 초기 게임, 변동성이 강한 게임을 기획하고 있다면, 기존의 on-demand 모델보다도 Sererless 모델이 더 적합할 것 같다는 생각을 하게 될 정도였다.

세션에서 말하길 클라우드 서비스가 처음 등장 했을 때, 가장 먼저 인프라를 온프레미스에서 클라우드를 채택한 업계가 게임 분야였다고 한다. 그러나 Serverless에 대해서는 오히려 게임 없계가 진전이 느리고, 최근에 클라우드 인프라를 구축하는 신규 업계가 더 Serverless 모델을 채택하는 추세라고 한다.

이번 Marvel Snap의 세션으로 Serverless에 대한 내 인식이 크게 바뀐 것 같다. 어쩌면 다음 사내 GameJam에서는 Serverless 모델을 채택하게 될 것 같다 ㅎㅎ

<hr/>

# Riot Games에서의 EKS 사용법

요즘의 가장 핫한 게임회사 Riot의 EKS 사용법에 대한 강의였다. Riot라는 이름 때문인지 가장 큰 강연장에서 했음에도 사람이 꽉 찼다!!

여러 내용이 있었던 것 같은데, 기억에 남는 내용은 EKS Scaling에 대한 내용이었다.

## 수평적 파드 확장(HPA)

회사에서 여러 K8s Cluster와 Object를 운영하면서, 요즘 Autoscaling에 대한 관심이 생겼다. K8s에서 AutoScaling을 지원하는게 여러 방식이 있지만 가장 먼저 떠오른건 요 "HPA"다.

![](/images/development/Games-on-AWS-2023/k8s-hpa.png)

가장 K8s-native한 AutoScaling 방식인 HPA는 `HorizontlPodAutoscaler` 컨트롤러를 통해 K8s Deployment를 자동으로 업데이트 하는 방식이다.

K8s 1.23부터 도입된 기능으로 CPU, Memory 지표에 따라 레플리카 갯수를 조정할 수 있다.

## K8s Event-driven Autoscaling(KEDA)

![](/images/development/Games-on-AWS-2023/keda-event-sources.png)

K8s HPA와 비슷한 개념으로 [KEDA(K8s Event-driven Autoscaling)](https://keda.sh/docs/2.8/concepts/)란 것도 있다. 자료를 찾아보니 K8s HPA 보다 더 다양한 방식으로 수평 확장이 필요할 때 채택하는 것 같다.

K8s HPA에선 CPU, Mem 수치만으로 AutoScaling을 운영하는데, KEDA를 사용하면, AWS SQS 지표, 웹 접속 이벤트, Cron, 심지어는 PromQL을 사용한 AutoScaling이 가능하다! 사실상 정교한 수평 확장을 달성 하기 위해선 KEDA 도입은 필수적인 것으로 보인다.

회사에서 예전에 한번 POC 해본 것 같은데, 요걸 사용하는게 그렇게 활성화 되어 있진 않다 ㅠㅠ

## Node Scaling: K8s CAS vs. Karpenter

위의 HPA와 KEDA는 K8s Deployment에서 Pod 수를 동적으로 조정하는 방법이다. 그런데, K8s Cluster의 노드 수를 동적으로 조정하는 방법도 있을가? 있다!

K8s에서는 이미 Node Auto Scaling 기능 "Cluster AutoScaler(CAS)"를 제공하고 있다.

K8s 클러스터의 Cloud Provider에 따라 CAS의 동작 방식이 다르지만, AWS에선 EC2 Auto Scaling Group을 사용해 Node Group 단에서 Auto Scaling이 이뤄진다. 클러스터의 `cluster-autoscaler`가 Pod의 상태를 모니터링 하면서, node 선정이 지속적으로 실패한다면, Node Group을 통해 신규 Worker 노드가 추가된다. 이때, 추가되는 Worker 노드는 Node Grouop에 정의된 템플릿의 instance 스펙을 따른다!

그럼 Karpenter라는 Node Scaler는 왜 등장했는가?

![](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2021/11/23/2021-karpenter-diagram.png)

Karpenter도 마찬가지로 Pod이 들어갈 적절한 노드가 없는 경우에 신규 노드를 추가한다. 단, Karpenter의 경우, NodeGroup을 기준으로 신규 노드가 추가되는 것이라 아니라 "**Pod의 용량에 맞는 가장 저렴한 노드**"를 생성해 사용하게 할 수 있다!! 게다가 신규 노드를 띄울 때, Spot Instance로 띄웠다가 fail시 On-Demand로 바로 전화하는 기능도 있다고 한다!

가다가 그외에도 K8s CAS 보다 노드 추가와 제거가 빠르다(1분 이내) 평가가 많아 JIT(Just-in-Time) 스케일러로 인식된다.

단, K8s CAS와 Karpenter 둘다 Node Scaler의 기능을 하기 때문에 둘을 함께 쓰는 건 비추라고 한다.

아마 팀에서는 K8s CAS로 NodeGroup 사용해 Node Scaling을 하는 것 같은데, 언젠가 Karpenter도 시험해보면 좋을 것 같다 ㅎㅎ

<hr/>

# 마무리: 컨퍼런스는 즐거워

AWS 컨퍼런스는 늘 그렇듯 많은 영감을 받고 가는 곳인 것 같다. 지금 우리 팀이 잘 하고 있는지 점검하고, 팀에 도입할 신규 기능은 뭐가 있을지, 또 어떤 방향으로 가야할지 생각하게 되는 것 같다.

![](/images/development/Games-on-AWS-2023/databricks-party.png){: .align-center style="width: 50%;"}

컨퍼런스 후에는 Databricks 측에서 After Party를 열어주셔서 다녀왔는데, 회사 사람들이랑 같이 앉았지만, 다른 개발팀의 이야기를 많이 듣고, 또 내가 궁금했던 것도 많이 질문할 수 있었다 ㅎㅎ 여기서도 진짜진짜 많은 영감을 받았다 ㅎㅎ 아 그리고 공짜 맥주와 무제한 메뉴도 너무 좋았다 🐷

판교 까지 가는게 쉽진 않았지만 판교의 회사 분위기도 느낄 수 있었다 ㅎㅎ 특히 개발 회사들 사옥 구경하는 재미가 쏠쏠 했다 ㅋㅋ 그래서 저번 주말에 한번더 판교 가서 주변 구경하고 화랑 공원에서 피크닉도 하고 왔다 🧺
