package io.whileaway.forgetcmd.service;

import io.whileaway.forgetcmd.entities.CmdParam;
import io.whileaway.forgetcmd.repository.CmdParamRepository;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CmdParamServiceImpl implements CmdParamService {

    private final CmdParamRepository repository;

    public CmdParamServiceImpl(CmdParamRepository repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepository<CmdParam, Long> getRepository() {
        return repository;
    }

    @Override
    public Optional<List<CmdParam>> findBydCid(Long cid) {
        return repository.findByCid(cid);
    }
}
