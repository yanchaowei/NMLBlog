package com.ycw.ssm.blog.controller.home;

import com.ycw.ssm.blog.entity.Article;
import com.ycw.ssm.blog.entity.Category;
import com.ycw.ssm.blog.entity.Tag;
import com.ycw.ssm.blog.service.IArticleService;
import com.ycw.ssm.blog.service.ICategoryService;
import com.ycw.ssm.blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author ycw
 */
@Controller
public class PageController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ITagService tagService;

    @RequestMapping("/articleFile")
    public String articleFile(Model model) {
        // 文章列表，数量过多，导致网络负载过大，无需查询文章内容
        List<Article> articleList = articleService.listArticleWithoutContent();
        model.addAttribute("articleList", articleList);

        // mostCommentArticleList：热评文章
        List<Article> mostCommentArticleList = articleService.listArticlesByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);

        return "Home/Page/articleFile";
    }

    @RequestMapping("/map")
    public String map(Model model) {
        List<Article> articleList = articleService.listArticleWithoutContent();
        model.addAttribute("articleList", articleList);

        // categoryList
        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);

        // mostCommentArticleList：热评文章
        List<Article> mostCommentArticleList = articleService.listArticlesByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);

        // tagList
        List<Tag> tagList = tagService.listTags();
        model.addAttribute("tagList", tagList);

        return "Home/Page/map";
    }

    /**
     * 留言板
     *
     * @return
     */
    @RequestMapping(value = "/message")
    public String message(Model model) {

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticlesByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/message";
    }
}
