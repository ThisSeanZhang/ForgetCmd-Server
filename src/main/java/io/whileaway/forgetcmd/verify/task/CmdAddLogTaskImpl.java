package io.whileaway.forgetcmd.verify.task;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.task.CmdTask;
import io.whileaway.forgetcmd.rbac.enums.PermissionType;
import io.whileaway.forgetcmd.rbac.enums.ResourceType;
import io.whileaway.forgetcmd.rbac.request.CreateRelatedRequest;
import io.whileaway.forgetcmd.rbac.task.RBACTask;
import io.whileaway.forgetcmd.rbac.task.RelatedTask;
import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import io.whileaway.forgetcmd.verify.entities.CmdAddLog;
import io.whileaway.forgetcmd.verify.enums.AddLogStatus;
import io.whileaway.forgetcmd.verify.request.AddLogSearchRequest;
import io.whileaway.forgetcmd.verify.request.CmdAddRequest;
import io.whileaway.forgetcmd.verify.service.CmdAddLogService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class CmdAddLogTaskImpl implements CmdAddLogTask {

    private final CmdAddLogService service;
    private final CmdTask cmdTask;
    private final RelatedTask relatedTask;

    public CmdAddLogTaskImpl(CmdAddLogService service, CmdTask cmdTask, RelatedTask relatedTask) {
        this.service = service;
        this.cmdTask = cmdTask;
        this.relatedTask = relatedTask;
    }

    @Override
    @Transactional
    public void addCmdLog(CmdAddRequest request) {
        CmdAddLog save = service.save(request.convertToCmdAddLog());
        CreateRelatedRequest relatedRequest = new CreateRelatedRequest();
        relatedRequest.setResourceId(save.getRid());
        relatedRequest.setPermits(PermissionType.allPermission());
        relatedRequest.setType(ResourceType.VERIFY);
        relatedTask.createRelated(relatedRequest);
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
