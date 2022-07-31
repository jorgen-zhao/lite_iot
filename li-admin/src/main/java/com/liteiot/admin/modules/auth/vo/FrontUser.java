package com.liteiot.admin.modules.auth.vo;

import com.liteiot.api.vo.authority.PermissionInfo;
import lombok.Data;

import java.util.List;

/**
 * 前端用户
 */
@Data
public class FrontUser {

    /**
     * id
     */
    private String id;
    /**
     * 账号
     */
    private String username;

    /**
     * 用户名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片
     */
    private String image;

    /**
     * 菜单
     */
    private List<PermissionInfo> menus;

    /**
     * 资源
     */
    private List<PermissionInfo> elements;
}
