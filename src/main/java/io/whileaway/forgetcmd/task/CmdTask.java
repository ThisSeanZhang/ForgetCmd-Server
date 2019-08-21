package io.whileaway.forgetcmd.task;

import io.whileaway.forgetcmd.entities.CmdOption;
import io.whileaway.forgetcmd.entities.Command;
import io.whileaway.forgetcmd.response.SearchCmdResponse;

import java.util.List;

public interface CmdTask {

    List<SearchCmdResponse> searchByKeyWord(String keyword);

    Command findById(Long cid);

    List<CmdOption> findCmdOptions(Long cid);
}
