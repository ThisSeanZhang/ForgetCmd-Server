package io.whileaway.forgetcmd.cmd.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.whileaway.forgetcmd.cmd.entities.CmdOption;
import io.whileaway.forgetcmd.cmd.entities.CmdParam;
import io.whileaway.forgetcmd.cmd.entities.Command;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class CommandResponse extends Command {
    private List<CmdOption> options;
    private List<CmdParam> params;

    public static Optional<CommandResponse> createFromCmd(Command cmd) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Optional.of(objectMapper.readValue(objectMapper.writeValueAsString(cmd), CommandResponse.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
