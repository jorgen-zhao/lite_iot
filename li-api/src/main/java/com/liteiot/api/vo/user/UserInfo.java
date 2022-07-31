package com.liteiot.api.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */

@Data
public class UserInfo implements Serializable {

    /**
     * id
     */
    public String id;

    /**
     * 账号
     */
    public String username;

    /**
     * 密码
     */
    public String password;

    /**
     * 用户名
     */
    public String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 更新时间
     */
    private Date updTime;
}
