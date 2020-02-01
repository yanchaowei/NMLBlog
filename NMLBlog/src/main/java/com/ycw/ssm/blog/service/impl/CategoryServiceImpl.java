package com.ycw.ssm.blog.service.impl;

import com.ycw.ssm.blog.entity.Category;
import com.ycw.ssm.blog.mapper.ArticleCategoryRefMapper;
import com.ycw.ssm.blog.mapper.CategoryMapper;
import com.ycw.ssm.blog.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ycw
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Override
    public List<Category> listCategory() {
        List<Category> categoryList = categoryMapper.findAll();

        // 为每个分类绑定文章数
        if (categoryList == null) {
            for (Category category : categoryList) {
                Integer articleCount = articleCategoryRefMapper.getArticleCountByCategoryId(category.getCategoryId());
                category.setArticleCount(articleCount);
            }
        }
        return categoryList;
    }

    @Override
    public void insertCategory(Category category) {
        categoryMapper.insertCategory(category);
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        Category category = categoryMapper.getCategoryById(categoryId);
        return category;
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public Integer getCategoryCount() {
        Integer categoryCount = categoryMapper.getCategoryCount();
        return categoryCount;
    }
}
