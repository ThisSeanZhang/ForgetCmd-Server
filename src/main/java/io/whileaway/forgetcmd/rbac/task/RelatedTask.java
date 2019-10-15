package io.whileaway.forgetcmd.rbac.task;

import io.whileaway.forgetcmd.rbac.enums.PermissionType;

import java.util.List;

public interface RelatedTask {

    void createRelated(Long resourceId, Long did, PermissionType type);

    void createRelated(Long resourceId, Long did, List<PermissionType> types);

    boolean checkPermit(Long resourceId, Long did, PermissionType type);

    void removeRelate(Long resourceId, Long did, PermissionType type);
}
