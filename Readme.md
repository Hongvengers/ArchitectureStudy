![Make_Clean_Architecture](https://github.com/Hongvengers/ArchitectureStudy/assets/66003338/17b06dba-64ca-4fe8-8859-d74ae18667bf)


> [📖] [만들면서 배우는 클린 아키텍처](https://wikibook.co.kr/clean-architecture/) <br>
> 해당 문서는 위의 서적을 읽고 개인적으로 정리한 내용입니다. <br>
> 그 외 Reference: [Clean Code Blog - Clean Architecture](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

# Clean Architecture

## Architecture?
클린 아키텍처가 무엇인지 정의하기에 앞서, 아키텍처란 무엇인가? <br>
일단 아키텍처(Architecture)란 우리가 만들고자 하는 서비스 혹은 애플리케이션의 구성요소 및 구성요소간의 관계, <br>
외부(Client)와 구성요소간의 관계 등을 정의하고 설명하는 '설계도' 같은 개념이라고 정의하겠습니다. <br>
<br>
그럼 클린 아키텍처 라는 것은 서비스의 설계도가 Clean한 것... 이라고 정의할 수 있겠습니다. <br>
뭐가 Clean 하길래, 클린 아키텍처 일까요? <br>

## 개요
클린 아키텍처의 특징을 다음과 같이 정의할 수 있습니다.
- 프레임워크와 독립적이다.
- Testable 하다.
- UI와 독립적이다.
- 데이터베이스(영속성)과 독립적이다.
- 그 외의 외부 세계와 독립적이다.

위와 같이 정의한 대로 **외부 인터페이스 및 프레임워크와 독립적**이며, **비즈니스 로직은 영속성에 영향을 주지 않고**, <br>
각 구성요소들이 **테스트하기 용이**하며, **확장성이 좋은** 구조를 갖도록 설계된 아키텍처가 Clean 하다고 할 수 있겠습니다. <br>
<br>
> 물론 각 조직 및 도메인에 따라 좋은 아키텍처는 다를 수 있음을 밝힙니다. <br>
> 하지만 기본적으로 의존성 및 결합도(Coupling)를 낮추며, 확장에 용이하도록 설계하는 것이 클린 아키텍처의 본질입니다.

## 적용해볼까?
이제 클린 아키텍처가 무엇인지 알게 되었고 좋은 서비스를 만들기 위해 적용해보려고 합니다. <br>
하지만 노하우가 없는 상태로 쌓아올리려 하니 도통 감이 잡히질 않습니다. <br>
그래서 우리 엔지니어들은 **코드들을 각 역할(관심사)에 맞게** 뭉치를 만들어 **분리**해냅니다. 마치 서류철처럼 말이죠. <br>
이를 **계층화(Layering)** 라고 합니다.

# 계층형 아키텍처
계층형 아키텍처(Layered Architecture)란 소스코드의 역할 및 **관심사에 따라** 각각 계층 이라는 틀로 **분리**한 아키텍처 입니다. <br>
n-Tier 아키텍처 라고도 하며, 계층의 수를 직접적으로 제한하지는 않지만 일반적으로 3가지의 계층으로 나뉘어서 설계하게 됩니다. <br>
<br>
![layered_architecture](https://github.com/Hongvengers/ArchitectureStudy/assets/66003338/30a02110-57b4-4dc0-b750-27b51ec8eb97) <br>
간략하게 표현하면 계층형 아키텍처는 위와 같은 구조를 갖게 됩니다.
### Presentation Layer(표현 계층)
- 클라이언트로부터 **요청을 받아**, 도메인 계층으로의 **전달을 담당**하는 계층입니다.
- Presentation Layer의 관심사는 보통 아래와 같습니다.
  - 클라이언트 요청에 대한 최소한의 검증을 진행한다. 
  - 어떤 디아비스로 접근을 해도(User-Agent) 정해진 형태의 정보로 정확하게 Domain Layer에 전달한다.

### Domain Layer(도메인 계층)
- 비즈니스 로직에 해당하는 계층입니다.
- Domain Layer는 Presentation Layer로부터 요청을 전달받아 비즈니스 로직을 수행합니다. <br>
- Domain Layer의 관심사는 오직 **로직 수행**에 있습니다.

### Persistence Layer(영속성 계층)
- 데이터베이스에 기록될 데이터들의 정보를 담습니다.
- 데이터를 데이터베이스에 저장하기 위한 역할을 수행하는 계층입니다.

> 클린 아키텍처에서는 Persistcne / Infrastructure 에 가까울 수록 고수준(High-Level), Client 에 가까울 수록 저수준(Low-Level) 이라고 표현합니다. <br>
> 원칙대로 설계했다는 전제하에, 고수준 계층은 저수준 계층의 변경사항에 영향을 받지 않습니다. <br>
> 한마디로, **의존성의 흐름은 저수준에서 고수준**으로 흐르게 됩니다.

## 이제 적용해보자
이제 계층형 아키텍처가 무엇인지 ~~대략적으로~~ 알게 되었으니 계좌(Account)라는 도메인으로 Java Spring 프로젝트를 구성해보겠습니다.
```text
└─layered
    ├─controller
    │      AccountController.java   # Presentation Layer (일반적으로 Java Spring 프로젝트에서는 Controller가 요청을 받도록 한다.)
    ├─domain
    │  ├─dao
    │  │      AccountRepository.java
    │  ├─dto                        
    │  │      AccountData.java
    │  └─entity                     # Persistence Layer 
    │          AccountEntity.java         # (Java Spring 에서는 Entity 클래스를 주로 영속성 데이터베이스 테이블과 1:1로 매핑시킨다. ≈ ORM)
    └─service
            AccountService.java     # Domain Layer (Controller 가 받은 요청을 비즈니스 로직으로 처리)
```
각 클래스들을 계층별로 분리하여 구성해놓으니, 한눈에 들어옵니다. <br>
이제 서비스에 장애가 발생하여도 ~~디버깅에 대한 처리가 잘 되었다는 가정하에,~~ <br>
어떤 계층에서 발생하였는지 빠르게 식별이 가능하고, 수정이 용이하게 됩니다. <br>
<br>
여기에 가계부(AccountBook)을 추가하도록 하겠습니다. 그럼 아래와 같은 구조가 됩니다. <br>
```text
└─layered
    ├─controller
    │      AccountBookController.java
    │      AccountController.java
    ├─domain
    │  ├─dao
    │  │      AccountBookRepository.java
    │  │      AccountRepository.java
    │  ├─dto
    │  │      AccountBookData.java
    │  │      AccountData.java
    │  └─entity
    │          AccountBookEntity.java
    │          AccountEntity.java
    └─service
            AccountBookService.java
            AccountService.java
```
이처럼 구성요소가 많아져도 계층끼리 분리되어 있기 때문에 수 많은 코드더미들 사이에서 길을 잃지 않을 수 있게되었습니다. <br>
<br>
> ### Example Case
> _사용자들에게서 가계부에 엉뚱한 기록이 있다고 문의를 받았다._ <br>
> -> 데이터에 관한 문의가 들어왔으니, Persistence Layer에서 AccountBookRepository를 확인하거나,<br>
> Domain Layer에서 AccountBookService를 확인하여 수정하면 된다는 것을 바로 인지할 수 있다.

> ⚠️ 주의! <br> 
> 위의 사례 에서는 레파지토리 수정을 예시로 들었지만, 기본적으로 Persistence Layer는 고수준 계층이므로, <br>
> 가능하다면 Service 쪽 로직을 수정하는게 더욱 바람직합니다.

이후 추가 요구사항이 들어옵니다. <br>
> _기획자: Account의 신용등급에 따라 송금, 인출의 수수료를 부여해주세요~_ <br>
> 위는 비즈니스 로직에 대한 요구사항이므로, Domain Layer에서 AccountService의 로직을 수행하면 되겠습니다. <br>
> 하지만 우리는 여기서, '신용등급' 이라는 요소가 Account뿐 아닌 다른 도메인에도 요구사항이 들어올 수 있을 것 같다고 직감을 하게됩니다. <br>
> 그래서 결국 Domain Layer에 신용등급과 관련된 역할을 수행하는 Util을 붙여서 작업할 수 있습니다. (편의상 SomethingUtil 으로 명명)

위의 요구사항대로 작업한 결과 우리는 아래와 같은 구조로 서비스를 운영하게 됩니다. <br>
![layered_architecture_package.png](..%2F..%2Ffor_velog%2FClean_Architecture%2Flayered_architecture_package.png)

위처럼 계층형 아키텍처로 하나의 서비스를 설계 해보았습니다. <br>
물론 실제 서비스는 위처럼 단순하지는 않겠지만, 보편적으로 계층형 아키텍처는 해당 골자에서 크게 벗어나지 않습니다.

## 테스트는?
이제 계층들이 각각의 관심사에 따라 분리되어 있으니, 각 계층은 그에따른 단위 테스트만 진행해주면 동작에 대한 보장을 할 수 있게 되었습니다. <br>
<br>
표현 계층에서는 사용자에 대한 요청을 받아서 다른 계층으로의 전달을 제대로 수행하는지에 대한 테스트만 이루어지면 됩니다. <br>
(Selenium을 쓰던, Cypress를 쓰던, CLI로 HTTP Reqeust를 날리던 상관없다. 말그대로 해당 모듈이 자신의 책임을 다하는지만 보장되면 된다.) <br>
<br>
도메인 계층에서는 로직에대한 수행을 테스트코드로 검증을 진행합니다. <br>
<br>
영속성 계층도 데이터베이스로의 CRUD작업의 UseCase가 정상적으로 동작하는지만 보장되면 됩니다. <br>
<br>
> 테스트의 퍼포먼스를 높이기 위해 In-Memory DB를 사용하거나, <br>
> 테스트에 사용될 객체가 모호할 경우 Test Double을 쓰거나 등 <br>
> 테스트에 관한 고민이 많겠지만, 해당 문서는 클린 아키텍처에 대한 글이니 넘어가겠습니다!


## 하지만...
계층형 아키텍처에서도 문제가 존재한다.

### 계층형 아키텍처는 데이터베이스 주도 설계를 유도한다
