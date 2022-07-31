package com.liteiot.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 配置信息表
 *
 * @version 2021-09-22 11:16:27
 * @Desc：为了序列化，需要将Integer转换为String才行
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmThresholdConfigVO {

    /**
     * 设备号
     */
    private String imei;

    /**
     * 启用的配置项（fall, swing, spin, lean）
     */
    private String enabledConfig;

    /**
     * 旋转报警阈值
     */
    private String spinThreshold;

    /**
     * 倾斜报警阈值
     */
    private String leanThreshold;

    /**
     * 位移报警阈值
     */
    private String distanceThreshold;

    /**
     * 超时报警配置
     */
    private String timeoutThreshold;

    /**
     * 频繁上报报警配置
     */
    private String frequencyThreshold;

    /**
     * 权重赋值 跌落>倾斜>旋转>摇摆
     */
    private String fallWeight = "55";
    private String leanWeight = "25";
    private String spinWeight = "15";
    private String swingWeight = "5";
}
