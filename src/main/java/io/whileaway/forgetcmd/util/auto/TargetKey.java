package io.whileaway.forgetcmd.util.auto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetKey {

    String key();

    String defaultValue() default "";
}
