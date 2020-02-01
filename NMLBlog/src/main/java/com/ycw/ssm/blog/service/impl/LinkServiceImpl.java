package com.ycw.ssm.blog.service.impl;

import com.ycw.ssm.blog.entity.Link;
import com.ycw.ssm.blog.mapper.LinkMapper;
import com.ycw.ssm.blog.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ycw
 */
@Service
public class LinkServiceImpl implements ILinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public List<Link> listLink(Integer linkStatus) {
        List<Link> linkList = linkMapper.findAll(linkStatus);
        return linkList;
    }

    @Override
    public Link getLinkById(Integer editId) {
        Link link = linkMapper.getLinkById(editId);
        return link;
    }

    @Override
    public void update(Link link) {
        link.setLinkUpdateTime(new Date());
        linkMapper.update(link);
    }

    @Override
    public void insert(Link link) {
        link.setLinkStatus(0);
        link.setLinkOrder(1);
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        linkMapper.insertLink(link);
    }

    @Override
    public void deleteById(Integer linkId) {
        linkMapper.deleteById(linkId);
    }

    @Override
    public Integer getLinkCount() {
        Integer linkCount = linkMapper.getLinkCount();
        return linkCount;
    }
}
