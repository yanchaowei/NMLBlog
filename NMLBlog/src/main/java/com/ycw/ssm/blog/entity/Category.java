package com.ycw.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ycw
 */
@Data
public class Category implements Serializable {

    private Integer categoryId;

    private Integer categoryPid;

    private String categoryName;

    private String categoryDescription;

    private Integer categoryOrder;

    private String categoryIcon;

    /**
     * 每个分类对应的文章数，非数据库字段
     */
    private Integer articleCount;

    /**
     * 当某篇文章未分类时，默认在分类栏显示 “未分类”
     * @return
     */
    public static Category defaultCategory() {
        Category category = new Category();
        category.setCategoryId(1000000000);
        category.setCategoryName("未分类");
        return category;
    }


}
