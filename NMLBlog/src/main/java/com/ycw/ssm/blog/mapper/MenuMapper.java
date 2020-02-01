package com.ycw.ssm.blog.mapper;

import com.ycw.ssm.blog.entity.Menu;

import java.util.List;

/**
 * @author ycw
 */
public interface MenuMapper {
    List<Menu> listMenu();

    void insert(Menu menu);

    Menu getMenuById(Integer menuId);

    void update(Menu menu);
}
