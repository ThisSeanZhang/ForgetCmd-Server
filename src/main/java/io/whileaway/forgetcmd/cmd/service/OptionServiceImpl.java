package io.whileaway.forgetcmd.cmd.service;

import io.whileaway.forgetcmd.cmd.entities.CmdOption;
import io.whileaway.forgetcmd.cmd.enums.CmdError;
import io.whileaway.forgetcmd.cmd.repository.CmdOptionRepository;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    public void updateCommandOptions(Long cid, List<CmdOption> options) {
//        List<Long> existOptionId = options.stream()
//                .filter(Objects::nonNull)
//                .map(CmdOption::getOid)
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//        List<CmdOption> existOption = repository.findAllById(existOptionId);
//        Map<Long, CmdOption> inExistOptions = options.stream()
//                .filter(Objects::nonNull)
//                .filter(op -> Objects.nonNull(op.getOid()))
//                .collect(Collectors.toMap(CmdOption::getOid, e -> e));
//        existOption.stream()
//                .filter(Objects::nonNull)
//                .peek(option -> option.update(inExistOptions.get(option.getOid())))
//        Map<Boolean, List<CmdOption>> newOldGroup = options.stream()
//                .filter(Objects::nonNull)
//                .collect(Collectors.groupingBy(op -> Objects.nonNull(op.getOid())));

        Map<String, CmdOption> dataBaseParams = findByCid(cid)
                .orElse(new ArrayList<>()).stream().collect(Collectors.toMap(CmdOption::getFullName, e -> e));
        Map<String, CmdOption> inParams = options.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(CmdOption::getFullName, e -> e));
        List<CmdOption> updatedParams = dataBaseParams.keySet().stream()
                .filter(inParams::containsKey)
                .map(dataBaseParams::get)
                .filter(Objects::nonNull)
                .peek(option -> option.update(inParams.get(option.getFullName())))
                .collect(Collectors.toList());
        List<CmdOption> deleteParams = dataBaseParams.keySet().stream()
                .filter( p -> !inParams.containsKey(p))
                .map(dataBaseParams::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        // 删除没有的
        repository.deleteAll(deleteParams);
        repository.saveAll(updatedParams);
        repository.saveAll(options);
    }

    @Override
    public BaseRepository<CmdOption, Long> getRepository() {
        return repository;
    }
}
