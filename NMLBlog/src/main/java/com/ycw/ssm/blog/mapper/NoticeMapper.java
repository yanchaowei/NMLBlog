package com.ycw.ssm.blog.mapper;

import com.ycw.ssm.blog.entity.Notice;

import java.util.List;

/**
 * @author ycw
 */
public interface NoticeMapper {
    List<Notice> findAll();

    Notice getNoticeById(Integer noticeId);

    void update(Notice notice);

    void deleteByNoticeId(Integer noticeId);

    void insert(Notice notice);
}
