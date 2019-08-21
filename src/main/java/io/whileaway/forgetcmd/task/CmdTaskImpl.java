package io.whileaway.forgetcmd.task;

import io.whileaway.forgetcmd.entities.CmdOption;
import io.whileaway.forgetcmd.entities.Command;
import io.whileaway.forgetcmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.service.CmdService;
import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CmdTaskImpl implements CmdTask {
    private final CmdService cmdService;

    @Autowired
    public CmdTaskImpl(CmdService cmdService) {
        this.cmdService = cmdService;
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
        return null;
    }
}
