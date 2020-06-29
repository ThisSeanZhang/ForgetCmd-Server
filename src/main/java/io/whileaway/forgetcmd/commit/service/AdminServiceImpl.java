package io.whileaway.forgetcmd.commit.service;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.enums.CmdError;
import io.whileaway.forgetcmd.cmd.enums.CommandStatus;
import io.whileaway.forgetcmd.cmd.repository.CommandRepository;
import io.whileaway.forgetcmd.cmd.request.SearchCmdRequest;
import io.whileaway.forgetcmd.cmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.util.spec.QueryListBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final CommandRepository commandRepository;

    public AdminServiceImpl(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }


    @Override
    public List<Command> search(SearchCmdRequest request) {
        return new QueryListBuilder<Command>()
                .findFrom(commandRepository::findAll)
                .primitive();
    }

    @Override
    public Command pauseCommand(Long cid) {
        Optional<Command> command = commandRepository.findById(cid);
        command.ifPresentOrElse(cmd ->{
            cmd.setStatus(CommandStatus.PAUSE);
            commandRepository.save(cmd);
        }, CmdError.NOT_FOUND::throwThis);
        return command.get();
    }
}
