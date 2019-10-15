package io.whileaway.forgetcmd.rbac.task;

import io.whileaway.forgetcmd.rbac.enums.PermissionType;
import io.whileaway.forgetcmd.rbac.request.CreateRelatedRequest;
import io.whileaway.forgetcmd.rbac.request.PermitCheckRequest;
import io.whileaway.forgetcmd.rbac.service.RelatedService;
import org.springframework.stereotype.Component;

@Component
public class RelatedTaskImpl implements RelatedTask {

    private final RelatedService relatedService;

    public RelatedTaskImpl(RelatedService relatedService) {
        this.relatedService = relatedService;
    }

    @Override
    public void createRelated(CreateRelatedRequest request) {
        relatedService.createRelated(request);
    }

    @Override
    public boolean checkPermit(PermitCheckRequest request) {
        return relatedService.checkPermit(request);
    }

    @Override
    public void removeRelate(Long resourceId, Long did, PermissionType type) {

    }
}
