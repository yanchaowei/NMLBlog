package com.ycw.ssm.blog.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ycw
 */
@Data
public class CommentParm implements Serializable {

    private String commentAuthorName;

    private String commentAuthorEmail;

    private String commentAuthorUrl;

    private String commentContent;

    private Integer commentArticleId;

    private Integer commentPid;

    private String commentPname;

}
