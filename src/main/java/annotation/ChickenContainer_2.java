package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 컨테이너 애노테이션은 중복 애노테이션과 @Retention 및 @Target이 같거나 더 넓어야 한다.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE) // 모든 곳에 가능
public @interface ChickenContainer_2 {

    Chicken_2[] value();
}
