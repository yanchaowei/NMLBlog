package com.ycw.ssm.blog.mapper;

import com.ycw.ssm.blog.entity.Article;
import com.ycw.ssm.blog.entity.ArticleCategoryRef;
import com.ycw.ssm.blog.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ycw
 */
public interface ArticleCategoryRefMapper {
    /**
     * 根据文章 id 查询其对应的分类列表
     * @param articleId
     * @return
     */
    List<Category> categoryListByArticleId(Integer articleId);

    /**
     * 根据文章 Id 获得 分类Idlei
     * @param articleId
     * @return
     */
    List<Integer> categoryIdsByArticleId(Integer articleId);

    /**
     * 根据 categoryIds 获取 articles
     * @param categoryIds
     * @return
     */
    List<Article> listArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds, @Param("limit") Integer limit);

    /**
     * 根据 id 获得对应该分类的文章数
     * @param categoryId
     * @return
     */
    Integer getArticleCountByCategoryId(@Param("categoryId") Integer categoryId);

    void insert(ArticleCategoryRef articleParentCategoryRef);

    void deleteByArticleId(Integer articleId);

    List<Article> listArticleBycategoryId(Integer categoryId);
}
