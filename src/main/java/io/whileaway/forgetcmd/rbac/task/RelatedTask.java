package io.whileaway.forgetcmd.rbac.task;

import io.whileaway.forgetcmd.rbac.enums.OptionType;

import java.util.List;

public interface RelatedTask {

    void createRelated(Long resourceId, Long did, OptionType type);

    void createRelated(Long resourceId, Long did, List<OptionType> types);

    boolean checkPermit(Long resourceId, Long did, OptionType type);

    void removeRelate(Long resourceId, Long did, OptionType type);
}
