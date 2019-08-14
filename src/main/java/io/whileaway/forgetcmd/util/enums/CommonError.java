package io.whileaway.forgetcmd.util.enums;

import io.whileaway.forgetcmd.util.exception.CommonException;

import java.util.function.Supplier;

public interface CommonError {

    default void throwThis() {
        throw getException();
    }

    CommonException getException();
}
