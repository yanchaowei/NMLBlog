package com.ycw.ssm.blog.mapper;

import com.ycw.ssm.blog.entity.Tag;

import java.util.List;

/**
 * @author ycw
 */
public interface TagMapper {

    /**
     * 查找所有标签
     * @return
     */
    List<Tag> findAll();

    /**
     * 保存一个标签
     * @param tag
     */
    void insertTag(Tag tag);

    void deleteTagById(Integer tagId);

    Tag findById(Integer tagId);

    void update(Tag tag);

    Integer getTagCount();
}
