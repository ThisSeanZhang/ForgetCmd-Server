package io.whileaway.forgetcmd.verify.service;

import io.whileaway.forgetcmd.util.BaseService;
import io.whileaway.forgetcmd.verify.entities.CommandCommit;
import io.whileaway.forgetcmd.verify.request.AddLogSearchRequest;
import io.whileaway.forgetcmd.verify.response.CmdAddLogBriefResponse;

import java.util.List;

public interface CommandCommitService extends BaseService<CommandCommit, Long> {

    List<CommandCommit> search(AddLogSearchRequest request);
}
