package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 监控-设备中间表
 *
 * @version 2022-04-20 16:45:47
 */
@Data
@Table(name = "monitor_device")
public class MonitorDevice implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 监控id
     */
    @Column(name = "monitor_id")
    private Integer monitorId;

    /**
     * 设备id
     */
    @Column(name = "device_id")
    private Integer deviceId;


}
