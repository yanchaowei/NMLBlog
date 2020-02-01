package com.ycw.ssm.blog.interceptor;

import com.ycw.ssm.blog.entity.Category;
import com.ycw.ssm.blog.entity.Menu;
import com.ycw.ssm.blog.entity.Options;
import com.ycw.ssm.blog.entity.Tag;
import com.ycw.ssm.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ycw
 */
@Component
public class HomeResourceInterceptor implements HandlerInterceptor {

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

    @Autowired
    private IMenuService menuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 博客顶部部分
        // menuList：菜单列表
        List<Menu> menuList = menuService.listMenu();
        request.setAttribute("menuList", menuList);

        // allCategoryList: 所有分类列表
        List<Category> allCategoryList = categoryService.listCategory();
        request.setAttribute("allCategoryList", allCategoryList);

        // siteBasicStatistics：侧边基本统计信息
        List<Integer> siteBasicStatistics = new ArrayList<>();
        // 文章数量
        Integer articleCount = articleService.getArticleCount();
        siteBasicStatistics.add(articleCount);
        // 留言数量
        Integer commentCount = commentService.getCommentCount();
        siteBasicStatistics.add(commentCount);
        // 分类数量
        Integer categoryCount = categoryService.getCategoryCount();
        siteBasicStatistics.add(categoryCount);
        // 标签数量
        Integer tagCount = tagService.getTagCount();
        siteBasicStatistics.add(tagCount);
        // 链接数量
        Integer linkCount = linkService.getLinkCount();
        siteBasicStatistics.add(linkCount);
        // 浏览数量
        Integer viewTotal = articleService.getViewTotal();
        siteBasicStatistics.add(viewTotal);
        request.setAttribute("siteBasicStatistics", siteBasicStatistics);

        // options: 选项列表
        Options options = optionsService.getOptions();
        request.setAttribute("options", options);

        // 侧边栏显示
        // allTagList: 所有标签列表
        List<Tag> allTagList = tagService.listTags();
        request.setAttribute("allTagList", allTagList);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
