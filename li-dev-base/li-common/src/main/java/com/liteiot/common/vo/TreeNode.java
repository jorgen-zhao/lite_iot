package com.liteiot.common.vo;

import lombok.Data;

import java.util.List;

/**
 * 基类: 数节点
 * 基本的树节点属性
 */
@Data
public class TreeNode {

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
    List<TreeNode> children = null;


    public void add(TreeNode node) {
        children.add(node);
    }
}
