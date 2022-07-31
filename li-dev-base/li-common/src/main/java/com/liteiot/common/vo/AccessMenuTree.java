package com.liteiot.common.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 可访问菜单树
 */
@Data
public class AccessMenuTree extends TreeNode implements Serializable {

    /**
     * 名称
     */
    String title;

    /**
     * 图标
     */
    String icon;

    /**
     * 路径
     */
    String path;

    /**
     * 节点
     */
    List<AccessMenuTree> nodes = new ArrayList<>();


    @Override
    public void setChildren(List<TreeNode> children) {
        super.setChildren(children);
        nodes = new ArrayList<>();
    }

    @Override
    public void add(TreeNode node) {
        super.add(node);
        AccessMenuTree n = new AccessMenuTree();
        BeanUtils.copyProperties(node, n);
        nodes.add(n);
    }
}
