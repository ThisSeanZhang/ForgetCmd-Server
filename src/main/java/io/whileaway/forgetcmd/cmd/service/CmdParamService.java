package io.whileaway.forgetcmd.cmd.service;

import io.whileaway.forgetcmd.cmd.entities.CmdParam;
import io.whileaway.forgetcmd.util.BaseService;

import java.util.List;
import java.util.Optional;

public interface CmdParamService extends BaseService<CmdParam, Long> {

    Optional<List<CmdParam>> findByCid(Long cid);

    void updateCommandParams(Long cid, List<CmdParam> params);
}
