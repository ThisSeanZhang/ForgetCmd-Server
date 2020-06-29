package io.whileaway.forgetcmd.rbac.enums;


import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public enum ResourceType {

    UNDEFINED(0, "UNDEFINED"),
    CMD(1, "CMD"),
    VERIFY(2, "待验证命令")
    ;

    private Integer type;
    private String description;


    ResourceType(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public static class Converter implements AttributeConverter<ResourceType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(ResourceType attribute) {
            return attribute.getType();
        }

        @Override
        public ResourceType convertToEntityAttribute(Integer dbData) {
            return Stream.of(values())
                    .filter(e -> e.getType().equals(dbData))
                    .findAny()
                    .orElse(ResourceType.UNDEFINED);
        }
    }
}
