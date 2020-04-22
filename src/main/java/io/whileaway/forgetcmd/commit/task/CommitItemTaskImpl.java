package io.whileaway.forgetcmd.commit.task;

import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.entities.CommitItem;
import io.whileaway.forgetcmd.commit.enums.CommitError;
import io.whileaway.forgetcmd.commit.request.ItemSearchRequest;
import io.whileaway.forgetcmd.commit.service.CommandCommitService;
import io.whileaway.forgetcmd.commit.service.CommitItemService;
import io.whileaway.forgetcmd.util.ListUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommitItemTaskImpl implements CommitItemTask {

    final private CommitItemService service;
    final private CommandCommitService commitService;

    public CommitItemTaskImpl(CommitItemService service, CommandCommitService commitService) {
        this.service = service;
        this.commitService = commitService;
    }

    @Override
    public List<CommitItem> searchItems(ItemSearchRequest request) {
        List<CommandCommit> commits = commitService.search(request.convertCommitSearch());
        if (ListUtils.isEmptyList(commits)) CommitError.NOT_FOUND.throwThis();
        List<Long> ccids = commits.stream()
                .map(CommandCommit::getCcid)
                .collect(Collectors.toList());
        return service.findByCommitIds(ccids);
    }

//    @Override
//    public List<CommitItem> getCommandCurrentVersion(Long cid) {
//        List<CommandCommit> commits = commitService.findAllCurrentByCid(cid);
//        return null;
//    }
}
