package com.ycw.ssm.blog.service;

import com.github.pagehelper.PageInfo;
import com.ycw.ssm.blog.entity.Comment;

import java.util.List;

/**
 * @author ycw
 */
public interface ICommentService {

    List<Comment> getCommentListByArticleId(Integer articleId);

    PageInfo<Comment> listComment(Integer pageIndex, Integer pageSize);

    Comment getCommentById(Integer commentId);

    void update(Comment comment);

    void deleteById(Integer commentId);

    List<Comment> getRecentComments(Integer limit);

    Integer getCommentCount();

    void insert(Comment comment);
}
