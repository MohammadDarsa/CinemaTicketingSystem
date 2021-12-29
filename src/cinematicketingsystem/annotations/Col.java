package cinematicketingsystem.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Col {
    String name() default "";
    boolean insertIgnore() default false;
    boolean updateIgnore() default false;
}
