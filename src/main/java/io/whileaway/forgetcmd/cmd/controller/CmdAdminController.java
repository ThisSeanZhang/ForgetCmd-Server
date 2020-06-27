package io.whileaway.forgetcmd.cmd.controller;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.request.SearchCmdRequest;
import io.whileaway.forgetcmd.commit.task.AdminTask;
import io.whileaway.forgetcmd.rbac.annotation.AdminPermit;
import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/cmds")
@AllArgsConstructor
public class CmdAdminController {

    private final AdminTask adminTask;

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
