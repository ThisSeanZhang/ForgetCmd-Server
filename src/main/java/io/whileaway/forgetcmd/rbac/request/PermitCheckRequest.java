package io.whileaway.forgetcmd.rbac.request;

import io.whileaway.forgetcmd.rbac.enums.PermissionType;
import io.whileaway.forgetcmd.rbac.enums.ResourceType;
import lombok.Data;

import java.util.List;
import java.util.function.Consumer;

@Data
public class PermitCheckRequest {

    private Long resourceId;
    private PermissionType permission;
    private ResourceType type;
    private Long did;

    Consumer<List<PermissionType>> afterCheck;

}
