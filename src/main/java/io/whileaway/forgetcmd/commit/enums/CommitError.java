package io.whileaway.forgetcmd.commit.enums;

import io.whileaway.forgetcmd.util.enums.CommonError;
import io.whileaway.forgetcmd.util.exception.CommonException;

public enum CommitError implements CommonError {
    CID_NOT_NULL(new CommonException.ParamErrorException("参数错误")),
    NOT_FOUND(new CommonException.NotFoundException("找不到相关资源")),
    CMD_PARAM_NOT_FOUND(new CommonException.NotFoundException("找不到命令参数")),
    CMD_CREATE_EXIST(new CommonException.NotFoundException("命令已存在，无法创建")),
    CMD_OPTION_NOT_FOUND(new CommonException.NotFoundException("找不到命令参数")),

    VERSION_IMPOSSIBLE_BE_NULL(new CommonException.ParamErrorException("版本号不可能为空")),
    NO_MATCH_COMMITS(new CommonException.NotFoundException("没有相关的修改请求")),
    ;
    private CommonException exception;

    CommitError(CommonException exception) {
        this.exception = exception;
    }

    @Override
    public CommonException getException() {
        return this.exception;
    }
}
