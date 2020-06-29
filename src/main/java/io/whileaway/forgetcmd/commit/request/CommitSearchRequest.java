package io.whileaway.forgetcmd.commit.request;

import io.whileaway.forgetcmd.commit.enums.CommitStatus;
import io.whileaway.forgetcmd.util.StringUtils;
import lombok.Data;

import java.util.List;

@Data
public class CommitSearchRequest {
    private Long cid;
    private String commandName;
    private Long version;
    private CommitStatus status;
    private String ccids;

    public List<Long> getCcids() {
        return StringUtils.decodeIDs(ccids);
    }
}
