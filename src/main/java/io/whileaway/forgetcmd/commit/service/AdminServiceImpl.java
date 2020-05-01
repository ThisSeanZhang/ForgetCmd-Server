package io.whileaway.forgetcmd.commit.service;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.repository.CommandRepository;
import io.whileaway.forgetcmd.cmd.request.SearchCmdRequest;
import io.whileaway.forgetcmd.util.spec.QueryListBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
