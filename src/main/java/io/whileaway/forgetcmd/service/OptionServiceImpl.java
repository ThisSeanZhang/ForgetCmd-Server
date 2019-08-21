package io.whileaway.forgetcmd.service;

import io.whileaway.forgetcmd.entities.CmdOption;
import io.whileaway.forgetcmd.enums.CmdError;
import io.whileaway.forgetcmd.repository.CmdOptionRepository;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OptionServiceImpl implements OptionService {

    private final CmdOptionRepository repository;

    public OptionServiceImpl(CmdOptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<List<CmdOption>> findByCid(Long cid) {
        if (Objects.isNull(cid)) CmdError.CID_NOT_NULL.throwThis();
        return repository.findByCid(cid);
    }

    @Override
    public BaseRepository<CmdOption, Long> getRepository() {
        return repository;
    }
}
