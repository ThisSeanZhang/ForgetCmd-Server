package io.whileaway.forgetcmd.commit.task;


import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.request.CommitSearchRequest;
import io.whileaway.forgetcmd.commit.request.CommandCommitRequest;
import io.whileaway.forgetcmd.commit.request.ConfirmCommitRequest;
import io.whileaway.forgetcmd.commit.response.CommandListResponse;

import java.util.List;

public interface CommitTask {

    CommandCommit createCommandCommit(CommandCommitRequest request);

    List<CommandCommit> searchAddLog(CommitSearchRequest request);

    CommandCommit findById(Long id);

    void passTheLog(Long cid);

    void rejectTheCommit(Long cid);

    List<CommandListResponse> commitCommandList();

}
