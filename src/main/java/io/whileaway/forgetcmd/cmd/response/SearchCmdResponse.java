package io.whileaway.forgetcmd.cmd.response;

import lombok.Data;

@Data
public class SearchCmdResponse {
    private Long cid;
    private String commandName;
    private String briefDesc;
}
