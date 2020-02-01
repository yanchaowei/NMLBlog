package com.ycw.ssm.blog.controller.admin;

import com.ycw.ssm.blog.entity.Tag;
import com.ycw.ssm.blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author ycw
 */
@Controller
@RequestMapping("/admin/tag")
public class BackTagController {

    @Autowired
    private ITagService tagService;

    /**
     * 标签模块首页
     *
     * @return
     */
    @RequestMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();

        List<Tag> tagList = tagService.listTags();
        modelAndView.addObject("tagList", tagList);
        modelAndView.setViewName("Admin/Tag/index");

        return modelAndView;
    }

    @RequestMapping("/insertSubmit")
    public String insertSubmit(Tag tag) {
        tagService.saveTag(tag);
        return "redirect:/admin/tag";
    }

    @RequestMapping("/delete/{tagId}")
    public String delete(@PathVariable(value = "tagId") Integer tagId) {
        tagService.deleteTagById(tagId);
        return "redirect:/admin/tag";
    }

    @RequestMapping("/edit/{tagId}")
    public String edit(@PathVariable(value = "tagId") Integer tagId, Model model) {
        Tag tag = tagService.getTagById(tagId);
        List<Tag> tagList = tagService.listTags();
        model.addAttribute("tag", tag);
        model.addAttribute("tagList", tagList);
        return "Admin/Tag/edit";
    }

    @RequestMapping("/editSubmit")
    public String editSubmit(Tag tag) {
        tagService.update(tag);
        return "redirect:/admin/tag";
    }
}
