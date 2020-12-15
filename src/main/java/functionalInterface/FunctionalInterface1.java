package functionalInterface;

@FunctionalInterface // 함수형 인터페이스를 정의한 경우에는 해당 어노테이션 추가, 인터페이스의 견고한 관리
public interface FunctionalInterface1 {

    abstract void doIt(); // abstract 생략 가능

    default void printAge(){
        System.out.print("40");
    }

    static void printName(){
        System.out.print("40");
    }

}
