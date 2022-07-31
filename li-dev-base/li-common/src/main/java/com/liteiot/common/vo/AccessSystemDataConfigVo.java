package com.liteiot.common.vo;

import lombok.Data;

import java.util.Date;

/**
 * Class:  AccessSystemDataConfigVo
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/24 11:09
 * Desc:   AccessSystemDataConfigVo
 */
@Data
public class AccessSystemDataConfigVo {

    private Integer id;

    /**
     * 接入系统名称
     */
    private String accessSystemName;


    /**
     * 接入系统id
     */
    private Integer accessSystemId;


    /**
     * 设备号
     */
    private String imei;


    /**
     * 选择的属性名称
     */
    private String attributeValues;


    /**
     * 创建时间
     */
    private Date crtTime;
}
