package com.ycw.ssm.blog.service.impl;

import com.ycw.ssm.blog.entity.Notice;
import com.ycw.ssm.blog.mapper.NoticeMapper;
import com.ycw.ssm.blog.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ycw
 */
@Service
public class NoticeServiceImpl implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> listNotice() {
        List<Notice> noticeList = noticeMapper.findAll();
        return noticeList;
    }

    @Override
    public Notice getNoticeById(Integer noticeId) {
        Notice notice = noticeMapper.getNoticeById(noticeId);
        return notice;
    }

    @Override
    public void update(Notice notice) {
        notice.setNoticeUpdateTime(new Date());
        noticeMapper.update(notice);
    }

    @Override
    public void deleteNoticeById(Integer noticeId) {
        noticeMapper.deleteByNoticeId(noticeId);
    }

    @Override
    public void insert(Notice notice) {
        notice.setNoticeCreateTime(new Date());
        notice.setNoticeUpdateTime(new Date());
        notice.setNoticeOrder(1);
        noticeMapper.insert(notice);
    }
}
