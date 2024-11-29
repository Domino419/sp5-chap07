# Chapter7. AOP 프로그래밍
#### 프로젝트 준비
#### 프록시와 AOP
#### AOP
#### 스프링 AOP 구현
#### 프록시 생성방식
#### @Around 의 Pointcut 설정과 @Pointcut 재사용



2024.11.29 00:48
chap07 프로젝트 준비 
 - pom에서 aspectjweaver 의존 추가
 - MySQL 설치 완료 / 데이터그립에 커넥션 설정 
 - git first commit !  

2024.11.29 08:59
#### 2.프록시와 AOP 
 * 프록시의 특징 : 공통 기능과 핵심 기능을 구분한다. 여러 객체에 공통으로 적용할 수 있는 기능을 구현한다.
- ImpeCalculator , RecCalculator - 팩토리얼을 구하는 핵심 기능 구현
- ExeTimeCalculator  실행시간 측정 구현

IllegalFormatConversionException = 문자열 형식이 잘못됐을때 나는 에러  

##### 2.1.AOP
AOP의 기본 개념 : 핵심 기능에 공통 기능을 삽입하는 것으로 세가지 방법이 있음
1. AOP 개발도구가 소스코드를 컴파일 하기 전에 공통 구현 코드를 소스에 삽입하는 방식 (AOP 전용도구 필요)
2. 클래스를 로딩할 때 바이트 코드에 공통 기능을 클래스에 삽입하는 방식  (AOP 전용도구 필요)
3. 런타임에 프록시 객체를 생성해서 공통 기능을 삽입하는 방법 ( 스프링이 기본적으로 제공하는 AOP방식)

```
 AOP 주요 용어 
Advice	       : 언제 공통 관심 기능을 핵심 로직에 적용할 지를 정의하고 있다. 
                 예를 들어 '메서드를 호출하기 전(언제)에 트랜잭션 시작’(공통 기능) 기능을 적용한다는 것을 정의한다.
Joinpoint	   :  Advice 를 적용 가능한 지점을 의미한다. 
                 메서드 호출, 필드 값 변경이 Joinpoint에 해당한다. 
                 스프링은 프록시를 이용해서 AOP를 구현하기 때문에 메서드 호출에 대한 Joinpoint 만 지원한다.
Pointcut       : Joinpoint 의 부분 집합으로서 실제 Advice 가 적용되는 Joinpoint 를 나타낸다 . 
                 스프링에서는 정규 표현식이나 AspectJ 의 문법을 이용하여 Pointcut 을 정의할 수 있다.
Weaving	Advice : 를 핵심 로직 코드에 적용하는 것을 weaving 이라고 한다.
Aspect	       : 여러 객체에 공통으로 적용되는 기능을 Aspect 라고 한다. 
                 트랜잭션이나 보안 등이 Aspect 의 좋은 예이다.
```
##### 2.2.Advice의 종류 
```
Before Advice	        : 대상 객체의 메서드 호출 전에 공통 기능을 실행한다.
After Returning Advice	: 대상 객체의 메서드가 익셉션 없이 실행된 이후에 공통 기능을 실행한다.
After Throwing Advice	: 대상 객체의 메서드를 실행하는 도중 익셉션이 발생한 경우에 공통 기능을실행한다.
After Advice	        : 익셉션 발생 여부에 상관없이 대상 객체의 메서드 실행 후 공통 기능을 실행한다.
                          (try-catch finally 의 finally 블록과 비슷하다.)
Around Advice	        : 대상 객체의 메서드 실행 전, 후 또는 익셉션 발생 시점에 공통 기능을 실행하는데 사용된다.
```
캐시 기능, 성능 모니터링 기능과 같은 Aspect를 구현할 때에는 Around Advice 를 주로 사용 


#### 3.스프링 AOP 구현 
스프링에서 AOP를 이용해서 공통 기능을 구현할 때 적용하는 방법
* Aspect로 사용할 클래스에 @Aspect 애노테이션을 붙인다 .
* @Pointcut 애노테이션으로 공통 기능을 적용할 Pointcut을 정의한다.
* 공통 기능을 구현한 메서드에 @Around 애노테이션을 적용한다.

#### 3.1 @Aspect, @Pointuct, @Around를 이용한 AOP구현 
공통 기능을 제공하는 Aspect 구현 클래스를 만들고 자바 설정을 이용해서 Aspect를 어디에 적용할지 설정하면 
프록시는 스프링 프레임워크가 알아서 만들어준다.

자바에서는 메서드 이름과 파라미터를 합쳐서 메서드 시그니처라고 한다,
메서드 이름이 다르거나 파라미터 타입, 갯수가 다르면 시그니처가 다르다고 표현한다.
자바에서 메서드의 리턴 타입이나 익셉션 타입은 시그니처에 포함되지 않는다.

