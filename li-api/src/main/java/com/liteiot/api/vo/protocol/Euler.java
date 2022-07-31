package com.liteiot.api.vo.protocol;

import lombok.Data;

/**
 * Class:  Euler
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/13 16:53
 * Desc:   Euler
 */

@Data
public class Euler {

    /**
     * 滚动角
     */
    private double roll;

    /**
     * 俯仰角
     */
    private double pitch;

    /**
     * 偏行角 航向
     */
    private double yaw;
}
