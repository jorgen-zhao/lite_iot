package com.liteiot.api.vo.authority;

import lombok.Data;

/**
 * 权限检查类
 */
@Data
public class CheckPermissionInfo {
    /**
     * 请求权限资源
     */
    private PermissionInfo permissionInfo;

    /**
     * 是否有权限
     */
    private Boolean isAuth;
}
