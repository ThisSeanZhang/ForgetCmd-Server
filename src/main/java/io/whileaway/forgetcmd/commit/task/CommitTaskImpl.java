package io.whileaway.forgetcmd.commit.task;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.request.CreateCmdRequest;
import io.whileaway.forgetcmd.cmd.task.CmdTask;
import io.whileaway.forgetcmd.commit.entities.CommitItem;
import io.whileaway.forgetcmd.commit.enums.CommitError;
import io.whileaway.forgetcmd.commit.request.ConfirmCommitRequest;
import io.whileaway.forgetcmd.commit.request.ItemSearchRequest;
import io.whileaway.forgetcmd.commit.response.CommandListResponse;
import io.whileaway.forgetcmd.rbac.task.RelatedTask;
import io.whileaway.forgetcmd.util.StringUtils;
import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.enums.CommitStatus;
import io.whileaway.forgetcmd.commit.request.CommitSearchRequest;
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
        return service.createCommit(request);
    }

    @Override
    public List<CommandCommit> searchAddLog(CommitSearchRequest request) {
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
        Command cmd = cmdTask.createCmd(new CreateCmdRequest());
        addLog.setCid(cmd.getCid());
        addLog.setStatus(CommitStatus.REFERENCED);
        service.save(addLog);
    }

    @Override
    public void rejectTheCommit(Long cid) {
        CommandCommit commit = findById(cid);
        commit.setStatus(CommitStatus.CREATE_REJECT);
        service.save(commit);
    }

    @Override
    public List<CommandListResponse> commitCommandList() {
        return service.commitCommandList();
    }

}
