package chap07;

/**
 * class         : ImpeCalculator
 * date          : 24-11-28
 * implements    : Calculator
 * description   : 반복문을 사용하여 팩토리얼을 계산하는 클래스.
 *                  Calculator 인터페이스를 구현하여 정수를 입력받아 팩토리얼 값을 반환.
 *                  계산 시 실행 시간을 측정하고 결과를 출력.
 */
public class ImpeCalculator implements Calculator  {

    /**
     * method        : factorial
     * date          : 24-11-28
     * param         : long num - 팩토리얼을 계산할 정수
     * return        : long - 계산된 팩토리얼 값
     * description   : 주어진 정수에 대해 반복문을 사용하여 팩토리얼 값을 계산하고 반환.
     *                 계산 중 실행 시간을 측정하여 로그로 출력.
     */
    @Override
    public long factorial(long num){
        long start = System.currentTimeMillis() ;
        long result = 1 ;
        for ( long i = 1 ; i<= num ; i++ ){
            result *= i;
        }
        long end = System.currentTimeMillis() ;
        return result ;
    }
}
