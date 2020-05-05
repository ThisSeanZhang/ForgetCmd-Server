package io.whileaway.forgetcmd.cmd.controller;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.request.SearchCmdRequest;
import io.whileaway.forgetcmd.commit.task.AdminTask;
import io.whileaway.forgetcmd.rbac.annotation.AdminPermit;
import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/cmds")
public class AdminController {

    private final AdminTask adminTask;

    public AdminController(AdminTask adminTask) {
        this.adminTask = adminTask;
    }

    @GetMapping
    @AdminPermit
    public Result<List<Command>> searchCommand(SearchCmdRequest request) {
        return ResultUtil.success(adminTask.searchCommand(request));
    }

    @PutMapping("{cid}/status/pause")
    @AdminPermit
    public Result<Command> pauseCommand(@PathVariable("cid") Long cid) {
        return ResultUtil.success(adminTask.pauseCommand(cid));
    }

}
