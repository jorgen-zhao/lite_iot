package com.liteiot.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 授权菜单节点
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AuthorityMenuTree extends TreeNode implements Serializable {

    /**
     *
     */
    String text;

    /**
     * 节点
     */
    List<AuthorityMenuTree> nodes = new ArrayList<>();

    /**
     * 图标
     */
    String icon;

    public AuthorityMenuTree(String text, List<AuthorityMenuTree> nodes) {
        this.text = text;
        this.nodes = nodes;
    }

    @Override
    public void setChildren(List<TreeNode> children) {
        super.setChildren(children);
        nodes = new ArrayList<>();
    }

    @Override
    public void add(TreeNode node) {
        super.add(node);
        AuthorityMenuTree n = new AuthorityMenuTree();
        BeanUtils.copyProperties(node, n);
        nodes.add(n);
    }
}
