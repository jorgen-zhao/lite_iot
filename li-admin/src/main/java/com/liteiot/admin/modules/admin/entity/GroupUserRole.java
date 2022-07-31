package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 组织用户角色关系表
 *
 * @version 2021-12-23 16:14:38
 */
@Data
@Table(name = "group_user_role")
public class GroupUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 组织id
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;
}
