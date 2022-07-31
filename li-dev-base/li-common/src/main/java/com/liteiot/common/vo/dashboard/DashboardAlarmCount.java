package com.liteiot.common.vo.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class:  DashboardAlarmCount
 * <p>
 * Author: zhaoyg
 * Date:   2021/10/12 14:50
 * Desc:   DashboardAlarmCount
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardAlarmCount {

    /**
     * 时间
     */
    private String time;

    /**
     * 未处理总数
     */
    private int unHandleCount;

    /**
     * 处理中总数
     */
    private int handlingCount;

    /**
     * 报警总数
     */
    private int alarmCount;
}
