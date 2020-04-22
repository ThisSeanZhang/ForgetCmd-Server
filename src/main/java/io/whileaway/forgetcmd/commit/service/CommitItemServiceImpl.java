package io.whileaway.forgetcmd.commit.service;

import io.whileaway.forgetcmd.commit.entities.CommitItem;
import io.whileaway.forgetcmd.commit.repository.CommitItemRepository;
import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.util.spec.QueryListBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitItemServiceImpl implements CommitItemService {

    private final CommitItemRepository repository;

    public CommitItemServiceImpl(CommitItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepository<CommitItem, Long> getRepository() {
        return this.repository;
    }

    @Override
    public List<CommitItem> findByCommitIds(List<Long> ccids) {
        return repository.findByCcidIn(ccids);
    }
}
