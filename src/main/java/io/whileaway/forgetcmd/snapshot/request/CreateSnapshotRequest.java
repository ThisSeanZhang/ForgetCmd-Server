package io.whileaway.forgetcmd.snapshot.request;

import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import lombok.Data;

@Data
public class CreateSnapshotRequest {

    private String config;
    private String title;
    private Long cid;

    public Snapshot convertToSnapshot() {
        Snapshot snapshot = new Snapshot();
        snapshot.setConfig(config);
        snapshot.setTitle(title);
        snapshot.setCid(cid);
        return snapshot;
    }
}
