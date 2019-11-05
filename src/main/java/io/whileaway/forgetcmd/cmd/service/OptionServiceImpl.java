package io.whileaway.forgetcmd.cmd.service;

import io.whileaway.forgetcmd.cmd.entities.CmdOption;
import io.whileaway.forgetcmd.cmd.enums.CmdError;
import io.whileaway.forgetcmd.cmd.repository.CmdOptionRepository;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void updateCommandOptions(Long cid, List<CmdOption> options) {
        Map<String, CmdOption> dataBaseOptions = findByCid(cid)
                .orElse(new ArrayList<>()).stream().collect(Collectors.toMap(CmdOption::getFullName, e -> e));
        Map<String, CmdOption> inOptions = options.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(CmdOption::getFullName, e -> e));
        List<CmdOption> updatedOptions = dataBaseOptions.keySet().stream()
                .filter(inOptions::containsKey)
                .map(dataBaseOptions::get)
                .filter(Objects::nonNull)
                .peek(option -> option.update(inOptions.get(option.getFullName())))
                .collect(Collectors.toList());
        List<CmdOption> deleteOptions = dataBaseOptions.keySet().stream()
                .filter( p -> !inOptions.containsKey(p))
                .map(dataBaseOptions::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        // 删除没有的
        repository.deleteAll(deleteOptions);
        // 更新已有的
        repository.saveAll(updatedOptions);
        // 新增没有的
        repository.saveAll(options.stream()
                .filter( p -> !dataBaseOptions.containsKey(p.getFullName()))
                .peek( p -> p.setCid(cid))
                .collect(Collectors.toList())
        );
    }

    @Override
    public BaseRepository<CmdOption, Long> getRepository() {
        return repository;
    }
}
