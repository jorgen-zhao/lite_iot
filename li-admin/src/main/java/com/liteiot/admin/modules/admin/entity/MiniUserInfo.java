package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ace on 2017/8/22.
 */
@Data
public class MiniUserInfo {
    /**
     * 头像地址
     */
    public String avatarUrl;

    /**
     * 昵称
     */
    public String userName;

    /**
     * 角色列表
     */
    public List<Role> userRoles;

    /**
     * 用户当前操作的group
     */
    public Group currentGroup;

    /**
     * 用户动作权限编码
     */
    private List<String> userPermissions = new ArrayList<>();

    /**
     * 是否为管理员
     */
    private boolean isAdmin;

}
