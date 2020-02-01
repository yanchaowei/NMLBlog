package com.ycw.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ycw
 */
@Data
public class Article implements Serializable {

    private Integer articleId;

    private Integer articleUserId;

    private String articleTitle;

    private String articleContent;

    private Integer articleViewCount;

    private Integer articleCommentCount;

    private Integer articleLikeCount;

    private Integer articleIsComment;

    private Integer articleStatus;

    private Integer articleOrder;

    private Date articleUpdateTime;

    private Date articleCreateTime;

    private String articleSummary;

    // 文章分类列表（非数据库字段）
    private List<Category> categoryList;

    // 作者（非数据库字段）
    private User user;

    // 标签列表（非数据库字段）
    private List<Tag> tagList;

    // 标签 id 列表
    private List<Integer> articleTagIds;

    // 一级分类 id (非数据库字段)
    private Integer articleParentCategoryId;

    // 二级分类 id (非数据库字段)
    private Integer articleChildCategoryId;

}
