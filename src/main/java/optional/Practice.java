package optional;

import optional.OnlineClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practice {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass onlineClass1 = new OnlineClass(1, "spring boot", true);

        try {
            Duration studyDuration = onlineClass1.getProgress().getStudyDuration();
            System.out.println("studyDuration = " + studyDuration); // NPE 오류
        } catch (NullPointerException e) {
            System.out.println("NPE 오류 입니다.");
        }
        // 해결책 1 : 보통의 해결책이며 실수를 유발할 수 있다. NULL 을 리턴하는 것도 문제이다.
        // 에러를 처리하기 위해 OnlineClass 클래스에서 로직을 사용하는 것은 좋지 못한 습관이다.
        Progress progress1 = onlineClass1.getProgress();
        if (progress1 != null) {
            System.out.println("studyDuration = " + onlineClass1.getProgress().getStudyDuration());
        }

        // 해결책 2 : OnlineClass 클래스에서 Optional 사용
        // 참고_1 MAP의 KEY를 Optional 사용한다? KEY 자체가 NOT NULL 이기 때문에 인터페이스를 깨뜨리는 것. -> 심각함
        // 참고_2 Optional.of(10) -> Boxing, UnBoxing 문제 -> OptionalInt.of(10) 사용
        // 참고_3 return null -> Optional.empty();
        Optional<Progress> optionalProgress = onlineClass1.getProgressOptional();
        System.out.println("=================================");

        // 사용법
        Optional<OnlineClass> optional = springClasses.stream().filter(oc -> oc.getTitle().startsWith("spring")).findFirst();
        boolean present = optional.isPresent();
        System.out.println("present = " + present);


        // 만약에 없는 경우 NoSuchElementException 오류 발생
        OnlineClass onlineClass2 = optional.get();
        System.out.println("onlineClass2 = " + onlineClass2.getTitle());

        // if(optional != null) 대용
        optional.ifPresent(oc -> System.out.println(oc.getTitle()));

        // orElse() : optional 에 있으면 가져오고, 없으면 새로운 클래스를 만들어서 넣어라
        // 그러나 optional 에 값이 있든, 없든 crateNewClass()는 실행됨.
        // 상수 사용
        OnlineClass onlineClass3 = optional.orElse(crateNewClass());

        // orElseGet() : 실제 optional 에 값이 없을 때만 crateNewClass()는 실행되는 방법
        // 동적 사용
        OnlineClass onlineClass4 = optional.orElseGet(Practice::crateNewClass);

        // orElseThrow() : optional 값이 없을 때는 에러 발생 시킨다.
        OnlineClass onlineClass5 = optional.orElseThrow(IllegalStateException::new);

        // filter 사용 가능 -> 리턴값 Optional
        Optional<OnlineClass> onlineClass6 = optional.filter(OnlineClass::isClosed);

        // Map 사용 가능 -> 리턴값 Optional
        Optional<Integer> onlineClass7 = optional.map(OnlineClass::getId);
        System.out.println("onlineClass7 = " + onlineClass7.isPresent());

        // flatMap -> Optional<Optional<...>> 한번 제거
        Optional<Progress> progress2 = optional.flatMap(OnlineClass::getProgressOptional);

    }

    private static OnlineClass crateNewClass() {
        return new OnlineClass(10, "New Class", false);
    }
}
