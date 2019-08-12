package io.whileaway.forgetcmd.util;

import lombok.Data;

@Data
public class Result<A> {
    private Integer status;
    private String message;
    private A data;
    private String redirect;
}
