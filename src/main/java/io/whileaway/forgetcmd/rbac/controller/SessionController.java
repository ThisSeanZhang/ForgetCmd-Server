package io.whileaway.forgetcmd.rbac.controller;

import io.whileaway.forgetcmd.rbac.SessionKeyConstant;
import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.rbac.request.CreateSession;
import io.whileaway.forgetcmd.rbac.task.RBACTask;
import io.whileaway.forgetcmd.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.whileaway.forgetcmd.util.ResultUtil;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final RBACTask rbacTask;

    public SessionController(RBACTask rbacTask) {
        this.rbacTask = rbacTask;
    }


    @PostMapping
    public Result<Developer> createSession(CreateSession createSession) {
        return ResultUtil.success(rbacTask.createSession(createSession));
    }
}
