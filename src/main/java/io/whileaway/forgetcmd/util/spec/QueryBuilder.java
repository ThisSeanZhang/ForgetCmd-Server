package io.whileaway.forgetcmd.util.spec;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.entities.Command_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class QueryBuilder<A, B> {

    final private List<Specification<A>> conditions = new ArrayList<>();

    final private List<A> primitive ;

    private QueryBuilder(List<A> primitive) {
        this.primitive = primitive;
    }

    public QueryBuilder() {
        this.primitive = new ArrayList<>();
    }


//    public Spec<A, B> findInDB (Function<Specification<A>, List<A>> findInDB) {
//        Specification<A> filterCondition = this.conditions.stream().filter(Objects::nonNull).reduce(this::andJoin).orElse(null);
//        List<A> primitive = findInDB.apply(filterCondition);
//        if (primitive.isEmpty()) {
//            throw new CommonException(ControllerEnum.NOT_FOUND);
//        }
//        return new Spec<>(primitive);
//    }

    public QueryBuilder<A, B> findInDB (Function<Specification<A>, List<A>> findInDB) {
        Specification<A> filterCondition = this.conditions.stream().filter(Objects::nonNull).reduce(this::andJoin).orElse(null);
        List<A> primitive = findInDB.apply(filterCondition);
        return new QueryBuilder<>(primitive);
    }

    public QueryBuilder<A, B> appendCondition(Specification<A> specification) {
        this.conditions.add(specification);
        return this;
    }

    public List<B> convertOther(Function<A, B> convert) {
        return this.primitive.stream().map(convert).collect(Collectors.toList());
    }

    public List<A> original() {
        return this.primitive;
    }

    private Specification<A> andJoin(Specification<A> perCondition, Specification<A> condition) {
        if (perCondition != null && condition != null)
            return perCondition.and(condition);
        return perCondition == null ? condition : perCondition;
    }

    public static<T, B> Specification<B> in(SingularAttribute<B, T> attribute, Predicate<List<T>> predicate, Supplier<List<T>> supplier) {
        return predicate.test(supplier.get())
                ? null
                : (root, query, builder) -> {
            Path<T> path = root.get(attribute);
            CriteriaBuilder.In<Object> in = builder.in(path);
            supplier.get().forEach(in::value);
            return builder.and(in);
        };
    }

    public static<T, B> Specification<B> equal(String paramName, Predicate<T> predicate, Supplier<T> supplier) {
        return predicate.test(supplier.get())
                ? null
                : (root, query, builder) -> builder.equal(root.get(paramName), supplier.get());
    }
    public static<T, B> Specification<B> equal(SingularAttribute<B, T> attribute, Predicate<T> predicate, Supplier<T> supplier) {
        return predicate.test(supplier.get())
                ? null
                : (root, query, builder) -> builder.equal(root.get(attribute), supplier.get());
    }

    public static<T> Specification<Command> equalA(String paramName, Predicate<T> predicate, Supplier<T> supplier) {
        return predicate.test(supplier.get())
                ? null
                : (root, query, builder) -> builder.equal(root.get(Command_.commandName), supplier.get());
    }

    public static<B> Specification<B> isNull(String paramName) {
        return (root, query, builder) -> builder.isNull(root.get(paramName));
    }

    public static<T, B> Specification<B> like(String paramName, Predicate<T> predicate, Supplier<T> supplier) {
        return predicate.test(supplier.get())
                ? null
                : (root, query, builder) -> builder.like(root.get(paramName), "%"+supplier.get()+"%");
    }
    public static<T, B> Specification<B> like(SingularAttribute<B, String> attribute, Predicate<T> predicate, Supplier<T> supplier) {
        return predicate.test(supplier.get())
                ? null
                : (root, query, builder) -> builder.like(root.get(attribute), "%"+supplier.get()+"%");
    }

    public static<B> Specification<B> like(String paramName, String supplier) {
        return (root, query, builder) -> builder.like(root.get(paramName), supplier );
    }
}
