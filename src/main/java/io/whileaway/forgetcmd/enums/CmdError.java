package io.whileaway.forgetcmd.enums;

import io.whileaway.forgetcmd.util.enums.CommonError;
import io.whileaway.forgetcmd.util.exception.CommonException;

public enum CmdError implements CommonError {
    CID_NOT_NULL(new CommonException.ParamErrorException("参数错误")),
    NOT_FOUND(new CommonException.NotFoundException("找不到相关资源")),
    ;
    private CommonException exception;

    CmdError(CommonException exception) {
        this.exception = exception;
    }

    @Override
    public CommonException getException() {
        return this.exception;
    }
}
