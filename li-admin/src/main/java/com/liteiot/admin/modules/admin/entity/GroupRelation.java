package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 组织关系表
 * @version 2022-05-11 16:41:58
 */
@Data
@Table(name = "group_relation")
public class GroupRelation implements Serializable {
private static final long serialVersionUID=1L;

    /**
     *祖先id
     */
    @Column(name = "ancestor")
    private Integer ancestor;

    /**
     * 后代id
     */
    @Column(name = "descendant")
    private Integer descendant;
}
