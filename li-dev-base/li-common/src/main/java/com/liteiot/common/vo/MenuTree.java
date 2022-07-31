package com.liteiot.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 菜单树
 * 与前端树组件对应
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MenuTree extends TreeNode {

    /**
     * 图标
     */
    private String icon;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 请求路径
     */
    private String href;

    /**
     * 是否展开
     */
    private boolean spread = false;

    /**
     * 路径
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * 资源
     */
    private String authority;

    /**
     * 是否重定向
     */
    private String redirect;

    /**
     * 资源编码
     */
    private String code;

    /**
     * 类型
     */
    private String type;

    /**
     * 标签
     */
    private String label;

    public MenuTree(int id, String name, int parentId) {
        this.id = id;
        this.parentId = parentId;
        this.title = name;
        this.label = name;
    }

    public MenuTree(int id, String name, MenuTree parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.title = name;
        this.label = name;
    }
}
