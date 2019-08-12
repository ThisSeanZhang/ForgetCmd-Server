package io.whileaway.forgetcmd.response;

import lombok.Data;

@Data
public class SearchCmdResponse {
    private Long cid;
    private String commandName;
    private String briefDesc;
}
