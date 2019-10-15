package io.whileaway.forgetcmd.rbac.request;

import io.whileaway.forgetcmd.rbac.enums.PermissionType;
import io.whileaway.forgetcmd.rbac.enums.ResourceType;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CreateRelatedRequest {

    private Long resourceId;
    private Long did;
    private List<PermissionType> permits;
    private ResourceType type;


    public String convertPermits2String() {
        return permits.stream()
                .map(PermissionType::getType)
                .collect(Collectors.joining(","));
    }
}
