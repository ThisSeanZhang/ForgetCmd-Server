package io.whileaway.forgetcmd.util.enums;

import lombok.Data;

@Data
public class IntegerKeyValue {

    private Integer key;
    private String value;

    public IntegerKeyValue(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
