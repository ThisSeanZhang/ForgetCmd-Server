package io.whileaway.forgetcmd.cmd.entities;

import io.whileaway.forgetcmd.cmd.enums.OptionTypeEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CmdOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private Long cid;
    private String briefName;
    private String fullName;
    private String rules;
    @Convert(converter = OptionTypeEnum.Converter.class)
    private OptionTypeEnum type;
    private String description;
    private Long whenDeprecated;
    private Long whenEnable;
    private Long frequency;

    public void update (CmdOption inOption) {
        this.briefName = inOption.briefName;
        this.rules = inOption.rules;
        this.type = inOption.type;
        this.description = inOption.description;
        this.whenDeprecated = inOption.whenDeprecated;
        this.whenEnable = inOption.whenEnable;
    }
}
