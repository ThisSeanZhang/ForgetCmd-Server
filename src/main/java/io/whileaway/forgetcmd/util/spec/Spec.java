package io.whileaway.forgetcmd.util.spec;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.metamodel.SingularAttribute;
import java.util.function.Supplier;

public interface Spec {

    static <T, B> Specification<B> like(SingularAttribute<B, String> attribute,  Supplier<T> supplier) {
        return (root, query, builder) -> builder.like(root.get(attribute), "%"+supplier.get()+"%");
    }

    static<T, B> Specification<B> equal(SingularAttribute<B, T> attribute, Supplier<T> supplier) {
        return (root, query, builder) -> builder.equal(root.get(attribute), supplier.get());
    }

    static<B, T> Specification<B> isNull(SingularAttribute<B, T> attribute) {
        return (root, query, builder) -> builder.isNull(root.get(attribute));
    }

    static<B, T> Specification<B> isTrue(SingularAttribute<B, Boolean> attribute) {
        return (root, query, builder) -> builder.isTrue(root.get(attribute));
    }
}
