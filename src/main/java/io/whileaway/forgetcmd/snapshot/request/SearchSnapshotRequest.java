package io.whileaway.forgetcmd.snapshot.request;

import lombok.Data;

@Data
public class SearchSnapshotRequest {
    private Long did;
    private String commandName;
}
