package chap07;

/**
 * class         : RecCalculator
 * date          : 24-11-28
 * implements    : Calculator
 * description   : 재귀 호출을 사용하여 팩토리얼을 계산하는 클래스.
 *                  Calculator 인터페이스를 구현하여 정수를 입력받아 팩토리얼 값을 반환.
 */
public class RecCalculator implements Calculator {

    /**
     * method        : factorial
     * date          : 24-11-28
     * param         : long num - 팩토리얼을 계산할 정수
     * return        : long - 계산된 팩토리얼 값
     * description   : 주어진 정수에 대해 재귀 호출을 사용하여 팩토리얼 값을 계산하고 반환.
     *
     */
    @Override
    public long factorial(long num){
        if(num== 0 ) {
            return 1;
        }else{
            return num * factorial(num-1 ) ;
        }
    }
}
