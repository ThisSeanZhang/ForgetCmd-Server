package io.whileaway.forgetcmd.cmd.request;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.whileaway.forgetcmd.cmd.entities.CmdOption;
import io.whileaway.forgetcmd.cmd.entities.CmdParam;
import io.whileaway.forgetcmd.cmd.entities.Command;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCmdRequest {

    private Command command;
    private String params;
    private String options;


    public List<CmdParam> getParams() {
        ObjectMapper om = new ObjectMapper();
        JavaType javaType = om.getTypeFactory().constructParametricType(ArrayList.class, CmdParam.class);
        try {
            return om.readValue(params, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<CmdOption> getOptions() {
        ObjectMapper om = new ObjectMapper();
        JavaType javaType = om.getTypeFactory().constructParametricType(ArrayList.class, CmdOption.class);
        try {
            return om.readValue(options, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
