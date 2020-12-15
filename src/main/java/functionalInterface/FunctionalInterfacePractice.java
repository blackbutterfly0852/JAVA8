package functionalInterface;

public class FunctionalInterfacePractice {
    public static void main(String[] args) {
        // [함수형 인터페이스를 구현하는 방법]
        // 인터페이스를 구현해야되는 클래스가 필요하나, 임시작업 때문에 구현 클래스를 만드는 것은 비효율적
        // 1. 익명 내부 클래스
        FunctionalInterface1 functionalInterface1 = new FunctionalInterface1() {
            @Override
            public void doIt() {
                System.out.println("Hello_1");
            }
        };

        // 2. 람다(수행 한줄)
        // () -> System.out.print("Hello_2") : 특수한 형태의 객체(함수형 인터페이스를 인라인으로 구현한 객체)
        // 변수에 할당, 메소드의 파라미터로 전달, 이 자체를 리턴 -> first class object, 고차함수로 사용할 수 있다
        FunctionalInterface1 functionalInterface2 = () -> System.out.println("Hello_2");
        FunctionalInterface1 functionalInterface3 = () -> System.out.println("hello_3");

        // 2. 람다(수행 두줄이상)
        FunctionalInterface1 functionalInterface4 = () -> {
            System.out.println("Hello_4");
            System.out.println("Hello_4");
        };

        functionalInterface1.doIt();
        functionalInterface2.doIt();
        functionalInterface3.doIt();


        // [순수함수(Pure Function)]
        // 입력받은 값이 동일한 경우, 결과값이 같아야 한다.
        // 동일한 결과값을 보장하지 못하는 경우가 존재? 함수형 프로그래밍이라고 할 수 없다.

        FunctionalInterface2 functionalInterface21 = (number) -> number + 10;


        // baseNumber라는 상태값에 의존하는 값이 존재한다? -> 순수 함수라고 할 수 없다. 함수형 프로그래밍 X
        FunctionalInterface2 functionalInterface22 = new FunctionalInterface2() {
            int baseNumber = 10;
            @Override
            public int doIt(int a) {
                return a + baseNumber;
            }
        };

        // 외부의 값을 변경하는 경우 -> 순수 함수라고 할 수 없다. 함수형 프로그래밍 X
        final int baseNumber = 10; //
        FunctionalInterface2 functionalInterface23 = new FunctionalInterface2() {

            @Override
            public int doIt(int a) {
                return a + baseNumber;
            }
        };

        // baseNumber ++ : 문법적으로 안되지만 가정, 외부의 값을 변경하는 경우 순수 함수라고 할 수 없다.

        // 함수형프로그래밍(순수함수)를 하려면 주의가 필요하다
        // 함수 밖에 있는 값을 참조하거나 변경하면 안된다. 함수에 전달받은 파라미터만 사용해야한다.
        // 그러나, 함수형프로그래밍이 아니라면 문법적으로 허용되는 범위에서 함수 밖을 참조해도 된다.


    }
}
