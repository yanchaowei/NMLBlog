package com.ycw.ssm.blog.service;

import com.ycw.ssm.blog.entity.Category;

import java.util.List;

/**
 * @author ycw
 */
public interface ICategoryService {
    List<Category> listCategory();

    void insertCategory(Category category);

    Category getCategoryById(Integer categoryId);

    void update(Category category);

    Integer getCategoryCount();
}
