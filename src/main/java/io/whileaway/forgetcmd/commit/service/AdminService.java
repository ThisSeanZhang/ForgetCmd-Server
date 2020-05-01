package io.whileaway.forgetcmd.commit.service;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.request.SearchCmdRequest;

import java.util.List;

public interface AdminService {

    List<Command> search(SearchCmdRequest request);
}
