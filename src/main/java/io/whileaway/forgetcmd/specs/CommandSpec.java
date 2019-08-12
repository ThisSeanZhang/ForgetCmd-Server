package io.whileaway.forgetcmd.specs;

import io.whileaway.forgetcmd.entities.Command;
import io.whileaway.forgetcmd.entities.Command_;
import io.whileaway.forgetcmd.util.StringUtils;
import io.whileaway.forgetcmd.util.spec.QueryBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;
import java.util.function.Supplier;

public class CommandSpec {
    public static Specification<Command> fuzzyMatch(Supplier<String> supplier) {
        return QueryBuilder.like(Command_.commandName, StringUtils::isEmptyOrBlank, supplier);
    }
    public static Specification<Command> fuzzyMatch(String keyword) {
        return fuzzyMatch(()->keyword);
    }

    public static Specification<Command> likeEmail(Supplier<String> supplier) {
        return QueryBuilder.like("email", StringUtils::isEmptyOrBlank, supplier);
    }

    public static Specification<Command> admin(Supplier<Boolean> supplier) {
        return QueryBuilder.equal("admin", Objects::isNull, supplier);
    }
}
