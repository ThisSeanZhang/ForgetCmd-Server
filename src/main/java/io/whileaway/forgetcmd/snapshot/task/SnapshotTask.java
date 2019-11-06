package io.whileaway.forgetcmd.snapshot.task;

import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.request.CreateSnapshotRequest;

public interface SnapshotTask {

    Snapshot createSnapshot(CreateSnapshotRequest request);

    Snapshot getSnapshotById(Long cid);
}
