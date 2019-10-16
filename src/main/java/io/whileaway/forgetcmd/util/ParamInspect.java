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

    // 基础使用方式
    private static boolean nonNullLong(Supplier<Long> l) {
        return Objects.nonNull(l.get());
    }
    public static boolean isNullLong(Supplier<Long> l) {
        return !nonNullLong(l);
    }

    public static boolean nonNullObject(Supplier o) {return !isNullObject(o);}
    public static boolean isNullObject(Supplier o) {return Objects.isNull(o.get());}
    public static boolean validString(String str) {
        return Objects.nonNull(str) && !str.isBlank() && !str.isEmpty();
    }

    public static boolean unValidString(String str) {
        return !validString(str);
    }

}
