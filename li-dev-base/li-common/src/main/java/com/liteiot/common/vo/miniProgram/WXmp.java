package com.liteiot.common.vo.miniProgram;

import lombok.Data;

/**
 * Class:  WXmp
 * <p>
 * Author: zhaoyg
 * Date:   2022/5/11 18:07
 * Desc:   WXmp
 */
@Data
public class WXmp {

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户在微信公众号中的唯一标识
     */
    private String openId;


    /**
     * 微信公众号Id
     */
    private String mpId;
}
