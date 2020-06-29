package io.whileaway.forgetcmd.commit.specs;

import io.whileaway.forgetcmd.commit.entities.CommandCommit;
import io.whileaway.forgetcmd.commit.entities.CommandCommit_;
import io.whileaway.forgetcmd.commit.enums.CommitStatus;
import io.whileaway.forgetcmd.util.ListUtils;
import io.whileaway.forgetcmd.util.spec.QueryBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class CommitSpec {

    public static Specification<CommandCommit> commandName(Supplier<String> supplier) {
        return QueryBuilder.equal(CommandCommit_.commandName, Objects::isNull, supplier);
    }

    public static Specification<CommandCommit> cid(Supplier<Long> supplier) {
        return QueryBuilder.equal(CommandCommit_.cid, Objects::isNull, supplier);
    }

    public static Specification<CommandCommit> version(Supplier<Long> supplier) {
        return QueryBuilder.equal(CommandCommit_.version, Objects::isNull, supplier);
    }

    public static Specification<CommandCommit> status(Supplier<CommitStatus> supplier) {
        return QueryBuilder.equal(CommandCommit_.status, Objects::isNull, supplier);
    }

    public static Specification<CommandCommit> ccids(Supplier<List<Long>> supplier) {
        return QueryBuilder.in(CommandCommit_.ccid, ListUtils::isEmptyList, supplier);
    }
}
