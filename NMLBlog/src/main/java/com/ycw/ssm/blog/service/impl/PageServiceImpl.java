package com.ycw.ssm.blog.service.impl;

import com.ycw.ssm.blog.entity.Page;
import com.ycw.ssm.blog.mapper.PageMapper;
import com.ycw.ssm.blog.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ycw
 */
@Service
public class PageServiceImpl implements IPageService {

    @Autowired
    private PageMapper pageMapper;

    @Override
    public List<Page> listPage() {
        List<Page> pageList = pageMapper.listPage();
        return pageList;
    }
}
