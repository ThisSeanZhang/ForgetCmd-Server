package io.whileaway.forgetcmd.service;

import io.whileaway.forgetcmd.entities.Command;
import io.whileaway.forgetcmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.util.BaseService;

import java.util.List;

public interface CmdService extends BaseService<Command, Long> {

    List<SearchCmdResponse> searchByKeyWord(String keyword);
}
