package io.whileaway.forgetcmd.rbac.enums;

import io.whileaway.forgetcmd.util.enums.CommonError;
import io.whileaway.forgetcmd.util.exception.CommonException;

public enum SessionError implements CommonError {
    NOT_FOUND(new CommonException.NotFoundException("会话过期")),
    ;
    private CommonException exception;

    SessionError(CommonException exception) {
        this.exception = exception;
    }

    @Override
    public CommonException getException() {
        return this.exception;
    }
}
