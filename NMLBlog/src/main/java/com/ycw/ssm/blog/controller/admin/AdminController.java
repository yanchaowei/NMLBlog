package com.ycw.ssm.blog.controller.admin;

import com.ycw.ssm.blog.entity.Article;
import com.ycw.ssm.blog.entity.Comment;
import com.ycw.ssm.blog.entity.User;
import com.ycw.ssm.blog.service.IArticleService;
import com.ycw.ssm.blog.service.ICommentService;
import com.ycw.ssm.blog.service.IUserService;
import com.ycw.ssm.blog.utils.MyUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author ycw
 */
@Controller
public class AdminController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ICommentService commentService;

    /**
     * 进入后台首页
     * @return
     */
    @RequestMapping("/admin")
    public String index(Model model) {
        List<Article> articleList = articleService.listLatestArticle();
        List<Comment> commentList = commentService.getRecentComments(5);
        model.addAttribute("articleList", articleList);
        model.addAttribute("commentList", commentList);
        return "Admin/index";
    }

    /**
     * 进入登陆页面
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "Admin/login";
    }

    /**
     * 登陆验证
     * @return
     */
    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) {

        HashMap<String, Object> map = new HashMap<>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.getUserByUserNameOrEmail(username);
        if (user == null) {
            map.put("code", 0);
            map.put("msg", "用户名无效");
        } else if (!user.getUserPass().equals(password)) {
            map.put("code", 0);
            map.put("msg", "密码错误");
        } else {
            map.put("code", "1");
            map.put("msg", "");
            request.getSession().setAttribute("user", user);
            String rememberme = request.getParameter("rememberme");
            // 增加 cookie 记住 用户名和密码
            if (rememberme != null) {
                Cookie cookie1 = new Cookie("username", username);
                cookie1.setMaxAge(60 * 60 * 24 * 3);
                Cookie cookie2 = new Cookie("password", password);
                cookie2.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            // 记录最近一次登陆时间和 ip
            user.setUserLastLoginTime(new Date());
            user.setUserLastLoginIp(MyUtil.getIpAddr(request));
            userService.updateUser(user);
        }
        String jsonStr = new JSONObject(map).toString();
        return jsonStr;
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @RequestMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "Admin/login";
    }
}
