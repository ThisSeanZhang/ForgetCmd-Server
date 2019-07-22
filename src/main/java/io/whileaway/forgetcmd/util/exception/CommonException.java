package io.whileaway.forgetcmd.util.exception;

public class CommonException extends RuntimeException{

    private Integer code;

    public CommonException(Integer code, String message) {
        super(message);
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
            super(400, message);
        }
    }

    public static class NotFoundException extends CommonException{
        public NotFoundException(String message) {
            super(404, message);
        }
    }

}
