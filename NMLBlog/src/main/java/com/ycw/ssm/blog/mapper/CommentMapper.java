package com.ycw.ssm.blog.mapper;

import com.ycw.ssm.blog.entity.Comment;

import java.util.List;

/**
 * @author ycw
 */
public interface CommentMapper {
    List<Comment> findCommentsByArticleId(Integer articleId);

    List<Comment> listComment();

    Comment getCommentById(Integer commentId);

    void update(Comment comment);

    void deleteById(Integer commentId);

    List<Comment> getRecentComments(Integer limit);

    Integer getCommentCount();

    void insert(Comment comment);
}
