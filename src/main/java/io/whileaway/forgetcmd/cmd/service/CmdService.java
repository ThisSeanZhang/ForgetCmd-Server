package io.whileaway.forgetcmd.cmd.service;

import io.whileaway.forgetcmd.cmd.entities.CmdHistory;
import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.request.CreateCmdRequest;
import io.whileaway.forgetcmd.cmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.util.BaseService;

import java.util.List;
import java.util.Optional;

public interface CmdService extends BaseService<Command, Long> {

    List<SearchCmdResponse> searchByKeyWord(String keyword);

    Command save(CreateCmdRequest request);

    Optional<Command> findByIdOrName(Long cid, String commandName);
}
