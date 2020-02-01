package com.ycw.ssm.blog.service.impl;

import com.ycw.ssm.blog.entity.Menu;
import com.ycw.ssm.blog.mapper.MenuMapper;
import com.ycw.ssm.blog.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ycw
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> listMenu() {
        List<Menu> menuList = menuMapper.listMenu();
        return menuList;
    }

    @Override
    public void insert(Menu menu) {
        menu.setMenuOrder(1);
        menuMapper.insert(menu);
    }

    @Override
    public Menu getMenuById(Integer menuId) {
        Menu menu = menuMapper.getMenuById(menuId);
        return menu;
    }

    @Override
    public void update(Menu menu) {
        menuMapper.update(menu);
    }
}
