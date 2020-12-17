package lamda;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Lamda {

    public static void main(String[] args) {
        Lamda l = new Lamda();
        l.run();
    }

    private void run() {
        // Java 8 부터는 effective final (사실상 final)인 경우 final 생략 가능 및 아래 3개가 참조 가능
        int baseNumber = 10;

        // 로컬 클래스
        class LocalClass {
            // 파라미터명이 baseNumber일 경우 쉐도잉 발생
            void printBaseNumber(Integer baseNumber) {
                System.out.println("localClass = " + baseNumber);
            }
        }

        // 익명 클래스
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            // 파라미터명이 baseNumber일 경우 쉐도잉 발생
             public void accept(Integer baseNumber) {
                System.out.println("AnonymousClass = " + baseNumber);
            }
        };

        // 람다
        // 파라미터명이 baseNumber일 경우 컴파일 오류 -> 쉐도잉 불가
        // baseNumber와 Lamda는 같은 scope 이다.
        IntConsumer intConsumer = (integer) -> {
            System.out.println("Lamda = " + baseNumber);
        };
        // 람다에서 위의 baseNumber가 final이 생략되있고, 아래와 같이 baseNumber를 수정하는 경우
        // 사실상 final이 아니구나라는 점을 컴파일러가 인지하고 컴파일 오류를 발생
        // baseNumber ++;

        LocalClass localClass = new LocalClass();
        localClass.printBaseNumber(20);
        consumer.accept(20);
        intConsumer.accept(20);

    }

}
