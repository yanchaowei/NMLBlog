package com.ycw.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ycw
 */
@Data
public class Link implements Serializable {

    private Integer linkId;

    private String linkUrl;

    private String linkName;

    private String linkImage;

    private String linkDescription;

    private String linkOwnerNickname;

    private String linkOwnerContact;

    private Date linkUpdateTime;

    private Date linkCreateTime;

    private Integer linkOrder;

    private Integer linkStatus;

}
