package io.whileaway.forgetcmd.verify.entities;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.request.CreateCmdRequest;
import io.whileaway.forgetcmd.verify.enums.AddLogStatus;
import io.whileaway.forgetcmd.verify.response.CmdAddLogBriefResponse;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CmdAddLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;
    private String commandName;
    private String briefDesc;
    private String description;
    private String version;
    private String platform;
    private Integer argNum;
    private String whenDeprecated;
    private String whenEnable;
    private Long cid;
    private AddLogStatus status;
    private String whoCreated;
    private Long creatorId;

    @Column(columnDefinition="text")
    private String cmdOptions;
    @Column(columnDefinition="text")
    private String cmdParams;

    public CmdAddLogBriefResponse convertBrief() {
        CmdAddLogBriefResponse response = new CmdAddLogBriefResponse();
        response.setRid(rid);
        response.setCommandName(commandName);
        response.setBriefDesc(briefDesc);
        response.setDescription(description);
        response.setVersion(version);
        response.setPlatform(platform);
        response.setArgNum(argNum);
        response.setWhenDeprecated(description);
        response.setWhenEnable(whenEnable);
        response.setStatus(status);
        return response;
    }

    public CreateCmdRequest createCommandRequest() {
        Command cmd = new Command();
        cmd.setCommandName(commandName);
        cmd.setBriefDesc(briefDesc);
        cmd.setDescription(description);
        cmd.setVersion(version);
        cmd.setPlatform(platform);
        cmd.setArgNum(argNum);
        cmd.setWhenDeprecated(whenDeprecated);
        cmd.setWhenEnable(whenEnable);
        cmd.setCreatorId(creatorId);
        cmd.setWhoCreated(whoCreated);
        CreateCmdRequest request = new CreateCmdRequest();
        request.setCommand(cmd);
        request.setOptions(cmdOptions);
        request.setParams(cmdParams);
        return request;
    }
}
