package io.whileaway.forgetcmd.util.enums;

import io.whileaway.forgetcmd.util.exception.CommonException;
import io.whileaway.forgetcmd.util.exception.CommonException.*;

public enum CommonErrorEnum implements CommonError {
    PARAM_ERROR(new ParamErrorException("参数错误")),
    NOT_FOUND(new NotFoundException("找不到相关资源")),
    ;
    private CommonException exception;

    CommonErrorEnum(CommonException exception) {
        this.exception = exception;
    }

    public CommonException getException() {
        return this.exception;
    }

}