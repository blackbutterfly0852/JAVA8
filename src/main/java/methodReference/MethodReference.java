package methodReference;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReference {

    public static void main(String[] args) {
        UnaryOperator<String> hi_1 = (s) -> "hi " + s;
        UnaryOperator<String> hi_2 = Greeting::hi;
        Greeting greeting_1 = new Greeting();
        UnaryOperator<String> hello = greeting_1::hello;

        System.out.println(hi_1.apply("김동우"));
        System.out.println(hi_2.apply("김동우"));
        System.out.println(hello.apply("김동우"));

        Supplier<Greeting> newGreeting_1 = Greeting::new;
        Greeting greeting_2 = newGreeting_1.get();

        Function<String, Greeting> newGreeting_2  = Greeting::new;
        Greeting greeting_3 = newGreeting_2.apply("김동우");


        String[] names_1 = {"김동우", "김상석", "김정우"};
        Arrays.sort(names_1, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return 0;
            }
        });
        System.out.println("name_1 = " + Arrays.toString(names_1));
        String[] names_2 = {"김동우", "김상석", "김정우"};
        Arrays.sort(names_2, (o1,o2) -> 0);
        System.out.println("name_2 = " + Arrays.toString(names_2));
        String[] names_3 = {"김동우", "김상석", "김정우"};
        Arrays.sort(names_3, String::compareToIgnoreCase);
        System.out.println("name_3 = " + Arrays.toString(names_3));

    }
}
