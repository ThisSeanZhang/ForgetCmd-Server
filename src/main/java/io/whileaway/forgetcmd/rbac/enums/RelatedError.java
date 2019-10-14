package io.whileaway.forgetcmd.rbac.enums;

import io.whileaway.forgetcmd.util.enums.CommonError;
import io.whileaway.forgetcmd.util.exception.CommonException;

public enum  RelatedError implements CommonError {
    PARAM_ERROR(new CommonException.ParamErrorException("参数错误")),
    NOT_FOUND(new CommonException.NotFoundException("找不到相关资源")),
    CMD_PARAM_NOT_FOUND(new CommonException.NotFoundException("找不到命令参数")),
    CMD_OPTION_NOT_FOUND(new CommonException.NotFoundException("找不到命令参数")),
    ;
    private CommonException exception;

    RelatedError(CommonException exception) {
        this.exception = exception;
    }

    @Override
    public CommonException getException() {
        return this.exception;
    }
}
