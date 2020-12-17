package changedInterface;

public interface Foo {

    void printName();
    // 추상 메소드를 추가할 경우 이미 구현한 구현객체들의 컴파일 오류 발생
    // default 메소드는 메소드 선언이 아니라 구현체를 제공하는 방법
    // 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다.
    // 항상 이 기능이 제대로 동작한다는 보장은 없다. ->  getName()에서 무슨 값이 리턴될지 모른다.
    // 즉, @implSpec 문서화 필요 및 구현객체에서 재정의 가능

    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase(){
        System.out.println("인터페이스 FOO 내부 구현체 = " + getName().toUpperCase());
    };

    String getName();

    // Object 내 메소드는 재정의 불가하지만 메소드 선언으로 가능
    //  -> 추상 메소드가 생각 안하고, 이 인터페이스에서 특수하게 제약하기 위해서 사용
    // default String toString(){
    // }
    String toString();

    // static
    static void printAnything(){
        System.out.println("인터페이스 FOO 내부 static 메소드를 출력");
    }
}
