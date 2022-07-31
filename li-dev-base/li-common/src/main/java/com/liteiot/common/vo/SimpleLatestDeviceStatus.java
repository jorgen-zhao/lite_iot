package com.liteiot.common.vo;

import lombok.Data;

import java.util.Date;

/**
 * Class:  SimpleLatestDeviceStatus
 * <p>
 * Author: zhaoyg
 * Date:   2022/3/8 11:19
 * Desc:   SimpleLatestDeviceStatus
 */
@Data
public class SimpleLatestDeviceStatus {

    private String imei;

    private Date createTime;
}
