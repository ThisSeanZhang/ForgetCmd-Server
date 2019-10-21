package io.whileaway.forgetcmd.cmd.specs;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.entities.Command_;
import io.whileaway.forgetcmd.cmd.enums.CommandStatus;
import io.whileaway.forgetcmd.rbac.entites.ResourceRelated;
import io.whileaway.forgetcmd.rbac.entites.ResourceRelated_;
import io.whileaway.forgetcmd.util.StringUtils;
import io.whileaway.forgetcmd.util.spec.QueryBuilder;
import io.whileaway.forgetcmd.util.spec.Spec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Supplier;

public class CommandSpec {

    private static Specification<Command> fuzzyMatch(Supplier<String> supplier) {
        return StringUtils.isEmptyOrBlank(supplier.get())
                ? null
                : Spec.like(Command_.commandName, supplier);
    }

    public static Specification<Command> fuzzyMatch(String keyword) {
        return fuzzyMatch(() -> keyword);
    }

    public static Specification<Command> normal() {
        return Spec.equal(Command_.status, () -> CommandStatus.NORMAL);
    }

    public static Specification<Command> likeEmail(Supplier<String> supplier) {
        return QueryBuilder.like("email", StringUtils::isEmptyOrBlank, supplier);
    }

    public static Specification<Command> admin(Supplier<Boolean> supplier) {
        return QueryBuilder.equal("admin", Objects::isNull, supplier);
    }

    public static Sort.Order moreFrequency() {
        return new Sort.Order(Sort.Direction.DESC,Command_.frequency.getName());
    }

//    public static Specification<Command> testJoin() {
//        return (root, query, builder) -> {
//            CriteriaQuery<ResourceRelated> reQuery = builder.createQuery(ResourceRelated.class);
//            Root<ResourceRelated> from = reQuery.from(ResourceRelated.class);
//            Path<Long> resourceId = from.get(ResourceRelated_.resourceId);
//            builder.in(resourceId).value(Command_.cid);
//            Root<ResourceRelated> related = query.from(ResourceRelated.class);
//
//            Join<Command, Long> join = root.join(Command_.cid, JoinType.RIGHT);
//            Join<Command, Long> on = join.on(
//                    builder.equal(root.get(Command_.cid), related.get(ResourceRelated_.resourceId)),
//                    builder.equal(related.get(ResourceRelated_.type), ResourceType.CMD)
//            );
//            return on.getOn();
//        };
//    }

    /**
     * 使用子查询版本
     * @return
     */
    public static Specification<Command> testJoin1() {
        return (root, query, builder) -> {
            Path<Long> path = root.get(Command_.cid); // field to map with sub-query
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<ResourceRelated> fromProject = subquery.from(ResourceRelated.class);
            subquery.select(fromProject.get(ResourceRelated_.resourceId)); // field to map with main-query
//            subquery.where(criteriaBuilder.and(criteriaBuilder.equal("name",name_value),criteriaBuilder.equal("id",id_value)));
            Join<Command, Long> join = root.join(Command_.cid, JoinType.RIGHT);
            join.on(builder.equal(subquery, root.get(Command_.cid)));
            return builder.in(path).value(subquery);
        };
    }


//    public static Specification<Command> testJoin1() {
//        return (root, query, builder) -> {
//            query.from(Command.class)
//                    .join()
//            Path<Long> path = root.get(Command_.cid); // field to map with sub-query
//            Subquery<Long> subquery = query.subquery(Long.class);
//            Root<ResourceRelated> fromProject = subquery.from(ResourceRelated.class);
//            subquery.select(fromProject.get(ResourceRelated_.resourceId)); // field to map with main-query
////            subquery.where(criteriaBuilder.and(criteriaBuilder.equal("name",name_value),criteriaBuilder.equal("id",id_value)));
//            Join<Command, ResourceRelated> join = root.join("cid", JoinType.RIGHT);
//            join.on()
//            return on.getOn();
//        };
//    }

}
