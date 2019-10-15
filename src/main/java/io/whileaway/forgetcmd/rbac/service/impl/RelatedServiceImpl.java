package io.whileaway.forgetcmd.rbac.service.impl;

import io.whileaway.forgetcmd.rbac.entites.ResourceRelated;
import io.whileaway.forgetcmd.rbac.enums.PermissionType;
import io.whileaway.forgetcmd.rbac.enums.RelatedError;
import io.whileaway.forgetcmd.rbac.repository.RelatedRepository;
import io.whileaway.forgetcmd.rbac.service.RelatedService;
import io.whileaway.forgetcmd.rbac.specs.RelatedSpec;
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
    public void createRelated(Long resourceId, Long did, List<PermissionType> types) {
        if(Objects.isNull(resourceId) || Objects.isNull(did) || Objects.isNull(types)) {
            RelatedError.PARAM_ERROR.throwThis();
        }
        String permit = types.stream()
                .map(PermissionType::getType)
                .collect(Collectors.joining(","));
        if (!permit.isEmpty()) {
            repository.save(new ResourceRelated(did, resourceId, permit));
        }
    }

    @Override
    public boolean checkPermit(Long resourceId, Long did, PermissionType type) {
        if (Objects.isNull(type)) return false;
        List<ResourceRelated> relates = new QueryListBuilder<ResourceRelated>()
                .appendCondition(RelatedSpec.developerId(() -> did))
                .appendCondition(RelatedSpec.checkResourceId(() -> resourceId))
                .appendCondition(RelatedSpec.hasPermit(type::getValue))
                .findFrom(repository::findAll)
                .primitive();
        return !relates.isEmpty();
    }

    @Override
    public void removeRelate(Long resourceId, Long did, List<PermissionType> types) {

    }
}
