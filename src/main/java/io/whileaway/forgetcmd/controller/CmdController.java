package io.whileaway.forgetcmd.controller;

import io.whileaway.forgetcmd.entities.CmdOption;
import io.whileaway.forgetcmd.entities.Command;
import io.whileaway.forgetcmd.response.JustNeedKeyValue;
import io.whileaway.forgetcmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.task.CmdTask;
import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cmds")
public class CmdController {

    private final CmdTask commandTask;

    @Autowired
    public CmdController(CmdTask commandTask) {
        this.commandTask = commandTask;
    }

    @GetMapping("/{cid}")
    public Result<Command> getCommand(@PathVariable("cid") Long cid){
        return ResultUtil.success(commandTask.findById(cid));
    }

    @GetMapping("/{cid}/options")
    public Result<List<CmdOption>> getCmdOptions(@PathVariable("cid") Long cid){
        return ResultUtil.success(commandTask.findCmdOptions(cid));
    }

    @GetMapping("/search-bar")
    public Result<List<SearchCmdResponse>> getCommand(@RequestParam("keyword") String keyword){
        return ResultUtil.success(commandTask.searchByKeyWord(keyword));
    }
}