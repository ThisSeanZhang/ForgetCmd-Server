package io.whileaway.forgetcmd.verify.task;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.task.CmdTask;
import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import io.whileaway.forgetcmd.verify.entities.CmdAddLog;
import io.whileaway.forgetcmd.verify.enums.AddLogStatus;
import io.whileaway.forgetcmd.verify.request.AddLogSearchRequest;
import io.whileaway.forgetcmd.verify.request.CmdAddRequest;
import io.whileaway.forgetcmd.verify.service.CmdAddLogService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CmdAddLogTaskImpl implements CmdAddLogTask {

    private final CmdAddLogService service;
    private final CmdTask cmdTask;

    public CmdAddLogTaskImpl(CmdAddLogService service, CmdTask cmdTask) {
        this.service = service;
        this.cmdTask = cmdTask;
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

    @Override
    @Transactional
    public void passTheLog(Long cid) {
        CmdAddLog addLog = findById(cid);
        Command cmd = cmdTask.createCmd(addLog.createCommandRequest());
        addLog.setCid(cmd.getCid());
        addLog.setStatus(AddLogStatus.CREATE_SUCCESS);
        service.save(addLog);
    }
}
