package io.whileaway.forgetcmd.service;

import io.whileaway.forgetcmd.entities.Command;
import io.whileaway.forgetcmd.repository.CommandRepository;
import io.whileaway.forgetcmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.specs.CommandSpec;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CmdServiceImpl implements CmdService {

    private final CommandRepository commandRepository;

    @Autowired
    public CmdServiceImpl(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public BaseRepository<Command, Long> getRepository() {
        return this.commandRepository;
    }

    @Override
    public List<SearchCmdResponse> searchByKeyWord(String keyword) {
        Optional<List<Command>> all = Optional.ofNullable(commandRepository.findAll(CommandSpec.fuzzyMatch(keyword)));
        return all.orElseGet(ArrayList::new).stream()
                .map(cmd-> {
                    SearchCmdResponse value = new SearchCmdResponse();
                    value.setCid(cmd.getCid());
                    value.setCommandName(cmd.getCommandName());
                    value.setBriefDesc(cmd.getBriefDesc());
                    return value;
                }).collect(Collectors.toList());
    }
}
