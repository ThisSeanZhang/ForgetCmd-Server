package io.whileaway.forgetcmd.service;

import io.whileaway.forgetcmd.entities.CmdParam;
import io.whileaway.forgetcmd.util.BaseService;

import java.util.List;
import java.util.Optional;

public interface CmdParamService extends BaseService<CmdParam, Long> {

    Optional<List<CmdParam>> findBydCid(Long cid);
}
