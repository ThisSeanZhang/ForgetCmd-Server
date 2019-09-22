package io.whileaway.forgetcmd.verify.service;

import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.verify.entities.CmdAddLog;
import io.whileaway.forgetcmd.verify.repository.CmdAddLogRepository;
import io.whileaway.forgetcmd.verify.request.AddLogSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmdAddLogServiceImpl implements CmdAddLogService {

    private final CmdAddLogRepository repository;

    public CmdAddLogServiceImpl(CmdAddLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepository<CmdAddLog, Long> getRepository() {
        return repository;
    }

    @Override
    public List<CmdAddLog> search(AddLogSearchRequest request) {
        return repository.findAll();
    }
}
