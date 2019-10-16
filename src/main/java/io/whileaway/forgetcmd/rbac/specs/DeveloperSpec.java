package io.whileaway.forgetcmd.rbac.specs;

import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.rbac.entites.Developer_;
import io.whileaway.forgetcmd.rbac.enums.DeveloperError;
import io.whileaway.forgetcmd.util.ParamInspect;
import io.whileaway.forgetcmd.util.spec.Spec;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Supplier;

public class DeveloperSpec {

    public static Specification<Developer> matchCreateName(Supplier<String> str) {
        if (ParamInspect.unValidString(str.get())) DeveloperError.CREATE_SESSION_NAME_NOT_NULL.throwThis();
        return matchNickName(str).or(matchEmail(str));
    }

    public static Specification<Developer> matchNickName(Supplier<String> str) {
        if (ParamInspect.unValidString(str.get())) DeveloperError.CREATE_SESSION_NAME_NOT_NULL.throwThis();
        return Spec.equal(Developer_.nickname, str);
    }

    public static Specification<Developer> matchEmail(Supplier<String> str) {
        if (ParamInspect.unValidString(str.get())) DeveloperError.CREATE_SESSION_NAME_NOT_NULL.throwThis();
        return Spec.equal(Developer_.nickname, str);
    }

}
