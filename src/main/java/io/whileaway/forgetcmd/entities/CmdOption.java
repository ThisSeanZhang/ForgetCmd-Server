package io.whileaway.forgetcmd.entities;

import io.whileaway.forgetcmd.enums.OptionTypeEnum;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

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
}
