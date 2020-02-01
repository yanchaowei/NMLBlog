package com.ycw.ssm.blog.service;

import com.ycw.ssm.blog.entity.Menu;

import java.util.List;

/**
 * @author ycw
 */
public interface IMenuService {
    List<Menu> listMenu();

    void insert(Menu menu);

    Menu getMenuById(Integer menuId);

    void update(Menu menu);
}
