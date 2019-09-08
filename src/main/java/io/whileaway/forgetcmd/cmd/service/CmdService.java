package io.whileaway.forgetcmd.cmd.service;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.util.BaseService;

import java.util.List;

public interface CmdService extends BaseService<Command, Long> {

    List<SearchCmdResponse> searchByKeyWord(String keyword);
}
