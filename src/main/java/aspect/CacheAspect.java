package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * class         : CacheAspect
 * date          : 24-11-29
 * description   : 메서드 호출 결과를 캐싱하여 동일한 입력 값에 대해 중복 계산을 방지하는 Aspect 클래스.
 *                  특정 메서드 실행 전, 캐시 확인 및 저장 기능을 제공하며, AOP를 통해 적용
 */
@Aspect
@Order(2)
public class CacheAspect {
    private Map<Long, Object> cache = new HashMap<>();  // 입력값과 결과를 저장하기 위한 캐시 맵

    /**
     * method        : cacheTarget
     * date          : 24-11-29
     * description   : long 타입 파라미터를 가지는 chap07 패키지의 모든 public 메서드를 대상으로 하는 Pointcut.
     */
   // @Pointcut("execution(public * chap07 .. *(long))") 공백 X
    @Pointcut("execution(public * chap07..*(long))")
    public void cacheTarget() {
    }


    /**
     * method        : execute
     * date          : 24-11-29
     * param         : ProceedingJoinPoint joinPoint - AOP 적용 대상 메서드의 실행 제어를 위한 객체
     * return        : Object - AOP 적용 대상 메서드의 반환값 또는 캐시에서 가져온 값
     */
//
//    @Around("cacheTarget()")
//    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
//        long num = (long) joinPoint.getArgs()[0] ;      // 입력값 추출 (첫 번째 long 타입 파라미터)
//
//        // 캐시 확인
//        if(cache.containsKey(num)){
//            System.out.printf("CacheAspect: Cache에서 구함[%d]\n", num);
//            return cache.get(num) ;
//        }
//
//        Object result = joinPoint.proceed() ;            // 캐시에 값이 없으면 메서드 실행
//        cache.put(num,result) ;                          // 결과를 캐시에 저장
//        System.out.printf("CacheAspect : Cache에 추가  [%d]\n" , num);
//        return  result ;
//    }



    /**
     * method        : execute
     * date          : 24-11-29
     * param         : ProceedingJoinPoint joinPoint - AOP 적용 대상 메서드의 실행 제어를 위한 객체
     * return        : Object - AOP 적용 대상 메서드의 반환값 또는 캐시에서 가져온 값
     * description   :
     *                  1. AOP 적용 대상 메소드가 실행되기 전에 캐시를 확인하여,
     *                     첫 번째 파라미터(long 타입)가 캐시된 값이 있으면 그 값을 반환.
     *                  2. 캐시에 값이 없으면, 원본 메소드를 실행하고 그 결과를 캐시에 저장한 후 반환.
     *                  3. 캐시를 활용하여 성능을 최적화하고 중복 계산을 방지.
     */


    @Around("execution(public * chap07..*(long))")  //// 'chap07' 패키지 내 모든 public 메서드 중 첫 번째 파라미터가 long 타입인 메서드를 대상으로 AOP 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long num = (long) joinPoint.getArgs()[0] ;      // 입력값 추출 (첫 번째 long 타입 파라미터)

        // 캐시 확인
        if(cache.containsKey(num)){
            System.out.printf("CacheAspect: Cache에서 구함[%d]\n", num);
            return cache.get(num) ;
        }

        Object result = joinPoint.proceed() ;            // 캐시에 값이 없으면 메서드 실행
        cache.put(num,result) ;                          // 결과를 캐시에 저장
        System.out.printf("CacheAspect : Cache에 추가  [%d]\n" , num);
        return  result ;
    }




}


