package com.ycw.ssm.blog.controller.admin;

import com.ycw.ssm.blog.entity.Link;
import com.ycw.ssm.blog.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author ycw
 */
@Controller
@RequestMapping("/admin/link")
public class BackLinkController {

    @Autowired
    private ILinkService linkService;

    @RequestMapping("")
    public ModelAndView index() {

        List<Link> linkList = linkService.listLink(null);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("linkList", linkList);
        modelAndView.setViewName("Admin/Link/index");

        return modelAndView;
    }

    @RequestMapping("/edit/{editId}")
    public ModelAndView edit(@PathVariable(value = "editId") Integer editId) {
        Link link = linkService.getLinkById(editId);
        List<Link> linkList = linkService.listLink(null);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("linkCustom", link);
        modelAndView.addObject("linkList", linkList);
        modelAndView.setViewName("Admin/Link/edit");

        return modelAndView;
    }

    @RequestMapping("/insert")
    public ModelAndView insert() {
        List<Link> linkList = linkService.listLink(null);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("linkList", linkList);
        modelAndView.setViewName("Admin/Link/insert");

        return modelAndView;
    }

    @RequestMapping("/editSubmit")
    public String editSubmit(Link link) {
        linkService.update(link);
        return "redirect:/admin/link";
    }



    @RequestMapping("/insertSubmit")
    public String insert(Link link) {
        linkService.insert(link);
        return "redirect:/admin/link";
    }

    @RequestMapping("/delete/{linkId}")
    public String delete(@PathVariable(value = "linkId") Integer linkId) {
        linkService.deleteById(linkId);
        return "redirect:/admin/link";
    }
}
