package com.ycw.ssm.blog.service;

import com.ycw.ssm.blog.entity.Tag;

import java.util.List;

/**
 * @author ycw
 */
public interface ITagService {
    List<Tag> listTags();

    void saveTag(Tag tag);

    void deleteTagById(Integer tagId);

    Tag getTagById(Integer tagId);

    void update(Tag tag);

    Integer getTagCount();
}
