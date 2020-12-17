package java8DefaultStaticMethod;

import java.util.*;
import java.util.stream.Collectors;

public class Practice {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("KIMDONGWOO");
        names.add("KIMJUNGWOO");
        names.add("KIMSANGSEOK");
        names.add("KANGJYUNSOOK");
        names.forEach(System.out::println);
        System.out.println("=============================");

        // Spliterator
        // 쪼갤 수 있는 iterator 및 병렬 처리
        Spliterator<String> spliterator1 = names.spliterator();
        // spliterator1 을 이 부분에서 모두 출력하면 spliterator2는 NPE
        Spliterator<String> spliterator2 = spliterator1.trySplit();
        while (spliterator1.tryAdvance(System.out::println)) ;
        System.out.println("-----------------------------");
        while (spliterator2.tryAdvance(System.out::println)) ;
        System.out.println("=============================");

        // stream
        long count = names.stream().map(String::toUpperCase).filter(s -> s.startsWith("K")).count();
        Set<String> set = names.stream().map(String::toUpperCase).filter(s -> s.startsWith("K")).collect(Collectors.toSet());
        System.out.println("count = " + count);
        System.out.println("=============================");

        // removeIf
        //names.removeIf(s -> s.startsWith("KIM")); // KIM로 시작하는 것을 빼라.
        names.forEach(System.out::println);
        System.out.println("=============================");

        // Comparator
        names.sort(String::compareToIgnoreCase);
        names.forEach(System.out::println);
        System.out.println("-----------------------------");
        Comparator<String> comparator = String::compareToIgnoreCase;
        names.sort(comparator.reversed());
        names.forEach(System.out::println);

        /**
        * 기존 : 인터페이스 <- 추상 클래스 <- API 구현 클래스
        * 현재(Java8) : 인터페이스 <- API 구현 클래스
        * */

    }
}
