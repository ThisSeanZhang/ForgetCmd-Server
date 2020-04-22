package io.whileaway.forgetcmd.cmd.service;

import io.whileaway.forgetcmd.cmd.entities.CmdParam;
import io.whileaway.forgetcmd.cmd.repository.CmdParamRepository;
import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.util.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
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
    public Optional<List<CmdParam>> findByCid(Long cid) {
        return repository.findByCid(cid);
    }

    @Override
    @Transactional
    public void updateCommandParams(Long cid, List<CmdParam> params) {
        if(ListUtils.isEmptyList(params)) return;
        Map<Integer, CmdParam> dataParams = findByCid(cid).stream().flatMap(Collection::stream)
                .collect(Collectors.toMap(CmdParam::getIndex, Function.identity()));

        List<CmdParam> needSave = params.stream()
                .filter(Objects::nonNull)
                .filter(p -> Objects.nonNull(p.getIndex()))
                .peek(param -> param.setCid(cid))
                .peek(param -> {
                    if (dataParams.containsKey(param.getIndex())) {
                        param.remainFromDataBase(dataParams.get(param.getIndex()));
                        dataParams.remove(param.getIndex());
                    }
                }).collect(Collectors.toList());

        // 删除没有的
        repository.deleteAll(dataParams.values());
        // 更新 以及 添加没有的
        repository.saveAll(needSave);
    }
}
