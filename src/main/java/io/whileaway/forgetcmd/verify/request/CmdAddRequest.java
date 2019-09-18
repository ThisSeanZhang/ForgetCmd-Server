package io.whileaway.forgetcmd.verify.request;

import io.whileaway.forgetcmd.verify.entities.CmdAddLog;
import io.whileaway.forgetcmd.verify.enums.AddLogStatus;
import lombok.Data;

@Data
public class CmdAddRequest {

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

    public CmdAddLog convertToCmdAddLog() {
//        ObjectMapper mapper = new ObjectMapper();
        CmdAddLog log = new CmdAddLog();
        log.setCommandName(commandName);
        log.setBriefDesc(briefDesc);
        log.setDescription(description);
        log.setVersion(version);
        log.setPlatform(platform);
        log.setArgNum(argNum);
        log.setWhenDeprecated(whenDeprecated);
        log.setWhenEnable(whenEnable);

        log.setStatus(AddLogStatus.NEED_REVIEW);

        log.setCmdOptions(options);
        log.setCmdParams(params);
//        try {
//            log.setCmdOptions(mapper.writeValueAsString(options));
//            log.setCmdParams(mapper.writeValueAsString(params));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            CommonErrorEnum.SERVER_ERROR.throwThis();
//        }
        return log;
    }
}
