package io.whileaway.forgetcmd.snapshot.service;

import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.request.GetSnapshotRequest;
import io.whileaway.forgetcmd.snapshot.request.SearchSnapshotRequest;
import io.whileaway.forgetcmd.util.BaseService;

import java.util.List;

public interface SnapshotService extends BaseService<Snapshot, Long> {

    List<Snapshot> getAllSnapshot();

    void deleteSnap(Long snapId);

    List<Snapshot> searchSnapshot(SearchSnapshotRequest request);

    Snapshot getSnapshot(GetSnapshotRequest request);
}
