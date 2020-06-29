package io.whileaway.forgetcmd.snapshot.request;

import lombok.Data;

@Data
public class GetSnapshotRequest {

    private Long snapId;
    private String shareCode;
}
