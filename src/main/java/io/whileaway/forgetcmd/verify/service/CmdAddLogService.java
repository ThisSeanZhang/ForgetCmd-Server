package io.whileaway.forgetcmd.verify.service;

import io.whileaway.forgetcmd.util.BaseService;
import io.whileaway.forgetcmd.verify.entities.CmdAddLog;
import io.whileaway.forgetcmd.verify.request.AddLogSearchRequest;
import io.whileaway.forgetcmd.verify.response.CmdAddLogBriefResponse;

import java.util.List;

public interface CmdAddLogService extends BaseService<CmdAddLog, Long> {

    List<CmdAddLog> search(AddLogSearchRequest request);
}
