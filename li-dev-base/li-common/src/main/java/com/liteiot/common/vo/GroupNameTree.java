package com.liteiot.common.vo;

import lombok.Data;

import java.util.List;

/**
 * 组织部门树
 */
@Data
public class GroupNameTree {

    /**
     * 标签
     */
    String label;

    /**
     * 名称
     */
    String name;

    /**
     * id
     */
    protected int id;

    /**
     * 父级ID
     */
    protected int parentId;

    /**
     * 子节点集合
     */
    List<GroupNameTree> children = null;


    public void add(GroupNameTree node) {
        children.add(node);
    }
}
