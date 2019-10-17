package io.whileaway.forgetcmd.cmd.entities;

import io.whileaway.forgetcmd.cmd.enums.CommandStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;
    private String commandName;
    private String briefDesc;
    private String description;
    private String version;
    private String platform;
    private Integer argNum;
    private String whenDeprecated;
    private String whenEnable;
    private Long frequency;
    @Convert(converter = CommandStatus.Converter.class)
    private CommandStatus status;
    private String whoCreated;
    private Long creatorId;
}
