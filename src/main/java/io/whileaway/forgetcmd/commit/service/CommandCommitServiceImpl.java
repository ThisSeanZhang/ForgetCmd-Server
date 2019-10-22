package io.whileaway.forgetcmd.commit.service;

import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.repository.CommandCommitRepository;
import io.whileaway.forgetcmd.commit.request.AddLogSearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandCommitServiceImpl implements CommandCommitService {

    private final CommandCommitRepository repository;

    public CommandCommitServiceImpl(CommandCommitRepository repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepository<CommandCommit, Long> getRepository() {
        return repository;
    }

    @Override
    public List<CommandCommit> search(AddLogSearchRequest request) {
        return repository.findAll();
    }
}
