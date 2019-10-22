package io.whileaway.forgetcmd.verify.enums;

import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public enum CommitStatus {
    NEED_REVIEW("需要审查", 0),
    CREATE_SUCCESS("创建成功", 1),
    ;
    private String status;
    private Integer code;

    CommitStatus(String string, int i) {
        this.status = string;
        this.code = i;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static class Converter implements AttributeConverter<CommitStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(CommitStatus attribute) {
            return attribute.getCode();
        }

        @Override
        public CommitStatus convertToEntityAttribute(Integer dbData) {
            return Stream.of(values())
                    .filter(e -> e.getCode().equals(dbData))
                    .findAny()
                    .orElseThrow(CommonErrorEnum.SERVER_ERROR::getException);
        }
    }
}
