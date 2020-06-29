package io.whileaway.forgetcmd.rbac.aop;

import io.whileaway.forgetcmd.rbac.annotation.AdminPermit;
import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.rbac.enums.DeveloperError;
import io.whileaway.forgetcmd.util.ValidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AdminVerify {

    private final ValidUtil validUtil;

    public AdminVerify(ValidUtil validUtil) {
        this.validUtil = validUtil;
    }

    @Before(value = "@annotation(adminPermit)", argNames = "joinPoint, adminPermit")
    public void beforeCheck(JoinPoint joinPoint, AdminPermit adminPermit) {
        System.out.println(joinPoint.getSignature());
        System.out.println("AspectJ" + Arrays.toString(joinPoint.getArgs()));
        Developer developer = validUtil.getCurrentDeveloper();
        if (!developer.isAdmin()) {
            DeveloperError.FORBIDDEN.throwThis();
        }

    }
}
