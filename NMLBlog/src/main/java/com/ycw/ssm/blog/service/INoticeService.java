package com.ycw.ssm.blog.service;

import com.ycw.ssm.blog.entity.Notice;

import java.util.List;

/**
 * @author ycw
 */
public interface INoticeService {

    List<Notice> listNotice();

    Notice getNoticeById(Integer noticeId);

    void update(Notice notice);

    void deleteNoticeById(Integer noticeId);

    void insert(Notice notice);
}
