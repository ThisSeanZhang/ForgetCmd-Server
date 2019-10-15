package io.whileaway.forgetcmd.util.spec;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QueryListBuilder<A> {

    final private List<Specification<A>> conditions = new ArrayList<>();

    final private List<A> primitive ;

    private QueryListBuilder(List<A> primitive) {
        this.primitive = primitive;
    }

    public QueryListBuilder() {
        this.primitive = new ArrayList<>();
    }

    public QueryListBuilder<A> appendCondition(Specification<A> specification) {
        conditions.add(specification);
        return this;
    }

    public QueryListBuilder<A> findFrom (Function<Specification<A>, List<A>> findInDB) {
        Specification<A> filterCondition = this.conditions.stream().filter(Objects::nonNull).reduce(this::andJoin).orElse(null);
        List<A> primitive = findInDB.apply(filterCondition);
        return new QueryListBuilder<>(primitive);
    }

    private Specification<A> andJoin(Specification<A> perCondition, Specification<A> condition) {
        if (perCondition != null && condition != null)
            return perCondition.and(condition);
        return perCondition == null ? condition : perCondition;
    }

    public<B> List<B> convert(Function<A, B> convert) {
        return this.primitive.stream().map(convert).collect(Collectors.toList());
    }

    public List<A> primitive() {
        return this.primitive;
    }
}
