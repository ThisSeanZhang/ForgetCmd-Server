package io.whileaway.forgetcmd.commit.task;


import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.request.AddLogSearchRequest;
import io.whileaway.forgetcmd.commit.request.CommandCommitRequest;

import java.util.List;

public interface CommitTask {

    CommandCommit createCommandCommit(CommandCommitRequest request);

    List<CommandCommit> searchAddLog(AddLogSearchRequest request);

    CommandCommit findById(Long id);

    void passTheLog(Long cid);

    void rejectTheCommit(Long cid);
}
