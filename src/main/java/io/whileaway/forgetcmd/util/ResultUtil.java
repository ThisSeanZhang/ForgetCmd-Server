package io.whileaway.forgetcmd.util;

import io.whileaway.forgetcmd.util.exception.CommonException;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultUtil {

    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setStatus(code);
        result.setMessage(message);
        return result;
    }

    public static Result error(CommonException e) {
        return error(e.getCode(),e.getMessage());
    }

    public static<T> Result<T> success(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        result.setStatus(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static<T> Result<T> success(Integer code, String message) {
        return success(code, message, null);
    }

    public static<T> Result<T> success() {
        return success(200, "成功", null);
    }

    public static<T> Result<T> success(T data) {
        return success(200, "成功", data);
    }
    public static<T> Result<List<T>> checkList(List<T> data, CommonException exception) {
        if (Objects.isNull(data) || data.isEmpty()) {
            throw exception;
        }
        return success(200, "成功", data);
    }

    public static void inspect(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return ;
        }
        final String format = "字段%s出现错误:%s";
        Optional<String> message = bindingResult
                .getFieldErrors()
                .stream()
                .map( error -> String.format(format, error.getField(), error.getDefaultMessage()))
                .reduce(String::join);
        throw new CommonException(400,message.orElse(""));
    }

    public static String invalidFormatDataMessage(String message) {
        final String format = "%s转换出错,请检查格式是否为年-月-日,例2019-01-01";
        final String pattern = "\\[\"(.*Time)+\"\\]";
        String info = "未知字段";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(message);
        info = m.find() ? m.group(0) : info;
        return String.format(format,info.replaceAll("\\[\"|\"\\]",""));
    }
    public static void main(String [] args) {
        String message = "Cannot deserialize value of type `java.util.Date` from String \"2018-12\": expected format \"yyyy-MM-dd\" at [Source: (PushbackInputStream); line: 1, column: 61] (through reference chain: io.whileaway.apit.account.request.CreateDeveloper[\"joinTime\"])";
        System.out.println(message);
        final String pattern = "\\[\"(.*Time)\"\\]";
        System.out.println(pattern);
        String info = "未知字段";
        System.out.println(message.matches(pattern));
//        if (message.matches(pattern)) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(message);
            info = m.find(0) ? m.group(0).replaceAll("\\[\"|\"\\]","") : info;
//        }
        System.out.println(info);
    }
}
