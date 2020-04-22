package io.whileaway.forgetcmd.commit.request;

import io.whileaway.forgetcmd.commit.enums.CommitStatus;
import io.whileaway.forgetcmd.util.StringUtils;
import lombok.Data;

import java.util.List;

@Data
public class ItemSearchRequest {

    private String ccids;
    private Long cid;
    private Long version;

    public List<Long> getCcids() {
        return StringUtils.decodeIDs(ccids);
    }

    public CommitSearchRequest convertCommitSearch() {
        CommitSearchRequest request = new CommitSearchRequest();
        request.setCid(cid);
        request.setVersion(version);
        request.setStatus(CommitStatus.NEED_REVIEW);
        request.setCcids(ccids);
        return request;
    }
}
