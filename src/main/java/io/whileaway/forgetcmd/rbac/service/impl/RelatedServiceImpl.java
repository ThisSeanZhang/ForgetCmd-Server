package io.whileaway.forgetcmd.rbac.service.impl;

import io.whileaway.forgetcmd.rbac.entites.ResourceRelated;
import io.whileaway.forgetcmd.rbac.enums.PermissionType;
import io.whileaway.forgetcmd.rbac.enums.RelatedError;
import io.whileaway.forgetcmd.rbac.repository.RelatedRepository;
import io.whileaway.forgetcmd.rbac.request.CreateRelatedRequest;
import io.whileaway.forgetcmd.rbac.request.PermitCheckRequest;
import io.whileaway.forgetcmd.rbac.service.RelatedService;
import io.whileaway.forgetcmd.rbac.specs.RelatedSpec;
import io.whileaway.forgetcmd.util.ParamInspect;
import io.whileaway.forgetcmd.util.spec.QueryListBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RelatedServiceImpl implements RelatedService {

    private final RelatedRepository repository;

    public RelatedServiceImpl(RelatedRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createRelated(CreateRelatedRequest request) {
        if(Objects.isNull(request.getResourceId()) || Objects.isNull(request.getDid()) || Objects.isNull(request.getPermits())) {
            RelatedError.PARAM_ERROR.throwThis();
        }
        if (!request.getPermits().isEmpty()) {
            repository.save(new ResourceRelated(request));
        }
    }

    @Override
    public boolean checkPermit(PermitCheckRequest request) {
        if (ParamInspect.isNullObject(request::getType)) return false;
        List<ResourceRelated> relates = new QueryListBuilder<ResourceRelated>()
                .appendCondition(RelatedSpec.developerId(request::getDid))
                .appendCondition(RelatedSpec.checkResourceId(request::getResourceId))
                .appendCondition(RelatedSpec.checkType(request::getType))
                .findFrom(repository::findAll)
                .primitive();

        List<Integer> allPermitValues = relates.stream()
                .map(ResourceRelated::getPermit)
                .map(permit -> List.of(permit.split(",")))
                .flatMap(List::stream)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        List<PermissionType> permissions = PermissionType.findPermissionByValues(allPermitValues);
        if (Objects.nonNull(request.getAfterCheck())) {
            request.getAfterCheck().accept(permissions);
        }
        return permissions.contains(request.getPermission());
    }

    @Override
    public void removeRelate(Long resourceId, Long did, List<PermissionType> types) {

    }
}
