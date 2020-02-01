package com.ycw.ssm.blog.controller.admin;

import com.ycw.ssm.blog.entity.Notice;
import com.ycw.ssm.blog.service.INoticeService;
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
@RequestMapping("/admin/notice")
public class BackNoticeController {

    @Autowired
    private INoticeService noticeService;

    @RequestMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();

        List<Notice> noticeList = noticeService.listNotice();
        modelAndView.addObject("noticeList", noticeList);
        modelAndView.setViewName("Admin/Notice/index");

        return modelAndView;
    }

    @RequestMapping("/edit/{noticeId}")
    public ModelAndView edit(@PathVariable(value = "noticeId") Integer noticeId) {
        ModelAndView modelAndView = new ModelAndView();

        Notice notice = noticeService.getNoticeById(noticeId);
        List<Notice> noticeList = noticeService.listNotice();
        modelAndView.addObject("notice", notice);
        modelAndView.addObject("noticeList", noticeList);

        modelAndView.setViewName("Admin/Notice/edit");

        return modelAndView;
    }

    @RequestMapping("/editSubmit")
    public String editSubmit(Notice notice) {
        noticeService.update(notice);
        return "redirect:/admin/notice";
    }

    @RequestMapping("/delete/{noticeId}")
    public String delete(@PathVariable(value = "noticeId") Integer noticeId) {
        noticeService.deleteNoticeById(noticeId);
        return "redirect:/admin/notice";
    }

    @RequestMapping("/insert")
    public String insert(){
        return "Admin/Notice/insert";
    }

    @RequestMapping("/insertSubmit")
    public String insertSubmit(Notice notice) {
        noticeService.insert(notice);
        return "redirect:/admin/notice";
    }
}
