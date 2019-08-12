package io.whileaway.forgetcmd.task;

import io.whileaway.forgetcmd.response.SearchCmdResponse;

import java.util.List;

public interface CmdTask {

    List<SearchCmdResponse> searchByKeyWord(String keyword);

}
