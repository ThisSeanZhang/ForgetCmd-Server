package io.whileaway.forgetcmd.util;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class ParamInspect<T> {

    private T target;

    private ParamInspect(T t) {
        this.target = t;
    }

    public static<T> ParamInspect<T> target(Supplier<T> target) {
        return new ParamInspect<>(target.get());
    }

    public<A> A convert(Function<T, A> convert) {
        return convert.apply(target);
    }
    public static boolean nonNullLong(Supplier<Long> l) {
        return Objects.nonNull(l);
    }
}
