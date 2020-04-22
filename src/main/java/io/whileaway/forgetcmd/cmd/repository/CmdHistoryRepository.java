package io.whileaway.forgetcmd.cmd.repository;

import io.whileaway.forgetcmd.cmd.entities.CmdHistory;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmdHistoryRepository extends BaseRepository<CmdHistory, Long> {

}
