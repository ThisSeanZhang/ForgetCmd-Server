package io.whileaway.forgetcmd.verify.task;

import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import io.whileaway.forgetcmd.verify.entities.CmdAddLog;
import io.whileaway.forgetcmd.verify.request.AddLogSearchRequest;
import io.whileaway.forgetcmd.verify.request.CmdAddRequest;
import io.whileaway.forgetcmd.verify.response.CmdAddLogBriefResponse;
import io.whileaway.forgetcmd.verify.service.CmdAddLogService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CmdAddLog> searchAddLog(AddLogSearchRequest request) {
//        return service.search(request).stream().map(CmdAddLog::convertBrief).collect(Collectors.toList());
        return service.search(request);
    }

    @Override
    public CmdAddLog findById(Long id) {
        return service.findById(id).orElseThrow(CommonErrorEnum.NOT_FOUND::getException);
    }
}
