package com.liteiot.common.dto;

import com.liteiot.common.bmsenum.OppositeStatus;
import lombok.Data;

import java.util.Date;

/**
 * Class:  AccessSystemPushData
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/23 13:40
 * Desc:   AccessSystemPushData
 */

@Data
public class AccessSystemPushData {

    /**
     * 设备号
     */
    private String imei;

    /**
     * 接入系统Id
     */
    private Integer accessSystemId;

    /**
     * 接入系统名称
     */
    private String accessSystemName;

    /**
     * 选择的属性名称
     */
    private String attributeValues;

    /**
     * 是否启用; {@link OppositeStatus}
     */
    private Integer enabled;

    /**
     * 截止时间
     */
    private Date deadlineDate;

    /**
     * 推送url
     */
    private String url;


    /**
     * appId
     */
    private String appId;


    /**
     * appKey
     */
    private String appKey;
}
