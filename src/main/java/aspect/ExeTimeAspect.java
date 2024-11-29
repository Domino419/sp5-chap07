package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * class         : ExeTimeAspect
 * date          : 24-11-29
 * annotation    : @Aspect
 * description   : AOP(Aspect-Oriented Programming)를 이용하여 메서드 실행 시간을 측정하는 클래스.
 *                  특정 Pointcut에 대해 실행 시간을 측정하고 로그로 출력. [리스트 7.6]
 */
@Aspect
public class ExeTimeAspect {

    /**
     * method        : publicTarget
     * date          : 24-11-29
     * annotation    : @Pointcut
     * description   : chap07 패키지와 하위 패키지의 모든 public 메서드를 Pointcut으로 설정.
     */
    @Pointcut("execution(public * chop07..*(..))")
    private void publicTarget(){
    }

    /**
     * method        : measure
     * date          : 24-11-29
     * annotation    : @Around
     * param         : ProceedingJoinPoint joinPoint - 대상 메서드 실행 정보를 담고 있는 객체
     * return        : Object - 대상 메서드의 실행 결과
     * throws        : Throwable - 대상 메서드 실행 중 발생할 수 있는 예외
     * description   : 대상 메서드 실행 시간을 측정하고, 메서드 이름 및 매개변수 정보를 함께 출력.
     *                 @Around를 사용하여 메서드 실행 전/후 로직을 정의.
     */
    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 메서드 실행 시작 시간 기록
        long start = System.nanoTime() ;

        try{
            // 2. 대상 메서드를 실행하고 결과를 반환
            Object result = joinPoint.proceed() ;
            return result ;
        } finally {
            // 3. 메서드 실행 종료 시간 기록
            long finish = System.nanoTime() ;

            // 4. 대상 메서드의 시그니처 정보 가져오기
            Signature sig = joinPoint.getSignature();

            // 5. 메서드 실행 시간과 호출 정보 출력
            System.out.printf(
                    "%s.%s(%s) 실행 시간 : %d ns\n",
                    joinPoint.getTarget().getClass().getSimpleName(),  // 대상 객체의 클래스 이름
                    sig.getName(),                                     // 메서드 이름
                    Arrays.toString(joinPoint.getArgs()),              // 메서드의 매개변수 목록
                    (finish - start)                                   // 실행 시간
            );
        }
    }
}
