package com.ycw.ssm.blog.controller.home;

import com.ycw.ssm.blog.entity.Article;
import com.ycw.ssm.blog.entity.Notice;
import com.ycw.ssm.blog.service.IArticleService;
import com.ycw.ssm.blog.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author ycw
 */
@Controller
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private IArticleService articleService;

    @RequestMapping("/notice/{noticeId}")
    public String notice(@PathVariable(value = "noticeId") Integer noticeId, Model model) {
        // notice
        Notice notice = noticeService.getNoticeById(noticeId);
        model.addAttribute("notice", notice);

        // mostCommentArticleList:热评文章
        List<Article> mostCommentArticleList = articleService.listArticlesByCommentCount(6);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);

        return "Home/Page/noticeDetail";
    }
}
