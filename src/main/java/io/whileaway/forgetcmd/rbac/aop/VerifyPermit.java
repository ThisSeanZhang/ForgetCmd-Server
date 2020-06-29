package io.whileaway.forgetcmd.rbac.aop;

import io.whileaway.forgetcmd.rbac.annotation.NeedPermit;
import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.rbac.enums.PermissionType;
import io.whileaway.forgetcmd.rbac.enums.RelatedError;
import io.whileaway.forgetcmd.rbac.request.PermitCheckRequest;
import io.whileaway.forgetcmd.rbac.task.RelatedTask;
import io.whileaway.forgetcmd.util.ValidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

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
//        System.out.println(joinPoint.getSignature());
//        System.out.println("AspectJ" + Arrays.toString(joinPoint.getArgs()));
        Developer developer = validUtil.getCurrentDeveloper();
        var isLogin = Objects.nonNull(developer.getDid());
//        if (developer.getAdmin()) return;
        Long pid = validUtil.getURITempleVariables(needPermit.resourceId());
        System.out.println("pid为" + pid);

        PermitCheckRequest request = new PermitCheckRequest();
        request.setDid(developer.getDid());
        request.setResourceId(pid);
        request.setPermission(needPermit.permission());
        request.setType(needPermit.type());
        request.setAfterCheck((permissions) -> {
            var hasPermit = permissions.contains(request.getPermission());
            if (hasPermit) return;
            var isOpen = permissions.contains(PermissionType.GET);
            if (!isOpen) {
                RelatedError.NOT_FOUND.throwThis();
            } else if (isLogin) {
                RelatedError.FORBIDDEN.throwThis();
            } else {
                RelatedError.UNAUTHORIZED.throwThis();
            }
        });
        boolean hasPermit = relatedTask.checkPermit(request);

//        if (!hasPermit && Objects.isNull(developer.getDid())) {
//            RelatedError.UNAUTHORIZED.throwThis();
//        } else if (!hasPermit && Objects.nonNull(developer.getDid())) {
//            RelatedError.FORBIDDEN.throwThis();
//        }

    }
}
