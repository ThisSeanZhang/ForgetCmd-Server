package io.whileaway.forgetcmd.verify.repository;

import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.verify.entities.CommandCommit;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandCommitRepository extends BaseRepository<CommandCommit, Long> {
}
