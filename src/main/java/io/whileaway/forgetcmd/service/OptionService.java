package io.whileaway.forgetcmd.service;

import io.whileaway.forgetcmd.entities.CmdOption;
import io.whileaway.forgetcmd.util.BaseService;

import java.util.List;
import java.util.Optional;

public interface OptionService extends BaseService<CmdOption, Long> {

    Optional<List<CmdOption>> findByCid(Long cid);
}
