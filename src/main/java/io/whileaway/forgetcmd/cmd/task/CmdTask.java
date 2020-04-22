package io.whileaway.forgetcmd.cmd.task;

import io.whileaway.forgetcmd.cmd.entities.CmdOption;
import io.whileaway.forgetcmd.cmd.entities.CmdParam;
import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.request.CreateCmdRequest;
import io.whileaway.forgetcmd.cmd.response.CommandResponse;
import io.whileaway.forgetcmd.cmd.response.SearchCmdResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CmdTask {

    List<SearchCmdResponse> searchByKeyWord(String keyword);

    CommandResponse findById(Long cid);

    List<CmdOption> findCmdOptions(Long cid);

    List<CmdParam> findCmdParams(Long cid);

    Command createCmd(CreateCmdRequest request);

}
