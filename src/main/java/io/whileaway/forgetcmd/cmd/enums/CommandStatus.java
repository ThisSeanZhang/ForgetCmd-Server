package io.whileaway.forgetcmd.cmd.enums;

import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import lombok.Getter;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

@Getter
public enum CommandStatus {

    FORBIDDEN(1, "禁用"),
    NORMAL(2, "正常"),
    ;

    private Integer code;
    private String status;

    CommandStatus( Integer i,String string) {
        this.code = i;
        this.status = string;
    }

    public static class Converter implements AttributeConverter<CommandStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(CommandStatus attribute) {
            return attribute.getCode();
        }

        @Override
        public CommandStatus convertToEntityAttribute(Integer dbData) {
            return Stream.of(values())
                    .filter(e -> e.getCode().equals(dbData))
                    .findAny()
                    .orElseThrow(CommonErrorEnum.SERVER_ERROR::getException);
        }
    }
}
