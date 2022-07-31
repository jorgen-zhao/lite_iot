package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "base_user_role")
@Data
public class UserRole {
    @Id
    private Integer id;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "user_id")
    private String userId;
}