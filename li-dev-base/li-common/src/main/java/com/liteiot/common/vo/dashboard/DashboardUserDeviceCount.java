package com.liteiot.common.vo.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class:  DashboardUserDeviceCount
 * <p>
 * Author: zhaoyg
 * Date:   2021/10/12 11:06
 * Desc:   DashboardUserDeviceCount
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardUserDeviceCount {

    /**
     * 用户总数
     */
    private int userCount;

    /**
     * 设备总数
     */
    private int deviceCount;
}
