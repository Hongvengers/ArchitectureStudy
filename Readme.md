![Make_Clean_Architecture.jpg](..%2F..%2Ffor_velog%2FClean_Architecture%2FMake_Clean_Architecture.jpg)

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
그래서 우리 엔지니어들은 **코드들을 각 역할에 맞게** 뭉치를 만들어 **분리**해냅니다. 마치 서류철처럼 말이죠. <br>
이를 **계층화(Layering)** 라고 합니다.

---
# 계층화
