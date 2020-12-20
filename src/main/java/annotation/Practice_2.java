package annotation;

import java.util.Arrays;

@Chicken_2("양념")
@Chicken_2("마늘간장")
public class Practice_2 {

    public static void main(String[] args) {
        Chicken_2[] chicken1 = Practice_2.class.getAnnotationsByType(Chicken_2.class);
        Arrays.stream(chicken1).forEach((c -> {
            System.out.println("chicken1 = " + c.value());
        }));

        System.out.println("============================");

        ChickenContainer_2 chicken2 = Practice_2.class.getAnnotation(ChickenContainer_2.class);
        Arrays.stream(chicken2.value()).forEach(c-> {
            System.out.println("chicken2 = " + c.value());
        });
    }
}
