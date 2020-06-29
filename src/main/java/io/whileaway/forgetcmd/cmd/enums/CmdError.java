package io.whileaway.forgetcmd.cmd.enums;

import io.whileaway.forgetcmd.util.enums.CommonError;
import io.whileaway.forgetcmd.util.exception.CommonException;

public enum CmdError implements CommonError {
    CID_NOT_NULL(new CommonException.ParamErrorException("参数错误")),
    NOT_FOUND(new CommonException.NotFoundException("找不到相关资源")),
    CMD_PARAM_NOT_FOUND(new CommonException.NotFoundException("找不到命令参数")),
    CMD_CREATE_EXIST(new CommonException.NotFoundException("命令已存在，无法创建")),
    CMD_OPTION_NOT_FOUND(new CommonException.NotFoundException("找不到命令参数")),
    SUBMITTED_VERSION_IS_OUT_OF_DATE(new CommonException.ParamErrorException("所提交的版本已经过时")),
    CREATE_CMD_HISTORY_ERROR(new CommonException.ServerErrorException("创建命令历史错误")),
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
