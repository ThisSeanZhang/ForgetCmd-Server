package io.whileaway.forgetcmd.rbac.annotation;

import io.whileaway.forgetcmd.rbac.enums.OptionType;
import io.whileaway.forgetcmd.rbac.enums.ResourceType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface NeedPermit {
    String pathKey();
    OptionType operator();
    ResourceType type();
}