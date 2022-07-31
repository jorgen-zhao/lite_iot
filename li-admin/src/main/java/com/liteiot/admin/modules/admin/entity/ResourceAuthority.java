package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "base_resource_authority")
@Data
public class ResourceAuthority {
    @Id
    private Integer id;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "resource_id")
    private String resourceId;

    @Column(name = "resource_type")
    private String resourceType;
}