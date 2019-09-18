package io.whileaway.forgetcmd.verify.repository;

import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.verify.entities.CmdAddLog;
import org.springframework.stereotype.Repository;

@Repository
public interface CmdAddLogRepository extends BaseRepository<CmdAddLog, Long> {
}
