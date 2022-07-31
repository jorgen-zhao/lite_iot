package com.liteiot.common.vo.dashboard;

import lombok.Data;

/**
 * Class:  DashboardMonthAlarmCount
 * <p>
 * Author: zhaoyg
 * Date:   2021/10/12 16:30
 * Desc:   DashboardMonthAlarmCount
 */

@Data
public class DashboardMonthAlarmCount {

    /**
     * 低等级总和
     */
    private int lowLevelCount;

    /**
     * 中等等级总和
     */
    private int middleLevelCount;

    /**
     * 高等级总和
     */
    private int highLevelCount;
}
