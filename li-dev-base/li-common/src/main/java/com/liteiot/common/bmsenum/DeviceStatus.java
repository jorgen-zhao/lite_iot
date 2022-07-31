package com.liteiot.common.bmsenum;

/**
 * Class:  DeviceStatusEnum
 * <p>
 * Author: zhaoyg
 * Date:   2022/5/17 15:54
 * Desc:   DeviceStatusEnum
 */
public interface DeviceStatus {

    /**
     * 0-生产 1-库存 2-未激活 3-已激活 4-停用 5-维修 6-报废
     * 生产状态
     */
    int STATUS_PRODUCTION = 0;

    /**
     * 0-生产 1-库存 2-未激活 3-已激活 4-停用 5-维修 6-报废
     * 库存状态
     */
    int STATUS_STOCK = 1;

    /**
     * 0-生产 1-库存 2-未激活 3-已激活 4-停用 5-维修 6-报废
     * 非激活状态
     */
    int STATUS_UNACTIVATED = 2;

    /**
     * 0-生产 1-库存 2-未激活 3-已激活 4-停用 5-维修 6-报废
     * 激活状态
     */
    int STATUS_ACTIVATED = 3;
}
