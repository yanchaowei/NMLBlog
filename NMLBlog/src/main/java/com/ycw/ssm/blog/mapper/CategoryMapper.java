package com.ycw.ssm.blog.mapper;

import com.ycw.ssm.blog.entity.Category;

import java.util.List;

/**
 * @author ycw
 */
public interface CategoryMapper {
    List<Category> findAll();

    void insertCategory(Category category);

    Category getCategoryById(Integer categoryId);

    void update(Category category);

    Integer getCategoryCount();
}
