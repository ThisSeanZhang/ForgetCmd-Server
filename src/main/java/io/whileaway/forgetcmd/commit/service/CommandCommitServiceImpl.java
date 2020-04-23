package io.whileaway.forgetcmd.commit.service;

import io.whileaway.forgetcmd.commit.enums.CommitError;
import io.whileaway.forgetcmd.commit.enums.CommitStatus;
import io.whileaway.forgetcmd.commit.repository.CommitItemRepository;
import io.whileaway.forgetcmd.commit.request.CommandCommitRequest;
import io.whileaway.forgetcmd.commit.request.ConfirmCommitRequest;
import io.whileaway.forgetcmd.commit.response.CommandListResponse;
import io.whileaway.forgetcmd.commit.specs.CommitSpec;
import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.repository.CommandCommitRepository;
import io.whileaway.forgetcmd.commit.request.CommitSearchRequest;
import io.whileaway.forgetcmd.util.ListUtils;
import io.whileaway.forgetcmd.util.StringUtils;
import io.whileaway.forgetcmd.util.auto.AutoFill;
import io.whileaway.forgetcmd.util.spec.QueryListBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommandCommitServiceImpl implements CommandCommitService {

    private final CommandCommitRepository repository;
    private final CommitItemRepository itemRepository;

    public CommandCommitServiceImpl(CommandCommitRepository repository, CommitItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }

    @Override
    public CommandCommit createCommit(CommandCommitRequest request) {
        if (StringUtils.isEmptyOrBlank(request.getCommandName())) CommitError.COMMAND_NAME_NOT_EMPTY.throwThis();
        List<CommandCommit> updatedVersionCommit = repository.updatedVersionCommit(request.getCommandName(), request.getVersion());
        if(ListUtils.notEmptyList(updatedVersionCommit))
            if (0 == request.getVersion())
                CommitError.COMMAND_EXIST.throwThis();
            else
                CommitError.OLDER_VERSION_COMMAND_NOT_ALLOW_COMMIT.throwThis();
        CommandCommit commit = repository.save(request.convertToCommandCommit());
        itemRepository.saveAll(request.itemStream()
                .peek(item -> item.setCcid(commit.getCcid()))
                .peek(item -> item.setCid(commit.getCid()))
                .peek(item -> item.setVersion(commit.getVersion()))
                .collect(Collectors.toList())
        );
        return commit;
    }

    @Override
    public BaseRepository<CommandCommit, Long> getRepository() {
        return repository;
    }

    @Override
    public List<CommandCommit> search(CommitSearchRequest request) {
        return new QueryListBuilder<CommandCommit>()
//                .appendCondition(CommitSpec.status(request::getStatus))
                .appendCondition(CommitSpec.commandName(request::getCommandName))
                .appendCondition(CommitSpec.cid(request::getCid))
                .appendCondition(CommitSpec.ccids(request::getCcids))
                .appendCondition(CommitSpec.version(request::getVersion))
                .findFrom(repository::findAll)
                .primitive();
//        return repository.findAll();
    }

    @Override
    public List<CommandListResponse> commitCommandList() {
        List<Map<String, Object>> cmds = repository.groupCommandName(CommitStatus.NEED_REVIEW);
        return cmds.stream()
                .map(AutoFill::create)
                .map(a -> a.autoFeed(CommandListResponse::new))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void confirmCommit(ConfirmCommitRequest request) {
        List<CommandCommit> needConfirmCommit =  request.isInitialCommit()
                ? repository.initialCommitByName(request.getCommandName())
                : repository.findByCommandNameAndVersion(request.getCommandName(), request.getVersion());
        if (ListUtils.isEmptyList(needConfirmCommit))
            CommitError.NO_MATCH_COMMITS.throwThis();
        List<CommandCommit> updatedCommit = needConfirmCommit.stream()
                .peek(commit -> commit.setCid(request.getCid()))
                .peek(commit -> commit.setStatus(
                        request.getUsedIdMap().containsKey(commit.getCcid())
                                ? CommitStatus.REFERENCED
                                : CommitStatus.UNREFERENCED
                ))
                .collect(Collectors.toList());
        itemRepository.updateCidByCcids(request.getCid(), request.getUsedIdMap().keySet());
        repository.saveAll(updatedCommit);
    }

    @Override
    public List<CommandCommit> findByIds(List<Long> ccids) {
        return new QueryListBuilder<CommandCommit>()
                .appendCondition(CommitSpec.status(() -> CommitStatus.NEED_REVIEW))
                .appendCondition(CommitSpec.ccids(() -> ccids))
                .findFrom(repository::findAll)
                .primitive();
    }

    @Override
    public List<CommandCommit> findAllCurrentByCid(Long cid) {
        return repository.findByCidAndStatus(cid, CommitStatus.NEED_REVIEW);
    }
}
