package io.whileaway.forgetcmd.cmd.specs;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.entities.Command_;
import io.whileaway.forgetcmd.cmd.enums.CommandStatus;
import io.whileaway.forgetcmd.util.StringUtils;
import io.whileaway.forgetcmd.util.spec.QueryBuilder;
import io.whileaway.forgetcmd.util.spec.Spec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

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

}
