package io.whileaway.forgetcmd.commit.response;

import lombok.Data;

@Data
public class CommandListResponse {
    private String ccids;
    private String commandName;
    private Long cid;
    private Long createTime;
    private Long version;
}
