package com.ycw.ssm.blog.service.impl;

import com.ycw.ssm.blog.entity.Tag;
import com.ycw.ssm.blog.mapper.ArticleTagRefMapper;
import com.ycw.ssm.blog.mapper.TagMapper;
import com.ycw.ssm.blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ycw
 */
@Service
public class TagServiceImpl implements ITagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagRefMapper articleTagRefMapper;

    @Override
    public List<Tag> listTags() {
        List<Tag> tagList = tagMapper.findAll();

        //为标签绑定文章数
        for (Tag tag : tagList) {
            Integer articleCount = articleTagRefMapper.findArticleCountByTagId(tag.getTagId());
            tag.setArticleCount(articleCount);
        }
        return tagList;
    }

    @Override
    public void saveTag(Tag tag) {
        tagMapper.insertTag(tag);
    }

    @Override
    public void deleteTagById(Integer tagId) {
        tagMapper.deleteTagById(tagId);
    }

    @Override
    public Tag getTagById(Integer tagId) {
        Tag tag = tagMapper.findById(tagId);
        return tag;
    }

    @Override
    public void update(Tag tag) {
        tagMapper.update(tag);
    }

    @Override
    public Integer getTagCount() {
        Integer tagCount = tagMapper.getTagCount();
        return tagCount;
    }
}
