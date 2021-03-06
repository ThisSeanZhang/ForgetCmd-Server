package io.whileaway.forgetcmd.cmd.repository;

import io.whileaway.forgetcmd.cmd.entities.CmdParam;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CmdParamRepository extends BaseRepository<CmdParam, Long> {

    Optional<List<CmdParam>> findByCid(Long cid);
}
