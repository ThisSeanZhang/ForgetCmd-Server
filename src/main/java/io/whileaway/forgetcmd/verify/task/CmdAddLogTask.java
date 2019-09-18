package io.whileaway.forgetcmd.verify.task;


import io.whileaway.forgetcmd.verify.request.CmdAddRequest;

public interface CmdAddLogTask {
    void addCmdLog(CmdAddRequest request);
}
