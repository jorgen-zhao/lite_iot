package com.liteiot.common.dto;

import lombok.Data;

/**
 * Class:  OpenDeviceInfoReq
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/27 13:44
 * Desc:   OpenDeviceInfoReq
 */

@Data
public class OpenDeviceInfoReq {

    private String appId;

    private String appKey;

    private String groupCode;

    private String imei;

    private String startTime;
    private String endTime;

    private String groupId;

    //当前页码
    private Integer page = 1;
    //每页条数
    private Integer limit = 1000;
}
