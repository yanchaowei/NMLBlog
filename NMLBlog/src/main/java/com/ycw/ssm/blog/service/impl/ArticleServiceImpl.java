package com.ycw.ssm.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ycw.ssm.blog.entity.*;
import com.ycw.ssm.blog.mapper.ArticleCategoryRefMapper;
import com.ycw.ssm.blog.mapper.ArticleMapper;
import com.ycw.ssm.blog.mapper.ArticleTagRefMapper;
import com.ycw.ssm.blog.mapper.UserMapper;
import com.ycw.ssm.blog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ycw
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleTagRefMapper articleTagRefMapper;

    @Override
    public PageInfo<Article> getArticleList(Integer pageIndex, Integer pageSize, Map<String, Object> criteria) {

        PageHelper.startPage(pageIndex, pageSize);

        List<Article> articleList = articleMapper.findAll(criteria);

        // 为每一篇文章绑定 CatagoryList 和 tagList
        if (articleList != null && !articleList.isEmpty()) {
            for (Article article : articleList) {
                setCategoryListForArticle(article);
            }
        }

        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }

    /**
     * // 为每一篇文章绑定 CatagoryList 和 tagList
     * @param article
     */
    private void setCategoryListForArticle(Article article) {
        List<Category> categoryList = articleCategoryRefMapper.categoryListByArticleId(article.getArticleId());
        if (categoryList == null || categoryList.size() == 0) {
            categoryList = new ArrayList<Category>();
            categoryList.add(Category.defaultCategory());
        }
        article.setCategoryList(categoryList);

        List<Tag> tagList = articleTagRefMapper.tagListByArticleId(article.getArticleId());
        if (tagList == null || tagList.size() == 0) {
            tagList = new ArrayList<Tag>();
            tagList.add(Tag.defaultTag());
        }
        article.setTagList(tagList);
    }

    @Override
    public Article getArticleInfoByArticleId(Integer status, Integer articleId) {
        Article article = articleMapper.selectArticleByArticleId(status, articleId);
        // 绑定作者
        User user = userMapper.getUserByUserId(article.getArticleUserId());
        article.setUser(user);

        // 绑定分类列表
        setCategoryListForArticle(article);

        // 绑定表标签列表
        List<Tag> tagList = articleTagRefMapper.tagListByArticleId(articleId);
        article.setTagList(tagList);

        return article;
    }

    @Override
    public List<Integer> categoryIdsByArticleId(Integer articleId) {
        List<Integer> categoryIdsByArticleId = articleCategoryRefMapper.categoryIdsByArticleId(articleId);
        return categoryIdsByArticleId;
    }

    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> categoryIds, Integer limit) {
        List<Article> articleList = articleCategoryRefMapper.listArticleByCategoryIds(categoryIds, limit);
        return articleList;
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        List<Article> articleList = articleMapper.findArticlesByViewCount(limit);
        return articleList;
    }

    @Override
    public Article getAfterArticle(Integer articleId) {
        Article preArticle = articleMapper.findPreArticle(articleId);
        return preArticle;
    }

    @Override
    public Article getPreArticle(Integer articleId) {
        Article afterArticle = articleMapper.findAfterArticle(articleId);
        return afterArticle;
    }

    @Override
    public List<Article> listArticlesByCommentCount(Integer limit) {
        List<Article> articlesByCommentCount = articleMapper.findArticlesByCommentCount(limit);
        return articlesByCommentCount;
    }

    @Override
    public void insert(Article article) {

        // 为 article 封装默认属性值
        article.setArticleCreateTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(1);
        article.setArticleCommentCount(0);
        article.setArticleLikeCount(0);
        article.setArticleOrder(1);
        article.setArticleViewCount(0);
        // 插入文章
        articleMapper.insert(article);

        // 为标签列表中的 tag 构建新的 ArticleTagRef 对象，并插入数据库
        insertArticleTagRef(article);

        // 为分类列表中的每个分类构建新的 ArticleCategoryRef 并插入
        article = insertArticleCategoryRef(article);

    }

    private void insertArticleTagRef(Article article) {
        for (Tag tag : article.getTagList()) {
            ArticleTagRef articleTagRef = new ArticleTagRef();
            articleTagRef.setArticleId(article.getArticleId());
            articleTagRef.setTagId(tag.getTagId());
            articleTagRefMapper.insert(articleTagRef);
        }
    }

    @Override
    public List<Article> listLatestArticle() {
        List<Article> articleList = articleMapper.listLatestArticle();
        return articleList;
    }

    @Override
    public Article getArticleById(Integer articleId) {
        Article article = null;
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("articleId", articleId);
        List<Article> articles = articleMapper.findAll(criteria);
        if (articles != null && !articles.isEmpty()) {
            article = articles.get(0);
        }
        List<Category> categoryList = articleCategoryRefMapper.categoryListByArticleId(articleId);
        article.setCategoryList(categoryList);
        List<Tag> tagList = articleTagRefMapper.tagListByArticleId(articleId);
        article.setTagList(tagList);
        return article;
    }

    @Override
    public void update(Article article) {

        article.setArticleUpdateTime(new Date());

        // 将数据库中原有 ArticleCategoryRef 对象删除，并插入新的联系对象
        articleCategoryRefMapper.deleteByArticleId(article.getArticleId());
        insertArticleCategoryRef(article);
        // 将数据库中原有 ArticleTagRef 对象删除，并插入新的联系对象\
        articleTagRefMapper.deleteByArticleId(article.getArticleId());
        insertArticleTagRef(article);

        articleMapper.update(article);
    }

    @Override
    public void deleteById(Integer articleId) {
        articleMapper.deleteById(articleId);
        articleCategoryRefMapper.deleteByArticleId(articleId);
        articleTagRefMapper.deleteByArticleId(articleId);
    }

    @Override
    public Integer getArticleCount() {
        Integer articleCount = articleMapper.getArticleCount();
        return articleCount;
    }

    @Override
    public Integer getViewTotal() {
        Integer viewTotal = articleMapper.countArticleView();
        return viewTotal;
    }

    @Override
    public List<Article> getRandomArticleList(Integer limit) {
        List<Article> randomArticleList = articleMapper.getRandomArticleList(limit);
        return randomArticleList;
    }

    @Override
    public PageInfo<Article> getArticleListByTagId(Integer pageIndex, Integer pageSize, Integer tagId) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articleList = articleTagRefMapper.listArticleByTagId(tagId);
        return new PageInfo<>(articleList);
    }

    @Override
    public PageInfo<Article> getArticleListBycategoryId(Integer pageIndex, Integer pageSize, Integer categoryId) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articleList = articleCategoryRefMapper.listArticleBycategoryId(categoryId);
        return new PageInfo<>(articleList);
    }

    @Override
    public List<Article> listArticleWithoutContent() {
        List<Article> articleList = articleMapper.listArticleWithoutContent();
        return articleList;
    }

    private Article insertArticleCategoryRef(Article article) {
        // 为分类列表中的每个分类构建新的 ArticleCategoryRef 并插入
        List<Category> categoryList = article.getCategoryList();
        if (categoryList != null) {
            for (Category category : categoryList) {
                ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef();
                articleCategoryRef.setArticleId(article.getArticleId());
                articleCategoryRef.setCategoryId(category.getCategoryId());
                articleCategoryRefMapper.insert(articleCategoryRef);
            }
        } else {
            categoryList = new ArrayList<>();
            categoryList.add(Category.defaultCategory());
            article.setCategoryList(categoryList);
        }
        return article;
    }

}
