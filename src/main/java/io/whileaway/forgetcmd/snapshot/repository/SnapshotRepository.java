package io.whileaway.forgetcmd.snapshot.repository;

import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnapshotRepository extends BaseRepository<Snapshot, Long> {
}
