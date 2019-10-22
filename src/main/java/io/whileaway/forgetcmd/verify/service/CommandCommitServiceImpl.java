package io.whileaway.forgetcmd.verify.service;

import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.verify.entities.CommandCommit;
import io.whileaway.forgetcmd.verify.repository.CommandCommitRepository;
import io.whileaway.forgetcmd.verify.request.AddLogSearchRequest;
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
