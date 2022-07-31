package com.liteiot.api.vo.authority;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限类
 */
@Data
public class PermissionInfo implements Serializable {

    /**
     * 资源编码
     */
    private String code;

    /**
     * 类型
     */
    private String type;

    /**
     * 资源路径
     */
    private String uri;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 名称
     */
    private String name;

    /**
     * 菜单
     */
    private String menu;
}
