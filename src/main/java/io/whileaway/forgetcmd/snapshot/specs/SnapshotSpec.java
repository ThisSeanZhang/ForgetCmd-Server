package io.whileaway.forgetcmd.snapshot.specs;

import io.whileaway.forgetcmd.rbac.CurrentDeveloperInfo;
import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.entities.Snapshot_;
import io.whileaway.forgetcmd.util.ParamInspect;
import io.whileaway.forgetcmd.util.spec.Spec;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Supplier;

@Component
@AllArgsConstructor
public class SnapshotSpec {

    private final CurrentDeveloperInfo developerInfo;

    public Specification<Snapshot> commandNameLike(Supplier<String> supplier) {
        return Spec.like(Snapshot_.commandName, () -> "%" + supplier.get() + "%");
    }

    public Specification<Snapshot> did(Supplier<Long> did) {
        if (ParamInspect.isNullLong(did)) return null;
        return Spec.equal(Snapshot_.did, did);
    }

    public Specification<Snapshot> canSearch() {
        Specification<Snapshot> currentDid = currentDid();
        return Objects.isNull(currentDid)
                ? isShared().and(nullShareCode())
                : currentDid.or(isShared().and(nullShareCode()));
    }

    public Specification<Snapshot> currentDid() {
        return did(developerInfo::getDid);
    }

    public Specification<Snapshot> isShared() {
        return Spec.isTrue(Snapshot_.share);
    }

    public Specification<Snapshot> nullShareCode() {
        return Spec.isNull(Snapshot_.shareCode);
    }

    public Specification<Snapshot> snapId(Supplier<Long> snapId) {
        if (ParamInspect.isNullLong(snapId)) return null;
        return Spec.equal(Snapshot_.snapId, snapId);
    }

}
