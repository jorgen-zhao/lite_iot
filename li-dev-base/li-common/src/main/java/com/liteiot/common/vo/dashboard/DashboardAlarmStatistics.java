package com.liteiot.common.vo.dashboard;

import lombok.Data;

/**
 * Class:  DashboardAlarmStatistics
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/13 16:14
 * Desc:   DashboardAlarmStatistics
 */
@Data
public class DashboardAlarmStatistics {

    /**
     * 组织名称
     */
    private String groupName;

    /**
     * 报警总数
     */
    private int alarmCount;
}
