package io.whileaway.forgetcmd.cmd.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OptionTypeEnum {
    NONE("undefined", 0),
    NUMBER("number", 1),
    ENUM("enum", 2),
    STRING("string", 3),
    MAP("map", 4),
    MULTIPLE("MULTIPLE", 1<<14),
    MULTIPLE_NUMBER("MULTIPLE_NUMBER", MULTIPLE.value + NUMBER.value),
    MULTIPLE_ENUM("MULTIPLE_ENUM", MULTIPLE.value + ENUM.value),
    MULTIPLE_STRING("MULTIPLE_STRING", MULTIPLE.value + STRING.value),
    MULTIPLE_MAP("MULTIPLE_MAP", MULTIPLE.value + MAP.value),
    ;
    private String type;
    private Integer value;

    OptionTypeEnum(String string, int i) {
        this.type = string;
        this.value = i;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonValue
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static class Converter implements AttributeConverter<OptionTypeEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(OptionTypeEnum attribute) {
            return attribute.getValue();
        }

        @Override
        public OptionTypeEnum convertToEntityAttribute(Integer dbData) {
            return Stream.of(values())
                    .filter(e -> e.getValue().equals(dbData))
                    .findAny()
                    .orElse(OptionTypeEnum.STRING);
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.valueOf(1<<30));
        System.out.println(Integer.toBinaryString(1<<14));
        System.out.println(OptionTypeEnum.MULTIPLE_MAP.value);
    }
}
