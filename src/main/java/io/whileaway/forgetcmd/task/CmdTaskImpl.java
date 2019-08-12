package io.whileaway.forgetcmd.task;

import io.whileaway.forgetcmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.service.CmdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
