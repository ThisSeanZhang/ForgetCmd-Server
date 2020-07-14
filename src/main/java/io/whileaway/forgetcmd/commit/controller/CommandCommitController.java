package io.whileaway.forgetcmd.commit.controller;

import io.whileaway.forgetcmd.commit.response.CommandListResponse;
import io.whileaway.forgetcmd.rbac.annotation.AdminPermit;
import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.request.CommitSearchRequest;
import io.whileaway.forgetcmd.commit.request.CommandCommitRequest;
import io.whileaway.forgetcmd.commit.task.CommitTask;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.whileaway.forgetcmd.util.ResultUtil.success;

@RestController
@RequestMapping("/commits")
public class CommandCommitController {

    private final CommitTask task;

    public CommandCommitController(CommitTask task) {
        this.task = task;
    }

    /** 创建修改申请以及命令申请
     * @param request 请求主体
     * @return 创建结果
     */
    @PostMapping
    @AdminPermit
    public Result createCommit(@RequestBody CommandCommitRequest request) {
        return success(task.createCommandCommit(request));
    }

    @GetMapping
    @AdminPermit
    public Result<List<CommandCommit>> search(CommitSearchRequest request) {
        return success(task.searchAddLog(request));
    }

    @GetMapping("/cmds")
    @AdminPermit
    public Result<List<CommandListResponse>> commitCommandList() {
        return success(task.commitCommandList());
    }


    @GetMapping("/{ccid}")
    @AdminPermit
    public Result<CommandCommit> getCommit(@PathVariable("ccid")Long cid) {
        return success(task.findById(cid));
    }

    @GetMapping("cmds/{cid}")
    @AdminPermit
    public Result<List<CommandCommit>> getCommandCommit(@PathVariable("cid")Long cid) {
        return success();
    }

    @GetMapping("{did}")
    public Result<List<CommandCommit>> getUserCommits(@PathVariable("did")Long did) {
        return success(task.getUserAllCommits(did));
    }
}
