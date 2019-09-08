package io.whileaway.forgetcmd.cmd.controller;

import io.whileaway.forgetcmd.cmd.entities.CmdOption;
import io.whileaway.forgetcmd.cmd.entities.CmdParam;
import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.cmd.task.CmdTask;
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

    @GetMapping("/{cid}/params")
        public Result<List<CmdParam>> getCmdParams(@PathVariable("cid") Long cid){
        return ResultUtil.success(commandTask.findCmdParams(cid));
    }

    @GetMapping("/search-bar")
    public Result<List<SearchCmdResponse>> getCommand(@RequestParam("keyword") String keyword){
        return ResultUtil.success(commandTask.searchByKeyWord(keyword));
    }
}
