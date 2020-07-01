package io.whileaway.forgetcmd.util.exception;

import static io.whileaway.forgetcmd.util.StringUtils.notEmptyOne;

public class CommonException extends RuntimeException{

    private Integer code;

    public CommonException(Integer code, String message) {
        super("other.except." + message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static class ParamErrorException extends CommonException{
        public ParamErrorException(String message) {
            super(400, notEmptyOne(message, "bad_request"));
        }
    }

    public static class ForbiddenException extends CommonException{
        public ForbiddenException(String message) {
            super(403, notEmptyOne(message, "forbidden"));
        }
    }

    public static class UnauthorizedException extends CommonException {
        public UnauthorizedException(String message) {
            super(401, notEmptyOne(message, "unauthorized"));
        }
    }

    public static class NotFoundException extends CommonException{
        public NotFoundException(String message) {
            super(404,  notEmptyOne(message, "not_found"));
        }
    }

    public static class ServerErrorException extends CommonException{
        public ServerErrorException(String message) {
            super(500, notEmptyOne(message, "server_error"));
        }
    }

}
