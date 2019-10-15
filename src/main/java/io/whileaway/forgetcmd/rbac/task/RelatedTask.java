package io.whileaway.forgetcmd.rbac.task;

import io.whileaway.forgetcmd.rbac.enums.PermissionType;
import io.whileaway.forgetcmd.rbac.request.CreateRelatedRequest;
import io.whileaway.forgetcmd.rbac.request.PermitCheckRequest;

import java.util.List;

public interface RelatedTask {

    void createRelated(CreateRelatedRequest request);

    boolean checkPermit(PermitCheckRequest request);

    void removeRelate(Long resourceId, Long did, PermissionType type);
}
