package io.whileaway.forgetcmd.cmd.entities;

import io.whileaway.forgetcmd.cmd.enums.ParamTypeEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CmdParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cpid;
    private Long cid;
    private Long frequency;
    private String paramName;
    private String description;
    @Convert(converter = ParamTypeEnum.Converter.class)
    private ParamTypeEnum type;
    private boolean required;


    public void update(CmdParam inParam) {
        this.description = inParam.getDescription();
        this.type = inParam.getType();
    }
}
