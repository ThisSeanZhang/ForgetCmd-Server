package io.whileaway.forgetcmd.cmd.service;

import io.whileaway.forgetcmd.cmd.entities.CmdHistory;
import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.enums.CmdError;
import io.whileaway.forgetcmd.cmd.repository.CmdHistoryRepository;
import io.whileaway.forgetcmd.cmd.repository.CommandRepository;
import io.whileaway.forgetcmd.cmd.request.CreateCmdRequest;
import io.whileaway.forgetcmd.cmd.response.SearchCmdResponse;
import io.whileaway.forgetcmd.cmd.specs.CommandSpec;
import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.util.spec.QueryListBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CmdServiceImpl implements CmdService {

    private final CommandRepository commandRepository;
    private final CmdHistoryRepository historyRepository;

    public CmdServiceImpl(CommandRepository commandRepository, CmdHistoryRepository historyRepository) {
        this.commandRepository = commandRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    public BaseRepository<Command, Long> getRepository() {
        return this.commandRepository;
    }

    @Override
    public List<SearchCmdResponse> searchByKeyWord(String keyword) {
        return new QueryListBuilder<Command>()
                .appendCondition(CommandSpec::normal)
                .appendCondition(CommandSpec.fuzzyMatch(keyword))
                .sortBy(CommandSpec::moreFrequency)
                .findFrom(commandRepository::findAll)
                .stream()
                .map(SearchCmdResponse::convertFrom)
                .collect(Collectors.toList());
//        return new QueryListBuilder<Command>()
//                .appendCondition(CommandSpec::testJoin1)
//                .findFrom(commandRepository::findAll)
//                .stream()
//                .map(SearchCmdResponse::convertFrom)
//                .collect(Collectors.toList());

    }

    private CmdHistory saveHistory(CmdHistory history) {
        if (Objects.isNull(history)) CmdError.CREATE_CMD_HISTORY_ERROR.throwThis();
        return historyRepository.save(history);
    }

    @Override
    @Transactional
    public Command save(CreateCmdRequest request) {
        // TODO 如果开放大众添加的话 最终cmd的生成需要在服务端完成，不然会导致伪造修改 导致实际修改出现偏差
        Command updatedCmd = request.ifUpdateKeepSomeThing(this::findByIdOrName);
        if(Objects.isNull(request.getCmd().getCid())) {
            updatedCmd = commandRepository.save(updatedCmd);
        }
        CmdHistory history = CmdHistory.createFromCmdRequest(request);
        history.setCid(updatedCmd.getCid());
        // 添加历史命令
        history = saveHistory(history);
        updatedCmd.setChid(history.getChid());
        return save(updatedCmd);
    }

    @Override
    public Optional<Command> findByIdOrName(Long cid, String commandName) {
        return commandRepository.findByCidOrCommandName(cid, commandName);
    }
}
