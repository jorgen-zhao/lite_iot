package com.liteiot.common.vo.miniProgram;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class:  WXUser
 * <p>
 * Author: zhaoyg
 * Date:   2022/5/11 15:28
 * Desc:   WXUser
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class WXUser extends MiniUser {


    /**
     * 用户在微信公众号中的唯一标识
     */
    private String openId;


    /**
     * 微信公众号Id
     */
    private String mpId;
}
