package com.liteiot.common.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 可访问路由节点
 */
@Data
public class AccessRouteTree extends TreeNode implements Serializable {

    /**
     * 名称
     */
    String name;

    /**
     * 图标
     */
    String icon;

    /**
     * 路径
     */
    String path;

    /**
     * 组件
     */
    String component;

    /**
     * 组件路径
     */
    String componentPath;

    /**
     * "meta": {
     * "title": "系统设置",
     * "cache": true
     * }
     */
    JSONObject meta;

    List<AccessRouteTree> nodes = new ArrayList<>();


    @Override
    public void setChildren(List<TreeNode> children) {
        super.setChildren(children);
        nodes = new ArrayList<>();
    }

    @Override
    public void add(TreeNode node) {
        super.add(node);
        AccessRouteTree n = new AccessRouteTree();
        BeanUtils.copyProperties(node, n);
        nodes.add(n);
    }
}
