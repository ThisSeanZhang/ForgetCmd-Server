package io.whileaway.forgetcmd.rbac.aop;

import io.whileaway.forgetcmd.rbac.annotation.NeedPermit;
import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.rbac.task.RelatedTask;
import io.whileaway.forgetcmd.util.ValidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class VerifyPermit {

    private final RelatedTask relatedTask;
    private final ValidUtil validUtil;

    public VerifyPermit(RelatedTask relatedTask, ValidUtil validUtil) {
        this.relatedTask = relatedTask;
        this.validUtil = validUtil;
    }

    @Before(value = "@annotation(needPermit)", argNames = "joinPoint, needPermit")
    public void beforeCheck(JoinPoint joinPoint, NeedPermit needPermit) {
        System.out.println(joinPoint.getSignature());
        System.out.println("AspectJ" + Arrays.toString(joinPoint.getArgs()));
        Developer developer = validUtil.getCurrentDeveloper();
//        if (developer.getAdmin()) return;
        Long pid = validUtil.getURITempleVariables(needPermit.resourceId());
        System.out.println("pid为" + pid);
    }
}
