package io.whileaway.forgetcmd.rbac.annotation;

import io.whileaway.forgetcmd.rbac.enums.PermissionType;
import io.whileaway.forgetcmd.rbac.enums.ResourceType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface NeedPermit {
    String resourceId();
    PermissionType permission();
    ResourceType type();
}