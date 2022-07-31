package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * @version 2022-03-03 14:56:19
 */
@Data
@Table(name = "constants_config")
public class ConstantsConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 配置键
     */
    @Column(name = "config_key")
    private String configKey;

    /**
     * 配置值
     */
    @Column(name = "config_value")
    private String configValue;

    /**
     * 类型
     */
    @Column(name = "type")
    private Integer type;


}
