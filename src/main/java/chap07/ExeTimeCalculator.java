package chap07;

/**
 * class         : ExeTimeCalculator
 * date          : 24-11-28
 * implements    : Calculator
 * description   : 위임 패턴(Delegate)을 사용하여 Calculator 구현 클래스의 실행 시간을 측정하는 클래스.
 *                  Calculator 인터페이스를 구현하며, 실행 시간 측정을 위해 대상 구현체를 위임받아 호출.
 */
public class ExeTimeCalculator implements Calculator {

    private Calculator delegate ;   // 실제 팩토리얼 계산을 수행하는 대상 객체

    /**
     * method        : ExeTimeCalculator
     * date          : 24-11-28
     * param         : Calculator delegate - 실행 시간을 측정할 대상 Calculator 구현체
     * description   : 팩토리얼 계산을 수행할 Calculator 구현체를 주입받아 초기화.
     */
    public ExeTimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }

    /**
     * method        : factorial
     * date          : 24-11-28
     * param         : long num - 팩토리얼을 계산할 정수
     * return        : long - 계산된 팩토리얼 값
     * description   : 위임받은 Calculator 구현체를 사용하여 팩토리얼 계산을 수행하고,
     *                  해당 계산의 실행 시간을 나노초 단위로 측정하여 출력.
     */
    @Override
    public long factorial(long num) {
        // 1. 실행 시작 시각을 나노초 단위로 기록
        long start = System.nanoTime();

        // 2. delegate(위임받은 Calculator 객체)를 사용하여 팩토리얼 계산 수행  (핵심메서드)
        long result = delegate.factorial(num);

        // 3. 실행 종료 시각을 나노초 단위로 기록
        long end = System.nanoTime();

        // 4. 실행 시간 차이를 계산하여 출력 (실행 시간: 종료 시간 - 시작 시간)
        System.out.printf("%s.factorial(%d) 실행 시간 = %d\n", delegate.getClass().getSimpleName(), num, (end - start));

        // 5. 계산된 팩토리얼 값 반환
        return result;
    }

}
