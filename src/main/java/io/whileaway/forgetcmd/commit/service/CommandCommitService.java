package io.whileaway.forgetcmd.commit.service;

import io.whileaway.forgetcmd.commit.request.CommandCommitRequest;
import io.whileaway.forgetcmd.commit.request.ConfirmCommitRequest;
import io.whileaway.forgetcmd.commit.response.CommandListResponse;
import io.whileaway.forgetcmd.util.BaseService;
import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.request.CommitSearchRequest;

import java.util.List;

public interface CommandCommitService extends BaseService<CommandCommit, Long> {

    CommandCommit createCommit(CommandCommitRequest request);

    List<CommandCommit> search(CommitSearchRequest request);

    List<CommandListResponse> commitCommandList();

    void confirmCommit(ConfirmCommitRequest confirmCommitRequest);

    List<CommandCommit> findByIds(List<Long> ccids);

    List<CommandCommit> findAllCurrentByCid(Long cid);

    List<CommandCommit> getUserAllCommits(Long did);
}
