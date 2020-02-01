package com.ycw.ssm.blog.controller.admin;

import com.ycw.ssm.blog.entity.Options;
import com.ycw.ssm.blog.service.IOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ycw
 */
@Controller
@RequestMapping("/admin/options")
public class BackOptionsController {

    @Autowired
    private IOptionsService optionsService;

    @RequestMapping("")
    public ModelAndView index() {
        Options option = optionsService.getOptions();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("option", option);
        modelAndView.setViewName("Admin/Options/index");
        return modelAndView;
    }

}
