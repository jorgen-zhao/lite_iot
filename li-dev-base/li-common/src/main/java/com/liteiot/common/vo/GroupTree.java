package com.liteiot.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织部门树
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupTree extends TreeNode {

    /**
     * 标签
     */
    String label;

    /**
     * 名称
     */
    String name;

    /**
     * 是否可拓展
     */
    boolean extendable;
}
