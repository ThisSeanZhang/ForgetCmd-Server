package io.whileaway.forgetcmd.cmd.service;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.repository.CommandRepository;
import io.whileaway.forgetcmd.cmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.cmd.specs.CommandSpec;
import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.util.spec.QueryListBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CmdServiceImpl implements CmdService {

    private final CommandRepository commandRepository;

    public CmdServiceImpl(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public BaseRepository<Command, Long> getRepository() {
        return this.commandRepository;
    }

    @Override
    public List<SearchCmdResponse> searchByKeyWord(String keyword) {
//        return new QueryListBuilder<Command>()
//                .appendCondition(CommandSpec::normal)
//                .appendCondition(CommandSpec.fuzzyMatch(keyword))
//                .sortBy(CommandSpec::moreFrequency)
//                .findFrom(commandRepository::findAll)
//                .stream()
//                .map(SearchCmdResponse::convertFrom)
//                .collect(Collectors.toList());
        return new QueryListBuilder<Command>()
                .appendCondition(CommandSpec::testJoin1)
                .findFrom(commandRepository::findAll)
                .stream()
                .map(SearchCmdResponse::convertFrom)
                .collect(Collectors.toList());

    }
}
