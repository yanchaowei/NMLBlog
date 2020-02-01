package com.ycw.ssm.blog.mapper;

import com.ycw.ssm.blog.entity.Link;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ycw
 */
public interface LinkMapper {
    List<Link> findAll(@Param("linkStatus") Integer linkStatus);

    Link getLinkById(Integer editId);

    void update(Link link);

    void insertLink(Link link);

    void deleteById(Integer linkId);

    Integer getLinkCount();
}
