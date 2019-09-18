package io.whileaway.forgetcmd.verify.controller;

import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import io.whileaway.forgetcmd.verify.request.CmdAddRequest;
import io.whileaway.forgetcmd.verify.task.CmdAddLogTask;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verifies")
public class CmdAddLogController {

    private final CmdAddLogTask task;

    public CmdAddLogController(CmdAddLogTask task) {
        this.task = task;
    }

    @PostMapping("/cmds")
    public Result createAddCommand(@RequestBody CmdAddRequest request) {
        task.addCmdLog(request);
        return ResultUtil.success();
    }
}
