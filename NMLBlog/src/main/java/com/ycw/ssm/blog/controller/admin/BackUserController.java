package com.ycw.ssm.blog.controller.admin;

import com.alibaba.druid.support.json.JSONUtils;
import com.ycw.ssm.blog.entity.User;
import com.ycw.ssm.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ycw
 */
@Controller
@RequestMapping(path = "/admin/user")
public class BackUserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("")
    public ModelAndView userList() {

        List<User> userList = userService.listUser();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("Admin/User/index");
        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        if (id != null) {
            userService.delete(id);
        }
        return "redirect:/admin/user";
    }

    @RequestMapping("/insert")
    public String insert() {
        return "Admin/User/insert";
    }

    @RequestMapping("insertSubmit")
    public String insertSubmit(User user) {
        User user1 = userService.getUserByUserName(user.getUserName());
        User user2 = userService.getUserByEmail(user.getUserEmail());
        if (user1 == null && user2 == null) {
            userService.insertUser(user);
        }
        return "redirect:/admin/user";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUserByUserId(id);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("Admin/User/edit");
        return modelAndView;
    }

    @RequestMapping("/editSubmit")
    public String editSubmit(User user) {
        userService.updateUser(user);
        return "redirect:/admin/user";
    }

    @RequestMapping("/profile/{id}")
    public ModelAndView profile(@PathVariable Integer id) {
        User user = userService.getUserByUserId(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("Admin/User/profile");

        return modelAndView;
    }

    @RequestMapping(value = "checkUserEmail", method = RequestMethod.POST)
    @ResponseBody
    private String checkUserEmail(HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();
        String userEmail = request.getParameter("email");
        Integer id = Integer.valueOf(request.getParameter("id"));
        User user = userService.getUserByEmail(userEmail);
        if (user != null) {
            if (id != null && user.getUserId().equals(id)) {
                result.put("code", 0);
                result.put("msg", "");
            } else {
                result.put("code", 1);
                result.put("msg", "电子邮箱已存在！");
            }
        } else {
            result.put("code", 0);
            result.put("msg", "");
        }

        String jsonString = JSONUtils.toJSONString(result);
        return jsonString;
    }

    @RequestMapping(value = "checkUserName", method = RequestMethod.POST)
    @ResponseBody
    private String checkUserName(HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();
        String username = request.getParameter("username");
        Integer id = Integer.valueOf(request.getParameter("id"));
        User user = userService.getUserByUserName(username);
        if (user != null) {
            if (id != null && user.getUserId().equals(id)) {
                result.put("code", 0);
                result.put("msg", "");
            } else {
                result.put("code", 1);
                result.put("msg", "用户名已存在！");
            }
        } else {
            result.put("code", 0);
            result.put("msg", "");
        }

        String jsonString = JSONUtils.toJSONString(result);
        return jsonString;
    }
}
