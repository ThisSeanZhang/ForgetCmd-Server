package io.whileaway.forgetcmd.repository;

import io.whileaway.forgetcmd.entities.Command;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandRepository extends JpaRepository<Command, Long> {
}
