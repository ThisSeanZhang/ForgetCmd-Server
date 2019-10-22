package io.whileaway.forgetcmd.commit.service;

import io.whileaway.forgetcmd.util.BaseService;
import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.request.AddLogSearchRequest;

import java.util.List;

public interface CommandCommitService extends BaseService<CommandCommit, Long> {

    List<CommandCommit> search(AddLogSearchRequest request);
}
