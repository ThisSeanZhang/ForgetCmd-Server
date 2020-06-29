package io.whileaway.forgetcmd.rbac.entites;

import io.whileaway.forgetcmd.rbac.enums.ResourceType;
import io.whileaway.forgetcmd.rbac.request.CreateRelatedRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class ResourceRelated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;
    private Long did;
    private Long resourceId;
    private String permit;
    @Convert(converter = ResourceType.Converter.class)
    private ResourceType type;

    public ResourceRelated(CreateRelatedRequest request) {
        this.did = request.getDid();
        this.resourceId = request.getResourceId();
        this.permit = request.convertPermits2String();
        this.type = request.getType();
    }
}
