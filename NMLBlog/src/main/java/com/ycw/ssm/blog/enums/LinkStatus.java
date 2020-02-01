package com.ycw.ssm.blog.enums;

/**
 * @author ycw
 */
public enum LinkStatus {

    NORMAL(1, "显示"),

    HIDDEN(0, "隐藏");

    private Integer value;

    private String massage;

    LinkStatus(Integer value, String massage) {
        this.value = value;
        this.massage = massage;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
