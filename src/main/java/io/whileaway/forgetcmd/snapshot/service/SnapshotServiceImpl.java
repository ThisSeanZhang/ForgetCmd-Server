package io.whileaway.forgetcmd.snapshot.service;

import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.repository.SnapshotRepository;
import io.whileaway.forgetcmd.snapshot.request.SearchSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.specs.SnapshotSpec;
import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.util.spec.QueryListBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SnapshotServiceImpl implements SnapshotService {

    private final SnapshotRepository repository;

    @Override
    public BaseRepository<Snapshot, Long> getRepository() {
        return repository;
    }

    @Override
    public List<Snapshot> getAllSnapshot() {
        return repository.findAll();
    }

    @Override
    public void deleteSnap(Long snapId) {
        repository.deleteById(snapId);
    }

    @Override
    public List<Snapshot> searchSnapshot(SearchSnapshotRequest request) {
        return new QueryListBuilder<Snapshot>()
                .appendCondition(SnapshotSpec.commandNameLike(request::getCommandName))
                .appendCondition(SnapshotSpec.did(request::getDid))
                .findFrom(repository::findAll)
                .primitive();
    }
}
