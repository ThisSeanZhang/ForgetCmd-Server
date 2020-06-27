package io.whileaway.forgetcmd.snapshot.specs;

import io.whileaway.forgetcmd.snapshot.entities.Snapshot;
import io.whileaway.forgetcmd.snapshot.entities.Snapshot_;
import io.whileaway.forgetcmd.util.ParamInspect;
import io.whileaway.forgetcmd.util.spec.Spec;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Supplier;

public class SnapshotSpec {

    public static Specification<Snapshot> commandNameLike(Supplier<String> supplier) {
        return Spec.like(Snapshot_.commandName, () -> "%" + supplier.get() + "%");
    }

    public static Specification<Snapshot> did(Supplier<Long> did) {
        if (ParamInspect.isNullLong(did)) return null;
        return Spec.equal(Snapshot_.did, did);
    }
}
