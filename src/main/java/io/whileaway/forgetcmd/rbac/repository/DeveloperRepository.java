package io.whileaway.forgetcmd.rbac.repository;

import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends BaseRepository<Developer, Long> {

}
