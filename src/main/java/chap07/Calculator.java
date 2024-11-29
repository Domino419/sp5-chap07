package chap07;

/**
 * interface      : Calculator
 * date           : 24-11-28
 * description    : 팩토리얼 계산을 위한 인터페이스.
 *                  정수를 입력받아 해당 수의 팩토리얼을 반환하는 메소드 정의.
 */
public interface  Calculator {

    /**
     * method        : factorial
     * date          : 24-11-28
     * param         : long num - 팩토리얼을 계산할 정수
     * return        : long - 계산된 팩토리얼 값
     * description   : 주어진 정수에 대한 팩토리얼 값을 계산하여 반환.
     */
    public long factorial(long num) ;

}

