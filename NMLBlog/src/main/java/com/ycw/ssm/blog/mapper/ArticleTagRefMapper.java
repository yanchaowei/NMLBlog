package com.ycw.ssm.blog.mapper;

import com.ycw.ssm.blog.entity.Article;
import com.ycw.ssm.blog.entity.ArticleTagRef;
import com.ycw.ssm.blog.entity.Tag;

import java.util.List;

/**
 * @author ycw
 */
public interface ArticleTagRefMapper {
    /**
     * 根据 articleId 获取 tag 列表
     * @param articleId
     * @return
     */
    List<Tag> tagListByArticleId(Integer articleId);

    Integer findArticleCountByTagId(Integer tagId);

    void insert(ArticleTagRef articleTagRef);

    void deleteByArticleId(Integer articleId);

    List<Article> listArticleByTagId(Integer tagId);
}
