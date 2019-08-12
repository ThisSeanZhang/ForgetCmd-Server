package io.whileaway.forgetcmd.util;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<A, ID> extends JpaRepository<A, ID>, JpaSpecificationExecutor<A> {

    default Optional<List<A>> findInDB (List<Specification<A>> conditions) {
        Specification<A> filterCondition = conditions.stream().filter(Objects::nonNull).reduce(this::andJoin).orElse(null);
        return Optional.ofNullable(findAll(filterCondition));
    }

    default Specification<A> andJoin(Specification<A> perCondition, Specification<A> condition) {
        if (perCondition != null && condition != null)
            return perCondition.and(condition);
        return perCondition == null ? condition : perCondition;
    }
}
