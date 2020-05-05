package io.whileaway.forgetcmd.commit.task;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.request.SearchCmdRequest;

import java.util.List;

public interface AdminTask {

    List<Command> searchCommand(SearchCmdRequest request);

    Command pauseCommand(Long cid);
}
