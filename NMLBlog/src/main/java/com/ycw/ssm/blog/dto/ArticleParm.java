package com.ycw.ssm.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ycw
 */
@Data
public class ArticleParm {

    private Integer articleId;

    private String articleTitle;

    private String articleContent;

    private Integer articleParentCategoryId;

    private Integer articleChildCategoryId;

    private List<Integer> articleTagIds;

    private Integer articleStatus;

    private Integer articleOrder;
}
