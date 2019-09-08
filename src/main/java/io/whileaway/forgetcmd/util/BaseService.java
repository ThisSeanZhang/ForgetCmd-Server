package io.whileaway.forgetcmd.util;

import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;
import java.util.Optional;

public interface BaseService<A, B> {

    default A save(A a) {
        if(Objects.isNull(a)) CommonErrorEnum.PARAM_ERROR.throwThis();
        return getRepository().save(a);
    }

    default Optional<A> findById(B b) {
        if(Objects.isNull(b)) CommonErrorEnum.PARAM_ERROR.throwThis();
        return getRepository().findById(b);
    }
    // 在想有没有必要吧Spec直接集成到Service中
//    default Optional<List<A>> findInDB (List<Specification<A>> conditions, Function<Specification<A>, Optional<List<A>>> findInDB) {
//        Specification<A> filterCondition = conditions.stream().filter(Objects::nonNull).reduce(this::andJoin).orElse(null);
//        getRepository().findAll(filterCondition);
//        return findInDB.apply();
//    }

    default Specification<A> andJoin(Specification<A> perCondition, Specification<A> condition) {
        if (perCondition != null && condition != null)
            return perCondition.and(condition);
        return perCondition == null ? condition : perCondition;
    }

    BaseRepository<A, B> getRepository();
}
