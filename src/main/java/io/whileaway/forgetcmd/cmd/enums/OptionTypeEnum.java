package io.whileaway.forgetcmd.cmd.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import io.whileaway.forgetcmd.util.enums.IntegerKeyValue;
import lombok.Getter;

import javax.persistence.AttributeConverter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum OptionTypeEnum {
    NONE("undefined", 0),
    NUMBER("NUMBER", 1),
    ENUM("ENUM", 2),
    STRING("STRING", 3),
    MAP("MAP", 4),
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

    @JsonValue
    public Integer getValue() {
        return value;
    }

    public static class Converter implements AttributeConverter<OptionTypeEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(OptionTypeEnum attribute) {
            return Optional.ofNullable(attribute)
                    .map(OptionTypeEnum::getValue)
                    .orElse(NONE.value);
        }

        @Override
        public OptionTypeEnum convertToEntityAttribute(Integer dbData) {
            return Stream.of(values())
                    .filter(e -> e.getValue().equals(dbData))
                    .findAny()
                    .orElse(NONE);
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.valueOf(1<<30));
        System.out.println(Integer.toBinaryString(1<<14));
        System.out.println(OptionTypeEnum.MULTIPLE_MAP.value);
    }

    public static List<IntegerKeyValue> getExhibit() {
        return Stream.of(values()).map(e -> new IntegerKeyValue(e.getValue(), e.getType()))
                .collect(Collectors.toList());
    }
}
