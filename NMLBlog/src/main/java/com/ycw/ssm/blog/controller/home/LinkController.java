package com.ycw.ssm.blog.controller.home;

import com.ycw.ssm.blog.entity.Link;
import com.ycw.ssm.blog.entity.Options;
import com.ycw.ssm.blog.entity.User;
import com.ycw.ssm.blog.service.ILinkService;
import com.ycw.ssm.blog.service.IOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ycw
 */
@Controller
public class LinkController {

    @Autowired
    private IOptionsService optionsService;

    @Autowired
    private ILinkService linkService;

    @RequestMapping(value = "/applyLink")
    public String applyLink(Model model) {
        Options options = optionsService.getOptions();
        model.addAttribute("options", options);
        return "Home/Page/applyLink";
    }

    @RequestMapping(value = "/applyLinkSubmit", method = RequestMethod.POST)
    @ResponseBody
    public String applyLinkSubmit(HttpServletRequest request, Link link) {
        User user = (User) request.getSession().getAttribute("user");
        link.setLinkOwnerNickname(user.getUserNickname());
        linkService.insert(link);
        return null;
    }
}
