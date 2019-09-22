package io.whileaway.forgetcmd.verify.entities;

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
    private Long frequency;
    private AddLogStatus status;

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
        response.setFrequency(frequency);
        response.setStatus(status);
        return response;
    }
}
