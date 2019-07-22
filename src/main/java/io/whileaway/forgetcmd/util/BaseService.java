package io.whileaway.forgetcmd.util;

import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Objects;

public interface BaseService<A, B> {

    default A save(A a) {
        if(Objects.isNull(a)) CommonErrorEnum.PARAM_ERROR.throwThis();
        return getRepository().save(a);
    }

    JpaRepository<A, B> getRepository();
}
