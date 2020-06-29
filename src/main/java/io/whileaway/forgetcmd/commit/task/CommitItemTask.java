package io.whileaway.forgetcmd.commit.task;

import io.whileaway.forgetcmd.commit.entities.CommitItem;
import io.whileaway.forgetcmd.commit.request.ItemSearchRequest;

import java.util.List;

public interface CommitItemTask {

    List<CommitItem> searchItems(ItemSearchRequest request);

//    List<CommitItem> getCommandCurrentVersion(Long cid);
}
