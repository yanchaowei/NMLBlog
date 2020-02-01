package com.ycw.ssm.blog.mapper;

import com.ycw.ssm.blog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ycw
 */
public interface ArticleMapper {
    /**
     * 根据用户 id 查询文章数量
     * @param userId
     * @return
     */
    Integer totalByUserId(Integer userId);

    /**
     * 查询所有文章
     * @return
     */
    List<Article> findAll(Map<String, Object> criteria);

    /**
     * 根据 文章id 获取文章详情
     * @param articleId
     * @return
     */
    Article selectArticleByArticleId(@Param("status") Integer status, @Param("articleId") Integer articleId);

    /**
     * 根据浏览量查询文章
     * @param limit
     * @return
     */
    List<Article> findArticlesByViewCount(Integer limit);

    /**
     * 查询下一篇文章
     * @param articleId
     * @return
     */
    Article findAfterArticle(@Param("articleId") Integer articleId);

    /**
     * 查询下一篇文章
     * @param articleId
     * @return
     */
    Article findPreArticle(@Param("articleId") Integer articleId);

    List<Article> findArticlesByCommentCount(@Param("limit") Integer limit);

    Article selectByArticleId(Integer commentArticleId);

    void insert(Article article);

    List<Article> listLatestArticle();

    void update(Article article);

    void deleteById(Integer articleId);

    Integer getArticleCount();

    Integer countArticleView();

    List<Article> getRandomArticleList(@Param("limit") Integer limit);

    List<Article> listArticleWithoutContent();
}
