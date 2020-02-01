package com.ycw.ssm.blog.controller.admin;

import com.ycw.ssm.blog.entity.Page;
import com.ycw.ssm.blog.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author ycw
 */
@Controller
@RequestMapping("/admin/page")
public class BackPageController {

    @Autowired
    private IPageService pageService;

    @RequestMapping("")
    public ModelAndView index() {
        List<Page> pageList = pageService.listPage();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageList", pageList);
        modelAndView.setViewName("Admin/Page/index");

        return modelAndView;
    }
}
