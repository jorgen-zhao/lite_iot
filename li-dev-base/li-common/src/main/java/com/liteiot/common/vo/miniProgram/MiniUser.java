package com.liteiot.common.vo.miniProgram;

import lombok.Data;

/**
 * Class:  MiniUser
 * <p>
 * Author: zhaoyg
 * Date:   2021/12/27 17:22
 * Desc:   MiniUser
 */
@Data
public class MiniUser {


    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 用户密码
     */
    private String password;
}
