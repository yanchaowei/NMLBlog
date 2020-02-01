package com.ycw.ssm.blog.controller.home;

import com.github.pagehelper.PageInfo;
import com.ycw.ssm.blog.entity.*;
import com.ycw.ssm.blog.enums.LinkStatus;
import com.ycw.ssm.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * @author ycw
 */
@Controller
public class IndexController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private ILinkService linkService;

    @Autowired
    private IOptionsService optionsService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ITagService tagService;

    @RequestMapping(value = {"/", "article"})
    public String index(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "status", required = false) Integer status,
                        Model model) {
        Map<String, Object> criteria = new HashMap<>();
        // 返回分页 uri
        if (status != null) {
            criteria.put("status", status);
            model.addAttribute("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
        } else {
            model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex");
        }
        // pageInfo:文章列表
        PageInfo<Article> pageInfo = articleService.getArticleList(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", pageInfo);
        // noticeList：公告列表
        List<Notice> noticeList = noticeService.listNotice();
        model.addAttribute("noticeList", noticeList);
        // 返回当前时间
        model.addAttribute("nowDate", new Date());
        // linkList: 链接列表
        List<Link> linkList = linkService.listLink(LinkStatus.NORMAL.getValue());
        model.addAttribute("linkList", linkList);

        // lastUpdateArticle：最近更新时间
        Article lastUpdateArticle = null;
        List<Article> articleList = articleService.listLatestArticle();
        if (articleList != null) {
            lastUpdateArticle = articleList.get(0);
        }
        model.addAttribute("lastUpdateArticle", lastUpdateArticle);

        // recentCommentList: 最近评论
        List<Comment> recentCommentList = commentService.getRecentComments(5);
        model.addAttribute("recentCommentList", recentCommentList);

        return "Home/index";
    }

}
