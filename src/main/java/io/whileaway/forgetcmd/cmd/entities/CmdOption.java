package io.whileaway.forgetcmd.cmd.entities;

import io.whileaway.forgetcmd.cmd.enums.OptionTypeEnum;
import io.whileaway.forgetcmd.util.StringUtils;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

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
    private boolean duplicate;

    public void update (CmdOption inOption) {
        this.briefName = inOption.briefName;
        this.rules = inOption.rules;
        this.type = inOption.type;
        this.description = inOption.description;
        this.whenDeprecated = inOption.whenDeprecated;
        this.whenEnable = inOption.whenEnable;
    }

//
//    public static void main(String[] args) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Object o = objectMapper.readValue("true", Object.class);
//        String out = objectMapper.writeValueAsString(o);
//        System.out.println(o);
//        System.out.println(out);
//    }

    public String getFullName() {
        return StringUtils.nonEmptyOrBlank(fullName) ? fullName : briefName;
    }

    public void remainFromDataBase(CmdOption cmdOption) {
        if (Objects.isNull(cmdOption)) return;
        this.frequency = cmdOption.frequency;
    }
}
