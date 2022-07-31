package com.liteiot.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * Class:  PacketChartResp
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/8 11:19
 * Desc:   PacketChartResp
 */

@Data
public class PacketChartResp {

    /**
     * 设备号
     */
    private String imei;

    /**
     * 温度
     */
    private double temperature;

    /**
     * 设备电量
     */
    private int battery;

    /**
     * 设备倾斜角
     */
    private double leanDegree;

    /**
     * 上报时间
     */
    private Date reportTime;

    /**
     * 方向(单位为度数)
     */
    private Double degree;

    /**
     * 方位角
     */
    private String azimuth;
}
