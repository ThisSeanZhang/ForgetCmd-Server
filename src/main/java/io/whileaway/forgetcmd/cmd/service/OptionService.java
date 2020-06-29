package io.whileaway.forgetcmd.cmd.service;

import io.whileaway.forgetcmd.cmd.entities.CmdOption;
import io.whileaway.forgetcmd.util.BaseService;

import java.util.List;
import java.util.Optional;

public interface OptionService extends BaseService<CmdOption, Long> {

    Optional<List<CmdOption>> findByCid(Long cid);

    void updateCommandOptions(Long cid, List<CmdOption> options);
}
