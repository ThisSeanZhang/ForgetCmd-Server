package io.whileaway.forgetcmd.rbac.request;

import lombok.Data;

@Data
public class CreateSession {

    private String nameOrEmail;
    private String  pass;
    private Boolean rememberMe;

}
