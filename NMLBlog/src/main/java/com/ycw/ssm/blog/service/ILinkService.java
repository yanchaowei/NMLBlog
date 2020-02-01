package com.ycw.ssm.blog.service;

import com.ycw.ssm.blog.entity.Link;

import java.util.List;

/**
 * @author ycw
 */
public interface ILinkService {
    List<Link> listLink(Integer linkStatus);

    Link getLinkById(Integer editId);

    void update(Link link);

    void insert(Link link);

    void deleteById(Integer linkId);

    Integer getLinkCount();
}
