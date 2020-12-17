package changedInterface;

public class DefaultFoo implements Foo {

    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println("printName() 출력 = " + this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    // Foo의 구현메소드를 재정의
    @Override
    public void printNameUpperCase() {
        System.out.println("DefaultFoo 에서 출력 = " + this.name.toUpperCase());
    }
}
