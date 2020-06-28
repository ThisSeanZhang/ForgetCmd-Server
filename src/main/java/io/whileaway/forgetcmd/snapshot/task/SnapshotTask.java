package io.whileaway.forgetcmd.snapshot.task;

import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.request.CreateSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.request.GetSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.request.SearchSnapshotRequest;

import java.util.List;

public interface SnapshotTask {

    Snapshot createSnapshot(CreateSnapshotRequest request);

    Snapshot getSnapshotById(Long cid);

    List<Snapshot> getAllSnaps();

    void deleteSnap(Long snapId);

    List<Snapshot> searchSnapshot(SearchSnapshotRequest request);

    Snapshot getSnapshot(GetSnapshotRequest request);
}
