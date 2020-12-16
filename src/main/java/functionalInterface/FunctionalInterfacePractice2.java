package functionalInterface;

import java.util.function.*;

public class FunctionalInterfacePractice2 {
    public static void main(String[] args) {

        FunctionalInterface4 f1 = () -> System.out.println("연습1");
        f1.doIt();
        FunctionalInterface4 f2 = () -> System.out.println("연습2");
        f2.doIt();
        FunctionalInterface5 f3 = (number) -> number + 4;
        System.out.println(f3.doIt(1));


        // 순수 함수 : 같은 입력 값이면 같은 값을 보장해야한다.
        // 함수 밖에 값을 의존한다? 순수 함수가 아니다.
        FunctionalInterface5 f4 = new FunctionalInterface5() {
            int baseNumber = 4; // 함수 밖의 값을 의존.
            @Override
            public int doIt(int a) {
                return 0+baseNumber;
            }
        };

        System.out.println(f4.doIt(4));
        System.out.println(f4.doIt(4));
        System.out.println(f4.doIt(4));

        Function<Integer, Integer> function1 = (number) -> number + 10;
        System.out.println(function1.apply(4));

        Function<Integer, Integer> function2 = (number) -> number * 2;


        Function<Integer, Integer> function3 = function1.andThen(function2); // 앞 -> 뒤
        System.out.println("andThen = "  + function3.apply(4));

        Function<Integer, Integer> function4 = function1.compose(function2); // 뒤 -> 앞
        System.out.println("compose = "  + function4.apply(4));

        BiFunction<Integer,Integer, Integer> function5 = (number1, number2) -> number1+number2;
        System.out.println(function5.apply(4,5));

        Consumer<Integer> consumer1 = (number) -> System.out.println("consumer = " + number );
        consumer1.accept(4);

        Supplier<Integer> supplier1 = () -> 100;
        System.out.println("supplier = " + supplier1.get());

        Predicate<Integer> predicate1 = (number) -> number == 10;
        System.out.println("predicate = " + predicate1.test(10));

        Predicate<String> predicate2 = (string) -> string.startsWith("S");
        System.out.println("predicate = " + predicate2.test("T"));

        UnaryOperator<Integer> unaryOperator = (number) -> number + 10;
        System.out.println("unaryOperator = " + unaryOperator.apply(10));

        BinaryOperator<Integer> binaryOperator = (number1,number2) -> number1 + number2;
        System.out.println("binaryOperator = " + binaryOperator.apply(10,20));

    }
}
