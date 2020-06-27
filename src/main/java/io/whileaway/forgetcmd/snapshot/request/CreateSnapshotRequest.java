package io.whileaway.forgetcmd.snapshot.request;

import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.enums.ShareType;
import lombok.Data;

import java.time.Instant;

@Data
public class CreateSnapshotRequest {

    private String paramVal;
    private String optionVal;
    private String title;
    private Long createTime;
    private boolean share;
    private String shareCode;
    private boolean allowCopy;
    private String commandName;
    private Long cid;
    private Long ccid;
    private Long did;

    public Snapshot convertToSnapshot() {
        Snapshot snapshot = new Snapshot();
        snapshot.setParamVal(paramVal);
        snapshot.setOptionVal(optionVal);
        snapshot.setTitle(title);
        snapshot.setCreateTime(Instant.now().toEpochMilli());
        snapshot.setShare(share);
        snapshot.setShareCode(shareCode);
        snapshot.setAllowCopy(allowCopy);
        snapshot.setCommandName(commandName);
        snapshot.setCid(cid);
        snapshot.setCcid(ccid);
        return snapshot;
    }
}
