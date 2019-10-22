package io.whileaway.forgetcmd.commit.request;

import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.enums.CommitStatus;
import lombok.Data;

@Data
public class CommandCommitRequest {

    private String commandName;
    private String briefDesc;
    private String description;
    private String version;
    private String platform;
    private Integer argNum;
    private String whenDeprecated;
    private String whenEnable;

//    private List<CmdOption> options;
//    private List<CmdParam> params;
    private String options;
    private String params;

    public CommandCommit convertToCommandCommit() {
//        ObjectMapper mapper = new ObjectMapper();
        CommandCommit commit = new CommandCommit();
        commit.setCommandName(commandName);
        commit.setBriefDesc(briefDesc);
        commit.setDescription(description);
        commit.setVersion(version);
        commit.setPlatform(platform);
        commit.setArgNum(argNum);
        commit.setWhenDeprecated(whenDeprecated);
        commit.setWhenEnable(whenEnable);

        commit.setStatus(CommitStatus.NEED_REVIEW);

        commit.setCmdOptions(options);
        commit.setCmdParams(params);
//        try {
//            commit.setCmdOptions(mapper.writeValueAsString(options));
//            commit.setCmdParams(mapper.writeValueAsString(params));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            CommonErrorEnum.SERVER_ERROR.throwThis();
//        }
        return commit;
    }
}
