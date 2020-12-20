package annotation;

import java.lang.annotation.*;

// 어노테이션 중복사용
@Retention(RetentionPolicy.RUNTIME)
// @Target(ElementType.TYPE_PARAMETER) // 타입 파라미터만 가능
@Target(ElementType.TYPE_USE) // 모든 곳에 가능
@Repeatable(ChickenContainer_2.class)
public @interface Chicken_2 {

    String value();
}
