package com.liteiot.common.vo;

import lombok.Data;

import java.util.Date;

/**
 * Class:  GroupUser
 * <p>
 * Author: zhaoyg
 * Date:   2021/8/11 16:44
 * Desc:   GroupUser
 */
@Data
public class GroupUser {
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String status;

    private String groupId;

    private String groupName;

    private String mobilePhone;

    private String description;

    private Date crtTime;

    private String crtUser;

    private String crtName;

    private String crtHost;

    private Date updTime;

    private String updUser;

    private String updName;

    private String updHost;

    /**
     * 用于展示原先的groupName
     */
    private String belongingGroupName;

    /**
     * 用于传递修改前的所属groupId
     */
    private String belongingGroupId;

    /**
     * 用于传递修改后的所属groupId
     */
    private String newBelongingGroupId;
}