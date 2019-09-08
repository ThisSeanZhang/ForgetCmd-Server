package io.whileaway.forgetcmd.util.exception;

import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class CommonHandle {

    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public Result commonHandle(CommonException commonException, HttpServletResponse response) {
//        CommonException commonException = (CommonException) e;
        response.setStatus(commonException.getCode());
        return ResultUtil.error(commonException);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public Result requestHandle(MissingServletRequestParameterException exception, HttpServletResponse response) {
        response.setStatus(400);
        return ResultUtil.error(400, exception.getMessage());
    }
}
