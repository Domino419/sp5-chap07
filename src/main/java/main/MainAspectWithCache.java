package main;

import chap07.Calculator;
import config.AppCtxWithCache;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * class         : MainAspectWithCache
 * date          : 24-11-29
 * description   : 캐시를 활용한 AOP 애스펙트가 적용된 Calculator 빈을 테스트하는 메인 클래스.
 *                  동일한 입력값에 대해 CacheAspect의 캐싱 동작을 확인하고 실행 시간을 측정.
 *                  AOP 프록시 실습에 사용
 */
public class MainAspectWithCache {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext((AppCtxWithCache.class)) ;

        Calculator cal = ctx.getBean("calculator", Calculator.class) ;
        cal.factorial(7) ;
        System.out.println("--------------------------------------");
        cal.factorial(7) ;
        System.out.println("--------------------------------------");
        cal.factorial(5) ;
        System.out.println("--------------------------------------");
        cal.factorial(5) ;
        System.out.println("--------------------------------------");
        ctx.close();
    }
}
