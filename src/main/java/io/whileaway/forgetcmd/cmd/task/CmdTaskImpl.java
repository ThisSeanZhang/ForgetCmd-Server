package io.whileaway.forgetcmd.cmd.task;

import io.whileaway.forgetcmd.cmd.entities.CmdOption;
import io.whileaway.forgetcmd.cmd.entities.CmdParam;
import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.enums.CmdError;
import io.whileaway.forgetcmd.cmd.request.CreateCmdRequest;
import io.whileaway.forgetcmd.cmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.cmd.service.CmdParamService;
import io.whileaway.forgetcmd.cmd.service.CmdService;
import io.whileaway.forgetcmd.cmd.service.OptionService;
import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public Command createCmd(CreateCmdRequest request) {
        Command cmd = cmdService.save(request.getCommand());
        paramService.saveAll(request.getParams()
                .stream()
                .peek(p->p.setCid(cmd.getCid()))
                .collect(Collectors.toList())
        );
        optionService.saveAll(request.getOptions()
                .stream()
                .peek(o -> o.setCid(cmd.getCid()))
                .collect(Collectors.toList())
        );
        return cmd;
    }
}
