package io.whileaway.forgetcmd.cmd.request;

import io.whileaway.forgetcmd.cmd.entities.CmdOption;
import io.whileaway.forgetcmd.cmd.entities.CmdParam;
import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.enums.CommandStatus;
import io.whileaway.forgetcmd.commit.request.ConfirmCommitRequest;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

@Data
public class CreateCmdRequest {

    private String ccids;
    private Command cmd;
    private List<Integer> ciids;
    private Command command;
    private List<CmdParam> params;
    private List<CmdOption> options;
    private Map<Long, List<Long>> usedIdMap;


//    public List<CmdParam> getParams() {
//        ObjectMapper om = new ObjectMapper();
//        JavaType javaType = om.getTypeFactory().constructParametricType(ArrayList.class, CmdParam.class);
//        try {
//            return om.readValue(params, javaType);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
//
//    public List<CmdOption> getOptions() {
//        ObjectMapper om = new ObjectMapper();
//        JavaType javaType = om.getTypeFactory().constructParametricType(ArrayList.class, CmdOption.class);
//        try {
//            return om.readValue(options, javaType);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }

    public ConfirmCommitRequest toConfirmCommitRequest() {
        return ConfirmCommitRequest.create(
                this::getCcids, this::getCiids,this::getUsedIdMap, this::getCmd
        );
    }

    public Command ifUpdateKeepSomeThing(BiFunction<Long, String, Optional<Command>> searchCmd) {
        searchCmd.apply(cmd.getCid(), cmd.getCommandName())
                .ifPresentOrElse(
                        this.cmd::remainFromDataBase,
                        this.cmd::init
                );
//        if (Objects.nonNull(io.whileaway.forgetcmd.cmd.getCid())) {
//            searchCmd.apply(io.whileaway.forgetcmd.cmd.getCid()).ifPresent(this.io.whileaway.forgetcmd.cmd::remainFromDataBase);
//        } else {
//            this.io.whileaway.forgetcmd.cmd.setStatus(CommandStatus.NORMAL);
//        }
        return this.cmd;
    }

//    private void remainSomeInCmd(Command io.whileaway.forgetcmd.cmd) {
//        this.io.whileaway.forgetcmd.cmd.remainFromDataBase(io.whileaway.forgetcmd.cmd);
//    }

}
