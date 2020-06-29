package io.whileaway.forgetcmd.cmd.controller;

import io.whileaway.forgetcmd.cmd.enums.OptionTypeEnum;
import io.whileaway.forgetcmd.util.Result;
import io.whileaway.forgetcmd.util.ResultUtil;
import io.whileaway.forgetcmd.util.enums.IntegerKeyValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/options")
public class OptionController {

    @GetMapping("/types")
    public Result<List<IntegerKeyValue>> getAllType() {
        return ResultUtil.success(OptionTypeEnum.getExhibit());
    }
}
