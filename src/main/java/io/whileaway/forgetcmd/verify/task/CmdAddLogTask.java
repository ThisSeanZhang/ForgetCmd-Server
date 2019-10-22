package io.whileaway.forgetcmd.verify.task;


import io.whileaway.forgetcmd.verify.entities.CommandCommit;
import io.whileaway.forgetcmd.verify.request.AddLogSearchRequest;
import io.whileaway.forgetcmd.verify.request.CmdAddRequest;

import java.util.List;

public interface CmdAddLogTask {

    void addCmdLog(CmdAddRequest request);

    List<CommandCommit> searchAddLog(AddLogSearchRequest request);

    CommandCommit findById(Long id);

    void passTheLog(Long cid);
}
