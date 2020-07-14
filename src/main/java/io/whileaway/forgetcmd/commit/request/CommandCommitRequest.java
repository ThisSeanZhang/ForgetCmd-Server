package io.whileaway.forgetcmd.commit.request;

import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.entities.CommitItem;
import io.whileaway.forgetcmd.commit.enums.CommitStatus;
import io.whileaway.forgetcmd.util.StringUtils;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Data
public class CommandCommitRequest {

    private Long cid;
    @NotNull(message = "需要传入命令名称")
    @NotBlank(message = "命令名称不能为空")
    private String commandName;
    private String briefDesc;
    private String description;
    private Long version;
    private String platform;
    private Integer argNum;
    private String whenDeprecated;
    private String whenEnable;

//    private List<CmdOption> options;
//    private List<CmdParam> params;
    private String options;
    private String params;
    private List<CommitItem> items;
    private Long did;

    public CommandCommit convertToCommandCommit() {
//        ObjectMapper mapper = new ObjectMapper();
        CommandCommit commit = new CommandCommit();
        commit.setCid(cid);
        commit.setCommandName(getCommandName());
        commit.setBriefDesc(briefDesc);
        commit.setDescription(description);
        commit.setVersion(Objects.isNull(version) ? 0L : version);
        commit.setPlatform(platform);
        commit.setArgNum(argNum);
        commit.setWhenDeprecated(whenDeprecated);
        commit.setWhenEnable(whenEnable);

        commit.setStatus(CommitStatus.NEED_REVIEW);

        commit.setOptions(options);
        commit.setParams(params);
        commit.setDid(did);
//        try {
//            commit.setCmdOptions(mapper.writeValueAsString(options));
//            commit.setCmdParams(mapper.writeValueAsString(params));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            CommonErrorEnum.SERVER_ERROR.throwThis();
//        }
        return commit;
    }

    public Stream<CommitItem> itemStream() {
        return Objects.isNull(items) ? Stream.empty() : items.stream().filter(Objects::nonNull);
    }

    public String getCommandName() {
        return StringUtils.removeExtraSeparator(commandName, " ");
    }
}
