package changedInterface;

public interface Bar extends Foo {

    // 인터페이스를 상속받는 인터페이스에서 다시 추상 메소드로 변경할 수 있다.
    // 이럴 경우 Bar을 구현한 구현객체들은 모두 printNameUpperCase()를 구현해야 한다.
    // void printNameUpperCase() ;

    default void printNameUpperCase() {
        System.out.println("인터페이스 FOO 내부 구현체 = " + getName().toUpperCase());
    }


}
