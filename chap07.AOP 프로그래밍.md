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

에러해결 
 -AOP 설정이 제대로 되어 있지 않아서 
리스트 7.7 ~ 프록시 클래스가 출력되어야 하는데 원본 클래스 이름이 출력됨.
.AppCtx에서 @EnableAspectJAutoProxy 설정 확인  ok
.포인트컷을 제대로 기재했는지 확인  - 패키지명 chop07로 오타, chap07로 수정 후 정상 작동

3.2 ProceedingJoinPoint 메서드
ProceedingJoinPoint 인터페이스가 제공하는 메서드
 Signiture getSignature () : 호출되는 메서드에 대한 정보를 구한다.
 Object getTarget() : 대상 객체를 구한다.
 Objectp[] getArgs() : 파라미터 목록을 구한다. 

org.aspectj.lang.Signature 인터페이스가 제공하는 메서드
String getName()  : 호출되는 메서드의 이름을 구한다.
String tolongString() : 호출되는 메서드를 완전하게 표현한 문장을 구한다(메서드의 리턴 타입 파라미터 타입이 모두 표시된다).
String toShortString() : 호출되는 메서드를 축약해서 표현한 문장을 구한다(기본 구현은 메서드의 이름만을 구한다)

4.프록시 생성 방식 
실습 
BeanNotOfRequiredTypeException: Bean named 'calculator' is expected to be of type 'chap07.RecCalculator' but was actually of type 'com.sun.proxy.$Proxy17'
 AOP 적용시 RecCalculator가 상속받은 Calculator 인터페이스를 이용해서 프록시 생성
 자바코드에서는 Calculator 빈의 실제 타입은 Calculator을 상속한 프록시 타입이므로
RecCalculator로 타입 변환을 할 수 없기 때문에 BeanNotOfRequiredTypeException 익셉션 발생

빈 객체가 인터페이스를 상속할 때 인터페이스가 아닌 클래스를 이용해서 프록시를 생성하고 싶다면
설정(AppCtx)에서 @EnableAspectJAutoProxy(proxyTargetClass = true) 으로 설정한다.

@EnableAspectJAutoProxy(proxyTargetClass = true)
CGLIB(Code Generation Library) 기반 프록시 사용: 클래스 자체를 상속받아 프록시를 생성합니다.
원본 클래스의 final 메서드는 오버라이드할 수 없으므로, 프록시가 적용되지 않음.
인터페이스 없이 클래스로만 구성된 코드에서도 AOP를 적용할 수 있음.
사용 시기:
AOP 적용 대상 클래스가 인터페이스를 구현하지 않은 경우.
JDK 동적 프록시가 아닌 CGLIB 기반 프록시를 강제로 사용해야 할 때.

proxyTargetClass = false (기본값)
JDK 동적 프록시 사용: 인터페이스를 구현한 객체만 프록시를 생성.
인터페이스가 필요하므로, AOP 적용 대상 클래스가 인터페이스를 구현해야 한다.
더 가볍고 메모리 사용량이 적다.

>>> CGLIB은 유연성이 높지만, 성능과 메모리 사용 측면에서는 JDK 동적 프록시가 더 효율적입니다. Spring은 이런 이유로 기본적으로 JDK 동적 프록시를 사용하고, 
> 필요할 때만 CGLIB을 사용하도록 설정합니다.

4.1 executation 명시자 표현식
execution( 수식어패턴 ? 리턴타입패턴 클래스이름패턴?메서드이름패턴(파라미터패턴))
수식어패턴: 생략 가능하며 public, protected 이 온다. 스프링 AOP는 public 메서드만 적용할 수 있기 때문에 사실상 public만 의미있다.
리턴타입패턴: 리턴 타입을 명시
클래스이름패턴, 메서드이름패턴: 클래스 이름 및 메서드 이름을 패턴으로 명시
파라미터패턴: 매칭될 파라미터 에 대해서 명시
//‘*’을 이용하여 모든 값을 표현, ‘..’(점 두개)을 이용하여 0개 이상이라는 의미를 표현.

4.2 Advice 적용 순서
>> IllegalArgumentException 에러 
> Pointcut is not well-formed: expecting 'name pattern cannot finish with ..' at character position 29
execution(public * chap07 .. *(Long)) 
> 표현식에서 앞뒤로 공백이 들어가거나 매게변수의 타입을 잘못 넣으면 발생하는 에러
>

MainAspectWithCache 에서 실행시 결과
```
RecCalculator.factorial([7]) 실행 시간 : 22200 ns
CacheAspect : Cache에 추가  [7]
--------------------------------------
CacheAspect: Cache에서 구함[7]
--------------------------------------
RecCalculator.factorial([5]) 실행 시간 : 6100 ns
CacheAspect : Cache에 추가  [5]
--------------------------------------
CacheAspect: Cache에서 구함[5]
--------------------------------------
```

같은 factorial([7]) 이어도 출력 결과가 차이나는 이유는 Advice 순서가 다음과 같아서임.
CacheAspect 프록시 > ExetimeAspect 프록시 > 실제 대상 객체  

ASpect의 적용순서는 스프링 프레임워크나 자바 버전에 따라 달라질 수 있으므로 
적용 순서가 중요한 경우에는 @Order로 직접 지정하여 사용 하여야 한다.

```
@Aspect 
@Order(1)   << 이렇게 
public class ExeTimeAspect { 
} 

@Aspect 
@Order(2)  << 이렇게 
public class CacheAspect { 
}
```

```
ㅇㅇ order로 주고 나서의 출력값 확인 

CacheAspect : Cache에 추가  [7]
RecCalculator.factorial([7]) 실행 시간 : 326900 ns
--------------------------------------
CacheAspect: Cache에서 구함[7]
RecCalculator.factorial([7]) 실행 시간 : 130800 ns
--------------------------------------
CacheAspect : Cache에 추가  [5]
RecCalculator.factorial([5]) 실행 시간 : 89800 ns
--------------------------------------
CacheAspect: Cache에서 구함[5]
RecCalculator.factorial([5]) 실행 시간 : 117900 ns
--------------------------------------
```

4.3 @Around 의 Pointcut 설정과 @Pointcut 재사용
@Around 애노테이션에 execution 명시자를 직접 지정할 수도 있다.


여러 Aspect에서 공통으로 사용하는 Pointcut이 있다면 
별도 클래스에 Pointcut을 정의하고, 
각 Aspect 에서 해당 Pointcut을 사용하도록 구성하면 Pointcut 관리가 편해진다.
