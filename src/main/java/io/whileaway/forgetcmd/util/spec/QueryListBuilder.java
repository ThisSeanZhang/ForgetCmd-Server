package io.whileaway.forgetcmd.util.spec;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueryListBuilder<A> {

    final private List<Specification<A>> conditions = new ArrayList<>();
    final private List<Sort.Order> orders = new ArrayList<>();

    final private List<A> primitive ;

    private QueryListBuilder(List<A> primitive) {
        this.primitive = primitive;
    }

    public QueryListBuilder() {
        this.primitive = new ArrayList<>();
    }

    public QueryListBuilder<A> appendCondition(Supplier<Specification<A>> specification) {
        return appendCondition(specification.get());
    }

    public QueryListBuilder<A> appendCondition(Specification<A> specification) {
        conditions.add(specification);
        return this;
    }

    public QueryListBuilder<A> sortBy(Supplier<Sort.Order> order) {
        return sortBy(order.get());
    }

    public QueryListBuilder<A> sortBy(Sort.Order order) {
        orders.add(order);
        return this;
    }

//    public QueryListBuilder<A> findFrom (Function<Specification<A>, List<A>> findInDB) {
//        Specification<A> filterCondition = this.conditions.stream().filter(Objects::nonNull).reduce(this::andJoin).orElse(null);
//        List<A> primitive = findInDB.apply(filterCondition);
//        return new QueryListBuilder<>(primitive);
//    }

    public QueryListBuilder<A> findFrom (BiFunction<Specification<A>, Sort, List<A>> findInDB) {
        Specification<A> filterCondition = this.conditions.stream().filter(Objects::nonNull).reduce(this::andJoin).orElse(null);
        List<A> primitive = findInDB.apply(filterCondition, Sort.by(orders));
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
    public Stream<A> stream() {
        return this.primitive.stream();
    }
}
