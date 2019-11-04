package io.whileaway.forgetcmd.cmd.service;

import io.whileaway.forgetcmd.cmd.entities.CmdParam;
import io.whileaway.forgetcmd.cmd.repository.CmdParamRepository;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    @Transactional
    public void updateCommandParams(Long cid, List<CmdParam> params) {
        Map<String, CmdParam> dataBaseParams = findBydCid(cid).orElse(new ArrayList<>()).stream().collect(Collectors.toMap(CmdParam::getParamName, e -> e));
        Map<String, CmdParam> inParams = params.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(CmdParam::getParamName, e -> e));
        List<CmdParam> updatedParams = dataBaseParams.keySet().stream()
                .filter(inParams::containsKey)
                .map(dataBaseParams::get)
                .filter(Objects::nonNull)
                .peek(param -> param.update(inParams.get(param.getParamName())))
                .collect(Collectors.toList());
        repository.saveAll(updatedParams);
        repository.saveAll(params);
    }
}
