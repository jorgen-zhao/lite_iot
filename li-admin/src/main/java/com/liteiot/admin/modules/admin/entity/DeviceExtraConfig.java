package com.liteiot.admin.modules.admin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @version 2022-02-17 14:22:11
 */
@NoArgsConstructor
@Data
@Table(name = "device_extra_config")
public class DeviceExtraConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 设备号
     */
    @Column(name = "imei")
    private String imei;

    /**
     * 配置项
     */
    @Column(name = "config_key")
    private String configKey;

    /**
     * 配置数值
     */
    @Column(name = "config_value")
    private Double configValue;

    /**
     * 类型（0 - 旋转配置项 1 - 倾斜配置项 2 - 旋转基准值 3 - 倾斜基准值）
     */
    @Column(name = "type")
    private Integer type;

    public DeviceExtraConfig(String imei, int type, String configKey, Double configValue) {
        super();
        this.imei = imei;
        this.configKey = configKey;
        this.configValue = configValue;
        this.type = type;
    }


}
