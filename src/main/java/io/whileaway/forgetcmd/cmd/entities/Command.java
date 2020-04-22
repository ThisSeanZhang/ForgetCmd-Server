package io.whileaway.forgetcmd.cmd.entities;

import io.whileaway.forgetcmd.cmd.enums.CmdError;
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
    // version of update

    private Long version = 0L;
    private String platform;
    private Integer argNum;
    private String whenDeprecated;
    private String whenEnable;
    private Long frequency;
    @Convert(converter = CommandStatus.Converter.class)
    private CommandStatus status;
    private String whoCreated;
    private Long creatorId;
    private Long ccid;
    private Long chid;


    public void updateSomeThingFromOther(Command cmd) {
        this.commandName = cmd.commandName;
        this.briefDesc = cmd.briefDesc;
        this.description = cmd.description;
        this.version = cmd.getVersion() + 1;
    }

    public void remainFromDataBase(Command dataBaseCmd) {
        if(this.version + 1 <= dataBaseCmd.version) {
            CmdError.SUBMITTED_VERSION_IS_OUT_OF_DATE.throwThis();
        }
        this.cid = dataBaseCmd.cid;
        this.frequency = dataBaseCmd.frequency;
        this.version = dataBaseCmd.version + 1;
        this.status = dataBaseCmd.status;
        this.whoCreated = this.whoCreated + " , " + dataBaseCmd.whoCreated;
        // TODO 填充剩下需要保持 CommandCommit ID
    }

    public void init() {
        this.setStatus(CommandStatus.NORMAL);
        this.version = 1L;
    }
}
