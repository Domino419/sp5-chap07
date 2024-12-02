package config;

import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * class         : AppCtx
 * date          : 24-11-29
 * annotation    : @Configuration, @EnableAspectJAutoProxy
 * description   : Spring 애플리케이션의 설정 클래스.
 *                  AOP 설정을 활성화하고 필요한 빈을 정의.  [리스트 7.7]
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppCtx {

    /**
     * method        : exeTimeAspect
     * date          : 24-11-29
     * return        : ExeTimeAspect - 실행 시간 측정 AOP 구현체
     * description   : 실행 시간 측정을 위한 Aspect를 빈으로 등록.
     *                  해당 Aspect는 메서드 실행 시간을 측정하여 로그로 출력.
     */
    @Bean
    public ExeTimeAspect exeTimeAspect(){
        return new ExeTimeAspect() ;
    }

    /**
     * method        : calculator
     * date          : 24-11-29
     * return        : Calculator - 계산기 인터페이스 구현체
     * description   : 팩토리얼 계산을 수행하는 `RecCalculator` 구현체를 빈으로 등록.
     *                  Aspect가 적용된 대상 메서드의 실행 시간을 측정 가능.
     */
    @Bean
    public Calculator calculator () {
        return new RecCalculator();
    }
}


// 162page 리스트 7.7까지 했음,
// 163page부터 읽을 것