package com.ycw.ssm.blog.service.impl;

import com.ycw.ssm.blog.entity.Options;
import com.ycw.ssm.blog.mapper.OptionsMapper;
import com.ycw.ssm.blog.service.IOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ycw
 */
@Service
public class OptionsServiceImpl implements IOptionsService {

    @Autowired
    private OptionsMapper optionsMapper;

    @Override
    public Options getOptions() {
        Options options = optionsMapper.getOptions();
        return options;
    }
}
