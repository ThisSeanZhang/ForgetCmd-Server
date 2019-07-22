package io.whileaway.forgetcmd.service;

import io.whileaway.forgetcmd.entities.Command;
import io.whileaway.forgetcmd.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CmdServiceImpl implements CmdService {

    private final CommandRepository commandRepository;

    @Autowired
    public CmdServiceImpl(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public JpaRepository<Command, Long> getRepository() {
        return this.commandRepository;
    }
}
