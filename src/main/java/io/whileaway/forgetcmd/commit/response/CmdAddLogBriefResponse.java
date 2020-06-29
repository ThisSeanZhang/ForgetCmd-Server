package io.whileaway.forgetcmd.commit.response;

import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.enums.CommitStatus;
import lombok.Data;

@Data
public class CmdAddLogBriefResponse {
    private Long ccid;
    private String commandName;
    private String briefDesc;
    private String description;
    private Long version;
    private String platform;
    private Integer argNum;
    private String whenDeprecated;
    private String whenEnable;
    private Long frequency;
    private CommitStatus status;

    public CmdAddLogBriefResponse(CommandCommit commit) {
        this.ccid = commit.getCcid();
        this.commandName = commit.getCommandName();
        this.briefDesc = commit.getBriefDesc();
        this.description = commit.getDescription();
        this.version = commit.getVersion();
        this.platform = commit.getPlatform();
        this.argNum = commit.getArgNum();
        this.description = commit.getDescription();
        this.whenEnable = commit.getWhenEnable();
        this.status = commit.getStatus();
    }
}
