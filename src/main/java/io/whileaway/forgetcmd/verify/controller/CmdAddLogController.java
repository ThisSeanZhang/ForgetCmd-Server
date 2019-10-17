package io.whileaway.forgetcmd.verify.controller;

import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import io.whileaway.forgetcmd.verify.entities.CmdAddLog;
import io.whileaway.forgetcmd.verify.enums.AddLogStatus;
import io.whileaway.forgetcmd.verify.request.AddLogSearchRequest;
import io.whileaway.forgetcmd.verify.request.CmdAddRequest;
import io.whileaway.forgetcmd.verify.response.CmdAddLogBriefResponse;
import io.whileaway.forgetcmd.verify.task.CmdAddLogTask;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/cmds/{cid}")
    public Result<CmdAddLog> findAddCommandById(@PathVariable("cid")Long cid) {
        return ResultUtil.success(task.findById(cid));
    }

    @GetMapping("/search")
    public Result<List<CmdAddLog>> getAll(AddLogSearchRequest request) {
        return ResultUtil.success(task.searchAddLog(request));
    }

    @PutMapping("/cmds/{cid}/pass")
    public Result changeStatus(@PathVariable("cid") Long cid) {
        task.passTheLog(cid);
        return ResultUtil.success();
    }

}
