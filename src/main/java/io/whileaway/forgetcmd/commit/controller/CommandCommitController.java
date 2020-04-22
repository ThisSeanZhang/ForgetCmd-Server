package io.whileaway.forgetcmd.commit.controller;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.commit.request.ConfirmCommitRequest;
import io.whileaway.forgetcmd.commit.response.CommandListResponse;
import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.request.CommitSearchRequest;
import io.whileaway.forgetcmd.commit.request.CommandCommitRequest;
import io.whileaway.forgetcmd.commit.task.CommitTask;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commits")
public class CommandCommitController {

    private final CommitTask task;

    public CommandCommitController(CommitTask task) {
        this.task = task;
    }

    /**
     * TODO 还没限制重复的命令名称  还有命令名称需要进行处理下
     * @param request
     * @return
     */
    @PostMapping
    public Result createCommit(@RequestBody CommandCommitRequest request) {
        return ResultUtil.success(task.createCommandCommit(request));
    }

    @GetMapping
    public Result<List<CommandCommit>> search(CommitSearchRequest request) {
        return ResultUtil.success(task.searchAddLog(request));
    }

    @GetMapping("/cmds")
    public Result<List<CommandListResponse>> commitCommandList() {
        return ResultUtil.success(task.commitCommandList());
    }


    @GetMapping("/{ccid}")
    public Result<CommandCommit> getCommit(@PathVariable("ccid")Long cid) {
        return ResultUtil.success(task.findById(cid));
    }

    @GetMapping("cmds/{cid}")
    public Result<List<CommandCommit>> getCommandCommit(@PathVariable("cid")Long cid) {
        return ResultUtil.success();
    }

}
