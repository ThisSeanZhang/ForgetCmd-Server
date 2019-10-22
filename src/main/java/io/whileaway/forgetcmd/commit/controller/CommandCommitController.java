package io.whileaway.forgetcmd.commit.controller;

import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.request.AddLogSearchRequest;
import io.whileaway.forgetcmd.commit.request.CommandCommitRequest;
import io.whileaway.forgetcmd.commit.task.CommitTask;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commits/cmds")
public class CommandCommitController {

    private final CommitTask task;

    public CommandCommitController(CommitTask task) {
        this.task = task;
    }

    @PostMapping
    public Result createCommandCommit(@RequestBody CommandCommitRequest request) {
        task.createCommandCommit(request);
        return ResultUtil.success();
    }

    @GetMapping("{cid}")
    public Result<CommandCommit> findAddCommandById(@PathVariable("cid")Long cid) {
        return ResultUtil.success(task.findById(cid));
    }

    @GetMapping
    public Result<List<CommandCommit>> getAll(AddLogSearchRequest request) {
        return ResultUtil.success(task.searchAddLog(request));
    }

    @PutMapping("/cmds/{cid}/pass")
    public Result changeStatus(@PathVariable("cid") Long cid) {
        task.passTheLog(cid);
        return ResultUtil.success();
    }

}
