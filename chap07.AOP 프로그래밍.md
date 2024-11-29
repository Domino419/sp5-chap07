# Chapter7. AOP 프로그래밍
### 프로젝트 준비
### 프록시와 AOP
### AOP
### 스프링 AOP 구현
### 프록시 생성방식
### @Around 의 Pointcut 설정과 @Pointcut 재사용



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

#### 3.AOP 
