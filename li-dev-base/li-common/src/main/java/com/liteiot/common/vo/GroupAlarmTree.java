package com.liteiot.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 组织部门树
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupAlarmTree extends TreeNode {

    /**
     * 标签
     */
    String label;

    /**
     * 名称
     */
    String name;

    /**
     * 报警处理用户
     */
    List<SimpleUser> alarmUsers;

    /**
     * 报表接受用户
     */
    List<SimpleUser> statisticsUsers;

    /**
     * 是否存在子节点
     */
    private boolean hasChildren;
}
