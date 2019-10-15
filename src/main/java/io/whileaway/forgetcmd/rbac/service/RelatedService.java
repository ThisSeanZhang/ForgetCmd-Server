package io.whileaway.forgetcmd.rbac.service;

import io.whileaway.forgetcmd.rbac.enums.PermissionType;

import java.util.List;

public interface RelatedService {
    void createRelated(Long resourceId, Long did, List<PermissionType> types);

    boolean checkPermit(Long resourceId, Long did, PermissionType type);

    void removeRelate(Long resourceId, Long did, List<PermissionType> types);
}
