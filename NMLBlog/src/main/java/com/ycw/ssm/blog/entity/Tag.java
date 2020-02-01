package com.ycw.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ycw
 */
@Data
public class Tag implements Serializable {

    private Integer tagId;

    private String tagName;

    private String tagDescription;

    /**
     * 标签所关联的文章数；非数据库字段。
     */
    private Integer articleCount;

    public static Tag defaultTag() {
        Tag tag = new Tag();
        tag.setTagName("无标签");
        tag.setTagId(1000000);
        return tag;
    }
}
