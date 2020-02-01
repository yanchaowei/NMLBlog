package com.ycw.ssm.blog.controller.home;

import com.alibaba.druid.support.json.JSONUtils;
import com.ycw.ssm.blog.entity.Article;
import com.ycw.ssm.blog.entity.Comment;
import com.ycw.ssm.blog.enums.ArticleStatus;
import com.ycw.ssm.blog.service.IArticleService;
import com.ycw.ssm.blog.service.ICommentService;
import com.ycw.ssm.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author ycw
 */
@Controller
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/article/{articleId}")
    public ModelAndView articleDetail(@PathVariable(value = "articleId") Integer articleId) {

        // article, commentList, similarArticleList, mostViewArticleList, preArticle, afterArticle, mostCommentArticleList, randomArticleList
        // 需要为文章绑定 作者，分类，标签，相似文章，猜你喜欢
        ModelAndView modelAndView = new ModelAndView();

        // article
        Article article = articleService.getArticleInfoByArticleId(ArticleStatus.PUBLISH.getValue(), articleId);
        if (article == null) {
            modelAndView.setViewName("Admin/Home/Error/404");
            return modelAndView;
        }

        // similarArticleList: 相似文章
        List<Integer> categoryIds = articleService.categoryIdsByArticleId(articleId);
        List<Article> similarArticleList = articleService.listArticleByCategoryIds(categoryIds, 5);

        // mostViewArticleList: 猜你喜欢
        List<Article> mostViewArticleList = articleService.listArticleByViewCount(5);

        // 上一篇文章
        Article preArticle = articleService.getPreArticle(articleId);

        // 下一篇文章
        Article afterArticle = articleService.getAfterArticle(articleId);

        // 评论列表
        List<Comment> commentList = commentService.getCommentListByArticleId(articleId);

        // mostCommentArticleList：热评文章
        List<Article> mostCommentArticleList = articleService.listArticlesByCommentCount(8);

        // randomArticleList：随机文章
        List<Article> randomArticleList = articleService.getRandomArticleList(5);

        modelAndView.addObject("article", article);
        modelAndView.addObject("similarArticleList", similarArticleList);
        modelAndView.addObject("mostViewArticleList", mostViewArticleList);
        modelAndView.addObject("preArticle", preArticle);
        modelAndView.addObject("afterArticle", afterArticle);
        modelAndView.addObject("commentList", commentList);
        modelAndView.addObject("mostCommentArticleList", mostCommentArticleList);
        modelAndView.addObject("randomArticleList", randomArticleList);
        modelAndView.setViewName("Home/Page/articleDetail");

        return modelAndView;
    }

    /**
     * @RequestMapping(value = "/article/{articleId}", method = RequestMethod.POST)
     * public String commentSubmit(HttpServletRequest request, CommentParm commentParm) {
     * // 验证用户
     * User user = (User) request.getSession().getAttribute("user");
     * if (user == null) {
     * // 根据 commentParme 查询数据库，验证用户的合法性
     * boolean isValidUser = checkUserByCommentParm(commentParm);
     * }
     * <p>
     * // 插入评论记录
     * commentService.insert(comment);
     * // 文章评论数加 1
     * <p>
     * //
     * return "redirect:/article/";
     * }
     * <p>
     * private boolean checkUserByCommentParm(CommentParm commentParm) {
     * User user = userService.getUserByEmail(commentParm.getCommentAuthorEmail());
     * if ()
     * }
     */

    @RequestMapping("/article/view/{articleId}")
    @ResponseBody
    public String view(@PathVariable(value = "articleId") Integer articleId) {
        // 浏览量加 1
        Article article = articleService.getArticleById(articleId);
        article.setArticleViewCount(article.getArticleViewCount() + 1);
        articleService.update(article);
        Integer viewCount = article.getArticleViewCount();
        String jsonString = JSONUtils.toJSONString(viewCount);
        return jsonString;
    }

    @RequestMapping(value = "/article/like/{articleId}", method = RequestMethod.POST)
    @ResponseBody
    public String increaseLikeCount(@PathVariable(value = "articleId") Integer articleId) {
        // likeCount加 1
        Article article = articleService.getArticleById(articleId);
        article.setArticleLikeCount(article.getArticleLikeCount() + 1);
        articleService.update(article);
        Integer likeCount = article.getArticleLikeCount();
        String jsonString = JSONUtils.toJSONString(likeCount);
        return jsonString;
    }


}
