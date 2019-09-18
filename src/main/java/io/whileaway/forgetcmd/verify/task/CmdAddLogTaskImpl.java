package io.whileaway.forgetcmd.verify.task;

import io.whileaway.forgetcmd.verify.request.CmdAddRequest;
import io.whileaway.forgetcmd.verify.service.CmdAddLogService;
import org.springframework.stereotype.Component;

@Component
public class CmdAddLogTaskImpl implements CmdAddLogTask {

    private final CmdAddLogService service;

    public CmdAddLogTaskImpl(CmdAddLogService service) {
        this.service = service;
    }

    @Override
    public void addCmdLog(CmdAddRequest request) {
        service.save(request.convertToCmdAddLog());
    }
}
