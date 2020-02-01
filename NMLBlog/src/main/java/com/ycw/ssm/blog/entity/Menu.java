package com.ycw.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ycw
 */
@Data
public class Menu implements Serializable {

    private Integer menuId;

    private String menuName;

    private String menuUrl;

    private Integer menuLevel;

    private String menuIcon;

    private Integer menuOrder;

}
