package io.whileaway.forgetcmd.commit.response;

import io.whileaway.forgetcmd.commit.enums.CommitStatus;
import lombok.Data;

@Data
public class CmdAddLogBriefResponse {
    private Long rid;
    private String commandName;
    private String briefDesc;
    private String description;
    private String version;
    private String platform;
    private Integer argNum;
    private String whenDeprecated;
    private String whenEnable;
    private Long frequency;
    private CommitStatus status;
}
