package io.whileaway.forgetcmd.rbac.enums;

import io.whileaway.forgetcmd.util.enums.CommonError;
import io.whileaway.forgetcmd.util.exception.CommonException;

public enum DeveloperError implements CommonError {
    CREATE_SESSION_NAME_NOT_NULL(new CommonException.ParamErrorException("登陆使用的名称不能为空")),
    NAME_OR_EMAIL_OR_PASS_ERROR(new CommonException.ParamErrorException("用户名或密码错误")),
    FORBIDDEN(new CommonException.ForbiddenException("访问拒绝")),
    UNAUTHORIZED(new CommonException.UnauthorizedException("未认证")),
    NOT_FOUND(new CommonException.NotFoundException("未找到相关资源")),
    CMD_OPTION_NOT_FOUND(new CommonException.NotFoundException("找不到命令参数")),
    ;
    private CommonException exception;

    DeveloperError(CommonException exception) {
        this.exception = exception;
    }

    @Override
    public CommonException getException() {
        return this.exception;
    }
}
