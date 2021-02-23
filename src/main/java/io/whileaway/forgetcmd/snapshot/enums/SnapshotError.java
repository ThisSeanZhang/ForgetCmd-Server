package io.whileaway.forgetcmd.snapshot.enums;

import io.whileaway.forgetcmd.util.enums.CommonError;
import io.whileaway.forgetcmd.util.exception.CommonException;
import io.whileaway.forgetcmd.util.exception.CommonException.*;

public enum SnapshotError implements CommonError {
    NOT_FOUND(new NotFoundException("snapshot.not_found")),
    FORBIDDEN(new ForbiddenException("snapshot.forbidden")),
    UNAUTHORIZED(new UnauthorizedException("snapshot.unauthorized")),
    SHARE_CODE_NOT_MATCH(new ForbiddenException("snapshot.share_code_not_match")),
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
