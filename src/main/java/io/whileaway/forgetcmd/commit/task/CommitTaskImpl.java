package io.whileaway.forgetcmd.commit.task;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.task.CmdTask;
import io.whileaway.forgetcmd.rbac.enums.PermissionType;
import io.whileaway.forgetcmd.rbac.enums.ResourceType;
import io.whileaway.forgetcmd.rbac.request.CreateRelatedRequest;
import io.whileaway.forgetcmd.rbac.task.RelatedTask;
import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.enums.CommitStatus;
import io.whileaway.forgetcmd.commit.request.AddLogSearchRequest;
import io.whileaway.forgetcmd.commit.request.CommandCommitRequest;
import io.whileaway.forgetcmd.commit.service.CommandCommitService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CommitTaskImpl implements CommitTask {

    private final CommandCommitService service;
    private final CmdTask cmdTask;
    private final RelatedTask relatedTask;

    public CommitTaskImpl(CommandCommitService service, CmdTask cmdTask, RelatedTask relatedTask) {
        this.service = service;
        this.cmdTask = cmdTask;
        this.relatedTask = relatedTask;
    }

    @Override
    @Transactional
    public CommandCommit createCommandCommit(CommandCommitRequest request) {
        CommandCommit save = service.save(request.convertToCommandCommit());
//        CreateRelatedRequest relatedRequest = new CreateRelatedRequest();
//        relatedRequest.setResourceId(save.getCcid());
//        relatedRequest.setPermits(PermissionType.allPermission());
//        relatedRequest.setType(ResourceType.VERIFY);
//        relatedTask.createRelated(relatedRequest);
        return save;
    }

    @Override
    public List<CommandCommit> searchAddLog(AddLogSearchRequest request) {
//        return service.search(request).stream().map(CmdAddLog::convertBrief).collect(Collectors.toList());
        return service.search(request);
    }

    @Override
    public CommandCommit findById(Long id) {
        return service.findById(id).orElseThrow(CommonErrorEnum.NOT_FOUND::getException);
    }

    @Override
    @Transactional
    public void passTheLog(Long cid) {
        CommandCommit addLog = findById(cid);
        Command cmd = cmdTask.createCmd(addLog.createCommandRequest());
        addLog.setCid(cmd.getCid());
        addLog.setStatus(CommitStatus.CREATE_SUCCESS);
        service.save(addLog);
    }
}
