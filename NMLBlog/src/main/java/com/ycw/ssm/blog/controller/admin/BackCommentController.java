package com.ycw.ssm.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ycw.ssm.blog.entity.Comment;
import com.ycw.ssm.blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ycw
 */
@Controller
@RequestMapping("/admin/comment")
public class BackCommentController {

    @Autowired
    private ICommentService commentService;

    @RequestMapping("")
    public ModelAndView index(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageInfo<Comment> pageInfo = commentService.listComment(pageIndex, pageSize);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("Admin/Comment/index");

        return modelAndView;
    }

    /**
     * 回复，这里需要登陆后才能回复
     *
     * @param commentId
     * @param request
     * @return
     */
    @RequestMapping("/reply/{commentId}")
    public ModelAndView reply(@PathVariable(value = "commentId") Integer commentId, HttpServletRequest request) {
        Comment comment = commentService.getCommentById(commentId);
//        User user = (User) request.getSession().getAttribute("user");
//        HttpSession sessionScope = request.getSession();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("comment", comment);
//        modelAndView.addObject("sessionScope", sessionScope);
//        modelAndView.addObject("user", user);
        modelAndView.setViewName("Admin/Comment/reply");

        return modelAndView;
    }

    @RequestMapping("/edit/{commentId}")
    public ModelAndView edit(@PathVariable(value = "commentId") Integer commentId) {
        Comment comment = commentService.getCommentById(commentId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("comment", comment);
        modelAndView.setViewName("Admin/Comment/edit");

        return modelAndView;
    }

    @RequestMapping("/editSubmit")
    public String editSubmit(Comment comment) {
        commentService.update(comment);
        return "redirect:/admin/comment";
    }

    @RequestMapping("/delete/{commentId}")
    public String delete(@PathVariable(value = "commentId") Integer commentId) {
        commentService.deleteById(commentId);
        return "redirect:/admin/comment";
    }
}
