package io.whileaway.forgetcmd.repository;

import io.whileaway.forgetcmd.entities.Command;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommandRepository extends BaseRepository<Command, Long> {
}
