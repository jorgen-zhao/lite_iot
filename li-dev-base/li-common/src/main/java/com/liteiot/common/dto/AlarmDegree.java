package com.liteiot.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmDegree {

    /**
     * 设备号
     */
    private String imei;

    private double leanIndex;

    private double spinIndex;

    private double distanceIndex;

    /**
     * 本次是否跌落报警
     */
    private boolean isDropAlarm;

    /**
     * 斜率运动报警
     */
    private boolean isSlopeMoveAlarm;

    /**
     * 是否存在摇摆数据
     */
    private boolean isSwingInfo;

    /**
     * 倾斜方向
     */
    private double leanAngle;
}
