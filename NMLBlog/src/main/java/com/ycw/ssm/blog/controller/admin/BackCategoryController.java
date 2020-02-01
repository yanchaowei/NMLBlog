package com.ycw.ssm.blog.controller.admin;

import com.ycw.ssm.blog.entity.Category;
import com.ycw.ssm.blog.service.ICategoryService;
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
@RequestMapping("/admin/category")
public class BackCategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("")
    public ModelAndView index() {
        List<Category> categoryList = categoryService.listCategory();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.setViewName("Admin/Category/index");
        return modelAndView;
    }

    @RequestMapping("/insertSubmit")
    public String insertSubmit(Category category) {
        categoryService.insertCategory(category);
        return "redirect:/admin/category";
    }

    @RequestMapping("/edit/{categoryId}")
    public ModelAndView edit(@PathVariable(value = "categoryId") Integer categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        List<Category> categoryList = categoryService.listCategory();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("category", category);
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.setViewName("Admin/Category/edit");

        return modelAndView;
    }

    @RequestMapping("/editSubmit")
    public String editSubmit(Category category) {
        categoryService.update(category);
        return "redirect:/admin/category";
    }
}
