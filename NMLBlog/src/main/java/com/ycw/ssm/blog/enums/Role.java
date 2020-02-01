package com.ycw.ssm.blog.enums;

/**
 * @author ycw
 */
public enum Role {

    ADMIN(1, "博主"),

    VISITOR(0, "访客");

    private Integer value;

    private String role;

    Role(Integer code, String role) {
        this.value = code;
        this.role = role;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
