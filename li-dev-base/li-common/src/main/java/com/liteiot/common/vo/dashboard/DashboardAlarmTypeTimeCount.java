package com.liteiot.common.vo.dashboard;

import lombok.Data;

/**
 * Class:  DashboardAlarmTypeTimeCount
 * <p>
 * Author: zhaoyg
 * Date:   2021/10/12 18:34
 * Desc:   DashboardAlarmTypeTimeCount
 */

@Data
public class DashboardAlarmTypeTimeCount {

    /**
     * 跌落统计
     */
    private int fallCount;

    /**
     * 旋转统计
     */
    private int spinCount;

    /**
     * 松动报警
     */
    private int loosenCount;

    /**
     * 撞击报警
     */
    private int crashCount;

    /**
     * 倾斜报警
     */
    private int leanCount;
}
