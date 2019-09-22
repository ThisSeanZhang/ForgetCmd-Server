package io.whileaway.forgetcmd.verify.task;


import io.whileaway.forgetcmd.verify.entities.CmdAddLog;
import io.whileaway.forgetcmd.verify.request.AddLogSearchRequest;
import io.whileaway.forgetcmd.verify.request.CmdAddRequest;
import io.whileaway.forgetcmd.verify.response.CmdAddLogBriefResponse;

import java.util.List;

public interface CmdAddLogTask {
    void addCmdLog(CmdAddRequest request);

    List<CmdAddLog> searchAddLog(AddLogSearchRequest request);

    CmdAddLog findById(Long id);
}
