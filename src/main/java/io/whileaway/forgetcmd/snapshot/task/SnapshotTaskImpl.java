package io.whileaway.forgetcmd.snapshot.task;

import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.enums.ShareType;
import io.whileaway.forgetcmd.snapshot.request.CreateSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.service.SnapshotService;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SnapshotTaskImpl implements SnapshotTask {

    private final SnapshotService service;

    public SnapshotTaskImpl(SnapshotService service) {
        this.service = service;
    }

    @Override
    public Snapshot createSnapshot(CreateSnapshotRequest request) {
        Snapshot snapshot = request.convertToSnapshot();
        snapshot.setCreateTime(Instant.now().toEpochMilli());
        snapshot.setShare(ShareType.SECRET);
        snapshot.setAllowCopy(false);
        return service.save(snapshot);
    }

    @Override
    public Snapshot getSnapshotById(Long cid) {
        // TODO 错误抛出
        return service.findById(cid).orElse(null);
    }
}
