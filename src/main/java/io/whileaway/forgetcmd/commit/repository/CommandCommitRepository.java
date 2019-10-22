package io.whileaway.forgetcmd.commit.repository;

import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandCommitRepository extends BaseRepository<CommandCommit, Long> {
}
