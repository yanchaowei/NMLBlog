package com.ycw.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ycw
 */
@Data
public class ArticleCategoryRef implements Serializable {

    private Integer articleId;

    private Integer categoryId;

}
