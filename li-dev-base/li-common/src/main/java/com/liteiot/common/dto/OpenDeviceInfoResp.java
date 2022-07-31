package com.liteiot.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * Class:  OpenDeviceInfoReq
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/27 13:44
 * Desc:   OpenDeviceInfoReq
 */

@Data
public class OpenDeviceInfoResp {

    private String imei;
    private Integer batteryLevel;
    private Double temperature;
    private Boolean dropFlag;
    private Boolean leanFlag;
    private Integer locationSteadySignal;
    private Integer signalValue;
    private Double latitude;
    private Double longitude;
    private Integer sx;
    private Integer sy;
    private Integer sz;
    private Integer dx;
    private Integer dy;
    private Integer dz;
    private Double leanDegrees;
    private Date reportTime;
}
