package io.whileaway.forgetcmd.snapshot.enums;

import lombok.Getter;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

@Getter
public enum ShareType {
    SECRET("秘密", 0),
    OPEN("开放", 1),
    SECRET_SHARE("私密分享", 2),
    ;

    private String type;
    private Integer value;

    ShareType(String type, Integer value) {
        this.type = type;
        this.value = value;
    }


    public static class Converter implements AttributeConverter<ShareType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(ShareType attribute) {
            return attribute.getValue();
        }

        @Override
        public ShareType convertToEntityAttribute(Integer dbData) {
            return Stream.of(values())
                    .filter(e -> e.getValue().equals(dbData))
                    .findAny()
                    .orElse(ShareType.SECRET);
        }
    }
}
