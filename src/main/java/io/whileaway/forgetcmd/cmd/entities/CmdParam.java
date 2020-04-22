package io.whileaway.forgetcmd.cmd.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.whileaway.forgetcmd.util.entity.MapConvert;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@Data
@Entity
public class CmdParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cpid;
    private Long cid;
    @Column(name = "site")
    private Integer index;
    @Column( columnDefinition = "json" )
//    @Convert(converter = MapConvert.class)
    private String description;
//    @Convert(converter = ParamTypeEnum.Converter.class)
//    private ParamTypeEnum type;
//    private boolean required;

    public void update(CmdParam inParam) {
        this.description = inParam.getDescription();
//        this.type = inParam.getType();
    }

    public void remainFromDataBase(CmdParam dataBaseParam) {
        if (Objects.isNull(dataBaseParam)) return;
        this.cpid = dataBaseParam.cpid;
        this.cid = dataBaseParam.cid;
        this.index = dataBaseParam.index;
    }
}
