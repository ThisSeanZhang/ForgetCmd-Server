package io.whileaway.forgetcmd.repository;

import io.whileaway.forgetcmd.entities.CmdOption;
import io.whileaway.forgetcmd.util.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface CmdOptionRepository extends BaseRepository<CmdOption, Long> {

    Optional<List<CmdOption>> findByCid(Long cid);
}
