package com.ycw.ssm.blog.service;

import com.github.pagehelper.PageInfo;
import com.ycw.ssm.blog.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * @author ycw
 */
public interface IArticleService {
    PageInfo<Article> getArticleList(Integer pageIndex, Integer pageSize, Map<String, Object> criteria);

    Article getArticleInfoByArticleId(Integer status, Integer articleId);

    List<Integer> categoryIdsByArticleId(Integer articleId);

    List<Article> listArticleByCategoryIds(List<Integer> categoryIds, Integer limit);

    List<Article> listArticleByViewCount(Integer limit);

    Article getAfterArticle(Integer articleId);

    Article getPreArticle(Integer articleId);

    List<Article> listArticlesByCommentCount(Integer i);

    void insert(Article article);

    List<Article> listLatestArticle();

    Article getArticleById(Integer articleId);

    void update(Article article);

    void deleteById(Integer articleId);

    Integer getArticleCount();

    Integer getViewTotal();

    List<Article> getRandomArticleList(Integer limit);

    PageInfo<Article> getArticleListByTagId(Integer pageIndex, Integer pageSize, Integer tagId);

    PageInfo<Article> getArticleListBycategoryId(Integer pageIndex, Integer pageSize, Integer categoryId);

    List<Article> listArticleWithoutContent();
}
