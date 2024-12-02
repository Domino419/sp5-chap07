package main;


import chap07.Calculator;
import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect {
    public static void main(String[] args ) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class) ;

        Calculator cal = ctx.getBean("calculator", Calculator.class) ;
        long fiveFact = cal.factorial(5)  ;
        System.out.println("cal.factorial(5) = "+ fiveFact );
        System.out.println(cal.getClass().getName());
        ctx.close();
    }
}

/*
에러해결
        -AOP 설정이 제대로 되어 있지 않아서
        리스트 7.7 ~ 프록시 클래스가 출력되어야 하는데 원본 클래스 이름이 출력됨.
        .AppCtx에서 @EnableAspectJAutoProxy 설정 확인  ok
        .포인트컷을 제대로 기재했는지 확인  - 패키지명 chop07로 오타, chap07로 수정 후 정상 작동

        출력 결과 :
        RecCalculator.factorial([5]) 실행 시간 : 24100 ns
        cal.factorial(5) = 120
        com.sun.proxy.$Proxy17

*/