package main;

import chap07.ExeTimeCalculator;
import chap07.ImpeCalculator;
import chap07.RecCalculator;

/**
 * class         : MainProxy
 * date          : 24-11-28
 * description   : ExeTimeCalculator를 사용하여 ImpeCalculator와 RecCalculator의 실행 시간을 측정하고,
 *                  결과를 출력하는 메인 클래스 [ 리스트7.5]
 */
public class MainProxy {

    /**
     * method        : main
     * date          : 24-11-28
     * param         : String[] args - 프로그램 실행 시 전달받는 인자
     * return        : void
     * description   : ExeTimeCalculator를 이용해 ImpeCalculator와 RecCalculator의 팩토리얼 계산 실행 시간을 측정하고 출력.
     */
    public static void main(String[] args ){
        // ImpeCalculator에 대한 실행 시간 측정 객체 생성 및 팩토리얼 계산 수행
        ExeTimeCalculator ttCal1 = new ExeTimeCalculator(new ImpeCalculator()) ;
        System.out.println(ttCal1.factorial(20));

        // RecCalculator에 대한 실행 시간 측정 객체 생성 및 팩토리얼 계산 수행
        ExeTimeCalculator ttCal2 = new ExeTimeCalculator(new RecCalculator()) ;
        System.out.println(ttCal2.factorial(20));
    }
}
