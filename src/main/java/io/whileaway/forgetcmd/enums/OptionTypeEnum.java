package io.whileaway.forgetcmd.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
public enum OptionTypeEnum {
    NONE("undefined", 0),
    NUMBER("number", 1),
    ENUM("enum", 2),
    STRING("string", 3),
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

    @JsonProperty("type")
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
}
