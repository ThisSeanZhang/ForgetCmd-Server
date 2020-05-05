package io.whileaway.forgetcmd.cmd.repository;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandRepository extends BaseRepository<Command, Long> {

    Optional<Command> findByCidOrCommandName(Long cid, String commandName);

}
