package io.whileaway.forgetcmd.snapshot.task;

import io.whileaway.forgetcmd.rbac.CurrentDeveloperInfo;
import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.request.CreateSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.request.SearchSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.service.SnapshotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SnapshotTaskImpl implements SnapshotTask {

    private final SnapshotService service;
    private final CurrentDeveloperInfo currentDeveloper;

    @Override
    public Snapshot createSnapshot(CreateSnapshotRequest request) {
        Snapshot snapshot = request.convertToSnapshot();
        snapshot.setDid(currentDeveloper.getDid());
        // TODO 统一存放关键字
        snapshot.setLocation("online-remote");
        return service.save(snapshot);
    }

    @Override
    public Snapshot getSnapshotById(Long cid) {
        // TODO 错误抛出
        return service.findById(cid).orElse(null);
    }

    @Override
    public List<Snapshot> getAllSnaps() {
        return service.getAllSnapshot();
    }

    @Override
    public void deleteSnap(Long snapId) {
        service.deleteSnap(snapId);
    }

    @Override
    public List<Snapshot> searchSnapshot(SearchSnapshotRequest request) {
        return service.searchSnapshot(request);
    }
}
