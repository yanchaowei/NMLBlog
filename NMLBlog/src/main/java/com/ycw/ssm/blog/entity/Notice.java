package com.ycw.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ycw
 */
@Data
public class Notice implements Serializable {

    private Integer noticeId;

    private String noticeTitle;

    private String noticeContent;

    private Date noticeCreateTime;

    private Date noticeUpdateTime;

    private String noticeStatus;

    private Integer noticeOrder;

}
