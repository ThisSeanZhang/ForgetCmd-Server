package io.whileaway.forgetcmd.verify.response;

import io.whileaway.forgetcmd.verify.enums.AddLogStatus;
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
    private AddLogStatus status;
}
