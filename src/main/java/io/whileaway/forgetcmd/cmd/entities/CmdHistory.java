package io.whileaway.forgetcmd.cmd.entities;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.whileaway.forgetcmd.cmd.enums.CmdError;
import io.whileaway.forgetcmd.cmd.enums.OptionTypeEnum;
import io.whileaway.forgetcmd.cmd.request.CreateCmdRequest;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.IOException;

@Data
@Entity
public class CmdHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chid;
    private Long oid;
    private Long cid;
    private String commandName;
    private String briefName;
    private String fullName;
    private String rules;
    @Convert(converter = OptionTypeEnum.Converter.class)
    private OptionTypeEnum type;
    private String description;
    private Long whenDeprecated;
    private Long whenEnable;
    private Long frequency;
    @Column(columnDefinition="text")
    private String options;
    @Column(columnDefinition="text")
    private String params;

        public static CmdHistory createFromCmdRequest(CreateCmdRequest request) {
        CmdHistory history = new CmdHistory();
        try {
            ObjectMapper mapper = new ObjectMapper();
            BeanUtils.copyProperties(request.getCmd(), history);
            history.chid = null; // 需要清除id，不然会更新掉旧的
            history.options =  mapper.writeValueAsString(request.getOptions());
            history.params = mapper.writeValueAsString(request.getParams());
        } catch (IOException e) {
            e.printStackTrace();
            CmdError.CREATE_CMD_HISTORY_ERROR.throwThis();
        }
        return history;
    }
}
