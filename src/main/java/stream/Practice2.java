package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practice2 {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));


        System.out.println("spring 으로 시작하는 수업");
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println(oc.getId()));
        System.out.println("=================================");
        System.out.println("close 되지 않은 수업");
        springClasses.stream()
                .filter(oc -> oc.isClosed() == false)
                .forEach(oc -> System.out.println(oc.getId()));
        System.out.println("--------------------------------");
        springClasses.stream()
                .filter(oc -> !oc.isClosed())
                .forEach(oc -> System.out.println(oc.getId()));
        System.out.println("--------------------------------");
        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed))
                .forEach(oc -> System.out.println(oc.getId()));
        System.out.println("=================================");

        System.out.println("수업 이름만 모아서 스트림 만들기");
        // map : oc에서 getTitle String 타입만 추출 -> forEach : 여기서는 String 타입을 추출
        springClasses.stream().map(oc -> oc.getTitle()).forEach(System.out::println);
        System.out.println("=================================");

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", true));

        List<List<OnlineClass>> events = new ArrayList<>();
        events.add(springClasses);
        events.add(javaClasses);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        // 오퍼레이터에 오는 인자값 타입을 항상 생각할 것.
        events.stream()
                .flatMap(list -> list.stream())
                .forEach(oc -> System.out.println(oc.getId()));
        System.out.println("=================================");

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);
        System.out.println("=================================");
        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println("test = " + test);
        System.out.println("=================================");
        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        List<OnlineClass> onlineClasses = springClasses.stream().filter(oc -> oc.getTitle().contains("spring")).collect(Collectors.toList());
        onlineClasses.forEach(oc -> System.out.println(oc.getTitle()));
        System.out.println("---------------------------------");
        List<String> onlineClasses2 = springClasses.stream().filter(oc -> oc.getTitle().contains("spring")).map(OnlineClass::getTitle).collect(Collectors.toList());
        onlineClasses2.forEach(System.out::println);
        System.out.println("---------------------------------");
        List<String> onlineClasses3 = springClasses.stream().map(OnlineClass::getTitle).filter(t -> t.contains("spring")).collect(Collectors.toList());
        onlineClasses3.forEach(System.out::println);
    }
}
