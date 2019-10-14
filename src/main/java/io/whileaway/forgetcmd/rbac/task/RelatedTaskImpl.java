package io.whileaway.forgetcmd.rbac.task;

import io.whileaway.forgetcmd.rbac.enums.OptionType;
import io.whileaway.forgetcmd.rbac.enums.RelatedError;
import io.whileaway.forgetcmd.rbac.service.RelatedService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class RelatedTaskImpl implements RelatedTask {

    private final RelatedService relatedService;

    public RelatedTaskImpl(RelatedService relatedService) {
        this.relatedService = relatedService;
    }

    @Override
    public void createRelated(Long resourceId, Long did, OptionType type) {
        relatedService.createRelated(resourceId, did, Collections.singletonList(type));
    }

    @Override
    public void createRelated(Long resourceId, Long did, List<OptionType> types) {
        relatedService.createRelated(resourceId, did, types);
    }

    @Override
    public boolean checkPermit(Long resourceId, Long did, OptionType type) {
        return false;
    }

    @Override
    public void removeRelate(Long resourceId, Long did, OptionType type) {

    }
}
