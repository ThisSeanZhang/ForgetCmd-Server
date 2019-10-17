package io.whileaway.forgetcmd.cmd.response;

import io.whileaway.forgetcmd.cmd.entities.Command;
import lombok.Data;

@Data
public class SearchCmdResponse {
    private Long cid;
    private String commandName;
    private String briefDesc;

    public static SearchCmdResponse convertFrom(Command command) {
        SearchCmdResponse value = new SearchCmdResponse();
        value.setCid(command.getCid());
        value.setCommandName(command.getCommandName());
        value.setBriefDesc(command.getBriefDesc());
        return value;
    }
}
