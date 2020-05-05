package io.whileaway.forgetcmd.rbac.controller;

import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.rbac.request.CreateSession;
import io.whileaway.forgetcmd.rbac.task.RBACTask;
import io.whileaway.forgetcmd.util.Result;
import org.springframework.web.bind.annotation.*;

import io.whileaway.forgetcmd.util.ResultUtil;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    private final RBACTask rbacTask;

    public SessionController(RBACTask rbacTask) {
        this.rbacTask = rbacTask;
    }


    @PostMapping
    public Result<Developer> createSession(@RequestBody CreateSession createSession) {
        return ResultUtil.success(rbacTask.createSession(createSession));
    }

    @DeleteMapping("/{did}")
    public Result deleteSession(@PathVariable("did") Long did) {
        rbacTask.deleteSession(did);
        return ResultUtil.success();
    }
}
