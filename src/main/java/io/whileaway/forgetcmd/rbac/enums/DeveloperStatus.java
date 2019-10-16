package io.whileaway.forgetcmd.rbac.enums;

import lombok.Getter;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

@Getter
public enum DeveloperStatus {

    UNKNOWN(0, "未知"),
    FORBIDDEN(1, "禁用"),
    NORMAL(2, "正常"),
    ;
    private Integer value;
    private String description;

    DeveloperStatus(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static class Converter implements AttributeConverter<DeveloperStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(DeveloperStatus attribute) {
            return attribute.getValue();
        }

        @Override
        public DeveloperStatus convertToEntityAttribute(Integer dbData) {
            return Stream.of(values())
                    .filter(e -> e.getValue().equals(dbData))
                    .findAny()
                    .orElse(DeveloperStatus.UNKNOWN);
        }
    }
}
