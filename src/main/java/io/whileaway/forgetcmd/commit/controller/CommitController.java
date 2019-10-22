package io.whileaway.forgetcmd.commit.controller;

import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.request.AddLogSearchRequest;
import io.whileaway.forgetcmd.commit.request.CmdAddRequest;
import io.whileaway.forgetcmd.commit.task.CommitTask;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/verifies")
public class CommitController {

    private final CommitTask task;

    public CommitController(CommitTask task) {
        this.task = task;
    }

    @PostMapping("/cmds")
    public Result createAddCommand(@RequestBody CmdAddRequest request) {
        task.addCmdLog(request);
        return ResultUtil.success();
    }

    @GetMapping("/cmds/{cid}")
    public Result<CommandCommit> findAddCommandById(@PathVariable("cid")Long cid) {
        return ResultUtil.success(task.findById(cid));
    }

    @GetMapping("/search")
    public Result<List<CommandCommit>> getAll(AddLogSearchRequest request) {
        return ResultUtil.success(task.searchAddLog(request));
    }

    @PutMapping("/cmds/{cid}/pass")
    public Result changeStatus(@PathVariable("cid") Long cid) {
        task.passTheLog(cid);
        return ResultUtil.success();
    }

}
