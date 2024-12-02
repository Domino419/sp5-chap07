package config;

import aspect.CacheAspect;
import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * class         : AppCtxWithCache
 * date          : 24-11-29
 * description   : AOP 설정을 포함한 Spring 애플리케이션 컨텍스트 구성 클래스.
 *                  캐싱 및 실행 시간 측정과 관련된 Aspect를 등록하고 Calculator 빈을 정의.  >>> MainAspectWithCache.java 에서 쓸 거
 */

@Configuration             // 스프링 컨테이너에 설정 클래스를 나타내는 애너테이션
@EnableAspectJAutoProxy    // AOP 설정을 활성화하여 프록시 기반의 애스펙트를 적용
public class AppCtxWithCache {

    /**
     * method        : cacheAspect
     * date          : 24-11-29
     * return        : CacheAspect - 캐싱 기능을 제공하는 Aspect 빈
     * description   : CacheAspect를 Spring 컨텍스트에 등록.
     *                  chap07 패키지의 long 타입 인자를 받는 메서드에 대해 캐싱 기능을 적용.
     */
    @Bean
    public CacheAspect cacheAspect() {
        return new CacheAspect() ;
    }

    /**
     * method        : exeTimeAspect
     * date          : 24-11-29
     * return        : ExeTimeAspect - 실행 시간 측정 기능을 제공하는 Aspect 빈
     * description   : ExeTimeAspect를 Spring 컨텍스트에 등록.
     *                  chap07 패키지의 메서드 실행 시간을 측정하여 로그로 출력.
     */
    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect() ;
    }

    /**
     * method        : calculator
     * date          : 24-11-29
     * return        : Calculator - 팩토리얼 계산 기능을 제공하는 RecCalculator 빈
     * description   : RecCalculator를 Spring 컨텍스트에 등록.
     *                  AOP를 통해 CacheAspect 및 ExeTimeAspect의 적용 대상이 됨.
     */
    @Bean
    public Calculator calculator() {
        return new RecCalculator() ;
    }
}
