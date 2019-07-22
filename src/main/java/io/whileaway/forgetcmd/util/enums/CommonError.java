package io.whileaway.forgetcmd.util.enums;

import io.whileaway.forgetcmd.util.exception.CommonException;

public interface CommonError {

    default void throwThis() {
        throw getException();
    }
    CommonException getException();
}
