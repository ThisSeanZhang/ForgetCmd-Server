package io.whileaway.forgetcmd.rbac.specs;

import io.whileaway.forgetcmd.rbac.entites.ResourceRelated;
import io.whileaway.forgetcmd.rbac.entites.ResourceRelated_;
import io.whileaway.forgetcmd.rbac.enums.RelatedError;
import io.whileaway.forgetcmd.util.ParamInspect;
import io.whileaway.forgetcmd.util.spec.Spec;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Supplier;

public class RelatedSpec {

    public static Specification<ResourceRelated> anonymous() {
        return Spec.isNull(ResourceRelated_.did);
    }

    public static Specification<ResourceRelated> resourceId(Supplier<Long> rid) {
        if (ParamInspect.nonNullLong(rid)) return null;
        return Spec.equal(ResourceRelated_.resourceId, rid);
    }

    public static Specification<ResourceRelated> checkResourceId(Supplier<Long> rid) {
        if (ParamInspect.nonNullLong(rid)) RelatedError.PARAM_ERROR.throwThis();
        return resourceId(rid);
    }

    public static Specification<ResourceRelated> developerId(Supplier<Long> did) {
        if (ParamInspect.nonNullLong(did)) return null;
        return Spec.equal(ResourceRelated_.did, did);
    }

    public static Specification<ResourceRelated> checkdeveloperId(Supplier<Long> did) {
        if (ParamInspect.nonNullLong(did)) RelatedError.PARAM_ERROR.throwThis();
        return developerId(did);
    }
    public static Specification<ResourceRelated> hasPermit(Supplier<Integer> supplier) {
        return permitCheck1(supplier)
                .or(permitCheck2(supplier))
                .or(permitCheck3(supplier))
                .or(permitCheck4(supplier));
    }

    private static Specification<ResourceRelated> permitCheck1(Supplier<Integer> supplier) {
        return Spec.equal(ResourceRelated_.permit, () -> supplier.get().toString());
    }

    private static Specification<ResourceRelated> permitCheck2(Supplier<Integer> supplier) {
        return Spec.like(ResourceRelated_.permit, () -> supplier.get() + ",%");
    }

    private static Specification<ResourceRelated> permitCheck3(Supplier<Integer> supplier) {
        return Spec.like(ResourceRelated_.permit,() -> "%," + supplier.get());
    }

    private static Specification<ResourceRelated> permitCheck4(Supplier<Integer> supplier) {
        return Spec.like(ResourceRelated_.permit,() -> "%," + supplier.get() + ",%");
    }
}
