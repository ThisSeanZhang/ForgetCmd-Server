package io.whileaway.forgetcmd.snapshot.enums;

import io.whileaway.forgetcmd.util.enums.CommonError;
import io.whileaway.forgetcmd.util.exception.CommonException;

public enum SnapshotError implements CommonError {
    NOT_FOUND(new CommonException.NotFoundException("找不到相应的快照")),
    FORBIDDEN(new CommonException.ForbiddenException("other.except.forbidden"))
    ;
    private CommonException exception;

    SnapshotError(CommonException exception) {
        this.exception = exception;
    }

    @Override
    public CommonException getException() {
        return exception;
    }
}
