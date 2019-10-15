package io.whileaway.forgetcmd.rbac.service;

import io.whileaway.forgetcmd.rbac.enums.PermissionType;
import io.whileaway.forgetcmd.rbac.request.CreateRelatedRequest;
import io.whileaway.forgetcmd.rbac.request.PermitCheckRequest;

import java.util.List;

public interface RelatedService {

    void createRelated(CreateRelatedRequest request);

    void removeRelate(Long resourceId, Long did, List<PermissionType> types);

    boolean checkPermit(PermitCheckRequest request);
}
