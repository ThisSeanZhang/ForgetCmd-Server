package io.whileaway.forgetcmd.cmd.task;

import io.whileaway.forgetcmd.cmd.entities.CmdHistory;
import io.whileaway.forgetcmd.cmd.entities.CmdOption;
import io.whileaway.forgetcmd.cmd.entities.CmdParam;
import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.enums.CmdError;
import io.whileaway.forgetcmd.cmd.request.CreateCmdRequest;
import io.whileaway.forgetcmd.cmd.response.CommandResponse;
import io.whileaway.forgetcmd.cmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.cmd.service.CmdParamService;
import io.whileaway.forgetcmd.cmd.service.CmdService;
import io.whileaway.forgetcmd.cmd.service.OptionService;
import io.whileaway.forgetcmd.commit.service.CommandCommitService;
import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class CmdTaskImpl implements CmdTask {

    private final CmdService cmdService;
    private final OptionService optionService;
    private final CmdParamService paramService;
    private final CommandCommitService commitService;

    @Autowired
    public CmdTaskImpl(CmdService cmdService, OptionService optionService, CmdParamService paramService, CommandCommitService commitService) {
        this.cmdService = cmdService;
        this.optionService = optionService;
        this.paramService = paramService;
        this.commitService = commitService;
    }

    @Override
    public List<SearchCmdResponse> searchByKeyWord(String keyword) {
        return cmdService.searchByKeyWord(keyword);
    }

    @Override
    public CommandResponse findById(Long cid) {
        Command cmd = cmdService.findById(cid).orElseThrow(CommonErrorEnum.NOT_FOUND::getException);
        CommandResponse response = CommandResponse.createFromCmd(cmd).orElseThrow(CommonErrorEnum.SERVER_ERROR::getException);
        optionService.findByCid(cmd.getCid()).ifPresent(response::setOptions);
        paramService.findByCid(cmd.getCid()).ifPresent(response::setParams);
        return response;
    }

    @Override
    public List<CmdOption> findCmdOptions(Long cid) {
        Optional<List<CmdOption>> options = optionService.findByCid(cid);
        return options.orElseThrow(CmdError.CMD_OPTION_NOT_FOUND::getException);
    }

    @Override
    public List<CmdParam> findCmdParams(Long cid) {
        return paramService.findByCid(cid).orElseThrow(CmdError.CMD_PARAM_NOT_FOUND::getException);
    }

    @Override
    @Transactional
    public Command createCmd(CreateCmdRequest request) {
        Command newCmd = cmdService.save(request);
        commitService.confirmCommit(request.toConfirmCommitRequest());
        paramService.updateCommandParams(newCmd.getCid(), request.getParams());
        optionService.updateCommandOptions(newCmd.getCid(), request.getOptions());
        return newCmd;
    }

}
