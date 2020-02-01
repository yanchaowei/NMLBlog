package com.ycw.ssm.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ycw.ssm.blog.dto.ArticleParm;
import com.ycw.ssm.blog.entity.Article;
import com.ycw.ssm.blog.entity.Category;
import com.ycw.ssm.blog.entity.Tag;
import com.ycw.ssm.blog.entity.User;
import com.ycw.ssm.blog.service.IArticleService;
import com.ycw.ssm.blog.service.ICategoryService;
import com.ycw.ssm.blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ycw
 */
@Controller
@RequestMapping("/admin/article")
public class BackArticleController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ITagService tagService;

    @RequestMapping("")     //文章板块的默认页面
    public ModelAndView index(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                              @RequestParam(value = "status", required = false) Integer status) {
        Map<String, Object> criteria = new HashMap<>(1);
        ModelAndView modelAndView = new ModelAndView();

        if (status != null) {
            criteria.put("status", status);
            modelAndView.addObject("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
        } else {
            modelAndView.addObject("pageUrlPrefix", "/admin/article?pageIndex");
        }

        PageInfo<Article> pageInfo = articleService.getArticleList(pageIndex, pageSize, criteria);

        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("Admin/Article/index");

        return modelAndView;
    }

    @RequestMapping("/insert")
    public ModelAndView insert() {
        List<Category> categoryList = categoryService.listCategory();
        List<Tag> tagList = tagService.listTags();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("tagList", tagList);
        modelAndView.setViewName("Admin/Article/insert");

        return modelAndView;
    }

    @RequestMapping("/insertSubmit")
    public String insertSubmit(HttpSession session, ArticleParm articleParm) {
        Article article = new Article();

        // 将 articleParm 数据绑定到 article
        article.setArticleTitle(articleParm.getArticleTitle());
        article.setArticleContent(articleParm.getArticleContent());
        article.setArticleStatus(articleParm.getArticleStatus());
        // 绑定分类列表
        List<Category> categoryList = new ArrayList<>();
        Category parentCategory = categoryService.getCategoryById(articleParm.getArticleParentCategoryId());
        categoryList.add(parentCategory);
        Category childCategory = categoryService.getCategoryById(articleParm.getArticleChildCategoryId());
        categoryList.add(childCategory);
        article.setCategoryList(categoryList);
        // 绑定标签列表
        List<Tag> tagList = new ArrayList<>();
        for (Integer tagId : articleParm.getArticleTagIds()) {
            Tag tag = tagService.getTagById(tagId);
            tagList.add(tag);
        }
        article.setTagList(tagList);

        // 绑定作者
        User user = (User) session.getAttribute("user");
        if (user != null) {
            article.setUser(user);
            article.setArticleUserId(user.getUserId());
        }
        articleService.insert(article);
        return "redirect:/admin/article";
    }

    @RequestMapping("/edit/{articleId}")
    public String edit(@PathVariable(value = "articleId")Integer articleId, Model model) {
        Article article = articleService.getArticleById(articleId);
        List<Category> categoryList = categoryService.listCategory();
        List<Tag> tagList = tagService.listTags();

        model.addAttribute("article", article);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("tagList", tagList);

        return "Admin/Article/edit";
    }

    @RequestMapping("/editSubmit")
    public String editSubmit(HttpSession session, ArticleParm articleParm) {
        Article article = articleService.getArticleById(articleParm.getArticleId());

        // 将 articleParm 数据绑定到 article
        article.setArticleTitle(articleParm.getArticleTitle());
        article.setArticleContent(articleParm.getArticleContent());
        article.setArticleStatus(articleParm.getArticleStatus());
        article.setArticleOrder(articleParm.getArticleOrder());
        // 绑定分类列表
        List<Category> categoryList = new ArrayList<>();
        Category parentCategory = categoryService.getCategoryById(articleParm.getArticleParentCategoryId());
        categoryList.add(parentCategory);
        Category childCategory = categoryService.getCategoryById(articleParm.getArticleChildCategoryId());
        categoryList.add(childCategory);
        article.setCategoryList(categoryList);
        // 绑定标签列表
        List<Tag> tagList = new ArrayList<>();
        for (Integer tagId : articleParm.getArticleTagIds()) {
            Tag tag = tagService.getTagById(tagId);
            tagList.add(tag);
        }
        article.setTagList(tagList);

        articleService.update(article);
        return "redirect:/admin/article";
    }

    @RequestMapping("/delete/{articleId}")
    public String delete(@PathVariable(value = "articleId") Integer articleId) {
        articleService.deleteById(articleId);
        return "redirect:/admin/article";
    }

}
