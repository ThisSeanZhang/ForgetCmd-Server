package io.whileaway.forgetcmd.rbac.controller;

import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.rbac.request.CreateAccount;
import io.whileaway.forgetcmd.rbac.task.RBACTask;
import io.whileaway.forgetcmd.util.ParamInspect;
import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    private final RBACTask rbacTask;

    public DeveloperController(RBACTask rbacTask) {
        this.rbacTask = rbacTask;
    }

    @GetMapping("/developer-name/{key}")
    public Result matchTheWholeNameOrEmail(@PathVariable("key") String key) {
        return rbacTask.existNameOrEmail(key)
                ? ResultUtil.success()
                : ResultUtil.error(CommonErrorEnum.NOT_FOUND.getException());
    }

    @PostMapping
    public Result<Developer> createDeveloper(@RequestBody CreateAccount account) {
        return ResultUtil.success(rbacTask.createAccount(account));
    }

}
