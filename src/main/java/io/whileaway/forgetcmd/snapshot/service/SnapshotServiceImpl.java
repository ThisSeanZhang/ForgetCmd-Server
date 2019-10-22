package io.whileaway.forgetcmd.snapshot.service;

import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.repository.SnapshotRepository;
import io.whileaway.forgetcmd.util.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class SnapshotServiceImpl implements SnapshotService {

    private final SnapshotRepository repository;

    public SnapshotServiceImpl(SnapshotRepository repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepository<Snapshot, Long> getRepository() {
        return repository;
    }
}
