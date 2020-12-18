package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practice {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("KIMDONGWOO");
        names.add("KIMJUNGWOO");
        names.add("KIMSANGSEOK");
        names.add("KANGJYUNSOOK");
        names.forEach(System.out::println);
        System.out.println("============================");

        // 스트림 자체는 데이터를 변경하지 않는다.
        // 중개형 오퍼레이션은 Lazy 하며 Stream 리턴한다.
        // 파이프라인을 정의한것, 종료 오퍼레이션 오기전까지 실행하지 않는다.
        names.stream().map((s) -> {
            System.out.println("중개형 오퍼레이터 = " + s);
            return s.toUpperCase();
        });
        // 종료형 오퍼레이터가 반드시 있어야 한다.
        List<String> stringStream = names.stream().map(String::toLowerCase).collect(Collectors.toList());
        stringStream.forEach(System.out::println);

        System.out.println("============================");
        // LOOP
        // 기존 : 병렬 처리 어려움
        for(String name : names){
            if(name.startsWith("KANG")){
                System.out.println(name.toLowerCase());
            }
        }

        // Stream : 병렬 처리 가능 -> parallelStream() -> 데이터가 방대가하게 많은 경우외에는 그냥 Stream() 사용
        names.parallelStream().map(String::toLowerCase).collect(Collectors.toList());





    }


}
