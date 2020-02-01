package com.ycw.ssm.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ycw.ssm.blog.entity.Article;
import com.ycw.ssm.blog.entity.Comment;
import com.ycw.ssm.blog.mapper.ArticleMapper;
import com.ycw.ssm.blog.mapper.CommentMapper;
import com.ycw.ssm.blog.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ycw
 */
@Service
@Slf4j
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public List<Comment> getCommentListByArticleId(Integer articleId) {
        List<Comment> commentList = commentMapper.findCommentsByArticleId(articleId);
        return commentList;
    }

    @Override
    public PageInfo<Comment> listComment(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);

        List<Comment> commentList = commentMapper.listComment();

        // 为评论绑定文章
        for (Comment comment : commentList) {
            Article article = articleMapper.selectByArticleId(comment.getCommentArticleId());
            comment.setArticle(article);
        }

        return new PageInfo<>(commentList);
    }

    @Override
    public Comment getCommentById(Integer commentId) {
        Comment comment = commentMapper.getCommentById(commentId);
        return comment;
    }

    @Override
    public void update(Comment comment) {
        commentMapper.update(comment);
    }

    @Override
    public void deleteById(Integer commentId) {
        commentMapper.deleteById(commentId);
    }

    @Override
    public List<Comment> getRecentComments(Integer limit) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.getRecentComments(limit);

            // 为评论绑定文章
            for (Comment comment : commentList) {
                Article article = articleMapper.selectByArticleId(comment.getCommentArticleId());
                comment.setArticle(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取最新评论列表失败：limit={}, cause:{}",limit, e);
        }

        return commentList;
    }

    @Override
    public Integer getCommentCount() {
        Integer commentCount = commentMapper.getCommentCount();
        return commentCount;
    }

    @Override
    public void insert(Comment comment) {
        commentMapper.insert(comment);
    }
}
