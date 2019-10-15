package io.whileaway.forgetcmd.rbac.enums;


public enum ResourceType {

    CMD(1, "CMD");

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
}
