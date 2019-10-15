package io.whileaway.forgetcmd.rbac.enums;

import javax.persistence.AttributeConverter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PermissionType {

    NONE("undefined", 0),
    ADD("ADD", 1),
    GET("GET", 2),
    DELETE("DELETE", 3),
    UPDATE("UPDATE", 4),
    ;
    private String type;
    private Integer value;

    PermissionType(String string, int i) {
        this.type = string;
        this.value = i;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static class Converter implements AttributeConverter<PermissionType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(PermissionType attribute) {
            return attribute.getValue();
        }

        @Override
        public PermissionType convertToEntityAttribute(Integer dbData) {
            return Stream.of(values())
                    .filter(e -> e.getValue().equals(dbData))
                    .findAny()
                    .orElse(PermissionType.NONE);
        }
    }

    public static List<PermissionType> findPermissionByValues(List<Integer> invals) {
        return Stream.of(values())
                .filter(t -> invals.contains(t.value))
                .collect(Collectors.toList());
    }
}
