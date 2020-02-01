package com.ycw.ssm.blog.controller.admin;

import com.ycw.ssm.blog.entity.Menu;
import com.ycw.ssm.blog.service.IMenuService;
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
@RequestMapping("/admin/menu")
public class BackMenuController {

    @Autowired
    private IMenuService menuService;

    @RequestMapping("")
    public ModelAndView index() {
        List<Menu> menuList = menuService.listMenu();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("menuList", menuList);
        modelAndView.setViewName("Admin/Menu/index");

        return modelAndView;
    }

    @RequestMapping("/insertSubmit")
    public String insertSubmit(Menu menu) {
        menuService.insert(menu);
        return "redirect:/admin/menu";
    }

    @RequestMapping("/edit/{menuId}")
    public ModelAndView edit(@PathVariable(value = "menuId") Integer menuId) {
        Menu menu = menuService.getMenuById(menuId);
        List<Menu> menuList = menuService.listMenu();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("menu", menu);
        modelAndView.addObject("menuList", menuList);
        modelAndView.setViewName("Admin/Menu/edit");
        return modelAndView;
    }

    @RequestMapping("/editSubmit")
    public String editSubmit(Menu menu) {
        menuService.update(menu);
        return "redirect:/admin/menu";
    }
}
