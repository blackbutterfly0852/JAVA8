package me.whiteship.java8to11;

import java.util.function.*;

public class Section_1_2_1 implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
    // 자바에서 제공하는 함수형 인터페이스(추상메소드가 하나인 인터페이스)
    public static void main(String[] args) {
        Section_1_2_1 section_1_2_1 = new Section_1_2_1();
        System.out.println(section_1_2_1.apply(10));


        Function<Integer, Integer> plus10 = (i) -> i + 10;
        UnaryOperator<Integer> plus10_2 = (i) -> i + 10; // 입력값과 출력값이 동일한 경우
        System.out.println(plus10.apply(10));

        Function<Integer,Integer> multiply2 = (Integer) -> Integer * 2;
        Function<Integer,Integer> mp = plus10.compose(multiply2); // 고차함수성격, *2 -> + 10
        System.out.println(mp.apply(2)); // 14
        Function<Integer,Integer> pm = plus10.andThen(multiply2); // 고차함수성격, +10 -> * 2
        System.out.println(pm.apply(2)); // 28

        // void
        Consumer<Integer> printT  = (i) -> System.out.println("Integer : " + i);
        printT.accept(10); // 10

        // 값을 가져오는 함수형 인터페이스, 아래 Integer는 가져오는 값의 타입을 정한다.
        Supplier<Integer> get10  = () -> 10;
        System.out.println(get10.get()); // 무조건 10을 반환

        // 파라미터를 받아서 True/False를 리턴
        Predicate<String> isBoolean = (s) -> s.startsWith("KIM");
        System.out.println(isBoolean.test("KIM"));
        Predicate<Integer> isEven = (i) -> i % 2 == 0;
        System.out.println(isEven.test(20));

        Predicate<Integer> isBiggerThanFive = num -> num > 5;
        Predicate<Integer> isLowerThanSix = num -> num < 6;
        System.out.println(isBiggerThanFive.and(isLowerThanSix).test(10));
        System.out.println(isBiggerThanFive.or(isLowerThanSix).test(10));






    }
}
