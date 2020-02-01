package com.ycw.ssm.blog.controller.home;

import com.github.pagehelper.PageInfo;
import com.ycw.ssm.blog.entity.Article;
import com.ycw.ssm.blog.entity.Category;
import com.ycw.ssm.blog.service.IArticleService;
import com.ycw.ssm.blog.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @author ycw
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("/{categoryId}")
    public String articleBytag(@PathVariable(value = "categoryId") Integer categoryId,
                               @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                               Model model) {

        // category, pageInfo, nowDate,
        // category
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", category);
        // 返回分页 uri
        model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex");
        // pageInfo:文章列表
        PageInfo<Article> pageInfo = articleService.getArticleListBycategoryId(pageIndex, pageSize, categoryId);
        model.addAttribute("pageInfo", pageInfo);
        // 返回当前时间
        model.addAttribute("nowDate", new Date());

        // lastUpdateArticle：最近更新时间
        Article lastUpdateArticle = null;
        List<Article> articleList = articleService.listLatestArticle();
        if (articleList != null) {
            lastUpdateArticle = articleList.get(0);
        }
        model.addAttribute("lastUpdateArticle", lastUpdateArticle);

        // mostCommentArticleList：热评文章
        List<Article> mostCommentArticleList = articleService.listArticlesByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);

        // randomArticleList：随机文章
        List<Article> randomArticleList = articleService.getRandomArticleList(5);
        model.addAttribute("randomArticleList", randomArticleList);

        return "Home/Page/articleListByCategory";
    }
}
