package annotation;

import java.lang.annotation.*;
// 모든 곳에 가능하도록
@Retention(RetentionPolicy.RUNTIME)
// @Target(ElementType.TYPE_PARAMETER) // 타입 파라미터만 가능
@Target(ElementType.TYPE_USE) // 모든 곳에 가능
public @interface Chicken_1 {


}
