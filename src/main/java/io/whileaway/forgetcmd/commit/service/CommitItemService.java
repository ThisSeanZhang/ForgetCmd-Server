package io.whileaway.forgetcmd.commit.service;

import io.whileaway.forgetcmd.commit.entities.CommitItem;
import io.whileaway.forgetcmd.util.BaseService;

import java.util.List;

public interface CommitItemService extends BaseService<CommitItem, Long> {
    List<CommitItem> findByCommitIds(List<Long> ccids);

    List<CommitItem> findByCommitId(Long ccid);

}
