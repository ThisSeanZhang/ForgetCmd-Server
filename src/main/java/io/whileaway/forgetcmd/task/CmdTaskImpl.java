package io.whileaway.forgetcmd.task;

import io.whileaway.forgetcmd.entities.CmdOption;
import io.whileaway.forgetcmd.entities.CmdParam;
import io.whileaway.forgetcmd.entities.Command;
import io.whileaway.forgetcmd.enums.CmdError;
import io.whileaway.forgetcmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.service.CmdParamService;
import io.whileaway.forgetcmd.service.CmdService;
import io.whileaway.forgetcmd.service.OptionService;
import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CmdTaskImpl implements CmdTask {
    private final CmdService cmdService;
    private final OptionService optionService;
    private final CmdParamService paramService;
    @Autowired
    public CmdTaskImpl(CmdService cmdService, OptionService optionService, CmdParamService paramService) {
        this.cmdService = cmdService;
        this.optionService = optionService;
        this.paramService = paramService;
    }

    @Override
    public List<SearchCmdResponse> searchByKeyWord(String keyword) {
        return cmdService.searchByKeyWord(keyword);
    }

    @Override
    public Command findById(Long cid) {
        Optional<Command> cmd = cmdService.findById(cid);
        return cmd.orElseThrow(CommonErrorEnum.NOT_FOUND::getException);
    }

    @Override
    public List<CmdOption> findCmdOptions(Long cid) {
        Optional<List<CmdOption>> options = optionService.findByCid(cid);
        return options.orElseThrow(CmdError.CMD_OPTION_NOT_FOUND::getException);
    }

    @Override
    public List<CmdParam> findCmdParams(Long cid) {
        return paramService.findBydCid(cid).orElseThrow(CmdError.CMD_PARAM_NOT_FOUND::getException);
    }
}
