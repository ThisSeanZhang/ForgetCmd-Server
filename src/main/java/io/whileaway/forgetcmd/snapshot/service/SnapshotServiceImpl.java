package io.whileaway.forgetcmd.snapshot.service;

import io.whileaway.forgetcmd.rbac.CurrentDeveloperInfo;
import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.enums.SnapshotError;
import io.whileaway.forgetcmd.snapshot.repository.SnapshotRepository;
import io.whileaway.forgetcmd.snapshot.request.CreateSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.request.GetSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.request.SearchSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.specs.SnapshotSpec;
import io.whileaway.forgetcmd.util.BaseRepository;
import io.whileaway.forgetcmd.util.ValidUtil;
import io.whileaway.forgetcmd.util.spec.QueryListBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SnapshotServiceImpl implements SnapshotService {

    private final SnapshotRepository repository;
    private final CurrentDeveloperInfo developer;

    private final SnapshotSpec snapshotSpec;

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
                .appendCondition(snapshotSpec.commandNameLike(request::getCommandName))
                .appendCondition(snapshotSpec.canSearch())
                .findFrom(repository::findAll)
                .primitive();
    }

    @Override
    public Snapshot getSnapshot(GetSnapshotRequest request) {
        Optional<Snapshot> snapshotOptional = repository.findById(request.getSnapId());
        Snapshot snapshot = snapshotOptional.orElseThrow(SnapshotError.NOT_FOUND::getException);
        if (snapshot.canShare(request.getShareCode()))
            return snapshot;
        else if (Objects.equals(developer.getDid(), snapshot.getDid()))
            return snapshot;
        else
            throw SnapshotError.FORBIDDEN.getException();
    }

    @Override
    public Snapshot upgrade(CreateSnapshotRequest request) {
        Optional<Snapshot> snapshotOptional = repository.findBySnapIdAndDid(request.getSnapId(), developer.getDid());
        if (snapshotOptional.isEmpty()) SnapshotError.NOT_FOUND.throwThis();
        Snapshot snapshot = snapshotOptional.get();
        snapshot.updateFrom(request.convertToSnapshot());
        return repository.save(snapshot);
    }
}
