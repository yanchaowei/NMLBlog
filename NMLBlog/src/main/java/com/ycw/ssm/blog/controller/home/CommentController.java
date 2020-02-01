package com.ycw.ssm.blog.controller.home;

import cn.hutool.http.HtmlUtil;
import com.ycw.ssm.blog.dto.JsonResult;
import com.ycw.ssm.blog.entity.Article;
import com.ycw.ssm.blog.entity.Comment;
import com.ycw.ssm.blog.entity.User;
import com.ycw.ssm.blog.enums.Role;
import com.ycw.ssm.blog.service.IArticleService;
import com.ycw.ssm.blog.service.ICommentService;
import com.ycw.ssm.blog.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author ycw
 */
@Controller
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IArticleService articleService;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult conmentSubmit(HttpServletRequest request, Comment comment) {
        comment.setCommentCreateTime(new Date());
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            comment.setCommentRole(Role.VISITOR.getValue());
        } else {
            comment.setCommentRole(Role.ADMIN.getValue());
            // 若用户已登录，则相关数据需要从 user 中获取
            comment.setCommentAuthorName(user.getUserNickname());
            comment.setCommentAuthorEmail(user.getUserEmail());
            comment.setCommentAuthorUrl(user.getUserUrl());
        }
        comment.setCommentIp(MyUtil.getIpAddr(request));

        // 过滤字符防止 xss 攻击
        comment.setCommentAuthorName(HtmlUtil.escape(comment.getCommentAuthorName()));
        comment.setCommentAuthorUrl(HtmlUtil.escape(comment.getCommentAuthorUrl()));
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));
        comment.setCommentAuthorEmail(HtmlUtil.escape(comment.getCommentAuthorEmail()));

        comment.setCommentAuthorAvatar(MyUtil.getGravatar(comment.getCommentAuthorEmail()));
        try {
            commentService.insert(comment);
            // 增加文章评论数
            Article article = articleService.getArticleById(comment.getCommentArticleId());
            article.setArticleCommentCount(article.getArticleCommentCount() + 1);
            articleService.update(article);

        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult<>().fail();
        }
        return new JsonResult().ok();
    }
}
