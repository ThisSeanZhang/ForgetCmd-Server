package io.whileaway.forgetcmd.rbac.entites;

import io.whileaway.forgetcmd.rbac.enums.OptionType;
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

    public ResourceRelated(Long did, Long resourceId, String permit) {
        this.did = did;
        this.resourceId = resourceId;
        this.permit = permit;
    }
}
