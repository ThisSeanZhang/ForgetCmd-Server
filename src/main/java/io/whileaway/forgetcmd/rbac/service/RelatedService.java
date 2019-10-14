package io.whileaway.forgetcmd.rbac.service;

import io.whileaway.forgetcmd.rbac.enums.OptionType;

import java.util.List;

public interface RelatedService {
    void createRelated(Long resourceId, Long did, List<OptionType> types);

    boolean checkPermit(Long resourceId, Long did, OptionType type);

    void removeRelate(Long resourceId, Long did, List<OptionType> types);
}
