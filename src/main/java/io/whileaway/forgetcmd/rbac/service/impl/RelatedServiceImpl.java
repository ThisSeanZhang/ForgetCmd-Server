package io.whileaway.forgetcmd.rbac.service.impl;

import io.whileaway.forgetcmd.rbac.entites.ResourceRelated;
import io.whileaway.forgetcmd.rbac.enums.OptionType;
import io.whileaway.forgetcmd.rbac.enums.RelatedError;
import io.whileaway.forgetcmd.rbac.repository.RelatedRepository;
import io.whileaway.forgetcmd.rbac.service.RelatedService;
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
    public void createRelated(Long resourceId, Long did, List<OptionType> types) {
        if(Objects.isNull(resourceId) || Objects.isNull(did) || Objects.isNull(types)) {
            RelatedError.PARAM_ERROR.throwThis();
        }
        String permit = types.stream()
                .map(OptionType::getType)
                .collect(Collectors.joining(","));
        if (!permit.isEmpty()) {
            repository.save(new ResourceRelated(did, resourceId, permit));
        }
    }

    @Override
    public boolean checkPermit(Long resourceId, Long did, OptionType type) {
        List<ResourceRelated> results = repository.findAllByDidAndResourceId(did, resourceId);
        if(re)
        return !results.isEmpty();
    }

    @Override
    public void removeRelate(Long resourceId, Long did, List<OptionType> types) {

    }
}
